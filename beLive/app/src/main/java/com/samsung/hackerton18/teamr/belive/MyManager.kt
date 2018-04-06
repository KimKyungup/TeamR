package com.samsung.hackerton18.teamr.belive

import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.data.account.AccountEntity
import com.samsung.hackerton18.teamr.belive.web3j.KeyStore
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert
import java.math.BigInteger
import kotlin.concurrent.thread


interface AccountBalanceListener {
    fun accountBalance(accountHash:String, balance:String)
}

/**
 * Created by up on 2018-04-03.
 */
class MyManager(private val appDatabase:AppDatabase, private val keyStore: KeyStore) :AnkoLogger{
    var flagInit = false
    lateinit var myAccount :AccountEntity
    lateinit var credentials:Credentials
    var balanceString : String = "0"

    fun setAccount(account:AccountEntity){
        myAccount = account
        balanceString = Convert.fromWei(myAccount.balance.toString(), Convert.Unit.ETHER).toString()
        credentials = keyStore.getCredentials(myAccount.address)
        flagInit = true

        if(listener != null) listener!!.accountBalance(myAccount.address, balanceString)
    }

    private var listener: AccountBalanceListener? = null

    fun setListener(listener: AccountBalanceListener) {
        this.listener = listener
    }

    public fun init(){
        keyStore.refreshKeyList()
        if(keyStore.getKeyCnt() == 0){
            async(UI){
                async(CommonPool){
                    val filename = keyStore.newKey("default")
                    val hash = keyStore.getKeyAddress(filename)
                    //credentials = keyStore.getCredentials(hash)

                    val tempAccount = AccountEntity(hash,filename,"default","default account", BigInteger("0"))

                    setAccount(tempAccount)

                    appDatabase.accountDao().upsertAccount(tempAccount)
                    info("new default account ${myAccount.address}")
                }.await()
            }
        }
        else{
            async(UI){
                async(CommonPool){
                    try {
                        val dbResult = appDatabase.accountDao().loadAll()
                        if(dbResult.isNotEmpty()) {

                            setAccount(dbResult[0])

                            info("my default account ${myAccount.address}")
                        }
                        else{
                            info("In DB, there is no Account. DB version might be changed.")
                            val data = keyStore.getKeyArray()
                            var cnt = 0
                            for(item in data){
                                val tempAccount = AccountEntity(item.address,item.filename,"default"+cnt.toString(),"RestoredAccount"+cnt.toString(), BigInteger("0"))
                                if(cnt == 0) {
                                    setAccount(tempAccount)
                                }
                                appDatabase.accountDao().upsertAccount(tempAccount)
                            }
                            info("${cnt.toString()} account restored")
                        }
                    }
                    catch(e: Exception){
                        e.printStackTrace()
//                        error("DB: Acccount LoadAll Error ${e}")
//                        Log.e("error","DB: Acccount LoadAll Error "+e)
                    }
                }.await()
            }
        }
    }

    public fun balanceUpdator(){
        async(UI) {
            while (true) {
                try {
                    if(flagInit) {
                        val web3 = async(CommonPool) { Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz")) }.await()
                        val ethGetBalance = async(CommonPool) { web3.ethGetBalance(myAccount.address, DefaultBlockParameterName.LATEST).sendAsync().get() }.await()
                        val ether = Convert.fromWei(ethGetBalance.balance.toString(), Convert.Unit.ETHER)

                        if (myAccount.balance != ethGetBalance.balance) {
                            myAccount.balance = ethGetBalance.balance
                            balanceString = ether.toString()

                            if(listener != null){
                                listener!!.accountBalance(myAccount.address, balanceString)
                            }

                            async(CommonPool) { appDatabase.accountDao().upsertAccount(myAccount) }.await()
                        }

                        info("Balance is Updated. balance:${ether.toString()}")
                        delay(10000)
                    }
                    else{
                        delay(3000)
                    }
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}
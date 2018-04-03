package com.samsung.hackerton18.teamr.belive

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.data.account.AccountEntity
import com.samsung.hackerton18.teamr.belive.web3j.KeyStore
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.web3j.crypto.Credentials
import java.math.BigInteger

/**
 * Created by up on 2018-04-03.
 */
class MyManager(private val appDatabase:AppDatabase, private val keyStore: KeyStore) :AnkoLogger{
    lateinit var myAccount :AccountEntity
    lateinit var credentials:Credentials

    public fun init(){
        keyStore.refreshKeyList()
        if(keyStore.getKeyCnt() == 0){
            async(UI){
                async(CommonPool){
                    val filename = keyStore.newKey("default")
                    val hash = keyStore.getKeyAddress(filename)
                    credentials = keyStore.getCredentials(hash)

                    val tempAccount = AccountEntity(hash,filename,"default","default account", BigInteger("0"))
                    myAccount = tempAccount
                    appDatabase.accountDao().upsertAccount(tempAccount)
                    info("new default account ${myAccount.address}")
                }.await()
            }
        }
        else{
            async(UI){
                async(CommonPool){
                    val dbResult = appDatabase.accountDao().loadAll()
                    if(dbResult.isNotEmpty()) {
                        myAccount = dbResult[0]
                        info("my default account ${myAccount.address}")
                    }
                    else{
                        error("In DB, there is no Account. Recontructing DB is needed")
                        //TODO : Recontrcuting code
                    }
                }.await()
            }
        }
    }
}
package com.samsung.hackerton18.teamr.belive.web3j

import com.samsung.hackerton18.teamr.belive.MyManager
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.data.contractTx.ContractTxEntity
import com.samsung.hackerton18.teamr.belive.fragment.smartContract.contracts_class.TTS_Contract
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService
import java.math.BigInteger
import kotlin.concurrent.thread

/**
 * Created by up on 2018-04-04.
 */
class SmartContract(private val appDatabase: AppDatabase, private val myManager: MyManager) :AnkoLogger{

    val ttsContractHash = "0x47593a48186df37f8d505516fd8de582b444c236"

    fun joinTTS_Contract(text:String){
//        async(UI) {
//            async(CommonPool) {
        thread {
            try {
                val web3 = Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))
                val gasPrice = web3.ethGasPrice().sendAsync().get().gasPrice
                val gasLimit = BigInteger.valueOf(300000)
                val contract = TTS_Contract.load(ttsContractHash, web3, myManager.credentials, gasPrice, gasLimit)
                if (contract.isValid) {

                    val currentSeconds = System.currentTimeMillis() //LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toEpochSecond()
                    //val currentEpochSeconds2 = LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toEpochSecond()
                    var tx = ContractTxEntity("",
                            currentSeconds,
                            myManager.myAccount.address,
                            ttsContractHash,
                            "tts",
                            text,
                            "pending",
                            "")

                    appDatabase.contractTxDao().upsert(tx)

                    info("check valid contract")
                    val result = contract.setNewOrder(text).sendAsync().get()
                    info("got result tx hash: ${result.transactionHash}")
                    tx.hash = result.transactionHash;
                    tx.status = "Complete"
                    appDatabase.contractTxDao().upsert(tx)
                    info("Update DB")
                }
                else{
                    info("Unvalid Smart Contract")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
//            }
  //      }
    }
}
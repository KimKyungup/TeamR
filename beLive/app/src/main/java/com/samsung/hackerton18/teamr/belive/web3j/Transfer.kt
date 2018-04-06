package com.samsung.hackerton18.teamr.belive.web3j

import com.samsung.hackerton18.teamr.belive.MyManager
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.data.contractTx.ContractTxEntity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService
import org.web3j.tx.Transfer
import org.web3j.utils.Convert
import java.math.BigDecimal
import kotlin.concurrent.thread

class Transfer(private val appDatabase: AppDatabase, private val myManager: MyManager) : AnkoLogger {

    fun ether(toAddress : String, amount :String){
        val fromAddress = myManager.myAccount.address

        val valueToSend = amount.toDouble()

        thread() {
            val credentials = myManager.credentials

            val web3 = Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))    //"https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"

            //val ethGetTransactionCount = web3.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).sendAsync().get()
            //val nonce = ethGetTransactionCount.transactionCount

            /*rawTransaction  = RawTransaction.createEtherTransaction(
                    nonce, <gas price>, <gas limit>, <toAddress>, <value>);*/

            val currentSeconds = System.currentTimeMillis() //LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toEpochSecond()
            //val currentEpochSeconds2 = LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toEpochSecond()
            var tx = ContractTxEntity("",
                    currentSeconds,
                    myManager.myAccount.address,
                    toAddress,
                    "transfer",
                    amount,
                    "pending",
                    "")

            appDatabase.contractTxDao().upsert(tx)

            info("Transfer.sendFunds")
            val transactionReceipt = Transfer.sendFunds(
                    web3, credentials, toAddress,
                    BigDecimal.valueOf(valueToSend), Convert.Unit.ETHER).sendAsync().get()

            info("Send TX Requested Hash ${transactionReceipt.transactionHash} BlockHash ${transactionReceipt.blockHash} BlockNumber ${transactionReceipt.blockNumber}")

            tx.hash = transactionReceipt.transactionHash;
            tx.status = "complete"

            appDatabase.contractTxDao().upsert(tx)
            info("Update DB")

        }
    }
}
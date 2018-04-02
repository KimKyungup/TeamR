package teamr.hackerton18.samsung.fragmentex


import android.os.Bundle
import android.support.v4.app.Fragment  //import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test.*
import org.jetbrains.anko.*
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.Utf8String
import org.web3j.protocol.Web3j
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.utils.Convert

import teamr.hackerton18.samsung.fragmentex.manager.MyAccountManager
import kotlin.concurrent.thread
import org.web3j.protocol.core.methods.request.Transaction
import java.math.BigDecimal
import java.math.BigInteger
import org.web3j.protocol.core.methods.response.EthSendTransaction
import java.util.ArrayList
import org.web3j.tx.ManagedTransaction.GAS_PRICE
import teamr.hackerton18.samsung.fragmentex.solidityGenerated.HakathonDemo2018


/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment(),AnkoLogger {
    lateinit var web3 : Web3j

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button1.setOnClickListener {
            web3 = Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))
            val web3ClientVersion = web3.web3ClientVersion().sendAsync().get()

            val clientVersion = web3ClientVersion.getWeb3ClientVersion()


            val ethGetBalance = web3
                    .ethGetBalance(MyAccountManager.addressHash, DefaultBlockParameterName.LATEST)
                    .sendAsync().get()

            val ether = Convert.fromWei(ethGetBalance.balance.toString(), Convert.Unit.ETHER)

            val ethGetBalance2 = web3
                    .ethGetBalance("0xAf44747484436cc65327794cD1B12f085bea618a", DefaultBlockParameterName.LATEST)
                    .sendAsync().get()

            val ether2 = Convert.fromWei(ethGetBalance2.balance.toString(), Convert.Unit.ETHER)

            textView.append("clientVersion : " + clientVersion+"\n")
            textView.append("Account Hash : " + MyAccountManager.addressHash+"\n")
            textView.append("Account Balance : " + ether+" ETH\n")
            textView.append("Account Balance : " + ether2+" ETH\n")
        }

        var earliestBlock : EthBlock.Block
        var latestBlock : EthBlock.Block

        button2.setOnClickListener{
            val fromAddress = MyAccountManager.addressHash
            val contractAddress = "0x47593a48186df37f8d505516fd8de582b444c236"

            thread() {
                val inputParameters = ArrayList<Type<*>>()
                val usersId = Utf8String("My name is cup kim")
                inputParameters.add(usersId)
                val function = Function("setNewOrder",
                        inputParameters,
                        emptyList())
                val encodedFunction = FunctionEncoder.encode(function)
                val credentials = (activity as MainActivity).keyStore.getCredentials(fromAddress)

                val web3 = Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))    //"https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"
                val gasPrice = web3.ethGasPrice().sendAsync().get().gasPrice
                val gasLimit = BigInteger.valueOf(300000)
                val contract = HakathonDemo2018.load(
                        contractAddress, web3, credentials, gasPrice, gasLimit)

                if(contract.isValid) {
                    info("valid contract")
                    val result = contract.setNewOrder("I am Kup. Nice to meet you.").sendAsync().get()
                    info("result  tx hash: ${result.transactionHash}")
                }
                else
                {
                    info("not valid contract")
                }
                /*
                val ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.LATEST).sendAsync().get()
                val nonce = ethGetTransactionCount.transactionCount
                val transaction = Transaction.createFunctionCallTransaction(
                        fromAddress,
                        nonce,
                        gasPrice,
                        BigInteger.valueOf(30000),
                        contractAddress,
                        BigInteger.valueOf(0),
                        encodedFunction)

                val transactionResponse = web3.ethSendTransaction(transaction).sendAsync().get()

                info("SmartContact call TX Hash ${transactionResponse.transactionHash}")
                */
            }

            /*
            val response = web3.ethCall(
                    Transaction.createEthCallTransaction(MyAccountManager.addressHash, "0x47593a48186df37f8d505516fd8de582b444c236", encodedFunction),
            DefaultBlockParameterName.LATEST)
            .sendAsync().get();
            */

            /*
            web3 = Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))
            val earliestBlockResponse = web3.ethGetBlockByNumber(DefaultBlockParameterName.EARLIEST, false).sendAsync().get()
            earliestBlock = earliestBlockResponse.block
            info("EARLIEST block number: ${earliestBlock.number}")

            val latestBlockResponse = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).sendAsync().get()
            latestBlock = latestBlockResponse.block
            info("LATEST block number: ${latestBlock.number}")
            */
        }

        button3.setOnClickListener {
            /*
            val subscription = web3
                    .catchUpToLatestTransactionObservable(DefaultBlockParameterName.EARLIEST)
                    //.replayTransactionsObservable(startBlock, endBlock)
                    //.filter({ transaction -> KnownPeers.initialPeer.getAddress().equals(transaction.from()) || KnownPeers.initialPeer.getAddress().equals(transaction.to()) })
                    .doOnError({ throwable -> error("Error occurred ${throwable}") })
                    .doOnCompleted({ info("Completed") })
                    .doOnEach({ notification -> info("OnEach") })
                    .subscribe({ transaction ->
                        info("Transaction ${transaction.hash} of block ${transaction.blockNumber}. From ${transaction.from} to ${transaction.to}")
                    })
                    */


            thread() {
                web3 = Web3jFactory.build(HttpService("http://192.168.1.4:8545"))    //"https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"

                info("start")
                val subscription = web3.blockObservable(false).subscribe({ block ->
                    info("Sweet, block number ${block.block.number} has just been created")
                })
            }
        }

    }

}// Required empty public constructor

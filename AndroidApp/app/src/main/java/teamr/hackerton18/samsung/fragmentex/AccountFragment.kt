package teamr.hackerton18.samsung.fragmentex



import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment  //import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test.*
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.web3j.crypto.RawTransaction
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService
import teamr.hackerton18.samsung.fragmentex.manager.MyAccountManager
import kotlin.concurrent.thread
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.EthGetTransactionCount
import org.web3j.utils.Convert
import org.web3j.tx.Transfer
import org.web3j.tx.Transfer.sendFunds
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.crypto.WalletUtils
import java.math.BigDecimal


/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment(), AnkoLogger {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.subtitle = getString(R.string.address_subtitle)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        from_address.text = MyAccountManager.addressHash

        fabDone.onClick {
            val fromAddress = MyAccountManager.addressHash
            val toAddress = to_address_value.text.toString()
            val valueToSend = amount_input.text.toString().toDouble()

            thread() {
                val credentials = (activity as MainActivity).keyStore.getCredentials(fromAddress)

                val web3 = Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))    //"https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"

                //val ethGetTransactionCount = web3.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST).sendAsync().get()
                //val nonce = ethGetTransactionCount.transactionCount

                /*rawTransaction  = RawTransaction.createEtherTransaction(
                        nonce, <gas price>, <gas limit>, <toAddress>, <value>);*/
                info("Transfer.sendFunds")
                val transactionReceipt = Transfer.sendFunds(
                        web3, credentials, toAddress,
                        BigDecimal.valueOf(valueToSend), Convert.Unit.ETHER).sendAsync().get()

                info("Send TX Requested Hash ${transactionReceipt.transactionHash} BlockHash ${transactionReceipt.blockHash} BlockNumber ${transactionReceipt.blockNumber}")
            }

            Snackbar.make(view!!, "Transaction is sent.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }
}// Required empty public constructor

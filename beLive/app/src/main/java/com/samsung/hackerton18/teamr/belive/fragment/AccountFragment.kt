package com.samsung.hackerton18.teamr.belive.fragment


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
//import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.samsung.hackerton18.teamr.belive.MainActivity
import com.samsung.hackerton18.teamr.belive.MyManager

import com.samsung.hackerton18.teamr.belive.R
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() {
    private val myManager: MyManager by LazyKodein(appKodein).instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.subtitle = "Account"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        myBalance.text = myManager.balanceString + " ETH"
        myAddressHash.text = myManager.myAccount.address

        btn_to_copy.onClick {
            val clipboard = (activity as MainActivity).getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            try {
                val clip = ClipData.newPlainText("Copied Text", myManager.myAccount.address)
                clipboard.primaryClip = clip
                Snackbar.make(view!!, "Copied to clipboard.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }catch(e:Exception){
                e.printStackTrace()
            }
        }
    }

}// Required empty public constructor

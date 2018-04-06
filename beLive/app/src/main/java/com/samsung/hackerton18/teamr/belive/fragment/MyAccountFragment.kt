package com.samsung.hackerton18.teamr.belive.fragment


import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_my_account.*
import org.web3j.crypto.Keys
import org.web3j.crypto.ECKeyPair




/**
 * A simple [Fragment] subclass.
 */
class MyAccountFragment : Fragment() {
    private val myManager: MyManager by LazyKodein(appKodein).instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.subtitle = "MyAccount"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as MainActivity).supportActionBar?.subtitle = "MyAccount"
//        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        public_address.setText(myManager.myAccount.address)

        val ecKeyPair = myManager.credentials.ecKeyPair
        val privateKeyInDec = ecKeyPair.privateKey

        val sPrivatekeyInHex = privateKeyInDec.toString(16)

        private_address.setText(sPrivatekeyInHex)
    }

}// Required empty public constructor

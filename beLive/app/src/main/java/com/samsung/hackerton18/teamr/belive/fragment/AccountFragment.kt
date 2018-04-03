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
import com.samsung.hackerton18.teamr.belive.MyManager

import com.samsung.hackerton18.teamr.belive.R
import kotlinx.android.synthetic.main.fragment_account.*


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

        myBalance.text = myManager.myAccount.balance.toString()
        myAddressHash.text = myManager.myAccount.address
    }
}// Required empty public constructor

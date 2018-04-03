package com.samsung.hackerton18.teamr.belive.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
//import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samsung.hackerton18.teamr.belive.R


/**
 * A simple [Fragment] subclass.
 */
class MyAccountFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false)
    }

}// Required empty public constructor

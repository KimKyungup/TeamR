package com.samsung.hackerton18.teamr.belive.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
//import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.samsung.hackerton18.teamr.belive.R
import android.content.ContentValues.TAG
import com.samsung.hackerton18.teamr.belive.MainActivity
import kotlinx.android.synthetic.main.fragment_logo.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class LogoFragment : Fragment() , AnkoLogger{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logo, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logo.onClick {
            (activity as MainActivity).showHistoryFragment()

            val uiOptions = (activity as MainActivity).window.decorView.systemUiVisibility
            val newUiOptions = uiOptions xor  View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            (activity as MainActivity).window.decorView.systemUiVisibility = newUiOptions
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

    }

}// Required empty public constructor

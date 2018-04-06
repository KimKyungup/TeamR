package com.samsung.hackerton18.teamr.belive.fragment.smartContract


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
//import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.samsung.hackerton18.teamr.belive.R
import android.text.Spannable
import android.support.design.widget.Snackbar
import android.text.style.BackgroundColorSpan
import android.text.SpannableString
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.samsung.hackerton18.teamr.belive.MainActivity
import com.samsung.hackerton18.teamr.belive.fragment.HistoryFragment
import com.samsung.hackerton18.teamr.belive.web3j.SmartContract
import kotlinx.android.synthetic.main.fragment_tts_contract.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 */
class TTS_ContractFragment : Fragment(), AnkoLogger {
    private val smartContract: SmartContract by LazyKodein(appKodein).instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tts_contract, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.subtitle = "TTS Contract"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mainString = "I Want to\nSend a Text"
        val subString = "Send a Text"

        if (mainString.contains(subString)) {
            val startIndex = mainString.indexOf(subString)
            val endIndex = startIndex + subString.length
            val spannableString = SpannableString(mainString)
            val color = resources.getColor(R.color.colorPrimary,null)
            val test = com.samsung.hackerton18.teamr.belive.R.color.colorPrimary
            val backgroundSpan = BackgroundColorSpan(Color.parseColor("#34E2AE"))

            spannableString.setSpan(backgroundSpan, startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            contractSentence.text = spannableString
        }


        fab.onClick {
            if(textToSend.text.isEmpty()){

                toast("type something, plz.")
                return@onClick

            }

            smartContract.joinTTS_Contract(textToSend.text.toString())

            Snackbar.make(view!!, "Transaction is sent.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show ()
            (activity as MainActivity).replaceFragment(HistoryFragment())  //showHistoryFragment()
        }
    }

}// Required empty public constructor

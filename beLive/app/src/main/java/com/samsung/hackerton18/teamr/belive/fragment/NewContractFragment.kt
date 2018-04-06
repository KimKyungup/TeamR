package com.samsung.hackerton18.teamr.belive.fragment


import android.os.Bundle
import android.support.v4.app.Fragment //change     import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samsung.hackerton18.teamr.belive.MainActivity
import com.samsung.hackerton18.teamr.belive.R
import com.samsung.hackerton18.teamr.belive.fragment.smartContract.TTS_ContractFragment
import kotlinx.android.synthetic.main.fragment_new_contract.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class NewContractFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_contract, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.subtitle = "New Contract"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (activity as MainActivity).supportActionBar?.subtitle = "New Contract"
//        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tts_contract.onClick {
            (activity as MainActivity).replaceFragment(TTS_ContractFragment())  //showTTS_ContractFragment()
        }
    }

}// Required empty public constructor

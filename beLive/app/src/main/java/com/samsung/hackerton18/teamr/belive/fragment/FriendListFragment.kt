package com.samsung.hackerton18.teamr.belive.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
//import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samsung.hackerton18.teamr.belive.MainActivity
import com.samsung.hackerton18.teamr.belive.R


/**
 * A simple [Fragment] subclass.
 */
class FriendListFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.subtitle = "FriendList"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}// Required empty public constructor

package com.samsung.hackerton18.teamr.belive.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
//import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.samsung.hackerton18.teamr.belive.MainActivity
import com.samsung.hackerton18.teamr.belive.R
import com.samsung.hackerton18.teamr.belive.adapter_holder.DefaultSelectionRecyclerAdapter
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import kotlinx.android.synthetic.main.fragment_friend_list.*

import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import com.samsung.hackerton18.teamr.belive.fragment.smartContract.TTS_ContractFragment
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class FriendListFragment : Fragment() {
    private val appDatabase: AppDatabase by LazyKodein(appKodein).instance()

    lateinit var adapter : DefaultSelectionRecyclerAdapter

//    private val adapter by lazy{
//        DefaultSelectionRecyclerAdapter(context).apply {
//            recycler_view.adapter = this
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_list, container, false)
    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        (activity as MainActivity).supportActionBar?.subtitle = "FriendList"
//        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//    }


//    override fun onResume() {
//        super.onResume()
//        (activity as MainActivity).supportActionBar?.subtitle = "FriendList"
//        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//
//    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.subtitle = "FriendList"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = DefaultSelectionRecyclerAdapter(context)
        recycler_view.adapter = adapter

        recycler_view.layoutManager = LinearLayoutManager(context)

        //Custom Divider -_-; failed.
//        val itemDecorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
//        itemDecorator.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider))
//        recycler_view.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))

        addBtn.onClick {
            val transaction = (activity as MainActivity).manager.beginTransaction()
            val fragment = AddFriendFragment()

            transaction.replace(R.id.fragmentHolder,fragment)
            transaction.addToBackStack("Friendlist")
            transaction.commit()
        }

        var friendList = listOf("YongJae","NoKyung","LeeJu", "HyunJin")
        //var clickListenerList = listOf("add", "KimKyungup","NoKyung","LeeJu", "HyunJin")

        adapter.list = friendList
        adapter.updateList()
        adapter.notifyDataSetChanged()


    }

}// Required empty public constructor

package com.samsung.hackerton18.teamr.belive.fragment


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateUtils
//import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.samsung.hackerton18.teamr.belive.MainActivity
import com.samsung.hackerton18.teamr.belive.R
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.adapter_holder.SmartContractRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.concurrent.thread


/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment(), AnkoLogger {
    private val appDatabase: AppDatabase by LazyKodein(appKodein).instance()

    private val adapter by lazy{
        SmartContractRecyclerAdapter(context , appDatabase = appDatabase).apply {
            recycler_view.adapter = this
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.subtitle = "History"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //<Recycleing View>
        recycler_view.layoutManager = LinearLayoutManager(context)

        appDatabase.contractTxDao().loadAllLiveData().observe(this, Observer { items->
            if(items != null){
                adapter.list = items
                adapter.updateList()
                //refresh fragment
//                val ft = fragmentManager.beginTransaction()
//                ft.detach(this).attach(this).commit()
            }
        })

        thread {
            val result = appDatabase.contractTxDao().loadAll()

            info("Contract TX List in DB")
            for (item in result) {
                val timeString = DateUtils.getRelativeDateTimeString(context, item.timeStamp,
                        DateUtils.SECOND_IN_MILLIS,
                        DateUtils.WEEK_IN_MILLIS,
                        0
                )
                info("${item.timeStamp} ${item.hash} ${timeString}")
            }
        }
    }
}// Required empty public constructor

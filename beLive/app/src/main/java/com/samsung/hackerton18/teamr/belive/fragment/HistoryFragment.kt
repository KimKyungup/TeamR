package com.samsung.hackerton18.teamr.belive.fragment


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
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
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.concurrent.thread
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration


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

//        val itemDecorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
//        itemDecorator.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider))
//
//        recycler_view.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))


        appDatabase.contractTxDao().loadAllLiveData().observe(this, Observer { items->
            if(items != null){
                info("Got new contract list updated")
                adapter.list = items
                adapter.updateList()
                adapter.notifyDataSetChanged()
                //refresh fragment
//                val ft = fragmentManager.beginTransaction()
//                ft.detach(this).attach(this).commit()
            }
        })

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                val currentContract = adapter.displayList[viewHolder.adapterPosition] // .get(viewHolder.adapterPosition)
                fun changeDeleteState(state: Boolean) {
//                    launch {
//                        upsert(appDatabase, currentContract.copy(showInList = state))
//                    }
                    info("Swipe Event")
                }
                changeDeleteState(false)
                Snackbar.make(view!!, "The contract is deleted.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("UNDO", { changeDeleteState(false) })  //getString(R.string.undo)
                        .show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recycler_view)


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

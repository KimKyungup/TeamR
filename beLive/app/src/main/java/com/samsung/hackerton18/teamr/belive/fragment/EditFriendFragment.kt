package com.samsung.hackerton18.teamr.belive.fragment


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance

import com.samsung.hackerton18.teamr.belive.R
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.data.friend.FriendEntity
import kotlinx.android.synthetic.main.fragment_edit_friend.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EditFriendFragment : Fragment(), AnkoLogger {

    private val appDatabase: AppDatabase by LazyKodein(appKodein).instance()

    var friend : FriendEntity? = null
    var friendAddress : String? = null
    var friendName : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_friend, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val index = args.getInt("index")

        info("index = ${index.toString()}")

        appDatabase.friendDao().loadAllLiveData().observe(this, Observer{
            if(it != null){
                info("Got new Friend List")

                friend = it[index]
                friendName = it[index].name
                friendAddress = it[index].address

                friend_name.text = friendName
                friend_address.setText(friendAddress)
            }
        })

        done.setOnClickListener {
            val addressTyped = friend_address.text.toString()
            if(friendAddress!!.equals(addressTyped) or addressTyped.isEmpty()){
                Snackbar.make(view!!, "Check address typed", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
            else{
                friend!!.address = addressTyped

                async(CommonPool) {
                    appDatabase.friendDao().upsertAccount(friend!!)
                }


            }
        }
    }

}

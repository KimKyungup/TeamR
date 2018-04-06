package com.samsung.hackerton18.teamr.belive.fragment


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.samsung.hackerton18.teamr.belive.MainActivity
import com.samsung.hackerton18.teamr.belive.MyManager

import com.samsung.hackerton18.teamr.belive.R
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.data.friend.FriendEntity
import kotlinx.android.synthetic.main.fragment_add_friend.*
import kotlinx.android.synthetic.main.fragment_tts_contract.view.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.sdk25.coroutines.onClick




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AddFriendFragment : Fragment() {
    private val appDatabase: AppDatabase by LazyKodein(appKodein).instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_friend, container, false)
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.subtitle = "AddFriend"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onResume()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        done.onClick {
            if(name.text.isEmpty() or address.text.isEmpty()){
                Snackbar.make(view!!, "Type name and address.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                return@onClick
            }
            async(UI){
                async(CommonPool){
                    val friend = FriendEntity(System.currentTimeMillis(), name.text.toString(),address.text.toString(),"none")
                    appDatabase.friendDao().upsertAccount(friend)
                }.await()
            }

            Snackbar.make(view!!, "New Friend is added.", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            val imm = (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0);

            (activity as MainActivity).popFragment()
        }
    }
}

package teamr.hackerton18.samsung.fragmentex.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import teamr.hackerton18.samsung.fragmentex.R
import teamr.hackerton18.samsung.fragmentex.data.AddressItem
import kotlinx.android.synthetic.main.address_list_item.*
import kotlinx.android.synthetic.main.address_list_item.view.*

/**
 * Created by up on 2018-03-29.
 */
class AddressRecyclerAdapter(val context: Context, val addressData: MutableList<AddressItem>) : RecyclerView.Adapter<AddressRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.address_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return addressData.size
    }

    override fun onBindViewHolder(holder: AddressRecyclerAdapter.ViewHolder?, position: Int) {
        holder!!.addressHash.text = addressData[position].address
        holder!!.addressNote.text = addressData[position].note
    }

    class ViewHolder(view : View):RecyclerView.ViewHolder(view){
        // Holds the TextView that will add each animal to
        val addressHash = view.address_hash
        val addressNote = view.address_note
    }
}



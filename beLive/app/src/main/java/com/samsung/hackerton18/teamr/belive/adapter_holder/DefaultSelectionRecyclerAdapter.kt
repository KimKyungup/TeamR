package com.samsung.hackerton18.teamr.belive.adapter_holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.samsung.hackerton18.teamr.belive.R

class DefaultSelectionRecyclerAdapter(val context:Context)
                                    :RecyclerView.Adapter<DefaultSelectionViewHolder>() {

    var list = listOf<String>()
    var displayList = listOf<String>()

    override fun getItemCount() = displayList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DefaultSelectionViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.default_selection_list_item, parent,false)
        return DefaultSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DefaultSelectionViewHolder, position: Int) {
        holder.bind(context, displayList[position])
    }

    fun updateList(){
        displayList = list
    }

    fun setItemList(itemList : List<String>){
        list = itemList
    }
}
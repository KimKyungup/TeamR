package com.samsung.hackerton18.teamr.belive.adapter_holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.default_selection_list_item.view.*

class DefaultSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(context: Context, text: String) {

        itemView.item.text = text

//
//        itemView.address_starred.setOnClickListener {
//            launch {
//                val updatedEntry = contractTxEntity.copy(starred = !contractTxEntity.starred)
//                appDatabase.addressBook.upsert(updatedEntry)
//            }
//        }
    }
}
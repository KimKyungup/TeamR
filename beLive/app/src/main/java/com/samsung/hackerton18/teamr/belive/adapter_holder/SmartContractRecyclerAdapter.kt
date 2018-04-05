package com.samsung.hackerton18.teamr.belive.adapter_holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.samsung.hackerton18.teamr.belive.data.contractTx.ContractTxEntity
import android.view.ViewGroup
import com.samsung.hackerton18.teamr.belive.R
import com.samsung.hackerton18.teamr.belive.data.AppDatabase

class SmartContractRecyclerAdapter(val context:Context,
                                    val appDatabase: AppDatabase)
                                    :RecyclerView.Adapter<SmartContractViewHolder>() {

    var list = listOf<ContractTxEntity>()
    var displayList = listOf<ContractTxEntity>()

    override fun getItemCount() = displayList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SmartContractViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.contract_transaction_list_item, parent,false)
        return SmartContractViewHolder(view)
    }

    override fun onBindViewHolder(holder: SmartContractViewHolder, position: Int) {
        holder.bind(context, displayList[position], appDatabase)
    }

    fun updateList(){
        displayList = list
    }
}
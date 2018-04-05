package com.samsung.hackerton18.teamr.belive.adapter_holder

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.format.DateUtils
import android.text.style.BackgroundColorSpan
import android.view.View
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.data.contractTx.ContractTxEntity
import kotlinx.android.synthetic.main.contract_transaction_list_item.view.*

class SmartContractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(context: Context,
             contractTxEntity: ContractTxEntity,
             appDatabase: AppDatabase) {

        when(contractTxEntity.category){
            "tts" -> {
                val mainString = "Send a message for TTS"
                val subString = "Send"

                //Highlight
                val startIndex = mainString.indexOf(subString)
                val endIndex = startIndex + subString.length
                val spannableString = SpannableString(mainString)
                val backgroundSpan = BackgroundColorSpan(Color.parseColor("#34E2AE"))

                spannableString.setSpan(backgroundSpan, startIndex, endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                itemView.contract_content.text = spannableString
                itemView.contract_sub_content.text = "Message: \"" + contractTxEntity.note +"\""
            }

            "lotto" -> {
                itemView.contract_content.text = "Joined Lotto Event"
                itemView.contract_sub_content.text = contractTxEntity.note
            }

            else -> {
                itemView.contract_content.text = "Unknown Contract Transaction"
                itemView.contract_sub_content.text = "This contract was not categorized."
            }
        }

        itemView.contract_hash.text = contractTxEntity.hash

//        val timeString = DateUtils.getRelativeDateTimeString(context, contractTxEntity.timeStamp,
//                DateUtils.SECOND_IN_MILLIS,
//                DateUtils.WEEK_IN_MILLIS,
//                0
//        )
        val timeString = DateUtils.getRelativeTimeSpanString(
                contractTxEntity.timeStamp,
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.FORMAT_NO_NOON)

        itemView.diff_time.text = timeString
        itemView.status.text = contractTxEntity.status
        itemView.date_time.text = DateUtils.formatDateTime(
                context,
                contractTxEntity.timeStamp,
                0)


//        when {
//            keyStore.hasKeyForForAddress(contractTxEntity.address) -> R.drawable.ic_key
//            contractTxEntity.trezorDerivationPath != null -> R.drawable.trezor_icon
//            else -> R.drawable.ic_watch_only
//        }.let { itemView.key_indicator.setImageResource(it) }
//
//        if (contractTxEntity.note == null || contractTxEntity.note!!.isBlank()) {
//            itemView.address_note.visibility = View.GONE
//        } else {
//            itemView.address_note.visibility = View.VISIBLE
//            itemView.address_note.text = contractTxEntity.note
//        }
//
//        itemView.address_hash.text = contractTxEntity.address.hex
//
//        itemView.address_starred.setOnClickListener {
//            launch {
//                val updatedEntry = contractTxEntity.copy(starred = !contractTxEntity.starred)
//                appDatabase.addressBook.upsert(updatedEntry)
//            }
//        }
    }
}
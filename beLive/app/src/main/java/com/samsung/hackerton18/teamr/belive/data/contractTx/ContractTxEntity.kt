package com.samsung.hackerton18.teamr.belive.data.contractTx

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contractTx", primaryKeys = ["timeStamp", "fromAddress", "toAddress"])
data class ContractTxEntity(
        var hash: String,

        var timeStamp: Long,

        var fromAddress: String,

        var toAddress: String,

        var category: String,

        var note: String,

        @ColumnInfo(name = "pending -> ")
        var status: String,

        var jsonData: String
)
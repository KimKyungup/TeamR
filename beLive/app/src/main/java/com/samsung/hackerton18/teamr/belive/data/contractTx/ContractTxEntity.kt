package com.samsung.hackerton18.teamr.belive.data.contractTx

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contractTx")
data class ContractTxEntity(
        @PrimaryKey
        var hash: String,

        var note: String,

        var date: String,

        var category: String
)
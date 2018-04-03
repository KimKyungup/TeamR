package com.samsung.hackerton18.teamr.belive.data.account

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.math.BigInteger

@Entity(tableName = "account")
data class AccountEntity(
        @PrimaryKey
        var address: String,

        var file: String,

        var name: String,

        var note: String,

        var balance: BigInteger
)
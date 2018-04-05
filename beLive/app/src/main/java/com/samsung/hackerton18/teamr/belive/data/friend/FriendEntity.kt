package com.samsung.hackerton18.teamr.belive.data.friend

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.math.BigInteger

@Entity(tableName = "friend")
data class FriendEntity(
        @PrimaryKey
        var id:Long,

        var name:String,

        var address: String,

        var note: String
)
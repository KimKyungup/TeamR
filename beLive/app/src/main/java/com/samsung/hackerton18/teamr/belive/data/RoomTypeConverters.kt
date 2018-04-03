package com.samsung.hackerton18.teamr.belive.data

import android.arch.persistence.room.TypeConverter
import java.math.BigInteger
import java.util.*

/**
 * Created by up on 2018-04-04.
 */
class RoomTypeConverters {
    /** Date  */
    @TypeConverter
    fun fromTimestamp(value: Long?) = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time


    /** BigInteger  */
    @TypeConverter
    fun fromBigInteger(value: String?) = if (value == null) null else BigInteger(value)

    @TypeConverter
    fun bigIntegerToString(bigInteger: BigInteger?) = bigInteger?.toString()
}
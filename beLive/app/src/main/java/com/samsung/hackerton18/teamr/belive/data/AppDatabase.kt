package com.samsung.hackerton18.teamr.belive.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.samsung.hackerton18.teamr.belive.data.account.AccountDao
import com.samsung.hackerton18.teamr.belive.data.account.AccountEntity
import com.samsung.hackerton18.teamr.belive.data.contractTx.ContractTxDao
import com.samsung.hackerton18.teamr.belive.data.contractTx.ContractTxEntity
import teamr.hackerton18.samsung.fragmentex.data.transaction.TxEntity
import teamr.hackerton18.samsung.fragmentex.data.transaction.TxDao

@Database(entities = [AccountEntity::class, ContractTxEntity::class, TxEntity::class], version= 1, exportSchema=false )
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase()  {

    abstract fun txDao() : TxDao

    abstract fun accountDao() : AccountDao

    abstract fun contractTxDao() : ContractTxDao
}
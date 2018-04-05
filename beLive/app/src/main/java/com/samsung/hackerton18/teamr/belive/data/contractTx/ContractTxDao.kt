package com.samsung.hackerton18.teamr.belive.data.contractTx

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao interface ContractTxDao {

    @Query("SELECT * FROM contractTx")
    fun loadAllLiveData(): LiveData<List<ContractTxEntity>>

    @Query("SELECT * FROM contractTx")
    fun loadAll(): List<ContractTxEntity>

    @Query("SELECT * FROM contractTx WHERE \"to\" = :hash")
    fun loadByHash(hash:String): List<ContractTxEntity>

    @Query("SELECT * FROM contractTx WHERE \"timeStamp\" = :timeStamp")
    fun loadByTimeStamp(timeStamp:String): List<ContractTxEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(contractTx : ContractTxEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTx(contractTx : ContractTxEntity)

    @Delete
    fun deleteTx(contractTx: ContractTxEntity)
}
package com.samsung.hackerton18.teamr.belive.data.contractTx

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao interface ContractTxDao {

    @Query("SELECT * FROM contractTx")
    fun loadAllLiveData(): LiveData<ContractTxEntity>

    @Query("SELECT * FROM contractTx")
    fun loadAll(): List<ContractTxEntity>

    @Query("SELECT * FROM contractTx WHERE \"to\" = :hash")
    fun loadByHash(hash:String): List<ContractTxEntity>

    @Insert(onConflict = REPLACE)
    fun insertTx(contractTx : ContractTxEntity)

    @Update(onConflict = REPLACE)
    fun updateTx(contractTx : ContractTxEntity)

    @Delete
    fun deleteTx(contractTx: ContractTxEntity)
}
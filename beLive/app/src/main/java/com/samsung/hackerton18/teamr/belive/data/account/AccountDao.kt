package com.samsung.hackerton18.teamr.belive.data.account

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao interface AccountDao {
    @Query("SELECT * FROM account")
    fun loadAllLiveData(): LiveData<List<AccountEntity>>

    @Query("SELECT * FROM account")
    fun loadAll(): List<AccountEntity>

    @Query("SELECT * FROM account WHERE address = :address")
    fun loadByAddress(address:String): List<AccountEntity>

    @Query("SELECT * FROM account WHERE address = :address")
    fun loadByAddressLive(address:String): LiveData<AccountEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAccount(account : AccountEntity)

    @Delete
    fun deleteAcount(account: AccountEntity)
}
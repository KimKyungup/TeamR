package com.samsung.hackerton18.teamr.belive.data.friend

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao interface FriendDao {
    @Query("SELECT * FROM friend")
    fun loadAllLiveData(): LiveData<List<FriendEntity>>

    @Query("SELECT * FROM friend")
    fun loadAll(): List<FriendEntity>

    @Query("SELECT * FROM friend WHERE address = :address")
    fun loadByAddress(address:String): List<FriendEntity>

    @Query("SELECT * FROM friend WHERE address = :address")
    fun loadByAddressLive(address:String): LiveData<FriendEntity>

    @Query("SELECT * FROM friend WHERE name = :name")
    fun loadByName(name:String): List<FriendEntity>

    @Query("SELECT * FROM friend WHERE name = :name")
    fun loadByNameLive(name:String): LiveData<FriendEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAccount(friend : FriendEntity)

    @Delete
    fun deleteAcount(friend: FriendEntity)
}
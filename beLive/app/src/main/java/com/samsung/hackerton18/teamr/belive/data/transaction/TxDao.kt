package teamr.hackerton18.samsung.fragmentex.data.transaction

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao interface TxDao {

    @Query("SELECT * FROM tx")
    fun loadAllLiveData(): LiveData<TxEntity>

    @Query("SELECT * FROM tx")
    fun loadAll(): List<TxEntity>

    //@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    //fun loadAllByUserId(vararg userIds: Int): List<User>

    //@Query("SELECT * FROM user where name LIKE :first AND last_name LIKE :last LIMIT 1")
    //fun loadOneByNameAndLastName(first: String, last: String): User

    @Query("SELECT * FROM tx WHERE \"to\" = :toAddress OR \"from\" = :fromAddress")
    fun loadAllByFromToAddress(fromAddress:String?, toAddress:String?): List<TxEntity>

    @Query("SELECT * FROM tx WHERE \"to\" = :toAddress")
    fun loadAllByToAddress(toAddress:String): List<TxEntity>

    @Query("SELECT * FROM tx WHERE \"from\" = :fromAddress")
    fun loadAllByFromAddress(fromAddress:String): List<TxEntity>

    @Query("SELECT * FROM tx WHERE hash = :hash")
    fun loadByHash(hash:String): List<TxEntity>

    @Insert(onConflict = REPLACE)
    fun insertTx(tx : TxEntity)

    @Update(onConflict = REPLACE)
    fun updateTx(tx : TxEntity)

    @Delete
    fun deleteTx(user: TxEntity)
}
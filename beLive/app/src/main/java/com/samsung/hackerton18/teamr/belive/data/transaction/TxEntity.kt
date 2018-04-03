package teamr.hackerton18.samsung.fragmentex.data.transaction

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tx")
data class TxEntity(

        @PrimaryKey
        var hash: String,

        var from: String,

        var to: String,

        var amount: Int

        /*@Embedded
        var transaction: Transaction,

        @Embedded
        var signatureData: SignatureData?,

        @Embedded
        var transactionState: TransactionState*/
)
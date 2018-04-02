package teamr.hackerton18.samsung.fragmentex.manager

import android.content.Context

/**
 * Created by up on 2018-03-30.
 */
object MyAccountManager {
    var addressHash : String = ""
    var balance : Double = 0.0
    var lastblock = 0

    fun changeAddress(addHash : String){
        addressHash = addHash
    }
}
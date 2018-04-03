package com.samsung.hackerton18.teamr.belive.web3j

import android.content.Context

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import java.io.File

/**
 * Created by up on 2018-04-03.
 */

data class AddressItem(private val _address:String, private val _filename:String){
    var address:String = _address
    var filename:String= _filename
}

class KeyStore(val context: Context) : AnkoLogger {
    private val keyStoreFilePath = File(context.filesDir, "keystore")
    private var N_Key = 0
    private var pwd = "default" //It will be modifyied for more security.

    private var keyArray: MutableList<AddressItem> = arrayListOf<AddressItem>()
    private var hash_fileMap = HashMap<String, String>()

    public fun getKeyArray(): MutableList<AddressItem> {
        refreshKeyList()
        return keyArray
    }

    public fun init() {
        refreshKeyList()

        if (N_Key == 0) {
            newKey(pwd)
            refreshKeyList()
        }
    }

    public fun refreshKeyList() {
        val list = context.fileList()
        var cnt = 0;

        keyArray.clear()
        hash_fileMap.clear()

        for (file in list) {
            val sub = file.substring(0, 5)

            if (sub.equals("UTC--")) {
                info(file)
                cnt++

                val addressHash = getKeyAddress(file);
                var addressItem = AddressItem(addressHash, file)
                keyArray.add(addressItem)
                hash_fileMap[addressHash] = file
            }
        }
        N_Key = cnt;
    }

    public fun getKeyAddress(keyFile: String): String {
        val credentials = WalletUtils.loadCredentials(
                pwd,
                //File(keyStoreFilePath,keyFile))
                File(context.filesDir, keyFile))

        return credentials.address
    }

    public fun getCredentials(addressHash: String): Credentials {
        val file = hash_fileMap[addressHash]

        val credentials = WalletUtils.loadCredentials(
                pwd,
                //File(keyStoreFilePath,keyFile))
                File(context.filesDir, file))

        return credentials
    }

    public fun getKeyByIndex(idx: Int): String {
        val list = context.fileList()
        var cnt = 0;

        for (file in list) {

            val sub = file.substring(0, 5)

            if (sub.equals("UTC--")) {
                if (cnt == idx)
                    return file
                cnt++
            }
        }
        return ""
    }

    public fun getKeyCnt(): Int{
        return N_Key
    }

    public fun newKey(password: String): String {
        val fileName = WalletUtils.generateLightNewWalletFile(
                password,
                context.filesDir)
        info("New key file name :" + fileName)
        refreshKeyList()
        return fileName
    }

    public fun deleteKey(address: String, password: String) {

    }
}

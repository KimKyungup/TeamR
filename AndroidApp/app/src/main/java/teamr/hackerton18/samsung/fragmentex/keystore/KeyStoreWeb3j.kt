package teamr.hackerton18.samsung.fragmentex.keystore

import android.content.Context
import android.location.Address
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.web3j.crypto.WalletUtils
import teamr.hackerton18.samsung.fragmentex.data.AddressItem
import java.io.File

/**
 * Created by up on 2018-03-28.
 */
class KeyStoreWeb3j(val context: Context) : AnkoLogger {

    private val keyStoreFilePath = File(context.filesDir, "keystore")
    private var N_Key = 0
    private var pwd = "default" //It will be modifyied for more security.

    private var keyArray : MutableList<AddressItem> = arrayListOf<AddressItem>()

    public fun getKeyArray(): MutableList<AddressItem>{
        return keyArray
    }

    public fun init(){
        refreshKeyList()

        if(getKeyCnt() == 0)
        {
            newKey(pwd)
            refreshKeyList()
        }
    }
    public fun refreshKeyList(){
        val list = context.fileList()
        var cnt = 0;

        keyArray.clear()

        for(file in list){
            val sub = file.substring(0,5)

            if (sub.equals("UTC--")) {
                info(file)
                cnt++

                val addressHash = getKeyAddress(file);
                var addressItem = AddressItem(addressHash, file)
                keyArray.add(addressItem)
            }
        }
        N_Key = cnt;
    }

    public fun getKeyCnt(): Int{
        return N_Key
    }

    public fun getKeyAddress(keyFile : String):String{
        val credentials = WalletUtils.loadCredentials(
                "default",
                //File(keyStoreFilePath,keyFile))
                File(context.filesDir,keyFile))

        return credentials.getAddress()
    }

    public fun getKeyByIndex(idx: Int): String{
        val list = context.fileList()
        var cnt = 0;

        for(file in list){

            val sub = file.substring(0,5)

            if (sub.equals("UTC--")) {
                if(cnt == idx)
                    return file
                cnt++
            }
        }
        return ""
    }

    public fun newKey(password: String): String{
        val fileName = WalletUtils.generateLightNewWalletFile(
                password,
                context.filesDir)
        info("New key file name :" + fileName)

        return  fileName
    }

    public fun deleteKey(address: String, password: String){

    }
}
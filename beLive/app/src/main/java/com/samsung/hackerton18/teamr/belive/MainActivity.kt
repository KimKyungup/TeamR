package com.samsung.hackerton18.teamr.belive

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.fragment.*
import com.samsung.hackerton18.teamr.belive.web3j.KeyStore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_main.*	//custom navigation

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.samsung.hackerton18.teamr.belive.fragment.smartContract.TTS_ContractFragment
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {
    private val appDatabase: AppDatabase by LazyKodein(appKodein).instance()
    private val keyStore: KeyStore by LazyKodein(appKodein).instance()
    private val myManager: MyManager by LazyKodein(appKodein).instance()

    val manager = supportFragmentManager    // Show Fragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //test
        //supportActionBar!!.setLogo(R.drawable.logo_520x87)
        //supportActionBar!!.setDisplayUseLogoEnabled(true)


        myManager.init()  //Manager can deal with account
        myManager.balanceUpdator()

//      There is no Global FAB. In each fragment, It will be managed.
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        updateNavigationMenuUIThread()

        nav_user_address.setOnLongClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            try {
                val clip = ClipData.newPlainText("Copied Text", myManager.myAccount.address)
                clipboard.primaryClip = clip
                Snackbar.make(it, "Copied to clipboard.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }catch(e:Exception){
                e.printStackTrace()
            }
            true
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val showAccount = View.OnClickListener(){
            showAccountFragment()
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        nav_user_image.setOnClickListener(showAccount)
        nav_balance.setOnClickListener(showAccount)
        nav_email.setOnClickListener(showAccount)


	//<For Custom Navigation Menu
        menu_exit.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        nav_create_contract.setOnClickListener {
            showNewContractFragment()
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        nav_history.setOnClickListener {
            showHistoryFragment()
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        nav_friend_list.setOnClickListener {
            showFriendListFragment()
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        nav_my_account.setOnClickListener {
            showMyAccountFragment()
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        nav_setting.setOnClickListener {
            showSettingFragment()
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        nav_help.setOnClickListener {
            showHelpFragment()
            drawer_layout.closeDrawer(GravityCompat.START)
        }
//        nav_debug.setOnClickListener {
//            showSettingFragment()
//            drawer_layout.closeDrawer(GravityCompat.START)
//        }

//            Snackbar.make(it, "Camera", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show ()

	//For Custom Navigation Menu>
    }

    private fun updateNavigationMenuUIThread()
    {
        async(UI) {
            try {
                while (true) {
                    if(myManager.flagInit){
                        nav_user_address.text = myManager.myAccount.address
                        nav_balance.text = myManager.balanceString + " ETH"
                        delay(10000)
                    }
                    else
                    {
                        delay(500)
                    }
                }
            }
            catch(e:Exception){
                e.printStackTrace()
            }
        }
//                val web3 = async(CommonPool){Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))}.await()
//                val ethGetBalance = async(CommonPool){web3.ethGetBalance(myManager.myAccount.address, DefaultBlockParameterName.LATEST).sendAsync().get()}.await()
//                val ether = Convert.fromWei(ethGetBalance.balance.toString(), Convert.Unit.ETHER)
//
//                nav_user_address.text = myManager.myAccount.address
//                nav_balance.text = ether.toString() + " ETH"
//                info("Personal Information in NavigationMenu is Updated. balance:${ether.toString()}")
//                delay(10000)

                /*val result = async(CommonPool) {

                    val web3 = Web3jFactory.build(HttpService("https://rinkeby.infura.io/gGHwulfhVK8ouWn8aZMz"))
                    //val web3ClientVersion = web3.web3ClientVersion().sendAsync().get()
                    //val clientVersion = web3ClientVersion.getWeb3ClientVersion()

                    val ethGetBalance = web3.ethGetBalance(myManager.myAccount.address, DefaultBlockParameterName.LATEST)
                            .sendAsync().get()

                    val ether = Convert.fromWei(ethGetBalance.balance.toString(), Convert.Unit.ETHER)

                    val ethGetBalance2 = web3
                            .ethGetBalance("0xAf44747484436cc65327794cD1B12f085bea618a", DefaultBlockParameterName.LATEST)
                            .sendAsync().get()

                    val ether2 = Convert.fromWei(ethGetBalance2.balance.toString(), Convert.Unit.ETHER)

                    (ethGetBalance, ether)
                }.await()*/

//            }
//        }
    }
    //<Show Fragments
    private fun showAccountFragment(){
        val transaction = manager.beginTransaction()
        val fragment = AccountFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showNewContractFragment(){
        val transaction = manager.beginTransaction()
        val fragment = NewContractFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    public fun showTTS_ContractFragment(){
        val transaction = manager.beginTransaction()
        val fragment = TTS_ContractFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showHistoryFragment(){
        val transaction = manager.beginTransaction()
        val fragment = HistoryFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showFriendListFragment(){
        val transaction = manager.beginTransaction()
        val fragment = FriendListFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showMyAccountFragment(){
        val transaction = manager.beginTransaction()
        val fragment = MyAccountFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showSettingFragment(){
        val transaction = manager.beginTransaction()
        val fragment = SettingFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun showHelpFragment(){
        val transaction = manager.beginTransaction()
        val fragment = HelpFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun showDebugFragment(){
        val transaction = manager.beginTransaction()
        val fragment = DebugFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // Show Fragments>

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

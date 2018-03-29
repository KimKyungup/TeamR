package teamr.hackerton18.samsung.fragmentex

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.AnkoLogger
import teamr.hackerton18.samsung.fragmentex.keystore.KeyStoreWeb3j
import android.widget.TextView
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {
    override val loggerTag: String get() = "Main"

    val manager = supportFragmentManager
    var defaultAccountFile : String = "default"

    val keyStore by lazy {KeyStoreWeb3j(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("onCreate")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //ShowAccountFragment();

        keyStore.init()
        info("keyStore.init()")

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val hView = navigationView.getHeaderView(0)
        val navAccountAddress = hView.findViewById(R.id.accountAddress) as TextView
        navAccountAddress.setText(keyStore.getKeyByIndex(0))

        val navAccountBalance = hView.findViewById(R.id.accountBalance) as TextView
        navAccountBalance.setText("11.0 ETH")

        /*
        val navImage = hView.findViewById(R.id.accountImageView) as ImageView
        navImage.setOnClickListener(View.OnClickListener {
            ShowAccountFragment();
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        */

        val navLayout = hView.findViewById(R.id.accountLayout) as LinearLayout
        navLayout.setOnClickListener(View.OnClickListener {
            ShowAccountFragment();
            drawer_layout.closeDrawer(GravityCompat.START)
        })

        /*
        info(filesDir);
        val list = fileList()
        var flag = false;

        for(file in list){
            val sub = file.substring(0,5)
            info(file)
            info(sub)

            if (sub.equals("UTC--")) {
                info(file)
                defaultAccountFile = file
                flag = true

                break
            }
        }
        if(!flag){
            val fileName = WalletUtils.generateLightNewWalletFile(
                    "default",
                    filesDir)

            info("file name :" + fileName)
            defaultAccountFile = fileName
        }

        info(filesDir)
        info(defaultAccountFile)
        info(filesDir.toString() + "/" + defaultAccountFile)


        val fullFilePath : String = filesDir.toString() + "/" + defaultAccountFile

        val credentials = WalletUtils.loadCredentials(
                "default",
                fullFilePath)

        val address = credentials.getAddress()
        info("Address = " + address)
        */
        //ShowAccountListFragment()

/*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    fun ShowAccountFragment(){
        val transaction = manager.beginTransaction()
        val fragment = AccountFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun ShowCreateContractFragment(){
        val transaction = manager.beginTransaction()
        val fragment = NewContractFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun ShowAccountListFragment(){
        val transaction = manager.beginTransaction()
        val fragment = AccountListFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun ShowNewAccountFragment(){
        val transaction = manager.beginTransaction()
        val fragment = NewAccountFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun ShowTestFragment(){
        val transaction = manager.beginTransaction()
        val fragment = TestFragment()

        transaction.replace(R.id.fragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

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
            R.id.nav_newContract -> {
                // Handle the camera action
                ShowCreateContractFragment()
            }
            R.id.nav_history -> {
                ShowAccountFragment();
            }
            R.id.nav_friendList -> {
                ShowAccountFragment();
            }
            R.id.nav_myAccount -> {
                ShowAccountListFragment()
            }
            R.id.nav_network -> {
                ShowAccountFragment();
            }
            R.id.nav_log -> {
                ShowTestFragment()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

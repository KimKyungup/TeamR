package com.samsung.hackerton18.teamr.belive


import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Typeface
import android.icu.util.Calendar
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance

import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.web3j.KeyStore
import com.samsung.hackerton18.teamr.belive.web3j.SmartContract
import com.samsung.hackerton18.teamr.belive.web3j.Transfer
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

import java.util.ArrayList
import java.util.GregorianCalendar


class createContractActivity : AppCompatActivity() , AnkoLogger{

    private val appDatabase: AppDatabase by LazyKodein(appKodein).instance()
    private val keyStore: KeyStore by LazyKodein(appKodein).instance()

    private val smartContract: SmartContract by LazyKodein(appKodein).instance()
    private val transfer: Transfer by LazyKodein(appKodein).instance()

    internal var result_action: String? = null

    internal var result_eth: String? = null

    internal var result_date: String? = null
    internal var result_every_month: String? = null
    internal var result_specific_day: String? = null
    internal var result_time: String? = null
    internal var result_location: String? = null

    internal var result_to: String? = null


    lateinit var transferButton: Button
    lateinit var writeOnBlockButton: Button
    lateinit var playMusicButton: Button
    lateinit var turnOnBulbButton: Button

    lateinit var actionLayout: LinearLayout
    lateinit var addEthLayout: LinearLayout
    lateinit var conditionLayout: LinearLayout
    lateinit var dateLayout: LinearLayout
    lateinit var toLayout: LinearLayout
    lateinit var doneLayout: LinearLayout

    lateinit var skipButton: Button
    lateinit var dateButton: Button
    lateinit var timeButton: Button
    lateinit var locationButton: Button

    lateinit var cancelDateButton: Button
    lateinit var everyMonthButton: Button
    lateinit var specificDateButton: Button

    lateinit var toAddButton: Button
    lateinit var toMotherButton: Button
    lateinit var toYongJaeButton: Button
    lateinit var toKyungUpButton: Button
    lateinit var toKyungTaeButton: Button

    lateinit var doneButton: Button

    lateinit var resultText: TextView
    lateinit var currentStep: TextView

    lateinit var editEth: EditText

    lateinit var buttonArrayList: ArrayList<Button>
    lateinit var inpuLayoutArrayList: ArrayList<LinearLayout>

    private fun initResource() {
        transferButton = findViewById(R.id.transfer_button)
        writeOnBlockButton = findViewById(R.id.write_on_block)
        playMusicButton = findViewById(R.id.play_music_button)
        turnOnBulbButton = findViewById(R.id.turn_on_bulb_button)
        cancelDateButton = findViewById(R.id.cancel_date_button)
        everyMonthButton = findViewById(R.id.every_month_button)
        specificDateButton = findViewById(R.id.specific_date_button)

        skipButton = findViewById(R.id.skip_button)
        dateButton = findViewById(R.id.date_button)
        timeButton = findViewById(R.id.time_button)
        locationButton = findViewById(R.id.location_button)

        toAddButton = findViewById(R.id.to_add_button)
        toMotherButton = findViewById(R.id.to_mother_button)
        toYongJaeButton = findViewById(R.id.to_young_jae_button)
        toKyungUpButton = findViewById(R.id.to_kyung_up_button)
        toKyungTaeButton = findViewById(R.id.to_kyung_tae_button)

        doneButton = findViewById(R.id.done_button)

        actionLayout = findViewById(R.id.action_layout)
        addEthLayout = findViewById(R.id.type_eth_layout)
        conditionLayout = findViewById(R.id.condition_layout)
        dateLayout = findViewById(R.id.date_layout)
        toLayout = findViewById(R.id.to_layout)
        doneLayout = findViewById(R.id.done_layout)

        resultText = findViewById(R.id.result_text)
        currentStep = findViewById(R.id.current_step)

        editEth = findViewById(R.id.edit_eth)

        buttonArrayList = ArrayList()
        buttonArrayList.add(transferButton)
        buttonArrayList.add(writeOnBlockButton)
        buttonArrayList.add(playMusicButton)
        buttonArrayList.add(turnOnBulbButton)
        buttonArrayList.add(skipButton)
        buttonArrayList.add(dateButton)
        buttonArrayList.add(timeButton)
        buttonArrayList.add(locationButton)
        buttonArrayList.add(cancelDateButton)
        buttonArrayList.add(everyMonthButton)
        buttonArrayList.add(specificDateButton)
        buttonArrayList.add(toAddButton)
        buttonArrayList.add(toKyungTaeButton)
        buttonArrayList.add(toKyungUpButton)
        buttonArrayList.add(toMotherButton)
        buttonArrayList.add(toYongJaeButton)
        buttonArrayList.add(doneButton)

        inpuLayoutArrayList = ArrayList()
        inpuLayoutArrayList.add(actionLayout)
        inpuLayoutArrayList.add(addEthLayout)
        inpuLayoutArrayList.add(conditionLayout)
        inpuLayoutArrayList.add(dateLayout)
        inpuLayoutArrayList.add(toLayout)
        inpuLayoutArrayList.add(doneLayout)
    }

    enum class INPUUT_LAYOUT {
        ACTION_LAYOUT, ADD_ETH_LAYOUT, CONDITION_LAYOUT, DATE_LAYOUT, TO_LAYOUT, DONE_LAYOUT
    }

    private fun setInvisibleIputLayout() {
        val iterator = inpuLayoutArrayList.iterator()
        while (iterator.hasNext()) {
            iterator.next().visibility = View.INVISIBLE
        }
    }

    private fun changeInputLayout(inputLayout: INPUUT_LAYOUT) {
        setInvisibleIputLayout()
        when (inputLayout) {
            createContractActivity.INPUUT_LAYOUT.ACTION_LAYOUT -> {
                currentStep.text = "I Want to"
                actionLayout.visibility = View.VISIBLE
            }
            createContractActivity.INPUUT_LAYOUT.ADD_ETH_LAYOUT -> {
                currentStep.text = "ETH"
                addEthLayout.visibility = View.VISIBLE
            }
            createContractActivity.INPUUT_LAYOUT.CONDITION_LAYOUT -> {
                currentStep.text = "in this condition"
                conditionLayout.visibility = View.VISIBLE
            }
            createContractActivity.INPUUT_LAYOUT.DATE_LAYOUT -> {
                currentStep.text = "date"
                dateLayout.visibility = View.VISIBLE
            }
            createContractActivity.INPUUT_LAYOUT.TO_LAYOUT -> {
                currentStep.text = "to"
                toLayout.visibility = View.VISIBLE
            }
            createContractActivity.INPUUT_LAYOUT.DONE_LAYOUT -> {
                currentStep.text = ""
                doneLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun addResultText(addReslt: String) {
        val result = resultText.text.toString()
        resultText.text = result + "\n" + addReslt
    }

    private fun initListener() {
        initActionLayoutListener()
        initToLayoutListener()
        initDoneLayoutListener()
        initDateLayoutListener()
        initAddEthLayoutListener()
        initCondtionLayoutListener()
    }

    private fun initActionLayoutListener() {
        transferButton.setOnClickListener {
            resultText.text = "Transfer"

            result_action = "transfer"

            changeInputLayout(INPUUT_LAYOUT.ADD_ETH_LAYOUT)
        }
        writeOnBlockButton.setOnClickListener { }
        playMusicButton.setOnClickListener { }
        turnOnBulbButton.setOnClickListener { }
    }

    private fun initAddEthLayoutListener() {
        editEth.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            //Enter key Action
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                changeInputLayout(INPUUT_LAYOUT.CONDITION_LAYOUT)

                val eth = editEth.text.toString()
                addResultText("$eth ETH")

                result_eth = eth

                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                return@OnKeyListener true
            }
            false
        })
    }

    private fun initCondtionLayoutListener() {
        skipButton.setOnClickListener {
            changeInputLayout(INPUUT_LAYOUT.TO_LAYOUT) //INPUUT_LAYOUT.DONE_LAYOUT);
        }

        dateButton.setOnClickListener { changeInputLayout(INPUUT_LAYOUT.DATE_LAYOUT) }
        timeButton.setOnClickListener { }
        locationButton.setOnClickListener { }
    }

    private fun initDateLayoutListener() {
        val calendar = GregorianCalendar()
        cancelDateButton.setOnClickListener { changeInputLayout(INPUUT_LAYOUT.CONDITION_LAYOUT) }
        val everuDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            addResultText("Every month on " + dayOfMonth + "rd")

            result_every_month = "Every month on " + dayOfMonth + "rd"

            changeInputLayout(INPUUT_LAYOUT.TO_LAYOUT)
        }
        everyMonthButton.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this@createContractActivity, everuDateSetListener, year, month, day).show()
        }
        val specificDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            addResultText(year.toString() + "-" + monthOfYear + "-" + dayOfMonth)

            result_date = year.toString() + "-" + monthOfYear + "-" + dayOfMonth

            changeInputLayout(INPUUT_LAYOUT.TO_LAYOUT)
        }
        specificDateButton.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this@createContractActivity, specificDateSetListener, year, month, day).show()
        }
    }

    private fun initToLayoutListener() {
        toAddButton.setOnClickListener { }
        toMotherButton.setOnClickListener {
            changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT)
            addResultText("mother")
            result_to = "mother"
        }
        toYongJaeButton.setOnClickListener {
            changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT)
            addResultText("young jae")
            result_to = "young jae"
        }
        toKyungTaeButton.setOnClickListener {
            changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT)
            addResultText("kyung tae")
            result_to = "kyung tae"
        }
        toKyungUpButton.setOnClickListener {
            changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT)
            addResultText("kyung up")
            result_to = "kyung up"
        }
    }

    private fun initDoneLayoutListener() {
        doneButton.setOnClickListener {


            when{
                result_action == "transfer"->{

                    //result_to
                    if (result_to!!.isNotEmpty() and result_eth!!.isNotEmpty()){
                        val toAddress = "0xAf44747484436cc65327794cD1B12f085bea618a"
                        val amount = "0.01" //result_eth
                        transfer.ether(toAddress,amount!!)
                    }
                    info("Transfer start")

                }

                result_action == "tts"->{

                    smartContract.joinTTS_Contract("mac android")
                }
            }
        }
    }

    private fun initUIProperty() {
        val fontBold = "fonts/MyriadPro-Bold.otf"
        val fontRegular = "fonts/MyriadPro-Regular.otf"

        val typeFaceBold = Typeface.createFromAsset(assets, fontBold)

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        params.topMargin = 20
        params.gravity = Gravity.CENTER

        val iterator = buttonArrayList.iterator()
        while (iterator.hasNext()) {
            val button = iterator.next()
            button.typeface = typeFaceBold
            button.textSize = 29f
            button.layoutParams = params
            button.setPadding(15, 0, 15, 0)
            button.minWidth = 0
            button.minHeight = 0
            button.minimumWidth = 0
            button.minimumHeight = 0
        }

        resultText.typeface = typeFaceBold
        currentStep.typeface = typeFaceBold
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contrac)

        initResource()
        initUIProperty()

        initListener()
    }
}


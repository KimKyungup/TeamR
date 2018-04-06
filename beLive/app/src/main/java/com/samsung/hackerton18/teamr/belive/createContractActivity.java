package com.samsung.hackerton18.teamr.belive;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samsung.hackerton18.teamr.belive.data.AppDatabase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;


public class createContractActivity extends AppCompatActivity {
    public MyManager myManager = null;
    public AppDatabase appDatabase = null;

    String result_action = null;

    String result_eth = null;

    String result_date = null;
        String result_every_month = null;
        String result_specific_day = null;
    String result_time = null;
    String result_location = null;

    String result_to = null;


    Button transferButton;
    Button writeOnBlockButton;
    Button playMusicButton;
    Button turnOnBulbButton;

    LinearLayout actionLayout;
    LinearLayout addEthLayout;
    LinearLayout conditionLayout;
    LinearLayout dateLayout;
    LinearLayout toLayout;
    LinearLayout doneLayout;

    Button skipButton;
    Button dateButton;
    Button timeButton;
    Button locationButton;

    Button cancelDateButton;
    Button everyMonthButton;
    Button specificDateButton;

    Button toAddButton;
    Button toMotherButton;
    Button toYongJaeButton;
    Button toKyungUpButton;
    Button toKyungTaeButton;

    Button doneButton;

    TextView resultText;
    TextView currentStep;

    EditText editEth;

    ArrayList<Button> buttonArrayList;
    ArrayList<LinearLayout> inpuLayoutArrayList;

    private void initResource(){
        transferButton = findViewById(R.id.transfer_button);
        writeOnBlockButton = findViewById(R.id.write_on_block);
        playMusicButton = findViewById(R.id.play_music_button);
        turnOnBulbButton = findViewById(R.id.turn_on_bulb_button);
        cancelDateButton = findViewById(R.id.cancel_date_button);
        everyMonthButton = findViewById(R.id.every_month_button);
        specificDateButton = findViewById(R.id.specific_date_button);

        skipButton = findViewById(R.id.skip_button);
        dateButton = findViewById(R.id.date_button);
        timeButton = findViewById(R.id.time_button);
        locationButton = findViewById(R.id.location_button);

        toAddButton = findViewById(R.id.to_add_button);
        toMotherButton = findViewById(R.id.to_mother_button);
        toYongJaeButton = findViewById(R.id.to_young_jae_button);
        toKyungUpButton = findViewById(R.id.to_kyung_up_button);
        toKyungTaeButton = findViewById(R.id.to_kyung_tae_button);

        doneButton = findViewById(R.id.done_button);

        actionLayout = findViewById(R.id.action_layout);
        addEthLayout = findViewById(R.id.type_eth_layout);
        conditionLayout = findViewById(R.id.condition_layout);
        dateLayout = findViewById(R.id.date_layout);
        toLayout = findViewById(R.id.to_layout);
        doneLayout = findViewById(R.id.done_layout);

        resultText = findViewById(R.id.result_text);
        currentStep = findViewById(R.id.current_step);

        editEth = findViewById(R.id.edit_eth);

        buttonArrayList = new ArrayList<>();
        buttonArrayList.add(transferButton);
        buttonArrayList.add(writeOnBlockButton);
        buttonArrayList.add(playMusicButton);
        buttonArrayList.add(turnOnBulbButton);
        buttonArrayList.add(skipButton);
        buttonArrayList.add(dateButton);
        buttonArrayList.add(timeButton);
        buttonArrayList.add(locationButton);
        buttonArrayList.add(cancelDateButton);
        buttonArrayList.add(everyMonthButton);
        buttonArrayList.add(specificDateButton);
        buttonArrayList.add(toAddButton);
        buttonArrayList.add(toKyungTaeButton);
        buttonArrayList.add(toKyungUpButton);
        buttonArrayList.add(toMotherButton);
        buttonArrayList.add(toYongJaeButton);
        buttonArrayList.add(doneButton);

        inpuLayoutArrayList = new ArrayList<>();
        inpuLayoutArrayList.add(actionLayout);
        inpuLayoutArrayList.add(addEthLayout);
        inpuLayoutArrayList.add(conditionLayout);
        inpuLayoutArrayList.add(dateLayout);
        inpuLayoutArrayList.add(toLayout);
        inpuLayoutArrayList.add(doneLayout);
    }

    public enum INPUUT_LAYOUT{ACTION_LAYOUT, ADD_ETH_LAYOUT, CONDITION_LAYOUT, DATE_LAYOUT, TO_LAYOUT, DONE_LAYOUT};

    private void setInvisibleIputLayout(){
        Iterator iterator = inpuLayoutArrayList.iterator();
        while (iterator.hasNext()) {
            LinearLayout inputLayout = (LinearLayout) iterator.next();
            inputLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void changeInputLayout(INPUUT_LAYOUT inputLayout){
        setInvisibleIputLayout();
        switch(inputLayout) {
            case ACTION_LAYOUT:
                currentStep.setText("I Want to");
                actionLayout.setVisibility(View.VISIBLE);
                break;
            case ADD_ETH_LAYOUT:
                currentStep.setText("ETH");
                addEthLayout.setVisibility(View.VISIBLE);
                break;
            case CONDITION_LAYOUT:
                currentStep.setText("in this condition");
                conditionLayout.setVisibility(View.VISIBLE);
                break;
            case DATE_LAYOUT:
                currentStep.setText("date");
                dateLayout.setVisibility(View.VISIBLE);
                break;
            case TO_LAYOUT:
                currentStep.setText("to");
                toLayout.setVisibility(View.VISIBLE);
                break;
            case DONE_LAYOUT:
                currentStep.setText("");
                doneLayout.setVisibility(View.VISIBLE);
        }
    }

    private  void addResultText(String addReslt){
        String result = resultText.getText().toString();
        resultText.setText(result + "\n" + addReslt);
    }

    private void initListener(){
        initActionLayoutListener();
        initToLayoutListener();
        initDoneLayoutListener();
        initDateLayoutListener();
        initAddEthLayoutListener();
        initCondtionLayoutListener();
    }

    private void initActionLayoutListener() {
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("Transfer");

                result_action="transfer";

                changeInputLayout(INPUUT_LAYOUT.ADD_ETH_LAYOUT);
            }
        });
        writeOnBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        playMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        turnOnBulbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void initAddEthLayoutListener() {
        editEth.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    changeInputLayout(INPUUT_LAYOUT.CONDITION_LAYOUT);

                    String eth = editEth.getText().toString();
                    addResultText(eth +" ETH");

                    result_eth = eth;

                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    };
    private void initCondtionLayoutListener() {
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInputLayout(INPUUT_LAYOUT.TO_LAYOUT); //INPUUT_LAYOUT.DONE_LAYOUT);
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInputLayout(INPUUT_LAYOUT.DATE_LAYOUT);
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initDateLayoutListener() {
        final GregorianCalendar calendar = new GregorianCalendar();
        cancelDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInputLayout(INPUUT_LAYOUT.CONDITION_LAYOUT);
            }
        });
        final DatePickerDialog.OnDateSetListener everuDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                addResultText("Every month on "+ dayOfMonth+"rd");

                result_every_month = "Every month on "+ dayOfMonth+"rd";

                changeInputLayout(INPUUT_LAYOUT.TO_LAYOUT);
            }
        };
        everyMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day= calendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(createContractActivity.this, everuDateSetListener, year, month, day).show();
            }
        });
        final DatePickerDialog.OnDateSetListener specificDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                addResultText(year +"-"+ monthOfYear +"-"+ dayOfMonth);

                result_date = year +"-"+ monthOfYear +"-"+ dayOfMonth;

                changeInputLayout(INPUUT_LAYOUT.TO_LAYOUT);
            }
        };
        specificDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day= calendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(createContractActivity.this, specificDateSetListener, year, month, day).show();
            }
        });
    }

    private void initToLayoutListener(){
        toAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        toMotherButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT);
                addResultText("mother");
            }
        });
        toYongJaeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT);
                addResultText("young jae");
            }
        });
        toKyungTaeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT);
                addResultText("kyung tae");
            }
        });
        toKyungUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeInputLayout(INPUUT_LAYOUT.DONE_LAYOUT);
                addResultText("kyung up");
            }
        });
    }

    private void initDoneLayoutListener(){
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initUIProperty(){
        String fontBold = "fonts/MyriadPro-Bold.otf";
        String fontRegular = "fonts/MyriadPro-Regular.otf";

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), fontBold);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = 20;
        params.gravity = Gravity.CENTER;

        Iterator iterator = buttonArrayList.iterator();
        while (iterator.hasNext()) {
            Button button = (Button) iterator.next();
            button.setTypeface(typeFaceBold);
            button.setTextSize(29);
            button.setLayoutParams(params);
            button.setPadding(15,0,15,0);
            button.setMinWidth(0);
            button.setMinHeight(0);
            button.setMinimumWidth(0);
            button.setMinimumHeight(0);
        }

        resultText.setTypeface(typeFaceBold);
        currentStep.setTypeface(typeFaceBold);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contrac);

        initResource();
        initUIProperty();

        initListener();
    }
}


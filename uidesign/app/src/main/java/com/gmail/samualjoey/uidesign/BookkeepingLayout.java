package com.gmail.samualjoey.uidesign;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookkeepingLayout extends Activity implements View.OnClickListener{
    static int bCount = 0;
    final static  String TAG = "Bookkeeping";

    Button bt;
    EditText et;
    Spinner spinner;
    TextView theDate, theTime;
    /***************calender***************/
    SimpleDateFormat df_date = new java.text.SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());
    SimpleDateFormat df_time_am = new java.text.SimpleDateFormat(
            "hh a", Locale.US);
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bCount++;
        Log.i(TAG,"Enter onCreate(). count = " + bCount);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping);

        uiInit();
    }

    /************介面初始*****************/
    void uiInit(){
        bt = (Button) this.findViewById(R.id.sButton);
        et = (EditText) this.findViewById(R.id.editText);

        spinner = (Spinner) this.findViewById(R.id.planets_spinner);
        //create arrayadapter//

        //      ArrayList<Person> myArrayList;
        //       myArrayList = new ArrayList<Person>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        theDate = (TextView) findViewById(R.id.aDate);
        theDate.setGravity(Gravity.CENTER);
        theDate.setTextSize(20);
        theTime = (TextView) findViewById(R.id.aTime);
        theTime.setGravity(Gravity.CENTER);
        theTime.setTextSize(17);
    }
    /************變數初始***************/
    void varInit(){
        c = Calendar.getInstance();
        theDate.setText(df_date.format(c.getTime()));
        theTime.setText(df_time_am.format(c.getTime()));
    }
    /**************監聽******************/
    void setListener(){
        bt.setOnClickListener(this);
        et.setOnClickListener(this);
        theDate.setOnClickListener(this);
        theTime.setOnClickListener(this);
    }
    /*****************釋放監聽*****************/
    void releaseListener(){
        bt.setOnClickListener(null);
        et.setOnClickListener(null);
        theDate.setOnClickListener(null);
        theTime.setOnClickListener(null);
    }
    /**********app cycle************/
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause. count = " + bCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume. count = " + bCount);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart. count = " + bCount);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop. count = " + bCount);
        releaseListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy. count = " + bCount);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart. count = " + bCount);
        setListener();
        varInit();
        bCount--;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sButton:     /*********************儲存********************/
               // et.setText("1000000");
                //et.setTextColor(Color.CYAN);
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.aDate:         /*********日期****************/
                new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                theDate.setText(new StringBuilder().append(year)
                                        .append("/").append(monthOfYear + 1)
                                        .append("/").append(dayOfMonth));

                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, monthOfYear);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.aTime:               /*************時間*******************/
                new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener(){
                            @Override
                            public void  onTimeSet(TimePicker view, int hourOfDay, int minute){
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);

                                theTime.setText(df_time_am.format(c.getTime()));
                            }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),false).show();
                break;
        }
    }


}

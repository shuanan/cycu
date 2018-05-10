package com.gmail.samualjoey.uidesign;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookkeepingLayout extends AppCompatActivity implements View.OnClickListener {
    final static  String TAG = "Bookkeeping";
    Button bt;
    EditText et;
    Spinner spinner;
    TextView theDate, theTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Enter onCreate().");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping);

        bt = (Button) this.findViewById(R.id.sButton);
        et = (EditText) this.findViewById(R.id.editText);

        bt.setOnClickListener(this);
        et.setOnClickListener(this);

        spinner = (Spinner) this.findViewById(R.id.planets_spinner);
        //create arrayadapter//

        //      ArrayList<Person> myArrayList;
        //       myArrayList = new ArrayList<Person>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /***************calender***************/
    SimpleDateFormat df_date = new java.text.SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());
    SimpleDateFormat df_time_am = new java.text.SimpleDateFormat(
            "hh a", Locale.US);
    Calendar c;

    void uiInit(){
        bt = (Button) this.findViewById(R.id.sButton);
        et = (EditText) this.findViewById(R.id.editText);

        spinner = (Spinner) this.findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        theDate = (TextView) findViewById(R.id.aDate);
        theTime = (TextView) findViewById(R.id.aTime);
    }
    void varInit(){
        c = Calendar.getInstance();
        theDate.setText(df_date.format(c.getTime()));
        theTime.setText(df_time_am.format(c.getTime()));
    }
    void setListener(){
        bt.setOnClickListener(this);
        et.setOnClickListener(this);
        theDate.setOnClickListener(this);
        theTime.setOnClickListener(this);
    }
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
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        uiInit();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        releaseListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        setListener();
        varInit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sButton:
                et.setText("1000");
                et.setTextColor(Color.CYAN);
                break;
        }
    }
}

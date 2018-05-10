package com.gmail.samualjoey.uidesign;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static int mcount = 0;
    final static  String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mcount++;
        Log.i(TAG,"Enter onCreate(). count = " + mcount);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, BookkeepingLayout.class));
        //增加過廠動畫
        overridePendingTransition(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        this.finish();
    }

    /**********app cycle************/
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause. count = " + mcount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume. count = " + mcount);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart. count = " + mcount);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop. count = " + mcount);
        ((View) this.findViewById(android.R.id.content)).setOnClickListener(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy. count = " + mcount);
        mcount--;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart. count = " + mcount);
        ((View) this.findViewById(android.R.id.content)).setOnClickListener(this);
    }


}

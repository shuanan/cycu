package com.gmail.samualjoey.uidesign;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ShowResultActivity extends Activity {
    static int bCount = 0;
    final static String TAG = "ShowResultActivity";

    DB db;
    ListView lv;
    private Cursor cursor = null;
    SimpleCursorAdapter adapter;

    String[] from ={DB.KEY_MONEY, DB.KEY_CATEGORY};
    int[] to = {android.R.id.text1, android.R.id.text2};

    @Override
    protected void onCreate(Bundle savedInstanceState){

        bCount++;
        Log.i(TAG,"Enter onCreate(). count = " + bCount);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        uiInit();
        db = new DB(this);
    }

    void uiInit() {

        lv = (ListView) findViewById(R.id.listView);
//        adapter = new SimpleCursorAdapter()
    }

    void varInit(){

        db.openToRead();
        cursor = db.getAll();
        if(cursor != null && cursor.getCount() > 0){
            adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2, cursor, from, to, 0);
            lv.setAdapter(adapter);
        }
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
        if(cursor != null)
           cursor.close();

        if(db != null)
            db.close();
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
        varInit();
        bCount--;
    }
}

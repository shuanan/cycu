package com.gmail.samualjoey.uidesign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.tv.TvInputManager;
import android.os.Build;
import android.util.Log;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DB {
    private static  final String TAG = DB.class.getSimpleName();

    static final int VERSION = 1;
    public static final String DATABASE = "moneycare.db";
    public static final String TABLE = "moneycare";

    public static final String KEY_ID = "_id"; //資料流水號
    public static final String KEY_INOUT = "InOut"; //收入或支出
    public static final String KEY_CATEGORY = "category"; //資料分類
    public static final String KEY_ITEM = "item"; //項目名稱
    public static final String KEY_MONEY = "money"; //項目金額
    public static final String KEY_DATE = "myDate"; //項目日期與時間

    public static final String KEY_PAYSTYLE = "payStyle"; //付款方式
    public static final String KEY_MEMO = "memo"; //備註
    public static final String KEY_CAMERAITEM = "cameraItem"; //相片

//    public static final String IO[] = {"expense", "income", "credit",
//            "paid", "payForCredit", "IncomeForPaid"};
//    public class InOut{
//        public static final int expense = 1;
//        public static final int income = 2;
//        public static final int credit = 3;
//        public static final int paid = 4;
//        public static final int payForCredit = 5;
//        public static final int IncomeForPaid = 6;
//
//        public  int getSize() {return 6;}
//    }

    private  static Object LOCK;

    //建立資料欄位格式
    public static final String createSQL = "CREATE TABLE IF NOT EXISTS "
            + TABLE + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_DATE + "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, "
            + KEY_MONEY + "DOUBLE, " + KEY_INOUT + "INTEGER" + KEY_CATEGORY
            + "TEXT, " + KEY_ITEM + "TEXT, " + KEY_MEMO + "TEZT, "
            + KEY_CAMERAITEM + "TEZT, " + KEY_PAYSTYLE + "TEXT);";


    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE, null, VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "Creating database:" + DATABASE);
            db.execSQL(createSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE);
            this.onCreate(db);
        }
    }

    private final DbHelper dbHelper;
    private SQLiteDatabase db;

    private Context context;

    public DB(Context context){
        dbHelper = new DbHelper(context);
        this.context = context;
        LOCK = new Object();
        Log.d(TAG, "Initialized database in Constructor of DB");
    }

    /**開啟寫入模式(可寫可讀)**/
    public DB openToWrite() {
        synchronized (LOCK){
            db = dbHelper.getWritableDatabase();
            return  this;
        }
    }

    /**開啟*/
    public DB openToRead() throws SQLException {
        synchronized (LOCK){
            db = dbHelper.getReadableDatabase();
            return  this;
        }
    }

    /**關閉資料庫**/
    public  void close(){
        synchronized (LOCK){
            dbHelper.close();
        }
    }

    /**添加資料**/
    public void insert(ContentValues values) {
        synchronized (LOCK) {
            db.insertOrThrow(TABLE, null, values);
            Log.d(TAG, "insert data into db");
        }
    }

    /**根據id 更新資料**/
    public void update(int id, ContentValues values) {
        synchronized (LOCK) {
        db.update(TABLE, values, DB.KEY_ID + "=" + id, null);
        }
    }

    /**根據id 刪除資料**/
    public  void delete(int id)  {
        synchronized (LOCK) {
            db.delete(TABLE, DB.KEY_ID + "=" +id, null);
        }
    }

    /**根據日期 刪除資料**/
    public boolean delete(Timestamp from, Timestamp to) {
        synchronized (LOCK){
            String whereClause = KEY_DATE + "> = ? AND" + KEY_DATE + "< = ?";
            return db.delete(TABLE, whereClause, new String[] {
                    from.toString(), to.toString() }) != 0;
        }
    }

    /**根據id 獲得資料**/
    public Cursor getById(String id){
        synchronized (LOCK){
            String cmd = "SELECT + FROM" + TABLE + "WHERE" + KEY_ID + "="
                    +id;
            return db.rawQuery(cmd, null);
        }
    }

    /**獲得全部資料**/
    public Cursor getAll(){
        synchronized (LOCK){
            return db.rawQuery("SELECT + FROM" + TABLE, null);
        }
    }


}
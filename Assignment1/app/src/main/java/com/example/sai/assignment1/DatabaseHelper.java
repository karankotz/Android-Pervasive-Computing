package com.example.sai.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "people_table2";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "content";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE "+ TABLE_NAME);

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL2 + ", " + COL3 +" TEXT)";
        db.execSQL(createTable);
        //db.execSQL("DROP TABLE "+ TABLE_NAME);
    }

    public boolean addData(String item, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, content);

        Log.d(TAG, "addData: Adding " + item + "to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if the date us incorrectly inserted then it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    //Return all the data from the database

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}

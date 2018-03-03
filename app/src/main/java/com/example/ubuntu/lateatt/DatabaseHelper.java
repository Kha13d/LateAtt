package com.example.ubuntu.lateatt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ubuntu on 01/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBname = "Attendants.db";
    public static final String TableName = "Delay";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Day";
    public static final String COL_3 = "Date";
    public static final String COL_4 = "Time";
    public static final String COL_5 = "Delay";

    public DatabaseHelper(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Day TEXT, Date TEXT, Time TEXT, Delay TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table IF Exists " + TableName);
    }

    public boolean insertData(String day, String date, String time, String delay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,day);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,time);
        contentValues.put(COL_5,delay);
        long result = db.insert(TableName,null,contentValues);
        if (result == -1 ) return false; else return true;
    }

    public Cursor getTable(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TableName,null);
        return result;
    }

    public int deleteDelay(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + TableName + "WHERE "+ COL_1 + " = '" + ID +"'");
        return db.delete(TableName,"ID = ?", new String[] {ID});
    }
}



package com.example.hossein.wallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBase extends SQLiteOpenHelper {

    private static final String TAG = "jalil";
    private static final String DATABASE_NAME = "wallet.db";
    private static final String TABLE_NAME = "moneyManagement";
    private static final String NAME_COL = "Name";
    private static final String SCORE_COL = "Score";

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_EXPENSES_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + NAME_COL + " TEXT, " + SCORE_COL + " INTEGER" + ")";
        database.execSQL(CREATE_EXPENSES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        if (newVersion < oldVersion) {

            String drop = "DROP IF TABLE EXISTS ";
            database.execSQL(drop + TABLE_NAME);
            onCreate(database);

        }


    }

    public boolean addData(String name, Integer score) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL, name);
        contentValues.put(SCORE_COL, score);

        long result = database.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getData() {

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME, null);
        return result;

    }

    public void clearTable() {

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME);

    }

}

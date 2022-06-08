package com.example.tripplannerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME="TripPlannerApp.db";

    //USER TABLE
    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID = "ID";
    public static final String USER_NAME = "USER_NAME";
    //public static final String USER_AGE = "USER_AGE";
    public static final String USER_PASS = "USER_PASS";
    //public static final String USER_PHONE = "USER_PHONE";
    //public static final String USER_GENDER = "USER_GENDER";
    public static final String USER_EMAIL = "USER_EMAIL";
    //public static final String USER_ADDRESS = "USER_ADDRESS";
    //public static final String USER_BIRTH = "USER_BIRTH";

    public DataBaseHelper(Context context) {
        super(context, "TripPlannerApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table login(email TEXT primary key, password TEXT, username TEXT)");
        //String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT, " + USER_AGE + " INT, " + USER_PASS + " TEXT, " + USER_PHONE + " TEXT, " + USER_GENDER + " TEXT, " + USER_EMAIL + " TEXT, " + USER_ADDRESS + " TEXT, " + USER_BIRTH + " DATE)";
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT, " + USER_PASS + " TEXT,  " + USER_EMAIL + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(TripPlannerAppModel tripPlannerAppModel){
        SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, tripPlannerAppModel.getUser_name());
        cv.put(USER_EMAIL, tripPlannerAppModel.getUser_email());
        cv.put(USER_PASS, tripPlannerAppModel.getUser_pass());

        long insert = sqLiteOpenHelper.insert(USER_TABLE,null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }

    }
}

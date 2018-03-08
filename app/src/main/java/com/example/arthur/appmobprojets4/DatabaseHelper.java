package com.example.arthur.appmobprojets4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arthur on 08/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bddAppMob.db";
    public static final String TABLE_NAME = "places_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "address";
    public static final String COL_3 = "latitude";
    public static final String COL_4 = "longitude";
    public static final String COL_5 = "radius";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT,latitude REAL,longitude REAL,radius INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

package com.example.pokehelper.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    private static DB dbSingleton;
    private static int DATABASE_VERSION  = 1;

    public static SQLiteDatabase getDB(@Nullable Context context){
        if (dbSingleton==null){
            dbSingleton = new DB(context);
        }
        return dbSingleton.getWritableDatabase();
    }

    private DB(@Nullable Context context) {
        super(context, DB_Keys.DB_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "CREATE TABLE " +
                DB_Keys.TABLE_POKEDEX + " (" +
                DB_Keys.COL_POKEDEX + " INTEGER, " +
                DB_Keys.COL_NAME + " TEXT, " +
                DB_Keys.COL_TYPES + " TEXT, " +
                DB_Keys.COL_IMG + " BLOB, " +
                DB_Keys.COL_FAVORITO + " INTEGER)";
        sqLiteDatabase.execSQL(sqlCreate);

        sqlCreate = "CREATE TABLE " +
                DB_Keys.TABLE_TYPES + " (" +
                DB_Keys.COL_NAME + " TEXT, " +
                DB_Keys.COL_IMG + " BLOB)";
        sqLiteDatabase.execSQL(sqlCreate);

        sqlCreate = "CREATE TABLE " +
                DB_Keys.TABLE_STATS + " (" +
                DB_Keys.COL_POKEDEX + " INTEGER, " +
                DB_Keys.COL_PS + " INTEGER, " +
                DB_Keys.COL_ATQ + " INTEGER, " +
                DB_Keys.COL_DEF + " INTEGER, " +
                DB_Keys.COL_SP_ATQ + " INTEGER, " +
                DB_Keys.COL_SP_DEF + " INTEGER, " +
                DB_Keys.COL_VEL + " INTEGER, " +
                DB_Keys.COL_TOTAL + " INTEGER)";
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_Keys.TABLE_POKEDEX);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_Keys.TABLE_TYPES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_Keys.TABLE_STATS);
        onCreate(sqLiteDatabase);
    }
}

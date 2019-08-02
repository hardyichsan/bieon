package com.todev.bieon.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class SQLiteDataGaram extends SQLiteOpenHelper {

    //InnerClass, untuk mengatur artibut seperti Nama Tabel, nama-nama kolom dan Query
    public abstract class kolom implements BaseColumns {
        //Menentukan Nama Table dan Kolom
        public static final String NamaTabel = "Garam";
        public static final String NoSeri = "NoSeri";
        public static final String Nacl = "Nacl";
        public static final String Whiteness = "Whiteness";
        public static final String Watercontent= "Watercontent";
    }

    private static final String NamaDatabase = "DataGaram.db";
    private static final int VersiDatabase = 1;

    //Query yang digunakan untuk membuat Tabel
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+kolom.NamaTabel+
            "("+kolom.NoSeri+" TEXT PRIMARY KEY, "
            +kolom.Nacl+" TEXT NOT NULL, "
            +kolom.Whiteness+ " TEXT NOT NULL, "
            +kolom.Watercontent+" TEXT NOT NULL)";

    //Query yang digunakan untuk mengupgrade Tabel
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+kolom.NamaTabel;

    public SQLiteDataGaram(Context context) {
        super(context, NamaDatabase, null, VersiDatabase);
        Log.d("data",NamaDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}

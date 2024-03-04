package com.example.login.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db_user extends SQLiteOpenHelper {
    public db_user(Context Context) {
        super(Context, "UserAccount", null, 1);
    }

    public static  String TB_TAIKHOAN =  "taikhoan";

    public static  String TB_TAIKHOAN_TENDANGNHAP ="tendangnhap";
    public static  String TB_TAIKHOAN_MATKHAU ="matkhau";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbtaikhoan = "CREATE TABLE "+ TB_TAIKHOAN + "("+ TB_TAIKHOAN_TENDANGNHAP + " TEXT PRIMARY KEY, "
                + TB_TAIKHOAN_MATKHAU + "CHAR )";
        db.execSQL(tbtaikhoan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase conn(){
        return this.getWritableDatabase();
    }


}

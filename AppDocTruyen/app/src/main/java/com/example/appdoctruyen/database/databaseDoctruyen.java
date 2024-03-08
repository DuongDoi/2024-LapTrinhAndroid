package com.example.appdoctruyen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseDoctruyen  extends SQLiteOpenHelper {
    //Tên database
    private final static String DATABASE_NAME = "AppDoctruyen";

    //Version
    private final static int VERSION = 1;

    //Biến bảng truyện
    private final static String TB_Truyen = "truyen";
    private final static String matruyen = "matruyen";
    private final static String tentruyen = "tentruyen";
    private final static String motatruyen = "motatruyen";
    private final static String avatar_url = "avatar";

    private final static String sochuong = "sochuong";


    //Biến bảng chương chi tiết
    private final static String TB_Chitietchuong = "chitietchuong";
    private final static String machuong = "machuong";
    private final static String tenchuong = "tenchuong";
    private final static String noidungchuong = "noidungchuong";

    //Biến bảng tài khoản
    private final static String TB_Taikhoan = "taikhoan";
    private final static String tendangnhap = "tendangnhap";
    private final static String matkhau = "matkhau";

    //Biến bảng bình luận
    private final static String TB_Binhluan = "binhluan";
    private final static String mabinhluan = "mabinhluan";
    private final static String noidung = "noidung";

    //Biến bảng lịch sử đọc
    private final static String TB_Lichsudoc = "lichsudoc";
    private final static String malichsudoc = "malichsudoc";
    private final static String chuongdangdoc = "chuongdangdoc";

    private Context context;

    //Tạo bảng truyện
    private final static String createTB_Truyen = "CREATE TABLE "+TB_Truyen
            +"("+matruyen+" varchar(10) primary key, "
            +tentruyen+"TEXT, "
            +motatruyen+"TEXT, "+
            avatar_url+"TEXT, "+sochuong+"integer)";

    //Tạo bảng chi tiết chương
    private final static String createTB_Chitietchuong = "create table "+TB_Chitietchuong
            +"("+machuong+" integert primary key  autoincrement,"
            +tenchuong+" Text,"
            +noidungchuong+" Text,"
            +matruyen+" varchar(10), foreign key ("+matruyen+") references " +TB_Truyen+"("+matruyen+"))";

    //Tạo bảng tài khoản
    private final static String createTB_Taikhoan = "create table "+TB_Taikhoan
            +"("+tendangnhap+" varchar(30) primary key,"
            +matkhau+" varchar(30))";

    //Tạo bảng bình luận
    private final static String createTB_Binhluan = "create table "+TB_Binhluan
            +"("+mabinhluan+" integer primary key  autoincrement,"
            +noidung+" Text,"
            +noidungchuong+" Text,"
            +matruyen+" varchar(10), "
            +tendangnhap+" varchar(30), " +
            "foreign key ("+matruyen+") references " +TB_Truyen+"("+matruyen+"),"+
            "foreign key ("+tendangnhap+") references " +TB_Taikhoan+"("+tendangnhap+"))";

    //Tạo bảng lịch sử đọc
    private final static String createTB_Lichsudoc = "create table "+TB_Lichsudoc
            +"("+malichsudoc+" integer primary key  autoincrement, "
            +chuongdangdoc+" integer,"
            +matruyen+" varchar(10), "
            +tendangnhap+" varchar(30)," +
            " foreign key ("+tendangnhap+") references " +TB_Taikhoan+"("+tendangnhap+")," +
            " foreign key ("+matruyen+") references " +TB_Truyen+"("+matruyen+"))";

    

    public databaseDoctruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTB_Truyen);
        db.execSQL(createTB_Taikhoan);
        db.execSQL(createTB_Chitietchuong);
        db.execSQL(createTB_Binhluan);
        db.execSQL(createTB_Lichsudoc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

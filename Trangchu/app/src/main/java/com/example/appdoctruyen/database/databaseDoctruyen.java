package com.example.appdoctruyen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseDoctruyen  extends SQLiteOpenHelper {
    //Tên database
    private static String DATABASE_NAME = "Doctruyen";

    //Version
    private static int VERSION = 1;

    //Biến bảng truyện
    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    private Context context;

    //Tạo bảng truyện
    private String SQLQuery1 = "CREATE TABLE "+TABLE_TRUYEN+"("+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+"TEXT UNIQUE, "
            +NOI_DUNG+"TEXT, "+
            IMAGE+"TEXT)";

    //Insert truyện
    private String SQLQuery2 = "INSERT INTO truyen VALUES (null, 'Tinh tế nam thần là ba ta', 'Chương 1:"
            +"\n"+
            "Diêu Tư cảm thấy gần đây mình có cảm giác không đúng, cô luôn là người ngủ đến mức sét đánh cũng không nhúc nhích, vậy mà mấy ngày nay lại không ngủ được. Kỳ lạ là tinh thần lại tốt cực kỳ."
            +"\n"+
            "Diêu Tư cảm thấy gần đây mình có cảm giác không đúng, cô luôn là người ngủ đến mức sét đánh cũng không nhúc nhích, vậy mà mấy ngày nay lại không ngủ được. Kỳ lạ là tinh thần lại tốt cực kỳ.','https://img.wattpad.com/cover/167547650-416-k53203.jpg', 1)";
    private String SQLQuery3 = "INSERT INTO truyen VALUES (null, 'Phu thê nhà nghèo', 'Chương 1: Xuyên qua"
            +"\n"+
            "Diêu Tư cảm thấy gần đây mình có cảm giác không đúng, cô luôn là người ngủ đến mức sét đánh cũng không nhúc nhích, vậy mà mấy ngày nay lại không ngủ được. Kỳ lạ là tinh thần lại tốt cực kỳ."
            +"\n"+
            "Diêu Tư cảm thấy gần đây mình có cảm giác không đúng, cô luôn là người ngủ đến mức sét đánh cũng không nhúc nhích, vậy mà mấy ngày nay lại không ngủ được. Kỳ lạ là tinh thần lại tốt cực kỳ.','https://img.dtruyen.com/public/images/large/phuthenhangheoT2M2PHCDyr.jpg', 1)";

    public databaseDoctruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

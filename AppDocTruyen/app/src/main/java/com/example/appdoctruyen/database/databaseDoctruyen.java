package com.example.appdoctruyen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

public class databaseDoctruyen extends SQLiteOpenHelper {
    //CÁC BẢNG
    public static String TB_TAIKHOAN = "TAIKHOAN";
    public static String TB_TRUYEN = "TRUYEN";
    public static String TB_CHITIETCHUONG = "CHITIETCHUONG";
    public static String TB_BINHLUAN = "BINHLUAN";
    public static String TB_LICHSUDOC= "LICHSUDOC";
    //TÀI KHOẢN
    public static String TB_TAIKHOAN_TENDANGNHAP = "TENDANGNHAP";
    public static String TB_TAIKHOAN_MATKHAU = "MATKHAU";
    //TRUYỆN
    public static String TB_TRUYEN_MATRUYEN = "MATRUYEN";
    public static String TB_TRUYEN_TENTRUYEN = "TENTRUYEN";
    public static String TB_TRUYEN_MOTATRUYEN = "MOTATRUYEN";
    public static String TB_TRUYEN_AVATAR_URL = "AVATAR";
    public static String TB_TRUYEN_SOCHUONG = "SOCHUONG";
    //CHƯƠNG TRUYỆN
    public static String TB_CHITIETCHUONG_MACHUONG = "MACHUONG";
    public static String TB_CHITIETCHUONG_TENCHUONG = "TENCHUONG";
    public static String TB_CHITIETCHUONG_NOIDUNGCHUONG = "NOIDUNGCHUONG";
    //BÌNH LUẬN
    public static String TB_BINHLUAN_MABINHLUAN = "MABINHLUAN";
    public static String TB_BINHLUAN_NOIDUNGBINHLUAN = "NOIDUNGBINHLUAN";
    //LỊCH SỬ ĐỌC
    public static String TB_LICHSUDOC_MALICHSUDOC = "MALICHSUDOC";


    public databaseDoctruyen(Context context) {
        super(context,"AppDocTruyen",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbTAIKHOAN = "CREATE TABLE " + TB_TAIKHOAN
                + " ( " + TB_TAIKHOAN_TENDANGNHAP + " TEXT PRIMARY KEY, "
                + TB_TAIKHOAN_MATKHAU + " TEXT );";
        String tbTRUYEN = "CREATE TABLE " + TB_TRUYEN
                + " ( " + TB_TRUYEN_MATRUYEN + " TEXT PRIMARY KEY, "
                + TB_TRUYEN_TENTRUYEN + " TEXT, "
                + TB_TRUYEN_MOTATRUYEN + " TEXT, "
                + TB_TRUYEN_AVATAR_URL + " TEXT, "
                + TB_TRUYEN_SOCHUONG + " TEXT ); ";
        String tbCHITIETCHUONG = "CREATE TABLE "+ TB_CHITIETCHUONG
                + " ( " + TB_CHITIETCHUONG_MACHUONG + " TEXT PRIMARY KEY, "
                + TB_CHITIETCHUONG_TENCHUONG + " TEXT, "
                + TB_CHITIETCHUONG_NOIDUNGCHUONG + " TEXT, "
                + TB_TRUYEN_MATRUYEN + " TEXT , "
                + "FOREIGN KEY ( " + TB_TRUYEN_MATRUYEN +" ) REFERENCES " + TB_TRUYEN + " ( " + TB_TRUYEN_MATRUYEN + " )); ";
        String tbBINHLUAN = "CREATE TABLE " + TB_BINHLUAN
                + " ( " + TB_BINHLUAN_MABINHLUAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BINHLUAN_NOIDUNGBINHLUAN + " TEXT, "
                + TB_TRUYEN_MATRUYEN + " TEXT, "
                + TB_TAIKHOAN_TENDANGNHAP + " TEXT, "
                + " FOREIGN KEY ( " + TB_TRUYEN_MATRUYEN + " ) REFERENCES " + TB_TRUYEN + " ( " + TB_TRUYEN_MATRUYEN + " ), "
                + " FOREIGN KEY ( " + TB_TAIKHOAN_TENDANGNHAP + " ) REFERENCES " + TB_TAIKHOAN + " ( " + TB_TAIKHOAN_TENDANGNHAP + " ));";
        String tbLICHSUDOC = "CREATE TABLE " + TB_LICHSUDOC
                + " ( " + TB_CHITIETCHUONG_MACHUONG + " TEXT, "
                + TB_TRUYEN_MATRUYEN + " TEXT, "
                + TB_TAIKHOAN_TENDANGNHAP + " TEXT, "
                + "PRIMARY KEY ( " + TB_TRUYEN_MATRUYEN + " , " + TB_TAIKHOAN_TENDANGNHAP + " ) ,"
                + " FOREIGN KEY ( " + TB_TRUYEN_MATRUYEN + " ) REFERENCES " + TB_TRUYEN + " ( " + TB_TRUYEN_MATRUYEN + " ), "
                + " FOREIGN KEY ( " + TB_TAIKHOAN_TENDANGNHAP + " ) REFERENCES " + TB_TAIKHOAN + " ( " + TB_TAIKHOAN_TENDANGNHAP + " ));";

        String isTAIKHOAN = "INSERT INTO " + TB_TAIKHOAN + " ( " + TB_TAIKHOAN_TENDANGNHAP +" , " + TB_TAIKHOAN_MATKHAU + ") VALUES " +
                "    ('admin', 'admin')," +
                "    ('duongdoi', 'duongdoi')," +
                "    ('ngocdiu', 'ngocdiu')," +
                "    ('quynhanh', 'quynhanh')," +
                "    ('lanhuong', 'lanhuong')," +
                "    ('a', 'a')";
        String isTRUYEN = "INSERT INTO TRUYEN ( MATRUYEN , TENTRUYEN , MOTATRUYEN , AVATAR , SOCHUONG) \n" +
                "VALUES ('001', 'Tinh tế nam thần là ba ta', 'Diêu Tư là trạch nữ không cha không mẹ, cuộc sống luôn bình bình thường thường, không tai nạn không khó khăn, cô cảm thấy cả đời cứ như vậy sống yên tới già. Sau đó… cô chết!\n" +
                "Biến thành quỷ hút máu, còn là gà bệnh đời thứ năm, không hề có khả năng chiến đấu. Diêu Tư cảm thấy, cho dù nội chiến của Huyết tộc đánh lớn cỡ nào, trừ khi có kỳ tích, nếu không ngọn lửa đó chẳng bao giờ lan tới chỗ cô.\n" +
                "Vì thế, kỳ tích xảy ra… \n" +
                "Ngủ một giấc, vượt qua thời gian của cả hành tinh, Huyết tộc toàn vũ trụ đều xếp thành hai hàng gọi cô một tiếng lão tổ tông.\n" +
                "Hát khúc ca nông nô đổi đời, xem ai còn dám nói cô là gà bệnh!\n" +
                "Đến đây, đánh nhau nha, đánh không thắng ta gọi ngươi là ba.\n" +
                "Vì thế… cô có thêm một ba ba.'," +
                " 'https://static.8cache.com/cover/o/eJzLyTDW1zWMtzD2MdJ1MjSO1A8rLE1Oig_2MA301HeEAi8jE_2kiqJAR--UjICccv1yI0NT3QxjIyNdz2QTIwCCqxLp/tinh-te-nam-than-la-ba-ta.jpg'," +
                " '70 chương'),\n" +
                "       ('002', 'Cô vợ ngọt ngào có chút bất lương'," +
                " 'Khẩu vị của người này rốt cuộc ra sao a! Cái này cũng bỏ được vào miệng à?\n" +
                "Sau khi cô tỉnh dậy, nhìn vào trong gương thấy chính mình đầu xăm mặt giống như quỷ, cảm giác chỉ nhìn thêm một giây cũng hỏng đôi mắt.\n" +
                "Trước khi trọng sinh, Cố Việt Trạch chính là người mà cô dùng cả tấm lòng để yêu nhưng sau đó cũng là người mà cô hận thấu xương.\n" +
                "Đời trước cô chính là kiểu phụ nữ não tàn nên mới không muốn lây một ông xã tuyệt sắc, lại bị đôi tiện nam nữ hãm hại, bị người bạn thân nhất tẩy não, kết cục cuối cùng chính là không còn người nào muốn ở gần cô.\n" +
                "Đời này mặc cho các ngươi trâu bò rắn rết trăm phương nghìn kế, muốn cô ly dị, nhường đi ngôi vị phu nhân. Ngượng ngùng quá ~~, chỉ số thông minh của bản tiểu thư đã lên dây rồi nhé!'," +
                " 'https://static.8cache.com/cover/o/eJzLyTDW1zXKKnJ09440NzRw1Q8zCc_3LDTyDo_w1HeEgsCCdP2MND9Hl2wTP3_fYoOy3EDDSq-gomKf5GRns6KCIsPcxHRnD1_9ciNDU90MYyMjAHumGmo=/co-vo-ngot-ngao-co-chut-bat-luong-vo-moi-bat-luong-co-chut-ngot.jpg'," +
                " '15 chương'),\n" +
                "       ('003', 'Đạo tình'" +
                ", 'Đây là tác phẩm hắc bang, bối cảnh hoành tráng, tình tiết đẫm máu u ám. Nếu bạn muốn xem câu truyện tình lãng mạn thì đừng nhảy vào hố này. \\\"Đạo tình\\\" là một thế giới đen tối, trong đó nam chính tuyệt đối không phải là người tốt.\n" +
                "- Nàng là một trong những tên trộm kỳ tài nhất thế kỷ hai mốt. Nàng là nữ hoàng đua xe trong bóng đêm. Nàng là một người phụ nữ giống như cơn gió, đi đến bất định.\n" +
                "- Hắn là ông trùm đứng nhất nhì trong giới hắc đạo. Hắn là kẻ máu lạnh, tàn nhẫn, vô tình. Không ngờ trên đời này lại có người dám động đến tài sản của hắn. Nếu người đó chán sống, hắn sẽ kết thúc mạng sống thay.\n" +
                "- Là tình, là yêu, là đau, hay là hận?... tất cả đều không rõ ràng.'," +
                " 'https://static.8cache.com/cover/o/eJzLyTDR1w3NNCp29dKNcit01A9zSktPTHMyLij21HeEguyQQP1wv0q_0CJDo9IIE_1yI0NT3QxjIyMAUDASSQ==/dao-tinh.jpg'," +
                " '20 chương');";

        String insert_chuongtruyen1 = "INSERT INTO CHITIETCHUONG ( MACHUONG , TENCHUONG, NOIDUNGCHUONG , MATRUYEN ) VALUES \n" +
                " ( 'chuong1truyen1' , 'Chương 1: Em gái, em đã chết!' , 'Diêu Tư cảm thấy mình gần đây có chút gì đó không đúng, cô luôn là người ngủ đến mức sét đánh cũng không nhúc nhích, vậy mà mấy ngày nay lại không ngủ được. Kỳ lạ là tinh thần lại tốt cực kỳ' , '001' ) , " +
                " ( 'chuong2truyen1' , 'Chương 2: Cô muốn chết ở đâu?' , 'Mặt trời lặn về tây, sắc trời dần tối.' , '001' ) , " +
                " ( 'chuong3truyen1' , 'Chương 3: Flag tiến lên' , '“Em không thấy thông báo?” Lý Chính so với cô càng kinh ngạc.' , '001' ); ";
        String insert_chuongtruyen2 = "INSERT INTO CHITIETCHUONG ( MACHUONG , TENCHUONG, NOIDUNGCHUONG , MATRUYEN ) VALUES \n" +
                " ( 'chuong1truyen2' , 'Chương 1: Còn muốn trốn sao?' , 'Diệp Oản Oản mở mắt. Đối mặt là đôi môi khiến cho cô sợ hãi đến ngay cả linh hồn đều run rẩy con ngươi.' , '002' ) , " +
                " ( 'chuong2truyen2' , 'Chương 2: Nuốt được đến miệng' , 'Tư Dạ Hàn quyền thế ngút trời, bóp chết cô còn dễ hơn so với bóp chết một con kiến, thứ mà anh ta đã muốn muốn, tuyệt đối không có gì là không thể chiếm được.' , '002' ) , " +
                " ( 'chuong3truyen2' , 'Chương 3: Vị hôn phu trước' , 'Ba ngày này, Diệp Oản Oản phần lớn thời gian đều là ngủ và sửa sang lại trí nhớ của kiếp trước.' , '002' ); ";
        String insert_chuongtruyen3 = "INSERT INTO CHITIETCHUONG ( MACHUONG , TENCHUONG, NOIDUNGCHUONG , MATRUYEN ) VALUES \n" +
                " ( 'chuong1truyen3' , 'Chương 1: Ăn trộm' , 'Một bàn tay ngọc ngà thò vào bên trong nhấc miếng ngọc đặt trên quầy triển lãm, tiếng nói khe khẽ cất lên' , '003' ) , " +
                " ( 'chuong2truyen3' , 'Chương 2: Lần đầu gặp gỡ' , 'Trong một ngôi biệt thự gần bờ biển ở San Francisco, một cô gái có vẻ bề ngoài dịu dàng đứng ở cửa nhà bếp, nói với cô gái đang ngồi xem tivi vừa ăn hoa quả' , '003' ) , " +
                " ( 'chuong3truyen3' , 'Chương 3: Đua xe' , 'Ly Tâm giảm tốc độ, đi từ từ để thưởng thức trò vui. Quả nhiên nghe cô nhắc nhở, có kẻ thông minh quay về lấy xe. Ly Tâm lắc đầu đầy thất vọng. Xem ra trò vui sắp kết thúc rồi, cô quay đầu lại chuẩn bị tăng tốc độ.' , '003' ); ";
        String insert_binhluan = "INSERT INTO " + TB_BINHLUAN + " ( " + TB_BINHLUAN_NOIDUNGBINHLUAN + " , " + TB_TRUYEN_MATRUYEN + " , " + TB_TAIKHOAN_TENDANGNHAP + " ) VALUES\n" +
                "('Hay quá', '001', 'admin')," +
                "('Truyện này cày cả ngày không chán :)', '001', 'admin')," +
                "('Đang đọc dở thì hết :(', '001', 'admin')," +
                "('Bao giờ thì ra chương mới thế ad', '001', 'duongdoi')," +
                "('Bình luận cho vui thôi được không :)', '001', 'ngocdiu'),"+
                "('Không ổn rồi ::::', '001', 'duongdoi')," +
                "('Truyện này không hay nhưng vẫn đọc :)', '001', 'ngocdiu')," +
                "('Truyện này cày cả ngày không chán :)', '002', 'admin')," +
                "('Đang đọc dở thì hết :(', '002', 'admin')," +
                "('Bao giờ thì ra chương mới thế ad', '002', 'duongdoi')," +
                "('Bình luận cho vui thôi được không :)', '002', 'ngocdiu'),"+
                "('Không ổn rồi ::::', '002', 'duongdoi')," +
                "('Truyện này không hay nhưng vẫn đọc :)', '002', 'ngocdiu')," +
                "('Truyện này cày cả ngày không chán :)', '003', 'admin')," +
                "('Đang đọc dở thì hết :(', '003', 'admin')," +
                "('Bao giờ thì ra chương mới thế ad', '003', 'duongdoi')," +
                "('Bình luận cho vui thôi được không :)', '003', 'ngocdiu'),"+
                "('Không ổn rồi ::::', '003', 'duongdoi')," +
                "('Truyện này không hay nhưng vẫn đọc :)', '003', 'ngocdiu')" ;
        String insert_lichsudoc = "INSERT INTO LICHSUDOC (MATRUYEN , TENDANGNHAP ) VALUES \n" +
                "('001', 'admin'),\n" +
                "('002', 'admin'),\n" +
                "('002', 'ngocdiu'),\n" +
                "('001', 'duongdoi')";
        try {
            db.execSQL(tbTAIKHOAN);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Bảng tài khoản đã tồn tại");
        }
        try {
            db.execSQL(tbTRUYEN);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Bảng truyện đã tồn tại");
        }
        try {
            db.execSQL(tbCHITIETCHUONG);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Bảng chi tiết chương đã tồn tại");
        }
        try {
            db.execSQL(tbBINHLUAN);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Bảng bình luận đã tồn tại");
        }
        try {
            db.execSQL(tbLICHSUDOC);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Bảng lịch sử đọc đã tồn tại");
        }
        try {
            db.execSQL(isTAIKHOAN);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Người dùng mặc định đã được nhập");
        }
        try {
            db.execSQL(isTRUYEN);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Truyện mặc định đã được nhập");
        }
        try {
            db.execSQL(insert_chuongtruyen1);
            db.execSQL(insert_chuongtruyen2);
            db.execSQL(insert_chuongtruyen3);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Chương truyện mặc định đã được nhập");
        }
        try {
            db.execSQL(insert_binhluan);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Bình luận mặc định đã được nhập");
        }
        try {
            db.execSQL(insert_lichsudoc);
        }
        catch (Exception e)
        {
            Log.e("Thông báo","Lịch sử đọc mặc định đã được nhập");
        }


    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_TAIKHOAN);
        db.execSQL("DROP TABLE IF EXISTS " + TB_TRUYEN);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CHITIETCHUONG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_LICHSUDOC);
        db.execSQL("DROP TABLE IF EXISTS " + TB_BINHLUAN);
        onCreate(db);
    }
//Thêm mới tài khoản
    public void themTaikhoan(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_TAIKHOAN_TENDANGNHAP,username);
        values.put(TB_TAIKHOAN_MATKHAU,password);
        db.insert(TB_TAIKHOAN,null,values);
        db.close();
    }


    //Check tài khoản đã tồn tại chưa
    public boolean checkTendangnhap(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String querry = " SELECT * FROM " + TB_TAIKHOAN + " WHERE " + TB_TAIKHOAN_TENDANGNHAP + " = '" + username + "';";
        Cursor cursor = db.rawQuery(querry,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    //Check tài khoản xem có đăng nhp được không
    public boolean checkDangnhap(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TB_TAIKHOAN + " WHERE " + TB_TAIKHOAN_TENDANGNHAP + " =? " + " AND " + TB_TAIKHOAN_MATKHAU + " =? ", new String[]{username,password});
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    //Thêm comment mới của truyện có mã truyện = "_matruyen",với nội dung = "_noidung", do người dùng với tên đăng nhập = "_tendangnhap" bình luận
    public boolean themBinhluan(String _noidung ,String _matruyen ,String _tendangnhap){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TB_BINHLUAN_NOIDUNGBINHLUAN,_noidung );
            values.put(TB_TRUYEN_MATRUYEN,_matruyen );
            values.put(TB_TAIKHOAN_TENDANGNHAP,_tendangnhap);
            db.insert(TB_BINHLUAN,null,values);
            db.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //Lấy tất cả bình luận của truyện có mã truyện = "matruyen"
    public ArrayList<String> layBinhluan(String maTruyen){
        ArrayList<String> mylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "MATRUYEN = ?";
        String[] selectionArgs = { maTruyen };
        Cursor c = db.query(TB_BINHLUAN, null, selection, selectionArgs, null, null, null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            data =  c.getString(3) + ": \n\t- " + c.getString(1) ;
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        db.close();
        return  mylist;
    }
//Danh sách chương truyện***********************************************************************************************************************
    //Lấy về danh sách chương của truyện có mã truyện = "matruyen"
    public ArrayList<String> layDSChuong(String matruyen) {
        ArrayList<String> mylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "MATRUYEN = ?";
        String[] selectionArgs = { matruyen };
        Cursor c = db.query(TB_CHITIETCHUONG, null, selection, selectionArgs, null, null, null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            data = "\t" + c.getString(1) ;
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        db.close();
        return  mylist;
    }
    public ArrayList<String> layDSMaChuong(String matruyen){
        ArrayList<String> mylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "MATRUYEN = ?";
        String[] selectionArgs = { matruyen };
        Cursor c = db.query(TB_CHITIETCHUONG, null, selection, selectionArgs, null, null, null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            data = c.getString(0) ;
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        db.close();
        return  mylist;
    }
    public Cursor getDataChuong(String machuong){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "MACHUONG = ?";
        String[] selectionArgs = {machuong};
        Cursor cs = db.query(TB_CHITIETCHUONG, null, selection, selectionArgs, null, null, null);
        if (cs != null && cs.moveToFirst()){
            return cs;
        }
        else return null;
    }


//Hiển thị ra trang chủ************************************************************************************************************************
//Lấy về tất cả trong bảng truyện
    public Cursor getDataTruyen(String matruyen){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "MATRUYEN = ?";
        String[] selectionArgs = { matruyen };
        Cursor cs = db.query(TB_TRUYEN, null, selection, selectionArgs, null, null, null);
        if (cs != null && cs.moveToFirst()){
            return cs;
        }
        else return null;
    }
//Lấy tất cả truyện
    public ArrayList<String> layDSTenTruyen(){
        ArrayList<String> mylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TB_TRUYEN, null, null, null, null, null, null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            data =   c.getString(1) ;
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        db.close();
        return  mylist;
    }
    public ArrayList<String> layDSMaTruyen(){
        ArrayList<String> mylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TB_TRUYEN, null, null, null, null, null, null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            data =   c.getString(0) ;
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        db.close();
        return  mylist;
    }

//Lịch sử đọc

    //Đầu vào là tên đăng nhập 
    //truy vấn select trong bảng lịch sử đọc theo tên đăng nhập và trả về danh sách mã truyện,
    //từ mã truyện truy vấn select trong bảng truyện để trả về tên truyện
    public ArrayList<String> laydanhsachlichsudoc(String username) {
        ArrayList<String> mylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "TENDANGNHAP = ?";
        String[] selectionArgs = { username };
        Cursor c = db.query(TB_LICHSUDOC, null, selection, selectionArgs, null, null, null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            String selection2 = "MATRUYEN = ?";
            String[] selectionArgs2 = { c.getString(2) };
            Cursor c2 = db.query(TB_TRUYEN, null, selection2, selectionArgs2, null, null, null);
            c2.moveToNext();
            while (!c2.isAfterLast()){
                data = c2.getString(1);
                mylist.add(data);
                c2.moveToNext();
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return  mylist;
    }

//Trang chủ************************************************************
    //Kiểm tra xem người dùng đã đọc truyện này chưa
    public boolean checkLichsudoc(String matruyen,String tendangnhap) {
        SQLiteDatabase db = this.getReadableDatabase();
        String querry = " SELECT * FROM " + TB_LICHSUDOC + " WHERE " + TB_TAIKHOAN_TENDANGNHAP + " = '" + tendangnhap + "' AND "+ TB_TRUYEN_MATRUYEN +" = '" + matruyen + "';";
        Cursor cursor = db.rawQuery(querry,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
        //Chưa đọc thì thêm vào bảng lịch sử đọc
        public void themLichsudoc(String matruyen,String tendangnhap){
            if(!checkLichsudoc(matruyen,tendangnhap)){
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(TB_TRUYEN_MATRUYEN,matruyen);
                values.put(TB_TAIKHOAN_TENDANGNHAP,tendangnhap);
                db.insert(TB_LICHSUDOC,null,values);
                db.close();
            }
        }
    public ArrayList<String> layDSAvatar(){
        ArrayList<String> mylist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TB_TRUYEN, null, null, null, null, null, null);
        c.moveToNext();
        String data = "";
        while(!c.isAfterLast()){
            data = c.getString(3) ;
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        db.close();
        return  mylist;
    }

    public Cursor timKiemTruyen(String searchText) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM"+ TB_TRUYEN +" WHERE"+ TB_TRUYEN_TENTRUYEN+ "LIKE '%" + searchText + "%'";
        return db.rawQuery(query, null);
    }
}


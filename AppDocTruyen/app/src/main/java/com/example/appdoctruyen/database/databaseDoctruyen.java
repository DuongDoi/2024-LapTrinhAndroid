package com.example.appdoctruyen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.*;

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
    private final static String createTB_Truyen = "CREATE TABLE " + TB_Truyen +
            "(" + matruyen + " varchar(10) primary key, " +
            tentruyen + " TEXT, " +
            motatruyen + " TEXT, " +
            avatar_url + " TEXT, " +
            sochuong + " integer)";

    //Tạo bảng chi tiết chương
    private final static String createTB_Chitietchuong = "CREATE TABLE " + TB_Chitietchuong +
            "(" + machuong + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            tenchuong + " TEXT, " +
            noidungchuong + " TEXT, " +
            matruyen + " varchar(10), " +
            "FOREIGN KEY (" + matruyen + ") REFERENCES " + TB_Truyen + "(" + matruyen + "))";


    //Tạo bảng tài khoản
    private final static String createTB_Taikhoan = "create table "+TB_Taikhoan
            +"("+tendangnhap+" varchar(30) primary key,"
            +matkhau+" varchar(30))";

    //Tạo bảng bình luận
    private final static String createTB_Binhluan = "CREATE TABLE " + TB_Binhluan +
            "(" + mabinhluan + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            noidung + " TEXT, " +
            noidungchuong + " TEXT, " +
            matruyen + " varchar(10), " +
            tendangnhap + " varchar(30), " +
            "FOREIGN KEY (" + matruyen + ") REFERENCES " + TB_Truyen + "(" + matruyen + ")," +
            "FOREIGN KEY (" + tendangnhap + ") REFERENCES " + TB_Taikhoan + "(" + tendangnhap + "))";

    //Tạo bảng lịch sử đọc
    private final static String createTB_Lichsudoc = "create table "+TB_Lichsudoc
            +"("+malichsudoc+" integer primary key  autoincrement, "
            +chuongdangdoc+" integer,"
            +matruyen+" varchar(10), "
            +tendangnhap+" varchar(30)," +
            " foreign key ("+tendangnhap+") references " +TB_Taikhoan+"("+tendangnhap+")," +
            " foreign key ("+matruyen+") references " +TB_Truyen+"("+matruyen+"))";



    //Insert DL
    private final String insertTruyen1 = "INSERT INTO TB_Truyen VALUES(1,'Tinh tế nam thần là ba ta','Diêu Tư là trạch nữ không cha không mẹ, cuộc sống luôn bình bình thường thường, không tai nạn không khó khăn, cô cảm thấy cả đời cứ như vậy sống yên tới già.\n" +
            "\n" +
            "Sau đó… cô chết!\n" +
            "\n" +
            "Biến thành quỷ hút máu, còn là gà bệnh đời thứ năm, không hề có khả năng chiến đấu. Diêu Tư cảm thấy, cho dù nội chiến của Huyết tộc đánh lớn cỡ nào, trừ khi có kỳ tích, nếu không ngọn lửa đó chẳng bao giờ lan tới chỗ cô.\n" +
            "\n" +
            "Vì thế, kỳ tích xảy ra…\n" +
            "\n" +
            "Ngủ một giấc, vượt qua thời gian của cả hành tinh, Huyết tộc toàn vũ trụ đều xếp thành hai hàng gọi cô một tiếng lão tổ tông.\n" +
            "\n" +
            "Hát khúc ca nông nô đổi đời, xem ai còn dám nói cô là gà bệnh!\n" +
            "\n" +
            "Đến đây, đánh nhau nha, đánh không thắng ta gọi ngươi là ba.\n" +
            "\n" +
            "Vì thế… cô có thêm một ba ba.\n" +
            "\n" +
            "Diêu Tư \n" +
            "\n" +
            "Mộ Huyền','https://static.8cache.com/cover/o/eJzLyTDW1zWMtzD2MdJ1MjSO1A8rLE1Oig_2MA301HeEAi8jE_2kiqJAR--UjICccv1yI0NT3QxjIyNdz2QTIwCCqxLp/tinh-te-nam-than-la-ba-ta.jpg',76)";

    private final String insertTruyen2 = "INSERT INTO TB_Truyen VALUES(2,'Cô vợ ngọt ngào có chút bất lương','\"Khẩu vị của người này rốt cuộc ra sao a! Cái này cũng bỏ được vào miệng à?\"\n" +
            "\n" +
            "Sau khi cô tỉnh dậy, nhìn vào trong gương thấy chính mình đầu xăm mặt giống như quỷ, cảm giác chỉ nhìn thêm một giây cũng hỏng đôi mắt.\n" +
            "\n" +
            "Trước khi trọng sinh, Cố Việt Trạch chính là người mà cô dùng cả tấm lòng để yêu nhưng sau đó cũng là người mà cô hận thấu xương.\n" +
            "\n" +
            "Đời trước cô chính là kiểu phụ nữ não tàn nên mới không muốn lây một ông xã tuyệt sắc, lại bị đôi tiện nam nữ hãm hại, bị người bạn thân nhất tẩy não, kết cục cuối cùng chính là không còn người nào muốn ở gần cô.\n" +
            "\n" +
            "Đời này mặc cho các ngươi trâu bò rắn rết trăm phương nghìn kế, muốn cô ly dị, nhường đi ngôi vị phu nhân. Ngượng ngùng quá ~~, chỉ số thông minh của bản tiểu thư đã lên dây rồi nhé!','https://static.8cache.com/cover/o/eJzLyTDW1zXKKnJ09440NzRw1Q8zCc_3LDTyDo_w1HeEgsCCdP2MND9Hl2wTP3_fYoOy3EDDSq-gomKf5GRns6KCIsPcxHRnD1_9ciNDU90MYyMjAHumGmo=/co-vo-ngot-ngao-co-chut-bat-luong-vo-moi-bat-luong-co-chut-ngot.jpg',2489)";

    private final String insertTruyen3 = "INSERT INTO TB_Truyen VALUES(3,'Đạo tình','Đây là tác phẩm hắc bang, bối cảnh hoành tráng, tình tiết đẫm máu u ám. Nếu bạn muốn xem câu truyện tình lãng mạn thì đừng nhảy vào hố này. \"Đạo tình\" là một thế giới đen tối, trong đó nam chính tuyệt đối không phải là người tốt.\n" +
            "\n" +
            "- Nàng là một trong những tên trộm kỳ tài nhất thế kỷ hai mốt. Nàng là nữ hoàng đua xe trong bóng đêm. Nàng là một người phụ nữ giống như cơn gió, đi đến bất định.\n" +
            "\n" +
            "- Hắn là ông trùm đứng nhất nhì trong giới hắc đạo. Hắn là kẻ máu lạnh, tàn nhẫn, vô tình. Không ngờ trên đời này lại có người dám động đến tài sản của hắn. Nếu người đó chán sống, hắn sẽ kết thúc mạng sống thay.\n" +
            "\n" +
            "- Là tình, là yêu, là đau, hay là hận?... tất cả đều không rõ ràng.\n" +
            "\n" +
            "- Trên các diễn đàn về tiểu thuyết ngôn tình ở Trung Quốc, \"Đạo tình\" nhận được vô số lời khen từ độc giả như \"đọc đi đọc lại không chán\", \"tiểu thuyết hắc bang kinh điển\"... Hy vọng cuốn truyện sẽ khiến mọi người hài lòng. Còn bây giờ, chúng ta hãy bắt đầu cuộc phiêu lưu trong thế giới \"Đạo tình\"...','https://static.8cache.com/cover/o/eJzLyTDR1w3NNCp29dKNcit01A9zSktPTHMyLij21HeEguyQQP1wv0q_0CJDo9IIE_1yI0NT3QxjIyMAUDASSQ==/dao-tinh.jpg',132)";

    private final String insertTruyen4 = "INSERT INTO TB_Truyen VALUES(4,'Trò chơi nguy hiểm: Tổng tài tội ác tày trờ','Nội Dung Truyện:\n" +
            "\n" +
            "Là ma quỷ, muốn giữ lấy người, hắn chỉ biết dùng cách thức của bản thân. Trong giới giải trí, cô là ngôi sao ca nhạc được chú ý nhất, và đằng sau, cũng là một con chim được một sợi dây vàng trói buộc, mãi mãi không thoát khỏi bàn tay hắn.\n" +
            "\n" +
            "Tám tuổi, cô được đưa đến Bạc Tuyết viên xinh đẹp. Biệt thự mộng ảo, người hầu đếm không xuể, duy chỉ thiếu nam chủ nhân thần bí——cha nuôi trên danh nghĩa của cô… Nghe nói, hắn là người đàn ông tàn khốc nhất, tội ác tày trời, một tay che trời; đồng thời lại là gã si tình, triền miên nguy hiểm nhất…Mười năm, cô thoát kén hóa bướm, và trong bóng đêm đôi mắt ám liệt ấy vẫn chăm chú quan sát. Lễ thành nhân năm mười tám tuổi, người cha nuôi thần bí cuối cùng cũng xuất hiện… Truyền thuyết trở thành sự thật cũng là lúc ác mộng của cô bắt đầu… Đêm tân hôn của cô, tập trung rất nhiều ngôi sao, chú rể chết vô cớ, động phòng cũng là người đàn ông ma quỷ đó. Hắn cười lạnh thấu xương, bàn tay lớn khẽ vuốt bụng cô, “Lập gia đình, cũng được, có điều, ta muốn biết nơi này sẽ là cháu ta, hay là…con ta!”\n" +
            "\n" +
            "Bóng tối bao trùm, ảo mộng nát tan… Hắn lạnh lùng nói: “Vĩnh viễn đừng nghĩ đến chuyện rời đi.” Cô chỉ có thể ngước mắt nhìn người đàn ông đáng sợ này…Một tình yêu cấm kỵ cứ thế tiếp diễn hàng đêm…','https://static.8cache.com/cover/o/eJzLyTDW140IdjQy1vVyCsz21A9zLrDMS_Yq8s7w1HeEguzEUP2C0hQnvyJvD-dCX_1yI0NT3QxjIyMAQRQSPQ==/tro-choi-nguy-hiem-tong-tai-toi-ac-tay-troi.jpg',200)";

    private final String insertchuongTruyen1 = "insert into TB_Chitietchuong values\n" +
            "(1,'Chương 1: Em gái, em đã chết!','Diêu Tư cảm thấy mình gần đây có chút gì đó không đúng, cô luôn là người ngủ đến mức sét đánh cũng không nhúc nhích, vậy mà mấy ngày nay lại không ngủ được. Kỳ lạ là tinh thần lại tốt cực kỳ.\n" +
            "\n" +
            "Eo không mỏi, chân không mềm, liền ngay cả dì cả nhiều năm gây rối cho cô cũng đều không tới, một hơi leo lên mười tầng lầu cũng không mệt. Càng thần kỳ chính là vốn dĩ trong ăn uống cô cực thích ăn cay, nhưng hiện tại vừa thấy ớt cay thì dạ dày liền đau. Ban đầu, cô còn tưởng rằng do mất ngủ nên dạ dày suy yếu, nhưng vài ngày sau cô phát hiện không chỉ đồ cay, mà đối với tất cả đồ ăn đều mất đi hứng thú, giống như bị bệnh kén ăn.'),\n" +
            "(1,'Chương 2: Cô muốn chết ở đâu?','Mặt trời lặn về tây, sắc trời dần tối.\n" +
            "\n" +
            "Diêu Tư lúc này mới cuốn lên bức màn thật dày thở phào một hơi, nhìn dưới lầu đèn đuốc sáng trưng, cô thuận tay cầm lấy một cái áo khoác màu đen, đang định ra cửa. Di động lại ngay lúc này đinh linh linh vang lên.\n" +
            "\n" +
            "Cô móc ra thì thấy, mặt trêи đó viết bốn chữ “Số không xác định”, đáy lòng cô không khỏi căng thẳng, nhìn chằm chằm bốn chữ này hai giây, mới hít sâu một hơi bắt máy.'),\n" +
            "(1,'Chương 3: Flag tiến lên','“Em không thấy thông báo?” Lý Chính so với cô càng kinh ngạc.\n" +
            "\n" +
            "“Thông báo?”\n" +
            "\n" +
            "“Xem anh này, đã quên em không có Người dẫn đường, còn không biết dùng.” Hắn vỗ vỗ đầu, lại phẫn nộ mắng to lên, “Thật là tạo nghiệt nga, đều do cái tên đáng chém ngàn đao kia, biến đổi em xong lại không phụ trách, đem em một cái con non ném ở chỗ này mặc kệ, hắn đáng cô độc cả đời.” Hắn một bên mắng, một bên lại không quên cầm lấy thẻ thân phận trong tay cô, cũng không biết làm như thế nào, tấm card hình con dơi đột nhiên biến thành màu đỏ chói mắt, con số “5” ở phía trêи cũng biến thành dấu chấm than (!).');";

    private final String insertchuongTruyen2 = "insert into TB_Chitietchuong values\n" +
            "(2,'Chương 1: Còn muốn trốn sao?','Diệp Oản Oản mở mắt.\n" +
            "\n" +
            "Đối mặt là đôi môi khiến cho cô sợ hãi đến ngay cả linh hồn đều run rẩy con ngươi.\n" +
            "\n" +
            "\"A--\"\n" +
            "\n" +
            "Đôi tay tái nhợt của cô gái đột nhiên cầm cái chăn ngăn chặn vật dưới thân thể.\n" +
            "\n" +
            "Cảm giác tê liệt thống khổ, rốt cuộc bản thân vẫn sống sờ sờ mà vẫn phải trải qua một lần nữa.\n" +
            "\n" +
            "Chẳng lẽ nơi này là Địa Ngục sao?\n" +
            "\n" +
            "Tại sao, rõ ràng cô đã chết, nhưng lại trở về nơi ày, trở lại bên cạnh tên ác ma này?\n" +
            "\n" +
            "Trong đầu của cô lập tức bị nhiệt độ nóng bỏng của người đàn ông bốc hơi lên đến hỗn loạn tưng bừng, theo bản năng chống cự, \"Đừng đụng vào tôi!\"\n" +
            "\n" +
            "Động tác của người đàn ông ngưng lại một hồi, ngay sau đó giống như bị đụng vào vảy ngược, khuôn mặt giống quỷ lệ khát máu nhất thời u ám, môi mỏng lạnh giá tàn nhẫn cắn xé đi xuống, giống như muốn nuốt hết máu và xương của cô vào bụng.'),\n" +
            "(2,'Chương 2: Nuốt được đến miệng','A...\n" +
            "\n" +
            "Nhưng mà, thay đổi, cô phải thay đổi như thế nào đây?\n" +
            "\n" +
            "Tư Dạ Hàn quyền thế ngút trời, bóp chết cô còn dễ hơn so với bóp chết một con kiến, thứ mà anh ta đã muốn muốn, tuyệt đối không có gì là không thể chiếm được.\n" +
            "\n" +
            "Diệp Oản Oản hít sâu một hơi, bức lui sợ hãi trong thân thể đối với người đàn ông kia.\n" +
            "\n" +
            "Nhất định sẽ có biện pháp!\n" +
            "\n" +
            "Ít nhất cô đã không phải là thiếu nữ ngu ngốc, ngu xuẩn của bảy năm trước mặc cho người khác định đoạt.\n" +
            "\n" +
            "\"Trời ơi! Oản Oản...\" Bên tai đột nhiên truyền tới một tiếng kêu kích động.\n" +
            "\n" +
            "Nghe được cái âm thanh quen thuộc này, sống lưng Diệp Oản Oản nhất thời cứng đờ, ánh mắt lạnh như băng thẳng hướng cửa nhìn.'),\n" +
            "(2,'Chương 3: Vị hôn phu trước','Đảo mắt đã là ba ngày sau.\n" +
            "\n" +
            "Ba ngày này, Diệp Oản Oản phần lớn thời gian đều là ngủ và sửa sang lại trí nhớ của kiếp trước.\n" +
            "\n" +
            "Tư Dạ Hàn cùng kiếp trước vẫn như thế, suốt ba ngày cũng không có xuất hiện, từng người trong phòng đều vùi đầu vào làm việc, nói chuyện với cô cực ít, thậm chí ngay cả ánh mắt cũng không dám cùng với cô đối mặt.\n" +
            "\n" +
            "Nhà ở lớn như vậy, giống như bị bỏ hoang.\n" +
            "\n" +
            "Diệp Oản Oản thay đổi quần áo ngủ, liếc nhìn thời gian trên đồng hồ, ngay sau đó xuống lầu hướng trong sân đi tới.\n" +
            "\n" +
            "Tối nay ánh trăng rất tốt, gió mát hiu hiu, để cho cái nhà tù âm trầm trong trí nhớ cũng không đáng sợ tới như vậy.\n" +
            "\n" +
            "Thật ra thì, cảnh trí của ngôi nhà rất đẹp, dù sao cũng là Tư Dạ Hàn tự mình thiết kế, còn mời cả đoàn đội kiến trúc cấp thế giới, ở vị trí phong thủy bảo địa tốt nhất Đế Đô, hao tốn suốt năm năm thời gian mới hoàn toàn làm xong.')";

    private final String insertchuongTruyen3 = "insert into TB_Chitietchuong values\n" +
            "(3,'Chương 1: Ăn trộm','Ding dong, ding dong, chuông đồng hồ buổi sáng điểm chín tiếng. Khi tiếng chuông vang lên, tủ kính chống trộm phát ra tiếng động nhẹ, rồi từ từ bị dỡ ra.\n" +
            "\n" +
            "Một bàn tay ngọc ngà thò vào bên trong nhấc miếng ngọc đặt trên quầy triển lãm, tiếng nói khe khẽ cất lên: \" Có đầu rồng, hoa văn long phượng...quả nhiên là đồ tốt\".\n" +
            "\n" +
            "\"Mấy thứ đó mà trị giá những 30 triệu đô la Mỹ?\" Một giọng nói vọng đến từ cửa phòng triển lãm.\n" +
            "\n" +
            "Cô gái đứng trước tủ trưng bày miếng ngọc mỉm cười, không quay đầu lại. Cô dùng ngón chỏ và ngón giữa kẹp miếng ngọc ném về nơi phát tiếng nói, rồi từ từ quay người: \"Bảo vật của thời Tây Chu, chị thử nói xem?\". Cô gái đứng cách đó không xa nhảy lên bắt trọn rồi chăm chú nhìn miếng ngọc trong tay mình.'),\n" +
            "(3,'Chương 2: Lần đầu gặp gỡ','Trong một ngôi biệt thự gần bờ biển ở San Francisco, một cô gái có vẻ bề ngoài dịu dàng đứng ở cửa nhà bếp, nói với cô gái đang ngồi xem tivi vừa ăn hoa quả: \"Ly Tâm, mau đi mua xì dầu. Nhà hết xì dầu rồi\".\n" +
            "\n" +
            "Ly Tâm cuộn mình trong sofa bất giác cau mày. Ban đầu, cô cảm thấy dãy biệt thự này nằm gần bờ biển, không khí trong lành lại đúng lúc cho thuê nên mới quyết định thuê một ngôi. Hơn nữa, chủ nhà là đồng hương, người có vẻ lịch sự trang nhã nên cô đoán sẽ không gặp phiền phức. Nào ngờ, chủ ngân ngôi biệt thự này nhiệt tình quá đáng, thấy cô là đồng bào tóc đen như mình liền coi cô như em gái. Chủ nhà luôn tỏ thái độ thân mật, không cần biết tâm trạng của người khác ra sao.'),\n" +
            "(3,'Chương 3: Đua xe','Ly Tâm giảm tốc độ, đi từ từ để thưởng thức trò vui. Quả nhiên nghe cô nhắc nhở, có kẻ thông minh quay về lấy xe. Ly Tâm lắc đầu đầy thất vọng. Xem ra trò vui sắp kết thúc rồi, cô quay đầu lại chuẩn bị tăng tốc độ.\n" +
            "\n" +
            "Đúng lúc này, xe Ly Tâm đi ngang qua hai người đàn ông bỏ chạy. Trong khi cô chưa kịp nhấn ga, một người đàn ông thuận tay nắm lấy cửa xe cô bay người nhảy trong rồi kéo người còn lại leo lên xe.')";

    private final String insertchuongTruyen4 = "insert into TB_Chitietchuong values\n" +
            "(4,'Chương 1','Đêm tối, gió thổi mây tan, hạt mưa lớn đọng trên tấm cửa kính trong suốt của cửa sổ sát đất, dần tan đi chỉ còn một mảnh mỏng manh.\n" +
            "\n" +
            "Bên trong căn biệt thự tràn ngập mùi máu tươi.\n" +
            "\n" +
            "Một người thanh niên bị rất đông đám vệ sĩ giữ chặt, khuôn mặt lạnh lùng dị thường dính đầy máu tươi, đôi môi lạnh lẽo mím chặt, một vệt máu lại từ khóe miệng anh ta chảy xuống.'),\n" +
            "(4,'Chương 2','Tám năm sau...\n" +
            "\n" +
            "Mưa dầm đến đầu chiều, tí tách tí tách tựa như không thấy có chút dấu hiệu ngớt, đầu ngõ âm u ẩm ướt, đầy những tiếng cãi lộn của phụ nữ cùng tiếng bọn trẻ con.\n" +
            "\n" +
            "\"Con nhãi thối tha, còn chạy đi đâu hả?\"\n" +
            "\n" +
            "Một người phụ nữ nhìn khoảng bốn mươi tuổi hung hăng tóm lấy một bé gái nhỏ bé, yếu ớt, dùng cây roi thô đánh vào người đứa nhỏ...'),\n" +
            "(4,'Chương 3','Tòa thành Bạc Tuyết tuyệt đẹp còn được gọi là Bạc Tuyết bảo. Đây như một chốn yên vui, là nơi những bông hoa ngọc sơn bạc tuyết* nở rộ quanh năm. Cô bé vừa bước xuống từ chiếc xe cao cấp liền lạc vào tổng thể kiến trúc to lớn này, suốt dọc đường đi càng lúc càng thấy kinh ngạc.\n" +
            "\n" +
            "Đúng là một tòa thành! Đây là lần đầu tiên cô nhìn thấy một tòa thành ở khoảng cách gần như vậy, nơi này cao sang, tuyệt đẹp như thiên đường vậy.\n" +
            "\n" +
            "Mặt trời ló từ trong đám mây chiếu rọi ánh sáng rực rỡ, làn sương mù mờ ảo, lập lờ như biển mây vây lấy Bạc Tuyết bảo, tựa như một bức thi họa; đỉnh mái trắng tinh, tạo hình duyên dáng, cao thấp đan xen, lầu trong lầu, cửa sổ thông thiên thẳng xuống, cột trụ đều thiết kế dáng vòm, điển hình cho phong cách kiến trúc Rô-ma.')";


    private final String insertTaikhoan = "insert into TB_Taikhoan values \n" +
            "('tminigo0', 'oG8%`v$@'),\n" +
            "('kberkeley1', 'lN5=.ETSM*o9$'),\n" +
            "('cgulk2', 'jX9''M=R7rXw77('),\n" +
            "('mweatherup3', 'mR1''(.Bz'),\n" +
            "('cayars4', 'pS9=8/ob/pucI'),\n" +
            "('lmunn5', 'pB0}kUI<U$'),\n" +
            "('gwainscoat6', 'wL6?\"DEBD~rB|'),\n" +
            "('gwand7', 'vS1)k5L6032'),\n" +
            "('tluetchford8', 'cW7(AnUc''cKs'),\n" +
            "('iallchorn9', 'sC6\"V5(q')";
    private final String insertBinhluan = "insert into TB_Binhluan values\n" +
            "('cayars4',1,'Hay quá'),\n" +
            "('cayars4',2,'Quá tuyệt vời'),\n" +
            "('lmunn5',1,'Nữ chính xinh quá'),\n" +
            "('lmunn5',4,'Nam chính ngầu bá cháy'),\n" +
            "('gwand7',3,'Nội dung xì ke')";



    private final String insertLichsudoc = "insert into TB_Lichsudoc values\n" +
            "('gwainscoat6',1,3),\n" +
            "('iallchorn9',1,2),\n" +
            "('cayars4',2,1),\n" +
            "('lmunn5',4,2),\n" +
            "('gwand7',3,1)";

    public databaseDoctruyen(@Nullable Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(createTB_Truyen);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(createTB_Taikhoan);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(createTB_Chitietchuong);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(createTB_Binhluan);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(createTB_Lichsudoc);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertTruyen1);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertTruyen2);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertTruyen3);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertTruyen4);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertchuongTruyen1);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertchuongTruyen2);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertchuongTruyen3);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertchuongTruyen4);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertTaikhoan);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertBinhluan);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            db.execSQL(insertLichsudoc);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Thêm mới tài khoản
    public void addNewUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tendangnhap, username);
        values.put(matkhau, password);
        db.insert(TB_Taikhoan, null, values);
        db.close();
    }


    //Check tài khoản đã tồn tại chưa
    public boolean checkUserExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_Taikhoan + " WHERE " + tendangnhap + "=?", new String[]{username});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    //Check tài khoản login

    public boolean checkUserLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_Taikhoan + " WHERE " + tendangnhap + "=?" + " AND " + matkhau + "=?", new String[]{username,password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }


}

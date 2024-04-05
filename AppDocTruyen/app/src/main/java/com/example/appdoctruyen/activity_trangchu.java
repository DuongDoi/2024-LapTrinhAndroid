package com.example.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.appdoctruyen.database.databaseDoctruyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class activity_trangchu extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navview;
    ListView listview;
    EditText timKiem;
    ImageButton btnsearch;
    private ListView lvDSTruyen;
    private ArrayList<String> myList;
    private ArrayAdapter<String> myadapter;

    public static String username,matruyen;
    DrawerLayout drawerLayout;
    databaseDoctruyen db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        username = getIntent().getStringExtra("TENDANGNHAP");

        db = new databaseDoctruyen(this);
        db.open();

        AnhXa();
        ActionViewFlipper();
        ActionBar();

        // Xử lý sự kiện khi người dùng muốn chuyển đến màn hình tìm kiếm
//        timKiem.setOnClickListener(view -> openTimKiemActivity());


        lvDSTruyen = findViewById(R.id.trangchu_lvdstruyen);
        myList = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myList);
        lvDSTruyen.setAdapter(myadapter);
        hienthiDSTruyen();
        lvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenTruyenDuocChon = myList.get(position);
                int viTri = myList.indexOf(tenTruyenDuocChon);
                String maTruyenTuongUng = db.layDSMaTruyen().get(viTri);
                matruyen = maTruyenTuongUng;
                Intent intent = new Intent(activity_trangchu.this,activity_chitiettruyen.class);
                intent.putExtra("TENDANGNHAP",username);
                intent.putExtra("MATRUYEN",matruyen);
                db.themLichsudoc(matruyen,username);
                startActivity(intent);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_trangchu.this, ManTimKiem.class);
                startActivity(intent);
            }
        });

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Kiểm tra xem ID của mục menu được chọn có phải là "lsdoc" không
                if (item.getItemId() == R.id.lsdoc) {
                    // Chuyển hướng sang hoạt động mới (activity_lsdoc)
                    Intent intent = new Intent(activity_trangchu.this, activity_lichsudoc.class);
                    intent.putExtra("TENDANGNHAP",username);
                    startActivity(intent);
                    return true;
                }
                if (item.getItemId() == R.id.logout) {
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
    private void hienthiDSTruyen(){
        myList.clear();
        myList.addAll(db.layDSTenTruyen());
        myadapter.notifyDataSetChanged();
    }
    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listview = findViewById(R.id.trangchu_lvdstruyen);
        navview = findViewById(R.id.navview);
        drawerLayout = findViewById(R.id.drawerlayout);
        timKiem = findViewById(R.id.menu1);
        btnsearch = findViewById(R.id.btnsearch);
    }
    private void ActionViewFlipper() {
        //mảng chứa ảnh cho màn hình chạy
        ArrayList<String> mangQC = new ArrayList<>();
        //add ảnh vào mảng
        mangQC.add("https://static.8cache.com/cover/o/eJzLyTDR1w3NNCp29dKNcit01A9zSktPTHMyLij21HeEguyQQP1wv0q_0CJDo9IIE_1yI0NT3QxjIyMAUDASSQ==/dao-tinh.jpg");
        mangQC.add("https://img.dtruyen.com/public/images/large/phuthenhangheoT2M2PHCDyr.jpg");
        mangQC.add("https://static.8cache.com/cover/o/eJzLyTDW1zWMtzD2MdJ1MjSO1A8rLE1Oig_2MA301HeEAi8jE_2kiqJAR--UjICccv1yI0NT3QxjIyNdz2QTIwCCqxLp/tinh-te-nam-than-la-ba-ta.jpg");
        mangQC.add("https://static.8cache.com/cover/o/eJzLyTDW1zXKKnJ09440NzRw1Q8zCc_3LDTyDo_w1HeEgsCCdP2MND9Hl2wTP3_fYoOy3EDDSq-gomKf5GRns6KCIsPcxHRnD1_9ciNDU90MYyMjAHumGmo=/co-vo-ngot-ngao-co-chut-bat-luong-vo-moi-bat-luong-co-chut-ngot.jpg");

        //thực hiện vòng lặp for gán ảnh vào Imageview, rồi từ imgview lên app
        for(int i=0; i<mangQC.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangQC.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //thêm ảnh từ imageView vào viewFlipper
            viewFlipper.addView(imageView);

            //thiết lập tự động chạy trong 4s
            viewFlipper.setFlipInterval(3000);

            //run auto viewFlipper
            viewFlipper.setAutoStart(true);

            //gọi animation cho vào và ra
            Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
            Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

            //gọi animation vào viewFlipper
            viewFlipper.setInAnimation(slide_in);
            viewFlipper.setInAnimation(slide_out);
        }
    }

    //Tạo thanh action bar với toolbar
    private void ActionBar() {
        //Hàm hỗ trợ toolBar
        setSupportActionBar(toolbar);
        //set nút của toolbar là true
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        //Tạo sự kiện click cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi lại drawerlayout, do toolbar được gọi ra nhờ drawerlayout
                drawerLayout.openDrawer(GravityCompat.START);   //GravityCompat.START làm nó nhảy ra giữa
            }
        });
    }

    //    Bắt sự kiện khi click vào search
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu1) {
            Intent intent = new Intent(this, ManTimKiem.class);
            intent.putExtra("TENDANGNHAP", username);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
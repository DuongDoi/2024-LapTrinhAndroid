package com.example.appdoctruyen;

import static com.example.appdoctruyen.database.databaseDoctruyen.TB_Truyen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.appdoctruyen.database.TruyenDataSource;
import com.example.appdoctruyen.database.databaseDoctruyen;
import com.example.appdoctruyen.model.ChuyenMuc;
import com.example.appdoctruyen.model.Truyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class activity_trangchu extends AppCompatActivity {
    //Khai báo các biến giao diện
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navview;
    DrawerLayout drawerlayout;
    Menu menusearch;
    //Khai báo listview
    ListView lvthongtin, lvMHC;
    ArrayList<String> listTruyen;
//    ArrayAdapter<String> adapterTruyen;
    TruyenDataSource db;
    //abc
    databaseDoctruyen dbDoctruyen;
    SQLiteDatabase sqLiteDatabase;
    adapterTruyen adapterTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        dbDoctruyen=new databaseDoctruyen(this);

        AnhXa();
        ActionBar();
        ActionViewFlipper();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void displayData() {
        sqLiteDatabase=dbDoctruyen.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TB_Truyen+"", null);
        ArrayList<Truyen>truyenArrayList=new ArrayList<>();
        while(cursor.moveToNext()){
            String avatar=cursor.getString(3);
            String name=cursor.getString(1);
            truyenArrayList.add(new Truyen(avatar, name));
        }
        cursor.close();
        adapterTruyen=new adapterTruyen(this, R.layout.item_truyen, truyenArrayList, sqLiteDatabase);
        recyclerView.setAdapter(adapterTruyen);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        //set nút cho ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        //bắt sự kiện click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerView = findViewById(R.id.recycleView);
        navview = findViewById(R.id.navview);
        drawerlayout = findViewById(R.id.drawerlayout);
        menusearch = findViewById(R.id.menusearch);
        //Tạo listview
        lvthongtin = findViewById(R.id.lvthongtin);
        lvMHC = findViewById(R.id.lvMHC);
        listTruyen = new ArrayList<>();
    }
    private void ActionViewFlipper() {
        //mảng chứa ảnh cho màn hình chạy
        ArrayList<String> mangQC = new ArrayList<>();
        //add ảnh vào mảng
        mangQC.add("https://wattpad.vn/assets/img/story/tinh_te_nam_than_la_ba_ta.1612549508.jpg");
        mangQC.add("https://img.dtruyen.com/public/images/large/phuthenhangheoT2M2PHCDyr.jpg");
        mangQC.add("https://sachtruyen.net/covers/co-vo-ngot-ngao-co-chut-bat-lu.e14b2.cover.jpg");

        //thực hiện vòng lặp for gán ảnh vào Imageview, rồi từ imgview lên app
        for(int i=0; i<mangQC.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangQC.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //thêm ảnh từ imageView vào viewFlipper
            viewFlipper.addView(imageView);

            //thiết lập tự động chạy trong 4s
            viewFlipper.setFlipInterval(4000);

            //run auto viewFlipper
            viewFlipper.setAutoStart(true);

            //gọi animation cho vào và ra
            Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
            Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

            //gọi animation vào viewFlipper
            viewFlipper.setInAnimation(slide_in);
            viewFlipper.setOutAnimation(slide_out);
        }
    }

    private class adapterTruyen extends RecyclerView.Adapter {
        public adapterTruyen(activity_trangchu activityTrangchu, int itemTruyen, ArrayList<Truyen> truyenArrayList, SQLiteDatabase sqLiteDatabase) {
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}

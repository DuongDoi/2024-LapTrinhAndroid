package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navview;
    ListView listview, lvthongtin, lvMHC;
    DrawerLayout drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        AnhXa();
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {
        //mảng chứa ảnh cho màn hình chạy
        ArrayList<String> mangQC = new ArrayList<>();
        //add ảnh vào mảng
        mangQC.add("https://img.wattpad.com/cover/167547650-416-k53203.jpg");
        mangQC.add("https://img.dtruyen.com/public/images/large/phuthenhangheoT2M2PHCDyr.jpg");

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
            viewFlipper.setInAnimation(slide_out);
        }
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listview = findViewById(R.id.listview);
        lvthongtin = findViewById(R.id.lvthongtin);
        lvMHC = findViewById(R.id.lvMHC);
        navview = findViewById(R.id.navview);
        drawerlayout = findViewById(R.id.drawerlayout);
    }
}
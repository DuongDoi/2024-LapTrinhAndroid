package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appdoctruyen.database.databaseDoctruyen;

public class activity_chitiettruyen extends AppCompatActivity {

    public static String username, matruyen ;
    ImageView img_binhluan,img_dschuong,img_quaylai,imgAvatar;
    TextView tvTentruyen,tvMotatruyen,tvSochuong;
    databaseDoctruyen db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiettruyen);
        username = getIntent().getStringExtra("TENDANGNHAP");
        matruyen = getIntent().getStringExtra("MATRUYEN");

        db = new databaseDoctruyen(this);
        db.open();

        tvTentruyen = findViewById(R.id.chitiettruyen_tvTentruyen);
        tvMotatruyen = findViewById(R.id.chitiettruyen_tvnoidungtruyen);
        tvSochuong = findViewById(R.id.chitiettruyen_tvTrangthai);
        imgAvatar = findViewById(R.id.chitiettruyen_imgAvatar);
        String url_image;
        Cursor cursor = db.getDataTruyen(matruyen);
        if(cursor != null && cursor.moveToFirst()){
            tvTentruyen.setText(cursor.getString(1));
            tvMotatruyen.setText(cursor.getString(2));
            tvSochuong.setText(cursor.getString(4));
            url_image = cursor.getString(3);
            Glide.with(this).load(url_image).into(imgAvatar);
        }
        else {
            Toast.makeText(activity_chitiettruyen.this,"lỗi truy cập",Toast.LENGTH_SHORT).show();
        }
        cursor.close();

        img_binhluan = findViewById(R.id.chitiettruyen_imgvBinhluan);
        img_binhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chitiettruyen.this,activity_comment.class);
                intent.putExtra("TENDANGNHAP",username);
                intent.putExtra("MATRUYEN",matruyen);
                startActivity(intent);
            }
        });

        img_dschuong = findViewById(R.id.chitiettruyen_imgvDanhsachchuong);
        img_dschuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chitiettruyen.this,activity_danhsachchuong.class);
                intent.putExtra("TENDANGNHAP",username);
                intent.putExtra("MATRUYEN",matruyen);
                startActivity(intent);
            }
        });

        img_quaylai = findViewById(R.id.chitiettruyen_imgquaylai);
        img_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
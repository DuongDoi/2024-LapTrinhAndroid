package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appdoctruyen.database.databaseDoctruyen;

public class activity_doctruyen extends AppCompatActivity {

    public static String username, matruyen, machuong ;
    ImageView imgquaylai;
    TextView tvtenchuong,tvnoidungchuong;

    databaseDoctruyen db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctruyen);
        username = getIntent().getStringExtra("TENDANGNHAP");
        matruyen = getIntent().getStringExtra("MATRUYEN");
        machuong = getIntent().getStringExtra("MACHUONG");

        db = new databaseDoctruyen(this);
        db.open();
        tvtenchuong = findViewById(R.id.doctruyen_tvtenchuong);
        tvnoidungchuong =findViewById(R.id.doctruyen_tvnoidung);
        Cursor cursor = db.getDataChuong(machuong);
        if(cursor != null && cursor.moveToFirst()){
            tvtenchuong.setText(cursor.getString(1));
            tvnoidungchuong.setText(cursor.getString(2));
        }
        else {
            Toast.makeText(activity_doctruyen.this,"lỗi truy cập",Toast.LENGTH_SHORT).show();
        }
        cursor.close();


        imgquaylai =findViewById(R.id.doctruyen_imgQuayLai);
        imgquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
package com.example.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdoctruyen.database.databaseDoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_danhsachchuong extends AppCompatActivity {


    public static String username,matruyen,machuong;

    ImageView goback;
    private ListView lv_danhsachchuong;
    private ArrayList<String> myList;
    private ArrayAdapter<String> myadapter;

    databaseDoctruyen db = new databaseDoctruyen(activity_danhsachchuong.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachchuong);

        username = getIntent().getStringExtra("TENDANGNHAP");
        matruyen = getIntent().getStringExtra("MATRUYEN");

        lv_danhsachchuong = findViewById(R.id.lv_danhsachchuong);
        myList = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myList);
        lv_danhsachchuong.setAdapter(myadapter);
        hienthiDSChuong();
        //Toast.makeText(activity_danhsachchuong.this,"Tải thành công.",Toast.LENGTH_SHORT).show();
        lv_danhsachchuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenChuongDuocChon = myList.get(position);
                int viTri = myList.indexOf(tenChuongDuocChon);
                String maChuongTuongUng = db.layDSMaChuong(matruyen).get(viTri);
                machuong = maChuongTuongUng;
                Intent intent = new Intent(activity_danhsachchuong.this,activity_doctruyen.class);
                intent.putExtra("TENDANGNHAP",username);
                intent.putExtra("MATRUYEN",matruyen);
                intent.putExtra("MACHUONG",machuong);
                startActivity(intent);
            }
        });
        goback = findViewById(R.id.img_backdsc);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void hienthiDSChuong(){
        myList.clear();
        myList.addAll(db.layDSChuong(matruyen));
        myadapter.notifyDataSetChanged();
    }
}

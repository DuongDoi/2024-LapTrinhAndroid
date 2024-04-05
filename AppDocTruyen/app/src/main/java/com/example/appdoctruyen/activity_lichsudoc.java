package com.example.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen.database.databaseDoctruyen;

import java.util.ArrayList;
import java.util.List;

public class activity_lichsudoc extends AppCompatActivity {

    public static String username,matruyen;
    ImageView goback;
    ListView listView;
    ArrayList<String> myList;
    ArrayAdapter<String> myadapter;
    databaseDoctruyen db = new databaseDoctruyen(activity_lichsudoc.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsudoc);
        db.open();
        username = getIntent().getStringExtra("TENDANGNHAP");
        listView = findViewById(R.id.listview_lichsudoc);

        myList = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myList);
        listView.setAdapter(myadapter);
        hienthiDSLichsudoc();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenTruyenDuocChon = myList.get(position);
                int viTri = myList.indexOf(tenTruyenDuocChon);
                String maTruyenTuongUng = db.layDSMaTruyen().get(viTri);
                matruyen = maTruyenTuongUng;
                Intent intent = new Intent(activity_lichsudoc.this,activity_chitiettruyen.class);
                intent.putExtra("TENDANGNHAP",username);
                intent.putExtra("MATRUYEN",matruyen);
                startActivity(intent);
            }
        });



        goback = findViewById(R.id.img_backlsd);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void hienthiDSLichsudoc(){
        myList.clear();
        myList.addAll(db.laydanhsachlichsudoc(username));
        myadapter.notifyDataSetChanged();
    }
}

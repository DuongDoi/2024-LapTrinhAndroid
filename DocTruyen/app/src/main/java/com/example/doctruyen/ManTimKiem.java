package com.example.doctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctruyen.adapter.adaptertruyen;
import com.example.doctruyen.data.DatabaseDocTruyen;
import com.example.doctruyen.model.Truyen;

import java.util.ArrayList;

public class ManTimKiem extends AppCompatActivity {

    ListView listView;
    //Toolbar toolbar;
    //SearchView searchView;
    EditText edt;

    ArrayList<Truyen> TruyenArrayList;
    //
    ArrayList<Truyen> arrayList;

    adaptertruyen adaptertruyen;
    DatabaseDocTruyen databaseDocTruyen;

//    ArrayAdapter<Truyen> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        listView = findViewById(R.id.listviewtimkiem);
        //toolbar = findViewById(R.id.toolbartimkiem);
        edt = findViewById(R.id.timkiem);

        //ActionBar();
        initList();

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });

        //su kien click listiew
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManTimKiem.this,ManNoiDungTruyen.class);
                String tent =   arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                //Log.e("Tên truyện : ",tent);
                startActivity(intent);
            }
        });

    }

    //search
    private void filter(String text){

        //xóa sau mỗi lần gọi tới filter
        arrayList.clear();

        ArrayList<Truyen> filteredList = new ArrayList<>();

        for(Truyen item : TruyenArrayList){
            if (item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                //Thêm dữ liệu để hiển thị ra item nội dung
               arrayList.add(item);
            }
        }
        adaptertruyen.filterList(filteredList);
    }

    //Hàm  gán dữ liệu từ CSDL vào listview
    public void initList(){
        TruyenArrayList = new ArrayList<>();
        //
        arrayList = new ArrayList<>();
        databaseDocTruyen = new DatabaseDocTruyen(this);

        Cursor cursor1 = databaseDocTruyen.getData2();
        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            //Thêm dữ liệu vào mảng
            arrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adaptertruyen = new adaptertruyen(getApplicationContext(),TruyenArrayList);
            //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,TruyenArrayList);
            listView.setAdapter(adaptertruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }

//    //Tạo thanh action bar với toolbar
//    private void ActionBar() {
//        //Hàm hỗ trợ toolBar
//        setSupportActionBar(toolbar);
//        //set nút của toolbar là true
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }
}
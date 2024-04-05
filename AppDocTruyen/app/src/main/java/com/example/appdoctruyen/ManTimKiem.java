package com.example.appdoctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appdoctruyen.database.databaseDoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManTimKiem extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;
    //SearchView searchView;
    EditText edt;
    public static String username, matruyen;
    private ListView lvDSTruyen;

    ArrayList<Truyen> TruyenArrayList;
    //
    ArrayList<Truyen> arrayList;
    private ArrayList<String> myList;
    private ArrayAdapter<String> myadapter;
    databaseDoctruyen db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        db = new databaseDoctruyen(this);
        db.open();

        username = getIntent().getStringExtra("TENDANGNHAP");

//        performSearch();

        edt = findViewById(R.id.timkiem);

        lvDSTruyen = findViewById(R.id.listviewtimkiem);
        myList = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myList);
        lvDSTruyen.setAdapter(myadapter);
        hienthiDSTruyen();

        //su kien click listview
        lvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenTruyenDuocChon = myList.get(position);
                int viTri = myList.indexOf(tenTruyenDuocChon);
                String maTruyenTuongUng = db.layDSMaTruyen().get(viTri);
                matruyen = maTruyenTuongUng;
                Intent intent = new Intent(ManTimKiem.this,activity_chitiettruyen.class);
                intent.putExtra("TENDANGNHAP",username);
                intent.putExtra("MATRUYEN",matruyen);
                startActivity(intent);
            }
        });
    }

    private void hienthiDSTruyen(){
        myList.clear();
        myList.addAll(db.layDSTenTruyen());
        myadapter.notifyDataSetChanged();
    }

    private void performSearch(String searchText) {
        // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
        myList.clear();

        // Thực hiện truy vấn tìm kiếm trong cơ sở dữ liệu
        Cursor cursor = db.timKiemTruyen(searchText);

        // Kiểm tra xem có dữ liệu tìm kiếm được hay không
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy dữ liệu từ cơ sở dữ liệu và thêm vào danh sách myList
//                String tenTruyen = cursor.getString(cursor.getColumnIndex("TB_TRUYEN_TENTRUYEN"));
                int index = cursor.getColumnIndexOrThrow("TENTRUYEN");
                String tenTruyen = cursor.getString(index);
                myList.add(tenTruyen);
            } while (cursor.moveToNext());

            // Đóng con trỏ sau khi sử dụng xong
            cursor.close();
        }

        // Cập nhật Adapter để hiển thị kết quả tìm kiếm
        myadapter.notifyDataSetChanged();
    }

}
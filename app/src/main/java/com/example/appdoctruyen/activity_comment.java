package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdoctruyen.database.databaseDoctruyen;

import java.sql.SQLException;
import java.util.ArrayList;

public class activity_comment extends AppCompatActivity {
    private ImageButton btnComment;
    private EditText edtComment;
    private ListView lvComment;
    private ArrayList<String> myList;
    private ArrayAdapter<String> myadapter;

    public static String username,matruyen;


    databaseDoctruyen db = new databaseDoctruyen(activity_comment.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        username = getIntent().getStringExtra("TENDANGNHAP");
        matruyen = getIntent().getStringExtra("MATRUYEN");
        db.open();
        lvComment = findViewById(R.id.lvComment);
        myList = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myList);
        lvComment.setAdapter(myadapter);
        hienthicomment();
        //Toast.makeText(activity_comment.this,"Load comment successfully",Toast.LENGTH_SHORT).show();
        btnCommentClick();
    }
    private void hienthicomment(){
        myList.clear();
        myList.addAll(db.layBinhluan(matruyen));
        myadapter.notifyDataSetChanged();
    }
    private void btnCommentClick(){
        btnComment =findViewById(R.id.btnComment);
        edtComment = findViewById(R.id.edtComment);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = edtComment.getText().toString();
                String msg = "";
                if(!db.themBinhluan(comment,matruyen,username)){
                    msg = "Fail";
                }
                else {
                    msg = "Successfully";
                    edtComment.setText("");
                    hienthicomment();
                }
                Toast.makeText(activity_comment.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }




}

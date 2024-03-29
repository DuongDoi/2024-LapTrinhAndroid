package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.databaseDoctruyen;

public class RegistryActivity extends AppCompatActivity {

    private EditText Username,Password,rePassword;

    databaseDoctruyen db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registry);
        db = new databaseDoctruyen(this);
        db.open();
        Username = findViewById(R.id.dangky_tendangnhap);
        Password = findViewById(R.id.dangky_matkhau);
        rePassword = findViewById(R.id.dangky_nhaplaimatkhau);
        btn_doneClick();
        btn_thoatClick();

    }
    private void btn_doneClick()
    {
        Button buttonRegister = findViewById(R.id.dangky_btndangky);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                String repassword = rePassword.getText().toString();
                if(username.isEmpty() || password.isEmpty() || repassword.isEmpty()){
                    Toast.makeText(RegistryActivity.this, "Vui lòng điền đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
                else
                // Kiểm tra mật khẩu và mật khẩu nhập lại có khớp nhau hay không
                if (!password.equals(repassword)) {
                    // Nếu không khớp, hiển thị thông báo và không tiếp tục xử lý
                    Toast.makeText(RegistryActivity.this, "Mật khẩu phải giống nhau.", Toast.LENGTH_SHORT).show();
                }
                else{
                    addNewUserToDatabase(username, password);
                }


            }
        });
    }

    // Phương thức để thêm tài khoản mới vào cơ sở dữ liệu
    private void addNewUserToDatabase(String username, String password) {

        databaseDoctruyen databaseHelper = new databaseDoctruyen(RegistryActivity.this);

        if(databaseHelper.checkTendangnhap(username)){
            Toast.makeText(RegistryActivity.this, "Tài khoản đã ồn tại!!!", Toast.LENGTH_SHORT).show();
        }
        else{

            databaseHelper.themTaikhoan(username, password);

            Toast.makeText(RegistryActivity.this, "Đăng ký thành công. Quay lại trang đăng nhập", Toast.LENGTH_SHORT).show();

            finish();
        }
        databaseHelper.close();
    }

    private void btn_thoatClick()
    {
        Button btn_thoat;
        btn_thoat = findViewById(R.id.dangky_btnquaylai);
        btn_thoat.setOnClickListener(v -> {
            Toast.makeText(RegistryActivity.this,"Exit",Toast.LENGTH_SHORT).show();
            finish();
        });
    }




}
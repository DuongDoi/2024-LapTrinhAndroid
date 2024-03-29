package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import com.example.appdoctruyen.database.databaseDoctruyen;

import java.sql.SQLException;


public class LoginActivity extends AppCompatActivity {

    EditText edTendangnhap,edMatkhau;
    Button btnDangnhap,btnDangky;
    databaseDoctruyen db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        btnDangnhap = findViewById(R.id.dangnhap_btndangnhap);
        btnDangky = findViewById(R.id.dangnhap_btndangky);
        edTendangnhap = findViewById(R.id.dangnhap_tendangnhap);
        edMatkhau = findViewById(R.id.dangnhap_matkhau);
        db = new databaseDoctruyen(this);
        db.open();
        btn_dangnhapClick();
        btn_dangkyClick();
    }
    private void btn_dangnhapClick() {
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
    }
    private void handleLogin() {
        try {
            // Lấy dữ liệu từ EditText
            String username = edTendangnhap.getText().toString();
            String password = edMatkhau.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                if (isUserAdmin(username, password)) {
                    navigateToHomeActivity();
                    Toast.makeText(LoginActivity.this, "Welcome back boss ^.^", Toast.LENGTH_SHORT).show();
                } else if (loginUser(username, password)) {
                    navigateToHomeActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Lỗi!!! Tài khoản không tồn tại!!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Vui lòng điền đủ thông tin.", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            Toast.makeText(LoginActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isUserAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private boolean loginUser(String username, String password) throws SQLException {
        try {
            return db.checkDangnhap(username, password);
        }
        catch (Exception e){
            return false;
        }

    }

    private void navigateToHomeActivity() {
        Toast.makeText(LoginActivity.this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_trangchu.class);//Thay activity_comment = activity muốn chuyển đến khi đăng nhập thành công
        intent.putExtra("TENDANGNHAP", edTendangnhap.getText().toString());
        startActivity(intent);
    }

    private void btn_dangkyClick(){
        //Nút đăng ký
        btnDangky.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistryActivity.class);
            startActivity(intent);
        });
    }

}
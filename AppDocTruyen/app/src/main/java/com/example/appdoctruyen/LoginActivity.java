package com.example.appdoctruyen.;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import com.example.appdoctruyen.database.databaseDoctruyen;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button loginButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        databaseDoctruyen dbUser = new databaseDoctruyen(this);
        databaseDoctruyen.conn();
        // Ánh xạ các phần tử UI
        loginButton = findViewById(R.id.btn_login);
        usernameEditText = findViewById(R.id.edittext_username);
        passwordEditText = findViewById(R.id.edittext_repassword);

        // Thiết lập lệnh cho nút đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin người dùng nhập vào
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Kiểm tra thông tin đăng nhập và xử lý logic tương ứng
                if (isValidCredentials(username, password)) {
                    // Đăng nhập thành công, chuyển sang màn hình chính hoặc màn hình tiếp theo
                    // Ví dụ: startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this,"Succesful.",Toast.LENGTH_SHORT).show();
                } else {
                    // Hiển thị thông báo lỗi hoặc xử lý khác
                    Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
            // Phương thức kiểm tra tính hợp lệ của thông tin đăng nhập
            private boolean isValidCredentials(String username, String password) {
                // Kiểm tra logic đăng nhập ở đây, ví dụ: kiểm tra username và password có rỗng không
                return !username.isEmpty() && !password.isEmpty();
            }
        });

        Button registerButton = findViewById(R.id.btn_reg);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistryActivity.class);
                startActivity(intent);
            }
        });
    }



}
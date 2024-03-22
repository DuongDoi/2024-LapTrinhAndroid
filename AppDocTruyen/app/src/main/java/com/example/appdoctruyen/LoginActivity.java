package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import com.example.appdoctruyen.database.databaseDoctruyen;

import java.sql.SQLException;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);

        btn_dangnhapClick();
        btn_dangkyClick();


    }




    private void btn_dangnhapClick() {
        // Nút đăng nhập
        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
    }
    private void handleLogin() {
        try {
            // Lấy dữ liệu từ EditText
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                if (isUserAdmin(username, password)) {
                    navigateToHomeActivity();
                    Toast.makeText(LoginActivity.this, "Welcome back boss ^.^", Toast.LENGTH_SHORT).show();
                } else if (loginUser(username, password)) {
                    navigateToHomeActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Login fail!!! User does not exist!!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Login fail!!! Please fill username and password", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(LoginActivity.this, "Login fail!!! An error occurred.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isUserAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private boolean loginUser(String username, String password) throws SQLException {
        try {

            databaseDoctruyen databaseHelper = new databaseDoctruyen(LoginActivity.this);
            return databaseHelper.checkUserLogin(username, password);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private void navigateToHomeActivity() {
        Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_comment.class);//Thay activity_comment = activity muốn chuyển đến khi đăng nhập thành công
        startActivity(intent);
    }

    private void btn_dangkyClick(){
        //Nút đăng ký
        Button registerButton = findViewById(R.id.btn_reg);
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistryActivity.class);
            startActivity(intent);
        });
    }



}

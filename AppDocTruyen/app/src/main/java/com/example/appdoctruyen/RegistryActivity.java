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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registry);
        Username = findViewById(R.id.edittext_username);
        Password = findViewById(R.id.edittext_password);
        rePassword = findViewById(R.id.edittext_repassword);
        btn_doneClick();
        btn_thoatClick();

    }
    private void btn_doneClick()
    {
        Button buttonRegister = findViewById(R.id.btn_reg);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                String repassword = rePassword.getText().toString();
                // Kiểm tra mật khẩu và mật khẩu nhập lại có khớp nhau hay không
                if (!password.equals(repassword)) {
                    // Nếu không khớp, hiển thị thông báo và không tiếp tục xử lý
                    Toast.makeText(RegistryActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
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

        if(databaseHelper.checkUserExist(username)){
            Toast.makeText(RegistryActivity.this, "User exist!!!", Toast.LENGTH_SHORT).show();
        }
        else{

            databaseHelper.addNewUser(username, password);

            Toast.makeText(RegistryActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegistryActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        databaseHelper.close();
    }

    private void btn_thoatClick()
    {
        Button btn_thoat;
        btn_thoat = findViewById(R.id.bntThoat);
        btn_thoat.setOnClickListener(v -> {
            Toast.makeText(RegistryActivity.this,"Exit",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistryActivity.this,LoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegistryActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}

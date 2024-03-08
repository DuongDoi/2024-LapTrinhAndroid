package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class RegistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registry);
        Button btn_done;
        btn_done = findViewById(R.id.btn_reg);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistryActivity.this,"Successful.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistryActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
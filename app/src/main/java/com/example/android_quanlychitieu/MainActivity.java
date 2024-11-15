package com.example.android_quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView resendTextView;
    Button editLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         resendTextView = findViewById(R.id.text_resend);
        resendTextView.setOnClickListener(v -> {
            // Khi bấm vào "Resend", chuyển sang trang đăng ký
            Intent intent = new Intent(MainActivity.this, Singup.class);
            startActivity(intent);
        });

        editLogin=findViewById(R.id.btn_login);
        editLogin.setOnClickListener(v -> {
            // Khi bấm vào "Resend", chuyển sang trang đăng ký
            Intent intent = new Intent(MainActivity.this, Singup.class);
            startActivity(intent);
        });

    }
}
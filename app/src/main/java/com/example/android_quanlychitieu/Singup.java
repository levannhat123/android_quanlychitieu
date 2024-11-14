package com.example.android_quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Singup extends AppCompatActivity {
    Button editsigup;
    TextView resendTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
         resendTextView = findViewById(R.id.text_resend1);
        resendTextView.setOnClickListener(v -> {
            finish();
        });

        editsigup=findViewById(R.id.btn_signup);
        editsigup.setOnClickListener(v -> {
            Intent intent = new Intent(Singup.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
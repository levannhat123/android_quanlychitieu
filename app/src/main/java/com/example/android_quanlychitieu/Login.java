package com.example.android_quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextView resendTextView;
    Button editLogin;
    EditText editName,editPass;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database=new Database(this);
        editName=findViewById(R.id.login_editname);
        editPass=findViewById(R.id.login_editpass);
         resendTextView = findViewById(R.id.text_resend);
        resendTextView.setOnClickListener(v -> {
            // Khi bấm vào "Resend", chuyển sang trang đăng ký
            Intent intent = new Intent(Login.this, Singup.class);
            startActivity(intent);
        });

        editLogin=findViewById(R.id.btn_login);
        editLogin.setOnClickListener(v -> {
            // Khi bấm vào "Resend", chuyển sang trang đăng ký
            String name = editName.getText().toString().trim();
            String pass = editPass.getText().toString().trim();
//            Intent intent = new Intent(Login.this, Singup.class);
//            startActivity(intent);
            boolean isloggetId=database.checUser(name,pass);
            if (!isValidName(name)) {
                Toast.makeText(Login.this, "Invalid name. Use 6 characters .", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidPassword(pass)) {
                Toast.makeText(Login.this, "Invalid password. Use 6 characters .", Toast.LENGTH_SHORT).show();
                return;
            }
            if(isloggetId){

                Toast.makeText(Login.this, "User Login Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, tablayout_khoanchi.class);
                intent.putExtra("name", name);
           startActivity(intent);
//                editName.setText("");
//                editPass.setText("");
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private boolean isValidPassword(String password) {
        return password.length() >= 6 ;

    }
    private boolean isValidName(String name) {
        return name.length() >= 6 ;

    }
}
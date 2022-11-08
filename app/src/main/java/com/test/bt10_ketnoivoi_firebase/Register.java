package com.test.bt10_ketnoivoi_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText edtemail, edtpass;
    private Button btn_signin;
    private TextView tvLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        edtemail = findViewById(R.id.email);
        edtpass = findViewById(R.id.password);
        btn_signin = findViewById(R.id.button_signin);
        tvLogin = findViewById(R.id.login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String email = edtemail.getText().toString();
        String pass = edtpass.getText().toString();
        if(email.isEmpty()||pass.isEmpty()){
            Toast.makeText(Register.this, "Vui lòng nhập đầy đủ thông tin!!!",Toast.LENGTH_SHORT).show();
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login() {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
}
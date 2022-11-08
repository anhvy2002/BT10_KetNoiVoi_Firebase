package com.test.bt10_ketnoivoi_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText edtemail, edtpass;
    private Button btbLogin;
    private TextView tvReister;
    private FirebaseAuth mAuth;
    CheckBox cbNhoMK;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        edtemail = findViewById(R.id.email);
        edtpass = findViewById(R.id.password);
        btbLogin = findViewById(R.id.button_signin);
        tvReister = findViewById(R.id.login);
        cbNhoMK = findViewById(R.id.checkBoxNhoMK);

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        edtemail.setText(sharedPreferences.getString("email",""));
        edtpass.setText(sharedPreferences.getString("password",""));
        cbNhoMK.setChecked(sharedPreferences.getBoolean("checked", false));

        btbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        tvReister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    private void login() {
        String email = edtemail.getText().toString();
        String pass = edtpass.getText().toString();
        if(email.isEmpty()||pass.isEmpty()){
            Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin!!!",Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    if (cbNhoMK.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putString("password", pass);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }
                    else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("email");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
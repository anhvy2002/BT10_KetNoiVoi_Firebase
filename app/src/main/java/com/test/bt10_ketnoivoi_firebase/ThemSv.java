package com.test.bt10_ketnoivoi_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.bt10_ketnoivoi_firebase.model.SinhVien;

public class ThemSv extends AppCompatActivity {
    private EditText edtMasv, edtTen, edtEmail, edtSdt;
    private Button btnThem, btnThoat, btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sv);
        
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtMasv.setText("");
                edtTen.setText("");
                edtEmail.setText("");
                edtSdt.setText("");
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masv = edtMasv.getText().toString();
                String ten = edtTen.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSdt.getText().toString();

                SinhVien sinhVien = new SinhVien(masv,ten,email,sdt);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DbSinhVien");

                String id = myRef.push().getKey();
                myRef.child(id).setValue(sinhVien).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Thêm thất bại "+e.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void addControl() {
        edtMasv = findViewById(R.id.edt_masv);
        edtTen = findViewById(R.id.edt_hoten);
        edtEmail = findViewById(R.id.edt_email);
        edtSdt = findViewById(R.id.edt_dienthoai);

        btnThem = findViewById(R.id.bt_them);
        btnThoat = findViewById(R.id.bt_thoat);
        btnHuy = findViewById(R.id.bt_huy);
    }
}
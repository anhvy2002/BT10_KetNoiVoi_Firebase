package com.test.bt10_ketnoivoi_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.bt10_ketnoivoi_firebase.model.SinhVien;

public class SuaSv extends AppCompatActivity {
    private EditText edtMasv, edtTen, edtEmail, edtSdt;
    private Button btnSua, btnThoat, btnHuy;
    private SinhVien sinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sv);
        
        addControl();
        addEvent();
    }

    private void addControl() {
        edtMasv = findViewById(R.id.edt_masv);
        edtTen = findViewById(R.id.edt_hoten);
        edtEmail = findViewById(R.id.edt_email);
        edtSdt = findViewById(R.id.edt_dienthoai);

        btnSua = findViewById(R.id.bt_sua);
        btnThoat = findViewById(R.id.bt_thoat);
        btnHuy = findViewById(R.id.bt_huy);

        Intent intent = getIntent();
        sinhVien = (SinhVien) intent.getSerializableExtra("SINHVIEN");
        if (sinhVien!=null){
            edtMasv.setText(sinhVien.getMasv());
            edtTen.setText(sinhVien.getHoTen());
            edtEmail.setText(sinhVien.getEmail());
            edtSdt.setText(sinhVien.getSDT());
        }else {
            Toast.makeText(this,"Lỗi load dữ liệu",Toast.LENGTH_LONG).show();
        }
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
                if (sinhVien!=null){
                    edtMasv.setText(sinhVien.getMasv());
                    edtTen.setText(sinhVien.getHoTen());
                    edtEmail.setText(sinhVien.getEmail());
                    edtSdt.setText(sinhVien.getSDT());
                }else {
                    Toast.makeText(getApplicationContext(),"Lỗi load dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masv = edtMasv.getText().toString();
                String ten = edtTen.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSdt.getText().toString();
                String id = sinhVien.getId();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DbSinhVien");

                myRef.child(id).child("email").setValue(email);
                myRef.child(id).child("hoTen").setValue(ten);
                myRef.child(id).child("masv").setValue(masv);
                myRef.child(id).child("sdt").setValue(sdt);
                finish();
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_LONG).show();
            }
        });
    }
}
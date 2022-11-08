package com.test.bt10_ketnoivoi_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.bt10_ketnoivoi_firebase.adapter.SinhVienAdapter;
import com.test.bt10_ketnoivoi_firebase.model.SinhVien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvSinhVien;
    private ArrayList<SinhVien> sinhVienArrayList;
    private SinhVienAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = findViewById(R.id.lvSinhVien);
        sinhVienArrayList = new ArrayList<>();
        GetData();

        adapter = new SinhVienAdapter(this,R.layout.custom_listview_item, sinhVienArrayList);
        lvSinhVien.setAdapter(adapter);
    }
    private void GetData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbSinhVien");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();

                for (DataSnapshot data : snapshot.getChildren()){
                    SinhVien sinhVien = data.getValue(SinhVien.class);
                        sinhVien.setId(data.getKey());
                        adapter.add(sinhVien);
                    Log.d("MYTAG","onDataChange: "+sinhVien);
                }
                Toast.makeText(getApplicationContext(),"Load thành công",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Load thất bại",Toast.LENGTH_LONG).show();
                Log.d("MYTAG","onCancelled: "+error.toString());
            }
        });
    }
}
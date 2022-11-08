package com.test.bt10_ketnoivoi_firebase.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.bt10_ketnoivoi_firebase.R;
import com.test.bt10_ketnoivoi_firebase.SuaSv;
import com.test.bt10_ketnoivoi_firebase.ThemSv;
import com.test.bt10_ketnoivoi_firebase.model.SinhVien;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    @NonNull
    private Activity activity;
    private int resource;
    @NonNull
    private List<SinhVien> objects;

    public SinhVienAdapter(@NonNull Activity activity, int resource, @NonNull List<SinhVien> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);

        TextView txtHoTen = view.findViewById(R.id.txtHoTen);
        TextView txtEmail = view.findViewById(R.id.txtEmail);

        SinhVien sinhVien = this.objects.get(position);
        txtHoTen.setText(sinhVien.getHoTen());
        txtEmail.setText(sinhVien.getEmail());

        ImageView btnMenu= view.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(activity,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.item_them_sv){
                            Intent intent = new Intent(activity, ThemSv.class);
                            activity.startActivity(intent);
                        }
                        if(menuItem.getItemId() == R.id.item_sua_sv){
                            Intent intent= new Intent(activity, SuaSv.class);
                            intent.putExtra("SINHVIEN",sinhVien);
                            activity.startActivity(intent);
                        }
                        if(menuItem.getItemId() == R.id.item_xoa_sv){
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("DbSinhVien");
                            myRef.child(sinhVien.getId()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Toast.makeText(activity,"Xóa thành công",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        return false;
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                try{
                    Field field = popupMenu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper = field.get(popupMenu);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[]{boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper, new Object[]{true});
                }catch (Exception e){
                    Log.d("MYTAG","onClick: "+e.toString());
                }
                popupMenu.show();
            }
        });
        return view;
    }
}

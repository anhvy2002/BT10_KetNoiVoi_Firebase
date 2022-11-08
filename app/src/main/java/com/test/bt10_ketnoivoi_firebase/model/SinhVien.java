package com.test.bt10_ketnoivoi_firebase.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String id;
    private String Masv;
    private String HoTen;
    private String Email;
    private String SDT;

    public SinhVien() {
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id='" + id + '\'' +
                ", Masv='" + Masv + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", Email='" + Email + '\'' +
                ", SDT='" + SDT + '\'' +
                '}';
    }

    public SinhVien(String masv, String hoTen, String email, String SDT) {
        Masv = masv;
        HoTen = hoTen;
        Email = email;
        this.SDT = SDT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasv() {
        return Masv;
    }

    public void setMasv(String masv) {
        Masv = masv;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}

package com.example.du_an_mau.model;

public class thanhvien {
    int matv;
    String hoTen;
    int namSinh;

    public thanhvien() {
    }

    public thanhvien(int matv, String hoTen, int namSinh) {
        this.matv = matv;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}

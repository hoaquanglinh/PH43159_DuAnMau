package com.example.du_an_mau.model;

public class thuthu {
    String maTT;
    String hoTenTT;
    String matKhau;
    public thuthu() {
    }

    public thuthu(String maTT, String hoTenTT, String matKhau) {
        this.maTT = maTT;
        this.hoTenTT = hoTenTT;
        this.matKhau = matKhau;
    }

    public thuthu(String hoTenTT, String matKhau) {
        this.hoTenTT = hoTenTT;
        this.matKhau = matKhau;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTenTT() {
        return hoTenTT;
    }

    public void setHoTenTT(String hoTenTT) {
        this.hoTenTT = hoTenTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}

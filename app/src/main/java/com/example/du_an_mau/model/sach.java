package com.example.du_an_mau.model;

public class sach {
    int masach;
    String tensach;
    Double giaThue;
    int maloai;
    String tenLoai;
    int soLuongDaMuon;

    public sach() {
    }


    public sach(int masach, String tensach, int soLuongDaMuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public sach(int masach, String tensach, Double giaThue, int maloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giaThue = giaThue;
        this.maloai = maloai;
    }

    public sach(int masach, String tensach, Double giaThue, int maloai, String tenLoai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giaThue = giaThue;
        this.maloai = maloai;
        this.tenLoai = tenLoai;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public Double getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(Double giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getSoLuongDaMuon() {
        return soLuongDaMuon;
    }

    public void setSoLuongDaMuon(int soLuongDaMuon) {
        this.soLuongDaMuon = soLuongDaMuon;
    }
}

package com.example.du_an_mau.model;

public class phieumuon {
    int mapm;
    String matt;
    int matv;
    String tentv;
    int masach;
    String tensach;
    Double tienthue;
    int trangthai;
    String ngaythue;

    public phieumuon() {
    }

    public phieumuon(int mapm, String matt, int matv, String tentv, int masach, String tensach, Double tienthue, int trangthai, String ngaythue) {
        this.mapm = mapm;
        this.matt = matt;
        this.matv = matv;
        this.tentv = tentv;
        this.masach = masach;
        this.tensach = tensach;
        this.tienthue = tienthue;
        this.trangthai = trangthai;
        this.ngaythue = ngaythue;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
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

    public Double getTienthue() {
        return tienthue;
    }

    public void setTienthue(Double tienthue) {
        this.tienthue = tienthue;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }
}

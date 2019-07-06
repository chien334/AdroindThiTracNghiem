package com.example.bancuoi.Model;

public class point_BoDe {
    private String tieude;
    private String ngaylam;
    private String thoigianlam;
    private double diem;

    public point_BoDe(String tieude, String ngaylam, String thoigianlam, double diem) {
        this.tieude = tieude;
        this.ngaylam = ngaylam;
        this.thoigianlam = thoigianlam;
        this.diem = diem;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNgaylam() {
        return ngaylam;
    }

    public void setNgaylam(String ngaylam) {
        this.ngaylam = ngaylam;
    }

    public String getThoigianlam() {
        return thoigianlam;
    }

    public void setThoigianlam(String thoigianlam) {
        this.thoigianlam = thoigianlam;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}

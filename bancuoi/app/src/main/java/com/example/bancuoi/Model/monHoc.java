package com.example.bancuoi.Model;

public class monHoc {
    private int id;
    private String tenMonHoc;
    private  String tenGV;

    public monHoc(int id, String tenMonHoc, String tenGV) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.tenGV = tenGV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }
}

package com.example.bancuoi.Model;

public class monHoc {
    private int id;
    private String tenMonHoc;

    public monHoc(int id, String tenMonHoc) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
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
}

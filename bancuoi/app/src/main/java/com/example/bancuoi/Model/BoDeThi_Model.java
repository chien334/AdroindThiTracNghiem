package com.example.bancuoi.Model;

public class BoDeThi_Model {
    private  int Made;
    private  String TenDeThi;
    private  int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public BoDeThi_Model(int made, String tenDeThi, int time) {
        Made = made;
        TenDeThi = tenDeThi;
        this.time = time;
    }

    public int getMade() {
        return Made;
    }

    public void setMade(int made) {
        Made = made;
    }

    public String getTenDeThi() {
        return TenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        TenDeThi = tenDeThi;
    }
}

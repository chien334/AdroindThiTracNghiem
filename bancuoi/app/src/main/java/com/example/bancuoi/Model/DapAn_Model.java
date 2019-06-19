package com.example.bancuoi.Model;

public class DapAn_Model {
    private  int made;
    private  int[] dapan;

    public int getMade() {
        return made;
    }

    public void setMade(int made) {
        this.made = made;
    }

    public int[] getDapan() {
        return dapan;
    }

    public void setDapan(int[] dapan) {
        this.dapan = dapan;
    }

    public DapAn_Model(int made, int[] dapan) {
        this.made = made;
        this.dapan = dapan;
    }
}

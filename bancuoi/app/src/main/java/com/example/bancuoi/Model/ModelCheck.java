package com.example.bancuoi.Model;

public class ModelCheck {
    private int CauHoi;
    private int DapAn;

    public ModelCheck(int cauHoi, int dapAn) {
        CauHoi = cauHoi;
        DapAn = dapAn;
    }

    public int getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(int cauHoi) {
        CauHoi = cauHoi;
    }

    public int getDapAn() {
        return DapAn;
    }

    public void setDapAn(int dapAn) {
        DapAn = dapAn;
    }
}

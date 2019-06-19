package com.example.bancuoi.Model;

public class CT_BAITHI {
    private int MA_BT;
    private int MA_CH;
    private int MA_LC;

    public CT_BAITHI(int MA_BT, int MA_CH, int MA_LC) {
        this.MA_BT = MA_BT;
        this.MA_CH = MA_CH;
        this.MA_LC = MA_LC;
    }

    public int getMA_BT() {
        return MA_BT;
    }

    public void setMA_BT(int MA_BT) {
        this.MA_BT = MA_BT;
    }

    public int getMA_CH() {
        return MA_CH;
    }

    public void setMA_CH(int MA_CH) {
        this.MA_CH = MA_CH;
    }

    public int getMA_LC() {
        return MA_LC;
    }

    public void setMA_LC(int MA_LC) {
        this.MA_LC = MA_LC;
    }
}

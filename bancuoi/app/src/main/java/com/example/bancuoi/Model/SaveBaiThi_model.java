package com.example.bancuoi.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SaveBaiThi_model {
    private int MA_HS;
    private int MA_DT;
    private Date NGAY_THI = new Date("yyyy/MM/dd");
    private Date THOI_GIAN_LAM = new Date("HH:mm:ss");

    public SaveBaiThi_model(int MA_HS, int MA_DT, Date NGAY_THI, Date THOI_GIAN_LAM) {
        this.MA_HS = MA_HS;
        this.MA_DT = MA_DT;
        this.NGAY_THI = NGAY_THI;
        this.THOI_GIAN_LAM = THOI_GIAN_LAM;
    }

    public int getMA_HS() {
        return MA_HS;
    }

    public void setMA_HS(int MA_HS) {
        this.MA_HS = MA_HS;
    }

    public int getMA_DT() {
        return MA_DT;
    }

    public void setMA_DT(int MA_DT) {
        this.MA_DT = MA_DT;
    }

    public Date getNGAY_THI() {
        return NGAY_THI;
    }

    public void setNGAY_THI(Date NGAY_THI) {
        this.NGAY_THI = NGAY_THI;
    }

    public Date getTHOI_GIAN_LAM() {
        return THOI_GIAN_LAM;
    }

    public void setTHOI_GIAN_LAM(Date THOI_GIAN_LAM) {
        this.THOI_GIAN_LAM = THOI_GIAN_LAM;
    }
}

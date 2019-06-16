package com.example.bancuoi.Model;

public class LuaChon_Model {
    private int MA_CH;
    private int MA_LC;
    private String NOI_DUNG_LC;
    private boolean LA_DAP_AN;

    public LuaChon_Model(int MA_CH, int MA_LC, String NOI_DUNG_LC, boolean LA_DAP_AN) {
        this.MA_CH = MA_CH;
        this.MA_LC = MA_LC;
        this.NOI_DUNG_LC = NOI_DUNG_LC;
        this.LA_DAP_AN = LA_DAP_AN;
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

    public String getNOI_DUNG_LC() {
        return NOI_DUNG_LC;
    }

    public void setNOI_DUNG_LC(String NOI_DUNG_LC) {
        this.NOI_DUNG_LC = NOI_DUNG_LC;
    }

    public boolean isLA_DAP_AN() {
        return LA_DAP_AN;
    }

    public void setLA_DAP_AN(boolean LA_DAP_AN) {
        this.LA_DAP_AN = LA_DAP_AN;
    }
}

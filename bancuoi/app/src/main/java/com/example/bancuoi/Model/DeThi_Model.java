package com.example.bancuoi.Model;

import java.util.List;

public class DeThi_Model {
    private int MA_DT;
    private int MA_CH;
    private String NOI_DUNG_CH;
    private List<LuaChon_Model> luachon;

    public DeThi_Model(int MA_DT, int MA_CH, String NOI_DUNG_CH, List<LuaChon_Model> luachon) {
        this.MA_DT = MA_DT;
        this.MA_CH = MA_CH;
        this.NOI_DUNG_CH = NOI_DUNG_CH;
        this.luachon = luachon;
    }

    public int getMA_DT() {
        return MA_DT;
    }

    public void setMA_DT(int MA_DT) {
        this.MA_DT = MA_DT;
    }

    public int getMA_CH() {
        return MA_CH;
    }

    public void setMA_CH(int MA_CH) {
        this.MA_CH = MA_CH;
    }

    public String getNOI_DUNG_CH() {
        return NOI_DUNG_CH;
    }

    public void setNOI_DUNG_CH(String NOI_DUNG_CH) {
        this.NOI_DUNG_CH = NOI_DUNG_CH;
    }

    public List<LuaChon_Model> getLuachon() {
        return luachon;
    }

    public void setLuachon(List<LuaChon_Model> luachon) {
        this.luachon = luachon;
    }
}

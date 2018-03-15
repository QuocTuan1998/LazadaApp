package com.example.quoctuan.lazadaapp.model.object;

import java.util.List;

/**
 * Created by quoctuan on 09/03/2018.
 */

public class LoaiSanPham {

    private int MALOAISP, MALOAICHA;
    private String TENLOAISP;
    private List<LoaiSanPham> list_child;

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMALOAICHA() {
        return MALOAICHA;
    }

    public void setMALOAICHA(int MALOAICHA) {
        this.MALOAICHA = MALOAICHA;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public List<LoaiSanPham> getList_child() {
        return list_child;
    }

    public void setList_child(List<LoaiSanPham> list_child) {
        this.list_child = list_child;
    }
}

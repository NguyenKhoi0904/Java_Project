package com.bt.quanlythuchicanhan;

import java.io.Serializable;
import java.time.LocalDate;


public class GioiHanGiaoDich implements Serializable{
    private int solangiaodich;
    private int ThangHienTai;
    public GioiHanGiaoDich(int solangiaodich,int thang){//free
        this.solangiaodich=solangiaodich;
        this.ThangHienTai= thang;

    }

    public int getsolangiaodich() {
        return solangiaodich;
    }

    public void setSolangiaodich(int solangiaodich) {
        this.solangiaodich = solangiaodich;

    }

    public int getThang() {
        return ThangHienTai;
    }

    public void setThang(int ThangHienTai) {
       this.ThangHienTai = ThangHienTai;

    }
    public void kiemTraThayDoiThang(int thangmoi){// Dùng để tạo gd cho free user
        if(getThang()!=thangmoi){
            this.setThang(thangmoi);
            this.setSolangiaodich(5);
        }
    }

}

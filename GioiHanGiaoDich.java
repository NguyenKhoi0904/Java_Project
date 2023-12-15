package com.bt.quanlythuchicanhan;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;

public class GioiHanGiaoDich implements Serializable{
    private int solangiaodich;
    private int ThangHienTai;
    public GioiHanGiaoDich(int solangiaodich){//free
        this.solangiaodich=solangiaodich;
        this.ThangHienTai= LocalDate.now().getMonthValue();

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
    public void kiemTraThayDoiThang(){// Dùng để tạo gd cho free user
        int Thang=LocalDate.now().getMonthValue();
        if(getThang()!=Thang){
            this.setThang(Thang);
            solangiaodich=50;
        }
    }

}

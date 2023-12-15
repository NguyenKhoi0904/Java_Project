package com.bt.quanlythuchicanhan;

import java.time.LocalDate;
import java.time.YearMonth;

public class GioiHanGiaoDich {
    private int solangiaodich;
    private int ThangHienTai;
    public GioiHanGiaoDich(int solangiaodich){//free
        this.solangiaodich=solangiaodich;
        this.ThangHienTai= LocalDate.now().getMonthValue();

    }
    public GioiHanGiaoDich(){//Pro
        this.solangiaodich= Integer.MAX_VALUE;
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

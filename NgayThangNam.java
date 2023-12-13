/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.Serializable;

public class NgayThangNam implements Serializable{
    private int ngay;
    private int thang;
    private int nam;

    public NgayThangNam(int ngay, int thang, int nam) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public int getngay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;

    }

    public int getthang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;

    }

    public int getnam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;

    }
    public String toStringdate(){
        return this.getngay()+"-"+this.getthang()+"-"+this.getnam();
    }

}

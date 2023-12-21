package com.bt.quanlythuchicanhan;

import java.io.Serializable;
import java.util.ArrayList;

public class NganSach implements Serializable {
    private int thang;
    private int nam;
    private int sotien;
    private double phantramcanhbao;
    private boolean BatTat;

    private DanhMuc danhmucchi;

    public DanhMuc danhmucchi() {
        return danhmucchi;
    }

    public NganSach setDanhmucchi(DanhMuc danhmucchi) {
        this.danhmucchi = danhmucchi;
        return this;
    }

    public NganSach(){
    }
    public NganSach(int thang, int nam, int sotien,double phantramcanhbao,DanhMuc danhmucchi) {
        this.thang = thang;
        this.nam = nam;
        this.sotien = sotien;
        this.phantramcanhbao=phantramcanhbao;
        this.danhmucchi=danhmucchi;
        BatTat=true;
    }
    public NganSach(DanhMuc danhmucchi){
        this.danhmucchi=danhmucchi;
    }

    public int getthang() {
        return this.thang;
    }

    public int getnam() {
        return this.nam;
    }

    public void setNam(int nam) {
        this.nam = nam;

    }
    public Boolean Kiemtrangansachcoduoctaochua(int year,int month,DanhMuc danhmuccankiemtra){
        if(nam==year && thang==month && danhmucchi==danhmuccankiemtra){
            return true;
        }
        else{
            return false;
        }
    }
    public int getsotien() {
        return sotien;
    }
    public void setPhanTram(double Phantram){
        if(Phantram<0 || Phantram>100) {
            System.out.println("phần trăm không hợp lệ");
        }
        else this.phantramcanhbao = phantramcanhbao;
    }
    public double getPhanTram(){
        return phantramcanhbao;
    }
    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public void setThang(int thang) {
        this.thang = thang;

    }
    public void batTat(boolean flag){
        this.BatTat=flag;
    }
    public boolean getbatTat(){
        return  this.BatTat;
    }
    public void setPhanTramSoTien(double phantram){
        if(phantram<0 || phantram >100){
            System.out.println("Số phần trăm bạn nhập không hợp lệ");
        }
    }
    public double phantramsudung(int tiengd,int sotiencuathangHienTai){
        int tienhientai = tiengd+sotiencuathangHienTai;
        return 1.0*tienhientai/getsotien();
    }
    public void hienThiBieuDoNganSach(int tienCuaThangHienTai){
        System.out.println("Ta có tiền đã xài tháng này là "+tienCuaThangHienTai);
        double percent = 1.0*tienCuaThangHienTai*100/getsotien();
        System.out.println("Ta có dữ liệu thống kê như sau");
        if(percent<100){
            for(int i=1;i<(100-percent)/4;i++){
                System.out.println("                                            **");
            }
            for(int x=1;x<(percent/4);x++){
                System.out.println("**                                          **");
            }
                System.out.println("used                                        money-condition");
                System.out.println("Tiền đã sử dụng Chiếm "+percent+"%            Tiền quy định tháng này");
        }
        else if(percent>100){
            for(int i=1;i<((percent-100)/4);i++){
                System.out.println("**                                           ");
            }
            for(int x=1;x<(percent/4);x++){
                System.out.println("**                                         **");
            }
            System.out.println("used                                 money-condition");
            System.out.println("Tiền đã sử dụng Chiếm "+percent+"%          Tiền quy định tháng này");

        }
        else if(percent==100){
            for(int i=1;i<100/4;i++){
                System.out.println("**                                         **");
            }
            System.out.println("used                                 money-condition");
            System.out.println("Tiền đã sử dụng Chiếm "+percent+"%          Tiền quy định tháng này");
        }
        System.out.println("Trong đó");
        System.out.println("used:Tiền đã sử dụng");
        System.out.println("money-condition:Tiền đã sử dụng");

    }


}

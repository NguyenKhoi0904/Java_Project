package com.bt.quanlythuchicanhan;

public class gioiHanNganSach {
    private int thang;
    private int nam;
    private int sotien;
    private double PhanTramSoTien;
    public gioiHanNganSach(){
    }
    public gioiHanNganSach(int thang, int nam, int sotien,double PhanTramSoTien) {
        this.thang = thang;
        this.nam = nam;
        this.sotien = sotien;
        this.PhanTramSoTien=PhanTramSoTien;
    }

    public int getthang() {
        return this.thang;
    }

    public int getnam() {
        return this.nam;
    }

    public gioiHanNganSach setNam(int nam) {
        this.nam = nam;
        return this;
    }

    public int sotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public void setThang(int thang) {
        this.thang = thang;

    }
    public void setPhanTramSoTien(double phantram){
        if(phantram<0 || phantram >100){
            System.out.println("Số phần trăm bạn nhập không hợp lệ");
        }
    }
    public double getPhanTramSoTien(){

        return PhanTramSoTien;
    }
    public void kiemtraNganSach(int tiengd,int sotienHienTai){
        int tienneugiaodich = tiengd+sotienHienTai;
        double GioiHantheophantram=sotien*PhanTramSoTien*0.1;
        if(tienneugiaodich> GioiHantheophantram){
            System.out.println("Số tiền bạn sử dụng lớn hơn "+(tienneugiaodich-GioiHantheophantram));
        }
    }

}

package com.bt.quanlythuchicanhan;

public class SoDanhMucMoiThang {
    private int soDanhMuc;
    private int Thang;
    private int Nam;

    public SoDanhMucMoiThang(int soDanhMuc, int thang, int nam) {
        this.soDanhMuc = soDanhMuc;
        Thang = thang;
        Nam = nam;
    }

    public int getSoDanhMuc() {
        return soDanhMuc;
    }

    public void setSoDanhMuc(int soDanhMuc) {
        this.soDanhMuc = soDanhMuc;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int thang) {
        this.Thang = thang;

    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int nam) {
        Nam = nam;

    }
}

package com.bt.quanlythuchicanhan;

import java.io.Serializable;

public class NganSach  implements Serializable {
    private int Thang;
    private int Nam;

    private int SoTienChiQuyDinh;

    public int getThang() {
        return Thang;
    }

    public void setThang(int thang) {
        Thang = thang;

    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int nam) {
        Nam = nam;

    }

    public int getSoTienChiQuyDinh() {
        return SoTienChiQuyDinh;
    }

    public void setSoTienChiQuyDinh(int soTienChiQuyDinh) {
        SoTienChiQuyDinh = soTienChiQuyDinh;

    }
}

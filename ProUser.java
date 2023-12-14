/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author aries
 */
public class ProUser extends User implements Serializable, InterfaceClass.QuanLyGiaoDichDanhMuc_Interface, InterfaceClass.ManageCategory{
    int gioiHanNganSach;
   /*  private ArrayList<NganSach>;*/

    public ProUser() {
    }

    public ProUser(int gioiHanNganSach) {
        this.gioiHanNganSach = gioiHanNganSach;
    }

    public int getGioiHanNganSach() {
        return gioiHanNganSach;
    }

    public void setGioiHanNganSach(int gioiHanNganSach) {
        this.gioiHanNganSach = gioiHanNganSach;
    }
    
    public void DoiSanhCacKhoanThuCHiTheoThoiGianCuaCacThangOrNam(){
        
    }
    
    public void xuatBaoCaoRaFile(){
        
    }


    @Override
    public void tao1GiaoDichMoi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void thongkeDanhMucTheoNgayThangNam() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void timKiemThongTinGiaoDich() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void taoDanhMuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void doiTenDanhMuc() {
        this.getQldm().chonDanhMucDeSua();
    }

    @Override
    public void xoaDanhMuc() {
        this.getQldm().ChonDanhMucdexoa();
    }

    @Override
    public void BaoCaoChiTietTheoTungDanhMucVaThoiGian() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

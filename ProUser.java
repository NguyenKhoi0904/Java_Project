/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public void xuatBaoCaoRaFile(String s){
        ghiBaoCaoLenFile(s);
    }
    
    private void ghiBaoCaoLenFile(String s){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("BaoCaoChiTiet.txt"))){
            bw.write(s);
            System.out.println("Xuất báo cáo thành công");
        }catch(IOException e){
            System.out.println("Xuất báo cáo thất bại");
            e.printStackTrace();
        }
    }
    
    @Override
    public void tao1GiaoDichMoi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void thongkeDanhMucTheoNgayThangNam() {
        //this.getQldm().
    }

    @Override
    public void timKiemThongTinGiaoDich() {
        this.getQldm().timkiemthongtingiaodich();
    }

    @Override
    public void taoDanhMuc() {
        this.getQldm().ChonDanhMucDeThem();
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
    /* public Boolean LapNganSachVaNhacNho(){// viet them regrex de kiem tra;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập Năm ");
        String test1  = sc.nextLine();
        int nam;
        while(QuanLyDanhMuc.isInteger(test1)){
            nam = Integer.parseInt(test1);
        }
        System.out.println("Nhập Tháng ");
        String test2  = sc.nextLine();
        int thang;
        while(QuanLyDanhMuc.isInteger(test2)){
            thang = Integer.parseInt(test1);
        }
       for( GiaoDich gd:getQldm().getDsgiaodich().getDsGD()){
           if(gd.getNgayGiaoDich().getthang()==thang )
       }


    }*/
}

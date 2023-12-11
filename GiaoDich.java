/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;
import java.util.Random;
import java.util.Scanner;

public class GiaoDich {
    private String idGiaodich;
    private NgayThangNam ngayGiaoDich;
    private String loaigiaodich;
    private String thongtingiaodich;
    private long sotien;
    private DanhMuc tendanhmuc;
    private static int soLanGiaoDich = 0;
    Scanner sc = new Scanner(System.in);
    public void ChonLoaiGiaoDich(int n){
        switch(n){
            case 1:
                this.loaigiaodich = "Giao dịch chi";
                this.idGiaodich=generateID_GiaoDich("CH");
                break;
            case 2:
                this.loaigiaodich = "Giao dịch thu";
                this.idGiaodich=generateID_GiaoDich("TH");
                break;
            default:
                System.out.println("Lựa chọn không phù hợp");
        }
    }
    
    private String generateID_GiaoDich(String prefix){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 9));
        
        String formatNumber = String.format("%0" + 9 + "d", randomNumber);
        return prefix + formatNumber;
    }
    
    public GiaoDich( NgayThangNam ngayGiaoDich, String thongtingiaodich,long sotien, DanhMuc tendanhmuc,int loaigd){

        this.ngayGiaoDich = ngayGiaoDich;
        ChonLoaiGiaoDich(loaigd);
        this.thongtingiaodich = thongtingiaodich;
        this.sotien=sotien;
        this.tendanhmuc = tendanhmuc;
        this.tendanhmuc=tendanhmuc;
        soLanGiaoDich++;
    }
    public void setInFoGiaoDich(DanhMuc danhmuc,int loaigd){

        this.idGiaodich = sc.nextLine();
        System.out.println("Nhập ngày");
        int ngay=sc.nextInt();
        System.out.println("Nhập tháng");
        int thang = sc.nextInt();
        System.out.println("Nhập năm");
        int nam=sc.nextInt();
        this.ngayGiaoDich = new NgayThangNam(ngay,thang,nam);
        ChonLoaiGiaoDich(loaigd);
        this.thongtingiaodich=sc.nextLine();
        System.out.println("Nhập số tiền");
        this.sotien=sc.nextLong();
        System.out.println("nhập tên danh muc");
        this.tendanhmuc=danhmuc;
    }

    public String getidGiaodich() {
        return idGiaodich;
    }

    public void setIdGiaodich(String idGiaodich) {
        this.idGiaodich = idGiaodich;

    }

    public NgayThangNam getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(NgayThangNam ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;

    }

    public String getLoaigiaodich() {
        return loaigiaodich;
    }

    public void setLoaigiaodich(String loaigiaodich) {
        this.loaigiaodich = loaigiaodich;

    }

    public String getThongtingiaodich() {
        return thongtingiaodich;
    }

    public void setThongtingiaodich(String thongtingiaodich) {
        this.thongtingiaodich = thongtingiaodich;

    }

    public DanhMuc getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(DanhMuc tendanhmuc) {
        this.tendanhmuc = tendanhmuc;

    }

    public static int getsoLanGiaoDich() {
        return soLanGiaoDich;
    }

    public long getsotien() {
        return sotien;
    }

    public void setSotien(long sotien) {
        this.sotien = sotien;

    }

    public static void setSoLanGiaoDich(int soLanGiaoDich) {
        GiaoDich.soLanGiaoDich = soLanGiaoDich;
    }

    public void Chuyentienvaodanhmuc(){
        this.getTendanhmuc().setMoney((int) ((getTendanhmuc().getMoney())+this.getsotien()));
    }
    public String toStringGiaoDich(){
        return "|ID giao dịch: " +this.getidGiaodich()+", DATE: "+this.getNgayGiaoDich().toStringdate()+", THÔNG TIN GIAO DỊCH "+this.getThongtingiaodich()+":, SỐ TIỀN: "+this.getsotien()+"đ ,TÊN DANH MỤC: "+tendanhmuc.gettendanhmuc() +", TYPE : "+this.getLoaigiaodich()+"|";
    }
    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author aries
 */
public class FreeUser extends User implements Serializable, InterfaceClass.QuanLyGiaoDichDanhMuc_Interface, InterfaceClass.ManageCategory{

    public void nangCapTaiKhoan() throws IOException{
        ProUser proUser = nangcap();
        ArrayList<User> arraylist = docUserData();
        int dem = 0;
        for(User u: arraylist){
            if(u.getIdUser().equals(this.getIdUser())){
                break;
            }
            dem++;
        }
        arraylist.set(dem, proUser);
        ghiUserMoiNangCapLenFile(arraylist);
    }
    
    private ProUser nangcap(){
        ProUser pu = new ProUser();
        pu.setLoaitaiKhoan("PRO");
        pu.setIdUser(generateID_User(pu.getLoaitaiKhoan()));
        pu.setQldm(this.getQldm());
        pu.getQldm().setSoDanhMuc(100);
        pu.setTaiKhoanNguoiDung(this.getTaiKhoanNguoiDung());
        return pu;
    }
    private void ghiUserMoiNangCapLenFile(ArrayList<User> a) throws FileNotFoundException, IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userData.txt"))){
            oos.writeObject(a);
            System.out.println("NÂNG CẤP TÀI KHOẢN THÀNH CÔNG");
            System.out.println("Mời bạn đăng nhập lại");
        }catch(IOException e){
            System.out.println("NÂNG CẤP TÀI KHOẢN THẤT BẠI");
            e.printStackTrace();
        }
    }
    private ArrayList<User> docUserData(){
        ArrayList<User> a = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userData.txt"))) {
            a = (ArrayList<User>)ois.readObject();
        } catch (Exception e) {
        }
        return a;
    }
    private String generateID_User(String prefix){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 6));
        
        String formatNumber = String.format("%0" + 6 + "d", randomNumber);
        return prefix + formatNumber;
    }

    @Override
    public void tao1GiaoDichMoi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void thongkeDanhMucTheoNgayThangNam() {
        //this.getQldm().t
    }

    @Override
    public void timKiemThongTinGiaoDich() {
        
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
}

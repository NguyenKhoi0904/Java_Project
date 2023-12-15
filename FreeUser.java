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
//    private GioiHanGiaoDich solangiaodich;
    public void nangCapTaiKhoan() throws IOException{
        ProUser proUser = nangcap();
        ArrayList<User> arraylist = docUserData();
        System.out.println("Bạn phải trả 100USD để nâng cấp tài khoản thành Pro User (Nâng cấp 1 lần, sử dụng mãi mãi)");
        System.out.println("Bạn sẽ được sử dụng thêm các chức năng đặc quyền của Pro User và vô vàn những ưu đãi đặc biệt");
        System.out.println("Bạn có đồng ý nâng cấp không?");
        System.out.println("0. Không đồng ý");
        System.out.println("1. Đồng ý");
        int dem = 0;
        int luachon = nhap();
        if(luachon == 1){
            for(User u: arraylist){
                if(u.getIdUser().equals(this.getIdUser())){
                    break;
                }
                dem++;
            }
            arraylist.set(dem, proUser);
            ghiUserMoiNangCapLenFile(arraylist);
        }
    }
    
    private int nhap(){
        Scanner scanner = new Scanner(System.in);
        String s;
        do{
            System.out.print("Mời bạn lựa chọn: ");
            s = scanner.nextLine();
            switch(s){
                case "1" -> {
                    return 1;
                }
                case "0" -> {
                    return 0;
                }
                default -> {
                    System.out.println("Bạn nhập sai rồi");
                }
            }
        }while(true);
                
    }
    private ProUser nangcap(){
        ProUser pu = new ProUser();
        pu.setLoaitaiKhoan("PRO");
        pu.setIdUser(generateID_User(pu.getLoaitaiKhoan()));
        pu.setQldm(this.getQldm());
        pu.getQldm().setSoDanhMuc(10);
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
        this.getQldm().ThongKe();
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
        this.getQldm().BaoCaoChiTietTungDanhMuc() ;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

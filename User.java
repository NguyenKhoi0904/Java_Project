/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author aries
 */
public class User implements Serializable {
    private AccountUser TaiKhoanNguoiDung;
    private String loaitaiKhoan;
    private QuanLyDanhMuc qldm;
    private String idUser;
    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(AccountUser TaiKhoanNguoiDung, String loaitaiKhoan, QuanLyDanhMuc qldm, String idUser) {
        this.TaiKhoanNguoiDung = TaiKhoanNguoiDung;
        this.loaitaiKhoan = loaitaiKhoan;
        this.qldm = qldm;
        this.idUser = idUser;
    }

    public QuanLyDanhMuc getQldm() {
        return qldm;
    }

    public void setQldm(QuanLyDanhMuc qldm) {
        this.qldm = qldm;
    }

    public AccountUser getTaiKhoanNguoiDung() {
        return TaiKhoanNguoiDung;
    }

    public void setTaiKhoanNguoiDung(AccountUser TaiKhoanNguoiDung) {
        this.TaiKhoanNguoiDung = TaiKhoanNguoiDung;
    }

    public String getLoaitaiKhoan() {
        return loaitaiKhoan;
    }

    public void setLoaitaiKhoan(String loaitaiKhoan) {
        this.loaitaiKhoan = loaitaiKhoan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
    public void DangNhap() throws ClassNotFoundException {
        ArrayList<AccountUser> tmpDN;
        int flag = 0;
        try (Scanner input_User = new Scanner(System.in)) {
            System.out.println("  ^^^  DANG NHAP  ^^^  ");
            System.out.print("Tai khoan: ");
            String username = input_User.nextLine();
            System.out.print("Mat khau: ");
            String password = input_User.nextLine();
            tmpDN = readAccountUserDateFromFileAndCheck();
            for(AccountUser au: tmpDN){
                if(au.getTendangnhap().equals(username)){
                    flag = 0;
                    if(au.getMatkhau().equals(password)){
                        System.out.println("Dang nhap thanh cong");
                        return;
                    }
                    else{
                        int count = 0;
                        do{    
                            System.out.println("&&&&&&&&&&");
                            System.out.println("Sai mật khẩu");
                            System.out.print("Moi ban nhap lai mat khau: ");
                            password = input_User.nextLine();
                            count++;
                            if(au.getMatkhau().equals(password)){
                                System.out.println("Dang nhap thanh cong");
                                return;
                            }
                            if(count == 3){
                                return;
                            }
                        }while(true);
                    }
                }
                flag = 1;
            }
            switch(flag){
                case 1 -> {
                    System.out.println("Sai tài khoản hoặc tài khoản không tồn tại");
                    return;
                }
                default -> {
                    return;
                }
            }
        }
    }

    private ArrayList<AccountUser> readAccountUserDateFromFileAndCheck() throws ClassNotFoundException {
        ArrayList<AccountUser> tmp = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountUser.txt"))) {
            tmp = (ArrayList<AccountUser>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public void DangKy() throws FileNotFoundException, ClassNotFoundException {
        Scanner input_User = new Scanner(System.in);
        
        AccountUser a = new AccountUser();
        a.setInfor_AccountUser();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountUser.txt"))) {
            ArrayList<AccountUser> arrayListAU = (ArrayList<AccountUser>) ois.readObject();
            for(AccountUser tmp: arrayListAU){
                if(tmp.getTendangnhap().equals(a.getTendangnhap())){
                    System.out.println("Tai khoan da ton tai");
                    do{
                        System.out.println("Mời bạn nhập lại tài khoản");
                        a.setTendangnhap(input_User.nextLine());
                        if(tmp.getTendangnhap().equals(a.getTendangnhap())){
                            System.out.println("Tài khoản hợp lệ");
                            break;
                        }else{
                            System.out.println("Tai khoan da ton tai");
                        }
                    }while(true);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setTaiKhoanNguoiDung(a);
        
        System.out.println("   ^___^   ");
        System.out.println("Chon loai tai khoan ma ban muon dang ky");
        System.out.println("0.Free User");
        System.out.println("1.Pro User");
        
        int temporary = Integer.parseInt(input_User.nextLine());
        if (temporary == 0) {
            this.setLoaitaiKhoan("FREE");
            this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
            if (writeAccountUserDateToFile(a)) {
                System.out.println("Dang ky thanh cong");
            } else {
                System.out.println("Dang ky that bai");
            }
        } else {
            System.out.println("Ban phai tra 100USD de tro thanh Pro User!!!!");
            System.out.println("Ban co dong y khong?");
            System.out.println("Nhap 0 la khong dong y va ban se tro thanh Free User");
            System.out.println("Nhap 1 la dong y");
            System.out.print("Ban chon di: ");
            int tmp = Integer.parseInt(input_User.nextLine());
            switch (tmp) {
                case 1 -> {
                    System.out.println("Xac nhan thanh toan");
                    this.setLoaitaiKhoan("PRO");
                    this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
                    if (writeAccountUserDateToFile(a)) {
                        System.out.println("Dang ky thanh cong");
                    } else {
                        System.out.println("Dang ky that bai");
                    }
                }
                case 0 -> {
                    this.setLoaitaiKhoan("FREE");
                    this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
                    if (writeAccountUserDateToFile(a)) {
                        System.out.println("Dang ky thanh cong");
                        
                    } else {
                        System.out.println("Dang ky that bai");
                    }
                }
            }
        }
    }
    private String generateID_User(String prefix){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 6));
        
        String formatNumber = String.format("%0" + 6 + "d", randomNumber);
        return prefix + formatNumber;
    }
    private boolean writeAccountUserDateToFile(AccountUser au) throws FileNotFoundException, ClassNotFoundException {
        ArrayList<AccountUser> auTmp = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountUser.txt"))){
            auTmp = (ArrayList<AccountUser>) ois.readObject();
            ois.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        auTmp.add(au);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accountUser.txt"))) { 
            oos.writeObject(auTmp);
            System.out.println("Ghi vao file thanh cong");
            return true;
        } catch (IOException e) {
            System.out.println("GHI THONG TIN USER VAO FILE THAT BAI " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) throws ClassNotFoundException,FileNotFoundException {
        User u = new User();
        u.DangKy();
//        User u1 = new User();
//        u1.DangKy();       
//        User u2 = new User();
//        u2.DangKy();
//        
//        User u3 = new User();
//        u3.DangKy();
        
//        u.DangNhap();
//        u1.DangNhap();

 
    }
}

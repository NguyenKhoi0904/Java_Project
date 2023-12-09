/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.EOFException;
import java.io.File;
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
    
    public User DangNhap() throws ClassNotFoundException {
        ArrayList<AccountUser> tmpDN = readAccountUserDataFromFileAndCheck();
        ArrayList<User> pxm = docUserData();
        boolean isAccountValid = false;
        try (Scanner input_User = new Scanner(System.in)) {
            System.out.println("  ^^^  DANG NHAP  ^^^  ");
            System.out.print("Tai khoan: ");
            String username = input_User.nextLine();
            System.out.print("Mat khau: ");
            String password = input_User.nextLine();
            int index = 0;
            for(AccountUser au: tmpDN){
                if(au.getTendangnhap().equals(username)&&au.getMatkhau().equals(password)){
                    System.out.println("Dang nhap thanh cong");
                    return pxm.get(index);
                }
                if(au.getTendangnhap().equals(username)){
                    isAccountValid = true;
                    break;
                }
                index++;
            }
            index = 0;
            if(isAccountValid){
                int count;
                boolean flag = true;
                System.out.println("&&&&&& 404 &&&&&&");
                System.out.println("Sai mật khẩu");
                do{
                    System.out.println("Mời bạn nhập lại mật khẩu: ");
                    password = input_User.nextLine();
                    for(AccountUser au : tmpDN){
                        if(au.getMatkhau().equals(password)){
                            System.out.println("Dang nhap thanh cong");
                            return pxm.get(index);
                        }else{
                            flag = false;
                        }
                    }
                    if(!flag)   System.out.println("Sai mật khẩu");
                    System.out.println("Bạn có muốn thoát ra không?");
                    System.out.println("0.Không");
                    System.out.println("1.Có");
                    System.out.print("Mời bạn lựa chọn: ");
                    count = Integer.parseInt(input_User.nextLine());
                    if(count == 1){
                        return null;
                    }
                }while(true);
            }else{
                System.out.println("Sai tài khoản hoặc tài khoản không tồn tại");
            }
            input_User.close();
        }
        return null;
    }

    private ArrayList<User> docUserData(){
        ArrayList<User> a = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userData.txt"))) {
            a = (ArrayList<User>)ois.readObject();
        } catch (Exception e) {
        }
        return a;
    }
    
    private ArrayList<AccountUser> readAccountUserDataFromFileAndCheck() throws ClassNotFoundException {
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
        boolean isAccountExist = false;
        boolean flag = true;
        AccountUser a = new AccountUser();
        a.setInfor_AccountUser();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountUser.txt"))) {
            ArrayList<AccountUser> arrayListAU = (ArrayList<AccountUser>) ois.readObject();
            for(AccountUser t : arrayListAU){
                System.out.println(t.getTendangnhap());
            }
            for(AccountUser tmp: arrayListAU){
                if(tmp.getTendangnhap().equals(a.getTendangnhap())){
                    System.out.println("Tai khoan da ton tai");
                    isAccountExist = true;
                    break;
                }
            }
            if(isAccountExist){
                do{
                    System.out.print("Mời bạn nhập lại tài khoản: ");
                    a.setTendangnhap(input_User.nextLine());
                    System.out.println("tai khoan moi nhap: " +  a.getTendangnhap());
                    for(AccountUser temporary : arrayListAU){
                        System.out.println(temporary.getTendangnhap());
                        if(temporary.getTendangnhap().equals(a.getTendangnhap())){
                            System.out.println("Tai khoan da ton tai");
                            flag = true;
                            break;
                        }else{
                            flag = false;
                        }
                    }
                    if(!flag){
                        System.out.println("Tai khoan hop le");
                        break;
                    }
                }while(true);
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
            FreeUser fe = (FreeUser)this;//TẠO RA 1 FREE USER ĐỂ LƯU LÊN FILE
            if(ghiUserDataLenFile(fe)){ // GHI THÔNG TIN CỦA 1 USER LÊN FILE
                System.out.println("Ghi thong tin user thanh cong");
            }else{
                System.out.println("Ghi thong tin user that bai");
            }
            
            if (writeAccountUserDateToFile(a)) { //GHI ACCOUNT USER LÊN FILE
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
                    ProUser pu = (ProUser)this; // TẠO RA 1 PRO USER ĐỂ GHI LÊN FILE
                    if(ghiUserDataLenFile(pu)){ // GHI THÔNG TIN CỦA 1 USER LÊN FILE
                        System.out.println("Ghi thong tin user thanh cong");
                    }else{
                        System.out.println("Ghi thong tin user that bai");
                    }
                    
                    if (writeAccountUserDateToFile(a)) { //GHI ACCOUNT USER LÊN FILE
                        System.out.println("Dang ky thanh cong");
                    } else {
                        System.out.println("Dang ky that bai");
                    }
                }
                case 0 -> {
                    this.setLoaitaiKhoan("FREE");
                    this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
                    FreeUser fe = (FreeUser)this;//TẠO RA 1 FREE USER ĐỂ LƯU LÊN FILE

                    if(ghiUserDataLenFile(fe)){ // GHI THÔNG TIN CỦA 1 USER LÊN FILE
                        System.out.println("Ghi thong tin user thanh cong");
                    }else{
                        System.out.println("Ghi thong tin user that bai");
                    }
                    
                    if (writeAccountUserDateToFile(a)) { //GHI ACCOUNT USER LÊN FILE
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
    
    private boolean ghiUserDataLenFile(User a) throws FileNotFoundException, ClassNotFoundException{
        ArrayList<User> b = new ArrayList<>();
        File f = new File("userData.txt");
        if(f.exists()){
            try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("userData.txt"))){
            b = (ArrayList<User>)oos.readObject();
            oos.close();
            }catch(EOFException e){
                b = new ArrayList<>();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        b.add(a);
        
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("userData.txt"))) {
            ois.writeObject(b);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
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

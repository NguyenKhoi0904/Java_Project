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
public class AccountUser implements Serializable{
    private String tendangnhap;
    private String matkhau;
    private String email;
    private static final long serialVersionUID = 1L;
    public AccountUser() {
    }

    public AccountUser(String taikhoan) {
        this.tendangnhap = taikhoan;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountUser(String taikhoan, String matkhau, String email) {
        this.tendangnhap = taikhoan;
        this.matkhau = matkhau;
        this.email = email;
    }

    public void setInfor_AccountUser(){
        Scanner input_AccountUser = new Scanner(System.in);
        System.out.println("*** Dang ky tai khoan ***");
        System.out.print("Tai khoan: ");
        this.setTendangnhap(input_AccountUser.nextLine());
        System.out.print("Mat khau: ");
        this.setMatkhau(input_AccountUser.nextLine());
        System.out.print("Email: ");
        this.setEmail(input_AccountUser.nextLine());
    }
    
    @Override
    public String toString(){
        return """
               **** Thong tin dang ki cua ban ****
               Tai khoan: """ + this.getTendangnhap() + "\n"
               + "Mat khau: " + this.getMatkhau() + "\n"
               + "Email: " + this.getEmail() + "\n";
                
    }
    public static void main(String[] args) {
        AccountUser au = new AccountUser();
        au.setInfor_AccountUser();
        System.out.println(au.toString());
    }
}

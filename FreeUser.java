/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;
import java.util.ArrayList;
/**
 *
 * @author aries
 */
public class FreeUser extends User{
    public void nangCapTaiKhoan(){
        ProUser pu = new ProUser();
        pu.setLoaitaiKhoan("PRO");
        pu.setIdUser(generateID_User(pu.getLoaitaiKhoan()));
        pu.setQldm(this.getQldm());
        pu.setTaiKhoanNguoiDung(this.getTaiKhoanNguoiDung());
        ArrayList<User> arraylist = docUserData();
        int dem = 0;
        for(User u: arraylist){
            if(u.getIdUser().equals(this.getIdUser())){
                break;
            }
            dem++;
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
}

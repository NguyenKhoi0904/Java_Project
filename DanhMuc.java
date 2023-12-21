/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMuc implements Serializable{
    public String getName_danhmuccha() {
        return name_danhmuccha;
    }

    public DanhMuc setName_danhmuccha(String name_danhmuccha) {
        this.name_danhmuccha = name_danhmuccha;
        return this;
    }

    private String name_danhmuccha;
    private String id_danhmuc;
    private String tendanhmuc;
    private int Tien;
    private int TienDanhMucCap1=0;
    private ArrayList<DanhMuc> dsDanhMuccon;
    public DanhMuc(String id_danhmuc,String tendanhmuc){
        this.id_danhmuc=id_danhmuc;
        this.tendanhmuc=tendanhmuc;
        Tien=0;
        this.TienDanhMucCap1=0;
        dsDanhMuccon= new ArrayList<>();
    }
    public DanhMuc(String id_danhmuc,String tendanhmuc,String danhmuccha){
        this.id_danhmuc=id_danhmuc;
        this.tendanhmuc=tendanhmuc;
        this.name_danhmuccha=danhmuccha;
        this.Tien=Tien;
        dsDanhMuccon= new ArrayList<>();
    }


    public String getid_danhmuc() {
        return id_danhmuc;
    }

    public void setId_danhmuc(String id_danhmuc) {
        this.id_danhmuc = id_danhmuc;

    }

    public String gettendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;

    }

    public ArrayList<DanhMuc> getdanhsachdanhmuccon() {
        return dsDanhMuccon;
    }

    public void themdanhmuccon(DanhMuc tendanhmuc) {
        dsDanhMuccon.add(tendanhmuc);
        setMoney();

    }
    public void setTienDanhMucCap1(int tien){
        this.TienDanhMucCap1+=tien;
    }
    public int getTienDanhMucCap1(){
        return this.TienDanhMucCap1;
    }
    public void setMoney(){
        int tongtien=0;
        for(DanhMuc money : dsDanhMuccon){
            tongtien+=money.Tien;
        }
        this.Tien=tongtien+getTienDanhMucCap1();
    }
    public void setMoney(int money){

        this.Tien=money;
    }
    public void updateMoney(int money){
        this.Tien+=money;
    }
    public int getMoney(){
        return this.Tien;
    }





}

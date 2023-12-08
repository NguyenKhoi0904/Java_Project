/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;
import java.util.ArrayList;
/**
 *
 * @author aries
 */
public class ListGiaoDich {
    private ArrayList<GiaoDich> dsGD;

    public ListGiaoDich() {
        dsGD = new ArrayList<>();
    }

    public ListGiaoDich(ArrayList<GiaoDich> dsGD) {
        this.dsGD = dsGD;
    }

    public ArrayList<GiaoDich> getDsGD() {
        return dsGD;
    }

    public void setDsGD(ArrayList<GiaoDich> dsGD) {
        this.dsGD = dsGD;
    }
    
    public void addGD(GiaoDich gd){
        this.getDsGD().add(gd);
    }
    
    public void printAll_GiaoDich(){
        for(GiaoDich gd: this.getDsGD()){
            System.out.println(gd);
        }
    }
}

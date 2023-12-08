/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLyDanhMuc {
    private ListDanhMuc danhmucchi;
    private ListDanhMuc danhmucthu;
    private ArrayList<GiaoDich> dsgiaodich;

    public QuanLyDanhMuc(){
        this.danhmucchi=new ListDanhMuc(new DanhMuc("TY1","Thiết yếu"),new DanhMuc("BT1","Biếu tặng"),new DanhMuc("SK1","Sức khỏe"),new DanhMuc("GT","Giải trí"));
        this.danhmucthu=danhmucthu = new ListDanhMuc(new DanhMuc("L1","Lương"),new DanhMuc("T1","Thưởng"));
        dsgiaodich = new ArrayList<>();
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------XIN NHẬP LỰA CHỌN--------------------------");
        System.out.println("| 1.THÊM DANH MỤC                                                   |");
        System.out.println("| 2.XÓA DANH MỤC                                                    |");
        System.out.println("| 3.SỬA DANH MỤC                                                    |");
        System.out.println("| 4.DISPLAY DANH MỤC                                                |");
        System.out.println("| 5.GIAO DỊCH                                                       |");
        System.out.println("| 6.XEM LỊCH SỬ GIAO DỊCH                                           |");
        System.out.println("| 7.THOÁT                                                           |");
        System.out.println("---------------------------------------------------------------------");

        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Chọn loại danh mục ");
                    System.out.println("1 CHI ");
                    System.out.println("2 THU ");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("VUI LÒNG NHẬP DANH MỤC CẦN THÊM");
                            System.out.println("|Nhập id                                                            |");
                            String id = sc.nextLine();
                            sc.nextLine();
                            System.out.println("|Tên danh mục                                                       |");
                            String name = sc.nextLine();
                            DanhMuc danhmuccanthem = new DanhMuc(id, name);
                            System.out.println("|Vai trò danh mục cha hay con                                       |");
                            System.out.println("|Nhập 1 nếu danh mục cha                                            |");
                            System.out.println("|Nhập 2 nếu danh mục con                                            |");
                            System.out.println("|Nhập các nút còn lại nếu thoát                                     |");
                            int m = sc.nextInt();
                            sc.nextLine();
                            try {
                                switch (m) {
                                    case 1:
                                        danhmucchi.themdanhmuc(danhmuccanthem);
                                        menu();
                                        break;
                                    case 2:
                                        int i = 1;
                                        for (DanhMuc list : danhmucchi.getDsDanhMuc()) {

                                            System.out.println(i + ": " + list.gettendanhmuc());
                                            i++;
                                        }
                                        System.out.println("Chọn danh mục cha :");
                                        int type = sc.nextInt();
                                        try {
                                            DanhMuc DanhMucCha = danhmucchi.getDsDanhMuc().get(type - 1);
                                            DanhMucCha.getdanhsachdanhmuccon().add(danhmuccanthem);
                                            danhmuccanthem.setName_danhmuccha(DanhMucCha.gettendanhmuc());

                                        } catch (Exception e) {
                                            System.out.println("bạn nhập không hợp lệ");
                                            menu();
                                        }
                                        menu();
                                        break;


                                    default:
                                        System.out.println("------------------Bạn đã thoát-------------------");
                                        menu();

                                }
                            } catch (Exception e) {
                                System.out.println("------------------Bạn đã thoát-------------------");
                                menu();
                            }

                            break;
                        case 2:
                            System.out.println("VUI LÒNG NHẬP DANH MỤC CẦN THÊM");
                            System.out.println("|Nhập id                                                            |");
                            id = sc.nextLine();
                            sc.nextLine();
                            System.out.println("|Tên danh mục                                                       |");
                            name = sc.nextLine();
                            danhmuccanthem = new DanhMuc(id, name);
                            System.out.println("|Vai trò danh mục cha hay con                                       |");
                            System.out.println("Nhập 1 nếu danh mục cha,Nhập 2 nếu danh mục con                     |");
                            m = sc.nextInt();
                            sc.nextLine();
                            switch (m) {
                                case 1:
                                    danhmucthu.themdanhmuc(danhmuccanthem);
                                    menu();
                                    break;
                                case 2:
                                    int x = 1;
                                    for (DanhMuc list : danhmucthu.getDsDanhMuc()) {

                                        System.out.println(x + ": " + list.gettendanhmuc());
                                        x++;
                                    }
                                    int type = sc.nextInt();
                                    try {
                                        DanhMuc DanhMucCha = danhmucthu.getDsDanhMuc().get(type - 1);
                                        DanhMucCha.getdanhsachdanhmuccon().add(danhmuccanthem);

                                    } catch (Exception e) {
                                        System.out.println("bạn nhập không hợp lệ");
                                        menu();
                                    }
                                    menu();
                            }
                        default:menu();
                    }
                    break;


                case 2:
                    System.out.println("Nhap ten danh muc can xoa");
                    sc.nextLine();
                    String nameDanhMuc = sc.nextLine();
                    System.out.println("Nhap Loai danh muc can xoa");
                    System.out.println("1 :Danh Muc chi");
                    System.out.println("2 :Nhap Loai thu");
                    int typeDanhMuc = Integer.parseInt(sc.nextLine());
                    while (typeDanhMuc != 1 && typeDanhMuc != 2) {
                        System.out.println("Vui lòng nhập lại                            ");
                        typeDanhMuc = Integer.parseInt(sc.nextLine());
                    }
                    if (typeDanhMuc == 1) {
                        danhmucchi.deleteDanhMuc(nameDanhMuc);
                    } else if (typeDanhMuc == 2) {
                        danhmucthu.deleteDanhMuc(nameDanhMuc);
                    }
                    menu();
                    break;
                case 3:
                    System.out.println("Nhap Loai danh muc can sua ");
                    System.out.println("Nhap 1 nếu là Chi ");
                    System.out.println("Nhap 2 nếu là Thu ");
                    System.out.println("Các nút còn lại để thoát");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("Ban muon sua danh muc cha hay con ");
                            System.out.println("Chon 1 neu la cha , 2 neu la con , còn lại là thoát ");
                            switch (sc.nextInt()) {
                                case 1:
                                    System.out.println("Nhap ten danh muc can sua");
                                    String name1 = sc.nextLine();
                                    System.out.println("Nhap ten danh muc moi");
                                    String name2 = sc.nextLine();
                                    danhmucchi.EditDanhMucCha(name1, name2);
                                    menu();
                                    break;
                                case 2:
                                    System.out.println("Nhap ten danh muc can sua");
                                    String name3 = sc.nextLine();
                                    System.out.println("Nhap ten danh muc moi");
                                    String name4 = sc.nextLine();
                                    danhmucchi.EditDanhMucCon(name3, name4);
                                    menu();
                                    break;

                                default:
                                    menu();
                            }
                            break;
                        case 2:
                            System.out.println("Ban muon sua danh muc cha hay con ");
                            System.out.println("Chon 1 neu la cha , 2 neu la con ,các số còn lại là thoát ");
                            switch (sc.nextInt()) {
                                case 1:
                                    System.out.println("Nhap ten danh muc can sua");
                                    String name1 = sc.nextLine();
                                    System.out.println("Nhap ten danh muc moi");
                                    String name2 = sc.nextLine();
                                    danhmucthu.EditDanhMucCha(name1, name2);
                                    menu();
                                    break;
                                case 2:
                                    System.out.println("Nhap ten danh muc can sua");
                                    String name3 = sc.nextLine();
                                    System.out.println("Nhap ten danh muc moi");
                                    String name4 = sc.nextLine();
                                    danhmucthu.EditDanhMucCon(name3, name4);
                                    menu();
                                    break;
                                default:
                                    try {
                                        menu();
                                    } catch (Exception e) {
                                        System.out.println("Vui long nhap so");
                                        menu();
                                    }
                            }

                            break;
                        default:
                            System.out.println("-----------------Bạn đã thoát----------------------");
                            menu();
                    }

                case 4:
                    System.out.println("Danh Mục Chi là");
                    danhmucchi.lietkedanhmuc("Danh mục chi :");
                    System.out.println("Danh Mục thu là");
                    danhmucthu.lietkedanhmuc("Danh mục thu :");
                    menu();
                    break;

                case 5:
                    System.out.println("Nhap ngay giao dich");
                    int ngay = sc.nextInt();
                    while (ngay > 31 || ngay < 1 || ngay != (int) ngay) {
                        System.out.println("Ngay khong hop le, moi nhap lai");

                        ngay = sc.nextInt();
                    }
                    System.out.println("Nhap thang giao dich");
                    int thang = sc.nextInt();
                    while (thang > 12 || thang < 1 || thang != (int) thang) {
                        System.out.println("Thang khong hop le,moi nhap lai");
                        thang = sc.nextInt();
                    }
                    System.out.println("Nhap nam giao dich");
                    int nam = sc.nextInt();
                    while (nam < 2020 || nam > 2023 || nam != (int) nam) {
                        System.out.println("Nam khong hop le,moi nhap lai");
                        nam = sc.nextInt();
                    }
                    sc.nextLine();
                    NgayThangNam date = new NgayThangNam(ngay, thang, nam);
                    System.out.println("Nhap noi dung can giao dich");
                    String noidung = sc.nextLine();
                    System.out.println("Nhap so tien giao dich");
                    int sotien = Integer.parseInt(sc.nextLine());

                    System.out.println("Nhap loai danh muc can giao dich ");
                    System.out.println("1 Thu                            ");
                    System.out.println("2 Chi                            ");
                    int type = Integer.parseInt(sc.nextLine());
                    while (type != 1 && type != 2) {
                        System.out.println("Vui Long Nhap Lai");
                        type = Integer.parseInt(sc.nextLine());
                    }
                    if (type == 1) {
                        System.out.println("Nhập tên danh mục cần giao dịch ");
                        danhmucthu.lietkedanhmuc("Danh mục thu : ");
                        String tendanhmuc = sc.nextLine();
                        DanhMuc DanhMucCanGiaoDich = danhmucthu.timdanhmuctheoten(danhmucthu.getDsDanhMuc(), tendanhmuc);
                        while (DanhMucCanGiaoDich == null) {
                            System.out.println("tên danh mục không tồn tại, bạn muốn tiếp tục nhập hay quay về menu chính ");
                            System.out.println("Nhấn 0 nếu tiếp tục nhập, Nhấn các nút còn lại để thoát  ");
                            int choices = sc.nextInt();
                            if (choices == 0) {
                                System.out.println("Mời bạn nhập lại ");
                                tendanhmuc = sc.nextLine();
                                DanhMucCanGiaoDich = danhmucthu.timdanhmuctheoten(danhmucthu.getDsDanhMuc(), tendanhmuc);
                            } else {
                                menu();
                            }
                        }
                        System.out.println(DanhMucCanGiaoDich.gettendanhmuc());
                        GiaoDich Bill = new GiaoDich(date, noidung, sotien, DanhMucCanGiaoDich, 2);
                        Bill.Chuyentienvaodanhmuc();
                        danhmucthu.timdanhmuctheoten(DanhMucCanGiaoDich.getName_danhmuccha()).setMoney();
                        danhmucthu.setTongsotien();
                        dsgiaodich.add(Bill);
                        System.out.println("Giao Dich Thanh Cong");
                        menu();
                    }
                    if (type == 2) {
                        System.out.println("Nhập tên danh mục cần giao dịch ");
                        danhmucchi.lietkedanhmuc("Danh mục chi : ");
                        String tendanhmuc = sc.nextLine();
                        DanhMuc DanhMucCanGiaoDich = danhmucchi.timdanhmuctheoten(danhmucchi.getDsDanhMuc(), tendanhmuc);
                        while (DanhMucCanGiaoDich == null) {
                            System.out.println("tên danh mục không tồn tại, bạn muốn tiếp tục nhập hay quay về menu chính ");
                            System.out.println("Nhấn 0 nếu tiếp tục nhập, Nhấn các nút còn lại để thoát  ");
                            int choices = sc.nextInt();
                            if (choices == 0) {
                                System.out.println("Mời bạn nhập lại ");
                                sc.nextLine();
                                tendanhmuc = sc.nextLine();
                                DanhMucCanGiaoDich = danhmucchi.timdanhmuctheoten(danhmucchi.getDsDanhMuc(), tendanhmuc);
                            } else {
                                menu();
                            }
                        }
                        System.out.println(DanhMucCanGiaoDich.gettendanhmuc());
                        GiaoDich Bill = new GiaoDich(date, noidung, sotien, DanhMucCanGiaoDich, 1);
                        Bill.Chuyentienvaodanhmuc();
                        danhmucchi.timdanhmuctheoten(DanhMucCanGiaoDich.getName_danhmuccha()).setMoney();
                        danhmucchi.setTongsotien();
                        dsgiaodich.add(Bill);
                        System.out.println("Giao Dich Thanh Cong");
                        menu();
                    }
                    break;

                case 6:
                    System.out.println("-----------------------------------------------------Lịch sử giao dịch của bạn------------------------------------------------");
                    System.out.println("--------------------------------------------------------Thông tin giao dịch---------------------------------------------------");
                    for (GiaoDich lichsu : dsgiaodich) {
                        System.out.println(lichsu.toStringGiaoDich());
                    }
                    menu();
                    break;
                case 7:
                    System.out.println("HẸN GẶP LẠI!!!!!!");

                default:
                    System.out.println("Bạn nhập sai yêu cầu");
                    menu();
            }
        }
        catch(InputMismatchException e) {
            System.out.println("VUI LÒNG NHẬP SỐ");
            menu();
        }
    }
    public void setdanhmucchi(ListDanhMuc danhmucchi){
        this.danhmucchi=danhmucchi;
    }
    public void setdanhmucthu(ListDanhMuc danhmucthu){
        this.danhmucthu=danhmucthu;
    }
    public ListDanhMuc getDanhMucChi(){
        return this.danhmucchi;
    }
    public ListDanhMuc getDanhMucThu(){
        return this.danhmucthu;
    }
    public static void main(String [] args){
        QuanLyDanhMuc user = new QuanLyDanhMuc();
        user.menu();
    }
}

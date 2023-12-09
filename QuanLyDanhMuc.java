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
    private ListGiaoDich dsgiaodich;

    public QuanLyDanhMuc(){
        this.danhmucchi=new ListDanhMuc(new DanhMuc("TY1","Thiết yếu"),new DanhMuc("BT1","Biếu tặng"),new DanhMuc("SK1","Sức khỏe"),new DanhMuc("GT","Giải trí"));
        this.danhmucthu = new ListDanhMuc(new DanhMuc("L1","Lương"),new DanhMuc("T1","Thưởng"));
        this.dsgiaodich = new ListGiaoDich();
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("___________________________________________XIN NHẬP LỰA CHỌN___________________________________________");
        System.out.println("|1.THÊM DANH MỤC                                                                                      |");
        System.out.println("|------------------------------------------------------------------------------------------------------");
        System.out.println("|2.XÓA DANH MỤC                                                                                       |");
        System.out.println("|------------------------------------------------------------------------------------------------------");
        System.out.println("|3.SỬA DANH MỤC                                                                                       |");
        System.out.println("|------------------------------------------------------------------------------------------------------");
        System.out.println("|4.DISPLAY DANH MỤC                                                                                   |");
        System.out.println("|------------------------------------------------------------------------------------------------------");
        System.out.println("|5.GIAO DỊCH                                                                                          |");
        System.out.println("|------------------------------------------------------------------------------------------------------");
        System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                              |");
        System.out.println("|------------------------------------------------------------------------------------------------------");
        System.out.println("|7.THỐNG KÊ GIAO DỊCH                                                                                 |");
        System.out.println("|------------------------------------------------------------------------------------------------------");
        System.out.println("|8.THOÁT                                                                                              |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");

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
                            System.out.println("|Nhập ID danh mục cần thêm                                                             |");
                            String id = sc.nextLine();
                            sc.nextLine();
                            System.out.println("|Tên danh mục cần thêm                                                                 |");
                            String name = sc.nextLine();
                            DanhMuc danhmuccanthem = new DanhMuc(id, name);
                            System.out.println("|Vai trò danh mục cấp 1 hay cấp 2                                                      |");
                            System.out.println("|Nhập 1 nếu danh mục cấp 1                                                             |");
                            System.out.println("|Nhập 2 nếu danh mục cấp 2                                                             |");
                            System.out.println("|Nhập các nút còn lại nếu thoát                                                        |");
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
                            System.out.println("|Vai trò danh mục cấp 1 hay cấp 2                                         |");
                            System.out.println("|Nhập 1 nếu danh mục cấp 1,Nhập 2 nếu danh mục cấp 2                      |");
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
                                        danhmuccanthem.setName_danhmuccha(DanhMucCha.gettendanhmuc());
                                    } catch (Exception e) {
                                        System.out.println("bạn nhập không hợp lệ");
                                        menu();
                                    }
                                    menu();
                            }
                        default:
                            menu();
                    }
                    break;


                case 2:
                    System.out.println("Nhập tên danh mục cần xóa");
                    sc.nextLine();
                    String nameDanhMuc = sc.nextLine();
                    System.out.println("Nhap loại danh mục cần xóa");
                    System.out.println("1 :Danh mục chi        ");
                    System.out.println("2 :Danh mục thu        ");
                    int typeDanhMuc = Integer.parseInt(sc.nextLine());
                    while (typeDanhMuc != 1 && typeDanhMuc != 2) {
                        System.out.println("Vui lòng nhập lại ");
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
                    System.out.println("Nhập loại danh mục cần sửa ");
                    System.out.println("Nhập 1 nếu là Chi ");
                    System.out.println("Nhập 2 nếu là Thu ");
                    System.out.println("Các nút còn lại để thoát");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("Bạn muốn sửa danh mục cấp 1 hay cấp 2 ");
                            System.out.println("Chon 1 nếu là cấp 1 , 2 nếu là cấp 2 , còn lại là thoát ");
                            switch (sc.nextInt()) {
                                case 1:
                                    System.out.println("Nhập tên danh mục cần sửa");
                                    sc.nextLine();
                                    String name1 = sc.nextLine();
                                    System.out.println("Nhập tên danh mục mới");
                                    String name2 = sc.nextLine();
                                    danhmucchi.EditDanhMucCha(name1, name2);
                                    menu();
                                    break;
                                case 2:
                                    System.out.println("Nhập tên danh mục cần sửa");
                                    String name3 = sc.nextLine();
                                    System.out.println("Nhập tên danh mục mới");
                                    String name4 = sc.nextLine();
                                    danhmucchi.EditDanhMucCon(name3, name4);
                                    menu();
                                    break;

                                default:
                                    menu();
                            }
                            break;
                        case 2:
                            System.out.println("Bạn muốn thêm vào cấp 1 hay cấp 2");
                            System.out.println("Chọn 1 nếu là cấp 1 , 2 nếu là cấp 2 ,các số còn lại là thoát ");
                            switch (sc.nextInt()) {
                                case 1:
                                    System.out.println("Nhập tên danh mục cần sửa");
                                    String name1 = sc.nextLine();
                                    System.out.println("Nhập tên danh mục mới");
                                    String name2 = sc.nextLine();
                                    danhmucthu.EditDanhMucCha(name1, name2);
                                    menu();
                                    break;
                                case 2:
                                    System.out.println("Nhập tên danh mục cần sửa");
                                    String name3 = sc.nextLine();
                                    System.out.println("Nhập tên danh mục mới");
                                    String name4 = sc.nextLine();
                                    danhmucthu.EditDanhMucCon(name3, name4);
                                    menu();
                                    break;
                                default:
                                    try {
                                        menu();
                                    } catch (Exception e) {
                                        System.out.println("------------Vui lòng nhập số hợp lệ!!------------ ");
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
                    danhmucchi.lietkedanhmuc("danh mục chi :");
                    System.out.println("Danh Mục thu là");
                    danhmucthu.lietkedanhmuc("danh mục thu :");
                    menu();
                    break;

                case 5:
                    System.out.println("Nhập ngày giao dịch");
                    int ngay = sc.nextInt();
                    while (ngay > 31 || ngay < 1 || ngay != (int) ngay) {
                        System.out.println("Ngày không hợp lệ, mời nhập lại");

                        ngay = sc.nextInt();
                    }
                    System.out.println("Nhập tháng giao dịch");
                    int thang = sc.nextInt();
                    while (thang > 12 || thang < 1 || thang != (int) thang) {
                        System.out.println("Tháng không hợp mời nhập lại");
                        thang = sc.nextInt();
                    }
                    System.out.println("Nhập năm giao dịch");
                    int nam = sc.nextInt();
                    while (nam < 2000 || nam > 2023 || nam != (int) nam) {
                        System.out.println("Năm không hợp,mời nhập lại");
                        nam = sc.nextInt();
                    }
                    sc.nextLine();
                    NgayThangNam date = new NgayThangNam(ngay, thang, nam);
                    System.out.println("Nhập nội dung giao dịch");
                    String noidung = sc.nextLine();
                    System.out.println("Nhập số tiền cần giao dịch");
                    int sotien = Integer.parseInt(sc.nextLine());

                    System.out.println("Nhập loại danh mục cần giao dịch ");
                    System.out.println("1 Thu                            ");
                    System.out.println("2 Chi                            ");
                    int type = Integer.parseInt(sc.nextLine());
                    while (type != 1 && type != 2) {
                        System.out.println("Vui lòng nhập lại");
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
                                sc.nextLine();
                                System.out.println("Mời bạn nhập lại ");
                                tendanhmuc = sc.nextLine();
                                DanhMucCanGiaoDich = danhmucthu.timdanhmuctheoten(danhmucthu.getDsDanhMuc(), tendanhmuc);
                            } else {
                                menu();
                            }
                        }
                        System.out.println("Đã thêm vào danh mục " +DanhMucCanGiaoDich.gettendanhmuc());
                        GiaoDich Bill = new GiaoDich(date, noidung, sotien, DanhMucCanGiaoDich, 2);
                        Bill.Chuyentienvaodanhmuc();
                        danhmucchi.timdanhmuctheoten(danhmucthu.getDsDanhMuc(),DanhMucCanGiaoDich.getName_danhmuccha()).setMoney();
                        danhmucthu.setTongsotien();
                        dsgiaodich.addGD(Bill);
                        System.out.println("Giao dịch thành công");
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
                        System.out.println("Đã thêm vào danh mục " +DanhMucCanGiaoDich.gettendanhmuc());
                        GiaoDich Bill = new GiaoDich(date, noidung, sotien, DanhMucCanGiaoDich, 1);
                        Bill.Chuyentienvaodanhmuc();
                        danhmucchi.timdanhmuctheoten(DanhMucCanGiaoDich.getName_danhmuccha()).setMoney();
                        danhmucchi.setTongsotien();
                        dsgiaodich.addGD(Bill);
                        System.out.println("Giao dịch thành công");
                        menu();
                    }
                    break;

                case 6:
                    System.out.println("-----------------------------------------------------Lịch sử giao dịch của bạn------------------------------------------------");
                    System.out.println("--------------------------------------------------------Thông tin giao dịch---------------------------------------------------");
                    for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                        System.out.println(lichsu.toStringGiaoDich());
                    }
                    menu();
                    break;
                case 7:
                    System.out.println("----------------------------------THỐNG KÊ----------------------------------");
                    System.out.println("|1.Thống kê theo tuần                                                                        |");
                    System.out.println("|2.Thống kê theo tháng                                                                       |");
                    System.out.println("|1.Thống kê theo năm                                                                         |");
                    switch(sc.nextInt()) {

                        case 1:
                            sc.nextLine();
                            System.out.println("Nhap nam ");//thieu rang buoc
                            int year_1 = sc.nextInt();
                            System.out.println("Nhap thang ");//thieu rang buoc
                            int month_1 = sc.nextInt();
                            thongke(month_1, year_1);
                            menu();
                            break;

                        case 2:
                            sc.nextLine();
                            System.out.println("Nhap nam ");//thieu rang buoc
                            int year_2 = sc.nextInt();
                            thongke(year_2);
                            menu();
                            break;


                        case 4:
                            menu();
                    }
                    break;
               case 8:
                   System.out.println("HẸN GẶP LẠI!!!!!!");

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
    public void thongke(int month, int year) {
        int[] danhmucthu = new int[4];
        int[] danhmucchi = new int[4];

        for (int i = 0; i < 4; i++) {
            danhmucthu[i] = 0;
            danhmucchi[i] = 0;
        }

        for (GiaoDich gd : dsgiaodich.getDsGD()) {
            if (gd.getNgayGiaoDich().getthang() == month && gd.getNgayGiaoDich().getnam() == year) {
                int week = (gd.getNgayGiaoDich().getngay() - 1) / 7; // Xác định tuần

                if (gd.getLoaigiaodich().equals("Giao dịch chi")) {
                    danhmucchi[week] += gd.getsotien();
                } else if (gd.getLoaigiaodich().equals("Giao dịch thu")) {
                    danhmucthu[week] += gd.getsotien();
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(" - Tuần " + (i + 1) + " :");
            System.out.println(" +Số tiền chi: " + danhmucchi[i]);
            System.out.println(" +Số tiền thu được: " + danhmucthu[i]);

            double percent = danhmucchi[i] * 1.0 / (danhmucchi[i] + danhmucthu[i]);
            System.out.println(" Chiếm " + percent * 100 + "%");

            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        System.out.println("Tuần chi nhiều nhất là Tuần " + (chiNhieunhat(danhmucchi) + 1)+"với số tiền là "+danhmucchi[chiNhieunhat(danhmucchi)]);
    }

    public void thongke(int year) {
        int[] danhmucthu = new int[12];
        int[] danhmucchi = new int[12];

        for (int i = 0; i < 12; i++) {
            danhmucthu[i] = 0;
            danhmucchi[i] = 0;
        }

        for (GiaoDich gd : dsgiaodich.getDsGD()) {
            if (gd.getNgayGiaoDich().getnam() == year) {
                int i = gd.getNgayGiaoDich().getthang() - 1; // Số tháng từ 0 đến 11

                if (gd.getLoaigiaodich().equals("Giao dịch chi")) {
                    danhmucchi[i] += gd.getsotien();
                } else if (gd.getLoaigiaodich().equals("Giao dịch thu")) {
                    danhmucthu[i] += gd.getsotien();
                }
            }
        }
        for (int i = 0; i < 12; i++) {
            System.out.println(" - Tháng " + (i + 1) + " :");
            System.out.println(" +Số tiền chi: " + danhmucchi[i]);
            System.out.println(" +Số tiền thu được: " + danhmucthu[i]);

            double percent = danhmucchi[i] * 1.0 / (danhmucchi[i] + danhmucthu[i]);
            System.out.println(" Danh mục chi chiếm " + percent * 100 + "%");

            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        System.out.println("Tháng chi nhiều nhất là Tháng " + (chiNhieunhat(danhmucchi) + 1)+"với số tiền là "+danhmucchi[chiNhieunhat(danhmucchi)]);
    }



    public double TongSoTientrongdanhmuc(int [] array){
        int sum=0;
        for(int i=0;i<array.length;i++){
          sum+=array[i];
        }
        return sum;
    }
    public int chiNhieunhat(int [] arr){
        int maxIndex = 0;
        int maxVal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
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

    public ListGiaoDich getDsgiaodich() {
        return dsgiaodich;
    }
    public void setDsgiaodich(ListGiaoDich dsgiaodich) {
        this.dsgiaodich = dsgiaodich;
    }

    public static void main(String [] args){
        QuanLyDanhMuc user = new QuanLyDanhMuc();
        user.menu();
    }
}

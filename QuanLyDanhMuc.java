/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class QuanLyDanhMuc {

    private ListDanhMuc danhmucchi;
    private ListDanhMuc danhmucthu;
    private ListGiaoDich dsgiaodich;
    Scanner sc = new Scanner(System.in);
    public QuanLyDanhMuc(){
        this.danhmucchi=new ListDanhMuc(new DanhMuc("TY1","Thiết yếu"),new DanhMuc("BT1","Biếu tặng"),new DanhMuc("SK1","Sức khỏe"),new DanhMuc("GT","Giải trí"));
        this.danhmucthu = new ListDanhMuc(new DanhMuc("L1","Lương"),new DanhMuc("T1","Thưởng"));
        this.dsgiaodich = new ListGiaoDich();
    }
    private String generateID_DanhMuc(String prefix){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 9));

        String formatNumber = String.format("%0" + 9 + "d", randomNumber);
        return prefix + formatNumber;
    }
    public void ChonDanhMucDeThem(){
        System.out.println("Chọn loại danh mục ");
        System.out.println("Hãy nhập số ");
        System.out.println("1:CHI ");
        System.out.println("2:THU ");
        System.out.println("CÁC NÚT CÒN LẠI THOÁT ");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

                    ThemDanhMuc(danhmucchi, "DMC");
                    break;
                case 2:
                    ThemDanhMuc(danhmucthu, "DMT");
                    break;
                default:
                    menu();
            }
        }
        catch(Exception e){
            menu();
        }
    }
    public void ThemDanhMuc(ListDanhMuc danhmuc,String idDanhMuc){

        System.out.println("VUI LÒNG NHẬP DANH MỤC CẦN THÊM");
        String id = generateID_DanhMuc(idDanhMuc);
        System.out.println("|Tên danh mục cần thêm                                                                 |");
        String name = sc.nextLine();
        DanhMuc danhmuccanthem = new DanhMuc(id, name);
        while(danhmucchi.timdanhmuctheoten(danhmucchi.getDsDanhMuc(),danhmuccanthem.gettendanhmuc())!=null || danhmucthu.timdanhmuctheoten(danhmucthu.getDsDanhMuc(),danhmuccanthem.gettendanhmuc())!=null){
            System.out.println("Danh mục bị trùng , hãy nhập lại");
            name = sc.nextLine();
            danhmuccanthem.setTendanhmuc(name);
        }
        System.out.println("|Vai trò danh mục cấp 1 hay cấp 2                                                      |");
        System.out.println("|Nhập 1 nếu danh mục cấp 1                                                             |");
        System.out.println("|Nhập 2 nếu danh mục cấp 2                                                             |");
        System.out.println("|Nhập các nút còn lại nếu thoát                                                        |");
        int m = sc.nextInt();
        sc.nextLine();
        try {
            switch (m) {
                case 1:
                    danhmuc.themdanhmuc(danhmuccanthem);
                    System.out.println("Bạn có muốn thêm danh mục nữa không ?");
                    System.out.println("1:Có");
                    System.out.println("Các nút còn lại:back về menu");
                    try {
                        int choice1 = Integer.parseInt(sc.nextLine());
                        if (choice1 == 1) {
                            ChonDanhMucDeThem();
                        } else {
                            menu();
                        }
                    }
                    catch(Exception e){
                        menu();
                    }
                    break;
                case 2:
                    int i = 1;
                    for (DanhMuc list : danhmuc.getDsDanhMuc()) {
                        System.out.println(i + ": " + list.gettendanhmuc());
                        i++;
                    }
                    System.out.println("Chọn danh mục cha :");
                    System.out.println("Hãy nhập số :");
                    try {
                        int type = Integer.parseInt(sc.nextLine());
                        DanhMuc DanhMucCha = danhmuc.getDsDanhMuc().get(type - 1);
                        DanhMucCha.getdanhsachdanhmuccon().add(danhmuccanthem);
                        danhmuccanthem.setName_danhmuccha(DanhMucCha.gettendanhmuc());
                        System.out.println("Đã tạo danh mục " + danhmuccanthem.gettendanhmuc());
                        System.out.println("Bạn có muốn tạo tiếp danh mục nào nữa không");
                        System.out.println("1:YES ");
                        System.out.println("Các nút còn lại:Về menu ");
                        try {
                            int choice1 = Integer.parseInt(sc.nextLine());
                            if (choice1 == 1) {
                                ChonDanhMucDeThem();
                            } else {
                                menu();
                            }
                        } catch(Exception e){
                            menu();
                        }
                    }
                    catch (Exception e) {
                            System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                            System.out.println("Bạn muốn tiếp tục hay về menu.");
                            System.out.println("1:Tiếp tục.");
                            System.out.println("các số còn lại: về menu.");
                            try {
                                int choice1 = Integer.parseInt(sc.nextLine());
                                if (choice1 == 1) {
                                    ChonDanhMucDeThem();
                                }
                                else {
                                    menu();
                                }
                            }
                            catch(Exception a) {
                                menu();
                            }
                    }





                        break;


                default:
                    System.out.println("-----------------------------------------Bạn đã thoát-----------------------------------------");
                    menu();

            }
        } catch (Exception e) {
            System.out.println("-----------------------------------------Bạn đã thoát vì đã nhập chữ-----------------------------------");
            menu();
        }
    }
    public void ChonDanhMucdexoa(){

        System.out.println("Bạn muốn xóa danh mục nào ");
        System.out.println("1:CHI ");
        System.out.println("2:THU ");
        System.out.println("CÁC NÚT CÒN LẠI:THOÁT ");

        try {
            int choice=Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                xoaDanhMuc(danhmucchi);
                System.out.println("Bạn có muốn xóa tiếp danh mục nào nữa không");
                System.out.println("1:YES ");
                System.out.println("Các số còn lại:NO ");
                int choice1=sc.nextInt();
                if(choice1==1){
                    ChonDanhMucdexoa();
                }
                else{
                    menu();
                }
            }
            else if (choice == 2) {
                xoaDanhMuc(danhmucthu);
                System.out.println("Bạn có muốn xóa tiếp danh mục nào nữa không");
                System.out.println("1:YES ");
                System.out.println("2:NO ");
                int choice2=sc.nextInt();
                if(choice2==1){
                    ChonDanhMucdexoa();
                }
                else{
                    menu();
                }
            }
            else {
                menu();
            }
        }
        catch(Exception x){

                menu();


        }

    }
    public void xoaDanhMuc(ListDanhMuc danhmuc){
        danhmuc.lietkedanhmuc();
        System.out.println("Bạn muốn xóa danh mục nào trong các danh mục trên");
        String nameDanhMuc = sc.nextLine();
        danhmuc.deleteDanhMuc(nameDanhMuc);
    }
    public void chonDanhMucDeSua(){
        System.out.println("NHẬP LOẠI DANH MỤC CẦN SỬA ");
        System.out.println("HÃY NHẬP SỐ");
        System.out.println("1:CHI ");
        System.out.println("2:THU ");
        System.out.println("CÁC NÚT CÒN LẠI:THOÁT ");


        try {
            int choice=Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                suaDanhMuc(danhmucchi);
                System.out.println("Bạn có muốn sửa tiếp danh mục nào nữa không");
                System.out.println("1:YES ");
                System.out.println("Các số còn lại:NO ");
                int choice1 = Integer.parseInt(sc.nextLine());
                if (choice1 == 1) {
                    chonDanhMucDeSua();
                } else {
                    menu();
                }
            } else if (choice == 2) {
                suaDanhMuc(danhmucthu);
                System.out.println("Bạn có muốn sửa tiếp danh mục nào nữa không");
                System.out.println("1:YES ");
                System.out.println("2:NO ");
                int choice2 = Integer.parseInt(sc.nextLine());
                if (choice2 == 1) {

                    chonDanhMucDeSua();
                } else {
                    menu();
                }
            } else {
                menu();
            }
        }
        catch(Exception e) {
            menu();
        }
    }
    public void suaDanhMuc(ListDanhMuc danhmuc) {

                System.out.println("|Bạn muốn sửa danh mục cấp 1 hay cấp 2                  |");
                System.out.println("|1: nếu là cấp 1 ");
                System.out.println("|2: nếu là cấp 2 ");
                System.out.println("|3: còn lại back về menu|");
                try {
                    switch (Integer.parseInt(sc.nextLine())) {
                        case 1:
                            int i = 1;
                            for (DanhMuc list : danhmuc.getDsDanhMuc()) {
                                System.out.println(i + ": " + list.gettendanhmuc());
                                i++;
                            }
                            System.out.println("Chọn danh mục cha :");
                            System.out.println("Hãy nhập số :");
                            try {
                                int so = Integer.parseInt(sc.nextLine());
                                DanhMuc DanhMucCha = danhmuc.getDsDanhMuc().get(so - 1);
                                System.out.println("Nhập tên danh mục mới");
                                String name2 = sc.nextLine();
                                danhmuc.EditDanhMucCha(DanhMucCha.gettendanhmuc(), name2);
                            }
                            catch(Exception e){
                                System.out.println("Bạn nhập sai yêu cầu vì nhập chữ ");
                                System.out.println("Bạn muốn nhập lại từ đầu hay trở về menu");
                                System.out.println("1:Nếu nhập lại từ đầu");
                                System.out.println("2:Nếu trở về menu");
                                int choice2 = Integer.parseInt(sc.nextLine());
                                if (choice2 == 1) {
                                    chonDanhMucDeSua();
                                } else {
                                    menu();
                                }

                            }
                            break;
                        case 2:
                            System.out.println("Bạn muốn sửa tên hay số tiền trong danh mục");

                            System.out.println("|1: nếu muốn sửa tên                        |");
                            System.out.println("|2: nếu muốn sửa số tiền                    |");
                            System.out.println("|Các nút còn lại back về menu               |");

                            try {
                                int danhmuccanchon = Integer.parseInt(sc.nextLine());
                                switch (danhmuccanchon) {
                                    case 1:
                                        danhmuc.lietkedanhmuc();
                                        System.out.println("Nhập tên danh mục cần sửa");
                                        String name1 = sc.nextLine();
                                        System.out.println("Nhập tên danh mục mới");
                                        String name2 = sc.nextLine();
                                        danhmuc.EditDanhMucCon(name1, name2);

                                        break;
                                    case 2:
                                        danhmuc.lietkedanhmuc();
                                        System.out.println("Nhập tên danh mục muốn sửa số tiền");
                                        name1 = sc.nextLine();
                                        System.out.println("Nhập số tiền mới");
                                        int newMoney = Integer.parseInt(sc.nextLine());
                                        danhmuc.changeMoneyDanhMuc(name1, newMoney);
                                        danhmuc.setTongsotien();

                                        break;

                                    default:
                                        menu();
                                }
                            }
                            catch (InputMismatchException e) {
                                menu();
                            }
                    }

                }
                catch(Exception e){
                    menu();
                }
    }
    public void chonloaigiaodich(){
        System.out.println("Nhập loại danh mục cần giao dịch ");
        System.out.println("1 Chi                            ");
        System.out.println("2 Thu                            ");
        System.out.println("Các nút còn lại thoát            ");
        try {
            int type = Integer.parseInt(sc.nextLine());
            while (type != 1 && type != 2) {
                System.out.println("Vui lòng nhập lại");
                type = Integer.parseInt(sc.nextLine());
            }
            if (type == 1) {
                giaodich(danhmucchi,1);
                System.out.println("Bạn có muốn giao dịch tiếp không");
                System.out.println("1:YES ");
                System.out.println("Các số còn lại:về menu ");
                try {
                    int choice1 = Integer.parseInt(sc.nextLine());
                    if (choice1 == 1) {
                        chonloaigiaodich();
                    } else {
                        menu();
                    }
                }catch(Exception e){
                    menu();
                }
            }
            else if (type == 2) {
                giaodich(danhmucthu,2);
                System.out.println("Bạn có muốn giao dịch tiếp không");
                System.out.println("1:YES ");
                System.out.println("Các số còn lại:về menu ");
                try {
                    int choice1 = Integer.parseInt(sc.nextLine());
                    if (choice1 == 1) {
                        chonloaigiaodich();
                    } else {
                        menu();
                    }
                }catch(Exception e){
                    menu();
                }
            } else {
                menu();
            }
        }
        catch(Exception e){
            menu();
        }
    }
    public void giaodich(ListDanhMuc danhmuc,int loaigd){
        {
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
            System.out.println("Nhập tên danh mục cần giao dịch ");
            danhmuc.lietkedanhmuc();
            String tendanhmuc = sc.nextLine();
            DanhMuc DanhMucCanGiaoDich = danhmuc.timdanhmuctheoten(danhmuc.getDsDanhMuc(), tendanhmuc);
            while (DanhMucCanGiaoDich == null) {
                System.out.println("tên danh mục không tồn tại, bạn muốn tiếp tục nhập hay quay về menu chính ");
                System.out.println("Nhấn 0 nếu tiếp tục nhập");
                System.out.println("Nhấn các nút còn lại để thoát");
                int choices = sc.nextInt();
                if (choices == 0) {
                    sc.nextLine();
                    System.out.println("Mời bạn nhập lại ");
                    tendanhmuc = sc.nextLine();
                    DanhMucCanGiaoDich = danhmuc.timdanhmuctheoten(danhmuc.getDsDanhMuc(), tendanhmuc);
                } else {
                    menu();
                }
            }

            GiaoDich Bill = new GiaoDich(date, noidung, sotien, DanhMucCanGiaoDich, loaigd);
            Bill.Chuyentienvaodanhmuc();
            DanhMuc danhmuccha = danhmuc.timdanhmuctheoten(danhmuc.getDsDanhMuc(), DanhMucCanGiaoDich.getName_danhmuccha());
            if (danhmuccha != null) {
                danhmuccha.setMoney();
                danhmuc.setTongsotien();
                dsgiaodich.addGD(Bill);
                System.out.println("Đã thêm vào danh mục " + DanhMucCanGiaoDich.gettendanhmuc());
                System.out.println("Giao dịch thành công");
                System.out.println("Nhập 1 nút bất kỳ để back trở về menu");
                sc.nextLine();
                menu();
            } else
                System.out.println("Không thể giao dịch với danh mục cấp 1!!");
                System.out.println("Nhập 1 nút bất kỳ để back trở về menu");
                sc.nextLine();
                menu();
        }
    }
    public void menu() {

        System.out.println("___________________________________________XIN NHẬP LỰA CHỌN___________________________________________");
        System.out.println("|1.THÊM DANH MỤC                                                                                      |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("|2.XÓA DANH MỤC                                                                                       |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("|3.SỬA DANH MỤC                                                                                       |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("|4.DISPLAY DANH MỤC                                                                                   |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("|5.GIAO DỊCH                                                                                          |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                              |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("|7.THỐNG KÊ GIAO DỊCH                                                                                 |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("|8.THOÁT                                                                                              |");
        System.out.println("|-----------------------------------------------------------------------------------------------------|");
        System.out.println("-Hãy nhập lựa chọn của bạn :");
                try {
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                            case 1:
                            ChonDanhMucDeThem();
                            break;

                            case 2:
                            ChonDanhMucdexoa();
                            break;
                            case 3:
                                chonDanhMucDeSua();
                                break;

                            case 4:

                            System.out.println("-------------------------DANH MỤC CHI LÀ---------------------------");
                            danhmucchi.lietkedanhmuc();
                            System.out.println("Tổng số tiền của danh mục thu là " + danhmucchi.getTongsotien());
                            System.out.println("-------------------------DANH MỤC THU LÀ---------------------------");
                            danhmucthu.lietkedanhmuc();
                            System.out.println("Tổng số tiền của danh mục thu là " + danhmucthu.getTongsotien());
                            System.out.println("NHẤN 1 NÚT BẤT KỲ ĐỂ BACK VỀ MENU");
                            sc.nextLine();

                            menu();
                            break;

                        case 5:
                           chonloaigiaodich();
                           break;
                        case 6:
                            System.out.println("-----------------------------------------------------Lịch sử giao dịch của bạn------------------------------------------------");
                            System.out.println("--------------------------------------------------------Thông tin giao dịch---------------------------------------------------");
                            for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                                System.out.println(lichsu.toStringGiaoDich());
                            }
                            System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                            sc.nextLine();
                            sc.nextLine();
                            menu();
                            break;
                        case 7:
                            System.out.println(" __________________________________________THỐNG KÊ__________________________________________");
                            System.out.println("|1.Thống kê theo tuần                                                                        |");
                            System.out.println("|2.Thống kê theo tháng                                                                       |");
                            System.out.println("|3.Thống kê theo năm                                                                         |");
                            System.out.println("|4.Thoát                                                                                     |");
                            System.out.println("|____________________________________________________________________________________________|");
                            int choose = sc.nextInt();
                            try {
                                switch (choose) {

                                    case 1:
                                        sc.nextLine();
                                        System.out.println("Nhap nam ");//thieu rang buoc

                                        int year_1 = sc.nextInt();
                                        while (year_1 < 1000 || year_1 > 2022) {
                                            System.out.println("VUI LONG NHAP LAI ");
                                            year_1 = sc.nextInt();
                                        }
                                        System.out.println("Nhap thang ");//thieu rang buoc
                                        int month_1 = sc.nextInt();

                                        thongke(month_1, year_1);
                                        System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                                        sc.nextLine();
                                        sc.nextLine();
                                        menu();
                                        break;

                                    case 2:
                                        sc.nextLine();
                                        System.out.println("Nhap nam ");//thieu rang buoc
                                        int year_2 = sc.nextInt();
                                        thongke(year_2);
                                        System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                                        sc.nextLine();
                                        sc.nextLine();
                                        menu();
                                        break;

                                    case 3:
                                        sc.nextLine();
                                        int currentYear = LocalDate.now().getYear();
                                        System.out.println("Số năm hiện tại là " + currentYear);
                                        System.out.println("Nhập số năm gần đây nhất mà bạn muốn xem dữ liệu từ trước năm " + currentYear);
                                        int year = Integer.parseInt(sc.nextLine());
                                        thongketheonam(year);
                                        System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                                        sc.nextLine();
                                        sc.nextLine();
                                        menu();
                                        break;
                                    default:
                                        System.out.println("Bạn đã thoát");


                                }
                            } catch (Exception E) {
                                System.out.println("Bạn đã thoát do nhập chữ");
                                menu();
                            }
                            break;


                        case 8:
                            System.out.println("HẸN GẶP LẠI!");

                    }
            }
            catch(Exception e){
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
            double total = danhmucchi[i] + danhmucthu[i];
            double percent = (total != 0) ? danhmucchi[i] * 1.0 / total : 0.0;
            System.out.println("số tiền chi chiếm " + percent * 100 + "% trên tổng số tiền");

            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        System.out.println("                                        Ta có dữ liệu thống kê như sau                                           ");
        System.out.println("- Tuần chi nhiều nhất là Tuần " + (Nhieunhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Nhieunhat(danhmucchi)]);
        System.out.println("- Tuần thu nhiều nhất là Tuần " + (Nhieunhat(danhmucthu) + 1)+" với số tiền là "+danhmucthu[Nhieunhat(danhmucthu)]);
        System.out.println("- Tuần chi ít nhất là Tuần " + (Itnhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Itnhat(danhmucchi)]);
        System.out.println("- Tuần thu ít nhất là Tuần " + (Itnhat(danhmucthu) + 1)+" với số tiền là "+danhmucthu[Itnhat(danhmucthu)]);
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
            double total = danhmucchi[i] + danhmucthu[i];
            double percent = (total != 0) ? danhmucchi[i] * 1.0 / total : 0.0;
            System.out.println(" Danh mục chi chiếm " + percent * 100 + "%");
            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        System.out.println(" Dữ liệu trong năm "+year +" :");
        System.out.println("|Tháng chi nhiều nhất là Tháng " + (Nhieunhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Nhieunhat(danhmucchi)]);
        System.out.println("|Tháng thu nhiều nhất là Tháng " + (Nhieunhat(danhmucthu) + 1)+" với số tiền là "+danhmucthu[Nhieunhat(danhmucthu)]);
        System.out.println("|Tháng chi ít nhất là Tháng " + (Itnhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Itnhat(danhmucchi)]);
        System.out.println("|Tháng thu ít nhất là Tháng " + (Itnhat(danhmucthu) + 1)+" với số tiền là "+danhmucthu[Itnhat(danhmucthu)]);
    }


    public void thongketheonam(int soNamGanDay){
        int[] danhmucthu = new int[soNamGanDay];
        int[] danhmucchi = new int[soNamGanDay];
        for (int i = 0; i < soNamGanDay; i++) {
            danhmucthu[i] = 0;
            danhmucchi[i] = 0;
        }
        int currentYear = LocalDate.now().getYear();
        for (GiaoDich gd : dsgiaodich.getDsGD()) {
            int transactionYear = gd.getNgayGiaoDich().getnam();
            int yearIndex = soNamGanDay - (currentYear - transactionYear) - 1;
            if (yearIndex >= 0 && yearIndex < soNamGanDay) {
                if (gd.getLoaigiaodich().equals("Giao dịch chi")) {
                    danhmucchi[yearIndex] += gd.getsotien();
                } else if (gd.getLoaigiaodich().equals("Giao dịch thu")) {
                    danhmucthu[yearIndex] += gd.getsotien();
                }
            }
        }
        for (int i = soNamGanDay-1; i >=0; i--) {
            System.out.println(" - Năm " + (currentYear -  i) + " :");
            System.out.println(" +Số tiền chi: " + danhmucchi[i]);
            System.out.println(" +Số tiền thu được: " + danhmucthu[i]);
            double total = danhmucchi[i] + danhmucthu[i];
            double percent = (total != 0) ? danhmucchi[i] * 1.0 / total : 0.0;
            System.out.println(" Danh mục chi chiếm " + percent * 100 + "%");
            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        System.out.println(" Dữ liệu từ "+soNamGanDay+" gần đây nhất ");
        System.out.println("-Năm chi nhiều nhất trong những năm gần đây là " + (Nhieunhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Nhieunhat(danhmucchi)]);
        System.out.println("-Năm thu nhiều trong những năm gần đây là " + (Nhieunhat(danhmucthu) + 1)+" với số tiền là "+danhmucchi[Nhieunhat(danhmucthu)]);
        System.out.println("-Năm chi ít nhất trong những năm gần đây là " + (Itnhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Itnhat(danhmucchi)]);
        System.out.println("-Năm thu ít nhất năm trong những năm gần đây là " + (Itnhat(danhmucthu) + 1)+" với số tiền là "+danhmucthu[Itnhat(danhmucthu)]);
    }
    public double TongSoTientrongdanhmuc(int [] array){
        int sum=0;
        for(int i=0;i<array.length;i++){
          sum+=array[i];
        }
        return sum;
    }
    public int Nhieunhat(int [] arr){
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
    public int Itnhat(int [] arr){
        int minIndex = 0;
        int minVal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
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

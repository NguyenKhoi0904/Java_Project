package com.bt.quanlythuchicanhan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyDanhMucPro extends QuanLyDanhMuc{
    private ArrayList<gioiHanNganSach> GioiHan;

    public ArrayList<gioiHanNganSach> getGioiHan() {
        return GioiHan;
    }

    public void setGioiHan(ArrayList<gioiHanNganSach> gioiHan) {
        GioiHan = gioiHan;

    }

    public QuanLyDanhMucPro() { // tự đăng ký
        super();
        this.GioiHan = new ArrayList();
    }
    public void ThayDoiThangHienTai(){

    }

    @Override
    public void ChonDanhMucDeThem() { // abstract
        Scanner sc = new Scanner(System.in);
        System.out.println("Số lần thêm danh mục của bạn còn " + getSoDanhMuc());
        int SoDanhMucDaThem=10-getSoDanhMuc();
        if (SoDanhMucDaThem < 10) {
            System.out.println("Chọn loại danh mục ");
            System.out.println("Hãy nhập số ");
            System.out.println("1:CHI ");
            System.out.println("2:THU ");

            Boolean flat = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
            while (flat) {
                try {
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            flat = false;
                            ThemDanhMuc(this.getDanhMucChi(), "DMC");
                            break;
                        case 2:
                            flat = false;
                            ThemDanhMuc(this.getDanhMucThu(), "DMT");
                        default:
                            flat=true;
                            System.out.println("Lựa chọn không hợp lệ");
                            System.out.println("Hãy nhập số ");
                            System.out.println("1:CHI ");
                            System.out.println("2:THU ");

                    }
                } catch (NumberFormatException e) {
                    System.out.println("Xin vui lòng nhập lại");
                    System.out.println("Hãy nhập số ");
                    System.out.println("1:CHI ");
                    System.out.println("2:THU ");
                }
            }
        } else {
            System.out.println("Số Danh Mục bạn đã đạt giới hạn");
            System.out.println("Bạn muốn xóa danh mục hay back về menu");
            System.out.println("1:Xóa");
            System.out.println("các nút còn lại:Về menu");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    ChonDanhMucdexoa();
                    System.out.println("Bạn có muốn xóa tiếp danh mục nào nữa không");
                    System.out.println("1:YES ");
                    System.out.println("Các số còn lại:NO ");
                    int choice1 = Integer.parseInt(sc.nextLine());
                    if (choice1 == 1) {
                        ChonDanhMucdexoa();
                    }

                }
            } catch (NumberFormatException e) {
                //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
            }
        }


    }

    @Override
    public void chonloaigiaodich(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập loại danh mục cần giao dịch ");
        System.out.println("1 Chi                            ");
        System.out.println("2 Thu                            ");
        System.out.println("Các nút còn lại thoát            ");
        Boolean flat = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
        try {
            int type = Integer.parseInt(sc.nextLine());
            while (type != 1 && type != 2) {
                System.out.println("Vui lòng nhập lại");
                type = Integer.parseInt(sc.nextLine());
            }
            if (type == 1) {
                giaodich(getDanhMucChi(), 1);
                System.out.println("Bạn có muốn giao dịch tiếp không");
                System.out.println("1:YES ");
                System.out.println("Các số còn lại:về menu ");
                try {
                    int choice1 = Integer.parseInt(sc.nextLine());
                    if (choice1 == 1) {
                        chonloaigiaodich();
                    }
                } catch (NumberFormatException e) {
                    //KHÔNG CẦN XỬ LÝ Ở ĐÂY
                }
            } else if (type == 2) {
                giaodich(getDanhMucThu(), 2);
                System.out.println("Bạn có muốn giao dịch tiếp không");
                System.out.println("1:YES ");
                System.out.println("Các số còn lại:về menu ");
                try {
                    int choice1 = Integer.parseInt(sc.nextLine());
                    if (choice1 == 1) {
                        chonloaigiaodich();
                    }
                } catch (NumberFormatException e) {
                    return;
                }
            }
        } catch (NumberFormatException e) {
            return;
        }
    }
    @Override
    public void giaodich(ListDanhMuc danhmuc, int loaigd) {// abstract
        gioiHanNganSach GioiHanHienTai = null;
        Scanner sc = new Scanner(System.in);
        Boolean find = false;
        for (gioiHanNganSach ngansachmoithang : GioiHan) {
            if (ngansachmoithang.getthang() == getdateToDay().getthang() && ngansachmoithang.getnam() == getdateToDay().getnam()) {
                GioiHanHienTai = ngansachmoithang;
                find = true;
            }
        }

        if (find == false) {
            System.out.println("Thán hiện tại của bạn đang không có giới hạn ngân sách,bạn có muốn tạo ngân sách mới không?");
            System.out.println("1.Có");
            System.out.println("2.Không");
            String test = sc.nextLine();
            if (!isInteger(test) || Integer.parseInt(test) > 2 || Integer.parseInt(test) < 3) {
                System.out.println("Bạn nhập không hợp lệ!!");
                System.out.println("Hãy nhập lại!!");
                test = sc.nextLine();
            }
            int choice = Integer.parseInt(test);
            if (choice == 1) {
                TaoGioiHanNganSach();
                find=true;
            }
        }
        System.out.println("Bạn muốn giao dịch hôm nay hay hôm qua hay tùy chỉnh");
        System.out.println("1.Hôm nay");
        System.out.println("2.Hôm qua");
        System.out.println("3.Tùy chỉnh");
        System.out.println("Nhập phím bất kỳ để trở về menu;");
        Boolean flat = true;
        int day;
        int month;
        int year;
        NgayThangNam date = null;
        while (flat) {
            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {//Hôm nay
                    flat = false;
                    day = LocalDate.now().getDayOfMonth();
                    month = LocalDate.now().getMonthValue();
                    year = LocalDate.now().getYear();
                    date = new NgayThangNam(day, month, year);

                } else if (choice == 2) {//Hôm qua
                    flat = false;
                    day = LocalDate.now().getDayOfMonth();
                    month = LocalDate.now().getMonthValue();
                    year = LocalDate.now().getYear();
                    if (day == 1) {
                        day = 30;//ngay mac dinh tren 30
                        month = LocalDate.now().getMonthValue() - 1;
                    } else if (day == 1 && month == 1) {
                        day = 31;//ngay mac dinh tren 30
                        month = 12;
                        year -= 1;
                    }
                    //Tạm thời chưa xử lý năm nhuận
                    date = new NgayThangNam(day, month, year);
                } else if (choice == 3) {//Tùy chỉnh
                    System.out.println("Nhập ngày giao dịch");
                    String ngay = sc.nextLine();
                    while (!Kiemtrangayhople(ngay)) {
                        System.out.println("Ngày không hợp lệ, mời nhập lại!!!!!");
                        ngay = sc.nextLine();
                    }

                    System.out.println("Nhập tháng giao dịch");
                    String thang = sc.nextLine();
                    while (!Kiemtrathanghople(thang)) {
                        System.out.println("Tháng không hợp lệ, mời nhập lại!!!!!");
                        thang = sc.nextLine();
                    }
                    System.out.println("Nhập năm giao dịch");
                    String nam = sc.nextLine();
                    while (!Kiemtranamhople(nam)) {
                        System.out.println("Năm không hợp lệ, mời nhập lại!!!!!!");
                        nam = sc.nextLine();
                    }
                    day = Integer.parseInt(ngay);
                    month = Integer.parseInt(thang);
                    year = Integer.parseInt(nam);
                    date = new NgayThangNam(day, month, year);
                } else {
                    return;
                }
            } catch (NumberFormatException e) {
                return;
            }

                System.out.println("Nhập nội dung giao dịch");
                String noidung = sc.nextLine();
                System.out.println("Nhập số tiền cần giao dịch");
                String test = sc.nextLine();

                while (!isInteger(test)) {
                    System.out.println("Hãy nhập số!!!!!!!");
                    test = sc.nextLine();
                }

                int sotien = Integer.parseInt(test);
                if(find==true){
                   GioiHanHienTai.kiemtraNganSach(sotien,getDanhMucChi().getTongsotien());
                   System.out.print("Bạn có muốn giao dịch tiếp không ?");
                    System.out.print("1.Có");
                    System.out.print("Các nút còn lại:thoát");


                    int choice=Integer.parseInt(sc.nextLine());
                    if(choice!=1){
                        return;
                    }

                }
                // tim thong tin danh muc cha
                System.out.println("Bạn muốn giao dịch với nhóm nào");
                int i = 1;
                DanhMuc DanhMucCha = null;
                for (DanhMuc list : danhmuc.getDsDanhMuc()) {

                    System.out.println("Nhóm " + i + ": " + list.gettendanhmuc());
                    int x = 1;
                    for (DanhMuc danhmuccon : list.getdanhsachdanhmuccon()) {

                        System.out.println("     " + i + "." + x + " " + danhmuccon.gettendanhmuc());
                        x++;
                    }
                    i++;
                }
                int type;
                Boolean flat1 = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                while (flat1) {
                    try {
                        flat1 = false;
                        System.out.println("Chọn Nhóm :");
                        type = Integer.parseInt(sc.nextLine());
                        flat1 = false;
                        DanhMucCha = danhmuc.getDsDanhMuc().get(type - 1);
                    } catch (IndexOutOfBoundsException x) {
                        System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                        flat1 = true;
                    } catch (NumberFormatException x) {
                        System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                        flat1 = true;

                    }
                }
                DanhMuc DanhMucCanGiaoDich = null;
                if (DanhMucCha.getdanhsachdanhmuccon().size() > 0) {
                    Boolean flat2 = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                    while (flat2) {
                        int type_1;
                        try {
                            System.out.println("Trong nhóm bạn chọn bao gồm : ");
                            int temp = 1;
                            for (DanhMuc list : DanhMucCha.getdanhsachdanhmuccon()) {
                                System.out.println(temp + ": " + list.gettendanhmuc());
                                temp++;


                                System.out.println("Vui lòng nhập vị trí danh mục trong nhóm này");
                                type_1 = Integer.parseInt(sc.nextLine());
                                flat2 = false;
                                DanhMucCanGiaoDich = DanhMucCha.getdanhsachdanhmuccon().get(type_1 - 1);
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                            flat2 = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                            flat2 = true;

                        }
                    }
                    GiaoDich Bill = new GiaoDich(date, noidung, sotien, DanhMucCanGiaoDich, loaigd);
                    DanhMuc danhmuccha = danhmuc.timdanhmuctheoten(danhmuc.getDsDanhMuc(), DanhMucCanGiaoDich.getName_danhmuccha());//Giao Dich chi thuc hien voi danh muc cap 2
                    if (danhmuccha != null) {
                        Bill.Chuyentienvaodanhmuc();
                        danhmuccha.setMoney();
                        danhmuc.setTongsotien();
                        getDsgiaodich().addGD(Bill);
                        System.out.println("Đã thêm vào danh mục " + DanhMucCanGiaoDich.gettendanhmuc());
                        System.out.println("Giao dịch thành công");
                        return;
                    }
                } else {
                    System.out.println("Danh mục bạn chọn đang rỗng!!!!!( không tồn tại danh mục cấp 1 )");
                    return;
                }
            }
        }

    public void LapNganSachVaNhacNho(){

    }
    public void SoSanhCacKhoanThuChiTheoThoiGian(){

    }
    public void XuatBaoCaoRaFile(){

    }
   public void SuaGioiHanNganSach(){ // sửa giới hạn ngân sách
       Scanner sc = new Scanner(System.in);
       System.out.println("Bạn muốn sửa giới hạn ngân sách cho tháng này hay tùy chỉnh?!");
       System.out.println("1.Tháng này ");
       System.out.println("2.Tùy chỉnh");
       System.out.println("Các nút còn lại: về menu");
       int month = 12;
       int year = 2023;
       int choice = Integer.parseInt(sc.nextLine());
       try {
           if (choice == 1) {//thang hien tai
               month = LocalDate.now().getMonthValue();
               year = LocalDate.now().getYear();
           } else if (choice == 2) {//tuy chinh
               System.out.println("Nhập tháng để sửa");
               String thang = sc.nextLine();
               while (!Kiemtrathanghople(thang)) {
                   System.out.println("Tháng không hợp lệ, mời nhập lại!!!!!");
                   thang = sc.nextLine();
               }
               System.out.println("Nhập năm để sửa ");
               String nam = sc.nextLine();
               while (!Kiemtranamhople(nam)) {
                   System.out.println("Năm không hợp lệ, mời nhập lại!!!!!!");
                   nam = sc.nextLine();
               }
               month = Integer.parseInt(thang);
               year = Integer.parseInt(nam);
           } else {
               return;
           }
            System.out.println("1.Sửa phần trăm giới hạnn");
            System.out.println("2.sửa số tiền giới hạn");

            String test = sc.nextLine();
            while(!isInteger(test) || Integer.parseInt(test)>2 || Integer.parseInt(test)<1){
                if(!isInteger(test)) {
                    System.out.println("Bạn nhập không hợp lệ!!");
                    System.out.println("Hãy nhập lại!!");
                    test = sc.nextLine();
                }
                else{
                    System.out.println("Bạn nhập quá 2 hoặc bé hơn 1!!");
                    System.out.println("Hãy nhập lại!!");
                    test = sc.nextLine();
                }
            }

           int luachon = Integer.parseInt(test);
            if(luachon==1) {
            System.out.println("Nhập phần trăm !!");
                test = sc.nextLine();
                while(!isInteger(test) || Integer.parseInt(test)>100 || Integer.parseInt(test)<0){
                    if(!isInteger(test)) {
                        System.out.println("Bạn nhập không hợp lệ!!");
                        System.out.println("Hãy nhập lại!!");
                        test = sc.nextLine();
                    }
                    else{
                        System.out.println("Bạn nhập quá 100 hoặc bé hơn 0!!");
                        System.out.println("Hãy nhập lại!!");
                        test = sc.nextLine();
                    }
                }
                double percent = Double.parseDouble(test);
                for (gioiHanNganSach ketqua : getGioiHan()) {
                    if (ketqua.getnam() == year && ketqua.getthang() == month) {
                        ketqua.setPhanTramSoTien(percent);
                    }
                }
            }
            else{
                System.out.println("Nhập phần Giới Hạn số tiền !!");
                test = sc.nextLine();
                while(!isInteger(test)){

                        System.out.println("Bạn nhập không hợp lệ!!");
                        System.out.println("Hãy nhập lại!!");
                        test = sc.nextLine();

                }
                int soTienMoi = Integer.parseInt(test);
                for (gioiHanNganSach ketqua : getGioiHan()) {
                    if (ketqua.getnam() == year && ketqua.getthang() == month) {
                        ketqua.setSotien(soTienMoi);
                    }
                }
            }
       } catch (NumberFormatException e) {
           return;
       }
   }

    public void XoaGioiHanNganSach() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn xóa giới hạn ngân sách cho tháng này hay tùy chỉnh?!");
        System.out.println("1.Tháng này ");
        System.out.println("2.Tùy chỉnh");
        System.out.println("Các nút còn lại: về menu");
        int month = 12;
        int year = 2023;

        int choice = Integer.parseInt(sc.nextLine());
        try {
            if (choice == 1) {//thang hien tai
                month = LocalDate.now().getMonthValue();
                year = LocalDate.now().getYear();
            } else if (choice == 2) {//tuy chinh
                System.out.println("Nhập tháng giao dịch");
                String thang = sc.nextLine();
                while (!Kiemtrathanghople(thang)) {
                    System.out.println("Tháng không hợp lệ, mời nhập lại!!!!!");
                    thang = sc.nextLine();
                }
                System.out.println("Nhập năm giao dịch");
                String nam = sc.nextLine();
                while (!Kiemtranamhople(nam)) {
                    System.out.println("Năm không hợp lệ, mời nhập lại!!!!!!");
                    nam = sc.nextLine();
                }
                month = Integer.parseInt(thang);
                year = Integer.parseInt(nam);
            } else {
                return;
            }

            for (gioiHanNganSach ketqua : getGioiHan()) {
                if (ketqua.getnam() == year && ketqua.getthang() == month) {
                    this.getGioiHan().remove(ketqua);
                }

            }
        } catch (NumberFormatException e) {
            return;
        }
    }
        public void TaoGioiHanNganSach () {
            Scanner sc = new Scanner(System.in);
            System.out.println("Bạn muốn tạo giới hạn ngân sách cho tháng này hay tùy chỉnh?!");
            System.out.println("1.Tháng này ");
            System.out.println("2.Tùy chỉnh");
            System.out.println("Các nút còn lại: về menu");

            int month = 12;
            int year = 2023;

            int choice = Integer.parseInt(sc.nextLine());
            try {
                if (choice == 1) {//thang hien tai
                    month = LocalDate.now().getMonthValue();
                    year = LocalDate.now().getYear();
                } else if (choice == 2) {//tuy chinh
                    System.out.println("Nhập tháng giao dịch");
                    String thang = sc.nextLine();
                    while (!Kiemtrathanghople(thang)) {
                        System.out.println("Tháng không hợp lệ, mời nhập lại!!!!!");
                        thang = sc.nextLine();
                    }
                    System.out.println("Nhập năm giao dịch");
                    String nam = sc.nextLine();
                    while (!Kiemtranamhople(nam)) {
                        System.out.println("Năm không hợp lệ, mời nhập lại!!!!!!");
                        nam = sc.nextLine();
                    }
                    month = Integer.parseInt(thang);
                    year = Integer.parseInt(nam);
                } else {
                    return;
                }
                System.out.println("Nhập số tiền");
                String test = sc.nextLine();
                while (!QuanLyDanhMuc.isInteger(test)) {
                    sc.nextLine();
                }
                int sotien = Integer.parseInt(test);
                System.out.println("Nhập phần trăm ( lưu ý: phần trăm phải trên 0 và dưới 100");
                test = sc.nextLine();
                while (!QuanLyDanhMuc.isInteger(test)) {
                    test = sc.nextLine();
                }
                double percent = Double.parseDouble(test);
                gioiHanNganSach NganSach = new gioiHanNganSach(month, year, sotien, percent);
                this.getGioiHan().add(NganSach);
            } catch (NumberFormatException e) {
                return;
            }
        }
    }






package com.bt.quanlythuchicanhan;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyDanhMucPro extends QuanLyDanhMuc implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<NganSach> DsGioiHanChi ;
    public void setDanhSachNganSach(ArrayList<NganSach> ns){
        DsGioiHanChi=ns;
    }
    public ArrayList<NganSach> getDanhSachNganSach (){
        return this.DsGioiHanChi;
    }

    public QuanLyDanhMucPro() { // tự đăng ký
        super();
        this.setSoDanhMuc(12);// so lan tao danh muc cua pro user
        DsGioiHanChi = new ArrayList<>();
}
    @Override
    public void ChonDanhMucDeThem() { // abstract
        Scanner sc = new Scanner(System.in);
        System.out.println("Số lần thêm danh mục của bạn còn " + getSoDanhMuc());
        int SoDanhMucDaThem = 10 - getSoDanhMuc();// so danh muc toi da la 10
        if (SoDanhMucDaThem < 10) {
            System.out.println("Chọn loại danh mục ");
            System.out.println("Hãy nhập số ");
            System.out.println("1:CHI ");
            System.out.println("2:THU ");
            System.out.println("Các nút còn lại thoát ");
                try {
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:

                            ThemDanhMuc(this.getDanhMucChi(), "DMC");
                            break;
                        case 2:

                            ThemDanhMuc(this.getDanhMucThu(), "DMT");
                            break;
                        default:
                            return;

                    }
                } catch (NumberFormatException e) {
                  return;
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
                }
                    else{
                        return;
                    }


            } catch (NumberFormatException e) {
                return;
            }
        }


    }

    @Override
    public void chonloaigiaodich() {
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
            } else if (type == 2) {
                giaodich(getDanhMucThu(), 2);
            }else{
               return;
            }
        } catch (NumberFormatException e) {
            //KHÔNG CẦN XỬ LÝ Ở ĐÂY
        }
    }

    @Override
    public void giaodich(ListDanhMuc danhmuc, int loaigd) {// abstract
        // dat lai ngay hom nay
        setDateToDay(new NgayThangNam(getdateToDay().getngay(), getdateToDay().getthang(), getdateToDay().getnam()));
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn giao dịch hôm nay hay hôm qua hay tùy chỉnh");
        System.out.println("1.Hôm nay");
        System.out.println("2.Hôm qua");
        System.out.println("3.Tùy chỉnh");
        System.out.println("Nhập phím bất kỳ để trở về menu;");
        Boolean flat = true;
        int day = getdateToDay().getngay();
        int month = getdateToDay().getthang();
        int year = getdateToDay().getnam();
        NgayThangNam date = null;
        while (flat) {
            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {//Hôm nay
                    flat = false;
                    day = getdateToDay().getngay();
                    month = getdateToDay().getthang();
                    year = getdateToDay().getnam();
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
            /*
            boolean flag = true;
            while (!GioiHanNganSach.Kiemtrangansachcoduoctaochua(year, month) && flag) {
                System.out.println("Tháng hiện tại của bạn đang không có giới hạn ngân sách,bạn có muốn tạo ngân sách mới không?");
                System.out.println("1.Có");
                System.out.println("2.Không");
                String test = sc.nextLine();
                if (!isInteger(test) || Integer.parseInt(test) < 1 || Integer.parseInt(test) >2) {
                    System.out.println("Bạn nhập không hợp lệ!!");
                    System.out.println("Hãy nhập lại!!");
                    test = sc.nextLine();
                }
                int choice = Integer.parseInt(test);
                if (choice == 1) {
                    TaoGioiHanNganSach();
                    if(!GioiHanNganSach.Kiemtrangansachcoduoctaochua(year, month)){
                        flag=true;
                    }
                    flag=false;
                }
                if (choice == 2) {
                    flag = false;
                }
            }
        */
            System.out.println("Nhập nội dung giao dịch");
            String noidung = sc.nextLine();
            System.out.println("Nhập số tiền cần giao dịch");
            String test = sc.nextLine();

            while (!isInteger(test)) {
                System.out.println("Hãy nhập số!!!!!!!");
                test = sc.nextLine();
            }
            int sotien = Integer.parseInt(test);
            System.out.println("Bạn muốn giao dịch với danh mục cấp 1 hay cấp 2");
            System.out.println("1:Cấp 1");
            System.out.println("2:Cấp 2");
            System.out.println("Xin hãy nhập lựa chọn");
            int luachon1 = Integer.parseInt(sc.nextLine());
            // Cap 1
            if (luachon1 == 1) {
                int i = 1;
                for (DanhMuc list : danhmuc.getDsDanhMuc()) {
                    System.out.println(i + ": " + list.gettendanhmuc());
                    int x = 1;
                    for (DanhMuc danhmuccon : list.getdanhsachdanhmuccon()) {

                        System.out.println(i + "." + x + " " + danhmuccon.gettendanhmuc());
                        x++;
                    }
                    i++;
                }
                System.out.println("Chọn danh mục cần giao dịch");
                DanhMuc danhmuccap1 = null;
                int luachon;
                boolean flag = true;
                while (flag) {
                    try {
                        luachon = Integer.parseInt(sc.nextLine());
                        flag = false;
                        danhmuccap1 = danhmuc.getDsDanhMuc().get(luachon - 1);

                    } catch (NumberFormatException e) {
                        System.out.println("Bạn nhập không hợp lệ");
                        flag = true;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Vị trí bạn nhập không hợp lệ");
                        flag = true;
                    }
                }
                /// kiem tra ngan sach khuc nay
                NganSach ngansachcankiemtra = null;
                for (NganSach temp : getDanhSachNganSach()) { // ktra xem ngan sach co duoc tao chua
                    if (temp.danhmucchi() == danhmuccap1 && temp.Kiemtrangansachcoduoctaochua(year, month, danhmuccap1)) {
                        ngansachcankiemtra = temp;
                    }
                }
                if (ngansachcankiemtra != null) {
                    int tongtienthangnay = 0;
                    for (GiaoDich gd : getDsgiaodich().getDsGD()) {
                        if (gd.getNgayGiaoDich().getthang() == month && gd.getNgayGiaoDich().getnam() == year && gd.getLoaigiaodich().equals("Giao dịch chi") && (gd.getTendanhmuc()==danhmuccap1 || gd.getTendanhmuc().getName_danhmuccha().equals(danhmuccap1.gettendanhmuc()) )){
                            tongtienthangnay += gd.getsotien();
                        }
                    }

                    double sophantramneugiaodich = ngansachcankiemtra.phantramsudung(sotien, tongtienthangnay);
                    System.out.println("Nếu giao dịch thì số tiền đã chi chiếm  " + sophantramneugiaodich * 100 + "% số tiền đã được giới hạn");
                    System.out.println("Số tiền đã giới hạn của bạn đang là " + ngansachcankiemtra.getsotien() + "đ");
                    System.out.println("Và phần trăm bạn đã giới hạn của bạn đang là " + ngansachcankiemtra.getPhanTram() + "%");
                    if (sophantramneugiaodich * 100 > ngansachcankiemtra.getPhanTram()) {
                        System.out.println("Bạn có muốn giao dịch tiếp không ?");
                        System.out.println("1.Có");
                        System.out.println("Các nút còn lại:thoát");
                        try {
                            int choice = Integer.parseInt(sc.nextLine());
                            if (choice != 1) {
                                return;
                            }
                        } catch (NumberFormatException e) {
                            return;
                        }
                    }
                }
                    GiaoDich Bill = new GiaoDich(date, noidung, sotien, danhmuccap1, loaigd);
                    Bill.Chuyentienvaodanhmuc();
                    danhmuccap1.setTienDanhMucCap1(sotien);
                    danhmuc.setTongsotien();
                    getDsgiaodich().addGD(Bill);
                    System.out.println("Đã thêm vào danh mục " + danhmuccap1.gettendanhmuc());
                    System.out.println("Giao dịch thành công");
                    System.out.println("Bạn có muốn giao dịch tiếp không");
                    System.out.println("1:YES ");
                    System.out.println("Các nút còn lại:về menu ");
                    try {
                        int choice1 = Integer.parseInt(sc.nextLine());
                        if (choice1 == 1) {
                            chonloaigiaodich();
                        } else {
                            return;
                        }
                    } catch (NumberFormatException e) {
                        //KHÔNG CẦN XỬ LÝ GÌ HẾT
                    }
                    return;
                }


            /*
            if (GioiHanNganSach.getbatTat() && loaigd==1 && GioiHanNganSach.Kiemtrangansachcoduoctaochua(getdateToDay().getnam(), getdateToDay().getthang())) { // Chuc nang gioi han dang duoc su dung
                int sotiencuathang = 0;
                for (GiaoDich gd : getDsgiaodich().getDsGD()) {
                    if (gd.getNgayGiaoDich().getthang() == month && gd.getNgayGiaoDich().getnam() == year && gd.getLoaigiaodich().equals("Giao dịch chi")) {
                        sotiencuathang += gd.getsotien();
                    }
                }
                double sophantramneugiaodich = getGioiHan().phantramsudung(sotien, sotiencuathang);
                System.out.println("Nếu giao dịch thì số tiền đã chi chiếm  " + sophantramneugiaodich *100 +"% số tiền đã được giới hạn");
                System.out.println("Và phần trăm bạn đã giới hạn của bạn đang là "+this.GioiHanNganSach.getPhanTram()+"%");
                if (sophantramneugiaodich*100 > getGioiHan().getPhanTram()) {
                    System.out.println("Bạn có muốn giao dịch tiếp không ?");
                    System.out.println("1.Có");
                    System.out.println("Các nút còn lại:thoát");
                    try {
                        int choice = Integer.parseInt(sc.nextLine());
                        if (choice != 1) {
                            return;
                        }
                    } catch (NumberFormatException e) {
                        return;
                    }
                }}

             */

                // tim thong tin danh muc cha
             else if (luachon1 == 2) {// giaodich voi danh muc cap 2
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
                            }
                            System.out.println("Vui lòng nhập vị trí danh mục trong nhóm này");
                            type_1 = Integer.parseInt(sc.nextLine());
                            flat2 = false;
                            DanhMucCanGiaoDich = DanhMucCha.getdanhsachdanhmuccon().get(type_1 - 1);

                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                            flat2 = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                            flat2 = true;

                        }
                    }
                    //Tu danh muc cap 2 truy xuat nguoc lai danh muc c1
                    DanhMuc danhmuccap1 = getDanhMucChi().timdanhmuctheoten(getDanhMucChi().getDsDanhMuc(), DanhMucCanGiaoDich.getName_danhmuccha());
                    // dung chuc nang ngan sách tại đây;
                    NganSach ngansachcankiemtra = null;
                    for (NganSach temp : getDanhSachNganSach()) { // ktra xem ngan sach co duoc tao chua
                        if (temp.danhmucchi().gettendanhmuc().equals(DanhMucCanGiaoDich.getName_danhmuccha()) && temp.Kiemtrangansachcoduoctaochua(year, month, danhmuccap1) || temp.danhmucchi() == DanhMucCanGiaoDich && temp.Kiemtrangansachcoduoctaochua(year, month, DanhMucCanGiaoDich)) {
                            ngansachcankiemtra = temp;
                        }
                    }
                    int tongtienthangnay = 0;
                    for (GiaoDich gd : getDsgiaodich().getDsGD()) {
                        if (gd.getNgayGiaoDich().getthang() == month && gd.getNgayGiaoDich().getnam() == year && gd.getLoaigiaodich().equals("Giao dịch chi") && (gd.getTendanhmuc().getName_danhmuccha().equals(danhmuccap1.gettendanhmuc()) || gd.getTendanhmuc()==danhmuccap1)) {
                            tongtienthangnay += gd.getsotien();
                        }
                    }
                    if(ngansachcankiemtra!=null) {
                        double sophantramneugiaodich = ngansachcankiemtra.phantramsudung(sotien, tongtienthangnay);
                        System.out.println("Nếu giao dịch thì số tiền đã chi chiếm  " + sophantramneugiaodich * 100 + "% số tiền đã được giới hạn");
                        System.out.println("Số tiền đã giới hạn của bạn đang là " + ngansachcankiemtra.getsotien() + "đ");
                        System.out.println("Và phần trăm bạn đã giới hạn của bạn đang là " + ngansachcankiemtra.getPhanTram() + "%");
                        if (sophantramneugiaodich * 100 > ngansachcankiemtra.getPhanTram()) {
                            System.out.println("Bạn có muốn giao dịch tiếp không ?");
                            System.out.println("1.Có");
                            System.out.println("Các nút còn lại:thoát");
                            try {
                                int choice = Integer.parseInt(sc.nextLine());
                                if (choice != 1) {
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                return;
                            }
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
                            System.out.println("Bạn có muốn giao dịch tiếp không");
                            System.out.println("1:YES ");
                            System.out.println("Các nút còn lại:về menu ");
                            try {
                                int choice1 = Integer.parseInt(sc.nextLine());
                                if (choice1 == 1) {
                                    chonloaigiaodich();
                                } else {
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                //KHÔNG CẦN XỬ LÝ Ở ĐÂY
                            }
                            return;
                        }
                    } else {
                        System.out.println("Danh mục bạn chọn đang rỗng!!!!!( không tồn tại danh mục cấp 2 )");
                        return;
                    }
                }
            }
        }


    public void LapNganSachVaNhacNho() {// Chuc nang ngan sach
        Scanner sc=new Scanner(System.in);
        System.out.println("__________________________________________________________________XIN NHẬP LỰA CHỌN__________________________________________________________");
        System.out.println("|1.TẠO NGÂN SÁCH                                                                                                                            |");
        System.out.println("|2.SỬA NGÂN SÁCH                                                                                                                            |");
        System.out.println("|3.XEM BIỂU ĐỒ PHẦN TRĂM ĐÃ SỬ DỤNG CỦA THÁNG HIỆN TẠI                                                                                      |");
        System.out.println("|CÁC NÚT CÒN LẠI TRỞ VỀ MENU                                                                                                                |");
        System.out.println("|___________________________________________________________________________________________________________________________________________|");
        try{
            int choice = Integer.parseInt(sc.nextLine());
            if(choice==1){
                TaoGioiHanNganSach();
            }
            else if(choice ==2 ){
                SuaGioiHanNganSach();
            }
            else if(choice ==3){
                int temp=1;
                for (DanhMuc list : getDanhMucChi().getDsDanhMuc()) {
                    System.out.println(temp + ": " + list.gettendanhmuc());
                    temp++;
                }
                System.out.println("Vui lòng nhập vị trí danh mục trong này");
                int type_1 = Integer.parseInt(sc.nextLine());
                DanhMuc danhmuccap1=getDanhMucChi().getDsDanhMuc().get(type_1-1);
                int tongtienthangnay=0;
                for(GiaoDich gd:getDsgiaodich().getDsGD()){
                    if(gd.getNgayGiaoDich().getthang()==getdateToDay().getthang() && (gd.getTendanhmuc()==danhmuccap1 || gd.getTendanhmuc().getName_danhmuccha().equals(danhmuccap1.gettendanhmuc()))){ //kieam tra thang kiem tra ngay
                        tongtienthangnay+=gd.getsotien();
                    }
                }
                NganSach GioiHanNganSach=null;
                for (NganSach temp1 : getDanhSachNganSach()) { // ktra xem ngan sach co duoc tao chua
                    if(temp1.danhmucchi()==danhmuccap1){
                       GioiHanNganSach=temp1;
                    }
                }
                try{
                    if(GioiHanNganSach != null) {
                        GioiHanNganSach.hienThiBieuDoNganSach(tongtienthangnay);
                    }
                    else{
                        System.out.println("Không có dữ liệu tháng này");
                    }

                System.out.println("Nhấn 1 nút bất kỳ để quay về menu");
                sc.nextLine();
                }
                catch(NullPointerException e){
                    System.out.println("Rỗng");
                }
            }
        }
        catch(NumberFormatException e){
            //KHÔNG CẦN XỬ LÝ Ở ĐÂY
        }
    }



    public void SuaGioiHanNganSach() { // sửa giới hạn ngân sách
        Scanner sc = new Scanner(System.in);
        System.out.println("__________________________________________________________________XIN NHẬP LỰA CHỌN__________________________________________________________");
        System.out.println("|1.SỦA PHẦN TRĂM CỦA NGÂN SÁCH                                                                                                              |");
        System.out.println("|2.SỬA GIỚI HẠN SỐ TIỀN                                                                                                                     |");
        System.out.println("|CÁC NÚT CÒN LẠI TRỞ VỀ MENU                                                                                                                |");
        System.out.println("|___________________________________________________________________________________________________________________________________________|");
            String test = sc.nextLine();
            if (!isInteger(test) || Integer.parseInt(test) > 3 || Integer.parseInt(test) < 1) {
               return;
            }
            int luachon = Integer.parseInt(test);
            if (luachon == 1) {
                System.out.println("Nhập phần trăm !!");
                test = sc.nextLine();
                while (!isInteger(test) || Integer.parseInt(test) > 100 || Integer.parseInt(test) < 0) {
                    if (!isInteger(test)) {
                        System.out.println("Bạn nhập không hợp lệ!!");
                        System.out.println("Hãy nhập lại!!");
                        test = sc.nextLine();
                    } else {
                        System.out.println("Bạn nhập quá 100 hoặc bé hơn 0!!");
                        System.out.println("Hãy nhập lại!!");
                        test = sc.nextLine();
                    }
                }

                double percent = Double.parseDouble(test);
                System.out.println("Nhập danh mục bạn muốn sửa phầm trăm");
                int temp=1;
                for (DanhMuc list : getDanhMucChi().getDsDanhMuc()) {
                    System.out.println(temp + ": " + list.gettendanhmuc());
                    temp++;
                }
                System.out.println("Vui lòng nhập vị trí danh mục trong nhóm này");
                int type_1 = Integer.parseInt(sc.nextLine());
                DanhMuc danhmuccap1=getDanhMucChi().getDsDanhMuc().get(type_1-1);
                for(NganSach ngansach:getDanhSachNganSach()){
                    if(ngansach.danhmucchi()==danhmuccap1){
                        ngansach.setPhanTram(percent);
                        break;
                    }
                }
                System.out.println("Điều chỉnh thành công");
                System.out.println("Nhấn 1 nút để trở về menu");
                sc.nextLine();
            } else if(luachon == 2) {
                System.out.println("Nhập phần Giới Hạn số tiền !!");
                test = sc.nextLine();
                while (!isInteger(test)) {
                    System.out.println("Bạn nhập không hợp lệ!!");
                    System.out.println("Hãy nhập lại!!");
                    test = sc.nextLine();
                }
                int soTienMoi = Integer.parseInt(test);
                int temp=1;
                for (DanhMuc list : getDanhMucChi().getDsDanhMuc()) {
                    System.out.println(temp + ": " + list.gettendanhmuc());
                    temp++;
                }
                System.out.println("Vui lòng nhập vị trí danh mục trong nhóm này");
                int type_1 = Integer.parseInt(sc.nextLine());
                DanhMuc danhmuccap1=getDanhMucChi().getDsDanhMuc().get(type_1-1);
                for(NganSach ngansach:getDanhSachNganSach()){
                    if(ngansach.danhmucchi()==danhmuccap1){
                        ngansach.setSotien(soTienMoi);
                        break;
                    }
                }
                System.out.println("Điều chỉnh thành công");
                System.out.println("Nhấn 1 nút để trở về menu");
                sc.nextLine();

            }
            else{
                return;
            }
            /*
            else if(luachon == 3){
                if(this.getGioiHan().getbatTat()){
                    System.out.println("Chức năng giới hạn ngân sách của bạn hiện đang bật!");
                    System.out.println("Bạn muốn tắt?");
                    System.out.println("Nhập 1");
                    System.out.println("ấn các phím còn lại để thoát");
                    test = sc.nextLine();
                    if (!isInteger(test) || Integer.parseInt(test)>1 ||Integer.parseInt(test)<1) {
                       return;
                    }
                    int Chon = Integer.parseInt(test);
                    if(Chon==1){
                        this.getGioiHan().batTat(false);
                        System.out.println("Bạn đã tắt");
                        System.out.println("Ấn 1 phím bất kỳ để thoát");
                        sc.nextLine();

                    }
                }
                else{
                    System.out.println("Chức năng giới hạn ngân sách của bạn hiện đang tắt!");
                    System.out.println("Bạn muốn bật lại?");
                    System.out.println("Nhập 1:Có");
                    System.out.println("ấn các phím còn lại để thoát");
                    test = sc.nextLine();
                    if (!isInteger(test) || Integer.parseInt(test)>1 ||Integer.parseInt(test)<1) {
                        return;
                    }
                    int Chon = Integer.parseInt(test);
                    if(Chon==1){
                        this.getGioiHan().batTat(true);
                        System.out.println("Bạn đã bật");
                        System.out.println("Ấn 1 phím bất kỳ để thoát");
                        sc.nextLine();
                    }
                }

            }*/
        }

    public void TaoGioiHanNganSach() {
        setDateToDay(new NgayThangNam(getdateToDay().getngay(), getdateToDay().getthang(), getdateToDay().getnam()));
        Scanner sc = new Scanner(System.in);
        System.out.println("BẠN MUỐN TẠO NGÂN SÁCH CHO DANH MỤC NÀO                                                                                                    ");

        int i = 1;
        DanhMuc DanhMucCha = null;
        for (DanhMuc list : getDanhMucChi().getDsDanhMuc()) {
            System.out.println(+i + ": " + list.gettendanhmuc());
            i++;
        }
        System.out.println("Hãy nhập lựa chọn của bạn:");
        int luachoncuaban = Integer.parseInt(sc.nextLine());
        DanhMucCha = getDanhMucChi().getDsDanhMuc().get(luachoncuaban - 1);
        System.out.println("Bạn đã chọn danh mục " + DanhMucCha.gettendanhmuc());
        System.out.println("__________________________________________________________________XIN NHẬP LỰA CHỌN__________________________________________________________");
        System.out.println("|BẠN MUỐN TẠO NGÂN SÁCH THÁNG HIỆN TẠI HAY NHỮNG THÁNG KHÁC?                                                                                |");
        System.out.println("|1.HIỆN TẠI                                                                                                                                 |");
        System.out.println("|2.TÙY CHỈNH                                                                                                                                |");
        System.out.println("|CÁC NÚT CÒN LẠI: VỀ MENU                                                                                                                   |");
        System.out.println("|___________________________________________________________________________________________________________________________________________|");
        int month = 12;
        int year = 2023;
        System.out.println("Hãy nhập lựa chọn của bạn:");
        int choice = Integer.parseInt(sc.nextLine());
        try {
            if (choice == 1) {//thang hien tai
                month = getdateToDay().getthang();
                year = getdateToDay().getnam();
            } else if (choice == 2) {//tuy chinh
                System.out.println("Nhập tháng ");
                String thang = sc.nextLine();
                while (!Kiemtrathanghople(thang)) {
                    System.out.println("Tháng không hợp lệ, mời nhập lại!!!!!");
                    thang = sc.nextLine();
                }
                System.out.println("Nhập năm ");
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


        } catch (NumberFormatException e) {
            //KHÔNG CẦN XỬ LÝ Ở ĐÂY
        }
        if (this.getDanhSachNganSach() == null) {
            {
                System.out.println("Nhập số tiền");
                String test = sc.nextLine();
                while (!QuanLyDanhMuc.isInteger(test)) {
                    System.out.println("Bạn nhập không hợp lệ vui lòng nhập lại");
                    test = sc.nextLine();
                }
                int sotien = Integer.parseInt(test);
                System.out.println("Nhập phần trăm để dễ quản lý hơn: ( lưu ý: phần trăm phải trên 0 và dưới 100 ) ");
                test = sc.nextLine();
                while (!isInteger(test) || Integer.parseInt(test) > 100 || Integer.parseInt(test) < 0) {
                    System.out.println("Vui lòng nhập hợp lệ");
                    test = sc.nextLine();
                }
                double percent = Double.parseDouble(test);
                this.DsGioiHanChi.add(new NganSach(month, year, sotien, percent, DanhMucCha));
                System.out.println("Bạn đã tạo ngân sách giới hạn thành công");
                System.out.println("Ấn một nút bất kỳ để tiếp tục");
                sc.nextLine();
            }
        } else {
            NganSach kiemtrangansach = null;
            for (NganSach ngsach : getDanhSachNganSach()) {
                if (ngsach.danhmucchi() == DanhMucCha) {
                    kiemtrangansach = ngsach;
                }
            }
            if (kiemtrangansach!=null && kiemtrangansach.Kiemtrangansachcoduoctaochua(year, month, DanhMucCha)) {
                System.out.println("Bạn đã tạo ngân sách cho tháng này rồi");
                System.out.println("Nhấn 1 phím để quay về ");
                sc.nextLine();
            } else if (kiemtrangansach == null) {
                System.out.println("Nhập số tiền");
                String test = sc.nextLine();
                while (!QuanLyDanhMuc.isInteger(test)) {
                    System.out.println("Bạn nhập không hợp lệ vui lòng nhập lại");
                    test = sc.nextLine();
                }
                int sotien = Integer.parseInt(test);
                System.out.println("Nhập phần trăm để dễ quản lý hơn: ( lưu ý: phần trăm phải trên 0 và dưới 100 ) ");
                test = sc.nextLine();
                while (!isInteger(test) || Integer.parseInt(test) > 100 || Integer.parseInt(test) < 0) {
                    System.out.println("Vui lòng nhập hợp lệ");
                    test = sc.nextLine();
                }
                double percent = Double.parseDouble(test);
                this.DsGioiHanChi.add(new NganSach(month, year, sotien, percent, DanhMucCha));
                System.out.println("Bạn đã tạo ngân sách giới hạn thành công");
                System.out.println("Ấn một nút bất kỳ để tiếp tục");
                sc.nextLine();
            }
        }
    }

    @Override
    public void thongke(int month , int year) {//Thong ke theo tuan cua pro user
            int[] danhmucthu = new int[4];
            int[] danhmucchi = new int[4];
    
            for (int i = 0; i < 4; i++) {
                danhmucthu[i] = 0;
                danhmucchi[i] = 0;
            }
    
            for (GiaoDich gd : getDsgiaodich().getDsGD()) {
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
                if(i!=0 && danhmucchi[i]>danhmucchi[i-1]){
                    System.out.println("Sử dụng nhiều hơn tuần trước "+(danhmucchi[i]-danhmucchi[i-1]));
                }
                if(i!=0 && danhmucchi[i]<danhmucchi[i-1]){
                    System.out.println("Sử dụng ít hơn tuần trước "+(danhmucchi[i-1]-danhmucchi[i]));
                }
    
                System.out.println("---->số tiền chi chiếm " + percent * 100 + "% trên tổng số tiền thu và chi tuần này");
                int use = danhmucchi[i] - danhmucthu[i];
                if (use>0) {
                    
                    System.out.println("Sử dụng nhiều hơn thu nhập :" + use);
                }
                else{
                    System.out.println("Chúc mừng bạn đã tiết kiệm được : " + use);
                }
            }
        
            double totalchi = TongSoTientrongdanhmuc(danhmucchi);
            double totalthu = TongSoTientrongdanhmuc(danhmucthu);
        if(totalchi+totalthu==0){
            System.out.println("Không có dữ liệu thống kê");
        }
        else {
            System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
            System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng cho mỗi tháng trong năm " + year + ":                                         ");
            System.out.println("------------------------------------------------------CHI-------------------------------------------------------");
            for (int i = 0; i < 4; i++) {
                double percent = (totalchi != 0) ? danhmucchi[i] * 100.0 / totalchi : 0.0;
                //if(percent>0) {
                System.out.print(" - Tuần " + (i + 1) + " : ");
                // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0

                // In số lượng dấu sao tương ứng với phần trăm chiếm
                for (int x = 0; x < percent; x++) {
                    System.out.print("*");
                }
                System.out.print("\n");
                // In phần trăm chiếm
                System.out.println(" Chiếm " + percent + " %");
                // }
            }
            System.out.println("------------------------------------------------------THU-------------------------------------------------------");
            for (int i = 0; i < 4; i++) {
                double percent = (totalthu != 0) ? danhmucthu[i] * 100.0 / totalthu : 0.0;
                //if(percent>0) {
                System.out.print(" - Tuần " + (i + 1) + " : ");
                // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0

                // In số lượng dấu - tương ứng với phần trăm chiếm
                for (int x = 0; x < percent; x++) {
                    System.out.print("-");
                }
                System.out.print("\n");
                // In phần trăm chiếm
                System.out.println(" Chiếm " + percent + " %");
                // }
            }
            int Chi_leastWeek = Itnhat(danhmucchi); // tháng chi có giá trị ít nhất(tra ve index)
            int Chi_mostWeek = Nhieunhat(danhmucchi);  // tháng thu có giá trị nhiều nhất(tra ve index)
            int Thu_leastWeek = Itnhat(danhmucthu);//tháng thu có giá trị ít nhất (tra ve index)
            int Thu_mostWeek = Nhieunhat(danhmucthu);// tháng thu có giá trị nhiều nhất (tra ve index)
            System.out.println(" Dữ liệu trong tháng " + month + " :");
            System.out.println("------------------------------------------------------CHI--------------------------------------------------------");
            System.out.println("-Tuần chi nhiều nhất là tuần " + (Chi_mostWeek + 1) + " với số tiền là " + danhmucchi[Chi_mostWeek]);
            for (int i = 0; i < danhmucchi.length; i++) {
                if (danhmucchi[i] == danhmucchi[Chi_mostWeek] && i != Chi_mostWeek) {
                    System.out.println("-Tuần chi ít nhất là Tuần " + (i + 1) + " với số tiền là " + danhmucchi[Chi_leastWeek]);
                }
            }
            System.out.println("------------------------------------------------------THU--------------------------------------------------------");
            System.out.println("-Tuần thu nhiều nhất là Tuần " + (Thu_mostWeek + 1) + " với số tiền là " + danhmucthu[Thu_mostWeek]);
            for (int i = 0; i < danhmucthu.length; i++) {
                if (danhmucthu[i] == danhmucthu[Thu_mostWeek] && i != Thu_mostWeek) {
                    System.out.println("-Tuần thu ít nhất là Tuần " + (i + 1) + " với số tiền là " + danhmucthu[Thu_leastWeek]);
                }
            }

        }
    
        }
        @Override
        public void thongke(int year) {//Thong ke theo thang cua pro user
            int[] danhmucthu = new int[12];
            int[] danhmucchi = new int[12];

            for (int i = 0; i < 12; i++) {
                danhmucthu[i] = 0;
                danhmucchi[i] = 0;
            }

            for (GiaoDich gd : getDsgiaodich().getDsGD()) {
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
                if (i != 0 && danhmucchi[i] > danhmucchi[i - 1]) {
                    System.out.println("Sử dụng nhiều hơn tháng trước " + (danhmucchi[i] - danhmucchi[i - 1]));
                }
                if (i != 0 && danhmucchi[i] < danhmucchi[i - 1]) {
                    System.out.println("Sử dụng ít hơn tháng trước " + (danhmucchi[i - 1] - danhmucchi[i]));
                }

                double total = danhmucchi[i] + danhmucthu[i];
                double percent = (total != 0) ? danhmucchi[i] * 1.0 / total : 0.0;
                System.out.println(" ---->số tiền chi chiếm " + percent * 100 + "% trên tổng số tiền thu và chi trong tháng này");
                int use = danhmucchi[i] - danhmucthu[i];
                if (use > 0) {
                    System.out.println(" Sử dụng nhiều hơn thu nhập :" + use);
                } else {

                    System.out.println(" Bạn đã tiết kiêm được :" + use);

                }
            }
            double totalchi = TongSoTientrongdanhmuc(danhmucchi);
            double totalthu = TongSoTientrongdanhmuc(danhmucthu);
            if (totalchi + totalthu == 0) {
                System.out.println("Không có dữ liệu thống kê");
            } else {
                System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
                System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng cho mỗi tháng trong năm " + year + ":                                         ");
                System.out.println("------------------------------------------------------CHI-------------------------------------------------------");
                for (int i = 0; i < 12; i++) {
                    double percent = (totalchi != 0) ? danhmucchi[i] * 100.0 / totalchi : 0.0;
                    //if(percent>0) {
                    System.out.print(" - Tháng " + (i + 1) + " : ");
                    // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0

                    // In số lượng dấu sao tương ứng với phần trăm chiếm
                    for (int x = 0; x < percent; x++) {
                        System.out.print("*");
                    }
                    System.out.print("\n");
                    // In phần trăm chiếm
                    System.out.println(" Chiếm " + percent + " %");
                    // }
                }
                System.out.println("------------------------------------------------------THU-------------------------------------------------------");
                for (int i = 0; i < 12; i++) {
                    double percent = (totalthu != 0) ? danhmucthu[i] * 100.0 / totalthu : 0.0;
                    //if(percent>0) {
                    System.out.print(" - Tháng " + (i + 1) + " : ");
                    // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0

                    // In số lượng dấu - tương ứng với phần trăm chiếm
                    for (int x = 0; x < percent; x++) {
                        System.out.print("-");
                    }
                    System.out.print("\n");
                    // In phần trăm chiếm
                    System.out.println(" Chiếm " + percent + " %");
                    // }
                }
                int Chi_leastMonth = Itnhat(danhmucchi); // tháng chi có giá trị ít nhất(tra ve index)
                int Chi_mostMonth = Nhieunhat(danhmucchi);  // tháng thu có giá trị nhiều nhất(tra ve index)
                int Thu_leastMonth = Itnhat(danhmucthu);//tháng thu có giá trị ít nhất (tra ve index)
                int Thu_mostMonth = Nhieunhat(danhmucthu);// tháng thu có giá trị nhiều nhất (tra ve index)
                System.out.println(" Dữ liệu trong năm " + year + " :");
                System.out.println("------------------------------------------------------CHI--------------------------------------------------------");
                System.out.println("-Tháng chi nhiều nhất là Tháng " + (Chi_mostMonth + 1) + " với số tiền là " + danhmucchi[Chi_mostMonth]);
                for (int i = 0; i < danhmucchi.length; i++) {
                    if (danhmucchi[i] == danhmucchi[Chi_leastMonth] && i != Chi_mostMonth) {
                        System.out.println("-Tháng chi ít nhất là Tháng " + (i + 1) + " với số tiền là " + danhmucchi[Chi_leastMonth]);
                    }
                }
                System.out.println("------------------------------------------------------THU--------------------------------------------------------");
                System.out.println("-Tháng thu nhiều nhất là Tháng " + (Thu_mostMonth + 1) + " với số tiền là " + danhmucthu[Thu_mostMonth]);
                for (int i = 0; i < danhmucthu.length; i++) {
                    if (danhmucthu[i] == danhmucthu[Thu_leastMonth] && i != Thu_mostMonth) {
                        System.out.println("-Tháng thu ít nhất là Tháng " + (i + 1) + " với số tiền là " + danhmucthu[Thu_leastMonth]);
                    }
                }
            }
        }
        @Override
        public void thongketheonam(int soNamGanDay) {//thong ke theo nam cua pro user
            int[] danhmucthu = new int[soNamGanDay];
            int[] danhmucchi = new int[soNamGanDay];
            for (int i = 0; i < soNamGanDay; i++) {
                danhmucthu[i] = 0;
                danhmucchi[i] = 0;
            }
            int currentYear = LocalDate.now().getYear();
            for (GiaoDich gd : getDsgiaodich().getDsGD()) {
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
            double totalchi = TongSoTientrongdanhmuc(danhmucchi);
            double totalthu = TongSoTientrongdanhmuc(danhmucthu);
            if (totalchi + totalthu == 0) {
                System.out.println("Không có dữ liệu thống kê");
            } else {
                for (int i = soNamGanDay - 1; i >= 0; i--) {//////// free
                    System.out.println(" - Năm " + (currentYear - i) + " :");
                    System.out.println(" +Số tiền chi: " + danhmucchi[i]);
                    System.out.println(" +Số tiền thu được: " + danhmucthu[i]);
                    if (i != 0 && danhmucchi[i] > danhmucchi[i - 1]) {
                        System.out.println("Sử dụng nhiều hơn năm trước " + (danhmucchi[i] - danhmucchi[i - 1]));
                    }
                    if (i != 0 && danhmucchi[i] < danhmucchi[i - 1]) {
                        System.out.println("Sử dụng ít hơn năm trước " + (danhmucchi[i - 1] - danhmucchi[i]));
                    }
                    double total = danhmucchi[i] + danhmucthu[i];
                    double percent = (total != 0) ? danhmucchi[i] * 1.0 / total : 0.0;
                    System.out.println("---->số tiền chi chiếm " + percent * 100 + "% trên tổng số thu chi");
                    int use = danhmucchi[i] - danhmucthu[i];
                    if (use > 0) {
                        System.out.println("Sử dụng nhiều hơn thu nhập :" + use + "đ");
                    } else {
                        System.out.println("Bạn đã tiết kiệm được: " + use + "đ");
                    }
                } ///////free

                System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
                // bieu do chi
                System.out.println("------------------------------------------------------CHI-------------------------------------------------------");
                System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng trong " + soNamGanDay + " gần đây :                                           ");
                for (int i = soNamGanDay - 1; i >= 0; i--) {
                    System.out.print(" - Năm " + (currentYear - i) + " : ");
                    // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0
                    double percent = (totalchi != 0) ? danhmucchi[i] * 100.0 / totalchi : 0.0;
                    // In số lượng dấu sao tương ứng với phần trăm chiếm
                    for (int x = 0; x < percent; x++) {
                        System.out.print("*");
                    }
                    System.out.print("\n");
                    // In phần trăm chiếm
                    System.out.println(" Chiếm " + percent + " %");
                }
                System.out.println("------------------------------------------------------THU--------------------------------------------------------");
                /// bieu do thu
                System.out.println("- Ta có biểu đồ từ số tiền đã thu được trong " + soNamGanDay + " gần đây : ");

                for (int i = soNamGanDay - 1; i >= 0; i--) {
                    // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0
                    double percent = (totalthu != 0) ? danhmucthu[i] * 100.0 / totalthu : 0.0;
                    System.out.print(" - Năm " + (currentYear - i) + " : ");


                    // In số lượng dấu + tương ứng với phần trăm chiếm
                    for (int x = 0; x < percent; x++) {
                        System.out.print("+");
                    }
                    System.out.print("\n");
                    // In phần trăm chiếm
                    System.out.println(" Chiếm " + percent + " %");
                }
                int Chi_leastYear = Itnhat(danhmucchi); // Năm chi có giá trị ít nhất(tra ve index)
                int Chi_mostYear = Nhieunhat(danhmucchi);  // Năm thu có giá trị nhiều nhất(tra ve index)
                int Thu_leastYear = Itnhat(danhmucthu);//năm thu có giá trị ít nhất (tra ve index)
                int Thu_mostYear = Nhieunhat(danhmucthu);// Năm thu có giá trị nhiều nhất (tra ve index)
                System.out.println(" Dữ liệu từ " + soNamGanDay + " năm gần đây nhất: ");
                System.out.println("------------------------------------------------------CHI--------------------------------------------------------");

                System.out.println("-Năm chi nhiều nhất trong những năm gần đây là " + (currentYear - (Nhieunhat(danhmucchi)) + " với số tiền là " + danhmucchi[Nhieunhat(danhmucchi)]));
                for (int i = 0; i < danhmucchi.length; i++) {
                    if (danhmucchi[i] == danhmucchi[Chi_leastYear] && i != Chi_mostYear) {
                        System.out.println("-Năm sử dụng ít nhất là năm " + (currentYear - i) + " với số tiền là " + danhmucchi[Itnhat(danhmucchi)]);
                    }
                }
                System.out.println("------------------------------------------------------THU--------------------------------------------------------");
                System.out.println("-Năm thu nhiều nhất trong những năm gần đây là " + (currentYear - (Nhieunhat(danhmucthu)) + " với số tiền là " + danhmucthu[Nhieunhat(danhmucthu)]));
                for (int i = 0; i < danhmucthu.length; i++) {
                    if (danhmucthu[i] == danhmucthu[Thu_leastYear] && i != Thu_mostYear) {
                        System.out.println("-Năm thu ít nhất là năm " + (currentYear - i) + " với số tiền là " + danhmucthu[Itnhat(danhmucthu)]);
                    }
                }

            }
        }

        public void XuatBaoCaoRaFile(){};
    }

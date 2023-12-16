package com.bt.quanlythuchicanhan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class QuanLyDanhMucFree extends QuanLyDanhMuc implements Serializable {
    private GioiHanGiaoDich solangiaodich;
    private static final long serialVersionUID = 1L;
    public QuanLyDanhMucFree() {
        super();
        this.setSoDanhMuc(3);
        solangiaodich=new GioiHanGiaoDich(5,getdateToDay().getthang());
    }
    
    public GioiHanGiaoDich getSolanGiaoDich() {
        return solangiaodich;
    }
    
    public void setSolangiaodich(GioiHanGiaoDich solangiaodich) {
        this.solangiaodich = solangiaodich;
    }
    
    public void setSolangiaodich(int solan,int Thang) {
        this.solangiaodich = new GioiHanGiaoDich(solan,Thang);
    }
    
    @Override
    public void ChonDanhMucDeThem() { // abstract
        Scanner sc = new Scanner(System.in);
        System.out.println("Số lần thêm danh mục của bạn còn " + getSoDanhMuc());
        int SoDanhMucDaThem=3-getSoDanhMuc();
        if (SoDanhMucDaThem < 3) {
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

    public void TuDongTaoGiaoDichMoiThang() {
        this.getSolanGiaoDich().kiemTraThayDoiThang(getdateToDay().getthang());
    } // Ham nay tu dong tao giao dich khi qua thang moi
    public void ThayDoiThangHienTai(){ // dùng cho case test
        Scanner sc = new Scanner(System.in);
        System.out.println("Thay đổi tháng hiện tại,nhập tháng");
        this.getdateToDay().setThang(Integer.parseInt(sc.nextLine()));
        System.out.println("Số lần giao dich là "+this.getSolanGiaoDich().getsolangiaodich());
    }
@Override
    public void chonloaigiaodich() {
        setDateToDay(new NgayThangNam(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        Scanner sc = new Scanner(System.in);
        TuDongTaoGiaoDichMoiThang();
        int soluonggiaodich = this.getSolanGiaoDich().getsolangiaodich();
        System.out.println("Số lần giao dịch của bạn còn " + soluonggiaodich);
        if (soluonggiaodich > 0) {
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
                        //KHÔNG CẦN XỬ LÝ Ở ĐÂY
                    }
                }
            } catch (NumberFormatException e) {
                //KHÔNG CẦN XỬ LÝ Ở ĐÂY
            }
        } else {
            System.out.println("Số lần giao dịch của bạn trong tháng này đã hết !!");
            System.out.println("Nhấn 1 phím bất kỳ để back về menu");
            sc.nextLine();
        }
    }
@Override
    public void giaodich(ListDanhMuc danhmuc, int loaigd) {
        setDateToDay(new NgayThangNam(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        Scanner sc = new Scanner(System.in);
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
            System.out.println("Nhập nội dung giao dịch");
            String noidung = sc.nextLine();
            System.out.println("Nhập số tiền cần giao dịch");
            String test = sc.nextLine();
            while (!isInteger(test)) {
                System.out.println("Hãy nhập số!!!!!!!");
                test = sc.nextLine();
            }
            int sotien = Integer.parseInt(test);
            // tim thong tin danh muc cha
            System.out.println("Bạn muốn giao dịch với nhóm nào");
            int i = 1;
            DanhMuc DanhMucCha = null;
            for (DanhMuc list : danhmuc.getDsDanhMuc()) {

                System.out.println("Nhóm " + i + ": " + list.gettendanhmuc());
                int x = 1;
                for (DanhMuc danhmuccon : list.getdanhsachdanhmuccon()) {

                    System.out.println("     " +i+"."+ x + " " + danhmuccon.gettendanhmuc());
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
                } catch (NumberFormatException e) {
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
                    } catch (IndexOutOfBoundsException x) {
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
                    this.getSolanGiaoDich().setSolangiaodich(this.getSolanGiaoDich().getsolangiaodich() - 1);
                    return;
                }
            } else {
                System.out.println("Danh mục bạn chọn đang rỗng!!!!!( không tồn tại danh mục cấp 1 )");
                return;
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
        double totalchi = TongSoTientrongdanhmuc(danhmucchi);
        double totalthu = TongSoTientrongdanhmuc(danhmucthu);
        System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
        System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng cho mỗi tháng trong năm "+year+":                                         ");
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
            double percent = (totalthu   != 0) ? danhmucthu[i] * 100.0 / totalthu : 0.0;
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
        System.out.println(" Dữ liệu trong tháng "+month +" :");
        System.out.println("------------------------------------------------------CHI--------------------------------------------------------");
        System.out.println("-Tuần chi nhiều nhất là tuần " + (Chi_mostWeek + 1)+" với số tiền là "+danhmucchi[Chi_mostWeek]);
        for(int i=0;i<danhmucchi.length;i++){
            if(danhmucchi[i] == danhmucchi[Chi_mostWeek] && i != Chi_mostWeek) {
                System.out.println("-Tuần chi ít nhất là Tuần " + (i + 1) + " với số tiền là " + danhmucchi[Chi_leastWeek]);
            }
        }
        System.out.println("------------------------------------------------------THU--------------------------------------------------------");
        System.out.println("-Tuần thu nhiều nhất là Tuần " + (Thu_mostWeek + 1)+" với số tiền là "+danhmucthu[Thu_mostWeek]);
        for(int i=0;i<danhmucthu.length;i++){
            if(danhmucthu[i] == danhmucthu[Thu_mostWeek] && i != Thu_mostWeek) {
                System.out.println("-Tuần thu ít nhất là Tuần " + (i + 1) + " với số tiền là " + danhmucthu[Thu_leastWeek]);
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
        double totalchi = TongSoTientrongdanhmuc(danhmucchi);
        double totalthu = TongSoTientrongdanhmuc(danhmucthu);
        System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
        System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng cho mỗi tháng trong năm "+year+":                                         ");
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
            double percent = (totalthu   != 0) ? danhmucthu[i] * 100.0 / totalthu : 0.0;
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
        System.out.println(" Dữ liệu trong năm "+year +" :");
        System.out.println("------------------------------------------------------CHI--------------------------------------------------------");
        System.out.println("-Tháng chi nhiều nhất là Tháng " + (Chi_mostMonth + 1)+" với số tiền là "+danhmucchi[Chi_mostMonth]);
        for(int i=0;i<danhmucchi.length;i++){
            if(danhmucchi[i] == danhmucchi[Chi_leastMonth] && i != Chi_mostMonth) {
                System.out.println("-Tháng chi ít nhất là Tháng " + (i + 1) + " với số tiền là " + danhmucchi[Itnhat(danhmucchi)]);
            }
        }
        System.out.println("------------------------------------------------------THU--------------------------------------------------------");
        System.out.println("-Tháng thu nhiều nhất là Tháng " + (Thu_mostMonth + 1)+" với số tiền là "+danhmucthu[Thu_mostMonth]);
        for(int i=0;i<danhmucthu.length;i++){
            if(danhmucthu[i] == danhmucthu[Thu_leastMonth] && i != Thu_mostMonth) {
                System.out.println("-Tháng thu ít nhất là Tháng " + (i + 1) + " với số tiền là " + danhmucthu[Itnhat(danhmucthu)]);
            }
        }
    }
    @Override
    public void thongketheonam(int soNamGanDay){//thong ke theo nam cua pro user
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
        System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
        // bieu do chi
        System.out.println("------------------------------------------------------CHI-------------------------------------------------------");
        System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng trong "+soNamGanDay+" gần đây :                                           ");
        for (int i = soNamGanDay-1; i >=0; i--) {
            System.out.print(" - Năm " + (currentYear -  i) + " : ");
            // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0
            double percent = (totalchi != 0) ? danhmucchi[i] * 100.0 / totalchi : 0.0;
            // In số lượng dấu sao tương ứng với phần trăm chiếm
            for (int x = 0; x < percent; x++) {
                System.out.print("*");
            }
            System.out.print("\n");
            // In phần trăm chiếm
            System.out.println(" Chiếm " + percent + " %");
        }System.out.println("------------------------------------------------------THU--------------------------------------------------------");
        /// bieu do thu
        System.out.println("- Ta có biểu đồ từ số tiền đã thu được trong "+soNamGanDay+" gần đây : ");
        double totalthu = TongSoTientrongdanhmuc(danhmucthu);
        for (int i = soNamGanDay-1; i >=0; i--) {
            // Kiểm tra nếu totalchi khác 0 để tránh lỗi chia cho 0
            double percent = (totalthu != 0) ? danhmucthu[i] * 100.0 / totalchi : 0.0;
            System.out.print(" - Năm " + (currentYear -  i) + " : ");


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
         System.out.println(" Dữ liệu từ "+soNamGanDay+" năm gần đây nhất: ");
        System.out.println("------------------------------------------------------CHI--------------------------------------------------------");

        System.out.println("-Năm chi nhiều nhất trong những năm gần đây là " + (currentYear - (Nhieunhat(danhmucchi) )+" với số tiền là "+danhmucchi[Nhieunhat(danhmucchi)]));
        for(int i=0;i<danhmucchi.length;i++){
            if(danhmucchi[i] == danhmucchi[Chi_leastYear] && i != Chi_mostYear) {
                System.out.println("-Năm sử dụng ít nhất là năm " + (currentYear - i) + " với số tiền là " + danhmucchi[Itnhat(danhmucchi)]);
            }
        }
        System.out.println("------------------------------------------------------THU--------------------------------------------------------");
        System.out.println("-Năm thu nhiều nhất trong những năm gần đây là " + (currentYear - (Nhieunhat(danhmucthu) )+" với số tiền là "+danhmucthu[Nhieunhat(danhmucthu)]));
        for(int i=0;i<danhmucthu.length;i++){
            if(danhmucthu[i]==danhmucthu[Thu_leastYear] && i!=Thu_mostYear) {
                System.out.println("-Năm thu ít nhất là năm " + (currentYear - i) + " với số tiền là " + danhmucthu[Itnhat(danhmucthu)]);
            }
        }

    }
    
    public static void main(String [] args){

    }
}

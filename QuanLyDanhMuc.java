/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;
import com.bt.quanlythuchicanhan.GiaoDich;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;


public abstract class QuanLyDanhMuc implements Serializable {
    private int sodanhmuc;
    private ListDanhMuc danhmucchi;// danh sach danh muc chi
    private ListDanhMuc danhmucthu; // danh sach danh muc thu
    private ListGiaoDich dsgiaodich; // Lich su giao dich
    private NgayThangNam dateToDay;
    private static final long serialVersionUID = 1L;

    public NgayThangNam getdateToDay() {
        return dateToDay;
    }

    public void setDateToDay(NgayThangNam dateToDay) {
        this.dateToDay = dateToDay;
    }

    
    public abstract void giaodich(ListDanhMuc danhmuc, int loaigd);
    public abstract void chonloaigiaodich();


    public abstract void ChonDanhMucDeThem() ;
    public static String generateID_DanhMuc(String prefix) {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 9));

        String formatNumber = String.format("%0" + 9 + "d", randomNumber);
        return prefix + formatNumber;
    }

    public QuanLyDanhMuc() {
        this.danhmucchi = new ListDanhMuc(new DanhMuc("TY1", "Thiết yếu","Chi"), new DanhMuc("BT1", "Biếu tặng","Chi"), new DanhMuc("SK1", "Sức khỏe","Chi"), new DanhMuc("GT", "Giải trí","Chi"));
        this.danhmucthu = new ListDanhMuc(new DanhMuc("L1", "Lương","Thu"), new DanhMuc("T1", "Thưởng","Thu"));
        this.dsgiaodich = new ListGiaoDich();
        this.dateToDay=new NgayThangNam(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear());
    }


    public void ThemDanhMuc(ListDanhMuc danhmuc, String idDanhMuc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("VUI LÒNG NHẬP CÁC THÔNG TIN CÁC DANH MỤC CẦN THÊM");
        String id = QuanLyDanhMuc.generateID_DanhMuc(idDanhMuc);
        System.out.println("Tên danh mục cần thêm:                                                                 ");
        String name = sc.nextLine();
        DanhMuc danhmuccanthem = new DanhMuc(id, name);
        danhmuccanthem.setName_danhmuccha(danhmuc.getType());
        while (getDanhMucChi().timdanhmuctheoten(getDanhMucChi().getDsDanhMuc(), danhmuccanthem.gettendanhmuc()) != null || getDanhMucThu().timdanhmuctheoten(getDanhMucThu().getDsDanhMuc(), danhmuccanthem.gettendanhmuc()) != null) {
            System.out.println("Danh mục bị trùng , hãy nhập lại");
            name = sc.nextLine();
            danhmuccanthem.setTendanhmuc(name);
        }
        System.out.println("-Vai trò danh mục cấp 1 hay cấp 2                                                      ");
        System.out.println("-Nhập 1 nếu danh mục cấp 1                                                             ");
        System.out.println("-Nhập 2 nếu danh mục cấp 2                                                             ");
        System.out.println("-Nhập các nút còn lại về menu                                                          ");
        int m;
        try {
            m = Integer.parseInt(sc.nextLine());
            switch (m) {
                case 1:

                    danhmuc.themdanhmuc(danhmuccanthem);
                    setSoDanhMuc(getSoDanhMuc() - 1);

                    System.out.println("Số lần thêm danh mục bạn còn " + getSoDanhMuc());
                    System.out.println("Bạn có muốn thêm danh mục nữa không ?");
                    System.out.println("1:Có");
                    System.out.println("Các nút còn lại:back về menu");


                    try {
                        int choice1 = Integer.parseInt(sc.nextLine());
                        if (choice1 == 1) {
                            ChonDanhMucDeThem();
                        }
                        else{
                            return;
                        }
                    } catch (NumberFormatException e) {
                        //KHÔNG CẦN XỬ LÝ GÌ HẾT
                        return;
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
                    Boolean flat = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                    while (flat) {
                        try {
                            int type = Integer.parseInt(sc.nextLine());
                            flat = false;
                            DanhMuc DanhMucCha = danhmuc.getDsDanhMuc().get(type - 1);
                            DanhMucCha.getdanhsachdanhmuccon().add(danhmuccanthem);
                            danhmuccanthem.setName_danhmuccha(DanhMucCha.gettendanhmuc());
                            System.out.println("Đã tạo danh mục " + danhmuccanthem.gettendanhmuc());
                            setSoDanhMuc(getSoDanhMuc() - 1);
                            System.out.println("Số danh mục bạn còn " + getSoDanhMuc());
                            System.out.println("Bạn có muốn tạo tiếp danh mục nào nữa không");
                            System.out.println("1:YES ");
                            System.out.println("Các nút còn lại:Về menu ");
                            try {
                                int choice1 = Integer.parseInt(sc.nextLine());
                                if (choice1 == 1) {
                                    ChonDanhMucDeThem();
                                }
                                else{
                                    return;
                                }
                            } catch (Exception e) {
                                //KHÔNG CẦN XỬ LÝ Ở ĐÂY
                                return;
                            }
                        } catch (IndexOutOfBoundsException x) {
                            System.out.println("Số bạn nhập không nằm trong vị trí cho phép.");
                            flat = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                            flat = true;
                        }
                    }
                    break;
                default:
                    System.out.println("-----------------------------------------Bạn đã thoát-----------------------------------------");

            }
        } catch (NumberFormatException e) {
           return;
        }
    }
    public void ChonDanhMucdexoa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn xóa danh mục nào ");
        System.out.println("1:CHI ");
        System.out.println("2:THU ");
        System.out.println("Các nút còn lại:Trở về menu ");


        try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {

                xoaDanhMuc(getDanhMucChi());

            } else if (choice == 2) {

                xoaDanhMuc(getDanhMucThu());

            }
            else{
                return;
            }

        } catch (NumberFormatException x) {
            return;
        }
    }


    private void xoaDanhMuc(ListDanhMuc dsdanhmuc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn xóa danh mục cấp 1 hay cấp 2");
        System.out.println("1: cấp 1");
        System.out.println("2: cấp 2");
        String NameDanhMucCanXoa = null;
        Boolean flat = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập chuỗi thì cờ hiệu vẫn bằng true
        while (flat) {
            int choice;
            try {
                // lua chon danh muc cap 1 hoac 2 de xoa
                choice = Integer.parseInt(sc.nextLine());
                flat = false;
                if (choice == 1) {
                    // thuc hien xoa danh muc cap 1 trong đây
                    if (dsdanhmuc.getDsDanhMuc().isEmpty()) {
                        System.out.println("Không còn danh mục để xóa , nhấn phím bẩt kỳ để quay về menu ");
                        sc.nextLine();
                    } else {
                        int i = 1;
                        for (DanhMuc list : dsdanhmuc.getDsDanhMuc()) {
                                System.out.println(i + ": " + list.gettendanhmuc());

                            int x=1;
                            for(DanhMuc danhmuccap2:list.getdanhsachdanhmuccon()){
                                System.out.println(i +"."+x+": " + danhmuccap2.gettendanhmuc());
                                x++;
                            }
                            i++;
                        }
                        System.out.println("Bạn muốn xóa danh mục nào theo thứ tự từ 1" + " đến " + (i - 1));
                        int luachon;
                        Boolean CoHieu = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                        while (CoHieu) {
                            try {
                                luachon = Integer.parseInt(sc.nextLine());
                                CoHieu = false;
                                DanhMuc DanhMucCanXoa = dsdanhmuc.getDsDanhMuc().get(luachon - 1);
                                NameDanhMucCanXoa = DanhMucCanXoa.gettendanhmuc();
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                   CoHieu = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                CoHieu = true;
                            }

                        }
                    }
                } else if (choice == 2) { // thuc hien xoa danh muc cap 2 trong đây
                    if (dsdanhmuc.getDsDanhMuc().isEmpty())//nếu danh mục cap 1 rỗng phần tử thì back ve menu
                    {
                        System.out.println("Không còn danh mục để xóa , bạn có muốn quay lại menu hay tạo danh mục ");
                        System.out.println("ấn phím bất kỳ để quay về menu ");
                        String test = sc.nextLine();
                        {
                            return;
                        }
                    } else {
                        //nếu danh mục cấp 1 có phần tử
                        flat = false;// // đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                        int i = 1;
                        for (DanhMuc list : dsdanhmuc.getDsDanhMuc()) {
                                System.out.println("Nhóm :" + i + ": " + list.gettendanhmuc());
                            int x = 1;
                            for (DanhMuc danhmuccap2 : list.getdanhsachdanhmuccon()) {
                                System.out.println( "       "+i+"."+x + ": " + danhmuccap2.gettendanhmuc());
                                x++;
                            }
                            i++;
                        }
                        System.out.println("Bạn muốn xóa danh mục cấp 2 nào trong nhóm này");
                        DanhMuc DanhMucCap1 = null;
                        int type;
                        Boolean flat1 = true; // đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                        while (flat1 ) {
                            try {
                                flat1 = false;
                                System.out.println("Chọn Nhóm :");
                                type = Integer.parseInt(sc.nextLine());
                                flat1 = false;
                                DanhMucCap1 = dsdanhmuc.getDsDanhMuc().get(type - 1); // lấy ra nhóm Danh Mục cấp 1 cần xóa
                                if(DanhMucCap1.getdanhsachdanhmuccon().size()==0) {
                                    System.out.println("Danh Mục bạn chọn hiện đang rỗng");
                                    System.out.println("Bạn muốn chọn tiếp hay về menu?");
                                    System.out.println("1:Chọn tiếp");
                                    System.out.println("Các phím còn lại : về menu");
                                    try {
                                        int temp=Integer.parseInt(sc.nextLine());
                                        if(temp==1) {
                                            flat1=true;
                                        }
                                        else {
                                            return;
                                        }
                                    }
                                    catch(NumberFormatException e) {
                                        return;
                                    }
                                }
                            } catch (IndexOutOfBoundsException x) {
                                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                flat1 = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                flat1 = true;

                            }
                        }
                        if(DanhMucCap1.getdanhsachdanhmuccon().size()==1){
                            DanhMuc DanhMucCanXoa = DanhMucCap1.getdanhsachdanhmuccon().get(0);
                            NameDanhMucCanXoa = DanhMucCanXoa.gettendanhmuc();
                        }
                        else{
                            Boolean flat2 = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                        while (flat2) {
                            int type_1;
                            try {
                                // Nếu tìm được danh mục cấp 1 và Danh mục cấp 1 không rỗng
                                    System.out.println("Trong nhóm bạn chọn bao gồm : ");
                                    int temp = 1;
                                    for (DanhMuc list : DanhMucCap1.getdanhsachdanhmuccon()) {
                                        System.out.println(temp + ": " + list.gettendanhmuc());
                                        temp++;
                                    }

                                    System.out.println("Vui lòng nhập vị trí danh mục trong nhóm này");
                                    type_1 = Integer.parseInt(sc.nextLine());
                                    flat2 = false;
                                    DanhMuc DanhMucCanXoa = DanhMucCap1.getdanhsachdanhmuccon().get(type_1 - 1);
                                    NameDanhMucCanXoa = DanhMucCanXoa.gettendanhmuc();

                                } /* else { // Nếu kích thước danh mục cấp 1 rỗng
                                    System.out.println("Nhóm bạn chọn hiện đang không có danh mục cấp 1");
                                    return;

                                }*/
                             catch (IndexOutOfBoundsException x) {
                                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                flat2 = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                flat2 = true;

                            }
                        }
                        }

                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                flat = true;
            } catch (NumberFormatException e) {
                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                flat = true;
            }
        }
        dsdanhmuc.deleteDanhMuc(NameDanhMucCanXoa);
        setSoDanhMuc(getSoDanhMuc() + 1);
        System.out.println("Bạn có muốn xóa tiếp danh mục nào nữa không");
        System.out.println("1:YES ");
        System.out.println("Các nút còn lại:về menu ");
        int choice2;
        try {
            choice2 = Integer.parseInt(sc.nextLine());
            if (choice2 == 1) {
                ChonDanhMucdexoa();
            }
            else
                return;
        } catch (NumberFormatException e) {
            return;
        }
    }

    public void chonDanhMucDeSua() {
        Scanner sc = new Scanner(System.in);
        System.out.println("NHẬP LOẠI DANH MỤC CẦN SỬA ");
        System.out.println("HÃY NHẬP SỐ");
        System.out.println("1:CHI ");
        System.out.println("2:THU ");
        System.out.println("CÁC NÚT CÒN LẠI:TRỞ VỀ MENU ");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                suaDanhMuc(getDanhMucChi());

            } else if (choice == 2) {
                suaDanhMuc(getDanhMucThu());

            }
        } catch (NumberFormatException e) {
            //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
        }
    }

    private void suaDanhMuc(ListDanhMuc dsdanhmuc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("BẠN MUỐN SỬA DANH MỤC CẤP 1 HAY CẤP 2       ");
        System.out.println("1: nếu là cấp 1 ");
        System.out.println("2: nếu là cấp 2 ");
        System.out.println("Nhập 1 nút bất kỳ để thoát");
        ;
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:// Sửa danh mục cấp 1
                        if (dsdanhmuc.getDsDanhMuc().isEmpty())//nếu danh mục cap 1 rỗng phần tử thì back ve menu
                        {
                            System.out.println("Không còn danh mục để sửa, bạn có muốn quay lại menu hay tạo danh mục ");
                            System.out.println("ấn phím bất kỳ để quay về menu ");
                            sc.nextLine();
                            {
                                return;
                            }
                        } else {
                            int i = 1;
                            for (DanhMuc list : dsdanhmuc.getDsDanhMuc()) {
                                System.out.println(i + ": " + list.gettendanhmuc());
                                i++;
                            }
                            System.out.println("CHỌN DANH MỤC CHA :");
                            System.out.println("HÃY NHẬP SỐ :");
                            Boolean flat = true;
                            while (flat) {
                                int so;
                                try {
                                    so = Integer.parseInt(sc.nextLine());
                                    flat = false;
                                    DanhMuc DanhMucCansua = dsdanhmuc.getDsDanhMuc().get(so - 1);
                                    System.out.println("NHẬP TÊN DANH MỤC MỚI");
                                    String name2 = sc.nextLine();
                                    if (getDanhMucChi().timdanhmuctheoten(getDanhMucChi().getDsDanhMuc(), name2) != null && getDanhMucThu().timdanhmuctheoten(getDanhMucThu().getDsDanhMuc(), name2) != null) {
                                        System.out.println("Danh mục bị trùng hãy nhập lại");
                                        name2 = sc.nextLine();
                                    }
                                    dsdanhmuc.EditDanhMucCha(DanhMucCansua.gettendanhmuc(), name2);
                                    System.out.println("BẠN ĐÃ SỬA THÀNH CÔNG");
                                    System.out.println("BẠN CÓ MUỐN SỬA TIẾP DANH MỤC NÀO KHÔNG");
                                    System.out.println("1:YES ");
                                    System.out.println("Các nút còn lại:BACK VỀ MENU ");
                                    try{
                                        int choice2 = Integer.parseInt(sc.nextLine());

                                        if (choice2 == 1) {

                                            chonDanhMucDeSua();
                                        }
                                        else{
                                            return;
                                        }
                                    }catch(NumberFormatException e){
                                        return;
                                    }

                                } catch (IndexOutOfBoundsException x) {
                                    System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                    flat = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                    flat = true;

                                }
                            }
                        }
                        break;
                    case 2:// Sửa danh mục cấp 2
                        if (dsdanhmuc.getDsDanhMuc().isEmpty())//nếu danh mục cap 1 rỗng phần tử thì back ve menu
                        {
                            System.out.println("Không còn danh mục để sửa, bạn có muốn quay lại menu hay tạo danh mục ");
                            System.out.println("ấn phím bất kỳ để quay về menu ");
                            sc.nextLine();
                            {
                                return;
                            }
                        } else {

                                        String name1 = null;
                                        int sodanhmuc = 1;
                                        for (DanhMuc list : dsdanhmuc.getDsDanhMuc()) {
                                            System.out.println("Nhóm :"+sodanhmuc + ": " + list.gettendanhmuc());
                                            int x = 1;
                                            for (DanhMuc danhmuccap2 : list.getdanhsachdanhmuccon()) {
                                            System.out.println("      "+sodanhmuc+"."+x + ": " + danhmuccap2.gettendanhmuc());
                                            x++;
                                            }
                                            sodanhmuc++;
                                        }
                                        DanhMuc danhmuccap1 = null;
                                        System.out.println("Nhập nhóm danh mục cần sửa");
                                        Boolean CoHieu = true;
                                        while (CoHieu) {
                                            int nhom;
                                            try {
                                                nhom = Integer.parseInt(sc.nextLine());
                                                CoHieu = false;
                                                danhmuccap1 = dsdanhmuc.getDsDanhMuc().get(nhom - 1);

                                            } catch (IndexOutOfBoundsException x) {
                                                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                                CoHieu = true;
                                            } catch (NumberFormatException e) {
                                                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                                CoHieu = true;

                                            }
                                        }
                                        if (danhmuccap1.getdanhsachdanhmuccon().isEmpty()) {
                                            System.out.println("Nhóm bạn chọn không còn danh mục để sửa ");
                                            System.out.println("Nhấn 1 phím bất kỳ để quay trở lại ");
                                            sc.nextLine();
                                            return;
                                        }
                                        else if(danhmuccap1.getdanhsachdanhmuccon().size()==1){
                                            DanhMuc danhmuccansua = danhmuccap1.getdanhsachdanhmuccon().get(0);
                                            name1 = danhmuccansua.gettendanhmuc();
                                    }   else {
                                            System.out.println("Trong nhóm bạn chọn bao gồm : ");
                                            int pos = 1;

                                            for (DanhMuc list : danhmuccap1.getdanhsachdanhmuccon()) {
                                                System.out.println(pos + ": " + list.gettendanhmuc());
                                                pos++;
                                            }
                                            Boolean Cohieu = true;
                                            while (Cohieu) {
                                                int position;
                                                try {
                                                    System.out.println("Nhập vị trí danh mục bạn muốn sửa");
                                                    position = Integer.parseInt(sc.nextLine());
                                                    Cohieu = false;
                                                    DanhMuc danhmuccansua = danhmuccap1.getdanhsachdanhmuccon().get(position - 1);
                                                    name1 = danhmuccansua.gettendanhmuc();
                                                } catch (IndexOutOfBoundsException x) {
                                                    System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                                    Cohieu = true;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                                    Cohieu = true;

                                                }
                                            }
                                        }
                                            System.out.println("Nhập tên danh mục mới");
                                            String name2 = sc.nextLine();
                                            if (getDanhMucChi().timdanhmuctheoten(getDanhMucChi().getDsDanhMuc(), name2) != null && getDanhMucThu().timdanhmuctheoten(getDanhMucChi().getDsDanhMuc(), name2) != null) {
                                                System.out.println("Danh mục bị trùng hãy nhập lại");
                                                name2 = sc.nextLine();
                                            }
                                            dsdanhmuc.EditDanhMucCon(name1, name2);
                                                System.out.println("Sửa danh tên danh mục thành công");
                                             System.out.println("BẠN CÓ MUỐN SỬA TIẾP DANH MỤC NÀO KHÔNG");
                                             System.out.println("1:YES ");
                                             System.out.println("Các nút còn lại:BACK VỀ MENU ");
                                            try{
                                               int choice2 = Integer.parseInt(sc.nextLine());
                                               if (choice2 == 1) {

                                             chonDanhMucDeSua();
                                             }
                                             else{
                                                 return;
                                             }
                                            }catch(NumberFormatException e){
                                                return;
                                           }

                                }

                }
            } catch (NumberFormatException e) {
                System.out.println("Bạn chỉ được nhâp 1 hoặc 2");
            }
        }


    public static Boolean isInteger(String s) {
        String regex = "^?\\d+$";
        Boolean flat = s.matches(regex);
        return flat;
    }

    public static Boolean Kiemtrangayhople(String s) {
        if (isInteger(s)) {
            int ngay = Integer.parseInt(s);
            while (ngay < 31 && ngay > 1) {
                return true;
            }
        }
        return false;
    }

    public static Boolean Kiemtrathanghople(String s) {
        if (isInteger(s)) {
            int thang = Integer.parseInt(s);
            while (thang <= 12 && thang >= 1) {
                return true;
            }
        }
        return false;
    }

    public static Boolean Kiemtranamhople(String s) {
        if (isInteger(s)) {
            int nam = Integer.parseInt(s);
            while (nam <= LocalDate.now().getYear() && nam >= 2000) {
                return true;
            }
        }
        return false;
    }

    public void HienThiDanhMuc() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------DANH MỤC CHI LÀ---------------------------------------------------------------");
        this.getDanhMucChi().lietkedanhmuc();
        System.out.println("Tổng số tiền của danh mục chi là " + this.getDanhMucChi().getTongsotien() + "đ");
        System.out.println("---------------------------------------------------------------DANH MỤC THU LÀ---------------------------------------------------------------");
        this.getDanhMucThu().lietkedanhmuc();
        System.out.println("Tổng số tiền của danh mục thu là " + this.getDanhMucThu().getTongsotien() + "đ");
//        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("NHẤN 1 NÚT BẤT KỲ ĐỂ BACK VỀ MENU");
        sc.nextLine();
    }

    public void hienThiLichSuGiaoDich() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Bạn muốn hiển thị lịch sử giao dịch theo thời gian hay danh mục ");
            System.out.println("1.Hien thị toàn bộ lịch sử giao dịch");
            System.out.println("2.Hiển thị theo loaị danh mục");
            System.out.println("3.Hiển thị theo tên danh mục");
            System.out.println("4.Hiển thị theo ngày tháng năm");
            System.out.println("Ấn 1 nút bất kỳ để trở về menu");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                //1.hien thi toan bo lich su giao dich
                if(getDsgiaodich().getDsGD().size()>0) {
                    System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                    System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                        System.out.println(lichsu.toStringGiaoDich());
                    }
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();
                }
                else{
                    System.out.println("Không tìm thấy kết quả ");
                    System.out.println("Nhấn 1 phím bất kỳ để trở về menu");
                    sc.nextLine();
                }
            }
            //2.1 hien thi theo loai danh muc
            else if (choice == 2) {
                //2.1.1 danh muc thu hay chi
                System.out.println("Bạn muốn hiển thị lịch sử theo danh mục thu hay chi");
                System.out.println("1.Chi");
                System.out.println("2.Thu");
                String kiemtra = sc.nextLine();
                while (!isInteger(kiemtra) || Integer.parseInt(kiemtra) < 1 || Integer.parseInt(kiemtra) > 2) {
                    System.out.println("Hãy nhập lại");
                    kiemtra = sc.nextLine();
                }
                int luachon = Integer.parseInt(kiemtra);
                if (luachon == 1) {
                    //1.hien thi lich su giao dich chi
                    Boolean flag=true;
                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                        if(lichsu.getLoaigiaodich().equals("Giao dịch chi")) {
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) {
                        System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                        System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                        for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                            if (lichsu.getLoaigiaodich().equals("Giao dịch chi")) {
                                System.out.println(lichsu.toStringGiaoDich());
                            }
                        }
                    }
                    else{
                        System.out.println("Không tìm thấy dữ liệu");
                    }
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();
                } else if (luachon == 2) {
                    //1.hien thi lich su giao dich thu
                    Boolean flag=true;
                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                        if(lichsu.getLoaigiaodich().equals("Giao dịch thu")) {
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) {
                        System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                        System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                        for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                            if (lichsu.getLoaigiaodich().equals("Giao dịch thu")) {
                                System.out.println(lichsu.toStringGiaoDich());
                            }
                        }
                    }
                    else{
                        System.out.println("Không tìm thấy dữ liệu");
                    }
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();
                }
            } else if (choice == 3) { //Hien thi theo ten danh muc

                System.out.println("Tên danh mục bạn muốn hiển thị trong lịch sử thuộc loại danh mục nào ");
                System.out.println("1.Chi ");
                System.out.println("2.Thu");
                //Loai danh muc
                String kiemtra = sc.nextLine();
                while (!isInteger(kiemtra) || Integer.parseInt(kiemtra) < 1 || Integer.parseInt(kiemtra) > 2) {
                    System.out.println("Hãy nhập lại");
                    kiemtra = sc.nextLine();
                }
                int luachon = Integer.parseInt(kiemtra);
                if (luachon == 1) { // Hien thi theo ten danh muc chi
                    String name = null;
                    DanhMuc DanhMucCanGiaoDich = null;
                    int i = 1;
                    for (DanhMuc danhmuc : this.getDanhMucChi().getDsDanhMuc()) {
                            System.out.println("Nhóm " + i + ": " + danhmuc.gettendanhmuc());
                        int x = 1;
                        for (DanhMuc danhmuccon : danhmuc.getdanhsachdanhmuccon()) {

                            System.out.println("     " + i+"."+x + ". " + danhmuccon.gettendanhmuc());
                            x++;
                        }
                        i++;
                    }

                    System.out.println("Vui lòng chọn nhóm :");
                    Boolean flat = true;
                    while (flat) {
                        try {
                            int chonnhom = Integer.parseInt(sc.nextLine());
                            DanhMuc DanhMucCap1 = this.getDanhMucChi().getDsDanhMuc().get(chonnhom - 1);
                            if (DanhMucCap1.getdanhsachdanhmuccon().size() > 0) {
                                int x = 1;
                                for (DanhMuc danhmuc : DanhMucCap1.getdanhsachdanhmuccon()) {
                                    System.out.println(x + "." + danhmuc.gettendanhmuc());
                                    x++;
                                }
                                int luachon1 = Integer.parseInt(sc.nextLine());
                                DanhMucCanGiaoDich = DanhMucCap1.getdanhsachdanhmuccon().get(luachon1 - 1);
                                Boolean flag=true;
                                for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                                    if(lichsu.getTendanhmuc() == DanhMucCanGiaoDich) {
                                        flag = false;
                                        break;
                                    }
                                }
                                if(!flag) {
                                    System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                                    System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                                        if (lichsu.getTendanhmuc() == DanhMucCanGiaoDich) {
                                            System.out.println(lichsu.toStringGiaoDich());
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Không tìm thấy dữ liệu");
                                }
                                System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                                sc.nextLine();
                                return;
                            } else {
                                System.out.println("Nhóm bạn chọn hiện đang rỗng");
                                System.out.println("Nhập số nếu tiếp tục ,Hãy nhập chữ để về menu");
                                flat = true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Nhóm bạn nhập nằm ngoài chỉ số");
                            System.out.println("Nhập số nếu tiếp tục ,Hãy nhập chữ để về menu");
                            flat = true;
                        } catch (NumberFormatException e) {
                            return;
                        }
                    }
                } else if (luachon == 2) {
                    {
                        // Hien thi theo ten danh muc thu
                        DanhMuc DanhMucCanGiaoDich = null;
                        int i = 1;
                        for (DanhMuc danhmuc : this.getDanhMucThu().getDsDanhMuc()) {
                                System.out.println("Nhóm " + i + ": " + danhmuc.gettendanhmuc());
                            int x = 1;
                            for (DanhMuc danhmuccon : danhmuc.getdanhsachdanhmuccon()) {
                                System.out.println("      " +i+"."+ x + ". " + danhmuccon.gettendanhmuc());
                                x++;
                            }
                            i++;
                        }

                        System.out.println("Vui lòng chọn nhóm :");
                        Boolean flat = true;
                        while (flat) {
                            try {
                                int chonnhom = Integer.parseInt(sc.nextLine());
                                DanhMuc DanhMucCap1 = this.getDanhMucThu().getDsDanhMuc().get(chonnhom - 1);
                                if (DanhMucCap1.getdanhsachdanhmuccon().size() > 0) {
                                    int x = 1;
                                    for (DanhMuc danhmuc : DanhMucCap1.getdanhsachdanhmuccon()) {
                                        System.out.println(x + "." + danhmuc.gettendanhmuc());
                                        x++;
                                    }
                                    int luachon1 = Integer.parseInt(sc.nextLine());
                                    DanhMucCanGiaoDich = DanhMucCap1.getdanhsachdanhmuccon().get(luachon1 - 1);
                                    Boolean flag=true;
                                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                                        if(lichsu.getTendanhmuc() == DanhMucCanGiaoDich) {
                                            flag = false;
                                            break;
                                        }
                                    }
                                    if(!flag) {
                                        System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                                        System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                                        for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                                            if (lichsu.getTendanhmuc() == DanhMucCanGiaoDich) {
                                                System.out.println(lichsu.toStringGiaoDich());
                                            }
                                        }
                                    }
                                    else{
                                        System.out.println("Không tìm thấy dữ liệu");
                                    }
                                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                                    sc.nextLine();
                                } else {
                                    System.out.println("Nhóm bạn chọn hiện đang rỗng");
                                    System.out.println("Nhập số nếu tiếp tục ,Hãy nhập chữ để về menu");
                                    flat = true;
                                }
                            } catch (IndexOutOfBoundsException e) {


                                System.out.println("Nhóm bạn nhập nằm ngoài chỉ số");
                                System.out.println("Nhập số nếu tiếp tục , nhập chữ để về menu");
                                flat = true;
                            } catch (NumberFormatException e) {
                                return;
                            }
                        }
                    }
                }


            } else if (choice == 4) { // Hien thi danh muc theo thoi gian
                System.out.println("1:Hiển thị lịch sử giao dịch hôm nay");
                System.out.println("2:Tùy chỉnh");
                String test = sc.nextLine();
                if (!isInteger(test)) {
                    System.out.println("Hãy nhập lại");
                    test = sc.nextLine();
                }
                int luachon = Integer.parseInt(test);
                if (luachon == 1) {
                    int year = getdateToDay().getnam();
                    int month = getdateToDay().getthang();
                    int day = getdateToDay().getngay();
                    Boolean flag=true;
                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                        if(lichsu.getNgayGiaoDich().getngay() == day && lichsu.getNgayGiaoDich().getnam() == year && lichsu.getNgayGiaoDich().getthang() == month) {
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) {
                        //Hien thi theo ngay thang nam
                        System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                        System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                        for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                            if (lichsu.getNgayGiaoDich().getngay() == day && lichsu.getNgayGiaoDich().getnam() == year && lichsu.getNgayGiaoDich().getthang() == month) {
                                System.out.println(lichsu.toStringGiaoDich());
                            }
                        }
                    }
                    else{
                        System.out.println("Không tìm thấy dữ liệu");
                    }
                    System.out.println("Nhấn 1 nút để quay về menu");
                    sc.nextLine();

                } else if (luachon == 2) {
                    System.out.println("1.Hiển thị theo ngày tháng năm cụ thể ");
                    System.out.println("2.Hiển thị theo tháng");
                    System.out.println("3.Hiển thị theo năm ");
                    System.out.println("Các nút còn lại back về menu ");
                    String input = sc.nextLine();
                    while (!isInteger(input) && Integer.parseInt(input) > 3 || Integer.parseInt(input) < 1) {
                        input = sc.nextLine();
                    }
                    int luachon2 = Integer.parseInt(input);
                    switch (luachon2) {
                        case 1:
                            System.out.println("Nhập ngày :");
                            String kiemtra1 = sc.nextLine();
                            while (!Kiemtrangayhople(kiemtra1)) {
                                System.out.println("Nhập ngày không hợp lệ vui lòng nhập lại:");
                                kiemtra1 = sc.nextLine();
                            }
                            int ngay = Integer.parseInt(kiemtra1);
                            System.out.println("Nhập tháng :");
                            kiemtra1 = sc.nextLine();
                            while (!Kiemtrathanghople(kiemtra1)) {
                                System.out.println("Nhập tháng không hợp lệ vui lòng nhập lại:");
                                kiemtra1 = sc.nextLine();
                            }
                            int thang = Integer.parseInt(kiemtra1);
                            System.out.println("Nhập năm :");
                            kiemtra1 = sc.nextLine();
                            while (!Kiemtranamhople(kiemtra1)) {
                                System.out.println("Nhập năm không hợp lệ vui lòng nhập lại:");
                                kiemtra1 = sc.nextLine();
                            }
                            int nam = Integer.parseInt(kiemtra1);
                            Boolean flag=true;
                            for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                                if(lichsu.getNgayGiaoDich().getngay() == ngay && lichsu.getNgayGiaoDich().getnam() == nam && lichsu.getNgayGiaoDich().getthang() == thang) {
                                    flag = false;
                                    break;
                                }
                            }
                            if(!flag) {
                                System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                                System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                                for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                                    if (lichsu.getNgayGiaoDich().getngay() == ngay && lichsu.getNgayGiaoDich().getnam() == nam && lichsu.getNgayGiaoDich().getthang() == thang) {
                                        System.out.println(lichsu.toStringGiaoDich());
                                    }
                                }
                            }
                            else{
                                System.out.println("Không tìm thấy dữ liệu");
                            }
                            System.out.println("Ấn 1 nút để back về menu");
                            sc.nextLine();
                            break;
                        case 2:
                            System.out.println("Nhập tháng :");
                            String kiemtra2 = sc.nextLine();
                            while (!Kiemtrathanghople(kiemtra2)) {
                                System.out.println("Nhập tháng không hợp lệ vui lòng nhập lại:");
                                kiemtra2 = sc.nextLine();
                            }
                            thang = Integer.parseInt(kiemtra2);
                            System.out.println("Nhập năm :");
                            kiemtra2 = sc.nextLine();
                            while (!Kiemtranamhople(kiemtra2)) {
                                System.out.println("Nhập năm không hợp lệ vui lòng nhập lại:");
                                kiemtra2 = sc.nextLine();
                            }

                            nam = Integer.parseInt(kiemtra2);
                            flag=true;
                            for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                                if(lichsu.getNgayGiaoDich().getnam() == nam && lichsu.getNgayGiaoDich().getthang() == thang) {
                                    flag = false;
                                    break;
                                }
                            }
                            if(!flag) {
                                System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                                System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                                for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                                    if (lichsu.getNgayGiaoDich().getnam() == nam && lichsu.getNgayGiaoDich().getthang() == thang) {
                                        System.out.println(lichsu.toStringGiaoDich());
                                    }
                                }
                            }
                            else{
                                System.out.println("Không tìm thấy dữ liệu");
                            }
                            System.out.println("Ấn 1 nút để back về menu");
                            sc.nextLine();
                            break;
                        case 3:
                            System.out.println("Nhập năm :");
                            String kiemtra3 = sc.nextLine();
                            while (!Kiemtranamhople(kiemtra3)) {
                                System.out.println("Nhập năm không hợp lệ vui lòng nhập lại:");
                                kiemtra3 = sc.nextLine();
                            }
                            nam = Integer.parseInt(kiemtra3);
                            flag=true;
                            for (GiaoDich lichsu : getDsgiaodich().getDsGD()){
                                if(lichsu.getNgayGiaoDich().getnam() == nam) {
                                    flag = false;
                                    break;
                                }
                            }
                            if(!flag) {
                                System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                                System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                                for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                                    if (lichsu.getNgayGiaoDich().getnam() == nam) {
                                        System.out.println(lichsu.toStringGiaoDich());
                                    }
                                }
                            }
                            else{
                                System.out.println("Không tìm thấy dữ liệu");
                            }
                            System.out.println("Ấn 1 nút để back về menu");
                            sc.nextLine();
                            break;
                        default: return;
                    }
                }
            }
            else{
                return;
            }

            //2.2.1 hien thi theo ngày tháng năm
            //2.2.1.1 hien thi theo ngay (nhập năm nhập tháng nhập ngày)
            //2.2.1.2 hien thi theo thang( nhap năm với nhập tháng)
            //2.2.1.3 hien thi theo nam( Nhập năm)

        } catch (NumberFormatException e) {
            // back ve menu o day
            //KHÔNG CẦN XỬ LÝ Ở ĐÂY
        }

    }

    public void timkiemthongtingiaodich() {
        Scanner sc = new Scanner(System.in);
        ArrayList<GiaoDich> ketquatimkiem = new ArrayList<>();
        System.out.println("Bạn hãy nhập nội dung giao dịch ");
                String find = sc.nextLine();
                for (GiaoDich giaodich : this.getDsgiaodich().getDsGD()) {
                    String thongtingiaodich = giaodich.getThongtingiaodich();
                    int timthay = thongtingiaodich.indexOf(find);
                    if (timthay != -1) {
                        ketquatimkiem.add(giaodich);

                    }
                }
                if(ketquatimkiem.size()>0){
                    System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                    System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                for (GiaoDich output : ketquatimkiem) {
                    System.out.println(output.toStringGiaoDich());
                }
                System.out.println("Nhấn 1 phím bất kỳ để trở về menu");
                sc.nextLine();
                }
                else{
                    System.out.println("Không tìm thấy kết quả ");
                    System.out.println("Nhấn 1 phím bất kỳ để trở về menu");
                    sc.nextLine();
                }
    }

    public String BaoCaoChiTietTungDanhMuc() {// abstract
        String ketqua=null;
        StringBuilder report = new StringBuilder("\t\tBÁO CÁO CHI TIẾT THEO DANH MỤC\n");
        report.append("\tI.Mục đích và tầm quan trọng\n");
        report.append("\t- Chức năng Chức năng \"Xem Báo Cáo chi tiết theo từng danh mục và thời gian\" là một phần quan trọng trong hệ thống quản lý tài chính cá nhân.\n");
        report.append("\t-Chức năng này giúp người dùng:\n");
        report.append("\t.Theo dõi Chi Tiêu: Hiểu rõ hơn về cách họ tiêu tiền theo từng danh mục và trong khoảng thời gian cụ thể\n");
        report.append("\t.Quản Lý Ngân Sách: Đặt ngân sách cho từng danh mục và theo dõi xem họ đã tuân thủ ngân sách hay chưa\n");
        report.append("\tII.Lựa Chọn Danh Mục,Thời Gian và hiển thị báo cáo chi tiết\n");
        System.out.println(report.toString());
        report.append("\t-Theo danh mục: \n");
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn xem báo cáo chi tiết theo loại danh mục nào ");
        System.out.println("1.Chi ");
        System.out.println("2.Thu");
        //Loai danh muc
        String kiemtra = sc.nextLine();
        while (!isInteger(kiemtra) || Integer.parseInt(kiemtra) < 1 || Integer.parseInt(kiemtra) > 2) {
            System.out.println("Hãy nhập lại");
            kiemtra = sc.nextLine();
        }
        int luachon = Integer.parseInt(kiemtra);
        if (luachon == 1) {// Hien thi theo ten danh muc chi
            String name = null;
            DanhMuc DanhMucCanXem = null;
            report.append("\tLoại danh mục: Chi\n");
            int i = 1;
            for (DanhMuc danhmuc : this.getDanhMucChi().getDsDanhMuc()) {
                System.out.println("Nhóm " + i + ": " + danhmuc.gettendanhmuc());
                int x = 1;
                for (DanhMuc danhmuccon : danhmuc.getdanhsachdanhmuccon()) {

                    System.out.println("     " + i+"."+x + ". " + danhmuccon.gettendanhmuc());
                    x++;
                }
                i++;
            }
            System.out.println("Vui lòng chọn nhóm(Nhập chữ để thoát) :");
            Boolean flat = true;
            while (flat) {
                try {

                    int chonnhom = Integer.parseInt(sc.nextLine());
                    DanhMuc DanhMucCap1 = this.getDanhMucChi().getDsDanhMuc().get(chonnhom - 1);
                    report.append("\tDanh mục cha: ").append(DanhMucCap1.gettendanhmuc()).append("\n");
                    if (DanhMucCap1.getdanhsachdanhmuccon().size() > 0) { //Kiểm tra kích thước của danh mục
                        int x = 1;
                        for (DanhMuc danhmuc : DanhMucCap1.getdanhsachdanhmuccon()) {
                            System.out.println(x + "." + danhmuc.gettendanhmuc());
                            x++;
                        }
                        System.out.println("Bạn muốn chọn danh mục nào (Chọn số)?");
                        int luachon1 = Integer.parseInt(sc.nextLine());
                        DanhMucCanXem = DanhMucCap1.getdanhsachdanhmuccon().get(luachon1 - 1);// chọn ra 1 danh mục con
                        report.append("\tDanh mục con: ").append(DanhMucCanXem.gettendanhmuc()).append("\n");
                        int SoLanGiaoDichCuaDanhMuc = 0; // tổng số lần giao dịch của danh mục
                        String loaigd = null;
                        for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                            if (lichsu.getTendanhmuc() == DanhMucCanXem) {
                                loaigd = lichsu.getLoaigiaodich();
                                SoLanGiaoDichCuaDanhMuc++;
                            }
                        }
                        System.out.println("Tổng số lần giao dịch của danh mục này là: " + SoLanGiaoDichCuaDanhMuc);
                        report.append("\tTổng số lần giao dịch của danh mục này là: ").append(SoLanGiaoDichCuaDanhMuc).append("\n");
                        //Bạn muốn xem dữ liệu theo tuần , theo tháng ,theo năm
                        report.append("\t-Theo thời gian: \n");
                        System.out.println("Bạn muốn xem dữ liệu theo");
                        System.out.println("1.Tuần");
                        System.out.println("2.Tháng");
                        System.out.println("3.Năm");

                        Boolean Cohieu = true;
                        while (Cohieu) {
                            try {
                                
                                int choice = Integer.parseInt(sc.nextLine());
                                if (choice == 1) { // xem dữ liệu danh mục theo tuần
                                    report.append("\tXem dữ liệu danh mục theo tuần\n");
                                    Cohieu = false;
                                    System.out.println("Vui lòng nhập năm");
                                    String test = sc.nextLine();
                                    while (!Kiemtranamhople(test)) {
                                        System.out.println("Hãy nhập lại");
                                        test = sc.nextLine();
                                    }
                                    int Year = Integer.parseInt(test);
                                    System.out.println("vui lòng nhập tháng");
                                    test = sc.nextLine();
                                    while (!Kiemtrathanghople(test)) {
                                        System.out.println("Hãy nhập lại ");
                                        test = sc.nextLine();
                                    }
                                    int Month = Integer.parseInt(test);
                                    
                                    report.append("\tNăm: ").append(Year).append("\n");
                                    report.append("\tTháng: ").append(Month).append("\n");
                                    
                                    ketqua = thongkedanhmuctheotuan(Month, Year, DanhMucCanXem, loaigd); // ghi file ở khúc này
                                    System.out.println(ketqua);
                                    
                                    report.append("\t").append(ketqua).append("\n");
                                } else if (choice == 2) {// xem dữ liệu danh mục theo tháng
                                    report.append("\tXem dữ liệu danh mục theo tháng\n");
                                    Cohieu = false;
                                    System.out.println("Vui lòng nhập năm");
                                    String test = sc.nextLine();
                                    while (!Kiemtranamhople(test)) {
                                        System.out.println("Hãy nhập lại ");
                                        test = sc.nextLine();
                                    }
                                    int Year = Integer.parseInt(test);
                                    
                                    report.append("\tNăm: ").append(Year).append("\n");
                                    
                                    ketqua = thongkedanhmuctheothang(Year, DanhMucCanXem, loaigd);// ghi file ở khúc này
                                    System.out.println(ketqua);
                                    
                                    report.append("\t").append(ketqua).append("\n");
                                } else if (choice == 3) {// xem dữ liệu danh mục theo năm
                                    report.append("\tXem dữ liệu danh mục theo năm\n");
                                    Cohieu = false;
                                    System.out.println("Vui lòng nhập năm gần đây ( trong phạm vi trừ 2 đến 10)");
                                    String test = sc.nextLine();
                                    while (!isInteger(test) || Integer.parseInt(test) < 2 || Integer.parseInt(test) > 10) {
                                        System.out.println("Bạn đã nhập không hợp lệ vui lòng nhập lại");
                                        test=sc.nextLine();
                                    }
                                    int NamGanDay = Integer.parseInt(test);
                                    
                                    report.append("\t").append(NamGanDay).append(" năm gần đây: ").append("\n");
                                    
                                    ketqua = thongkedanhmuctheonam(NamGanDay, DanhMucCanXem, loaigd);// ghi file ở khúc này
                                    System.out.println(ketqua);
                                    report.append("\t").append(ketqua).append("\n");
                                } else {
                                    System.out.println("Vui lòng nhập lại");
                                    Cohieu = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Vui lòng nhập lại");
                                Cohieu = true;
                            }
                        }

                        System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
//                        sc.nextLine();

                    } else {
                        System.out.println("Nhóm bạn chọn hiện đang rỗng");
                        System.out.println("Nhập số nếu tiếp tục ,Hãy nhập chữ để về menu");
                        flat = true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Nhóm bạn nhập nằm ngoài chỉ số");
                    System.out.println("Nhập số nếu tiếp tục ,Hãy nhập chữ để về menu");
                    flat = true;
                }
                 catch (NumberFormatException e) {
                    flat=false;
                }
            }
        } else if (luachon == 2) {
            { //hiển thị theo danh mục thu
                String name = null;
                DanhMuc DanhMucCanXem = null;
                report.append("\tLoại danh mục: Thu\n");
                int i = 1;
                for (DanhMuc danhmuc : this.getDanhMucThu().getDsDanhMuc()) {
                    System.out.println("Nhóm " + i + ": " + danhmuc.gettendanhmuc());
                    int x = 1;
                    for (DanhMuc danhmuccon : danhmuc.getdanhsachdanhmuccon()) {

                        System.out.println("     " + i+" "+x + ". " + danhmuccon.gettendanhmuc());
                        x++;
                    }
                    i++;
                }
                System.out.println("Vui lòng chọn nhóm(Nhập chữ để thoát) :");
                Boolean flat = true;
                while (flat) {
                    try {
                        int chonnhom = Integer.parseInt(sc.nextLine());
                        DanhMuc DanhMucCap1 = this.getDanhMucThu().getDsDanhMuc().get(chonnhom - 1);
                        report.append("\tDanh mục cha: ").append(DanhMucCap1.gettendanhmuc()).append("\n");
                        if (DanhMucCap1.getdanhsachdanhmuccon().size() > 0) { //Kiểm tra kích thước của danh mục
                            int x = 1;
                            for (DanhMuc danhmuc : DanhMucCap1.getdanhsachdanhmuccon()) {
                                System.out.println(x + "." + danhmuc.gettendanhmuc());
                                x++;
                            }
                            System.out.println("Bạn muốn giao dịch với danh mục con nào");
                            int luachon1 = Integer.parseInt(sc.nextLine());
                            DanhMucCanXem = DanhMucCap1.getdanhsachdanhmuccon().get(luachon1 - 1);// chọn ra 1 danh mục con
                            report.append("\tDanh mục con: ").append(DanhMucCanXem.gettendanhmuc()).append("\n");
                            int SoLanGiaoDichCuaDanhMuc = 0; // tổng số lần giao dịch của danh mục
                            String loaigd = null;
                            for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                                if (lichsu.getTendanhmuc() == DanhMucCanXem) {
                                    loaigd = lichsu.getLoaigiaodich();
                                    SoLanGiaoDichCuaDanhMuc++;
                                }
                            }
                            System.out.println("Tổng số lần giao dịch của danh mục này là: " + SoLanGiaoDichCuaDanhMuc);
                            report.append("\tTổng số lần giao dịch của danh mục này là: ").append(SoLanGiaoDichCuaDanhMuc).append("\n");
                            //Bạn muốn xem dữ liệu theo tuần , theo tháng ,theo năm
                            System.out.println("Bạn muốn xem dữ liệu theo");
                            System.out.println("1.Tuần");
                            System.out.println("2.Tháng");
                            System.out.println("3.Năm");

                            Boolean Cohieu = true;
                            while (Cohieu) {
                                try {
                                    int choice = Integer.parseInt(sc.nextLine());
                                    if (choice == 1) { // xem dữ liệu danh mục theo tuần
                                        Cohieu = false;
                                        report.append("\tXem dữ liệu danh mục theo tuần\n");
                                        System.out.println("Vui lòng nhập năm");
                                        String test = sc.nextLine();
                                        while (!Kiemtranamhople(test)) {
                                            System.out.println("Hãy nhập lại ");
                                            test = sc.nextLine();
                                        }
                                        int Year = Integer.parseInt(test);
                                        System.out.println("vui lòng nhập tháng");
                                        test = sc.nextLine();
                                        while (!Kiemtrathanghople(test)) {
                                            System.out.println("Hãy nhập lại ");
                                            test = sc.nextLine();
                                        }
                                        int Month = Integer.parseInt(test);
                                        
                                        report.append("\tNăm: ").append(Year).append("\n");
                                        report.append("\tTháng: ").append(Month).append("\n");
                                        
                                        ketqua = thongkedanhmuctheotuan(Month, Year, DanhMucCanXem, loaigd); // ghi file trong hàm này dòng 1644
                                        System.out.println(ketqua);
                                        
                                        report.append("\t").append(ketqua).append("\n");
                                    } else if (choice == 2) {// xem dữ liệu danh mục theo tháng
                                        report.append("\tXem dữ liệu danh mục theo tháng\n");
                                        Cohieu = false;
                                        System.out.println("Vui lòng nhập năm");
                                        String test = sc.nextLine();
                                        while (!Kiemtranamhople(test)) {
                                            System.out.println("Hãy nhập lại");
                                            test = sc.nextLine();
                                        }
                                        int Year = Integer.parseInt(test);
                                        
                                        report.append("\tNăm: ").append(Year).append("\n");
                                        
                                        ketqua=thongkedanhmuctheothang(Year, DanhMucCanXem, loaigd);
                                        System.out.println(ketqua);
                                        
                                        report.append("\t").append(ketqua).append("\n");
                                    } else if (choice == 3) {// xem dữ liệu danh mục theo năm
                                        report.append("\tXem dữ liệu danh mục theo năm\n");
                                        Cohieu = false;
                                        System.out.println("Vui lòng nhập năm gần đây ( trong phạm vi trừ 2 đến 10)");
                                        String test = sc.nextLine();
                                        while (!isInteger(test) || Integer.parseInt(test) < 2 || Integer.parseInt(test) > 10) {
                                            System.out.println("Bạn đã nhập không hợp lệ vui lòng nhập lại");
                                            test = sc.nextLine();
                                        }
                                        int NamGanDay = Integer.parseInt(test);
                                        report.append("\t").append(NamGanDay).append(" năm gần đây: ").append("\n");
                                        ketqua=thongkedanhmuctheonam(NamGanDay, DanhMucCanXem, loaigd);
                                        System.out.println(ketqua);
                                        
                                        report.append("\t").append(ketqua).append("\n");
                                    } else {
                                        System.out.println("Vui lòng nhập lại");
                                        Cohieu = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Vui lòng nhập lại");
                                    Cohieu = true;
                                }
                            }
                            System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");

                        } else {
                            System.out.println("Nhóm bạn chọn hiện đang rỗng");
                            System.out.println("Nhập số nếu tiếp tục ,Hãy nhập chữ để về menu");
                            flat = true;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Nhóm bạn nhập nằm ngoài chỉ số");
                        System.out.println("Nhập số nếu tiếp tục ,Hãy nhập chữ để về menu");
                        flat = true;
                    } catch (NumberFormatException e) {
                        flat=false;
                        return "Không có danh mục cấp 1";

                    }
                }
            }
        }
        //System.out.println(ketqua);
        report.append("\tIII.Kết Luận và Hướng Phát Triển Tương Lai\n");
        report.append("\t-Tóm Tắt Lợi Ích và Tính Năng\n");
        report.append("\t.Chức năng \"Xem Báo Cáo chi tiết theo từng danh "
                + "mục và thời gian\" mang lại nhiều lợi ích quan trọng cho người dùng trong quản lý tài chính cá nhân:\n");
        report.append("\t1.Theo Dõi Chi Tiêu Chi Tiết: Cho phép người dùng theo dõi mức chi tiêu của "
                + "họ theo từng danh mục cụ thể và trong các khoảng thời gian nhất định.\n");
        report.append("\t2.Quản Lý Ngân Sách Hiệu Quả: Hỗ trợ người dùng đặt ngân sách cho từng danh mục,"
                + " giúp họ theo dõi việc tuân thủ ngân sách và điều chỉnh chi tiêu nếu cần thiết.\n");
        report.append("\t3.Trực Quan Hóa Thông Tin: Sử dụng biểu đồ và bảng báo cáo để trực quan hóa thông tin, giúp người dùng nhanh chóng nhận diện xu hướng, mức độ quan trọng của từng danh mục và thay đổi theo thời gian.\n");
        report.append("\t4.Lọc và Sắp Xếp Linh Hoạt: Cung cấp tính năng lọc và sắp xếp, giúp người dùng tinh chỉnh báo cáo theo nhiều tiêu chí khác nhau, từ loại giao dịch đến mức độ quan trọng.\n");
        report.append("\t5.Xuất Báo Cáo Thuận Tiện: Cho phép người dùng xuất báo cáo chi tiết theo danh mục và thời gian vào các định dạng khác nhau như PDF hoặc CSV, giúp họ lưu trữ hoặc chia sẻ thông tin một cách thuận lợi.\n");
        report.append("\t- Hướng Phát Triển Tương Lai\n");
        report.append("\tĐể nâng cao trải nghiệm người dùng và mở rộng khả năng quản lý tài chính, có một số hướng phát triển có thể được xem xét trong tương lai:\n");
        report.append("\t1.Tích Hợp Thêm Nhiều Danh Mục và Thống Kê Nâng Cao: Mở rộng danh sách danh mục và thêm các thống kê nâng cao để cung cấp thông tin chi tiết hơn về xu hướng và biến động tài chính.\n");
        report.append("\t2.Giao Diện Người Dùng Tương Tác: Phát triển giao diện người dùng tương tác hơn, cho phép người dùng tương tác trực tiếp với báo cáo và biểu đồ, thực hiện phân tích chi tiết.\n");
        report.append("\t3.Dự Đoán Tài Chính Tương Lai: Tích hợp tính năng dự đoán để người dùng có thể xem trước chi tiêu trong tương lai dựa trên xu hướng hiện tại.\n");
        report.append("\t4.Kết Nối Với Ngân Hàng và Dịch Vụ Tài Chính Khác: Mở rộng tính năng kết nối để tự động đồng bộ dữ liệu từ ngân hàng và các dịch vụ tài chính khác, tăng tính chính xác và thuận tiện.\n");
        report.append("\t5.Phân Tích Dữ Liệu Sâu Rộng: Sử dụng công nghệ phân tích dữ liệu để cung cấp thông tin chi tiết và gợi ý thông minh dựa trên hành vi và xu hướng chi tiêu cụ thể của người dùng.\n");
        return report.toString();
    }
    public void thongketheokhoangthoigian() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder NgayBatDau = new StringBuilder();
        StringBuilder NgayKetThuc = new StringBuilder();

        System.out.println("----- TÌM KIẾM THEO KHOẢNG THỜI GIAN -----");
        System.out.println("Mời bạn nhập khoảng thời gian bắt đầu");
        System.out.print("Ngày: ");
        String ngay = scanner.nextLine();
        System.out.print("Tháng: ");
        String thang = scanner.nextLine();
        System.out.print("Năm: ");
        String nam = scanner.nextLine();

        NgayBatDau.append(ngay).append("/").append(thang).append("/").append(nam);
        String time1 = NgayBatDau.toString();
        Date startDay = sdf.parse(time1);

        System.out.println("Mời bạn nhập khoảng thời gian kết thúc");
        System.out.print("Ngày: ");
        ngay = scanner.nextLine();
        System.out.print("Tháng: ");
        thang = scanner.nextLine();
        System.out.print("Năm: ");
        nam = scanner.nextLine();

        NgayKetThuc.append(ngay).append("/").append(thang).append("/").append(nam);
        String time2 = NgayKetThuc.toString();

        Date endDay = sdf.parse(time2);

        ArrayList<GiaoDich> giaodich= new ArrayList<>();
        for(GiaoDich gd: this.getDsgiaodich().getDsGD()){
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toString(gd.getNgayGiaoDich().getngay())).append("/").append(Integer.toString(gd.getNgayGiaoDich().getthang()))
                    .append("/").append(Integer.toString(gd.getNgayGiaoDich().getnam()));
            Date ngayGiaoDich = sdf.parse(sb.toString());

            if(startDay.before(ngayGiaoDich)&&endDay.after(ngayGiaoDich)){
                giaodich.add(gd);
            }
        }
        int TienGiaoDichChi = 0;
        int TienGiaoDichThu = 0;

        for(GiaoDich gd : giaodich){
            if(gd.getLoaigiaodich().equals("Giao dịch chi")){
                TienGiaoDichChi += gd.getsotien();
            }else if(gd.getLoaigiaodich().equals("Giao dịch thu")){
                TienGiaoDichThu += gd.getsotien();
            }
        }

        if(giaodich.isEmpty()){
            System.out.println("Không có dữ liệu thống kê");
        }else{
            System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
            System.out.println("Từ " + NgayBatDau.toString() + " đến " + NgayKetThuc.toString());
            if(TienGiaoDichChi != 0 && TienGiaoDichThu != 0){
                System.out.println("-Giao dịch chi");
                System.out.println("Bạn đã sử dụng: " + TienGiaoDichChi + "Đ cho các loại giao dịch");
                System.out.println("-Giao dịch thu");
                System.out.println("Bạn đã sử dụng: " + TienGiaoDichThu + "Đ cho các loại giao dịch thu");
            }else if(TienGiaoDichChi != 0){
                System.out.println("-Giao dịch chi");
                System.out.println("Bạn đã sử dụng: " + TienGiaoDichChi + "Đ cho các loại giao dịch chi");
            }else if(TienGiaoDichThu != 0){
                System.out.println("-Giao dịch thu");
                System.out.println("Bạn đã sử dụng: " + TienGiaoDichThu + "Đ cho các loại giao dịch thu");
            }
        }

    }
    public void ThongKe() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*-------------------------------------------------------------THỐNG KÊ-----------------------------------------------------------------------*");
        System.out.println("*1.THỐNG KÊ THEO TUẦN                                                                                                                        *");
        System.out.println("*2.THỐNG KÊ THEO THÁNG                                                                                                                       *");
        System.out.println("*3.THỐNG KÊ THEO NĂM                                                                                                                         *");
        System.out.println("*4.THỐNG KÊ THEO KHOẢNG THỜI GIAN                                                                                                            *");
        System.out.println("*NHẬP MỘT NÚT BẤT KỲ ĐỂ TRỞ LẠI MENU                                                                                                         *");
        System.out.println("*____________________________________________________________________________________________________________________________________________*");
        int choose;
        Boolean flat = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
        try {
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {

                case 1:
                    System.out.println("Nhập năm:");
                    String year_1 = sc.nextLine();
                    while (!Kiemtranamhople(year_1)) {
                        System.out.println("Năm không hợp lệ, mời nhập lại");
                        year_1 = sc.nextLine();
                    }
                    System.out.println("Nhập tháng ");//thieu rang buoc
                    String month_1 = sc.nextLine();
                    while (!Kiemtrathanghople(month_1)) {
                        System.out.println("Tháng không hợp lệ, mời nhập lại");
                        month_1 = sc.nextLine();
                    }

                    thongke(Integer.parseInt(month_1), Integer.parseInt(year_1));
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");

                    sc.nextLine();
                    return;
                    

                case 2:
                    System.out.println("Nhập năm:");
                    String year_2 = sc.nextLine();
                    while (!Kiemtranamhople(year_2)) {
                        System.out.println("Năm không hợp lệ, mời nhập lại");
                        year_2 = sc.nextLine();
                    }
                    thongke(Integer.parseInt(year_2));
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();
                    return;

                case 3:
                    int currentYear = getdateToDay().getnam();
                    System.out.println("Số năm hiện tại là " + currentYear);
                    System.out.println("Nhập số năm gần đây nhất mà bạn muốn xem dữ liệu từ tính từ năm ( " + currentYear+" ) kể cả năm "+currentYear);
                    System.out.println("số năm gần đây phải nằm trong khoảng từ 2 đến 10)");
                    String test = sc.nextLine();
                    while (!isInteger(test) || Integer.parseInt(test)<2 ||  Integer.parseInt(test)>10) {
                        System.out.println("số năm gần đây không hợp lệ, mời nhập lại");
                        test = sc.nextLine();
                    }
                    int year=Integer.parseInt(test);
                    thongketheonam(year);
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();

                case 4:
                    thongketheokhoangthoigian();
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();
                    break;
                default:
                    return;

            }
        } catch (NumberFormatException | ParseException E) {
            //KHÔNG CẦN XỬ LÝ Ở ĐÂY
        }

    }

    public void setdanhmucchi(ListDanhMuc danhmucchi) {
        this.danhmucchi = danhmucchi;
    }

    public String thongkedanhmuctheotuan(int month, int year, DanhMuc danhMuc, String loaigd) { // theo tuan
        if (loaigd == null || danhMuc == null) {
            return "Bạn chưa có gd nào về danh mục này";
        } else {
            StringBuilder ketqua = new StringBuilder();
            int[] soLanGiaoDichMoiTuan = new int[4];
            int[] sotien = new int[4];
            for (int i = 0; i < 4; i++) {
                soLanGiaoDichMoiTuan[i] = 0;
                sotien[i] = 0;
            }
            for (GiaoDich gd : getDsgiaodich().getDsGD()) {
                if (gd.getNgayGiaoDich().getthang() == month && gd.getNgayGiaoDich().getnam() == year) {
                    int week = (gd.getNgayGiaoDich().getngay() - 1) / 7; // Xác định tuần
                    if (gd.getTendanhmuc() == danhMuc) {
                        soLanGiaoDichMoiTuan[week] += 1;
                        sotien[week] += gd.getsotien();
                    }
                }
            }
            if (TongSoTientrongdanhmuc(sotien) == 0) {
                return "Không có dữ liệu để hiển thị ";
            } else {
                if (loaigd.equals("Giao dịch chi")) {
                    for (int i = 0; i < 4; i++) {
                        //   if(soLanGiaoDichMoiTuan[i]>0) {
                        ketqua.append(" - Tuần ").append(i + 1).append(" :\n");
                        ketqua.append("Số lần giao dịch là: ").append(soLanGiaoDichMoiTuan[i]).append("\n");
                        ketqua.append("Số tiền sử dụng là: ").append(sotien[i]).append("\n");
                        // }
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        //  if(soLanGiaoDichMoiTuan[i]>0) {
                        ketqua.append(" - Tuần ").append(i + 1).append(" :\n");
                        ketqua.append("Số lần giao dịch là: ").append(soLanGiaoDichMoiTuan[i]).append("\n");
                        ketqua.append("Số tiền đã kiếm được là: ").append(sotien[i]).append("\n");
                        //  }
                    }
                }
                return ketqua.toString();
            }
        }
    }

    public String thongkedanhmuctheothang(int year, DanhMuc danhMuc, String loaigd) { // theo tháng
        if(loaigd==null || danhMuc==null){
            return "Bạn chưa có gd nào về danh mục này";
        }
        else {
            StringBuilder ketqua = new StringBuilder();
            int[] soLanGiaoDichMoiThang = new int[12];
            int[] sotien = new int[12];

            for (int i = 0; i < 12; i++) {
                soLanGiaoDichMoiThang[i] = 0;
                sotien[i] = 0;
            }

            for (GiaoDich gd : getDsgiaodich().getDsGD()) {
                if (gd.getNgayGiaoDich().getnam() == year) {
                    int month = gd.getNgayGiaoDich().getthang() - 1; // Số tháng từ 0 đến 11

                    if (gd.getTendanhmuc() == danhMuc) {
                        soLanGiaoDichMoiThang[month] += 1;
                        sotien[month] += gd.getsotien();
                    }
                }
            }
            if (TongSoTientrongdanhmuc(sotien) == 0){
                return "Không có dữ liệu để hiển thị ";
            }
            else {
                if (loaigd.equals("Giao dịch chi")) {
                    for (int i = 0; i < 12; i++) {
                        ketqua.append(" - Tháng ").append(i + 1).append(" :\n");
                        ketqua.append("Số lần giao dịch là: ").append(soLanGiaoDichMoiThang[i]).append("\n");
                        ketqua.append("Số tiền sử dụng là: ").append(sotien[i]).append("\n");
                    }
                } else {
                    for (int i = 0; i < 12; i++) {
                        ketqua.append(" - Tháng ").append(i + 1).append(" :\n");
                        ketqua.append("Số lần giao dịch là: ").append(soLanGiaoDichMoiThang[i]).append("\n");
                        ketqua.append("Số tiền đã kiếm được là: ").append(sotien[i]).append("\n");
                    }
                }
                return ketqua.toString();
            }

        }
    }

    public String thongkedanhmuctheonam(int sonamganday, DanhMuc danhMuc, String loaigd) { // theo tháng
        StringBuilder ketqua = new StringBuilder();
        if(loaigd==null || danhMuc==null){
            return "Bạn chưa có gd nào về danh mục này";
        }
        else {
            int currentYear = getdateToDay().getnam();
            int[] soLanGiaoDichTrongNhungNamGanDay = new int[sonamganday];
            int[] sotien = new int[sonamganday];

            for (int i = 0; i < sonamganday; i++) {
                soLanGiaoDichTrongNhungNamGanDay[i] = 0;
                sotien[i] = 0;
            }
            for (GiaoDich gd : getDsgiaodich().getDsGD()) {
                int transactionYear = gd.getNgayGiaoDich().getnam();
                int yearIndex = sonamganday - (currentYear - transactionYear) - 1;
                if (gd.getTendanhmuc() == danhMuc) {
                    soLanGiaoDichTrongNhungNamGanDay[yearIndex] += 1;
                    sotien[yearIndex] += gd.getsotien();
                }
            }
            if(TongSoTientrongdanhmuc(sotien)==0){
                return "Không có dữ liệu để hiển thị ";
            }
            else{
            if (loaigd.equals("Giao dịch chi")) {
                for (int i = sonamganday - 1; i >= 0; i--) {
                    ketqua.append(" - Năm ").append(currentYear - sonamganday+i+1).append(" :\n");
                    ketqua.append("Số lần giao dịch là: ").append(soLanGiaoDichTrongNhungNamGanDay[i]).append("\n");
                    ketqua.append("Số tiền đã kiếm được là: ").append(sotien[i]).append("\n");
                }
            } else {
                for (int i = sonamganday - 1; i >= 0; i--) {
                    ketqua.append(" - Năm ").append(currentYear - sonamganday+i+1).append(" :\n");
                    ketqua.append("Số lần giao dịch là: ").append(soLanGiaoDichTrongNhungNamGanDay[i]).append("\n");
                    ketqua.append("Số tiền đã kiếm được là: ").append(sotien[i]).append("\n");
                }
            }

            return ketqua.toString();
        }
        }
    }
    public abstract void thongke(int month , int year);//thong ke theo tuan
    public abstract void thongke(int year);// thong ke theo thang
    public abstract void thongketheonam(int soNamGanDay);//thong ke theo nam

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
    public int getSoDanhMuc(){
        return this.sodanhmuc;
    }
    public void setSoDanhMuc(int sodanhmuc){
        this.sodanhmuc=sodanhmuc;
    }
    public ListGiaoDich getDsgiaodich() {
        return dsgiaodich;
    }
    public void setDsgiaodich(ListGiaoDich dsgiaodich) {
        this.dsgiaodich = dsgiaodich;
    }

}
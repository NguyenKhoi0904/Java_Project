/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class QuanLyDanhMuc implements Serializable {
    private int sodanhmuc;
    private GioiHanGiaoDich solangiaodich;
    private ListDanhMuc danhmucchi;
    private ListDanhMuc danhmucthu;
    private ListGiaoDich dsgiaodich;


    public QuanLyDanhMuc(int soLanGiaoDich) {
        this.danhmucchi = new ListDanhMuc(new DanhMuc("TY1", "Thiết yếu"), new DanhMuc("BT1", "Biếu tặng"), new DanhMuc("SK1", "Sức khỏe"), new DanhMuc("GT", "Giải trí"));
        this.danhmucthu = new ListDanhMuc(new DanhMuc("L1", "Lương"), new DanhMuc("T1", "Thưởng"));
        this.dsgiaodich = new ListGiaoDich();
        this.sodanhmuc = 2;
        this.solangiaodich = new GioiHanGiaoDich(soLanGiaoDich);
    }
    public QuanLyDanhMuc() { // Prouser
        this.danhmucchi = new ListDanhMuc(new DanhMuc("TY1", "Thiết yếu"), new DanhMuc("BT1", "Biếu tặng"), new DanhMuc("SK1", "Sức khỏe"), new DanhMuc("GT", "Giải trí"));
        this.danhmucthu = new ListDanhMuc(new DanhMuc("L1", "Lương"), new DanhMuc("T1", "Thưởng"));
        this.dsgiaodich = new ListGiaoDich();
        this.sodanhmuc = 10;
        this.solangiaodich = new GioiHanGiaoDich();
    }

    private String generateID_DanhMuc(String prefix) {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 9));

        String formatNumber = String.format("%0" + 9 + "d", randomNumber);
        return prefix + formatNumber;
    }
    /*public void taoDanhMucmoithang(int thang,int nam){
            SoDanhMucMoiThang gioihandanhmuc = new SoDanhMucMoiThang(2,thang,nam);
            DsDanhMuc.add(gioihandanhmuc);
    }
    private SoDanhMucMoiThang kiemtradanhmucThang(int thang,int nam){
        int timthay = 0;
        for(SoDanhMucMoiThang danhmuc: DsDanhMuc){
            if(danhmuc.getThang()==thang && danhmuc.getNam()==nam){
              timthay=1;
              return danhmuc;

            }
        }
        return null;



    }*/

    public void ChonDanhMucDeThem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Số lần thêm danh mục của bạn còn " + getSoDanhMuc());
        if (getSoDanhMuc() > 0) {
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
                            return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Xin vui lòng nhập lại");
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

    private void ThemDanhMuc(ListDanhMuc danhmuc, String idDanhMuc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("VUI LÒNG NHẬP CÁC THÔNG TIN CÁC DANH MỤC CẦN THÊM");
        String id = generateID_DanhMuc(idDanhMuc);
        System.out.println("Tên danh mục cần thêm:                                                                 ");
        String name = sc.nextLine();
        DanhMuc danhmuccanthem = new DanhMuc(id, name);
        while (getDanhMucChi().timdanhmuctheoten(getDanhMucChi().getDsDanhMuc(), danhmuccanthem.gettendanhmuc()) != null || getDanhMucThu().timdanhmuctheoten(getDanhMucThu().getDsDanhMuc(), danhmuccanthem.gettendanhmuc()) != null) {
            System.out.println("Danh mục bị trùng , hãy nhập lại");
            name = sc.nextLine();
            danhmuccanthem.setTendanhmuc(name);
        }
        System.out.println("-Vai trò danh mục cấp 1 hay cấp 2                                                      |");
        System.out.println("-Nhập 1 nếu danh mục cấp 1                                                             |");
        System.out.println("-Nhập 2 nếu danh mục cấp 2                                                             |");
        System.out.println("-Nhập các nút còn lại về menu                                                          |");
        int m;
        try {
            m = Integer.parseInt(sc.nextLine());
            switch (m) {
                case 1:

                    danhmuc.themdanhmuc(danhmuccanthem);
                    setSoDanhMuc(getSoDanhMuc() - 1);
                    System.out.println("Số danh mục bạn còn " + getSoDanhMuc());
                    System.out.println("Bạn có muốn thêm danh mục nữa không ?");
                    System.out.println("1:Có");
                    System.out.println("Các nút còn lại:back về menu");


                    try {
                        int choice1 = Integer.parseInt(sc.nextLine());
                        if (choice1 == 1) {
                            ChonDanhMucDeThem();
                        }
                    } catch (NumberFormatException e) {
                        //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
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
            //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
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
                System.out.println("Bạn có muốn xóa tiếp danh mục nào nữa không");
                System.out.println("1:YES ");
                System.out.println("Các nút còn lại:về menu ");
                int choice1;
                try {
                    choice1 = Integer.parseInt(sc.nextLine());
                    if (choice1 == 1) {
                        ChonDanhMucdexoa();
                    }
                } catch (NumberFormatException e) {
                    //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
                }
            } else if (choice == 2) {

                xoaDanhMuc(getDanhMucThu());
                System.out.println("Bạn có muốn xóa tiếp danh mục nào nữa không");
                System.out.println("1:YES ");
                System.out.println("Các nút còn lại:về menu ");
                int choice2;
                try {
                    choice2 = Integer.parseInt(sc.nextLine());
                    if (choice2 == 1) {
                        ChonDanhMucdexoa();
                    }
                } catch (NumberFormatException e) {
                    //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
                }
            }

        } catch (NumberFormatException x) {
            //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
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
                                System.out.println(x + ": " + danhmuccap2.gettendanhmuc());
                            }
                            i++;
                        }
                        System.out.println("Bạn muốn xóa danh mục cấp 2 nào trong nhóm này");
                        DanhMuc DanhMucCap1 = null;
                        int type;
                        Boolean flat1 = true; // đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                        while (flat1) {
                            try {
                                flat1 = false;
                                System.out.println("Chọn Nhóm :");
                                type = Integer.parseInt(sc.nextLine());
                                flat1 = false;
                                DanhMucCap1 = dsdanhmuc.getDsDanhMuc().get(type - 1); // lấy ra nhóm Danh Mục cấp 1 cần xóa
                            } catch (IndexOutOfBoundsException x) {
                                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                flat1 = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                flat1 = true;

                            }
                        }

                        Boolean flat2 = true;// đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
                        while (flat2) {
                            int type_1;
                            try {
                                if (DanhMucCap1 != null && DanhMucCap1.getdanhsachdanhmuccon().size() > 0) { // Nếu tìm được danh mục cấp 1 và Danh mục cấp 1 không rỗng
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

                                } else { // Nếu kích thước danh mục cấp 1 rỗng
                                    System.out.println("Nhóm bạn chọn hiện đang không có danh mục");
                                    System.out.println("Nhập 1 phím bất kỳ để quay trở về menu");
                                    sc.nextLine();
                                    return;

                                }
                            } catch (IndexOutOfBoundsException x) {
                                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                flat2 = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                flat2 = true;

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
                System.out.println("BẠN CÓ MUỐN SỬA DANH MỤC NÀO NỮA KHÔNG");
                System.out.println("1:YES ");
                System.out.println("CÁC NÚT CÒN LẠI:QUAY TRỞ VỀ MENU ");
                int choice1 = Integer.parseInt(sc.nextLine());
                if (choice1 == 1) {
                    chonDanhMucDeSua();
                }
            } else if (choice == 2) {
                suaDanhMuc(getDanhMucThu());
                System.out.println("BẠN CÓ MUỐN SỬA TIẾP DANH MỤC NÀO KHÔNG");
                System.out.println("1:YES ");
                System.out.println("2:BACK VỀ MENU ");
                int choice2 = Integer.parseInt(sc.nextLine());
                if (choice2 == 1) {

                    chonDanhMucDeSua();
                }
            }
        } catch (NumberFormatException e) {
            //KHÔNG CẦN XỬ LÝ GÌ Ở CHỖ NÀY
        }
    }

    private void suaDanhMuc(ListDanhMuc dsdanhmuc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("|BẠN MUỐN SỬA DANH MỤC CẤP 1 HAY CẤP 2       ");
        System.out.println("|1: nếu là cấp 1 ");
        System.out.println("|2: nếu là cấp 2 ");
        Boolean temp = true;// // đặt biến cờ hiệu này để nhập hợp lệ , nếu nhập quá giới hạn index hoặc nhập chuỗi thì cờ hiệu vẫn bằng true
        while (temp) {
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
                            temp = false;
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
                            temp = false;
                            System.out.println("BẠN MUỐN SỬA TÊN HAY SỐ TIỀN TRONG DANH MỤC");
                            System.out.println("|1: NẾU MUỐN SỬA TÊN                       |");
                            System.out.println("|2: NẾU MUỐN SỬA SỐ TIỀN                   |");
                            System.out.println("|Các nút còn lại back về menu              |");

                            try {
                                int danhmuccanchon = Integer.parseInt(sc.nextLine());
                                switch (danhmuccanchon) {
                                    case 1:
                                        int sodanhmuc = 1;
                                        for (DanhMuc list : dsdanhmuc.getDsDanhMuc()) {
                                            System.out.println("Nhóm :" + sodanhmuc + ": " + list.gettendanhmuc());
                                            int x = 1;
                                            for (DanhMuc danhmuccap2 : list.getdanhsachdanhmuccon()) {
                                                System.out.println(x + ": " + danhmuccap2.gettendanhmuc());
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
                                        } else {
                                            System.out.println("Trong nhóm bạn chọn bao gồm : ");
                                            int pos = 1;
                                            String name1 = null;
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
                                                    CoHieu = true;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                                    CoHieu = true;

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
                                            sc.nextLine();
                                            System.out.println("Nhấn 1 phím bất kỳ để trở về menu");
                                            return;
                                        }
                                        //break;
                                    case 2:
                                        sodanhmuc = 1;
                                        for (DanhMuc list : dsdanhmuc.getDsDanhMuc()) {
                                            System.out.println("Nhóm :" + sodanhmuc + ": " + list.gettendanhmuc());
                                            int x = 1;
                                            for (DanhMuc danhmuccap2 : list.getdanhsachdanhmuccon()) {
                                                System.out.println(x + ": " + danhmuccap2.gettendanhmuc());
                                            }
                                            sodanhmuc++;
                                        }
                                        DanhMuc Danhmuccap1 = null;
                                        System.out.println("Nhập nhóm danh mục cần sửa");
                                        CoHieu = true;
                                        while (CoHieu) {
                                            int nhom;
                                            try {
                                                nhom = Integer.parseInt(sc.nextLine());
                                                CoHieu = false;
                                                Danhmuccap1 = dsdanhmuc.getDsDanhMuc().get(nhom - 1);

                                            } catch (IndexOutOfBoundsException x) {
                                                System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                                CoHieu = true;
                                            } catch (NumberFormatException e) {
                                                System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                                CoHieu = true;

                                            }
                                        }
                                        if (Danhmuccap1.getdanhsachdanhmuccon().isEmpty()) {
                                            System.out.println("Nhóm bạn chọn không còn danh mục để sửa ");
                                            System.out.println("Nhấn 1 phím bất kỳ để quay trở lại ");
                                            sc.nextLine();
                                            return;
                                        } else {
                                            System.out.println("Trong nhóm bạn chọn bao gồm : ");
                                            int pos = 1;
                                            String name1 = null;
                                            for (DanhMuc list : Danhmuccap1.getdanhsachdanhmuccon()) {
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

                                                    DanhMuc danhmuccansua = Danhmuccap1.getdanhsachdanhmuccon().get(position - 1);
                                                    name1 = danhmuccansua.gettendanhmuc();
                                                } catch (IndexOutOfBoundsException x) {
                                                    System.out.println("Số bạn nhập không nằm trong vị trí cho phép , vui lòng nhập lại !!!!!.");
                                                    CoHieu = true;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Bạn đã nhập không hợp lệ. Vui lòng nhập số.");
                                                    CoHieu = true;

                                                }
                                            }

                                            System.out.println("Nhập số tiền");
                                            String test = sc.nextLine();
                                            while (!isInteger(test)) {
                                                System.out.println("Hãy nhập chữ");
                                                test = sc.nextLine();
                                            }
                                            int sotien = Integer.parseInt(test);
                                            dsdanhmuc.changeMoneyDanhMuc(name1, sotien);
                                            dsdanhmuc.setTongsotien();
                                            System.out.println("Sửa số tiền thành công");
                                            System.out.println("Nhấn 1 phím bất kỳ để trở về menu");
                                            sc.nextLine();
                                            return;
                                        }
                                        //break;

                                        /*
                                        dsdanhmuc.lietkedanhmuc();
                                        System.out.println("Nhập nhóm danh mục bạn muốn sửa tiền ");////////////////////////////////////////////////
                                        name1= sc.nextLine();
                                        Boolean temp2 = true;
                                        while (temp2) {
                                            try {
                                                System.out.println("Nhập số tiền mới");
                                                int newMoney = Integer.parseInt(sc.nextLine());
                                                temp = true;
                                                dsdanhmuc.changeMoneyDanhMuc(name1, newMoney);
                                                dsdanhmuc.setTongsotien();
                                            } catch (Exception e) {
                                                System.out.println("Bạn nhập không hợp lệ vì nhập chữ , vui lòng nhập lại");
                                            }
                                        }
                                        break;
                                        */
                                    default:
                                        return;
                                }
                            } catch (NumberFormatException e) {
                                return;
                            }
                        }
                }
            } catch (NumberFormatException e) {
                System.out.println("Bạn chỉ được nhâp 1 hoặc 2");
            }
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
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("NHẤN 1 NÚT BẤT KỲ ĐỂ BACK VỀ MENU");
        sc.nextLine();
    }

    public void TuDongTaoGiaoDichMoiThang() {
        this.getSolanGiaoDich().kiemTraThayDoiThang();
    }

    private void chonloaigiaodich() {
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

    private void giaodich(ListDanhMuc danhmuc, int loaigd) {
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
            // tim thong tin danh muc cha
            System.out.println("Bạn muốn giao dịch danh mục cấp 1 với nhóm nào");
            int i = 1;
            DanhMuc DanhMucCha = null;
            for (DanhMuc list : danhmuc.getDsDanhMuc()) {

                System.out.println("Nhóm " + i + ": " + list.gettendanhmuc());
                int x = 1;
                for (DanhMuc danhmuccon : list.getdanhsachdanhmuccon()) {

                    System.out.println("     " + x + ". " + danhmuccon.gettendanhmuc());
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
                DanhMuc danhmuccha = danhmuc.timdanhmuctheoten(danhmuc.getDsDanhMuc(), DanhMucCanGiaoDich.getName_danhmuccha());
                if (danhmuccha != null) {
                    Bill.Chuyentienvaodanhmuc();
                    danhmuccha.setMoney();
                    danhmuc.setTongsotien();
                    getDsgiaodich().addGD(Bill);
                    System.out.println("Đã thêm vào danh mục " + DanhMucCanGiaoDich.gettendanhmuc());
                    System.out.println("Giao dịch thành công");
                    this.getSolanGiaoDich().setSolangiaodich(this.getSolanGiaoDich().getsolangiaodich() - 1);
                    System.out.println("Nhập 1 nút bất kỳ để back trở về menu");
                    sc.nextLine();
                    return;
                }
            } else {
                System.out.println("Danh mục bạn chọn đang rỗng");
                System.out.println("Nhấn nút bất kỳ để về menu!!");
                sc.nextLine();
                return;
            }
        }
    }


    public void hienThiLichSuGiaoDich() {
        Scanner sc = new Scanner(System.in);
        try {

            System.out.println("Bạn muốn hiển thị lịch sử giao dịch theo thời gian hay danh mục ");
            System.out.println("1.Hien thị toàn bộ lịch sử giao dịch");
            System.out.println("2.Hiển thị theo loaị danh mục");
            System.out.println("3.Hiển thị theo tên danh mục");
            System.out.println("4.Hiển thị theo ngày tháng năm");
            System.out.println("5.Trở về menu!");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                //1.hien thi toan bo lich su giao dich
                System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                    System.out.println(lichsu.toStringGiaoDich());
                }
                System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                sc.nextLine();
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
                    System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                    System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                        if (lichsu.getLoaigiaodich().equals("Giao dịch chi")) {
                            System.out.println(lichsu.toStringGiaoDich());
                        }
                    }
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();
                } else if (luachon == 2) {
                    //1.hien thi lich su giao dich thu
                    System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                    System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                        if (lichsu.getLoaigiaodich().equals("Giao dịch thu")) {
                            System.out.println(lichsu.toStringGiaoDich());
                        }
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

                            System.out.println("     " + x + ". " + danhmuccon.gettendanhmuc());
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

                                System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                                System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                                for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                                    if (lichsu.getTendanhmuc() == DanhMucCanGiaoDich) {
                                        System.out.println(lichsu.toStringGiaoDich());
                                    }
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

                                System.out.println("     " + x + ". " + danhmuccon.gettendanhmuc());
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

                                    System.out.println("---------------------------------------------------------Lịch sử giao dịch của bạn-----------------------------------------------------------");
                                    System.out.println("------------------------------------------------------------Thông tin giao dịch--------------------------------------------------------------");
                                    for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                                        if (lichsu.getTendanhmuc() == DanhMucCanGiaoDich) {
                                            System.out.println(lichsu.toStringGiaoDich());
                                        }
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
                    test = sc.nextLine();
                }
                int luachon = Integer.parseInt(sc.nextLine());
                if (luachon == 1) {
                    int year = LocalDate.now().getYear();
                    int month = LocalDate.now().getMonthValue();
                    int day = LocalDate.now().getDayOfMonth();
                    //Hien thi theo ngay thang nam
                    for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                        if (lichsu.getNgayGiaoDich().getngay() == day && lichsu.getNgayGiaoDich().getnam() == year && lichsu.getNgayGiaoDich().getthang() == month) {
                            System.out.println(lichsu.toStringGiaoDich());
                        }
                    }


                } else if (luachon == 2) {
                    System.out.println("1.Hiển thị theo ngày tháng năm cụ thể ");
                    System.out.println("2.Hiển thị theo tháng");
                    System.out.println("3.Hiển thị theo năm ");
                    String input = sc.nextLine();
                    while (!isInteger(input) && Integer.parseInt(input) > 3 || Integer.parseInt(input) < 0) {
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
                            while (Kiemtrathanghople(kiemtra1)) {
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
                            for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                                if (lichsu.getNgayGiaoDich().getngay() == ngay && lichsu.getNgayGiaoDich().getnam() == nam && lichsu.getNgayGiaoDich().getthang() == thang) {
                                    System.out.println(lichsu.toStringGiaoDich());
                                }
                            }
                            break;
                        case 2:

                            String kiemtra2 = sc.nextLine();
                            ngay = Integer.parseInt(kiemtra2);
                            System.out.println("Nhập tháng :");
                            kiemtra2 = sc.nextLine();
                            while (Kiemtrathanghople(kiemtra2)) {
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
                            for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                                if (lichsu.getNgayGiaoDich().getnam() == nam && lichsu.getNgayGiaoDich().getthang() == thang) {
                                    System.out.println(lichsu.toStringGiaoDich());
                                }
                            }
                            break;
                        case 3:
                            String kiemtra3 = sc.nextLine();
                            ngay = Integer.parseInt(kiemtra3);
                            System.out.println("Nhập tháng :");
                            kiemtra3 = sc.nextLine();
                            while (Kiemtrathanghople(kiemtra3)) {
                                System.out.println("Nhập tháng không hợp lệ vui lòng nhập lại:");
                                kiemtra2 = sc.nextLine();
                            }
                            thang = Integer.parseInt(kiemtra3);
                            System.out.println("Nhập năm :");
                            kiemtra3 = sc.nextLine();
                            while (!Kiemtranamhople(kiemtra3)) {
                                System.out.println("Nhập năm không hợp lệ vui lòng nhập lại:");
                                kiemtra3 = sc.nextLine();
                            }
                            nam = Integer.parseInt(kiemtra3);
                            for (GiaoDich lichsu : dsgiaodich.getDsGD()) {
                                if (lichsu.getNgayGiaoDich().getnam() == nam) {
                                    System.out.println(lichsu.toStringGiaoDich());
                                }
                            }
                            break;
                    }
                }
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
        System.out.println("Bạn hãy nhập nội dung tìm kiếm ");
                String find = sc.nextLine();
                for (GiaoDich giaodich : this.getDsgiaodich().getDsGD()) {
                    String thongtingiaodich = giaodich.getThongtingiaodich();
                    int timthay = thongtingiaodich.indexOf(find);
                    if (timthay != -1) {
                        ketquatimkiem.add(giaodich);

                    }
                }
                if(ketquatimkiem.size()>0){
                for (GiaoDich output : ketquatimkiem) {
                    System.out.println(output.toStringGiaoDich());
                }
                }
                else{
                    System.out.println("Không tìm thấy kế quả ");
                }
    }

    public String BaoCaoChiTietTungDanhMuc() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn muốn xem báo cáo chi tiết theo loại danh mục nào ");
        System.out.println("1.Chi ");
        System.out.println("2.Thu");
        System.out.println("3.Thoát");
        //Loai danh muc
        String kiemtra = sc.nextLine();
        while (!isInteger(kiemtra) || Integer.parseInt(kiemtra) < 1 || Integer.parseInt(kiemtra) > 2) {
            System.out.println("Hãy nhập lại");
            kiemtra = sc.nextLine();
        }
        int luachon = Integer.parseInt(kiemtra);
        if (luachon == 1) { // Hien thi theo ten danh muc chi
            String name = null;
            DanhMuc DanhMucCanXem = null;
            int i = 1;
            for (DanhMuc danhmuc : this.getDanhMucChi().getDsDanhMuc()) {
                System.out.println("Nhóm " + i + ": " + danhmuc.gettendanhmuc());
                int x = 1;
                for (DanhMuc danhmuccon : danhmuc.getdanhsachdanhmuccon()) {

                    System.out.println("     " + x + ". " + danhmuccon.gettendanhmuc());
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
                    if (DanhMucCap1.getdanhsachdanhmuccon().size() > 0) { //Kiểm tra kích thước của danh mục
                        int x = 1;
                        for (DanhMuc danhmuc : DanhMucCap1.getdanhsachdanhmuccon()) {
                            System.out.println(x + "." + danhmuc.gettendanhmuc());
                            x++;
                        }
                        int luachon1 = Integer.parseInt(sc.nextLine());
                        DanhMucCanXem = DanhMucCap1.getdanhsachdanhmuccon().get(luachon1 - 1);// chọn ra 1 danh mục con
                        int SoLanGiaoDichCuaDanhMuc = 0; // tổng số lần giao dịch của danh mục
                        String loaigd = null;
                        for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                            if (lichsu.getTendanhmuc() == DanhMucCanXem) {
                                loaigd = lichsu.getLoaigiaodich();
                                SoLanGiaoDichCuaDanhMuc++;
                            }
                        }
                        System.out.println("Tổng số lần giao dịch của danh mục này là: " + SoLanGiaoDichCuaDanhMuc);
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
                                    System.out.println("Vui lòng nhập năm");
                                    String test = sc.nextLine();
                                    while (!Kiemtranamhople(test)) {
                                        test = sc.nextLine();
                                    }
                                    int Year = Integer.parseInt(test);
                                    System.out.println("vui lòng nhập tháng");
                                    test = sc.nextLine();
                                    while (!Kiemtrathanghople(test)) {
                                        test = sc.nextLine();
                                    }
                                    int Month = Integer.parseInt(test);
                                    thongkedanhmuctheotuan(Month, Year, DanhMucCanXem, loaigd); // ghi file trong hàm này dòng 1644
                                    return null;

                                } else if (choice == 2) {// xem dữ liệu danh mục theo tháng
                                    Cohieu = false;
                                    System.out.println("Vui lòng nhập năm");
                                    String test = sc.nextLine();
                                    while (!Kiemtranamhople(test)) {
                                        test = sc.nextLine();
                                    }
                                    int Year = Integer.parseInt(test);
                                    thongkedanhmuctheothang(Year, DanhMucCanXem, loaigd);
                                    return null;

                                } else if (choice == 3) {// xem dữ liệu danh mục theo năm
                                    Cohieu = false;
                                    System.out.println("Vui lòng nhập năm gần đây ( trong phạm vi trừ 1 đến 10)");
                                    String test = sc.nextLine();
                                    while (!isInteger(test) || Integer.parseInt(test) < 1 || Integer.parseInt(test) > 10) {
                                        System.out.println("Bạn đã nhập không hợp lệ vui lòng nhập lại");
                                        sc.nextLine();
                                    }
                                    int NamGanDay = Integer.parseInt(test);
                                    thongkedanhmuctheonam(NamGanDay, DanhMucCanXem, loaigd);

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
                        sc.nextLine();
                        return null;
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
                    return null;
                }
            }
        } else if (luachon == 2) {
            { //hiển thị theo danh mục thu
                String name = null;
                DanhMuc DanhMucCanXem = null;
                int i = 1;
                for (DanhMuc danhmuc : this.getDanhMucChi().getDsDanhMuc()) {
                    System.out.println("Nhóm " + i + ": " + danhmuc.gettendanhmuc());
                    int x = 1;
                    for (DanhMuc danhmuccon : danhmuc.getdanhsachdanhmuccon()) {

                        System.out.println("     " + x + ". " + danhmuccon.gettendanhmuc());
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
                        if (DanhMucCap1.getdanhsachdanhmuccon().size() > 0) { //Kiểm tra kích thước của danh mục
                            int x = 1;
                            for (DanhMuc danhmuc : DanhMucCap1.getdanhsachdanhmuccon()) {
                                System.out.println(x + "." + danhmuc.gettendanhmuc());
                                x++;
                            }
                            int luachon1 = Integer.parseInt(sc.nextLine());
                            DanhMucCanXem = DanhMucCap1.getdanhsachdanhmuccon().get(luachon1 - 1);// chọn ra 1 danh mục con
                            int SoLanGiaoDichCuaDanhMuc = 0; // tổng số lần giao dịch của danh mục
                            String loaigd = null;
                            for (GiaoDich lichsu : getDsgiaodich().getDsGD()) {
                                if (lichsu.getTendanhmuc() == DanhMucCanXem) {
                                    loaigd = lichsu.getLoaigiaodich();
                                    SoLanGiaoDichCuaDanhMuc++;
                                }
                            }
                            System.out.println("Tổng số lần giao dịch của danh mục này là: " + SoLanGiaoDichCuaDanhMuc);
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
                                        System.out.println("Vui lòng nhập năm");
                                        String test = sc.nextLine();
                                        while (!Kiemtranamhople(test)) {
                                            test = sc.nextLine();
                                        }
                                        int Year = Integer.parseInt(test);
                                        System.out.println("vui lòng nhập tháng");
                                        test = sc.nextLine();
                                        while (!Kiemtrathanghople(test)) {
                                            test = sc.nextLine();
                                        }
                                        int Month = Integer.parseInt(test);
                                        thongkedanhmuctheotuan(Month, Year, DanhMucCanXem, loaigd); // ghi file trong hàm này dòng 1644
                                        return null;

                                    } else if (choice == 2) {// xem dữ liệu danh mục theo tháng
                                        Cohieu = false;
                                        System.out.println("Vui lòng nhập năm");
                                        String test = sc.nextLine();
                                        while (!Kiemtranamhople(test)) {
                                            test = sc.nextLine();
                                        }
                                        int Year = Integer.parseInt(test);
                                        thongkedanhmuctheothang(Year, DanhMucCanXem, loaigd);
                                        return null;

                                    } else if (choice == 3) {// xem dữ liệu danh mục theo năm
                                        Cohieu = false;
                                        System.out.println("Vui lòng nhập năm gần đây ( trong phạm vi trừ 1 đến 10)");
                                        String test = sc.nextLine();
                                        while (!isInteger(test) || Integer.parseInt(test) < 1 || Integer.parseInt(test) > 10) {
                                            System.out.println("Bạn đã nhập không hợp lệ vui lòng nhập lại");
                                            sc.nextLine();
                                        }
                                        int NamGanDay = Integer.parseInt(test);
                                        thongkedanhmuctheonam(NamGanDay, DanhMucCanXem, loaigd);

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
                            sc.nextLine();
                            return null;
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
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public static void main_menu() {
        System.out.println("__________________________________________________________________XIN NHẬP LỰA CHỌN__________________________________________________________");
        System.out.println("|1.THÊM DANH MỤC                                                                                                                            |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|2.XÓA DANH MỤC                                                                                                                             |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|3.SỬA DANH MỤC                                                                                                                             |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|4.HIỂN THỊ DANH MỤC                                                                                                                        |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|5.GIAO DỊCH                                                                                                                                |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                                                                    |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|7.THỐNG KÊ GIAO DỊCH                                                                                                                       |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|8.TÌM KIẾM THÔNG TIN GIAO DỊCH                                                                                                             |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        // chay menu;
        main_menu();

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
                    HienThiDanhMuc();
                    break;

                case 5:
                    chonloaigiaodich();
                    break;
                case 6:
                    hienThiLichSuGiaoDich();
                    break;
                case 7:
                    ThongKe();
                    break;

                case 8:
                    timkiemthongtingiaodich();
                    break;


            }
        } catch (NumberFormatException e) {
            System.out.println("VUI LÒNG NHẬP SỐ");
            return;

        }
    }

    public void ThongKe() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------THỐNG KÊ-----------------------------------------------------------------------*");
        System.out.println("1.THỐNG KÊ THEO TUẦN                                                                                                                         *");
        System.out.println("2.THỐNG KÊ THEO THÁNG                                                                                                                        *");
        System.out.println("3.THỐNG KÊ THEO NĂM                                                                                                                          *");
        System.out.println("NHẬP MỘT NÚT BẤT KỲ ĐỂ TRỞ LẠI MENU                                                                                                          *");
        System.out.println("_____________________________________________________________________________________________________________________________________________*");
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
                    int currentYear = LocalDate.now().getYear();
                    System.out.println("Số năm hiện tại là " + currentYear);
                    System.out.println("Nhập số năm gần đây nhất mà bạn muốn xem dữ liệu từ trước năm " + currentYear);
                    int year = Integer.parseInt(sc.nextLine());
                    thongketheonam(year);
                    System.out.println("NHẬP MỘT PHÍM BẤT KỲ ĐỂ BACK VỀ MENU");
                    sc.nextLine();
                default:
                    return;

            }
        } catch (NumberFormatException E) {
            //KHÔNG CẦN XỬ LÝ Ở ĐÂY
        }

    }

    public void setdanhmucchi(ListDanhMuc danhmucchi) {
        this.danhmucchi = danhmucchi;
    }

    public void thongkedanhmuctheotuan(int month, int year, DanhMuc danhMuc, String loaigd) { // theo tuan
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
        if (loaigd.equals("Giao dịch chi")) {
            for (int i = 0; i < 4; i++) {
                System.out.println(" - Tuần " + (i + 1) + " :");
                System.out.println("Số lần giao dịch là: " + soLanGiaoDichMoiTuan[i]);
                System.out.println("Số tiền sử dụng là: " + sotien[i]);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                System.out.println(" - Tuần " + (i + 1) + " :");
                System.out.println("Số lần giao dịch là: " + soLanGiaoDichMoiTuan[i]);
                System.out.println("Số tiền đã kiếm được là: " + sotien[i]);
            }
        }

    }

    public void thongkedanhmuctheothang(int year, DanhMuc danhMuc, String loaigd) { // theo tháng
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
        if (loaigd.equals("Giao dịch chi")) {
            for (int i = 0; i < 12; i++) {
                System.out.println(" - Tháng " + (i + 1) + " :");
                System.out.println("Số lần giao dịch là: " + soLanGiaoDichMoiThang[i]);
                System.out.println("Số tiền sử dụng là: " + sotien[i]);
            }
        } else {
            for (int i = 0; i < 12; i++) {
                System.out.println(" - Tháng " + (i + 1) + " :");
                System.out.println("Số lần giao dịch là: " + soLanGiaoDichMoiThang[i]);
                System.out.println("Số tiền đã kiếm được là: " + sotien[i]);
            }
        }


    }

    public void thongkedanhmuctheonam(int sonamganday, DanhMuc danhMuc, String loaigd) { // theo tháng
        int currentYear = LocalDate.now().getYear();
        int[] soLanGiaoDichTrongNhungNamGanDay = new int[sonamganday];
        int[] sotien = new int[sonamganday];

        for (int i = 0; i < sonamganday; i++) {
            soLanGiaoDichTrongNhungNamGanDay[i] = 0;
            sotien[i] = 0;
        }
        for (GiaoDich gd : getDsgiaodich().getDsGD()) {
            int yearIndex = sonamganday - (currentYear - gd.getNgayGiaoDich().getnam() - 1);
            if (gd.getTendanhmuc() == danhMuc) {
                soLanGiaoDichTrongNhungNamGanDay[yearIndex] += 1;
                sotien[yearIndex] += gd.getsotien();
            }
        }

        if(loaigd.equals("Giao dịch chi")) {
        for (int i = sonamganday-1; i >= 0; i++) {
            System.out.println(" -Năm " +(currentYear - i) + " :");
            System.out.println("Số lần giao dịch là: " + soLanGiaoDichTrongNhungNamGanDay[i]);
            System.out.println("Số tiền sử dụng là: " + sotien[i]);
        }
    } else
    {
        for (int i = 0; i < 12; i++) {
            System.out.println(" - Tháng " + (i + 1) + " :");
            System.out.println("Số lần giao dịch là: " + soLanGiaoDichTrongNhungNamGanDay[i]);
            System.out.println("Số tiền đã kiếm được là: " + sotien[i]);
        }
    }


}


    public void thongke(int month, int year) {
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

            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        double totalchi = TongSoTientrongdanhmuc(danhmucchi);
        System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
        System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng cho mỗi tuần                                                              ");
        for (int i = 0; i < 4; i++) {
            System.out.print(" - Tuần " + (i + 1) + " : ");
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
        System.out.println("- Tuần chi nhiều nhất là Tuần " + (Nhieunhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Nhieunhat(danhmucchi)]);

        for(int i=0;i<danhmucchi.length;i++){
            if(danhmucchi[i]==danhmucchi[Itnhat(danhmucchi)]);
            System.out.println("|Tuần chi ít nhất là Tuần " + (i + 1)+" với số tiền là "+danhmucchi[Itnhat(danhmucchi)]);
        }




    }


    public void thongke(int year) {
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
            if(i!=0 && danhmucchi[i]>danhmucchi[i-1]){
                System.out.println("Sử dụng nhiều hơn tháng trước "+(danhmucchi[i]-danhmucchi[i-1]));
            }
            if(i!=0 && danhmucchi[i]<danhmucchi[i-1]){
                System.out.println("Sử dụng ít hơn tháng trước "+(danhmucchi[i-1]-danhmucchi[i]));
            }

            double total = danhmucchi[i] + danhmucthu[i];
            double percent = (total != 0) ? danhmucchi[i] * 1.0 / total : 0.0;
            System.out.println(" Danh mục chi chiếm " + percent * 100 + "% trên tổng số tiền thu và chi trong tháng này");
            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        double totalchi = TongSoTientrongdanhmuc(danhmucchi);
        System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
        System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng cho mỗi tháng trong năm "+year+":                                         ");
        for (int i = 0; i < 12; i++) {
            System.out.print(" - Tháng " + (i + 1) + " : ");
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
        System.out.println(" Dữ liệu trong năm "+year +" :");
        System.out.println("|Tháng chi nhiều nhất là Tháng " + (Nhieunhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Nhieunhat(danhmucchi)]);
        for(int i=0;i<danhmucchi.length;i++){
            if(danhmucchi[i]==danhmucchi[Itnhat(danhmucchi)]);
            System.out.println("|Tháng chi ít nhất là Tháng " + (i + 1)+" với số tiền là "+danhmucchi[Itnhat(danhmucchi)]);
        }
    }
    public void thongketheonam(int soNamGanDay){
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
        for (int i = soNamGanDay-1; i >=0; i--) {
            System.out.println(" - Năm " + (currentYear -  i) + " :");
            System.out.println(" +Số tiền chi: " + danhmucchi[i]);
            System.out.println(" +Số tiền thu được: " + danhmucthu[i]);
            if(i!=0 && danhmucchi[i]>danhmucchi[i-1]){
                System.out.println("Sử dụng nhiều hơn năm trước "+(danhmucchi[i]-danhmucchi[i-1]));
            }
            if(i!=0 && danhmucchi[i]<danhmucchi[i-1]){
                System.out.println("Sử dụng ít hơn năm trước "+(danhmucchi[i-1]-danhmucchi[i]));
            }
            double total = danhmucchi[i] + danhmucthu[i];
            double percent = (total != 0) ? danhmucchi[i] * 1.0 / total : 0.0;
            System.out.println(" Danh mục chi chiếm " + percent * 100 + "%");
            if (danhmucchi[i] > danhmucthu[i]) {
                int use = danhmucchi[i] - danhmucthu[i];
                System.out.println(" Sử dụng nhiều hơn thu nhập " + use);
            }
        }
        double totalchi = TongSoTientrongdanhmuc(danhmucchi);
        System.out.println("---------------------------------------Ta có dữ liệu thống kê như sau-------------------------------------------");
        System.out.println("- Ta có biểu đồ từ số tiền đã sử dụng trong "+soNamGanDay+" gần đây :                                           ");
        for (int i = soNamGanDay-1; i >=0; i--) {
            System.out.print(" - Năm " + (i + 1) + " : ");
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
        System.out.println(" Dữ liệu từ "+soNamGanDay+" gần đây nhất ");
        System.out.println("-Năm chi nhiều nhất trong những năm gần đây là " + (Nhieunhat(danhmucchi) + 1)+" với số tiền là "+danhmucchi[Nhieunhat(danhmucchi)]);

        for(int i=0;i<danhmucchi.length;i++){
            if(danhmucchi[i]==danhmucchi[Itnhat(danhmucchi)]);
            System.out.println("|Năm sử dụng ít nhất là năm " + (i + 1)+" với số tiền là "+danhmucchi[Itnhat(danhmucchi)]);
        }

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

    public GioiHanGiaoDich getSolanGiaoDich() {
        return solangiaodich;
    }
    

    public void setSolangiaodich(GioiHanGiaoDich solangiaodich) {
        this.solangiaodich = solangiaodich;
    }
    public void setSolangiaodich(int solan) {
        this.solangiaodich = new GioiHanGiaoDich(solan);
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


    public static void main(String [] args){
        QuanLyDanhMuc user = new QuanLyDanhMuc();
        user.menu();
    }
}
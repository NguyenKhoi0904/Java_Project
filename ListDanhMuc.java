
package com.bt.quanlythuchicanhan;

 import java.util.ArrayList;
 import java.util.Scanner;

public class ListDanhMuc{
    private ArrayList<DanhMuc> dsdanhmuc;
    private int tongsotien = 0;

    public ListDanhMuc() {
        dsdanhmuc = new ArrayList<>();
    }

    public ListDanhMuc(DanhMuc Danhmuc1, DanhMuc Danhmuc2, DanhMuc Danhmuc3, DanhMuc Danhmuc4) {
        dsdanhmuc = new ArrayList<>();
        dsdanhmuc.add(Danhmuc1);
        dsdanhmuc.add(Danhmuc2);
        dsdanhmuc.add(Danhmuc3);
        dsdanhmuc.add(Danhmuc4);
        setTongsotien();
    }

    public ListDanhMuc(DanhMuc Danhmuc1, DanhMuc Danhmuc2) {
        dsdanhmuc = new ArrayList<>();
        dsdanhmuc.add(Danhmuc1);
        dsdanhmuc.add(Danhmuc2);
        setTongsotien();

    }

    public void themdanhmuc(DanhMuc danhmuccanthem) {
        boolean timThay = false;
        for (DanhMuc danhMuc : dsdanhmuc) {
            if (danhMuc.gettendanhmuc().equals(danhmuccanthem.gettendanhmuc())) {
                timThay = true;
                System.out.println("danh mục " + danhmuccanthem.gettendanhmuc() + " bị trùng");
                break;
            }
        }
        if (!timThay) {
            dsdanhmuc.add(danhmuccanthem);
            System.out.println("Them danh muc thanh cong");
        }
        setTongsotien();
    }

    public void themdanhmuctheoten(String tendanhmuc, DanhMuc danhmuccanthem) {
        boolean timThay = false;

        for (DanhMuc danhMuc : dsdanhmuc) {
            if (danhMuc.gettendanhmuc().equals(tendanhmuc)) {
                danhMuc.themdanhmuccon(danhmuccanthem);
                timThay = true;
                System.out.println("Them danh muc thanh cong");
                setTongsotien();
                break;
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay danh muc");
        }
    }

    public void lietkedanhmuc(String typeDanhMuc){
        for (DanhMuc ds : dsdanhmuc) {
            System.out.println("-" + ds.gettendanhmuc() + ":" + ds.getMoney());
            for (DanhMuc ds2 : ds.getdanhsachdanhmuccon()) {
                System.out.println(" +" + ds2.gettendanhmuc() + ":" + ds2.getMoney());
            }
        }
        System.out.println("Tổng số tiền của "+typeDanhMuc + getTongsotien());
    }
    public void lietketendanhmuccon(ArrayList<DanhMuc> List) {
        if(List!=null){
        for (DanhMuc ds : List) {
            System.out.println("  +" + ds.gettendanhmuc() + ":" + ds.getMoney());
            lietketendanhmuccon(ds.getdanhsachdanhmuccon());
        }

    }}

    public int getTongsotien() {
        return this.tongsotien;
    }

    public void setTongsotien() {
        int tongtien = 0;
        for (DanhMuc money : dsdanhmuc) {
            tongtien += money.getMoney();
        }
        this.tongsotien = tongtien;
    }

    public void setTongsotien(int money) {

        this.tongsotien = money;
    }

    public ArrayList<DanhMuc> getDsDanhMuc() {
        return this.dsdanhmuc;
    }

    public void deleteDanhMuc(String tendanhmuc) {
        boolean timThay = false;
        DanhMuc danhMucCanXoa = null;

        // Tìm danh mục cần xóa
        for (DanhMuc danhMuc : dsdanhmuc) {
            Scanner sc = new Scanner(System.in);
            if (danhMuc.gettendanhmuc().equals(tendanhmuc)) {
                timThay = true;
                System.out.println("Nếu Bạn xóa thì sẽ xóa toàn bộ số tiền của danh mục con");
                System.out.println("1: có ");
                System.out.println("2: không ");
                try{
                    int type = Integer.parseInt(sc.nextLine());
                    while(type !=1 && type !=2){
                        System.out.println("Mời bạn nhập lại");
                        type = Integer.parseInt(sc.nextLine());
                    }
                    if(type==1){
                        danhMucCanXoa = danhMuc;
                        break;
                    }
                    else{
                        System.out.println("Danh mục của bạn vẫn được giữ nguyên");
                    }


                }
                catch(Exception e){
                        System.out.println("vui lòng nhập số hợp lệ");
                        deleteDanhMuc(tendanhmuc);
                }

            } else {
                // Kiểm tra trong danh sách con của mỗi danh mục
                for (DanhMuc danhMucCon : danhMuc.getdanhsachdanhmuccon()) {
                    if (danhMucCon.gettendanhmuc().equals(tendanhmuc)) {
                        timThay = true;
                        danhMucCanXoa = danhMucCon;

                        break;
                    }
                }
            }
        }

        // Xóa danh mục đã tìm thấy
        if (timThay) {
            if (danhMucCanXoa != null) {
                // Xóa danh mục khỏi danh sách hoặc danh sách con của danh mục cha
                for (DanhMuc danhMuc : dsdanhmuc) {
                    if (danhMuc.getdanhsachdanhmuccon().contains(danhMucCanXoa)) {
                        danhMuc.getdanhsachdanhmuccon().remove(danhMucCanXoa);
                        setTongsotien(getTongsotien() - danhMucCanXoa.getMoney());
                        break;
                    } else if (danhMuc == danhMucCanXoa) {
                        dsdanhmuc.remove(danhMucCanXoa);

                        break;
                    }
                }
                System.out.println("Xóa danh mục thành công");
            }
        } else {
            System.out.println("Không tìm thấy danh mục");
        }
    }

    public void EditDanhMucCha(String namecansua, String name) {
        DanhMuc danhmuccansua = timdanhmuctheoten(dsdanhmuc,namecansua);
        danhmuccansua.setTendanhmuc(name);

    }

    public void EditDanhMucCon(String namecansua, String name) {
        Boolean timkiem = false;
        for (DanhMuc danhmuc : dsdanhmuc) {
            for (DanhMuc danhmuccon : danhmuc.getdanhsachdanhmuccon()) {
                if (danhmuccon.gettendanhmuc().equals(namecansua))
                    timkiem = true;
                danhmuccon.setTendanhmuc(name);
            }
        }

    }

    public DanhMuc timdanhmuctheoten(String name) {
        Boolean timkiem = false;
        for (DanhMuc danhmuc : dsdanhmuc) {
            if (danhmuc.gettendanhmuc().equals(name)) {
                timkiem = true;
                return danhmuc;
            }
        }
        if (!timkiem) {
            for (DanhMuc danhmuc : dsdanhmuc) {
                if (danhmuc.gettendanhmuc().equals(name)) {
                    timkiem = true;
                    return danhmuc;
                }
            }
        }
        return null;
    }

    public DanhMuc timdanhmuctheoten(ArrayList<DanhMuc> list, String name) {
        for (DanhMuc danhmuc : list) {
            if (danhmuc.gettendanhmuc().equals(name)) {
                return danhmuc; // Nếu tên khớp, trả về đối tượng danh mục
            } else {
                DanhMuc danhMucTimThay = timdanhmuctheoten(danhmuc.getdanhsachdanhmuccon(), name);
                if (danhMucTimThay != null) {
                    return danhMucTimThay; // Nếu tìm thấy trong danh sách con, trả về đối tượng danh mục đó
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public void themdanhmuccon(String nameDanhMucCha, DanhMuc name) {
        DanhMuc DanhMucCanTim = timdanhmuctheoten(dsdanhmuc,nameDanhMucCha);
        if(DanhMucCanTim!=null) {
            DanhMucCanTim.getdanhsachdanhmuccon().add(name);
            System.out.println("Thêm Danh Mục Thành Công");
        }
        else
            System.out.println("Thêm Danh Mục Thất bại");
    }

}




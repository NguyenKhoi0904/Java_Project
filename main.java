/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.bt.quanlythuchicanhan;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author aries
 */
public class main {
    private static ArrayList<User> dsUser = new ArrayList<>();
    private static User u = new User();
    
private static void CapNhatUserDateFile(ArrayList<User> a) throws FileNotFoundException, IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userData.txt"))){
            oos.writeObject(a);
            System.out.println("CẬP NHẬT TÀI KHOẢN THÀNH CÔNG");
            System.out.println("Mời bạn đăng nhập lại");
        }catch(IOException e){
            System.out.println("CẬP NHẬT TÀI KHOẢN THẤT BẠI");
            e.printStackTrace();
        }
    }
private static ArrayList<User> UserData(){
        ArrayList<User> a = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userData.txt"))) {
            a = (ArrayList<User>)ois.readObject();
        } catch (Exception e) {
        }
        return a;
}

private static boolean isInteger(String s){
    String regex = "^?\\d+$";
    Boolean flat = s.matches(regex);
    return flat;
}
//    public static void ChoUserDangKy() throws FileNotFoundException, ClassNotFoundException{
//        User u = new User();
//        boolean flag = true;
//        do{ 
//        }while(flag == true);
//    }
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
        // Dang nhap
        // kiem tra tai khoan mat khau trong file
//        ArrayList<User> a = new ArrayList<>();
//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("~/d/duserData.txt"))) {
//            a = (ArrayList<User>)ois.readObject();
//        } catch (Exception e) {
//        }
//        System.out.println(a);
        System.out.println("\tCHÀO MỪNG BẠN ĐẾN VỚI HỆ THỐNG QUẢN LÝ THU CHI CÁ NHÂN");
        boolean flag = true;
        User a = new User();
        String chuoi = new String();
        int n;
        Scanner scanner = new Scanner(System.in);
        do{
            try{
                do{
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("|   0. Đăng ký                                                                        |");
                    System.out.println("|   1. Đăng Nhập                                                                      |");
                    System.out.println("|   2. Quên mật khẩu                                                                  |");
                    System.out.println("|   3. Thoát                                                                          |");
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.print("  - Mời bạn lựa chọn: ");
                    n = Integer.parseInt(scanner.nextLine());
                    switch(n){
                        case 0 -> {
                            a.DangKy();
                        }
                        case 1 -> {
                            u = a.DangNhap();
                            if(u != null){
                                flag = false;
                            }
                        }
                        case 2->{
                            a.QuenMatKhau();
                        }
                        case 3->{
                            return;
                        }
                        default ->{
                            System.out.println("Vui lòng nhập lại");
                        }
                    }
                }while(flag);
            }catch(IOException e){
                e.printStackTrace();
            }
            catch(NumberFormatException e){
                System.out.println("vui lòng nhập lại!");
                flag=true;
            }
//            System.out.println("System.in đã đóng chưa? " + System.in.markSupported());
            if(u instanceof FreeUser freeUser){
                System.out.println("- FREE USER");
                boolean cohieu = true;
                
                do{ 
                    System.out.println("__________________________________________________________________XIN NHẬP LỰA CHỌN__________________________________________________________");
                    System.out.println("|1.THÊM DANH MỤC                                                                                                                            |");
                    System.out.println("|2.XÓA DANH MỤC                                                                                                                             |");
                    System.out.println("|3.SỬA DANH MỤC                                                                                                                             |");
                    System.out.println("|4.HIỂN THỊ DANH MỤC                                                                                                                        |");
                    System.out.println("|5.TẠO GIAO DỊCH MỚI                                                                                                                        |");
                    System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                                                                    |");
                    System.out.println("|7.TÌM KIẾM THÔNG TIN GIAO DỊCH                                                                                                             |");
                    System.out.println("|8.THỐNG KÊ DANH MỤC                                                                                                                        |");
                    System.out.println("|9.XEM BÁO CÁO CHI TIẾT                                                                                                                     |");
                    System.out.println("|10.NÂNG CẤP TÀI KHOẢN                                                                                                                      |");
                    System.out.println("|11.ĐĂNG XUẤT                                                                                                                               |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.print(" -Mời bạn lựa chọn: ");
                    String test = scanner.nextLine();
                    while(!isInteger(test)){
                        System.out.print("Mời bạn nhập lại: ");
                        test = scanner.nextLine();
                    }
                    int i = Integer.parseInt(test);
                    switch(i){
                        case 1 -> {
                            freeUser.taoDanhMuc();
                        }
                        case 2 -> {
                            freeUser.doiTenDanhMuc();
                        }
                        case 3-> {
                            freeUser.xoaDanhMuc();
                        }
                        case 4 -> {
                            freeUser.getQldmFree().HienThiDanhMuc();
                        }
                        case 5 -> {
                            freeUser.tao1GiaoDichMoi();
                        }
                        case 6 -> {
                            freeUser.getQldmFree().hienThiLichSuGiaoDich();
                        }
                        case 7 -> {
                            freeUser.timKiemThongTinGiaoDich();
                        }
                        case 8 -> {
                            freeUser.thongkeDanhMucTheoNgayThangNam();
                        }
                        case 9 -> {
                            freeUser.BaoCaoChiTietTheoTungDanhMucVaThoiGian();
                        }
                        case 10 -> {
                            freeUser.nangCapTaiKhoan();
                            cohieu = false;
                        }
                        case 11 -> {
                            dsUser = main.UserData();
                            int index = 0;
                            for(User user: dsUser){
                                if(user.getIdUser().equals(u.getIdUser())){
                                    break;
                                }
                                index++;
                            }
                            dsUser.set(index, u);
                            main.CapNhatUserDateFile(dsUser);
                            cohieu = false;
                        }
                        default -> {
                            
                        }
                    }
                }while(cohieu);
            }

            else if(u instanceof ProUser proUser){
                System.out.println("- PRO USER");
                boolean cohieu = true;
                
                do{
                    System.out.println("__________________________________________________________________XIN NHẬP LỰA CHỌN__________________________________________________________");
                    System.out.println("|1.THÊM DANH MỤC                                                                                                                            |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|2.XÓA DANH MỤC                                                                                                                             |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|3.SỬA DANH MỤC                                                                                                                             |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|4.HIỂN THỊ DANH MỤC                                                                                                                        |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|5.TẠO GIAO DỊCH MỚI                                                                                                                        |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                                                                    |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|7.TÌM KIẾM THÔNG TIN GIAO DỊCH                                                                                                             |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|8.THỐNG KÊ DANH MỤC VÀ ĐỐI SÁNH CÁC KHOẢN THU CHI                                                                                          |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|9.XEM BÁO CÁO CHI TIẾT                                                                                                                     |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|10.XUẤT BÁO CÁO RA FILE                                                                                                                    |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|11.LẬP NGÂN SÁCH VÀ NHẮC NHỞ                                                                                                               |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|12.CHỨC NĂNG LẬP NGÂN SÁCH VÀ NHẮC NHỞ                                                                                                     |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|13.ĐĂNG XUẤT                                                                                                                               |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.print(" -Mời bạn lựa chọn: ");
                    String s = scanner.nextLine();
                    while(!isInteger(s)){
                        System.out.print("Mời bạn nhập lại: ");
                        s = scanner.nextLine();
                    }
                    int i = Integer.parseInt(s);
                    switch(i){
                        case 1 ->{
                           proUser.taoDanhMuc();
                        }
                        case 2 ->{
                            proUser.xoaDanhMuc();
                        }
                        case 3 ->{
                            proUser.doiTenDanhMuc();
                        }
                        case 4 ->{
                            proUser.getQldmPro().HienThiDanhMuc();
                        }
                        case 5 ->{
                            proUser.tao1GiaoDichMoi();
                        }
                        case 6 ->{
                            proUser.getQldmPro().hienThiLichSuGiaoDich();
                        }
                        case 7 ->{
                            proUser.timKiemThongTinGiaoDich();
                        }
                        case 8 ->{
                            proUser.thongkeDanhMucTheoNgayThangNam();
                        }
                        case 9 ->{
                            chuoi = proUser.BaoCaoChiTietTheoTungDanhMucVaThoiGian(); // Cần xử lý hàm BÁO CÁO CHI TIẾT DANH MỤC CỦA PRO
                        }
                        case 10 ->{
                            System.out.println("Chức năng chưa hoàn thiện");
                            System.out.println("Bạn hãy chọn chức năng khác");
                            proUser.xuatBaoCaoRaFile(chuoi);
                        }
                        case 11 ->{
                            proUser.getQldmPro().LapNganSachVaNhacNho();
                        }
                        case 12 ->{
                            dsUser = main.UserData();
                            int index = 0;
                            for(User user: dsUser){
                                if(user.getIdUser().equals(u.getIdUser())){
                                    break;
                                }
                                index++;
                            }
                            dsUser.set(index, u);
                            main.CapNhatUserDateFile(dsUser);
                            cohieu = false;
                        }
                        default ->{
                            System.out.println("Không có chức năng này");
                            System.out.println("Vui lòng nhập lại");
                        }
                    }
                }while(cohieu);
            }
        }while(true);
        // Neu co tra ve User 
        // Neu khong co se hoi user dang ky va tao ra user moi
        // sau khi tao ra user thi lưu user vào file 
        // nếu user muốn update lên pro thì dùng phương thức update()
        // downcasting xuong lop freeuser và dùng phương thức nâng cấp
    }
}

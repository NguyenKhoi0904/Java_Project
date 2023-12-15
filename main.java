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
        int n;
        Scanner scanner = new Scanner(System.in);
        do{
            try{
                do{
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("|   0. Đăng ký                                                                        |");
                    System.out.println("|   1. Đăng Nhập                                                                      |");
                    System.out.println("|   2. Thoát                                                                          |");
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.print("  - Mời bạn lựa chọn: ");
                    n = Integer.parseInt(scanner.nextLine());
                    switch(n){
                        case 0 -> {
                            a.DangKy();
                            break;
                        }
                        case 1 -> {
                            u = a.DangNhap();
                            flag = false;
                            break;
                        }
                        case 2->{
                            return;
                        }
                        default ->{

                            System.out.println("Vui lòng nhập lại");
                            flag=true;
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
                System.out.println("\t\tFREE USER");
                boolean cohieu = true;
                System.out.println("Số danh mục: " + u.getQldm().getSoDanhMuc());
                do{ 
                    System.out.println("__________________________________________________________________XIN NHẬP LỰA CHỌN__________________________________________________________");
                    System.out.println("|1.THÊM DANH MỤC                                                                                                                            |");
                    System.out.println("|2.XÓA DANH MỤC                                                                                                                             |");
                    System.out.println("|3.SỬA DANH MỤC                                                                                                                             |");
                    System.out.println("|4.HIỂN THỊ DANH MỤC                                                                                                                        |");
                    System.out.println("|5.TẠO GIAO DỊCH MỚI                                                                                                                        |");
                    System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                                                                    |");
                    System.out.println("|7.TÌM KIẾM THÔNG TIN GIAO DỊCH                                                                                                             |");
                    System.out.println("|8.THỐNG KÊ GIAO DỊCH                                                                                                                       |");
                    System.out.println("|9.XEM BÁO CÁO CHI TIẾT                                                                                                                     |");
                    System.out.println("|10.NÂNG CẤP TÀI KHOẢN                                                                                                                      |");
                    System.out.println("|11.ĐĂNG XUẤT                                                                                                                               |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.print(" -Mời bạn lựa chọn: ");
                    String test = scanner.nextLine();
                    while(!isInteger(test)){
                        System.out.println("Mời bạn nhập lại");
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
                            freeUser.getQldm().HienThiDanhMuc();
                        }
                        case 5 -> {
                            freeUser.tao1GiaoDichMoi();
                        }
                        case 6 -> {
                            freeUser.getQldm().hienThiLichSuGiaoDich();
                        }
                        case 7 -> {
                            freeUser.timKiemThongTinGiaoDich();
                        }
                        case 8 -> {
                            freeUser.thongkeDanhMucTheoNgayThangNam();
                        }
                        case 9 -> {

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
                System.out.println("\t\tPRO USER");
                boolean cohieu = true;
                System.out.println("Số danh mục: " + u.getQldm().getSoDanhMuc());
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
                    System.out.println("|8.THỐNG KÊ                                                                                                                                 |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|9.XEM BÁO CÁO CHI TIẾT                                                                                                                     |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|10.ĐỐI SÁNH CÁC KHOẢN THU CHI                                                                                                              |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|11.XUẤT BÁO CÁO RA FILE                                                                                                                    |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|12.LẬP NGÂN SÁCH VÀ NHẮC NHỞ                                                                                                               |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|13.ĐĂNG XUẤT                                                                                                                               |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.print(" -Mời bạn lựa chọn: ");
                    String s = scanner.nextLine();
                    while(!isInteger(s)){
                        System.out.println("Mời bạn nhập lại");
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
                            proUser.getQldm().HienThiDanhMuc();
                        }
                        case 5 ->{
                            //pro
                        }
                        case 6 ->{
                            proUser.getQldm().hienThiLichSuGiaoDich();
                        }
                        case 7 ->{
                            proUser.timKiemThongTinGiaoDich();
                        }
                        case 8 ->{
                            
                        }
                        case 9 ->{
                            
                        }
                        case 10 ->{
                            
                        }
                        case 11 ->{
                            System.out.println("Chức năng chưa hoàn thiện");
                            System.out.println("Bạn hãy chọn chức năng khác");
                        }
                        case 12 ->{
                            
                        }
                        default ->{
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

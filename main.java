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
//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userData.txt"))) {
//            a = (ArrayList<User>)ois.readObject();
//        } catch (Exception e) {
//        }
//        System.out.println(a);
        System.out.println("\tCHÀO MỪNG BẠN ĐẾN VỚI HỆ THỐNG QUẢN LÝ THU CHI CÁ NHÂN NHỮNG CON BÁO");
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
                        default ->{
                            return;
                        }
                    }
                }while(flag);
            }catch(IOException e){
                e.printStackTrace();
            }
//            System.out.println("System.in đã đóng chưa? " + System.in.markSupported());
            if(u instanceof FreeUser freeUser){
                System.out.println("                FREE USER");
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
                    System.out.println("|5.GIAO DỊCH                                                                                                                                |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                                                                    |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|7.THỐNG KÊ GIAO DỊCH                                                                                                                       |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|8.NÂNG CẤP TÀI KHOẢN                                                                                                                       |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|9.THOÁT                                                                                                                                    |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.print(" -Mời bạn lựa chọn: ");
                    int i = Integer.parseInt(scanner.nextLine());
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
                            
                        }
                        case 8 -> {
                            freeUser.nangCapTaiKhoan();
                            cohieu = false;
                        }
                        default -> {
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

            else if(u instanceof ProUser proUser){
                System.out.println("PRO USER");
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
                    System.out.println("|5.GIAO DỊCH                                                                                                                                |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|6.XEM LỊCH SỬ GIAO DỊCH                                                                                                                    |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|7.THỐNG KÊ GIAO DỊCH                                                                                                                       |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|8.ĐỐI SÁNH CÁC KHOẢN THU CHI THEO THỜI GIAN CỦA CÁC THÁNG HOẶC NĂM                                                                         |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|9.XUẤT BÁO CÁO RA FILE                                                                                                                     |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|10.LẬP NGÂN SÁCH VÀ NHẮC NHỞ                                                                                                               |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("|11.THOÁT                                                                                                                                    |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
                    System.out.print(" -Mời bạn lựa chọn: ");
                    int i = Integer.parseInt(scanner.nextLine());
                    switch(i){
                        case 1 ->{
                           
                        }
                        case 2 ->{
                            proUser.xoaDanhMuc();
                        }
                        case 3 ->{
                            proUser.doiTenDanhMuc();
                        }
                        case 4 ->{
                            
                        }
                        case 5 ->{
                            
                        }
                        case 6 ->{
                            
                        }
                        case 7 ->{
                            
                        }
                        case 8 ->{
                            
                        }
                        case 9 ->{
                            
                        }
                        case 10 ->{
                            
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

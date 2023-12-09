/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.bt.quanlythuchicanhan;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    
    
private boolean ghiUserDataLenFile(User a) throws FileNotFoundException, ClassNotFoundException{
        ArrayList<User> b = new ArrayList<>(); 
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("userData.txt"))){
            b = (ArrayList<User>)oos.readObject();
            oos.close();
        }catch(EOFException e){
            b = new ArrayList<>();
        }catch (IOException e) {
            e.printStackTrace();
        }
        b.add(a);
        
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("userData.txt"))) {
            ois.writeObject(b);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
}
    
    public static void ChoUserDangNhap() throws FileNotFoundException, ClassNotFoundException{
        boolean flag = true;
        User a = new User();
        int n;
        try(Scanner scanner = new Scanner(System.in)){
            do{
                System.out.println("------------------");
                System.out.println("0.Đăng ký");
                System.out.println("1.Đăng Nhập");
                System.out.println("2.Thoát");
                System.out.print("Mời bạn lựa chọn: ");
                n = Integer.parseInt(scanner.nextLine());
                switch(n){
                    case 0 -> {
                        a.DangKy();
                        break;
                    }
                    case 1 -> {
                        a.DangNhap();
                        flag = false;
                        break;
                    }
                    default ->{
                        break;
                    }
                }
          }while(flag);
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
//    public static void ChoUserDangKy() throws FileNotFoundException, ClassNotFoundException{
//        User u = new User();
//        boolean flag = true;
//        do{ 
//        }while(flag == true);
//    }
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        // Dang nhap
        // kiem tra tai khoan mat khau trong file
        ChoUserDangNhap();
        // Neu co tra ve User 
        // Neu khong co se hoi user dang ky va tao ra user moi
        //readIntegerFromUser();
        // sau khi tao ra user thi lưu user vào file 
        // nếu user muốn update lên pro thì dùng phương thức update()
        // downcasting xuong lop freeuser và dùng phương thức nâng cấp
    }
    private static void readIntegerFromUser(){
        Scanner scanner = new Scanner(System.in);
        int a;

        do {
            System.out.print("Nhập giá trị cho a: ");
            a = Integer.parseInt(scanner.nextLine());

            System.out.println("Giá trị của a là: " + a);
        } while (a <= 100);

        System.out.println("Biến a đã vượt qua giá trị 100.");
    }
}

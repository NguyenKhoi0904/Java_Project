/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;

import java.io.EOFException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aries
 */
public class User implements Serializable {
    private AccountUser TaiKhoanNguoiDung;
    private String loaitaiKhoan;
    private QuanLyDanhMuc qldm;
    private String idUser;
    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(AccountUser TaiKhoanNguoiDung, String loaitaiKhoan, QuanLyDanhMuc qldm, String idUser) {
        this.TaiKhoanNguoiDung = TaiKhoanNguoiDung;
        this.loaitaiKhoan = loaitaiKhoan;
        this.qldm = qldm;
        this.idUser = idUser;
    }

    public QuanLyDanhMuc getQldm() {
        return qldm;
    }

    public void setQldm(QuanLyDanhMuc qldm) {
        this.qldm = qldm;
    }

    public AccountUser getTaiKhoanNguoiDung() {
        return TaiKhoanNguoiDung;
    }

    public void setTaiKhoanNguoiDung(AccountUser TaiKhoanNguoiDung) {
        this.TaiKhoanNguoiDung = TaiKhoanNguoiDung;
    }

    public String getLoaitaiKhoan() {
        return loaitaiKhoan;
    }

    public void setLoaitaiKhoan(String loaitaiKhoan) {
        this.loaitaiKhoan = loaitaiKhoan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
    public User DangNhap() throws ClassNotFoundException, FileNotFoundException {
        ArrayList<AccountUser> tmpDN = readAccountUserDataFromFileAndCheck();
        ArrayList<User> pxm = docUserData();
        boolean isAccountValid = false;
        Scanner input_User = new Scanner(System.in);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++  ĐĂNG NHẬP  +++++++++++++++++++++++++++++++++++++");
        System.out.print("  Tài khoản: ");
        String username = input_User.nextLine();
        System.out.print("  Mật khẩu: ");
        String password = input_User.nextLine();
        int index = 0;
        for(AccountUser au: tmpDN){
            if(au.getTendangnhap().equals(username)&&au.getMatkhau().equals(password)){
                System.out.println("    ĐĂNG NHẬP THÀNH CÔNG");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                return pxm.get(index);
            }
            if(au.getTendangnhap().equals(username)){
                isAccountValid = true;
                break;
            }
            index++;
        }
        if(isAccountValid){
            int count = 0;
            boolean flag = true;
            System.out.println("&&&&&& 404 &&&&&&");
            System.out.println("Sai mật khẩu");
            do{
                index = 0;
                System.out.print("Mời bạn nhập lại mật khẩu: ");
                password = input_User.nextLine();
                for(AccountUser au : tmpDN){
                    if(au.getMatkhau().equals(password)){
                        System.out.println(" Đăng nhập thành công");
                        return pxm.get(index);
                    }else{
                        flag = false;
                    }
                    index++;
                }
                
                if(!flag){
                    System.out.println("Sai mật khẩu");
                    count++;
                    if(count >= 3){
                        System.out.println("Bạn có muốn thoát ra không?"); // CẦN XỬ LÝ LẠI CHỖ NÀY
                        System.out.println("0.Không");
                        System.out.println("1.Có");
                        System.out.print("Mời bạn lựa chọn: ");
                        int temp = Integer.parseInt(input_User.nextLine());
                        if(temp == 1){
                            return null;
                        }
                    }
                }   
            }while(true);
        }else{ //KIỂM TRA TRUÒNG HỢP TÀI KHOẢN KHÔNG TỒN TẠI
            int chiso = 0;
            int dem = 0; //BIẾN NÀY DÙNG ĐỂ ĐẾM SỐ VÒNG CHẠY CỦA DO WHILE
            boolean cohieu = true;
            System.out.println("---------- 404 ----------");
            System.out.println("  Sai tài khoản hoặc tài khoản không tồn tại");
            do{
                System.out.print("  Mời bạn nhập lại tài khoản: ");
                username = input_User.nextLine();
                for(AccountUser au : tmpDN){
                    if(au.getTendangnhap().equals(username)){
                        if(au.getTendangnhap().equals(password)){
                            System.out.println(" Đăng nhập thành công");
                            return pxm.get(chiso);
                        }
                        else{
                            cohieu = false;
                        }
                    }
                    chiso++;
                }
                
                if(!cohieu){ //TRƯỜNG HỢP SAU KHI NHẬP ĐÚNG TÀI KHOẢN NHƯNG LẠI NHẬP SAI MẬT KHẨU
                    boolean isCorrectPassword = true;
                    int count = 0;
                    System.out.println("---------- 404 ----------");
                    System.out.println("  Mật khẩu ở trên của bạn bị sai");
                    do{
                        chiso=0;
                        System.out.print("  Mời bạn nhập lại mật khẩu: ");
                        password = input_User.nextLine();
                        for(AccountUser au : tmpDN){
                            if(au.getMatkhau().equals(password)){
                                System.out.println("    Đăng nhập thành công");
                                return pxm.get(chiso);
                            }else{
                                isCorrectPassword = false;
                            }
                            chiso++;
                        }
                        if(!isCorrectPassword){
                            System.out.println(" Sai mật khẩu");
                            count++;
                        }
                        if(count >= 3){
                            System.out.println(" Bạn có muốn thoát ra không?");
                            System.out.println(" 0.Không");
                            System.out.println(" 1.Có");
                            System.out.print(" Mời bạn lựa chọn: ");
                            int temp = Integer.parseInt(input_User.nextLine());
                            if(temp == 1){
                                return null;
                            }
                        }
                        
                    }while(true);
                }
                System.out.println("---------- 404 ----------");
                System.out.println("Sai tài khoản hoặc tài khoản không tồn tại");
                dem++;
                if(dem >= 3){
                    System.out.println(" Bạn có muốn thoát ra không?");
                    System.out.println(" 0.Không");
                    System.out.println(" 1.Có");
                    System.out.print(" Mời bạn lựa chọn: ");
                    int i = Integer.parseInt(input_User.nextLine());
                    if(i == 1){
                        return null;
                    }
                }
            }while(true);
            
        }
//            input_User.close();
    }

    private ArrayList<User> docUserData() throws ClassNotFoundException, FileNotFoundException{
        ArrayList<User> a = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream("userData.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            if(fis.available() > 0){
                a = (ArrayList<User>) ois.readObject();
            }
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return a;
    }
    
    private ArrayList<AccountUser> readAccountUserDataFromFileAndCheck() throws ClassNotFoundException,FileNotFoundException {
        ArrayList<AccountUser> tmp = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountUser.txt"))) {
            tmp = (ArrayList<AccountUser>) ois.readObject();
        } catch (EOFException e) {
            //không cần làm gì ở đây cả
            return tmp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public void DangKy() throws FileNotFoundException, ClassNotFoundException {
        System.out.println("*** Dang ky tai khoan ***");
        Scanner input_User = new Scanner(System.in);
        boolean isAccountExist = false;
        boolean isEmailExist = false;
        
        ArrayList<User> user_array = docUserData();
        String taikhoan = null, matkhau = null,email = null;
        if(!user_array.isEmpty()){
            //XỬ LÝ TÀI KHOẢN
            System.out.print("- Tài khoản: ");
            taikhoan = input_User.nextLine(); //ĐOẠN CODE TỪ DÒNG 246 ĐẾN 281 CHỈ ĐỂ XỬ LÝ TÀI KHOẢN
            while(!isAccountRegexValid(taikhoan)){
                System.out.println("Tài khoản phải có ít nhất 6 kí tự và không được có kí tự đặc biệt");
                System.out.print("Mời bạn nhập lại tài khoản: ");
                taikhoan = input_User.nextLine();
            }
            for(User user: user_array){
                if(user.getTaiKhoanNguoiDung().getTendangnhap().equals(taikhoan)){
                    System.out.println("---Tài khoản đã tồn tại");
                    isAccountExist = true;
                    break;
                }
            }
            if(isAccountExist){
                boolean flag = true;
                do{
                    System.out.print("Mời bạn nhập lại tài khoản: ");
                    taikhoan = input_User.nextLine();
                    while(!isAccountRegexValid(taikhoan)){
                        System.out.println("Tài khoản phải có ít nhất 6 kí tự và không được có kí tự đặc biệt");
                        System.out.print("Mời bạn nhập lại tài khoản: ");
                        taikhoan = input_User.nextLine();
                    }
                    for(User user : user_array){
                        if(user.getTaiKhoanNguoiDung().getTendangnhap().equals(taikhoan)){
                            System.out.println("---Tài khoản đã tồn tại");
                            flag = true;
                            break;
                        }else{
                            flag = false;
                        }
                    }
                    if(!flag){
                        System.out.println("Tài khoản hợp lệ");
                    }
                }while(true);
            }
            //XỬ LÝ MẬT KHẨU
            System.out.print("Mật khẩu: ");
            matkhau = input_User.nextLine();
            while(!isPasswordRegexValid(matkhau)){
                System.out.println("Mật khẩu phải có ít nhất 6 kí tự và không được có khoảng trắng");
                System.out.print("Mời bạn nhập lại mật khẩu");
                matkhau = input_User.nextLine();
            }
            
            //XỬ LÝ EMAIL
            System.out.println("Email: ");
            email = input_User.nextLine();
            while(!isGmailAddressRegexValid(email)){
                System.out.println("Đây không phải là email hợp lệ");
                System.out.print("Mời bạn nhập lại email: ");
                email = input_User.nextLine();
            }
            for(User user: user_array){
                if(user.getTaiKhoanNguoiDung().getEmail().equals(email)){
                    System.out.println("Email đã tồn tại");
                    isEmailExist = true;
                    break;
                }
            }
            if(isEmailExist){
                boolean flag = true;
                do{
                    System.out.print("Mời bạn nhập lại email: ");
                    email = input_User.nextLine();
                    while(!isGmailAddressRegexValid(email)){
                        System.out.println("Đây không phải là email hợp lệ");
                        System.out.print("Mời bạn nhập lại email: ");
                        email = input_User.nextLine();
                    }
                    for(User user : user_array){
                        if(user.getTaiKhoanNguoiDung().getEmail().equals(email)){
                            System.out.println("Email đã tồn tại");
                            flag = true;
                            break;
                        }else{
                            flag = false;
                        }
                    }
                    if(!flag){
                        System.out.println("Email hợp lệ");
                    }
                }while(flag);
            }
        }
        AccountUser a = new AccountUser(taikhoan,matkhau,email);
        this.setTaiKhoanNguoiDung(a);
        
        System.out.println("^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^");
        System.out.println("- Chọn loại tài khoản mà bạn muốn đăng ký");
        System.out.println("0. Free User");
        System.out.println("1. Pro User");
        System.out.println("_____________________________________________________________________________________");
        int temporary = Integer.parseInt(input_User.nextLine());
        if (temporary == 0) {
            this.setLoaitaiKhoan("FREE");
            this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
            //ĐANG LÀM TÀ ĐẠO YÊU CẦU KHÔNG BẮT CHƯỚC
            User u = new FreeUser();
            CopyGiaTri(u); // HÀM NÀY CÓ TÁC DỤNG GÁN GIÁ TRỊ CỦA THIS CHO U
            if(u instanceof FreeUser fu){
                if(ghiUserDataLenFile(fu)){ // GHI THÔNG TIN CỦA 1 USER LÊN FILE
                    System.out.println(" Ghi thông tin user thành công");
                }else{
                    System.out.println(" Ghi thông tin user thất bại");
                }
            }
            
            if (writeAccountUserDateToFile(a)) { //GHI ACCOUNT USER LÊN FILE
                System.out.println(" Đăng ký thành công");
            } else {
                System.out.println(" Đăng ký thất bại");
            }
            
        } else {
            System.out.println(" Bạn phải trả 100USD để trở thành Pro User!!!!");
            System.out.println(" Bạn có đồng ý không?");
            System.out.println("Nhập 0 là không đồng ý và bạn sẽ trở thành Free User");
            System.out.println("Nhập 1 là đồng ý");
            System.out.print("Mời bạn lựa chọn: ");
            int tmp = Integer.parseInt(input_User.nextLine());
            switch (tmp) {
                case 1 -> {
                    System.out.println("Xác nhận thanh toán");
                    this.setLoaitaiKhoan("PRO");
                    this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
                    //ĐANG LÀM TÀ ĐẠO YÊU CẦU KHÔNG BẮT CHƯỚC
                    User u = new ProUser();
                    CopyGiaTri(u); // HÀM NÀY CÓ TÁC DỤNG GÁN GIÁ TRỊ CỦA THIS CHO U
                    u.getQldm().setSoDanhMuc(10); //SỐ DANH MỤC MẶC ĐỊNH CHO PROUSER
                    if(u instanceof ProUser pu){
                        if(ghiUserDataLenFile(pu)){ // GHI THÔNG TIN CỦA 1 USER LÊN FILE
                            System.out.println("Ghi thông tin user thành công");
                        }else{
                            System.out.println("Ghi thông tin user thất bại");
                        }
                    }
                    
                    if (writeAccountUserDateToFile(a)) { //GHI ACCOUNT USER LÊN FILE
                        System.out.println(" Đăng ký thành công");
                    } else {
                        System.out.println(" Đăng ký thất bại");
                    }
                }
                case 0 -> {
                    this.setLoaitaiKhoan("FREE");
                    this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
                    System.out.println(this.getIdUser());
                    //ĐANG LÀM TÀ ĐẠO YÊU CẦU KHÔNG BẮT CHƯỚC
                    User u = new FreeUser();
                    CopyGiaTri(u); // HÀM NÀY CÓ TÁC DỤNG GÁN GIÁ TRỊ CỦA THIS CHO U
                    if(u instanceof FreeUser fu){
                        if(ghiUserDataLenFile(fu)){ // GHI THÔNG TIN CỦA 1 USER LÊN FILE
                            System.out.println("Ghi thông tin user thành công");
                        }else{
                            System.out.println("Ghi thông tin user thất bại");
                        }
            }
                    
                    if (writeAccountUserDateToFile(a)) { //GHI ACCOUNT USER LÊN FILE
                        System.out.println(" Đăng ký thành công");
                        
                    } else {
                        System.out.println(" Đăng ký thất bại");
                    }
                }
            }
        }
    }
    
    private boolean isAccountValid(String account){
        return account.length() > 6;
    }
    
    private boolean  isAccountRegexValid(String account){
        String regex = "^[a-zA-Z0-9 \\p{L}]{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(account);
        return matcher.matches();
    }
    
    private boolean isPasswordValid(String password){
        return password.length() > 6;
    }
    
    private boolean isPasswordRegexValid(String password){
        String regex = "^[^\\s]{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private boolean isGmailAddressRegexValid(String gmail){
        String regex = "^[a-zA-Z0-9]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gmail);
        return matcher.matches();
    }
    
    private void CopyGiaTri(User sourceUser){
        sourceUser.setLoaitaiKhoan(this.getLoaitaiKhoan());
        sourceUser.setIdUser(this.getIdUser());
        sourceUser.setTaiKhoanNguoiDung(this.getTaiKhoanNguoiDung());
        //sourceUser.setQldm(new QuanLyDanhMuc(2));
    }
    private String generateID_User(String prefix){ //HÀM RANDOM ID DỰA TRÊN MÃ ĐẦU VÀO
        Random random = new Random();              //VD: giá trị truyền vào là "PRO" kết quả khi trả về sẽ là PRO + 1 dãy số ngẫu nhiên có 6 kí tự, vd PRO000001
        int randomNumber = random.nextInt((int) Math.pow(10, 6));
        
        String formatNumber = String.format("%0" + 6 + "d", randomNumber);
        return prefix + formatNumber;
    }
    
    private boolean ghiUserDataLenFile(User a) throws FileNotFoundException, ClassNotFoundException{
        ArrayList<User> b = new ArrayList<>();
        File f = new File("userData.txt");
        if(f.exists()){
            try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("userData.txt"))){
            b = (ArrayList<User>)oos.readObject();
            oos.close();
            }catch(EOFException e){ // EXCEPTION KHI FILE TRỐNG
                // Ở ĐÂY KHÔNG CẦN XỬ LÝ GÌ CẢ
            }catch (IOException e) {
                e.printStackTrace();
            }
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
    
    private boolean writeAccountUserDateToFile(AccountUser au) throws FileNotFoundException, ClassNotFoundException {
        ArrayList<AccountUser> auTmp = new ArrayList<>();
        File f = new File("accountUser.txt");
        if(f.exists()){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountUser.txt"))){
            auTmp = (ArrayList<AccountUser>) ois.readObject();
            ois.close();
            }catch(EOFException e){
                //KHÔNG CẦ XỬ LÝ GÌ HẾT NẾU NỘI DUNG TRONG FILE TRỐNG
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        auTmp.add(au);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accountUser.txt"))) { 
            oos.writeObject(auTmp);
            System.out.println("GHI THÔNG TIN USER VÀO FILE THÀNH CÔNG");
            return true;
        } catch (IOException e) {
            System.out.println("GHI THÔNG TIN USER VÀO FILE THẤT BẠI " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) throws ClassNotFoundException,FileNotFoundException {
        User u = new User();
        u.setLoaitaiKhoan("FREE");
//        User u1 = new User();
//        u1.DangKy();       
//        User u2 = new User();
//        u2.DangKy();
//        
//        User u3 = new User();
//        u3.DangKy();
        
//        u.DangNhap();
//        u1.DangNhap();

//          System.out.println(generateID_User(u.getLoaitaiKhoan()));
    }
}

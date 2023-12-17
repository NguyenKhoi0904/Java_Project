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
    private String idUser;
    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(AccountUser TaiKhoanNguoiDung, String loaitaiKhoan, String idUser) {
        this.TaiKhoanNguoiDung = TaiKhoanNguoiDung;
        this.loaitaiKhoan = loaitaiKhoan;
        this.idUser = idUser;
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
    
    public void QuenMatKhau() throws ClassNotFoundException, FileNotFoundException{
        Scanner input_User = new Scanner(System.in);
        boolean flag = false;
        User tmp = new User();
        ArrayList<User> user_array = docUserData();
        do{
            System.out.println("Mời bạn nhập email để xác định tài khoản");
            System.out.println("Email: ");
            String email = input_User.nextLine();
            while(!isGmailAddressRegexValid(email)){
                System.out.println("Đây không phải là email hợp lệ");
                System.out.print("Mời bạn nhập lại email: ");
                email = input_User.nextLine();
            }
            int index = 0;
            for(User user: user_array){
                if(user.getTaiKhoanNguoiDung().getEmail().equals(email)){
                    flag = true;
                    tmp = user;
                    break;
                }
                index++;
            }
            if(flag){
                String verificationCode = generateVerificationCode();
                System.out.println("Mã xác minh: " + verificationCode);
                System.out.print("Bạn hãy nhập lại mã xác minh: ");
                String inputVerificationCode = input_User.nextLine();
                while(!isVerificationCodeValid(verificationCode, inputVerificationCode)){
                    System.out.println("Bạn nhập sai mã xác minh");
                    System.out.print("Mời bạn nhập lại mã xác minh: ");
                    inputVerificationCode = input_User.nextLine();
                }
                System.out.print("Mời bạn nhập mật khẩu mới: ");
                String newPassword = input_User.nextLine();
                while(!isPasswordRegexValid(newPassword)){
                    System.out.println("Mật khẩu phải có ít nhất 6 kí tự và không được có khoảng trắng");
                    System.out.print("Mời bạn nhập lại mật khẩu");
                    newPassword = input_User.nextLine();
                }
                if(tmp instanceof FreeUser fu){
                    fu.getTaiKhoanNguoiDung().setMatkhau(newPassword);
                    user_array.set(index, fu);
                    if(ghiArrayListUserLenFile(user_array)){
                        System.out.println("Cập nhật mật khẩu mới thành công");
                    }else{
                        System.out.println("Cập nhật mật khẩu mới thành công");
                    }
                }else if(tmp instanceof ProUser pu){
                    pu.getTaiKhoanNguoiDung().setMatkhau(newPassword);
                    user_array.set(index, pu);
                    if(ghiArrayListUserLenFile(user_array)){
                        System.out.println("Cập nhật mật khẩu mới thành công");
                    }else{
                        System.out.println("Cập nhật mật khẩu mới thành công");
                    }
                }
            }else{
                System.out.println("Email chưa được đăng ký");
                System.out.println("Vui lòng nhập lại email");
            }
        }while(!flag);
    }
    
    private boolean ghiArrayListUserLenFile(ArrayList<User> a){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userData.txt"))){
            oos.writeObject(a);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    private boolean isVerificationCodeValid(String s, String string){
        return s.equals(string);
    }
    
    private String generateVerificationCode(){
        int length = 6;
        StringBuilder verificationCode = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++){
            verificationCode.append(random.nextInt(10));
        }
        return verificationCode.toString();
    }
    public User DangNhap() throws ClassNotFoundException, FileNotFoundException {
        ArrayList<User> pxm = docUserData();
        boolean isAccountValid = false;
        Scanner input_User = new Scanner(System.in);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++  ĐĂNG NHẬP  +++++++++++++++++++++++++++++++++++++");
        System.out.print("  Tài khoản: ");
        String username = input_User.nextLine();
        System.out.print("  Mật khẩu: ");
        String password = input_User.nextLine();
        if(!pxm.isEmpty()){
            for(User user: pxm){
                if(user.getTaiKhoanNguoiDung().getTendangnhap().equals(username)
                   && user.getTaiKhoanNguoiDung().getMatkhau().equals(password)){
                    System.out.println("Đăng nhập thành công");
                    return user;
                }
            }
            int index = 0;
            do{
                System.out.println("Sai tài khoản hoặc mật khẩu");
                System.out.println("Mời bạn nhập lại tài khoản và mật khẩu");
                System.out.print("  Tài khoản: ");
                username = input_User.nextLine();
                System.out.print("  Mật khẩu: ");
                password = input_User.nextLine();
                for(User user: pxm){
                    if(user.getTaiKhoanNguoiDung().getTendangnhap().equals(username)
                       && user.getTaiKhoanNguoiDung().getMatkhau().equals(password)){
                        System.out.println("Đăng nhập thành công");
                        return user;
                    }
                }
                index++;
                if(index >= 3){
                    System.out.println("Đăng nhập không thành công. Có thể do:");
                    System.out.println("1. Bạn chưa đăng ký tài khoản.");
                    System.out.println("2. Bạn đã quên mật khẩu.");
                    System.out.println("3. Tài khoản hoặc mật khẩu không đúng.");
                        
                    System.out.println("Bạn có muốn thoát ra menu để đăng ký tài khoản mới hoặc lấy lại mật khẩu không?");
                    System.out.println("0. Không");
                    System.out.println("1. Có");
                    System.out.print("Mời bạn lựa chọn: ");
                    String s = input_User.nextLine();
                    while(!isIntegerValueValid(s)){
                        System.out.println("Bạn nhập giá trị không hợp lệ");
                        System.out.print("Mời bạn nhập lại: ");
                        s = input_User.nextLine();
                    }
                    int i = Integer.parseInt(s);
                    if(i == 1){
                        return null;
                    }
                }
            }while(true);
        }else{
            System.out.println("Bạn hãy đăng ký tài khoản rồi đăng nhập");
            return null;
        }
    }

    private ArrayList<User> docUserData() throws ClassNotFoundException, FileNotFoundException{
        ArrayList<User> a = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream("userData.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            a = (ArrayList<User>) ois.readObject();
        } catch (EOFException e) {
            System.out.println("FILE TRỐNG");
            //e.printStackTrace();
        } catch (IOException e){
            System.out.println("Lỗi ở đây");
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
        String taikhoan, matkhau, email;
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
            System.out.print("- Mật khẩu: ");
            matkhau = input_User.nextLine();
            while(!isPasswordRegexValid(matkhau)){
                System.out.println("Mật khẩu phải có ít nhất 6 kí tự và không được có khoảng trắng");
                System.out.print("Mời bạn nhập lại mật khẩu");
                matkhau = input_User.nextLine();
            }
            
            //XỬ LÝ EMAIL
            System.out.print("- Email: ");
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
        AccountUser a = new AccountUser(taikhoan,matkhau,email);
        this.setTaiKhoanNguoiDung(a);
        
        System.out.println("^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^___^");
        System.out.println("- Chọn loại tài khoản mà bạn muốn đăng ký");
        System.out.println("0. Free User");
        System.out.println("1. Pro User");
        System.out.println("_____________________________________________________________________________________");
        System.out.print("Mời bạn lựa chọn: ");
        String s = input_User.nextLine();
        while(!isIntegerValueValid(s)){
            System.out.println("Bạn nhập giá trị không hợp lệ");
            System.out.print("Mời bạn nhập lại: ");
            s = input_User.nextLine();
        }
        int temporary = Integer.parseInt(s);
        if (temporary == 0) {
            this.setLoaitaiKhoan("FREE");
            this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
            //ĐANG LÀM TÀ ĐẠO YÊU CẦU KHÔNG BẮT CHƯỚC
            User u = new FreeUser();
            CopyGiaTri(u); // HÀM NÀY CÓ TÁC DỤNG GÁN GIÁ TRỊ CỦA THIS CHO U
            if(u instanceof FreeUser fu){
                fu.setQldmFree(new QuanLyDanhMucFree());
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
            s = input_User.nextLine();
            while(!isIntegerValueValid(s)){
                System.out.println("Bạn nhập giá trị không hợp lệ");
                System.out.print("Mời bạn nhập lại: ");
                s = input_User.nextLine();
            }
            int tmp = Integer.parseInt(s);
            switch (tmp) {
                case 1 -> {
                    System.out.println("Xác nhận thanh toán");
                    this.setLoaitaiKhoan("PRO");
                    this.setIdUser(generateID_User(this.getLoaitaiKhoan()));
                    //ĐANG LÀM TÀ ĐẠO YÊU CẦU KHÔNG BẮT CHƯỚC
                    User u = new ProUser();
                    CopyGiaTri(u); // HÀM NÀY CÓ TÁC DỤNG GÁN GIÁ TRỊ CỦA THIS CHO U
                    if(u instanceof ProUser pu){
                        pu.setQldmPro(new QuanLyDanhMucPro());
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
                        fu.setQldmFree(new QuanLyDanhMucFree());
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
    
    private boolean  isAccountRegexValid(String account){
        String regex = "^[a-zA-Z0-9 \\p{L}]{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(account);
        return matcher.matches();
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
    
    private boolean isIntegerValueValid(String s){
        return s.equals("0") || s.equals("1");
    }
    private void CopyGiaTri(User sourceUser){
        sourceUser.setLoaitaiKhoan(this.getLoaitaiKhoan());
        sourceUser.setIdUser(this.getIdUser());
        sourceUser.setTaiKhoanNguoiDung(this.getTaiKhoanNguoiDung());
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

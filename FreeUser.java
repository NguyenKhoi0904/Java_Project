/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bt.quanlythuchicanhan;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author aries
 */
public class FreeUser extends User implements Serializable, InterfaceClass.QuanLyGiaoDichDanhMuc_Interface, InterfaceClass.ManageCategory{
    private QuanLyDanhMucFree qldmFree;
    private static final long serialVersionUID = 1L;
    public FreeUser() {
    }

    public FreeUser(QuanLyDanhMucFree qldmFree) {
        this.qldmFree = qldmFree;
    }

    public FreeUser(QuanLyDanhMucFree qldmFree, AccountUser TaiKhoanNguoiDung, String loaitaiKhoan, String idUser) {
        super(TaiKhoanNguoiDung, loaitaiKhoan, idUser);
        this.qldmFree = qldmFree;
    }

    public QuanLyDanhMucFree getQldmFree() {
        return qldmFree;
    }

    public void setQldmFree(QuanLyDanhMucFree qldmFree) {
        this.qldmFree = qldmFree;
    }
    
    
    public void nangCapTaiKhoan() throws IOException{
        Scanner scanner = new Scanner(System.in);
        ProUser proUser = nangcap();
        QuanLyDanhMucPro pldm_pro = dataTranfer();
        proUser.setQldmPro(pldm_pro);
        ArrayList<User> arraylist = docUserData();
        System.out.println("Bạn phải trả 100USD để nâng cấp tài khoản thành Pro User (Nâng cấp 1 lần, sử dụng mãi mãi)");
        System.out.println("Bạn sẽ được sử dụng thêm các chức năng đặc quyền của Pro User và vô vàn những ưu đãi đặc biệt");
        System.out.println("Bạn có đồng ý nâng cấp không?");
        System.out.println("0. Không đồng ý");
        System.out.println("1. Đồng ý");
        int dem = 0;
        String s = scanner.nextLine();
        while(!isIntegerValueValid(s)){
            System.out.println("Nhập giá trị không hợp lệ");
            System.out.print("Mời bạn nhập lại: ");
            s = scanner.nextLine();
        }
        int luachon = Integer.parseInt(s);
        if(luachon == 1){
            for(User u: arraylist){
                if(u.getIdUser().equals(this.getIdUser())){
                    break;
                }
                dem++;
            }
            arraylist.set(dem, proUser);
            ghiUserMoiNangCapLenFile(arraylist);
        }
    }
    
    private boolean isIntegerValueValid(String s){
        return s.equals("0") || s.equals("1");
    }
    
    private QuanLyDanhMucPro dataTranfer(){
        QuanLyDanhMucPro ql = new QuanLyDanhMucPro();
        ql.setdanhmucchi(this.getQldmFree().getDanhMucChi());
        ql.setdanhmucthu(this.getQldmFree().getDanhMucThu());
        ql.setDsgiaodich(this.getQldmFree().getDsgiaodich());
        return ql;
        
    }
    private ProUser nangcap(){
        ProUser pu = new ProUser();
        pu.setLoaitaiKhoan("PRO");
        pu.setIdUser(generateID_User(pu.getLoaitaiKhoan()));
        pu.setTaiKhoanNguoiDung(this.getTaiKhoanNguoiDung());
        return pu;
    }
    private void ghiUserMoiNangCapLenFile(ArrayList<User> a) throws FileNotFoundException, IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userData.txt"))){
            oos.writeObject(a);
            System.out.println("NÂNG CẤP TÀI KHOẢN THÀNH CÔNG");
            System.out.println("Mời bạn đăng nhập lại");
        }catch(IOException e){
            System.out.println("NÂNG CẤP TÀI KHOẢN THẤT BẠI");
            e.printStackTrace();
        }
    }
    private ArrayList<User> docUserData(){
        ArrayList<User> a = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userData.txt"))) {
            a = (ArrayList<User>)ois.readObject();
        } catch (Exception e) {
        }
        return a;
    }
    private String generateID_User(String prefix){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 6));
        
        String formatNumber = String.format("%0" + 6 + "d", randomNumber);
        return prefix + formatNumber;
    }

    @Override
    public void tao1GiaoDichMoi() {
        this.getQldmFree().chonloaigiaodich();
    }

    @Override
    public void thongkeDanhMucTheoNgayThangNam() {
        this.getQldmFree().ThongKe();
    }

    @Override
    public void timKiemThongTinGiaoDich() {
        this.getQldmFree().timkiemthongtingiaodich();
    }

    @Override
    public void taoDanhMuc() {
        this.getQldmFree().ChonDanhMucDeThem();
    }
    
    @Override
    public void doiTenDanhMuc() {
        this.getQldmFree().chonDanhMucDeSua();
    }

    @Override
    public void xoaDanhMuc() {
        this.getQldmFree().ChonDanhMucdexoa();
    }

    @Override
    public String BaoCaoChiTietTheoTungDanhMucVaThoiGian() {
        return this.getQldmFree().BaoCaoChiTietTungDanhMuc();
    }

}

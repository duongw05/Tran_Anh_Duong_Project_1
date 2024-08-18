/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao;



import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import main.config.DBConnect;
import main.entity.NhanVienConnect;

/**
 *
 * @author tungt
 */
public class NhanVienDao extends EntityDao<NhanVienConnect, String> {

    String insert = "insert into NhanVien(ma_nhan_vien,ten_nhan_vien,mat_khau,so_dien_thoai,vai_tro,can_cuoc_cong_dan,email,gioi_tinh,trang_thai,ngay_sinh,dia_chi,hinh_anh)\n"
            + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
    String updata = "update NhanVien\n"
            + "set ten_nhan_vien=?,mat_khau=?,so_dien_thoai=?,vai_tro=?,can_cuoc_cong_dan=?,email=?,gioi_tinh=?,trang_thai=?,ngay_sinh=?,dia_chi=?,hinh_anh=?\n"
            + "where ma_nhan_vien=?";
    String delete = "delete NhanVien where maNV = ?";
    String selectAll = "select * from NhanVien";
    String select_byID = "select * from NhanVien where maNV = ?";
    String select_byEmail = "select * from NhanVien where email = ?";
    String updateMatKhau = "update NhanVien set mat_khau = ? where ma_nhan_vien = ?";

    @Override
    public void insert(NhanVienConnect entity) {
        DBConnect.update(insert, entity.getMaNV(), entity.getTenNV(), entity.getMatKhau(), entity.getSdt(),
                entity.isVaiTro(), entity.getCccd(), entity.getEmail(), entity.isGioiTinh(), entity.isTrangThai(),
                entity.getNgaySinh(), entity.getDiaChi(), entity.getHinhAnh());
    }

    @Override
    public void update(NhanVienConnect entity) {
        DBConnect.update(updata,
                entity.getTenNV(),
                entity.getMatKhau(),
                entity.getSdt(),
                entity.isVaiTro(),
                entity.getCccd(),
                entity.getEmail(),
                entity.isGioiTinh(),
                entity.isTrangThai(),
                entity.getNgaySinh(),
                entity.getDiaChi(),
                entity.getHinhAnh(), // Thêm thiếu tham số hinh_anh
                entity.getMaNV()); // Tham số ma_nhan_vien cuối cùng
    }

    public boolean capNhatMatKhau(String matKhau, String maNV) {
        DBConnect.update(updateMatKhau, matKhau, maNV);
        return true;
    }

    @Override
    public void delete(String key) {
        DBConnect.update(delete, key);
    }

    @Override
    public List<NhanVienConnect> selectAll() {
        return select_by_sql(selectAll);
    }

    @Override
    public NhanVienConnect select_byID(String key) {
        List<NhanVienConnect> list = this.select_by_sql(select_byID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhanVienConnect select_byEmail(String key) {
        List<NhanVienConnect> list = this.select_by_sql(select_byEmail, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVienConnect> select_by_sql(String sql, Object... args) {
        List<NhanVienConnect> list = new ArrayList<>();
        try {
            ResultSet r = DBConnect.query(sql, args);
            while (r.next()) {
                NhanVienConnect nv = new NhanVienConnect();
                nv.setMaNV(r.getString("ma_nhan_vien"));
                nv.setTenNV(r.getString("ten_nhan_vien"));
                nv.setMatKhau(r.getString("mat_khau"));
                nv.setSdt(r.getString("so_dien_thoai"));
                nv.setVaiTro(r.getBoolean("vai_tro"));
                nv.setCccd(r.getString("can_cuoc_cong_dan"));
                nv.setEmail(r.getString("email"));
                nv.setGioiTinh(r.getBoolean("gioi_tinh"));
                nv.setTrangThai(r.getBoolean("trang_thai"));
                nv.setNgaySinh(r.getDate("ngay_sinh"));
                nv.setDiaChi(r.getString("dia_chi"));
                list.add(nv);

            }

            r.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<NhanVienConnect> searchNV(String key) {
        String select_search = "select * from nhanvien where ten_nhan_vien like ?";
        return select_by_sql(select_search, "%" + key + "%");
    }

}

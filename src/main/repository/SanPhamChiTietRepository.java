/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import main.config.DBConnect;
import main.entity.SanPhamChiTiet;
import com.Product.form.NhanVienForm;
import main.response.SanPhamChiTietRespone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class SanPhamChiTietRepository {

    public ArrayList<SanPhamChiTietRespone> getAll() {
        String sql = "SELECT \n"
                + "    spct.id, \n"
                + "    spct.ma_san_pham_chi_tiet, \n"
                + "    sp.ten_san_pham, \n"
                + "    th.ten_thuong_hieu, \n"
                + "    xx.ten_nuoc, \n"
                + "    ms.ten_mau, \n"
                + "    kt.size, \n"
                + "    cl.ten_chat_lieu, \n"
                + "    ca.ten_co_ao, \n"
                + "    dd.ten_do_day, \n"
                + "    tlh.ten_tinh_linh_hoat, \n"
                + "    spct.gia_ban, \n"
                + "    spct.so_luong_ton, \n"
                + "    CASE \n"
                + "        WHEN spct.so_luong_ton > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai\n"
                + "FROM \n"
                + "    dbo.SanPhamChiTiet spct\n"
                + "INNER JOIN \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id\n"
                + "INNER JOIN \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id \n"
                + "INNER JOIN \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id \n"
                + "INNER JOIN \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id \n"
                + "INNER JOIN \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id \n"
                + "INNER JOIN \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id \n"
                + "INNER JOIN \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id \n"
                + "INNER JOIN \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id \n"
                + "INNER JOIN \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id \n"
                + "WHERE \n"
                + "    spct.trang_thai_ban = 1;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public boolean add(SanPhamChiTiet spct) {
        int check = 0;

        String sql = "INSERT INTO SanPhamChiTiet "
                + "(id_san_pham, id_thuong_hieu, id_xuat_xu, id_mau_sac, id_kich_thuoc, "
                + "id_chat_lieu, id_co_ao, id_do_day, id_tinh_linh_hoat, gia_ban, so_luong_ton, trang_thai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Kiểm tra giá trị không null
            if (spct.getSanPhamID() == null || spct.getThuongHieuID() == null
                    || spct.getXuatXuID() == null || spct.getMauSacID() == null
                    || spct.getKichThuocID() == null || spct.getChatLieuID() == null
                    || spct.getCoAoID() == null || spct.getDoDayID() == null
                    || spct.getTinhLinhHoatID() == null || spct.getGiaBan() == null
                    || spct.getSoLuongTon() == null) {
                throw new IllegalArgumentException("Các giá trị bắt buộc không thể null.");
            }

            ps.setObject(1, spct.getSanPhamID());
            ps.setObject(2, spct.getThuongHieuID());
            ps.setObject(3, spct.getXuatXuID());
            ps.setObject(4, spct.getMauSacID());
            ps.setObject(5, spct.getKichThuocID());
            ps.setObject(6, spct.getChatLieuID());
            ps.setObject(7, spct.getCoAoID());
            ps.setObject(8, spct.getDoDayID());
            ps.setObject(9, spct.getTinhLinhHoatID());
            ps.setObject(10, spct.getGiaBan());
            ps.setObject(11, spct.getSoLuongTon());
            ps.setObject(12, spct.isTrangThai());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi dữ liệu: " + e.getMessage());
        }

        return check > 0;
    }

    public ArrayList<SanPhamChiTietRespone> searchh(String maSP) {
        String sql = "SELECT \n"
                + "    spct.id, \n"
                + "    spct.ma_san_pham_chi_tiet, \n"
                + "    sp.ten_san_pham, \n"
                + "    th.ten_thuong_hieu, \n"
                + "    xx.ten_nuoc, \n"
                + "    ms.ten_mau, \n"
                + "    kt.size, \n"
                + "    cl.ten_chat_lieu, \n"
                + "    ca.ten_co_ao, \n"
                + "    dd.ten_do_day, \n"
                + "    tlh.ten_tinh_linh_hoat, \n"
                + "    spct.gia_ban, \n"
                + "    spct.so_luong_ton, \n"
                + "    CASE \n"
                + "        WHEN spct.so_luong_ton > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai\n"
                + "FROM \n"
                + "    dbo.SanPhamChiTiet spct\n"
                + "INNER JOIN \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id\n"
                + "INNER JOIN \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id\n"
                + "INNER JOIN \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id\n"
                + "INNER JOIN \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id\n"
                + "INNER JOIN \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id\n"
                + "INNER JOIN \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id\n"
                + "INNER JOIN \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id\n"
                + "INNER JOIN \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id\n"
                + "INNER JOIN \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id\n"
                + "WHERE \n"
                + "    spct.ma_san_pham_chi_tiet LIKE ?\n"
                + "    OR sp.ten_san_pham LIKE ?\n"
                + "    OR th.ten_thuong_hieu LIKE ?\n"
                + "    OR xx.ten_nuoc LIKE ?\n"
                + "    OR ms.ten_mau LIKE ?\n"
                + "    OR kt.size LIKE ?\n"
                + "    OR cl.ten_chat_lieu LIKE ?\n"
                + "    OR ca.ten_co_ao LIKE ?\n"
                + "    OR dd.ten_do_day LIKE ?\n"
                + "    OR tlh.ten_tinh_linh_hoat LIKE ?\n"
                + "    OR spct.gia_ban LIKE ?\n"
                + "    OR spct.so_luong_ton LIKE ?\n"
                + "    OR spct.trang_thai LIKE ?";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchString = "%" + maSP + "%"; // Thêm % vào để tạo thành mô phỏng tìm kiếm
            // Đặt giá trị cho các tham số trong câu truy vấn SQL
            for (int i = 1; i <= 13; i++) {
                ps.setString(i, searchString);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham_chi_tiet = rs.getString("ma_san_pham_chi_tiet");
                String ten_san_pham = rs.getString("ten_san_pham");
                String thuong_hieu = rs.getString("ten_thuong_hieu");
                String ten_nuoc = rs.getString("ten_nuoc");
                String ten_mau = rs.getString("ten_mau");
                String size = rs.getString("size");
                String ten_chat_lieu = rs.getString("ten_chat_lieu");
                String ten_co_ao = rs.getString("ten_co_ao");
                String ten_do_day = rs.getString("ten_do_day");
                String ten_tinh_linh_hoat = rs.getString("ten_tinh_linh_hoat");
                Double giaBan = rs.getDouble("gia_ban");
                Integer so_luong_ton = rs.getInt("so_luong_ton");
                Boolean trang_thai = rs.getBoolean("trang_thai");

                SanPhamChiTietRespone sanPham = new SanPhamChiTietRespone(id, ma_san_pham_chi_tiet, ten_san_pham, thuong_hieu, ten_nuoc, ten_mau, size, ten_chat_lieu, ten_co_ao, ten_do_day, ten_tinh_linh_hoat, giaBan, so_luong_ton, true);
                lists.add(sanPham);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> getAllGiamDan() {
        String sql = "SELECT \n"
                + "    spct.id, \n"
                + "    spct.ma_san_pham_chi_tiet, \n"
                + "    sp.ten_san_pham, \n"
                + "    th.ten_thuong_hieu, \n"
                + "    xx.ten_nuoc, \n"
                + "    ms.ten_mau, \n"
                + "    kt.size, \n"
                + "    cl.ten_chat_lieu, \n"
                + "    ca.ten_co_ao, \n"
                + "    dd.ten_do_day, \n"
                + "    tlh.ten_tinh_linh_hoat, \n"
                + "    spct.gia_ban, \n"
                + "    spct.so_luong_ton, \n"
                + "    CASE \n"
                + "        WHEN spct.so_luong_ton > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai\n"
                + "FROM \n"
                + "    dbo.SanPhamChiTiet spct\n"
                + "INNER JOIN \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id\n"
                + "INNER JOIN \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id\n"
                + "INNER JOIN \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id\n"
                + "INNER JOIN \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id\n"
                + "INNER JOIN \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id\n"
                + "INNER JOIN \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id\n"
                + "INNER JOIN \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id\n"
                + "INNER JOIN \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id\n"
                + "INNER JOIN \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id\n"
                + "WHERE \n"
                + "    spct.trang_thai_ban = 1\n"
                + "ORDER BY \n"
                + "    sp.ngay_tao DESC;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoDieuKien(String tenChatLieu, String tenXuatXu, String tenThuongHieu, boolean sapXepGiaBan) {

        String sql = "SELECT  \n"
                + "    spct.id,  \n"
                + "    spct.ma_san_pham_chi_tiet,  \n"
                + "    sp.ten_san_pham,  \n"
                + "    th.ten_thuong_hieu,  \n"
                + "    xx.ten_nuoc,  \n"
                + "    ms.ten_mau,  \n"
                + "    kt.size,  \n"
                + "    cl.ten_chat_lieu,  \n"
                + "    ca.ten_co_ao,  \n"
                + "    dd.ten_do_day,  \n"
                + "    tlh.ten_tinh_linh_hoat,  \n"
                + "    spct.gia_ban,  \n"
                + "    spct.so_luong_ton,  \n"
                + "    CASE \n"
                + "        WHEN spct.so_luong_ton > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai\n"
                + "FROM  \n"
                + "    dbo.SanPhamChiTiet spct  \n"
                + "INNER JOIN  \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id\n"
                + "INNER JOIN  \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id  \n"
                + "WHERE  \n"
                + "    spct.trang_thai_ban = 1\n"
                + "    AND cl.ten_chat_lieu LIKE ?\n"
                + "    AND xx.ten_nuoc LIKE ?\n"
                + "    AND th.ten_thuong_hieu LIKE ?\n"
                + "ORDER BY  \n"
                + "    CASE WHEN ? = 0 THEN spct.gia_ban END ASC,\n"
                + "    CASE WHEN ? = 1 THEN spct.gia_ban END DESC;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenChatLieu);
            ps.setObject(2, tenXuatXu);
            ps.setObject(3, tenThuongHieu);
            ps.setObject(4, sapXepGiaBan ? "0" : "1");
            ps.setObject(5, sapXepGiaBan ? "0" : "1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietRespone> getSanPhamChiTietByIdSanPham(int idSanPham) {
        String sql = "SELECT  \n"
                + "    spct.id,  \n"
                + "    spct.ma_san_pham_chi_tiet,  \n"
                + "    sp.ten_san_pham,  \n"
                + "    th.ten_thuong_hieu,  \n"
                + "    xx.ten_nuoc,  \n"
                + "    ms.ten_mau,  \n"
                + "    kt.size,  \n"
                + "    cl.ten_chat_lieu,  \n"
                + "    ca.ten_co_ao,  \n"
                + "    dd.ten_do_day,  \n"
                + "    tlh.ten_tinh_linh_hoat,  \n"
                + "    spct.gia_ban,  \n"
                + "    spct.so_luong_ton,  \n"
                + "    CASE \n"
                + "        WHEN spct.so_luong_ton > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai\n"
                + "FROM  \n"
                + "    dbo.SanPhamChiTiet spct  \n"
                + "INNER JOIN  \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id\n"
                + "INNER JOIN  \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id  \n"
                + "WHERE  \n"
                + "    spct.trang_thai_ban = 1  \n"
                + "    AND sp.id = ?;"; // Lọc theo id_san_pham

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham_chi_tiet"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getString("ten_nuoc"),
                        rs.getString("ten_mau"),
                        rs.getString("size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getString("ten_co_ao"),
                        rs.getString("ten_do_day"),
                        rs.getString("ten_tinh_linh_hoat"),
                        rs.getDouble("gia_ban"),
                        rs.getInt("so_luong_ton"),
                        rs.getBoolean("trang_thai")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return lists;
    }

    public SanPhamChiTietRespone getSanPhamChiTietByMaSPCT(String maSPCT) {
        String sql = "SELECT  \n"
                + "    spct.id,  \n"
                + "    spct.ma_san_pham_chi_tiet,  \n"
                + "    sp.ten_san_pham,  \n"
                + "    th.ten_thuong_hieu,  \n"
                + "    xx.ten_nuoc,  \n"
                + "    ms.ten_mau,  \n"
                + "    kt.size,  \n"
                + "    cl.ten_chat_lieu,  \n"
                + "    ca.ten_co_ao,  \n"
                + "    dd.ten_do_day,  \n"
                + "    tlh.ten_tinh_linh_hoat,  \n"
                + "    spct.gia_ban,  \n"
                + "    spct.so_luong_ton,  \n"
                + "    spct.trang_thai  \n"
                + "FROM  \n"
                + "    dbo.SanPhamChiTiet spct  \n"
                + "INNER JOIN  \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id  \n"
                + "WHERE  \n"
                + "    spct.trang_thai_ban = 1  \n"
                + "    AND spct.ma_san_pham_chi_tiet = ?;";

        SanPhamChiTietRespone spct = null;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSPCT);  // Sử dụng setString thay vì setObject vì maSPCT là String
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                spct = new SanPhamChiTietRespone(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham_chi_tiet"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getString("ten_nuoc"),
                        rs.getString("ten_mau"),
                        rs.getString("size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getString("ten_co_ao"),
                        rs.getString("ten_do_day"),
                        rs.getString("ten_tinh_linh_hoat"),
                        rs.getDouble("gia_ban"),
                        rs.getInt("so_luong_ton"),
                        rs.getBoolean("trang_thai")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return spct;
    }

    public boolean updateSanPhamChiTiet(SanPhamChiTiet spct, String maSPCT) {
        int check = 0;
        String sql = "update SanPhamChiTiet\n"
                + "set id_san_pham=?,id_thuong_hieu=?,id_xuat_xu=?,id_mau_sac=?,id_kich_thuoc=?,id_chat_lieu=?,id_co_ao=?,id_do_day=?,id_tinh_linh_hoat=?,gia_ban=?,so_luong_ton=?\n"
                + "where ma_san_pham_chi_tiet=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, spct.getSanPhamID());
            ps.setObject(2, spct.getThuongHieuID());
            ps.setObject(3, spct.getXuatXuID());
            ps.setObject(4, spct.getMauSacID());
            ps.setObject(5, spct.getKichThuocID());
            ps.setObject(6, spct.getChatLieuID());
            ps.setObject(7, spct.getCoAoID());
            ps.setObject(8, spct.getDoDayID());
            ps.setObject(9, spct.getTinhLinhHoatID());
            ps.setObject(10, spct.getGiaBan());
            ps.setObject(11, spct.getSoLuongTon());
            ps.setObject(12, maSPCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<SanPhamChiTietRespone> getListSanPhamChiTietByMaSPCT(String maSPCT) {
        String sql = "SELECT  \n"
                + "    spct.id,  \n"
                + "    spct.ma_san_pham_chi_tiet,  \n"
                + "    sp.ten_san_pham,  \n"
                + "    th.ten_thuong_hieu,  \n"
                + "    xx.ten_nuoc,  \n"
                + "    ms.ten_mau,  \n"
                + "    kt.size,  \n"
                + "    cl.ten_chat_lieu,  \n"
                + "    ca.ten_co_ao,  \n"
                + "    dd.ten_do_day,  \n"
                + "    tlh.ten_tinh_linh_hoat,  \n"
                + "    spct.gia_ban,  \n"
                + "    spct.so_luong_ton,  \n"
                + "    spct.trang_thai  \n"
                + "FROM  \n"
                + "    dbo.SanPhamChiTiet spct  \n"
                + "INNER JOIN  \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id  \n"
                + "WHERE  \n"
                + "    spct.trang_thai_ban = 1  \n"
                + "    AND spct.ma_san_pham_chi_tiet = ?;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSPCT);  // Sử dụng setString thay vì setObject vì maSPCT là String
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt("id"), rs.getString("ma_san_pham_chi_tiet"), rs.getString("ten_san_pham"), rs.getString("ten_thuong_hieu"), rs.getString("ten_nuoc"), rs.getString("ten_mau"), rs.getString("size"), rs.getString("ten_chat_lieu"), rs.getString("ten_co_ao"), rs.getString("ten_do_day"), rs.getString("ten_tinh_linh_hoat"), rs.getDouble("gia_ban"), rs.getInt("so_luong_ton"), rs.getBoolean("trang_thai")));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return lists;
    }

    public boolean removeSanPhamChiTiet(Integer id_san_pham) {
        int check = 0;
        String sql = "update SanPhamChiTiet\n"
                + "	set trang_thai_ban = 0\n"
                + "	where id_san_pham =?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id_san_pham);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateSoLuong(SanPhamChiTietRespone response) {
        int check = 0;
        String sql = "UPDATE SanPhamChiTiet\n"
                + "SET so_luong_ton = ?\n"
                + "WHERE id=?;";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, response.getSoLuong());
            ps.setObject(2, response.getID());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    public boolean updateTrangThai(Integer id) {
        int check = 0;
        String sql = "update SanPhamChiTiet\n"
                + "set trang_thai_ban = 0\n"
                + "where id =?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<SanPhamChiTietRespone> locTheoDieuKienBanHang(String tenPhongCach, String tenKichThuoc, String tenXuatXu, boolean sapXepGiaBan) {

        String sql = "SELECT \n"
                + "    spct.id, \n"
                + "    spct.ma_san_pham_chi_tiet, \n"
                + "    sp.ten_san_pham, \n"
                + "    th.ten_thuong_hieu, \n"
                + "    xx.ten_nuoc, \n"
                + "    ms.ten_mau, \n"
                + "    kt.size, \n"
                + "    cl.ten_chat_lieu, \n"
                + "    ca.ten_co_ao, \n"
                + "    dd.ten_do_day, \n"
                + "    tlh.ten_tinh_linh_hoat, \n"
                + "    spct.gia_ban, \n"
                + "    spct.so_luong_ton, \n"
                + "    CASE \n"
                + "        WHEN spct.so_luong_ton > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai\n"
                + "FROM \n"
                + "    dbo.SanPhamChiTiet spct \n"
                + "INNER JOIN \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id\n"
                + "INNER JOIN \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id \n"
                + "INNER JOIN \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id \n"
                + "INNER JOIN \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id \n"
                + "INNER JOIN \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id \n"
                + "INNER JOIN \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id \n"
                + "INNER JOIN \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id \n"
                + "INNER JOIN \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id \n"
                + "INNER JOIN \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id \n"
                + "WHERE \n"
                + "    spct.trang_thai_ban = 1\n"
                + "    AND tlh.ten_tinh_linh_hoat LIKE ?\n"
                + "    AND kt.size LIKE ?\n"
                + "    AND xx.ten_nuoc LIKE ?\n"
                + "ORDER BY \n"
                + "    CASE WHEN ? = 0 THEN spct.gia_ban END ASC,\n"
                + "    CASE WHEN ? = 1 THEN spct.gia_ban END DESC;";

        ArrayList<SanPhamChiTietRespone> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenPhongCach);
            ps.setObject(2, tenKichThuoc);
            ps.setObject(3, tenXuatXu);
            ps.setObject(4, sapXepGiaBan ? "0" : "1");
            ps.setObject(5, sapXepGiaBan ? "0" : "1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietRespone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getInt(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public int countByIdSPCT(int idSPCT) {
        String sql = "SELECT COUNT(*) FROM SanPhamChiTiet WHERE id = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSPCT);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy sản phẩm chi tiết
    }

    public SanPhamChiTietRespone getSanPhamChiTietByIdSanPhamCheck(int idSanPham) {
        String sql = "SELECT  \n"
                + "    spct.id,  \n"
                + "    spct.ma_san_pham_chi_tiet,  \n"
                + "    sp.ten_san_pham,  \n"
                + "    th.ten_thuong_hieu,  \n"
                + "    xx.ten_nuoc,  \n"
                + "    ms.ten_mau,  \n"
                + "    kt.size,  \n"
                + "    cl.ten_chat_lieu,  \n"
                + "    ca.ten_co_ao,  \n"
                + "    dd.ten_do_day,  \n"
                + "    tlh.ten_tinh_linh_hoat,  \n"
                + "    spct.gia_ban,  \n"
                + "    spct.so_luong_ton,  \n"
                + "    CASE \n"
                + "        WHEN spct.so_luong_ton > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai\n"
                + "FROM  \n"
                + "    dbo.SanPhamChiTiet spct  \n"
                + "INNER JOIN  \n"
                + "    dbo.SanPham sp ON spct.id_san_pham = sp.id\n"
                + "INNER JOIN  \n"
                + "    dbo.ThuongHieu th ON spct.id_thuong_hieu = th.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.XuatXu xx ON spct.id_xuat_xu = xx.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.MauSac ms ON spct.id_mau_sac = ms.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.KichThuoc kt ON spct.id_kich_thuoc = kt.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.ChatLieu cl ON spct.id_chat_lieu = cl.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.CoAo ca ON spct.id_co_ao = ca.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.DoDay dd ON spct.id_do_day = dd.id  \n"
                + "INNER JOIN  \n"
                + "    dbo.TinhLinhHoat tlh ON spct.id_tinh_linh_hoat = tlh.id  \n"
                + "WHERE  \n"
                + "    spct.trang_thai_ban = 1  \n"
                + "    AND sp.id = ?;"; // Lọc theo id_san_pham

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSanPham);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Return the first result
                return new SanPhamChiTietRespone(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham_chi_tiet"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getString("ten_nuoc"),
                        rs.getString("ten_mau"),
                        rs.getString("size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getString("ten_co_ao"),
                        rs.getString("ten_do_day"),
                        rs.getString("ten_tinh_linh_hoat"),
                        rs.getDouble("gia_ban"),
                        rs.getInt("so_luong_ton"),
                        rs.getBoolean("trang_thai")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Return null if no result is found or if an error occurs
    }

}

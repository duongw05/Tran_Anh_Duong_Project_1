/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import main.config.DBConnect;
import main.entity.SanPham;
import main.entity.SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SanPhamRepository {

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.id,\n"
                + "    sp.ma_san_pham,\n"
                + "    sp.ten_san_pham,\n"
                + "    sp.mo_ta,\n"
                + "    COALESCE(ct.tong_so_luong_ton, 0) AS so_luong,\n"
                + "    CASE \n"
                + "        WHEN COALESCE(ct.tong_so_luong_ton, 0) > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai,\n"
                + "    sp.ngay_tao\n"
                + "FROM \n"
                + "    SanPham sp\n"
                + "LEFT JOIN (\n"
                + "    SELECT \n"
                + "        id_san_pham,\n"
                + "        SUM(so_luong_ton) AS tong_so_luong_ton\n"
                + "    FROM \n"
                + "        SanPhamChiTiet\n"
                + "    GROUP BY \n"
                + "        id_san_pham\n"
                + ") ct ON sp.id = ct.id_san_pham\n"
                + "WHERE \n"
                + "    sp.trang_thai_ban = 1;";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham(
                        rs.getInt("id"), // Sử dụng tên cột thay vì chỉ số
                        rs.getString("ma_san_pham"),
                        rs.getString("ten_san_pham"),
                        rs.getString("mo_ta"),
                        rs.getInt("so_luong"), // Sử dụng tên cột
                        rs.getBoolean("trang_thai"),
                        rs.getDate("ngay_tao")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<SanPham> getSanPhamDangBan() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.id,\n"
                + "    sp.ma_san_pham,\n"
                + "    sp.ten_san_pham,\n"
                + "    sp.mo_ta,\n"
                + "    COALESCE(ct.tong_so_luong_ton, 0) AS so_luong,\n"
                + "    CASE \n"
                + "        WHEN COALESCE(ct.tong_so_luong_ton, 0) > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai,\n"
                + "    sp.ngay_tao\n"
                + "FROM \n"
                + "    SanPham sp\n"
                + "LEFT JOIN (\n"
                + "    SELECT \n"
                + "        id_san_pham,\n"
                + "        SUM(so_luong_ton) AS tong_so_luong_ton\n"
                + "    FROM \n"
                + "        SanPhamChiTiet\n"
                + "    GROUP BY \n"
                + "        id_san_pham\n"
                + ") ct ON sp.id = ct.id_san_pham\n"
                + "WHERE \n"
                + "    sp.trang_thai_ban = 1\n"
                + "    AND CASE \n"
                + "            WHEN COALESCE(ct.tong_so_luong_ton, 0) > 0 THEN 1\n"
                + "            ELSE 0\n"
                + "        END = 1\n"
                + "ORDER BY \n"
                + "    sp.ngay_tao DESC;"; // Lọc sản phẩm đang bán

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham = rs.getString("ma_san_pham");
                String ten_san_pham = rs.getString("ten_san_pham");
                String mo_ta = rs.getString("mo_ta");
                int so_luong = rs.getInt("so_luong");
                boolean trang_thai = rs.getBoolean("trang_thai");
                Date ngay_tao = rs.getDate("ngay_tao");

                SanPham sanPham = new SanPham(id, ma_san_pham, ten_san_pham, mo_ta, so_luong, trang_thai, ngay_tao);
                list.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<SanPham> getSanPhamNgungBan() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.id,\n"
                + "    sp.ma_san_pham,\n"
                + "    sp.ten_san_pham,\n"
                + "    sp.mo_ta,\n"
                + "    COALESCE(ct.tong_so_luong_ton, 0) AS so_luong,\n"
                + "    CASE \n"
                + "        WHEN COALESCE(ct.tong_so_luong_ton, 0) > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai,\n"
                + "    sp.ngay_tao\n"
                + "FROM \n"
                + "    SanPham sp\n"
                + "LEFT JOIN (\n"
                + "    SELECT \n"
                + "        id_san_pham,\n"
                + "        SUM(so_luong_ton) AS tong_so_luong_ton\n"
                + "    FROM \n"
                + "        SanPhamChiTiet\n"
                + "    GROUP BY \n"
                + "        id_san_pham\n"
                + ") ct ON sp.id = ct.id_san_pham\n"
                + "WHERE \n"
                + "    sp.trang_thai_ban = 1\n"
                + "    AND CASE \n"
                + "            WHEN COALESCE(ct.tong_so_luong_ton, 0) > 0 THEN 1\n"
                + "            ELSE 0\n"
                + "        END = 0\n"
                + "ORDER BY \n"
                + "    sp.ngay_tao DESC;"; // Lọc sản phẩm ngừng bán

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham = rs.getString("ma_san_pham");
                String ten_san_pham = rs.getString("ten_san_pham");
                String mo_ta = rs.getString("mo_ta");
                int so_luong = rs.getInt("so_luong");
                boolean trang_thai = rs.getBoolean("trang_thai");
                Date ngay_tao = rs.getDate("ngay_tao");

                SanPham sanPham = new SanPham(id, ma_san_pham, ten_san_pham, mo_ta, so_luong, trang_thai, ngay_tao);
                list.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public boolean add(SanPham sanpham) {
        int check = 0;

        String sql = "insert into SanPham(ten_san_pham,mo_ta)\n"
                + "values(?,?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, sanpham.getTenSanPham());
            ps.setObject(2, sanpham.getMoTa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public ArrayList<SanPham> getAllGiamDan() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.id,\n"
                + "    sp.ma_san_pham,\n"
                + "    sp.ten_san_pham,\n"
                + "    sp.mo_ta,\n"
                + "    COALESCE(ct.tong_so_luong_ton, 0) AS so_luong,\n"
                + "    CASE \n"
                + "        WHEN COALESCE(ct.tong_so_luong_ton, 0) > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai,\n"
                + "    sp.ngay_tao\n"
                + "FROM \n"
                + "    SanPham sp\n"
                + "LEFT JOIN (\n"
                + "    SELECT \n"
                + "        id_san_pham,\n"
                + "        SUM(so_luong_ton) AS tong_so_luong_ton\n"
                + "    FROM \n"
                + "        SanPhamChiTiet\n"
                + "    GROUP BY \n"
                + "        id_san_pham\n"
                + ") ct ON sp.id = ct.id_san_pham\n"
                + "WHERE \n"
                + "    sp.trang_thai_ban = 1\n"
                + "ORDER BY \n"
                + "    sp.ngay_tao DESC;";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham(
                        rs.getInt("id"), // 1
                        rs.getString("ma_san_pham"), // 2
                        rs.getString("ten_san_pham"), // 3
                        rs.getString("mo_ta"), // 4
                        rs.getInt("so_luong"), // 5
                        rs.getBoolean("trang_thai"), // 6
                        rs.getDate("ngay_tao") // 7
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public boolean update(SanPham newSanPham, String ma) {
        int check = 0;
        String sql = "UPDATE SanPham\n"
                + "SET ten_san_pham = ?, mo_ta = ?\n"
                + "WHERE ma_san_pham=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, newSanPham.getTenSanPham());
            ps.setObject(2, newSanPham.getMoTa());
            ps.setObject(3, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean remove(Integer id) {
        int check = 0;
        String sql = "UPDATE SanPham\n"
                + "SET trang_thai_ban = 0\n"
                + "WHERE id = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<SanPham> searchh(String searchString) {
        // Cập nhật câu lệnh SQL để tìm kiếm theo các cột cần thiết
        String sql = "SELECT \n"
                + "    sp.id,\n"
                + "    sp.ma_san_pham,\n"
                + "    sp.ten_san_pham,\n"
                + "    sp.mo_ta,\n"
                + "    COALESCE(SUM(spct.so_luong_ton), 0) AS so_luong,\n"
                + "    CASE \n"
                + "        WHEN COALESCE(SUM(spct.so_luong_ton), 0) > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai,\n"
                + "    sp.ngay_tao\n"
                + "FROM \n"
                + "    SanPham sp\n"
                + "LEFT JOIN \n"
                + "    SanPhamChiTiet spct ON sp.id = spct.id_san_pham\n"
                + "WHERE \n"
                + "    sp.ma_san_pham LIKE ? \n"
                + "    OR sp.ten_san_pham LIKE ? \n"
                + "    OR sp.mo_ta LIKE ?\n"
                + "GROUP BY \n"
                + "    sp.id, \n"
                + "    sp.ma_san_pham, \n"
                + "    sp.ten_san_pham, \n"
                + "    sp.mo_ta, \n"
                + "    sp.ngay_tao;";

        ArrayList<SanPham> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String query = "%" + searchString + "%"; // Thêm % vào để tạo thành mô phỏng tìm kiếm

            // Đặt giá trị cho các tham số trong câu truy vấn SQL
            ps.setObject(1, query);
            ps.setObject(2, query);
            ps.setObject(3, query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ma_san_pham = rs.getString("ma_san_pham");
                String ten_san_pham = rs.getString("ten_san_pham");
                String mo_ta = rs.getString("mo_ta");
                int so_luong = rs.getInt("so_luong");
                boolean trang_thai = rs.getBoolean("trang_thai");
                Date ngay_tao = rs.getDate("ngay_tao");

                SanPham sanPham = new SanPham(id, ma_san_pham, ten_san_pham, mo_ta, so_luong, trang_thai, ngay_tao);
                lists.add(sanPham);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

    public ArrayList<SanPham> getSanPhamByTen(String tenSanPham) {
        ArrayList<SanPham> list = new ArrayList<>();
        String query = "update SanPham\n"
                + "set trang_thai = 1\n"
                + "where ten_san_pham =?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(query)) {
            ps.setObject(1, "%" + tenSanPham + "%"); // Tìm kiếm không phân biệt chữ hoa chữ thường
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
            e.printStackTrace();
        }
        String sql = "SELECT \n"
                + "    sp.id,\n"
                + "    sp.ma_san_pham,\n"
                + "    sp.ten_san_pham,\n"
                + "    sp.mo_ta,\n"
                + "    COALESCE(ct.tong_so_luong_ton, 0) AS so_luong,\n"
                + "    CASE \n"
                + "        WHEN COALESCE(ct.tong_so_luong_ton, 0) > 0 THEN 1\n"
                + "        ELSE 0\n"
                + "    END AS trang_thai,\n"
                + "    sp.ngay_tao\n"
                + "FROM \n"
                + "    SanPham sp\n"
                + "LEFT JOIN (\n"
                + "    SELECT \n"
                + "        id_san_pham,\n"
                + "        SUM(so_luong_ton) AS tong_so_luong_ton\n"
                + "    FROM \n"
                + "        SanPhamChiTiet\n"
                + "    GROUP BY \n"
                + "        id_san_pham\n"
                + ") ct ON sp.id = ct.id_san_pham\n"
                + "WHERE \n"
                + "    sp.ten_san_pham LIKE ?;";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tenSanPham + "%"); // Tìm kiếm không phân biệt chữ hoa chữ thường
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPham(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham"),
                        rs.getString("ten_san_pham"),
                        rs.getString("mo_ta"),
                        rs.getInt("so_luong"), // Sử dụng tổng số lượng tồn tính từ hàm SUM
                        rs.getBoolean("trang_thai"),
                        rs.getDate("ngay_tao")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateSoLuong(SanPham sp) {
        int check = 0;
        String sql = "update SanPham\n"
                + "set so_luong =?\n"
                + "where id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sp.getSoLuong());
            ps.setObject(2, sp.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean isSanPhamExist(String ten) {
        String sql = "SELECT COUNT(*) FROM SanPham  WHERE ten_san_pham= ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer getIDByMaSanPham(String ma) {
        String query = "select id from SanPham where ma_san_pham =?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
}

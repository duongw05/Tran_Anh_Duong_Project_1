/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import main.config.DBConnect;
import main.entity.HoaDon;
import main.response.HoaDonResponse;
import java.util.Date;
import main.response.HoaDonResponse1;
//

/**
 *
 * @author ADMIN
 */
public class HoaDonRepository {

    public ArrayList<HoaDonResponse> getAll() {
        String sql = "SELECT \n"
                + "    dbo.HoaDon.id, \n"
                + "    dbo.HoaDon.ma_hoa_don, \n"
                + "    dbo.HoaDon.ngay_tao, \n"
                + "    dbo.HoaDon.ngay_cap_nhat, \n"
                + "    dbo.HoaDon.tong_tien, \n" // Đổi tên cột để lấy tổng tiền gốc
                + "    dbo.HoaDon.tong_tien_sau_khi_giam, \n" // Thêm cột tổng tiền sau khi giảm
                + "    dbo.NhanVien.ma_nhan_vien, \n"
                + "    dbo.KhachHang.ho_ten, \n"
                + "    dbo.KhachHang.dia_chi, \n"
                + "    dbo.KhachHang.so_dien_thoai, \n"
                + "    dbo.HoaDon.trang_thai, \n"
                + "    dbo.HoaDon.hinh_thuc_thanh_toan\n"
                + "FROM \n"
                + "    dbo.HoaDon \n"
                + "INNER JOIN \n"
                + "    dbo.NhanVien \n"
                + "    ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id \n"
                + "INNER JOIN \n"
                + "    dbo.KhachHang \n"
                + "    ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id\n"
                + "ORDER BY dbo.HoaDon.ngay_tao DESC";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            ArrayList<HoaDonResponse> lists = new ArrayList<>();
            while (rs.next()) {
                double tongTien = rs.getDouble(5);
                Double tongTienSauKhiGiam = rs.getObject(6) != null ? rs.getDouble(6) : null;

                // Nếu tổng tiền sau khi giảm là null, sử dụng tổng tiền gốc
                if (tongTienSauKhiGiam == null) {
                    tongTienSauKhiGiam = tongTien;
                }

                HoaDonResponse response
                        = HoaDonResponse.builder()
                                .id(rs.getInt(1))
                                .maHoaDon(rs.getString(2))
                                .ngayTao(rs.getString(3))
                                .ngayCapNhap(rs.getString(4))
                                .tongTien(tongTienSauKhiGiam)
                                .maNhanVien(rs.getString(7))
                                .hoTen(rs.getString(8))
                                .diaChi(rs.getString(9))
                                .SDT(rs.getString(10))
                                .trangThai(rs.getInt(11))
                                .hinhThucTT(rs.getInt(12))
                                .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ArrayList<HoaDonResponse> trangThaiHoaDon(Integer trangThai) {
        String sql = "SELECT \n"
                + "    dbo.HoaDon.id, \n"
                + "    dbo.HoaDon.ma_hoa_don, \n"
                + "    dbo.HoaDon.ngay_tao, \n"
                + "    dbo.HoaDon.ngay_cap_nhat, \n"
                + "    dbo.HoaDon.tong_tien, \n"
                + "    dbo.NhanVien.ma_nhan_vien, \n"
                + "    dbo.KhachHang.ho_ten, \n"
                + "    dbo.KhachHang.dia_chi, \n"
                + "    dbo.KhachHang.so_dien_thoai, \n"
                + "    dbo.HoaDon.trang_thai, \n"
                + "    dbo.HoaDon.hinh_thuc_thanh_toan\n"
                + "FROM \n"
                + "    dbo.HoaDon\n"
                + "INNER JOIN \n"
                + "    dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id\n"
                + "INNER JOIN \n"
                + "    dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id\n"
                + "WHERE 1=1"; // Start with a dummy condition that will always be true

        // Append condition for trangThai if it is not "All"
        if (trangThai != null) {
            sql += " AND dbo.HoaDon.trang_thai = ?";
        }

        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (trangThai != null) {
                ps.setInt(1, trangThai);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonResponse response = HoaDonResponse.builder()
                            .id(rs.getInt(1))
                            .maHoaDon(rs.getString(2))
                            .ngayTao(rs.getString(3))
                            .ngayCapNhap(rs.getString(4))
                            .tongTien(rs.getDouble(5))
                            .maNhanVien(rs.getString(6))
                            .hoTen(rs.getString(7))
                            .diaChi(rs.getString(8))
                            .SDT(rs.getString(9))
                            .trangThai(rs.getInt(10))
                            .hinhThucTT(rs.getInt(11))
                            .build();
                    lists.add(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public ArrayList<HoaDonResponse> hinhThucThanhToan(Integer httt) {
        // Base SQL query with condition to filter by payment method
        String sql = "SELECT \n"
                + "    dbo.HoaDon.id, \n"
                + "    dbo.HoaDon.ma_hoa_don, \n"
                + "    dbo.HoaDon.ngay_tao, \n"
                + "    dbo.HoaDon.ngay_cap_nhat, \n"
                + "    dbo.HoaDon.tong_tien, \n"
                + "    dbo.NhanVien.ma_nhan_vien, \n"
                + "    dbo.KhachHang.ho_ten, \n"
                + "    dbo.KhachHang.dia_chi, \n"
                + "    dbo.KhachHang.so_dien_thoai, \n"
                + "    dbo.HoaDon.trang_thai, \n"
                + "    dbo.HoaDon.hinh_thuc_thanh_toan\n"
                + "FROM \n"
                + "    dbo.HoaDon\n"
                + "INNER JOIN \n"
                + "    dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id\n"
                + "INNER JOIN \n"
                + "    dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id\n"
                + "WHERE 1=1"; // Start with a dummy condition that will always be true

        // Append condition for hinhThucThanhToan if it is not null
        if (httt != null) {
            sql += " AND dbo.HoaDon.hinh_thuc_thanh_toan = ?";
        }

        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Set the parameter for the SQL query if hinhThucThanhToan is provided
            if (httt != null) {
                ps.setInt(1, httt);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonResponse response = HoaDonResponse.builder()
                            .id(rs.getInt(1))
                            .maHoaDon(rs.getString(2))
                            .ngayTao(rs.getString(3))
                            .ngayCapNhap(rs.getString(4))
                            .tongTien(rs.getDouble(5))
                            .maNhanVien(rs.getString(6))
                            .hoTen(rs.getString(7))
                            .diaChi(rs.getString(8))
                            .SDT(rs.getString(9))
                            .trangThai(rs.getInt(10))
                            .hinhThucTT(rs.getInt(11))
                            .build();
                    lists.add(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public ArrayList<HoaDonResponse> search(String maHoaDon) {
        String sql = "SELECT dbo.HoaDon.id, dbo.HoaDon.ma_hoa_don, dbo.HoaDon.ngay_tao, dbo.HoaDon.ngay_cap_nhat, dbo.HoaDon.tong_tien, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ho_ten, dbo.KhachHang.dia_chi, dbo.KhachHang.so_dien_thoai, dbo.HoaDon.trang_thai, \n"
                + "dbo.HoaDon.hinh_thuc_thanh_toan\n"
                + "FROM dbo.HoaDon INNER JOIN\n"
                + "dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id INNER JOIN\n"
                + "dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id \n"
                + "WHERE dbo.HoaDon.ma_hoa_don LIKE ? \n"
                + "OR dbo.HoaDon.ngay_tao LIKE ? \n"
                + "OR dbo.HoaDon.ngay_cap_nhat LIKE ? \n"
                + "OR dbo.HoaDon.tong_tien LIKE ? \n"
                + "OR dbo.NhanVien.ma_nhan_vien LIKE ? \n"
                + "OR dbo.KhachHang.ho_ten LIKE ? \n"
                + "OR dbo.KhachHang.dia_chi LIKE ? \n"
                + "OR dbo.KhachHang.so_dien_thoai LIKE ? \n"
                + "OR dbo.HoaDon.trang_thai LIKE ? \n"
                + "OR dbo.HoaDon.hinh_thuc_thanh_toan LIKE ?";

        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String searchString = "%" + maHoaDon + "%";
            for (int i = 1; i <= 10; i++) {
                ps.setString(i, searchString);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new HoaDonResponse(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // Xử lý lỗi khi xảy ra
        }
        return lists;
    }

    public HoaDonResponse timKiemHoaDonResponsebyQR(String maHoaDon) {
        String sql = "SELECT dbo.HoaDon.id, "
                + "dbo.HoaDon.ma_hoa_don, "
                + "dbo.HoaDon.ngay_tao, "
                + "dbo.HoaDon.ngay_cap_nhat, "
                + "dbo.HoaDon.tong_tien, "
                + "dbo.NhanVien.ma_nhan_vien, "
                + "dbo.KhachHang.ho_ten, "
                + "dbo.KhachHang.dia_chi, "
                + "dbo.KhachHang.so_dien_thoai, "
                + "dbo.HoaDon.trang_thai, "
                + "dbo.HoaDon.hinh_thuc_thanh_toan "
                + "FROM dbo.HoaDon "
                + "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id "
                + "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id "
                + "WHERE dbo.HoaDon.ma_hoa_don = ?";

        HoaDonResponse hoaDonResponse = null;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Set search parameter
            ps.setString(1, maHoaDon);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hoaDonResponse = new HoaDonResponse(
                            rs.getInt("id"),
                            rs.getString("ma_hoa_don"),
                            rs.getString("ngay_tao"),
                            rs.getString("ngay_cap_nhat"),
                            rs.getDouble("tong_tien"),
                            rs.getString("ma_nhan_vien"),
                            rs.getString("ho_ten"),
                            rs.getString("dia_chi"),
                            rs.getString("so_dien_thoai"),
                            rs.getInt("trang_thai"),
                            rs.getInt("hinh_thuc_thanh_toan")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out); // Handle SQL exceptions
        }

        return hoaDonResponse;
    }

    public ArrayList<HoaDonResponse> timKiemTheoGia(Double giaMin, Double giaMax) {
        String sql = "SELECT "
                + "dbo.HoaDon.id, "
                + "dbo.HoaDon.ma_hoa_don, "
                + "dbo.HoaDon.ngay_tao, "
                + "dbo.HoaDon.ngay_cap_nhat, "
                + "dbo.HoaDon.tong_tien, "
                + "dbo.NhanVien.ma_nhan_vien, "
                + "dbo.KhachHang.ho_ten, "
                + "dbo.KhachHang.dia_chi, "
                + "dbo.KhachHang.so_dien_thoai, "
                + "dbo.HoaDon.trang_thai, "
                + "dbo.HoaDon.hinh_thuc_thanh_toan "
                + "FROM dbo.HoaDon "
                + "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id "
                + "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id "
                + "WHERE dbo.HoaDon.tong_tien BETWEEN ? AND ?"; // Filter by price range

        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Set mandatory parameters
            ps.setDouble(1, giaMin != null ? giaMin : 0.0); // Set a default value or handle differently if needed
            ps.setDouble(2, giaMax != null ? giaMax : Double.MAX_VALUE); // Set a default value or handle differently if needed

            // Execute query and process results
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonResponse response = new HoaDonResponse();
                    response.setId(rs.getInt("id"));
                    response.setMaHoaDon(rs.getString("ma_hoa_don"));
                    response.setNgayTao(rs.getString("ngay_tao"));
                    response.setNgayCapNhap(rs.getString("ngay_cap_nhat"));
                    response.setTongTien(rs.getDouble("tong_tien"));
                    response.setMaNhanVien(rs.getString("ma_nhan_vien"));
                    response.setHoTen(rs.getString("ho_ten"));
                    response.setDiaChi(rs.getString("dia_chi"));
                    response.setSDT(rs.getString("so_dien_thoai"));
                    response.setTrangThai(rs.getInt("trang_thai"));
                    response.setHinhThucTT(rs.getInt("hinh_thuc_thanh_toan"));
                    lists.add(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle SQL exception
            // Optionally, log or rethrow the exception for better error handling
        }
        return lists;
    }

    public boolean add(HoaDon hoaDon) {

        int check = 0;

        String sql = "INSERT INTO HoaDon\n"
                + "   (id_khach_hang, id_nhan_vien, ngay_tao, trang_thai,tong_tien)\n"
                + "    VALUES(?,?,?,?,?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, hoaDon.getKhachHangID());
            ps.setObject(2, hoaDon.getNhanVienID()); // Nhan vien lay tu login
            ps.setObject(3, new Date());
            ps.setObject(4, 0);
            ps.setObject(5, 0);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public ArrayList<HoaDonResponse1> getAllByStatus() {
        String sql = "SELECT \n"
                + "    hd.id,\n"
                + "    hd.id_khach_hang,\n"
                + "    hd.id_nhan_vien,\n"
                + "    hd.trang_thai,\n"
                + "    hd.tong_tien,\n"
                + "    hd.ma_hoa_don,\n"
                + "    kh.ho_ten,\n"
                + "    nv.ma_nhan_vien,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    hd.ngay_tao,\n"
                + "	kh.ma_khach_hang\n"
                + "FROM HoaDon hd\n"
                + "JOIN KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "JOIN NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "WHERE hd.trang_thai = 0;";
        ArrayList<HoaDonResponse1> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonResponse1 response
                        = HoaDonResponse1.builder()
                                .id(rs.getInt(1))
                                .idKH(rs.getInt(2))
                                .idNV(rs.getInt(3))
                                .trangThai(rs.getInt(4))
                                .tongTien(rs.getDouble(5))
                                .maHoaDon(rs.getString(6))
                                .tenKhachHang(rs.getString(7))
                                .maNhanVien(rs.getString(8))
                                .hoTen(rs.getString(9))
                                .ngayTao(rs.getString(10))
                                .maKH(rs.getString(11))
                                .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public boolean updateTongTien(Double tongTien, Integer id) {
        int check = 0;
        String sql = "UPDATE HoaDon SET tong_tien=? WHERE id=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Log các giá trị để kiểm tra
            System.out.println("Updating HoaDon with id: " + id + " and tong_tien: " + tongTien);

            ps.setObject(1, tongTien);
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Update result: " + check); // Kiểm tra kết quả trả về
        return check > 0;
    }

    public boolean updateThongTin(HoaDonResponse1 response) {
        int check = 0;

        // SQL statement to update trang_thai, hinh_thuc_thanh_toan, tong_tien and tien_giam
        String sql = "UPDATE HoaDon SET trang_thai = 1, hinh_thuc_thanh_toan = ?, tong_tien = ?, tong_tien_sau_khi_giam = ?, id_voucher=? WHERE id = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, response.getHinhThucThanhToan()); // Set hinh_thuc_thanh_toan

            // Set tong_tien (total amount after discount)
            ps.setDouble(2, response.getTongTien());

            // Set tong_tien_sau_khi_giam (after discount), check for null
            if (response.getTienGiam() != null) {
                ps.setDouble(3, response.getTienGiam());
            } else {
                ps.setNull(3, java.sql.Types.DOUBLE);
            }

            // Set id_voucher, check for null
            if (response.getId_vouCher() != null) {
                ps.setInt(4, response.getId_vouCher());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            ps.setInt(5, response.getId());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public boolean huyHoaDon(HoaDonResponse1 hoaDon) {
        int check = 0;

        String sql1 = "UPDATE spct "
                + "SET spct.so_luong_ton = spct.so_luong_ton + quantities.total_quantity "
                + "FROM SanPhamChiTiet spct "
                + "JOIN ( "
                + "    SELECT hdct.id_san_pham_chi_tiet, SUM(hdct.so_luong) as total_quantity "
                + "    FROM HoaDonChiTiet hdct "
                + "    WHERE hdct.id_hoa_don = ? "
                + "    GROUP BY hdct.id_san_pham_chi_tiet "
                + ") AS quantities ON spct.id = quantities.id_san_pham_chi_tiet;";

        String sql2 = "UPDATE HoaDonChiTiet "
                + "SET so_luong = 0 "
                + "WHERE id_hoa_don = ?;";

        String sql3 = "UPDATE HoaDon "
                + "SET trang_thai = 2 "
                + "WHERE id = ?;";

        // Query to get all relevant product IDs for the given HoaDon
        String sqlGetProductIds = "SELECT id_san_pham_chi_tiet "
                + "FROM HoaDonChiTiet "
                + "WHERE id_hoa_don = ?;";

        // The new SQL query with the condition
        String sql4 = "UPDATE SanPhamChiTiet "
                + "SET trang_thai_ban = 1 "
                + "WHERE id = ? AND trang_thai_ban = 0;";

        try (Connection con = DBConnect.getConnection()) {
            con.setAutoCommit(false); // Begin transaction

            // Update SanPhamChiTiet quantities
            try (PreparedStatement ps1 = con.prepareStatement(sql1)) {
                ps1.setObject(1, hoaDon.getId());
                check += ps1.executeUpdate();
            }

            // Update HoaDonChiTiet
            try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                ps2.setObject(1, hoaDon.getId());
                check += ps2.executeUpdate();
            }

            // Update HoaDon
            try (PreparedStatement ps3 = con.prepareStatement(sql3)) {
                ps3.setObject(1, hoaDon.getId());
                check += ps3.executeUpdate();
            }

            // Get all product IDs associated with the HoaDon
            try (PreparedStatement psGetIds = con.prepareStatement(sqlGetProductIds)) {
                psGetIds.setObject(1, hoaDon.getId());
                try (ResultSet rs = psGetIds.executeQuery()) {
                    while (rs.next()) {
                        int productId = rs.getInt("id_san_pham_chi_tiet");

                        // Conditionally update SanPhamChiTiet's trang_thai_ban for each product
                        try (PreparedStatement ps4 = con.prepareStatement(sql4)) {
                            ps4.setInt(1, productId);
                            check += ps4.executeUpdate();
                        }
                    }
                }
            }

            con.commit(); // Commit transaction if all updates succeed
        } catch (Exception e) {
            e.printStackTrace(System.out);
            try (Connection con = DBConnect.getConnection()) {
                con.rollback(); // Rollback transaction in case of failure
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }

        return check > 0;
    }

    public boolean listPhieuGiamGia(double giaTri) {
        String sql = "select ma_voucher from Voucher where trang_thai = 0 and gia_tri_don_hang_toi_thieu < ? "
                + "order by phan_tram_giam_gia desc";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, giaTri);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có ít nhất một kết quả trả về, sẽ trả về true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có bất kỳ lỗi nào xảy ra hoặc không có kết quả nào thỏa mãn
    }

    public Integer getIDByHoaDon(String ma) {
        String query = "select id from HoaDon where ma_hoa_don = ?";
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

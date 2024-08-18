/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import com.Product.form.GiamGiaForm;
import main.config.DBConnect;
import main.entity.PhieuGiamGia1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class PhieuGiamGiaRepository {

    public ArrayList<PhieuGiamGia1> getAll() {
        String sql = "select id, ma_voucher, ngay_bat_dau,ngay_het_han,ten_giam_gia,phan_tram_giam_gia,gia_tri_don_hang_toi_thieu,so_lan_su_dung_toi_da,trang_thai,mo_ta \n"
                + "from Voucher";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)
            ArrayList<PhieuGiamGia1> list = new ArrayList<>();
            while (rs.next()) {
                PhieuGiamGia1 PGG = new PhieuGiamGia1(rs.getInt("id"), rs.getString("ma_voucher"), rs.getDate("ngay_bat_dau"), rs.getDate("ngay_het_han"), rs.getString("ten_giam_gia"), rs.getDouble("phan_tram_giam_gia"), rs.getDouble("gia_tri_don_hang_toi_thieu"), rs.getInt("so_lan_su_dung_toi_da"), rs.getInt("trang_thai"), rs.getString("mo_ta"));
                list.add(PGG);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public PhieuGiamGia1 getAllByID(int id) {
        String sql = "SELECT id, ma_voucher, ngay_bat_dau, ngay_het_han, ten_giam_gia, phan_tram_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, trang_thai, mo_ta "
                + "FROM Voucher WHERE id = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập giá trị cho tham số id
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PhieuGiamGia1(
                        rs.getInt("id"),
                        rs.getString("ma_voucher"),
                        rs.getDate("ngay_bat_dau"),
                        rs.getDate("ngay_het_han"),
                        rs.getString("ten_giam_gia"),
                        rs.getDouble("phan_tram_giam_gia"),
                        rs.getDouble("gia_tri_don_hang_toi_thieu"),
                        rs.getInt("so_lan_su_dung_toi_da"),
                        rs.getInt("trang_thai"),
                        rs.getString("mo_ta")
                );
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public void update(PhieuGiamGia1 voucher) {
        String sql = "UPDATE Voucher SET so_lan_su_dung_toi_da = ?, trang_thai = ? WHERE id = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh cập nhật
            ps.setInt(1, voucher.getSo_Lan_Su_Dung_Toi_Da());
            ps.setInt(2, voucher.getTrang_Thai());
            ps.setInt(3, voucher.getID());

            // Thực hiện cập nhật
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public boolean add(PhieuGiamGia1 phieu) {
        int check = 0;
        String sql = "INSERT INTO Voucher (ngay_bat_dau, ngay_het_han, ten_giam_gia, phan_tram_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, mo_ta) "
                + "VALUES (?,?,?,?,?,?,?);";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Đặt giá trị cho các tham số
            ps.setObject(1, phieu.getNgay_Bat_Dau());  // Ngày bắt đầu
            ps.setObject(2, phieu.getNgay_Het_Han());  // Ngày hết hạn
            ps.setObject(3, phieu.getTen_Giam_Gia());  // Tên giảm giá
            ps.setObject(4, phieu.getPhan_Tram_Giam_Gia());  // Phần trăm giảm giá
            ps.setObject(5, phieu.getGia_Tri_Don_Hang_Toi_Thieu());  // Giá trị đơn hàng tối thiểu
            ps.setObject(6, phieu.getSo_Lan_Su_Dung_Toi_Da());  // Số lần sử dụng tối đa
            ps.setObject(7, phieu.getMo_ta());  // Mô tả

            // Thực thi câu lệnh SQL
            check = ps.executeUpdate();

            if (check > 0) {
                // Lấy ID của bản ghi vừa thêm
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int newId = rs.getInt(1);
                        // Xử lý ID mới nếu cần
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi nếu có lỗi xảy ra
        }
        return check > 0;  // Trả về true nếu câu lệnh SQL thành công
    }

    public boolean update(PhieuGiamGia1 phieu, Integer id) {
        int check = 0;
        String sql = "UPDATE Voucher SET "
                + "ngay_bat_dau = ?, "
                + "ngay_het_han = ?, "
                + "ten_giam_gia = ?, "
                + "phan_tram_giam_gia = ?, "
                + "gia_tri_don_hang_toi_thieu = ?, "
                + "so_lan_su_dung_toi_da = ?, "
                + "trang_thai = ?, "
                + "mo_ta = ? "
                + "WHERE id = ?;";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Đặt giá trị cho các tham số
            ps.setObject(1, phieu.getNgay_Bat_Dau());  // Ngày bắt đầu
            ps.setObject(2, phieu.getNgay_Het_Han());  // Ngày hết hạn
            ps.setObject(3, phieu.getTen_Giam_Gia());  // Tên giảm giá
            ps.setObject(4, phieu.getPhan_Tram_Giam_Gia());  // Phần trăm giảm giá
            ps.setObject(5, phieu.getGia_Tri_Don_Hang_Toi_Thieu());  // Giá trị đơn hàng tối thiểu
            ps.setObject(6, phieu.getSo_Lan_Su_Dung_Toi_Da());  // Số lần sử dụng tối đa
            ps.setObject(7, phieu.getTrang_Thai());  // Trạng thái (nếu có)
            ps.setObject(8, phieu.getMo_ta());  // Mô tả
            ps.setObject(9, id);  // ID để xác định bản ghi cần cập nhật

            // Thực thi câu lệnh SQL
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi nếu có lỗi xảy ra
        }
        return check > 0;  // Trả về true nếu câu lệnh SQL thành công
    }

    private void loadVouchers() {
        ArrayList<PhieuGiamGia1> vouchers = new ArrayList<>();
        String sql = "SELECT id, ngay_bat_dau, ngay_het_han, ten_giam_gia, gia_tri_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, mo_ta "
                + "FROM Voucher ORDER BY id DESC"; // Sắp xếp theo id giảm dần để mục mới nhất lên đầu
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ngay_bat_dau = rs.getString("ngay_bat_dau");
                String ngay_het_han = rs.getString("ngay_het_han");
                String ten_giam_gia = rs.getString("ten_giam_gia");
                double gia_tri_giam_gia = rs.getDouble("gia_tri_giam_gia");
                double gia_tri_don_hang_toi_thieu = rs.getDouble("gia_tri_don_hang_toi_thieu");
                int so_lan_su_dung_toi_da = rs.getInt("so_lan_su_dung_toi_da");
                String mo_ta = rs.getString("mo_ta");

                PhieuGiamGia1 voucher = new PhieuGiamGia1();
                vouchers.add(voucher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<PhieuGiamGia1> Search(String searchCriteria) {
        ArrayList<PhieuGiamGia1> lists = new ArrayList<>();
        String sql = "SELECT id, ma_voucher, ngay_bat_dau, ngay_het_han, ten_giam_gia, phan_tram_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, trang_thai, mo_ta "
                + "FROM Voucher "
                + "WHERE ma_voucher LIKE ? "
                + "OR ngay_bat_dau LIKE ? "
                + "OR ngay_het_han LIKE ? "
                + "OR ten_giam_gia LIKE ? "
                + "OR phan_tram_giam_gia LIKE ? "
                + "OR gia_tri_don_hang_toi_thieu LIKE ? "
                + "OR so_lan_su_dung_toi_da LIKE ? "
                + "OR trang_thai LIKE ? "
                + "OR mo_ta LIKE ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String searchString = "%" + searchCriteria + "%"; // Tạo chuỗi tìm kiếm với %

            // Đặt giá trị cho từng tham số trong câu truy vấn SQL
            for (int i = 1; i <= 9; i++) {
                ps.setString(i, searchString);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String maVoucher = rs.getString("ma_voucher");
                Date ngayBatDau = rs.getDate("ngay_bat_dau");
                Date ngayHetHan = rs.getDate("ngay_het_han");
                String tenGiamGia = rs.getString("ten_giam_gia");
                double phanTramGiamGia = rs.getDouble("phan_tram_giam_gia");
                double giaTriDonHangToiThieu = rs.getDouble("gia_tri_don_hang_toi_thieu");
                int soLanSuDungToiDa = rs.getInt("so_lan_su_dung_toi_da");
                int trangThai = rs.getInt("trang_thai");
                String moTa = rs.getString("mo_ta");

                // Tạo đối tượng PhieuGiamGia1 từ dữ liệu trong ResultSet
                PhieuGiamGia1 voucher = new PhieuGiamGia1(id, maVoucher, ngayBatDau, ngayHetHan, tenGiamGia,
                        phanTramGiamGia, giaTriDonHangToiThieu, soLanSuDungToiDa,
                        trangThai, moTa);
                lists.add(voucher);
            }
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return lists;
    }

    public boolean remove(Integer id) {
        int check = 0;
        String sql = "UPDATE [dbo].[Voucher] "
                + "SET [trang_thai] = 1 " // Thay đổi trạng thái thành 1 (Ngừng Hoạt Động)
                + "WHERE [id] = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id); // Sử dụng setString nếu 'id' là kiểu String
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public Double getGiaTriGiam(String ma) {
        String sql = "select phan_tram_giam_gia from Voucher where ma_voucher = ?";
        double phanTram = 0.00;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();

            // Kiểm tra nếu có kết quả từ truy vấn
            if (rs.next()) {
                // Lấy giá trị phần trăm giảm giá từ ResultSet
                phanTram = rs.getDouble("phan_tram_giam_gia");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanTram;
    }

    public Double getGiaTriDonHangToiThieu(String ma) {
        String sql = "select gia_tri_don_hang_toi_thieu from Voucher where ma_voucher =?";
        double phanTram = 0.00;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                phanTram = rs.getDouble("gia_tri_don_hang_toi_thieu");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanTram;
    }

    public ArrayList<PhieuGiamGia1> getAllDangHoatHoatDong() {
        String sql = "SELECT id, \n"
                + "       ma_voucher, \n"
                + "       ngay_bat_dau, \n"
                + "       ngay_het_han, \n"
                + "       ten_giam_gia, \n"
                + "       phan_tram_giam_gia, \n"
                + "       gia_tri_don_hang_toi_thieu, \n"
                + "       so_lan_su_dung_toi_da, \n"
                + "       trang_thai, \n"
                + "       mo_ta \n"
                + "FROM Voucher  \n"
                + "WHERE trang_thai = 0 \n"
                + "ORDER BY ngay_tao DESC;";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)
            ArrayList<PhieuGiamGia1> list = new ArrayList<>();
            while (rs.next()) {
                PhieuGiamGia1 PGG = new PhieuGiamGia1(rs.getInt("id"), rs.getString("ma_voucher"), rs.getDate("ngay_bat_dau"), rs.getDate("ngay_het_han"), rs.getString("ten_giam_gia"), rs.getDouble("phan_tram_giam_gia"), rs.getDouble("gia_tri_don_hang_toi_thieu"), rs.getInt("so_lan_su_dung_toi_da"), rs.getInt("trang_thai"), rs.getString("mo_ta"));
                list.add(PGG);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Double getGiamNhieuHon(double tongTien) {
        String sql = "SELECT gia_tri_don_hang_toi_thieu FROM Voucher WHERE gia_tri_don_hang_toi_thieu > ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Thiết lập tham số
            ps.setDouble(1, tongTien);

            ResultSet rs = ps.executeQuery();

            // Nếu có kết quả, lấy giá trị
            if (rs.next()) {
                return rs.getDouble("gia_tri_don_hang_toi_thieu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Trả về null nếu không có kết quả nào
        return null;
    }

    public ArrayList<PhieuGiamGia1> getAllGiaTriTotNhat() {
        String sql = "SELECT id, \n"
                + "       ma_voucher, \n"
                + "       ngay_bat_dau, \n"
                + "       ngay_het_han, \n"
                + "       ten_giam_gia, \n"
                + "       phan_tram_giam_gia, \n"
                + "       gia_tri_don_hang_toi_thieu, \n"
                + "       so_lan_su_dung_toi_da, \n"
                + "       trang_thai, \n"
                + "       mo_ta \n"
                + "FROM Voucher  \n"
                + "WHERE trang_thai = 0 \n"
                + "ORDER BY phan_tram_giam_gia DESC";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)
            ArrayList<PhieuGiamGia1> list = new ArrayList<>();
            while (rs.next()) {
                PhieuGiamGia1 PGG = new PhieuGiamGia1(rs.getInt("id"), rs.getString("ma_voucher"), rs.getDate("ngay_bat_dau"), rs.getDate("ngay_het_han"), rs.getString("ten_giam_gia"), rs.getDouble("phan_tram_giam_gia"), rs.getDouble("gia_tri_don_hang_toi_thieu"), rs.getInt("so_lan_su_dung_toi_da"), rs.getInt("trang_thai"), rs.getString("mo_ta"));
                list.add(PGG);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Integer getIDByMaPhieuGiamGia(String ma) {
        String query = "SELECT id FROM Voucher WHERE ma_voucher = ?";
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

    public ArrayList<PhieuGiamGia1> timkiemTrangThai(String maVoucher, String tenGiamGia, String moTa, Integer trangThai) {
        ArrayList<PhieuGiamGia1> lists = new ArrayList<>();
        String sql = "SELECT id, ma_voucher, ngay_bat_dau, ngay_het_han, ten_giam_gia, phan_tram_giam_gia, gia_tri_don_hang_toi_thieu, so_lan_su_dung_toi_da, trang_thai, mo_ta "
                + "FROM [dbo].[Voucher] "
                + "WHERE ma_voucher LIKE ? "
                + "AND ten_giam_gia LIKE ? "
                + "AND mo_ta LIKE ? ";

        // Chỉ thêm điều kiện trang_thai nếu nó không phải là null
        if (trangThai != null) {
            sql += "AND trang_thai = ?";
        }

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Đặt giá trị cho từng tham số trong câu truy vấn SQL
            ps.setString(1, "%" + maVoucher + "%");
            ps.setString(2, "%" + tenGiamGia + "%");
            ps.setString(3, "%" + moTa + "%");

            // Đặt giá trị cho tham số trạng thái nếu có
            if (trangThai != null) {
                ps.setInt(4, trangThai);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhieuGiamGia1 response = new PhieuGiamGia1(
                        rs.getInt("id"),
                        rs.getString("ma_voucher"),
                        rs.getDate("ngay_bat_dau"),
                        rs.getDate("ngay_het_han"),
                        rs.getString("ten_giam_gia"),
                        rs.getDouble("phan_tram_giam_gia"),
                        rs.getDouble("gia_tri_don_hang_toi_thieu"),
                        rs.getInt("so_lan_su_dung_toi_da"),
                        rs.getInt("trang_thai"),
                        rs.getString("mo_ta")
                );
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace(); // In ra stack trace để xem chi tiết lỗi
        }
        return null;
    }
}

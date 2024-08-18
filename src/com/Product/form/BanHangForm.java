package com.Product.form;

import Jframe.ThongTinKhachHangJFrame;
import com.Product.main.Main;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.management.Notification;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import main.config.DBConnect;
import main.entity.HoaDon;
import main.entity.KhachHang;
import main.entity.KichThuoc;
import main.entity.PhieuGiamGia1;
import main.entity.PhieuGiamGia1;
import main.entity.ThuongHieu;
import main.entity.TinhLinhHoat;
import main.entity.XuatXu;
import main.repository.ChatLieuRepository;
import main.repository.HoaDonChiTietRepository;
import main.repository.HoaDonRepository;
import main.repository.KichThuocRepository;
import main.repository.PhieuGiamGiaRepository;
import main.repository.SanPhamChiTietRepository;
import main.repository.ThongTinKhachHangRepository;
import main.repository.ThuongHieuRepository;
import main.repository.TinhLinhHoatRepository;
import main.repository.XuatXuRepository;
import main.response.HoaDonChiTietReponse;
import main.response.HoaDonResponse;
import main.response.HoaDonResponse1;
import main.response.SanPhamChiTietRespone;

public class BanHangForm extends javax.swing.JPanel {

    private DefaultComboBoxModel dcbmPhongCach;
    private TinhLinhHoatRepository phongCachRepo = new TinhLinhHoatRepository();

    private DefaultComboBoxModel dcbmXuatXu;
    private XuatXuRepository xuatXuRepo = new XuatXuRepository();

    private DefaultComboBoxModel dcbmKichThuoc;
    private KichThuocRepository kichThuocRepo = new KichThuocRepository();

    private SanPhamChiTietRepository sanPhamChiTietRepository;

    private DefaultTableModel dtmSanPham;

    private HoaDonRepository hoaDonRepository;

    private DefaultTableModel dtmHoaDonChiTiet;

    private DefaultComboBoxModel dcbmGiamGia;

    private DefaultTableModel dtmHoaDon;

    private HoaDonChiTietRepository hoaDonChiTietRepository;

    private ThongTinKhachHangRepository thongTinKhachHangRepo = new ThongTinKhachHangRepository();

    private PhieuGiamGiaRepository giamGiaRepository = new PhieuGiamGiaRepository();

    private Integer indexHoaDonSelected;

    private Integer id_kh;

    private String maSPCT = Menu.maSPCTBanHang;

    Double tongTien;
    Double tongTienSauKhiGiam;
    private Integer id_voucher;

    private String tenPhongCach = "%";
    private String tenKichThuoc = "%";
    private String tenXuatXu = "%";
    private boolean tenGiaBan = false;
    private String maGiamGia;

    public BanHangForm() {
        initComponents();
        setOpaque(false);

        sanPhamChiTietRepository = new SanPhamChiTietRepository();

        hoaDonRepository = new HoaDonRepository();

        hoaDonChiTietRepository = new HoaDonChiTietRepository();

        dtmSanPham = (DefaultTableModel) tb_SP.getModel();

        dtmHoaDon = (DefaultTableModel) tb_hoaDon.getModel();

        dtmHoaDonChiTiet = (DefaultTableModel) tb_hoaDonChiTiet.getModel();

        showTableHoaDon(hoaDonRepository.getAllByStatus());

        indexHoaDonSelected = tb_hoaDon.getSelectedRow();

        showComboboxLocPhongCach(phongCachRepo.getAll());
        cbb_phongCach.setSelectedIndex(-1);
        showComboboxLocKichThuoc(kichThuocRepo.getAll());
        cbb_kichThuoc.setSelectedIndex(-1);
        showComboboxLocXuatXu(xuatXuRepo.getAll());
        cbb_xuatXu.setSelectedIndex(-1);
        showComboboxLocGia();
        cbb_gia.setSelectedIndex(-1);
        showTableSanPham(sanPhamChiTietRepository.getAll());
    }

    private void showComboboxLocGia() {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_gia.getModel();
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement("Giảm dần");
        comboBoxModel.addElement("Tăng dần");
    }

    private void showComboboxLocPhongCach(ArrayList<TinhLinhHoat> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_phongCach.getModel();
        comboBoxModel.removeAllElements();
        for (TinhLinhHoat cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showComboboxLocXuatXu(ArrayList<XuatXu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_xuatXu.getModel();
        comboBoxModel.removeAllElements();
        for (XuatXu cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showComboboxLocKichThuoc(ArrayList<KichThuoc> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_kichThuoc.getModel();
        comboBoxModel.removeAllElements();
        for (KichThuoc cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showTableSanPham(ArrayList<SanPhamChiTietRespone> lists) {
        dtmSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPham.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaSPCT(),
            s.getTenSP(),
            s.getPhongCach(),
            s.getXuatXu(),
            s.getKichThuoc(),
            s.getSoLuong(),
            String.format("%,.3f₫", s.getGiaBan()) // Định dạng giá bán
        }));
    }

    private void showTableHoaDon(ArrayList<HoaDonResponse1> lists) {
        dtmHoaDon.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmHoaDon.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaHoaDon(),
            s.getNgayTao(),
            s.getMaNhanVien(),
            String.format("%,.3f₫", s.getTongTien()),
            s.getTrangThai() == 0 ? "Chưa thanh toán" : "Đã Thanh Toán",
            s.getTienGiam(), s.getId_vouCher()
        }));
    }

    private void showComboboxGiamGia(ArrayList<PhieuGiamGia1> list) {
        cbo_PhieuGiamGia.removeAllItems();
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbo_PhieuGiamGia.getModel();
        for (PhieuGiamGia1 cl : list) {
            comboBoxModel.addElement(cl.getMa_Voucher());
        }
    }

    private void showTableHoaDonChiTiet(ArrayList<HoaDonChiTietReponse> lists) {
        dtmHoaDonChiTiet.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);

        // Map để lưu các sản phẩm đã được cộng dồn
        Map<String, HoaDonChiTietReponse> sanPhamMap = new HashMap<>();

        // Duyệt qua từng sản phẩm trong danh sách và cộng dồn số lượng nếu mã sản phẩm đã tồn tại
        for (HoaDonChiTietReponse s : lists) {
            String maSPCT = s.getMaSPCT();

            if (sanPhamMap.containsKey(maSPCT)) {
                // Cộng dồn số lượng và thành tiền nếu sản phẩm đã tồn tại trong Map
                HoaDonChiTietReponse existingSP = sanPhamMap.get(maSPCT);
                existingSP.setSoLuong(existingSP.getSoLuong() + s.getSoLuong());
                existingSP.setThanhTien(existingSP.getThanhTien() + s.getThanhTien());
            } else {
                // Nếu sản phẩm chưa tồn tại, thêm sản phẩm vào Map
                sanPhamMap.put(maSPCT, s);
            }
        }

        // Hiển thị các sản phẩm đã được cộng dồn vào bảng
        sanPhamMap.values().forEach(s -> dtmHoaDonChiTiet.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaSPCT(),
            s.getThuongHieu(),
            s.getMauSac(),
            s.getCoAo(),
            s.getKichThuoc(),
            String.format("%,.3f₫", s.getGiaBan()),
            s.getSoLuong(),
            String.format("%,.3f₫", s.getThanhTien())
        }));
    }

    private void calculateChange() {
        try {
            // Parse tổng tiền và tiền khách đưa, tiền khách CK
            String totalAmountStr = txt_tongTien1.getText().replace("₫", "").trim().replace(",", "").replace(".", "");
            String amountGivenStr = txt_tienKhachDua.getText().trim().replace(",", "").replace(".", "");
            String amountCKStr = txt_tienKhachCK.getText().trim().replace(",", "").replace(".", "");

            // Đảm bảo tổng tiền không bị rỗng
            if (!totalAmountStr.isEmpty()) {
                double totalAmount = Double.parseDouble(totalAmountStr);
                double amountGiven = amountGivenStr.isEmpty() ? 0 : Double.parseDouble(amountGivenStr);
                double amountCK = amountCKStr.isEmpty() ? 0 : Double.parseDouble(amountCKStr);

                // Tổng tiền khách đưa (tiền mặt + CK)
                double totalGiven = amountGiven + amountCK;

                // Chỉ tính toán khi tổng tiền khách đưa lớn hơn hoặc bằng tổng tiền
                if (totalGiven >= totalAmount) {
                    double change = totalGiven - totalAmount;
                    txt_tienThua.setText(String.format("%,.0f₫", change));
                } else {
                    txt_tienThua.setText("0₫");
                }
            }
        } catch (NumberFormatException e) {
            // Xử lý khi nhập liệu không hợp lệ
            txt_tienThua.setText("0₫");
        }
    }

    private int countHoaDon() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM HoaDon where trang_thai = 0";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_quetQR = new com.Product.swing.ButtonBadges();
        btn_TaoHoaDon = new com.Product.swing.ButtonBadges();
        btn_lamMoi = new com.Product.swing.ButtonBadges();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_hoaDon = new com.Product.GUI.Table();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txt_tenKhachHang1 = new com.Product.GUI.textfield.TextField();
        btn_timKhachHang = new com.Product.swing.ButtonBadges();
        txt_maKhachHang = new com.Product.GUI.textfield.TextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_maHoaDon = new com.Product.GUI.textfield.TextField();
        txt_tenKhachHang = new com.Product.GUI.textfield.TextField();
        txt_maNhanVien = new com.Product.GUI.textfield.TextField();
        cbb_httt = new com.Product.GUI.Combobox();
        jLabel2 = new javax.swing.JLabel();
        btn_Huy = new com.Product.swing.ButtonBadges();
        btn_ThanhToan = new com.Product.swing.ButtonBadges();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_tongTien1 = new javax.swing.JLabel();
        txt_tongTien = new javax.swing.JTextField();
        txt_tienKhachDua = new com.Product.GUI.textfield.TextField();
        txt_tienKhachCK = new com.Product.GUI.textfield.TextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbo_PhieuGiamGia = new com.Product.GUI.Combobox();
        txt_ngayTT = new javax.swing.JLabel();
        txt_tienThua = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_SP = new com.Product.GUI.Table();
        cbb_gia = new com.Product.GUI.Combobox();
        cbb_phongCach = new com.Product.GUI.Combobox();
        cbb_kichThuoc = new com.Product.GUI.Combobox();
        cbb_xuatXu = new com.Product.GUI.Combobox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_hoaDonChiTiet = new com.Product.GUI.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("Bán Hàng");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btn_quetQR.setBackground(new java.awt.Color(255, 204, 153));
        btn_quetQR.setText("Quét QR Sản Phẩm");
        btn_quetQR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_quetQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quetQRActionPerformed(evt);
            }
        });

        btn_TaoHoaDon.setBackground(new java.awt.Color(255, 204, 153));
        btn_TaoHoaDon.setText("Tạo Hóa Đơn ");
        btn_TaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_TaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoHoaDonActionPerformed(evt);
            }
        });

        btn_lamMoi.setBackground(new java.awt.Color(255, 204, 51));
        btn_lamMoi.setText("Làm Mới");
        btn_lamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });

        tb_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Nhân Viên", "Tổng Tiền", "Trạng Thái"
            }
        ));
        tb_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcl(evt);
            }
        });
        jScrollPane4.setViewportView(tb_hoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btn_quetQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 364, Short.MAX_VALUE)
                .addComponent(btn_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_quetQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 170, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        txt_tenKhachHang1.setLabelText("");

        btn_timKhachHang.setBackground(new java.awt.Color(255, 204, 204));
        btn_timKhachHang.setText("Chọn");
        btn_timKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_timKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKhachHangActionPerformed(evt);
            }
        });

        txt_maKhachHang.setLabelText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_maKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btn_timKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(txt_maKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã Hóa Đơn");

        txt_maHoaDon.setLabelText("Mã Hóa Đơn");

        txt_tenKhachHang.setLabelText("");

        txt_maNhanVien.setLabelText("");
        txt_maNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maNhanVienActionPerformed(evt);
            }
        });

        cbb_httt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chuyển khoản", "Tiền mặt", "Cả hai " }));
        cbb_httt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_htttActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Tổng");

        btn_Huy.setBackground(new java.awt.Color(255, 153, 153));
        btn_Huy.setText("Hủy");
        btn_Huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        btn_ThanhToan.setBackground(new java.awt.Color(255, 153, 153));
        btn_ThanhToan.setText("Thanh Toán");
        btn_ThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Ngày Tạo");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã Nhân Viên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên Khách Hàng");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Phiếu giảm giá");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Hình thức thanh toán");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tiền phải khách đưa");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tiền khách CK");

        txt_tongTien1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_tongTien1.setForeground(new java.awt.Color(255, 51, 0));
        txt_tongTien1.setText("0 VND");

        txt_tongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tongTienActionPerformed(evt);
            }
        });

        txt_tienKhachDua.setLabelText("");
        txt_tienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienKhachDuaActionPerformed(evt);
            }
        });

        txt_tienKhachCK.setLabelText("");
        txt_tienKhachCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienKhachCKActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tiền thừa");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("Tổng Tiền");

        cbo_PhieuGiamGia.setLabeText("--Phiếu giảm giá--");
        cbo_PhieuGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_PhieuGiamGiaActionPerformed(evt);
            }
        });

        txt_ngayTT.setBackground(new java.awt.Color(255, 204, 204));
        txt_ngayTT.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_maNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ngayTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_maHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_PhieuGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(cbb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(52, 52, 52)
                                .addComponent(txt_tienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_tienKhachCK, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_maHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4))
                    .addComponent(txt_ngayTT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_maNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_PhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tienKhachCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_tienThua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tongTien1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tb_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên SP", "Phong Cách", "Xuất Xứ", "Size", "SL Tồn", "Ðơn Giá"
            }
        ));
        tb_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_SPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tb_SPMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tb_SP);

        cbb_gia.setLabeText("Giá");
        cbb_gia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_giaItemStateChanged(evt);
            }
        });
        cbb_gia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_giaActionPerformed(evt);
            }
        });

        cbb_phongCach.setLabeText("Phong Cách");
        cbb_phongCach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_phongCachItemStateChanged(evt);
            }
        });
        cbb_phongCach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_phongCachActionPerformed(evt);
            }
        });

        cbb_kichThuoc.setLabeText("Kích Thước");
        cbb_kichThuoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_kichThuocItemStateChanged(evt);
            }
        });

        cbb_xuatXu.setLabeText("Xuất Xứ");
        cbb_xuatXu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_xuatXuItemStateChanged(evt);
            }
        });
        cbb_xuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_xuatXuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbb_phongCach, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(cbb_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cbb_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(cbb_gia, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_phongCach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 182, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tb_hoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên SP", "Màu Sắc", "Cổ Áo", "Size", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        tb_hoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_hoaDonChiTiet);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbo_PhieuGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_PhieuGiamGiaActionPerformed
        // Lấy giá trị giảm giá từ combobox
        maGiamGia = (String) cbo_PhieuGiamGia.getSelectedItem();
        System.out.println("giảm giá: " + maGiamGia);

        // Kiểm tra nếu giá trị giảm giá không null
        if (maGiamGia != null) {

            id_voucher = giamGiaRepository.getIDByMaPhieuGiamGia(maGiamGia);

            try {

                double giamGia = giamGiaRepository.getGiaTriGiam(maGiamGia);
                System.out.println("tiền giảm: " + giamGia);

                if (tongTien != null && tongTien > 0) {
                    // Tạo định dạng tiền tệ
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                    System.out.println("Tổng Tiền " + tongTien);

                    // Kiểm tra nếu tổng tiền >= giá trị đơn hàng tối thiểu để áp dụng giảm giá
                    if (tongTien >= giamGiaRepository.getGiaTriDonHangToiThieu(maGiamGia)) {

                        System.out.println("Đơn hàng tối thiểu đây này: " + giamGiaRepository.getGiaTriDonHangToiThieu(maGiamGia));
                        System.out.println("Tiền giảm: " + giamGia);

                        // Tính số tiền giảm
                        double tienGiam = tongTien * (giamGia / 100.00);
                        System.out.println("Tiền giảm 2: " + tienGiam);

                        // Tính tổng tiền sau khi giảm giá
                        double tong = tongTien - tienGiam;
                        tongTienSauKhiGiam = tong;
                        // Hiển thị tổng tiền đã giảm giá trên giao diện
                        txt_tongTien1.setText(String.format("%,.3f₫", tong));
                    } else {                        
                        System.out.println("sản phẩm không đủ điều kiện giảm!!!");
                        // Hiển thị thông báo cho người dùng
                        JOptionPane.showMessageDialog(null,
                                "Sản phẩm không đủ điều kiện dùng mã.\nVui lòng click lại hóa đơn để thanh toán lại.",
                                "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE);
                        txt_tongTien1.setText(String.format("%,.3f₫", tongTien));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("giá trị giảm giá không hợp lệ.");
                JOptionPane.showMessageDialog(null, "Giá trị giảm giá không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("giá trị giảm giá không hợp lệ.");
        }

    }//GEN-LAST:event_cbo_PhieuGiamGiaActionPerformed

    private void cbb_htttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_htttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_htttActionPerformed

    private void txt_tienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachDuaActionPerformed

    private void btn_TaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoHoaDonActionPerformed
        // Giới hạn số lượng hóa đơn tối đa
        // TODO add your handling code here:
        final int MAX_INVOICES = 10;

        // Kiểm tra số lượng hóa đơn hiện tại
        if (countHoaDon() < MAX_INVOICES) {
            // Lấy ID khách hàng đã chọn từ biến tĩnh hoặc mặc định là 1 nếu chưa chọn
            int khachHangID = ThongTinKhachHangJFrame.id_tamKH != null ? ThongTinKhachHangJFrame.id_tamKH : 1;

            // Tạo đối tượng HoaDon
            HoaDon hd = HoaDon.builder()
                    .khachHangID(khachHangID)
                    .nhanVienID(1)
                    .build();

            // Thêm hóa đơn vào cơ sở dữ liệu
            if (hoaDonRepository.add(hd)) {
                // Tải lại bảng hóa đơn
                showTableHoaDon(hoaDonRepository.getAllByStatus());
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Hiển thị thông báo lỗi khi đạt giới hạn số lượng hóa đơn
            JOptionPane.showMessageDialog(this, "Đã đạt giới hạn số lượng hóa đơn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_TaoHoaDonActionPerformed

    private void mcl(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcl
        // TODO add your handling code here:

        indexHoaDonSelected = tb_hoaDon.getSelectedRow();
        HoaDonResponse1 response1 = hoaDonRepository.getAllByStatus().get(indexHoaDonSelected);

        // Định dạng tổng tiền để hiển thị với dấu phân cách hàng nghìn
        tongTien = response1.getTongTien();
        System.out.println(response1.getTongTien());
        String formattedTongTien = String.format("%,.3f₫", tongTien);

        txt_maHoaDon.setText(response1.getMaHoaDon());
        txt_maNhanVien.setText(response1.getMaNhanVien());
        txt_tenKhachHang1.setText(response1.getTenKhachHang());
        txt_maKhachHang.setText(response1.getMaKH());
        txt_tenKhachHang.setText(response1.getTenKhachHang());
        txt_tongTien.setText(formattedTongTien);
        txt_tongTien1.setText(formattedTongTien);
        txt_ngayTT.setText(response1.getNgayTao());

        // Tạm thời loại bỏ ActionListener để tránh áp dụng tự động phiếu giảm giá
        ActionListener[] listeners = cbo_PhieuGiamGia.getActionListeners();
        for (ActionListener listener : listeners) {
            cbo_PhieuGiamGia.removeActionListener(listener);
        }
        if (tongTien >= giamGiaRepository.getGiaTriDonHangToiThieu(response1.getMaNhanVien())) {

            showComboboxGiamGia(giamGiaRepository.getAllGiaTriTotNhat());
            cbo_PhieuGiamGia.setSelectedIndex(-1);
        }

        // Đặt lại ActionListener sau khi đã cập nhật xong ComboBox
        for (ActionListener listener : listeners) {
            cbo_PhieuGiamGia.addActionListener(listener);
        }

        showTableHoaDonChiTiet(hoaDonChiTietRepository.getAll(response1.getId()));
        System.out.println("abcxyz: " + tongTien);
//        Double giamGia = giamGiaRepository.getGiamNhieuHon(tongTien);
//        if (giamGia != null && giamGia > tongTien) {
//            Double giam = giamGiaRepository.getGiaTriDonHangToiThieu(maGiamGia);
//
//            System.out.println("hóa đơn tối thiểu: " + giam);
//            Double soTienGiam = giam - tongTien;
//            String FormatSoTienGiam = String.format("%,.3f₫", soTienGiam);
//            lb_Nofitications.setText("Mua Thêm " + FormatSoTienGiam + " để nhận thêm ưu đãi");
//        } else {
//            lb_Nofitications.setText("");
//        }

        // Thêm DocumentListener để tính tiền thừa khi nhập từ bàn phím
        txt_tienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                calculateChange();
            }

            public void removeUpdate(DocumentEvent e) {
                calculateChange();
            }

            public void insertUpdate(DocumentEvent e) {
                calculateChange();
            }
        });

        txt_tienKhachCK.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                calculateChange();
            }

            public void removeUpdate(DocumentEvent e) {
                calculateChange();
            }

            public void insertUpdate(DocumentEvent e) {
                calculateChange();
            }
        });
    }//GEN-LAST:event_mcl

    private void tb_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SPMouseClicked

        try {
            // Get the selected row index
            int row = tb_SP.getSelectedRow();

            // Check if a row is actually selected
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để tiếp tục.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return; // Exit if no row is selected
            }

            // Get the selected SanPhamChiTietRespone object
            SanPhamChiTietRespone spctr = sanPhamChiTietRepository.getAll().get(row);

            // Check if the indexHoaDonSelected is valid
            if (indexHoaDonSelected < 0 || indexHoaDonSelected >= hoaDonRepository.getAllByStatus().size()) {
                JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ hoặc không được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Exit if the index is invalid
            }

            // Get the selected HoaDonResponse1 object
            HoaDonResponse1 response1 = hoaDonRepository.getAllByStatus().get(indexHoaDonSelected);

            // Ask the user for quantity input
            String soLuongStr = JOptionPane.showInputDialog("Số lượng là:", "0");

            // Validate the quantity input
            int soLuong;
            try {
                soLuong = Integer.parseInt(soLuongStr);
                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (soLuong > spctr.getSoLuong()) {
                    JOptionPane.showMessageDialog(null, "Số lượng nhập mua không được lớn hơn số lượng tồn kho.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create HoaDonChiTietReponse object
            HoaDonChiTietReponse hoaDonChiTietResponse = HoaDonChiTietReponse.builder()
                    .idHoaDon(response1.getId())
                    .idSpct(spctr.getID())
                    .maSPCT(spctr.getMaSPCT())
                    .thuongHieu(spctr.getThuongHieu())
                    .xuatXu(spctr.getXuatXu())
                    .mauSac(spctr.getMauSac())
                    .kichThuoc(spctr.getKichThuoc())
                    .chatLieu(spctr.getChatLieu())
                    .coAo(spctr.getCoAo())
                    .doDay(spctr.getDoDay())
                    .phongCach(spctr.getPhongCach())
                    .giaBan(spctr.getGiaBan())
                    .soLuong(soLuong)
                    .trangThai(spctr.isTrangThai())
                    .thanhTien(soLuong * spctr.getGiaBan())
                    .build();

            // Add HoaDonChiTietReponse to the list
            List<HoaDonChiTietReponse> hdctList = hoaDonChiTietRepository.getAll(response1.getId());

            // Check if the product detail already exists in the invoice
            boolean found = false;
            for (HoaDonChiTietReponse item : hdctList) {
                if (item.getIdSpct().equals(spctr.getID())) {
                    // Update the quantity and total amount if found
                    item.setSoLuong(item.getSoLuong() + soLuong);
                    item.setThanhTien(item.getSoLuong() * item.getGiaBan());
                    found = true;
                    break;
                }
            }

            if (!found) {
                // If not found, add the new HoaDonChiTietReponse to the list
                hdctList.add(hoaDonChiTietResponse);
            }

            // Update the quantity of the selected product
            spctr.setSoLuong(spctr.getSoLuong() - soLuong);

            if (spctr.getSoLuong() == 0) {
                sanPhamChiTietRepository.updateTrangThai(spctr.getID());
            }

            // Update the product quantity in the database
            sanPhamChiTietRepository.updateSoLuong(spctr);

            // Update the invoice details in the database
            hoaDonChiTietRepository.add(hoaDonChiTietResponse);

            // Reload tables
            showTableSanPham(sanPhamChiTietRepository.getAll());
            showTableHoaDonChiTiet((ArrayList<HoaDonChiTietReponse>) hdctList);

            // Update total amount in the invoice
            tongTien = showTotalMoney((ArrayList<HoaDonChiTietReponse>) hdctList);
            response1.setTongTien(tongTien);
            hoaDonRepository.updateTongTien(response1.getTongTien(), response1.getId());

            // Reload the invoice table
            showTableHoaDon(hoaDonRepository.getAllByStatus());

            System.out.println("list :" + hoaDonRepository.getAllByStatus());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý yêu cầu. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tb_SPMouseClicked

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        int selectedPaymentMethod = cbb_httt.getSelectedIndex();

        // Kiểm tra xem phương thức thanh toán đã được chọn chưa
        if (selectedPaymentMethod < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phương thức thanh toán.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng xử lý nếu chưa chọn phương thức thanh toán
        }

        // Lấy hóa đơn đã chọn
        if (indexHoaDonSelected < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng xử lý nếu chưa chọn hóa đơn
        }

        HoaDonResponse1 hd = hoaDonRepository.getAllByStatus().get(indexHoaDonSelected);

        if (hd == null) {
            JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng xử lý nếu hóa đơn không hợp lệ
        }

        // Thiết lập phương thức thanh toán cho đối tượng HoaDon
        hd.setHinhThucThanhToan(selectedPaymentMethod);

        // Kiểm tra xem có chọn phiếu giảm giá không
        if (cbo_PhieuGiamGia.getSelectedIndex() == -1) {
            // Không chọn phiếu giảm giá, giữ nguyên tổng tiền cũ
            hd.setTienGiam(null);
            hd.setId_vouCher(null);
        } else {
            // Nếu có chọn phiếu giảm giá, tính tổng tiền sau khi giảm giá
            if (tongTienSauKhiGiam == null) {
                JOptionPane.showMessageDialog(null, "Giá trị giảm giá không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Dừng xử lý nếu giá trị giảm giá không hợp lệ
            }

            hd.setTienGiam(tongTienSauKhiGiam);
            hd.setId_vouCher(id_voucher);
        }

        // Kiểm tra tổng số tiền
        double totalAmount = hd.getTongTien();
        if (totalAmount <= 0) {
            JOptionPane.showMessageDialog(null, "Số tiền thanh toán không hợp lệ.", "Lỗi thanh toán", JOptionPane.ERROR_MESSAGE);
            return; // Dừng xử lý nếu tổng số tiền không hợp lệ
        }

        // Hỏi người dùng có muốn tiếp tục thanh toán không
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán hay không?", "Xác nhận thanh toán", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Nếu người dùng chọn Yes, cập nhật thông tin vào cơ sở dữ liệu
                hoaDonRepository.updateThongTin(hd);

                if (hd.getId_vouCher() != null) {
                    PhieuGiamGia1 pgg = giamGiaRepository.getAllByID(hd.getId_vouCher());
                    if (pgg != null) {
                        pgg.setSo_Lan_Su_Dung_Toi_Da(pgg.getSo_Lan_Su_Dung_Toi_Da() - 1);
                        if (pgg.getSo_Lan_Su_Dung_Toi_Da() == 0) {
                            pgg.setTrang_Thai(1); // Giả sử 1 là trạng thái "không còn hiệu lực"
                        }
                        giamGiaRepository.update(pgg);
                    }
                }

                // Làm mới bảng hóa đơn
                showTableHoaDon(hoaDonRepository.getAllByStatus());

                // Xóa dữ liệu trong bảng Giỏ Hàng sau khi thanh toán
                clearTableData();

                // Xóa các trường văn bản
                txt_ngayTT.setText("");
                txt_tenKhachHang.setText("");
                txt_maHoaDon.setText("");
                txt_tongTien.setText("");
                txt_tongTien1.setText("");
                txt_maNhanVien.setText("");
                txt_tienKhachDua.setText("");
                txt_tienKhachCK.setText("");
                txt_tienThua.setText("");

                // Hiển thị thông báo thành công
                JOptionPane.showMessageDialog(null, "Thanh toán thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật thông tin hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void clearTableData() {
        DefaultTableModel model = (DefaultTableModel) tb_hoaDonChiTiet.getModel(); // Lấy mô hình của JTable
        model.setRowCount(0); // Xóa tất cả các hàng, đưa bảng về trạng thái không có dữ liệu
    }
    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        // TODO add your handling code here:
        txt_ngayTT.setText("");
        txt_tenKhachHang.setText("");
        txt_maHoaDon.setText("");
        txt_tongTien.setText("");
        txt_tongTien1.setText("");
        txt_maNhanVien.setText("");
        txt_tienKhachDua.setText("");
        txt_tienKhachCK.setText("");
        txt_tienThua.setText("");
        cbb_gia.setSelectedIndex(-1);
        cbb_httt.setSelectedIndex(-1);
        cbb_kichThuoc.setSelectedIndex(-1);
        cbb_xuatXu.setSelectedIndex(-1);
        tenPhongCach = "%";
        tenKichThuoc = "%";
        tenXuatXu = "%";
        tenGiaBan = false;
        showTableSanPham(sanPhamChiTietRepository.getAll());
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        // Lấy đối tượng hóa đơn tại chỉ số được chọn
        HoaDonResponse1 hd = hoaDonRepository.getAllByStatus().get(indexHoaDonSelected);
        // Xác thực rằng đối tượng hóa đơn không null và có thông tin cần thiết
        if (hd != null && hd.getId() != null) {
            int option = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn hủy hóa đơn này không?",
                    "Xác nhận hủy",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            // Kiểm tra sự lựa chọn của người dùng
            if (option == JOptionPane.YES_OPTION) {
                boolean isCancelled = hoaDonRepository.huyHoaDon(hd);  // Gọi phương thức hủy hóa đơn

                // Kiểm tra xem việc hủy hóa đơn có thành công hay không
                if (isCancelled) {
                    // Cập nhật lại bảng hiển thị hóa đơn
                    showTableHoaDon(hoaDonRepository.getAllByStatus());
                    txt_ngayTT.setText("");
                    txt_tenKhachHang.setText("");
                    txt_maHoaDon.setText("");
                    txt_tongTien.setText("");
                    txt_tongTien1.setText("");
                    txt_maNhanVien.setText("");
                    txt_tienKhachDua.setText("");
                    txt_tienKhachCK.setText("");
                    txt_tienThua.setText("");
                    clearTableData();
                    showTableSanPham(sanPhamChiTietRepository.getAll());
                    JOptionPane.showMessageDialog(null, "Hóa đơn đã được hủy thành công.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể hủy hóa đơn. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hủy hóa đơn đã bị hủy bỏ.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ hoặc không được chọn.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void btn_timKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKhachHangActionPerformed
        // TODO add your handling code here:
        ThongTinKhachHangJFrame khachHangFrame = new ThongTinKhachHangJFrame();
        khachHangFrame.setVisible(true);

        // Kiểm tra biến tĩnh sau khi đóng KhachHangFrame
        khachHangFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                id_kh = ThongTinKhachHangJFrame.id_tamKH;
                KhachHang kh = thongTinKhachHangRepo.getKhachHangById(id_kh);
                System.out.println("id bán hàng " + id_kh);
                if (id_kh != null && kh != null) {  // Kiểm tra id_kh và kh
                    txt_maKhachHang.setText(kh.getMaKhachHang());
                    txt_tenKhachHang.setText(kh.getHoTen());
                    txt_tenKhachHang1.setText(kh.getHoTen());
                } else {
                    // Xử lý trường hợp kh hoặc id_kh là null
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }//GEN-LAST:event_btn_timKhachHangActionPerformed

    private void cbb_xuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_xuatXuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_xuatXuActionPerformed

    private void cbb_phongCachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_phongCachItemStateChanged
        // TODO add your handling code here:
        if (cbb_phongCach.getSelectedIndex() < 0) {
            tenPhongCach = "%";
            locSpctBanHang();
        }
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            tenPhongCach = cbb_phongCach.getSelectedItem().toString();
            locSpctBanHang();
        }

    }//GEN-LAST:event_cbb_phongCachItemStateChanged

    private void cbb_kichThuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_kichThuocItemStateChanged
        // TODO add your handling code here:
        if (cbb_kichThuoc.getSelectedIndex() < 0) {
            tenKichThuoc = "%";
            locSpctBanHang();
        }
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            tenKichThuoc = cbb_kichThuoc.getSelectedItem().toString();
            locSpctBanHang();
        }
    }//GEN-LAST:event_cbb_kichThuocItemStateChanged

    private void cbb_xuatXuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_xuatXuItemStateChanged
        // TODO add your handling code here:
        if (cbb_xuatXu.getSelectedIndex() < 0) {
            tenXuatXu = "%";
            locSpctBanHang();
        }
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            tenXuatXu = cbb_xuatXu.getSelectedItem().toString();
            locSpctBanHang();
        }

    }//GEN-LAST:event_cbb_xuatXuItemStateChanged

    private void cbb_giaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_giaItemStateChanged
        // TODO add your handling code here:
        if (cbb_gia.getSelectedIndex() == 0) {
            tenGiaBan = false;
            locSpctBanHang();
        } else {
            tenGiaBan = true;
            locSpctBanHang();
        }

    }//GEN-LAST:event_cbb_giaItemStateChanged

    private void txt_maNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maNhanVienActionPerformed

    private void txt_tongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tongTienActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_tongTienActionPerformed

    private void txt_tienKhachCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienKhachCKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachCKActionPerformed

    private void cbb_phongCachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_phongCachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_phongCachActionPerformed

    private void cbb_giaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_giaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_giaActionPerformed

    private void tb_hoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoaDonChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_hoaDonChiTietMouseClicked

    private void btn_quetQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quetQRActionPerformed
// Tạo một Menu để quét mã QR
        Menu menu = new Menu(3);
        menu.setVisible(true);

        menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                String maSpct = Menu.maSPCTBanHang;

                if (maSpct == null || maSpct.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm sau khi quét mã QR.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<SanPhamChiTietRespone> listspct = sanPhamChiTietRepository.getListSanPhamChiTietByMaSPCT(maSpct);

                if (listspct.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm sau khi quét mã QR.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Chọn sản phẩm đầu tiên từ danh sách (nếu có nhiều sản phẩm, bạn có thể cho người dùng chọn sản phẩm cụ thể)
                SanPhamChiTietRespone spctr = listspct.get(0);

                // Kiểm tra xem hóa đơn đã được chọn chưa
                if (indexHoaDonSelected < 0 || indexHoaDonSelected >= hoaDonRepository.getAllByStatus().size()) {
                    JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ hoặc không được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Exit if the index is invalid
                }

                // Hỏi người dùng nhập số lượng sản phẩm
                String soLuongStr = JOptionPane.showInputDialog("Số lượng là:", "0");

                // Validate the quantity input
                int soLuong;
                try {
                    soLuong = Integer.parseInt(soLuongStr);
                    if (soLuong <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (soLuong > spctr.getSoLuong()) {
                        JOptionPane.showMessageDialog(null, "Số lượng nhập mua không được lớn hơn số lượng tồn kho.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the selected HoaDonResponse1 object
                HoaDonResponse1 response1 = hoaDonRepository.getAllByStatus().get(indexHoaDonSelected);

                // Get the list of HoaDonChiTietReponse objects
                List<HoaDonChiTietReponse> hdctList = hoaDonChiTietRepository.getAll(response1.getId());

                // Kiểm tra xem sản phẩm đã tồn tại trong hóa đơn hay chưa
                boolean isExisting = false;
                for (HoaDonChiTietReponse hdct : hdctList) {
                    if (hdct.getIdSpct().equals(spctr.getID())) {
                        // Nếu sản phẩm đã tồn tại, cộng dồn số lượng và cập nhật lại thành tiền
                        int newSoLuong = hdct.getSoLuong() + soLuong;
                        hdct.setSoLuong(newSoLuong);
                        hdct.setThanhTien(newSoLuong * hdct.getGiaBan());
                        isExisting = true;
                        break;
                    }
                }

                // Nếu sản phẩm chưa tồn tại, thêm mới vào hóa đơn
                if (!isExisting) {
                    HoaDonChiTietReponse hoaDonChiTietResponse = HoaDonChiTietReponse.builder()
                            .idHoaDon(response1.getId())
                            .idSpct(spctr.getID())
                            .maSPCT(spctr.getMaSPCT())
                            .thuongHieu(spctr.getThuongHieu())
                            .xuatXu(spctr.getXuatXu())
                            .mauSac(spctr.getMauSac())
                            .kichThuoc(spctr.getKichThuoc())
                            .chatLieu(spctr.getChatLieu())
                            .coAo(spctr.getCoAo())
                            .doDay(spctr.getDoDay())
                            .phongCach(spctr.getPhongCach())
                            .giaBan(spctr.getGiaBan())
                            .soLuong(soLuong)
                            .trangThai(spctr.isTrangThai())
                            .thanhTien(soLuong * spctr.getGiaBan())
                            .build();

                    hdctList.add(hoaDonChiTietResponse);
                    hoaDonChiTietRepository.add(hoaDonChiTietResponse);
                }

                // Update the quantity of the selected product
                spctr.setSoLuong(spctr.getSoLuong() - soLuong);

                if (spctr.getSoLuong() == 0) {
                    sanPhamChiTietRepository.updateTrangThai(spctr.getID());
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã hết hàng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

                // Update the product quantity in the database
                sanPhamChiTietRepository.updateSoLuong(spctr);

                // Reload tables
                showTableSanPham(sanPhamChiTietRepository.getAll());
                showTableHoaDonChiTiet((ArrayList<HoaDonChiTietReponse>) hdctList);

                // Update total amount in the invoice
                double tongTien = showTotalMoney((ArrayList<HoaDonChiTietReponse>) hdctList);
                response1.setTongTien(tongTien);
                hoaDonRepository.updateTongTien(response1.getTongTien(), response1.getId());

                // Reload the invoice table
                showTableHoaDon(hoaDonRepository.getAllByStatus());
            }
        });

    }//GEN-LAST:event_btn_quetQRActionPerformed

    private void tb_SPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_SPMouseEntered

    private Double showTotalMoney(ArrayList<HoaDonChiTietReponse> lists) {
        tongTien = 0.00;
        for (HoaDonChiTietReponse hdct : lists) {
            tongTien += hdct.getThanhTien();
        }
        return tongTien;
    }

    private void locSpctBanHang() {
        ArrayList<SanPhamChiTietRespone> list = sanPhamChiTietRepository.locTheoDieuKienBanHang(tenPhongCach, tenKichThuoc, tenXuatXu, tenGiaBan);
        showTableSanPham(list);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.ButtonBadges btn_Huy;
    private com.Product.swing.ButtonBadges btn_TaoHoaDon;
    private com.Product.swing.ButtonBadges btn_ThanhToan;
    private com.Product.swing.ButtonBadges btn_lamMoi;
    private com.Product.swing.ButtonBadges btn_quetQR;
    private com.Product.swing.ButtonBadges btn_timKhachHang;
    private com.Product.GUI.Combobox cbb_gia;
    private com.Product.GUI.Combobox cbb_httt;
    private com.Product.GUI.Combobox cbb_kichThuoc;
    private com.Product.GUI.Combobox cbb_phongCach;
    private com.Product.GUI.Combobox cbb_xuatXu;
    private com.Product.GUI.Combobox cbo_PhieuGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private com.Product.GUI.Table tb_SP;
    private com.Product.GUI.Table tb_hoaDon;
    private com.Product.GUI.Table tb_hoaDonChiTiet;
    private com.Product.GUI.textfield.TextField txt_maHoaDon;
    private com.Product.GUI.textfield.TextField txt_maKhachHang;
    private com.Product.GUI.textfield.TextField txt_maNhanVien;
    private javax.swing.JLabel txt_ngayTT;
    private com.Product.GUI.textfield.TextField txt_tenKhachHang;
    private com.Product.GUI.textfield.TextField txt_tenKhachHang1;
    private com.Product.GUI.textfield.TextField txt_tienKhachCK;
    private com.Product.GUI.textfield.TextField txt_tienKhachDua;
    private javax.swing.JLabel txt_tienThua;
    private javax.swing.JTextField txt_tongTien;
    private javax.swing.JLabel txt_tongTien1;
    // End of variables declaration//GEN-END:variables
}

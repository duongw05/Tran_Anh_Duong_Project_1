package com.Product.form;

import com.Product.GUI.Table;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.repository.PhieuGiamGiaRepository;

import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import main.entity.PhieuGiamGia1;

public class GiamGiaForm extends javax.swing.JPanel {

    private DefaultTableModel dtm;
    private int i = -1;
    private PhieuGiamGiaRepository repo;
    private ArrayList<PhieuGiamGia1> list;

    public GiamGiaForm() {
        initComponents();
        //viet code duoi innit
        dtm = (DefaultTableModel) tbl_BangTT.getModel();
        repo = new PhieuGiamGiaRepository();
        //show datatable
        ShowDataTable(repo.getAll());
    }

    private void ShowDataTable(ArrayList<PhieuGiamGia1> list) {
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1); // khởi tạo giá trị bắt đầu bằng 1 để tự động tăng

        // forEach + lambda
        list.forEach(s -> dtm.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMa_Voucher(),
            s.getNgay_Bat_Dau(),
            s.getNgay_Het_Han(),
            s.getTen_Giam_Gia(),
            s.getPhan_Tram_Giam_Gia(),
            s.getGia_Tri_Don_Hang_Toi_Thieu(),
            s.getSo_Lan_Su_Dung_Toi_Da(),
            s.getTrang_Thai() == 0 ? "Đang Hoạt Động"
            : s.getTrang_Thai() == 1 ? "Ngừng Hoạt Động"
            : s.getTrang_Thai() == 2 ? "Sắp Diễn Ra" : "Trạng Thái Không Xác Định",
            s.getMo_ta()
        }));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new com.Product.GUI.combo_suggestion.ComboSuggestionUI();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSoLanDungToiDa = new com.Product.GUI.textfield.TextField();
        txtTenKhuyenMai = new com.Product.GUI.textfield.TextField();
        txtHDToiThieu = new com.Product.GUI.textfield.TextField();
        txtMaGiamGia = new com.Product.GUI.textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BtnThem = new com.Product.swing.Button();
        BtnSua = new com.Product.swing.Button();
        BtnLamMoi = new com.Product.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        BtnExcel = new com.Product.swing.Button();
        txtTimKiem = new com.Product.GUI.textfield.TextField();
        rdDHD = new com.Product.GUI.radio_button.RadioButtonCustom();
        rdNHD = new com.Product.GUI.radio_button.RadioButtonCustom();
        rdSDR = new com.Product.GUI.radio_button.RadioButtonCustom();
        BtnSua1 = new com.Product.swing.Button();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        txt_PhanTramGiamGia = new com.Product.GUI.textfield.TextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_BangTT = new com.Product.GUI.Table();
        BtnTimKiemTrangThai = new com.Product.swing.Button();
        BtnTimKiem2 = new com.Product.swing.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Giảm Giá");

        txtSoLanDungToiDa.setLabelText("Số lần dùng tối đa: ");

        txtTenKhuyenMai.setLabelText("Tên Giảm Giá:");

        txtHDToiThieu.setLabelText("Hóa Đơn Tối Thiểu:");

        txtMaGiamGia.setEnabled(false);
        txtMaGiamGia.setLabelText("Mã Giảm Giá ");
        txtMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGiamGiaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ngày bắt đầu :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Ngày kết thúc :");

        BtnThem.setBackground(new java.awt.Color(255, 255, 0));
        BtnThem.setText("Thêm");
        BtnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnThemActionPerformed(evt);
            }
        });

        BtnSua.setBackground(new java.awt.Color(255, 255, 0));
        BtnSua.setText("Sửa");
        BtnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSuaActionPerformed(evt);
            }
        });

        BtnLamMoi.setBackground(new java.awt.Color(255, 255, 0));
        BtnLamMoi.setText("Làm Mới");
        BtnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLamMoiActionPerformed(evt);
            }
        });

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Mô Tả:");

        BtnExcel.setBackground(new java.awt.Color(255, 255, 0));
        BtnExcel.setText("Xuất Excel");
        BtnExcel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcelActionPerformed(evt);
            }
        });

        txtTimKiem.setLabelText("Tìm kiếm tại đây:");
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        rdDHD.setBackground(new java.awt.Color(255, 204, 0));
        rdDHD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroup1.add(rdDHD);
        rdDHD.setText("Đang Hoạt Động");

        rdNHD.setBackground(new java.awt.Color(255, 204, 0));
        rdNHD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroup1.add(rdNHD);
        rdNHD.setText("Ngừng Hoạt Động");

        rdSDR.setBackground(new java.awt.Color(255, 204, 0));
        rdSDR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroup1.add(rdSDR);
        rdSDR.setText("Sắp Diễn Ra");

        BtnSua1.setBackground(new java.awt.Color(255, 255, 0));
        BtnSua1.setText("Chuyển đổi");
        BtnSua1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSua1ActionPerformed(evt);
            }
        });

        txt_PhanTramGiamGia.setLabelText("Phần trăm giảm giá");

        tbl_BangTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Voucher", "Ngày Bắt Đầu", "Ngày Hết Hạn", "Tên Giảm Giá", "Phần Trăm Giảm", "Giá Trị Tối Thiểu", "Số Lần Dùng Tối Đa", "Trạng Thái", "Mô Tả"
            }
        ));
        tbl_BangTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_BangTTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_BangTT);

        BtnTimKiemTrangThai.setBackground(new java.awt.Color(255, 255, 0));
        BtnTimKiemTrangThai.setText("Tìm KiếmTrạng Thái:");
        BtnTimKiemTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnTimKiemTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTimKiemTrangThaiActionPerformed(evt);
            }
        });

        BtnTimKiem2.setBackground(new java.awt.Color(255, 255, 0));
        BtnTimKiem2.setText("Tìm Kiếm");
        BtnTimKiem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTimKiem2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(BtnTimKiemTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdDHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(rdNHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(rdSDR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(BtnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(BtnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHDToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(58, 58, 58))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLanDungToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(txt_PhanTramGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHDToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLanDungToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BtnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_PhanTramGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnTimKiemTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdDHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdSDR, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNHD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThemActionPerformed
        // TODO add your handling code here:
        Date ngayBatDau = txtNgayBatDau.getDate();
        Date ngayKetThuc = txtNgayKetThuc.getDate();

        // Lấy ngày hiện tại mà không tính đến giờ, phút, giây
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date ngayHienTai = cal.getTime();

        // Chuẩn hóa ngày bắt đầu và ngày kết thúc để loại bỏ giờ, phút, giây
        if (ngayBatDau != null) {
            cal.setTime(ngayBatDau);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            ngayBatDau = cal.getTime();
        }

        if (ngayKetThuc != null) {
            cal.setTime(ngayKetThuc);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            ngayKetThuc = cal.getTime();
        }

        // Validation for txtSoLanDungToiDa
        if (txtSoLanDungToiDa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lần dùng tối đa!");
            txtSoLanDungToiDa.requestFocus();
            return;
        } else {
            try {
                int soLanDungToiDa = Integer.parseInt(txtSoLanDungToiDa.getText().trim());
                if (soLanDungToiDa < 0) {
                    JOptionPane.showMessageDialog(this, "Số lần dùng tối đa không được nhỏ hơn 0!");
                    txtSoLanDungToiDa.requestFocus();
                    return;
                }
                if (soLanDungToiDa > 100) {
                    JOptionPane.showMessageDialog(this, "Số lần dùng tối đa không được vượt quá 100!");
                    txtSoLanDungToiDa.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lần dùng tối đa phải là số hợp lệ!");
                txtSoLanDungToiDa.requestFocus();
                return;
            }
        }

        // Validation for txtTenKhuyenMai
        if (txtTenKhuyenMai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khuyến mãi!");
            txtTenKhuyenMai.requestFocus();
            return;
        }

        // Validation for txtHDToiThieu
        if (txtHDToiThieu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào số tiền hóa đơn tối thiểu!");
            txtHDToiThieu.requestFocus();
            return;
        } else {
            try {
                double hdToiThieu = Double.parseDouble(txtHDToiThieu.getText().trim());
                if (hdToiThieu < 0) {
                    JOptionPane.showMessageDialog(this, "Số tiền hóa đơn tối thiểu không được nhỏ hơn 0!");
                    txtHDToiThieu.requestFocus();
                    return;
                }
                if (hdToiThieu > 9999999) { // 7 chữ số
                    JOptionPane.showMessageDialog(this, "Số tiền hóa đơn tối thiểu không được vượt quá 7 chữ số!");
                    txtHDToiThieu.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số tiền hóa đơn tối thiểu phải là một số hợp lệ!");
                txtHDToiThieu.requestFocus();
                return;
            }
        }

        // Validation for txtSoTienGiam
        if (txt_PhanTramGiamGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm giảm giá!");
            txt_PhanTramGiamGia.requestFocus();
            return;
        } else {
            try {
                double soTienGiam = Double.parseDouble(txt_PhanTramGiamGia.getText().trim());
                if (soTienGiam < 0) {
                    JOptionPane.showMessageDialog(this, "Phần trăm giảm giá không được nhỏ hơn 0!");
                    txt_PhanTramGiamGia.requestFocus();
                    return;
                }
                if (soTienGiam > 100) {
                    JOptionPane.showMessageDialog(this, "Phần trăm giảm giá không được vượt quá 100%!");
                    txt_PhanTramGiamGia.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm giá phải là một số hợp lệ!");
                txt_PhanTramGiamGia.requestFocus();
                return;
            }
        }

        // Kiểm tra nếu ngày bắt đầu hoặc ngày kết thúc là null
        if (ngayBatDau == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu!");
            txtNgayBatDau.requestFocus();
            return;
        }

        if (ngayKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc!");
            txtNgayKetThuc.requestFocus();
            return;
        }

        // Kiểm tra ngày bắt đầu không được nhỏ hơn ngày hiện tại
        if (ngayBatDau.before(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải bằng hoặc lớn hơn ngày hiện tại!");
            txtNgayBatDau.requestFocus();
            return;
        }

        // Kiểm tra ngày kết thúc không được nhỏ hơn ngày hiện tại
        if (ngayKetThuc.before(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được nhỏ hơn ngày hiện tại!");
            txtNgayKetThuc.requestFocus();
            return;
        }

        // Kiểm tra ngày bắt đầu không được lớn hơn ngày kết thúc
        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc!");
            txtNgayBatDau.requestFocus();
            return;
        }

        // Validation for txtMoTa
        if (txtMoTa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả!");
            txtMoTa.requestFocus();
            return;
        }

        // Nếu tất cả các kiểm tra đều hợp lệ, tiến hành thêm dữ liệu vào cơ sở dữ liệu
        try {
            repo.add(getFormData());
            ShowDataTable(repo.getAll());
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (Exception ex) {
            Logger.getLogger(GiamGiaForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm dữ liệu: " + ex.getMessage());
        }
    }//GEN-LAST:event_BtnThemActionPerformed

    private void BtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSuaActionPerformed
        int index = tbl_BangTT.getSelectedRow();
        PhieuGiamGia1 newPhieu = getFormData();

        if (newPhieu == null) {
            return;
        }
        Date ngayBatDau = txtNgayBatDau.getDate();
        Date ngayKetThuc = txtNgayKetThuc.getDate();
        Date ngayHienTai = new Date();
        // Validation for txtSoLanDungToiDa
        if (txtSoLanDungToiDa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lần dùng tối đa!");
            txtSoLanDungToiDa.requestFocus();
            return;
        } else {
            try {
                int soLanDungToiDa = Integer.parseInt(txtSoLanDungToiDa.getText().trim());
                if (soLanDungToiDa < 0) {
                    JOptionPane.showMessageDialog(this, "Số lần dùng tối đa không được nhỏ hơn 0!");
                    txtSoLanDungToiDa.requestFocus();
                    return;
                }
                if (soLanDungToiDa > 99999) {
                    JOptionPane.showMessageDialog(this, "Số lần dùng tối đa không được vượt quá 5 chữ số!");
                    txtSoLanDungToiDa.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lần dùng tối đa phải là một số hợp lệ!");
                txtSoLanDungToiDa.requestFocus();
                return;
            }
        }

// Validation for txtTenKhuyenMai
        if (txtTenKhuyenMai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khuyến mãi!");
            txtTenKhuyenMai.requestFocus();
            return;
        }

// Validation for txtHDToiThieu
        if (txtHDToiThieu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào số tiền hóa đơn tối thiểu!");
            txtHDToiThieu.requestFocus();
            return;
        } else {
            try {
                double hdToiThieu = Double.parseDouble(txtHDToiThieu.getText().trim());
                if (hdToiThieu < 0) {
                    JOptionPane.showMessageDialog(this, "Số tiền hóa đơn tối thiểu không được nhỏ hơn 0!");
                    txtHDToiThieu.requestFocus();
                    return;
                }
                if (hdToiThieu > 9999999) { // 7 chữ số
                    JOptionPane.showMessageDialog(this, "Số tiền hóa đơn tối thiểu không được vượt quá 7 chữ số!");
                    txtHDToiThieu.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số tiền hóa đơn tối thiểu phải là một số hợp lệ!");
                txtHDToiThieu.requestFocus();
                return;
            }
        }

// Validation for txtSoTienGiam
        if (txtSoLanDungToiDa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm giảm giá!");
            txtSoLanDungToiDa.requestFocus();
            return;
        } else {
            try {
                double soTienGiam = Double.parseDouble(txtSoLanDungToiDa.getText().trim());
                if (soTienGiam < 0) {
                    JOptionPane.showMessageDialog(this, "Phần trăm giảm giá không được nhỏ hơn 0!");
                    txtSoLanDungToiDa.requestFocus();
                    return;
                }
                if (soTienGiam > 100) {
                    JOptionPane.showMessageDialog(this, "Phần trăm giảm giá không được vượt quá 100%!");
                    txtSoLanDungToiDa.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm giá phải là một số hợp lệ!");
                txtSoLanDungToiDa.requestFocus();
                return;
            }
        }

// Kiểm tra nếu ngày bắt đầu hoặc ngày kết thúc là null
        if (ngayBatDau == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu!");
            txtNgayBatDau.requestFocus();
            return;
        }

        if (ngayKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc!");
            txtNgayKetThuc.requestFocus();
            return;
        }

// Kiểm tra ngày bắt đầu không được nhỏ hơn ngày hiện tại
        if (ngayBatDau.before(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải bằng hoặc lớn hơn ngày hiện tại!");
            txtNgayBatDau.requestFocus();
            return;
        }

// Kiểm tra ngày kết thúc không được nhỏ hơn ngày hiện tại
        if (ngayKetThuc.before(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được nhỏ hơn ngày hiện tại!");
            txtNgayKetThuc.requestFocus();
            return;
        }

// Kiểm tra ngày bắt đầu không được lớn hơn ngày kết thúc
        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc!");
            txtNgayBatDau.requestFocus();
            return;
        }

// Validation for txtMoTa
        if (txtMoTa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả!");
            txtMoTa.requestFocus();
            return;
        }

        try {
            PhieuGiamGia1 sp = repo.getAll().get(index);
            repo.update(newPhieu, sp.getID());
            ShowDataTable(repo.getAll());
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnSuaActionPerformed

    private void BtnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLamMoiActionPerformed
        // TODO add your handling code here:
        PhieuGiamGia1 ph = new PhieuGiamGia1();
        txtMaGiamGia.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayKetThuc.setDate(null);
        txtTenKhuyenMai.setText("");
        txt_PhanTramGiamGia.setText("");
        txtHDToiThieu.setText("");
        txtSoLanDungToiDa.setText("");
        txtMoTa.setText("");
    }//GEN-LAST:event_BtnLamMoiActionPerformed

    private void BtnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcelActionPerformed
        // TODO add your handling code here:
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Phiếu Giảm Giá");

            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Danh Sách Phiếu Giảm Giá");

            row = spreadsheet.createRow(0);
            row.setHeight((short) 500);
            String[] headers = {"STT", "Mã Voucher", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Tên Giảm Giá", "Phần Trăm Giảm", "Giá Trị Đơn Hàng Tối Thiểu", "Số Lần Dùng Tối Đa", "Trạng Thái", "Mô Tả"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }

            PhieuGiamGiaRepository repoGG = new PhieuGiamGiaRepository();
            ArrayList<PhieuGiamGia1> listItem = repoGG.getAll();

            for (int i = 0; i < listItem.size(); i++) {
                PhieuGiamGia1 phieu = listItem.get(i);
                row = spreadsheet.createRow(7 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(phieu.getMa_Voucher());
                row.createCell(2).setCellValue(phieu.getNgay_Bat_Dau());
                row.createCell(3).setCellValue(phieu.getNgay_Het_Han());
                row.createCell(4).setCellValue(phieu.getTen_Giam_Gia());
                row.createCell(5).setCellValue(phieu.getPhan_Tram_Giam_Gia());
                row.createCell(6).setCellValue(phieu.getGia_Tri_Don_Hang_Toi_Thieu());
                row.createCell(7).setCellValue(phieu.getSo_Lan_Su_Dung_Toi_Da());

                row.createCell(8).setCellValue(phieu.getTrang_Thai());
                row.createCell(9).setCellValue(phieu.getMo_ta());
            };

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                    System.out.println("Xuất file Excel thành công vào: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Có lỗi khi ghi file.");
                }

                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BtnExcelActionPerformed

    private void txtMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGiamGiaActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void BtnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSua1ActionPerformed
        // TODO add your handling code here:
        int i = tbl_BangTT.getSelectedRow();
        if (i >= 0) { // Kiểm tra chỉ số hàng có hợp lệ không
            int id = repo.getAll().get(i).getID(); // Lấy ID của voucher từ hàng được chọn

            // Gọi phương thức remove để thay đổi trạng thái voucher
            try {
                boolean removed = repo.remove(id); // Thay đổi trạng thái voucher
                if (removed) {
                    // Nếu thay đổi trạng thái thành công, cập nhật bảng dữ liệu
                    ShowDataTable(repo.getAll());
                    JOptionPane.showMessageDialog(null, "Trạng thái voucher đã được cập nhật thành công.");
                } else {
                    // Nếu không thành công, hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "Không thể thay đổi trạng thái của voucher. Vui lòng kiểm tra lại.");
                }
            } catch (Exception ex) {
                Logger.getLogger(GiamGiaForm.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thay đổi trạng thái của voucher. Vui lòng thử lại.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một voucher để thay đổi trạng thái.");
        }
    }//GEN-LAST:event_BtnSua1ActionPerformed

    private void tbl_BangTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_BangTTMouseClicked
        // TODO add your handling code here:
        int i = tbl_BangTT.getSelectedRow();
        txtMaGiamGia.setText(tbl_BangTT.getValueAt(i, 1).toString());
        Date ngayBatDauStr = (Date) tbl_BangTT.getValueAt(i, 2);
        txtNgayBatDau.setDate(ngayBatDauStr);
        Date ngayKetThucStr = (Date) tbl_BangTT.getValueAt(i, 3);
        txtNgayKetThuc.setDate(ngayKetThucStr);
        txtTenKhuyenMai.setText(tbl_BangTT.getValueAt(i, 4).toString());
        txt_PhanTramGiamGia.setText(tbl_BangTT.getValueAt(i, 5).toString());
        txtHDToiThieu.setText(tbl_BangTT.getValueAt(i, 6).toString());
        txtSoLanDungToiDa.setText(tbl_BangTT.getValueAt(i, 7).toString());
        txtMoTa.setText(tbl_BangTT.getValueAt(i, 9).toString());
    }//GEN-LAST:event_tbl_BangTTMouseClicked

    private void BtnTimKiemTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTimKiemTrangThaiActionPerformed
        // TODO add your handling code here:
        String maVoucher = txtMaGiamGia.getText();
        String tenGiamGia = txtTenKhuyenMai.getText();
        String moTa = txtMoTa.getText();
        Integer trangThai = null;

        // Xác định giá trị trạng thái dựa trên radio button được chọn
        if (rdDHD.isSelected()) {
            trangThai = 0; // 0 cho trạng thái Đang Hoạt Động
        } else if (rdNHD.isSelected()) {
            trangThai = 1; // 1 cho trạng thái Ngừng Hoạt Động
        } else if (rdSDR.isSelected()) {
            trangThai = 2; // 2 cho trạng thái Sắp Diễn Ra
        }

        ArrayList<PhieuGiamGia1> results = repo.timkiemTrangThai(maVoucher, tenGiamGia, moTa, trangThai);
        ShowDataTable(results); // Cập nhật giao diện người dùng với dữ liệu tìm kiếm
        try {
            JOptionPane.showMessageDialog(this, "Tìm kiếm thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnTimKiemTrangThaiActionPerformed

    private void BtnTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTimKiem2ActionPerformed
        // TODO add your handling code here:
        try {
            ShowDataTable(repo.Search(txtTimKiem.getText()));
            JOptionPane.showMessageDialog(this, "Tìm kiếm thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            // Thông báo lỗi khi tìm kiếm
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnTimKiem2ActionPerformed

    private void initComponets() {

        tbl_BangTT = (Table) new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    private PhieuGiamGia1 getFormData() {
        try {
            Double phanTramGiamGia = Double.parseDouble(txt_PhanTramGiamGia.getText().trim());
            Double GiaTriDonHangToiThieu = Double.parseDouble(txtHDToiThieu.getText().trim());
            Integer SoLanDungToiDa = Integer.parseInt(txtSoLanDungToiDa.getText().trim());
//        Integer SoLanDung = Integer.parseInt(txtSoLanDung.getText().trim());

            if (txtTenKhuyenMai.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên Khuyến Mãi", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
                return null; // Trả về null nếu có lỗi nhập liệu
            }

            String trangThai;
            if (rdDHD.isSelected()) {
                trangThai = "Đang hoạt động";
            } else if (rdNHD.isSelected()) { // Giả định rằng bạn có một radio button khác cho "Ngưng hoạt động"
                trangThai = "Ngưng hoạt động";
            } else {
                trangThai = "Sắp diễn ra";
            }

            return PhieuGiamGia1.builder()
                    .Ma_Voucher(txtMaGiamGia.getText())
                    .Ngay_Bat_Dau(txtNgayBatDau.getDate())
                    .Ngay_Het_Han(txtNgayKetThuc.getDate())
                    .ten_Giam_Gia(txtTenKhuyenMai.getText())
                    .phan_Tram_Giam_Gia(phanTramGiamGia)
                    .Gia_Tri_Don_Hang_Toi_Thieu(GiaTriDonHangToiThieu)
                    .So_Lan_Su_Dung_Toi_Da(SoLanDungToiDa)
                    //                .So_Lan_Su_Dung(SoLanDung)
                    .Mo_ta(txtMoTa.getText())
                    .build();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Kiểm tra lại giá trị nhập vào, có thể có lỗi định dạng số", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.Button BtnExcel;
    private com.Product.swing.Button BtnLamMoi;
    private com.Product.swing.Button BtnSua;
    private com.Product.swing.Button BtnSua1;
    private com.Product.swing.Button BtnThem;
    private com.Product.swing.Button BtnTimKiem2;
    private com.Product.swing.Button BtnTimKiemTrangThai;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.Product.GUI.combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.Product.GUI.radio_button.RadioButtonCustom rdDHD;
    private com.Product.GUI.radio_button.RadioButtonCustom rdNHD;
    private com.Product.GUI.radio_button.RadioButtonCustom rdSDR;
    private com.Product.GUI.Table tbl_BangTT;
    private com.Product.GUI.textfield.TextField txtHDToiThieu;
    private com.Product.GUI.textfield.TextField txtMaGiamGia;
    private javax.swing.JTextArea txtMoTa;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private com.Product.GUI.textfield.TextField txtSoLanDungToiDa;
    private com.Product.GUI.textfield.TextField txtTenKhuyenMai;
    private com.Product.GUI.textfield.TextField txtTimKiem;
    private com.Product.GUI.textfield.TextField txt_PhanTramGiamGia;
    // End of variables declaration//GEN-END:variables

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

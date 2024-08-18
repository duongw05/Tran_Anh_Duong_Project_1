/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import com.Product.form.SanPhamForm;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import main.entity.ChatLieu;
import main.entity.CoAo;
import main.entity.DoDay;
import main.entity.KichThuoc;
import main.entity.MauSac;
import main.entity.SanPham;
import main.entity.SanPhamChiTiet;
import main.entity.ThuongHieu;
import main.entity.TinhLinhHoat;
import main.entity.XuatXu;
import main.repository.ChatLieuRepository;
import main.repository.CoAoRepository;
import main.repository.DoDayRepository;
import main.repository.KichThuocRepository;
import main.repository.MauSacRepository;
import main.repository.SanPhamChiTietRepository;
import main.repository.SanPhamRepository;
import main.repository.ThuongHieuRepository;
import main.repository.TinhLinhHoatRepository;
import main.repository.XuatXuRepository;
import main.response.SanPhamChiTietRespone;

/**
 *
 * @author ADMIN
 */
public class CapNhatSPCTJFrame extends javax.swing.JFrame {
    String ma_tam = SanPhamForm.maSanPhamChiTiet;

    private DefaultComboBoxModel dcbmXuatXu;
    private XuatXuRepository xuatXuRepo = new XuatXuRepository();

    private DefaultComboBoxModel dcbmKichThuoc;
    private KichThuocRepository kichThuocRepo = new KichThuocRepository();

    private DefaultComboBoxModel dcbmCoAo;
    private CoAoRepository coAoRepo = new CoAoRepository();

    private DefaultComboBoxModel dcbmDoDay;
    private DoDayRepository doDayRepository = new DoDayRepository();

    private DefaultComboBoxModel dcbmMauSac;
    private MauSacRepository mauSacRepo = new MauSacRepository();

    private DefaultComboBoxModel dcbmTinhLinhHoat;
    private TinhLinhHoatRepository tinhLinhHoatRepo = new TinhLinhHoatRepository();

    private DefaultComboBoxModel dcbmThuongHieu;
    private ThuongHieuRepository thuongHieuRepo = new ThuongHieuRepository();

    private DefaultComboBoxModel dcbmChatLieu;
    private ChatLieuRepository chatLieuRepo = new ChatLieuRepository();
    
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();
    /**
     * Creates new form CapNhatSPCTJFrame
     */
    public CapNhatSPCTJFrame() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Cập Nhật Thông Tin Sản Phẩm Chi Tiết");
        showComboboxChatLieu(chatLieuRepo.getAll());
        showComboboxCoAo(coAoRepo.getAll());
        showComboboxMauSac(mauSacRepo.getAll());
        showComboboxDoDay(doDayRepository.getAll());
        showComboboxThuongHieu(thuongHieuRepo.getAll());
        showComboboxXuatXu(xuatXuRepo.getAll());
        showComboboxKichThuoc(kichThuocRepo.getAll());
        showComboboxTenSanPham(sanPhamRepository.getAll());
        showComboboxTinhLinhHoat(tinhLinhHoatRepo.getAll());
        
    }
    
    private void showComboboxChatLieu(ArrayList<ChatLieu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_ChatLieu.getModel();
        for (ChatLieu cl : list) {
            comboBoxModel.addElement(cl);
            System.out.println(cl.getId());
        }
    }

    private void showComboboxThuongHieu(ArrayList<ThuongHieu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_ThuongHieu.getModel();
        for (ThuongHieu cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxTinhLinhHoat(ArrayList<TinhLinhHoat> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_PhongCach.getModel();
        for (TinhLinhHoat cl : list) {
            comboBoxModel.addElement(cl);
        }
    }

    private void showComboboxCoAo(ArrayList<CoAo> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_CoAo.getModel();
        for (CoAo cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxKichThuoc(ArrayList<KichThuoc> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_KichThuoc.getModel();
        for (KichThuoc cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxTenSanPham(ArrayList<SanPham> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_TenSanPham.getModel();
        for (SanPham cl : list) {
            comboBoxModel.addElement(cl.getTenSanPham());
        }
    }

    private void showComboboxMauSac(ArrayList<MauSac> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_MauSac.getModel();
        for (MauSac cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxDoDay(ArrayList<DoDay> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_DoDay.getModel();
        for (DoDay cl : list) {
            comboBoxModel.addElement(cl.getTen());
        }
    }

    private void showComboboxXuatXu(ArrayList<XuatXu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbb_XuatXu.getModel();
        for (XuatXu cl : list) {
            comboBoxModel.addElement(cl);
        }
    }
    
  
    
    // Phương thức convertResponeToEntity sử dụng convertToId
    private SanPhamChiTiet convertResponeToEntity(SanPhamChiTietRespone respone) {
        // Lấy danh sách sản phẩm theo tên
        ArrayList<SanPham> spList = sanPhamRepository.getSanPhamByTen(respone.getTenSP());

        // Lấy phần tử đầu tiên trong danh sách hoặc trả về null nếu danh sách rỗng
        SanPham sp = spList.stream().findFirst().orElse(null);

        // Lấy các thuộc tính khác
        ChatLieu cl = chatLieuRepo.getChatLieuByTen(respone.getChatLieu());
        ThuongHieu th = thuongHieuRepo.getThuongHieuByTen(respone.getThuongHieu());
        XuatXu xx = xuatXuRepo.getXuatXuByTen(respone.getXuatXu());
        MauSac ms = mauSacRepo.getMauSacByTen(respone.getMauSac());
        KichThuoc kt = kichThuocRepo.getKichThuocByTen(respone.getKichThuoc());
        CoAo ca = coAoRepo.getCoAoByTen(respone.getCoAo());
        DoDay dd = doDayRepository.getDoDayByTen(respone.getDoDay());
        TinhLinhHoat tlh = tinhLinhHoatRepo.getTinhLinhHoatByTen(respone.getPhongCach());

        // Xử lý trường hợp không tìm thấy sản phẩm
        if (sp == null) {
            throw new RuntimeException("Sản phẩm không tìm thấy: " + respone.getTenSP());
        }

        return SanPhamChiTiet.builder()
                .maSanPhamChiTiet(respone.getMaSPCT())
                .sanPhamID(sp.getId())
                .thuongHieuID(th != null ? th.getId() : null)
                .XuatXuID(xx != null ? xx.getId() : null)
                .mauSacID(ms != null ? ms.getId() : null)
                .kichThuocID(kt != null ? kt.getId() : null)
                .chatLieuID(cl != null ? cl.getId() : null)
                .coAoID(ca != null ? ca.getId() : null)
                .doDayID(dd != null ? dd.getId() : null)
                .tinhLinhHoatID(tlh != null ? tlh.getId() : null)
                .giaBan(respone.getGiaBan())
                .soLuongTon(respone.getSoLuong())
                .trangThai(respone.isTrangThai())
                .build();
    }
    
    private SanPhamChiTietRespone getFormDataSanPhamCT() {
        // Lấy dữ liệu từ giao diện người dùng
        String tenSP = cbb_TenSanPham.getSelectedItem().toString().trim();
        String thuongHieu = cbb_ThuongHieu.getSelectedItem().toString().trim();
        String chatLieu = cbb_ChatLieu.getSelectedItem().toString().trim();
        String kichThuoc = cbb_KichThuoc.getSelectedItem().toString().trim();
        String coAo = cbb_CoAo.getSelectedItem().toString().trim();
        String mauSac = cbb_MauSac.getSelectedItem().toString().trim();
        String doDay = cbb_DoDay.getSelectedItem().toString().trim();
        String xuatXu = cbb_XuatXu.getSelectedItem().toString().trim();
        String tinhLinhHoat = cbb_PhongCach.getSelectedItem().toString().trim();
        String giaBanText = txt_GiaBan.getText().trim();
        String soLuongText = txtSoLuong.getText().trim();

        // Kiểm tra các trường dữ liệu không để trống
        if (tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (thuongHieu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Thương hiệu không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (chatLieu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chất liệu không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (kichThuoc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kích thước không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (coAo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Có áo không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (mauSac.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Màu sắc không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (doDay.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Độ dày không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (xuatXu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Xuất xứ không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (tinhLinhHoat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phong cách không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (giaBanText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá bán không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (soLuongText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra giá bán
        if (!giaBanText.matches("\\d+(\\.\\d{1,2})?")) { // Chỉ cho phép số dương, có thể có 1 hoặc 2 chữ số thập phân
            JOptionPane.showMessageDialog(null, "Giá bán phải là số dương và không có ký tự khác", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (giaBanText.length() > 12) { // Giới hạn 12 ký tự
            JOptionPane.showMessageDialog(null, "Giá bán không được vượt quá 12 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Double giaBan;
        try {
            giaBan = Double.parseDouble(giaBanText);
            if (giaBan <= 0) { // Giá bán phải lớn hơn 0
                JOptionPane.showMessageDialog(null, "Giá bán phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi chuyển đổi số
            JOptionPane.showMessageDialog(null, "Dữ liệu số cho giá bán không hợp lệ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra số lượng
        if (!soLuongText.matches("\\d+")) { // Chỉ cho phép số nguyên dương
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (soLuongText.length() > 12) { // Giới hạn 12 ký tự
            JOptionPane.showMessageDialog(null, "Số lượng không được vượt quá 12 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Integer soLuong;
        try {
            soLuong = Integer.parseInt(soLuongText);
            if (soLuong <= 0) { // Số lượng phải lớn hơn 0
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi chuyển đổi số
            JOptionPane.showMessageDialog(null, "Dữ liệu số cho số lượng không hợp lệ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Tạo đối tượng respone
        return SanPhamChiTietRespone.builder()
                .tenSP(tenSP)
                .thuongHieu(thuongHieu)
                .xuatXu(xuatXu)
                .mauSac(mauSac)
                .kichThuoc(kichThuoc)
                .chatLieu(chatLieu)
                .coAo(coAo)
                .doDay(doDay)
                .phongCach(tinhLinhHoat)
                .giaBan(giaBan)
                .soLuong(soLuong)
                .build();
    }
   
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbb_ChatLieu = new com.Product.GUI.Combobox();
        cbb_MauSac = new com.Product.GUI.Combobox();
        cbb_KichThuoc = new com.Product.GUI.Combobox();
        cbb_ThuongHieu = new com.Product.GUI.Combobox();
        cbb_TenSanPham = new com.Product.GUI.Combobox();
        cbb_CoAo = new com.Product.GUI.Combobox();
        cbb_XuatXu = new com.Product.GUI.Combobox();
        cbb_DoDay = new com.Product.GUI.Combobox();
        cbb_PhongCach = new com.Product.GUI.Combobox();
        txtSoLuong = new com.Product.GUI.textfield.TextField();
        txt_GiaBan = new com.Product.GUI.textfield.TextField();
        btn_CapNhat = new com.Product.swing.ButtonBadges();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Cập Nhật Thông Tin Sản Phẩm Chi Tiết");

        cbb_TenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TenSanPhamActionPerformed(evt);
            }
        });

        cbb_PhongCach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_PhongCachActionPerformed(evt);
            }
        });

        txtSoLuong.setLabelText("Số Lượng");
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txt_GiaBan.setLabelText("Giá Bán");

        btn_CapNhat.setBackground(new java.awt.Color(255, 204, 204));
        btn_CapNhat.setText("Cập Nhật");
        btn_CapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên Sản Phẩm");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Phong Cách");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Thương Hiệu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Kích Thước");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Chất Liệu");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Xuất Xứ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Cổ Áo ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Màu Sắc");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Độ Dày");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_CoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_PhongCach, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_TenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbb_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbb_DoDay, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbb_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbb_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(41, 41, 41)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_XuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_TenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_DoDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_XuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_CoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_PhongCach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_PhongCachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_PhongCachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_PhongCachActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        // TODO add your handling code here:
        SanPhamChiTietRespone spctrp = getFormDataSanPhamCT();
        if (spctrp == null) {
            return;
        }
        try {
            SanPhamChiTiet spct = convertResponeToEntity(spctrp);
            sanPhamChiTietRepository.updateSanPhamChiTiet(spct, ma_tam);
            JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công");
            this.dispose();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void cbb_TenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TenSanPhamActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbb_TenSanPhamActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CapNhatSPCTJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CapNhatSPCTJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CapNhatSPCTJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CapNhatSPCTJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CapNhatSPCTJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.ButtonBadges btn_CapNhat;
    private com.Product.GUI.Combobox cbb_ChatLieu;
    private com.Product.GUI.Combobox cbb_CoAo;
    private com.Product.GUI.Combobox cbb_DoDay;
    private com.Product.GUI.Combobox cbb_KichThuoc;
    private com.Product.GUI.Combobox cbb_MauSac;
    private com.Product.GUI.Combobox cbb_PhongCach;
    private com.Product.GUI.Combobox cbb_TenSanPham;
    private com.Product.GUI.Combobox cbb_ThuongHieu;
    private com.Product.GUI.Combobox cbb_XuatXu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private com.Product.GUI.textfield.TextField txtSoLuong;
    private com.Product.GUI.textfield.TextField txt_GiaBan;
    // End of variables declaration//GEN-END:variables
}

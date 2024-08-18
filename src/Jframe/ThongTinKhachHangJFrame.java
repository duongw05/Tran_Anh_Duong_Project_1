/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import com.Product.form.BanHangForm;
import com.Product.form.SanPhamForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.entity.KhachHang;
import main.repository.ThongTinKhachHangRepository;

/**
 *
 * @author ADMIN
 */
public class ThongTinKhachHangJFrame extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private ThongTinKhachHangRepository ttkhRepo = new ThongTinKhachHangRepository();

    public static Integer id_tamKH;

    /**
     * Creates new form ThongTinKhachHangJFrame
     */
    public ThongTinKhachHangJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Thông Tin Khách Hàng - SUNSHINE");
        showDataTTKH(ttkhRepo.getAll());

    }

    private void showDataTTKH(ArrayList<KhachHang> list_kh) {
        dtm = (DefaultTableModel) tbl_khachHang.getModel();
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list_kh.forEach(kh -> dtm.addRow(new Object[]{
            index.getAndIncrement(), kh.getMaKhachHang(), kh.getHoTen(),
            kh.getSoDienThoai(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getNgaySinh(),
            kh.getEmail(), kh.getDiaChi()
        }
        ));
    }

    private KhachHang getFormDataKhachHang() {
        // Lấy dữ liệu từ các trường
        String hoTen = txt_tenKhachHang.getText().trim();
        String diaChi = txt_DiaChi.getText().trim();
        String soDienThoai = txt_soDienThoai1.getText().trim();
        String email = txt_Email.getText().trim();
        String ngaySinh = txt_NgaySinh.getText().trim();

        // Kiểm tra xem tất cả các trường có bị bỏ trống không
        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (ngaySinh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
//        boolean emailExists = false;
//        boolean phoneExists = false;
//        List<KhachHang> existingKhachHangList = ttkhRepo.getAll();
//                for (KhachHang existingKh : existingKhachHangList) {
//            if (existingKh.getEmail().equalsIgnoreCase(email)) {
//                emailExists = true;
//            }
//            if (existingKh.getSoDienThoai().equals(soDienThoai)) {
//                phoneExists = true;
//            }
//        }
//
//        if (emailExists) {
//            JOptionPane.showMessageDialog(this, "Email đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (phoneExists) {
//            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

//        if (!soDienThoai.matches("\\d{9,14}")) {
//            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Vui lòng nhập từ 9 đến 14 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
        // Validate email
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }


        // Convert gender selection to Boolean
        Boolean gioiTinh = rdo_Nam.isSelected(); // true cho "Nam", false cho "Nữ"

        // Build KhachHang object using Lombok's builder
        KhachHang kh = KhachHang.builder()
                .hoTen(hoTen)
                .diaChi(diaChi)
                .soDienThoai(soDienThoai)
                .email(email)
                .ngaySinh(ngaySinh)
                .gioiTinh(gioiTinh)
                .build();

        return kh;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        tabbedPaneCustomm3 = new com.Product.GUI.tabbed.TabbedPaneCustomm();
        jPanel4 = new javax.swing.JPanel();
        txt_timKiem = new com.Product.GUI.textfield.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_khachHang = new com.Product.GUI.Table();
        jPanel5 = new javax.swing.JPanel();
        txt_tenKhachHang = new com.Product.GUI.textfield.TextField();
        txt_NgaySinh = new com.Product.GUI.textfield.TextField();
        txt_Email = new com.Product.GUI.textfield.TextField();
        rdo_Nam = new com.Product.GUI.radio_button.RadioButtonCustom();
        rdo_Nu = new com.Product.GUI.radio_button.RadioButtonCustom();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_DiaChi = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_ThemThongTinKhachHang = new com.Product.swing.ButtonBadges();
        txt_soDienThoai1 = new com.Product.GUI.textfield.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPaneCustomm3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabbedPaneCustomm3.setSelectedColor(new java.awt.Color(255, 204, 51));
        tabbedPaneCustomm3.setUnselectedColor(new java.awt.Color(255, 255, 204));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txt_timKiem.setLabelText("Tìm Kiếm");

        tbl_khachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Tên KH", "SĐT", "Giới Tính", "Ngày Sinh", "Email", "Địa Chỉ"
            }
        ));
        tbl_khachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_khachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_khachHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        tabbedPaneCustomm3.addTab("Danh Sách Khách Hàng", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txt_tenKhachHang.setLabelText("Họ Tên");

        txt_NgaySinh.setLabelText("Ngày Sinh");

        txt_Email.setLabelText("Email");

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setText("Nam");

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nữ");
        rdo_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NuActionPerformed(evt);
            }
        });

        txt_DiaChi.setColumns(20);
        txt_DiaChi.setRows(5);
        jScrollPane2.setViewportView(txt_DiaChi);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Giới Tính");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Địa Chỉ");

        btn_ThemThongTinKhachHang.setBackground(new java.awt.Color(255, 204, 204));
        btn_ThemThongTinKhachHang.setText("Thêm");
        btn_ThemThongTinKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_ThemThongTinKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemThongTinKhachHangActionPerformed(evt);
            }
        });

        txt_soDienThoai1.setLabelText("Số Điện Thoại");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ThemThongTinKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_soDienThoai1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(rdo_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txt_soDienThoai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rdo_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(btn_ThemThongTinKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        tabbedPaneCustomm3.addTab("Thiết Lập Thông Tin Khách Hàng", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustomm3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustomm3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NuActionPerformed

    private void tbl_khachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_khachHangMouseClicked
        // Lấy chỉ số hàng được chọn
        int selectedRowIndex = tbl_khachHang.getSelectedRow();

        if (selectedRowIndex != -1) {
            // Lấy giá trị từ cột đầu tiên và kiểm tra kiểu dữ liệu
            Object value = tbl_khachHang.getValueAt(selectedRowIndex, 0);

            if (value instanceof Integer) {
                id_tamKH = (Integer) value;
                System.out.println("ID khách hàng: " + id_tamKH);

                // Đóng cửa sổ hiện tại nếu ID hợp lệ
                if (id_tamKH != null) {
                    this.dispose(); // Đóng cửa sổ hiện tại
                } else {
                    // Thông báo lỗi nếu id_tamKH là null
                    JOptionPane.showMessageDialog(this, "Mã khách hàng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Thông báo lỗi nếu giá trị không phải là Integer
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Hiển thị thông báo lỗi nếu không có hàng nào được chọn
            JOptionPane.showMessageDialog(this, "Không có hàng nào được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tbl_khachHangMouseClicked

    private void btn_ThemThongTinKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemThongTinKhachHangActionPerformed
        // Hiển thị hộp thoại xác nhận
        int option = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn thêm thông tin khách hàng này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        // Nếu người dùng chọn "Có"
        if (option == JOptionPane.YES_OPTION) {
            // Thực hiện hành động thêm thông tin khách hàng
            ttkhRepo.add(getFormDataKhachHang());
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            showDataTTKH(ttkhRepo.getAll());
        } else {
            // Nếu người dùng chọn "Không", không thực hiện hành động nào
            // Có thể thêm thông báo hoặc hành động khác nếu cần
            JOptionPane.showMessageDialog(this, "Hành động đã bị hủy.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemThongTinKhachHangActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinKhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinKhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinKhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinKhachHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinKhachHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.ButtonBadges btn_ThemThongTinKhachHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.Product.GUI.radio_button.RadioButtonCustom rdo_Nam;
    private com.Product.GUI.radio_button.RadioButtonCustom rdo_Nu;
    private com.Product.GUI.tabbed.TabbedPaneCustomm tabbedPaneCustomm3;
    private com.Product.GUI.Table tbl_khachHang;
    private javax.swing.JTextArea txt_DiaChi;
    private com.Product.GUI.textfield.TextField txt_Email;
    private com.Product.GUI.textfield.TextField txt_NgaySinh;
    private com.Product.GUI.textfield.TextField txt_soDienThoai1;
    private com.Product.GUI.textfield.TextField txt_tenKhachHang;
    private com.Product.GUI.textfield.TextField txt_timKiem;
    // End of variables declaration//GEN-END:variables

}

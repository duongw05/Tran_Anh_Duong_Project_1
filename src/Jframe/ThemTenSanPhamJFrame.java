/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import main.repository.CoAoRepository;
import main.repository.SanPhamChiTietRepository;
import main.repository.SanPhamRepository;
import main.repository.ThuocTinhSanPhamRepository;
import main.entity.CoAo;
import main.entity.SanPham;
import main.entity.ThuocTinhSanPham;
import com.Product.form.SanPhamForm;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThemTenSanPhamJFrame extends javax.swing.JFrame {

    private DefaultTableModel dtmSanPham;
//    private ThuocTinhSanPhamRepository thuocTinhSanPhamRepository;
//    private SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    /**
     * Creates new form TestJframe
     */
    public ThemTenSanPhamJFrame() {
        initComponents();
        setTitle("Thêm Thuộc Tính Sản Phẩm");
        setLocationRelativeTo(null);

    }

    private SanPham getFormDataSP() {
        String tenSanPham = txtTenThuocTinh.getText().trim();
        // Lấy từ đầu tiên của tên sản phẩm
        tenSanPham = tenSanPham.split(" ")[0];
        if (txtTenThuocTinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên thuộc tính", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Kiểm tra xem tên sản phẩm có chứa số hoặc ký tự đặc biệt không
        if (!tenSanPham.matches("^[\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được chứa số hoặc ký tự đặc biệt", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra độ dài của tên sản phẩm
        if (tenSanPham.length() > 20) {
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được vượt quá 20 ký tự", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (sanPhamRepository.isSanPhamExist(tenSanPham)) {
            JOptionPane.showMessageDialog(null, "Tên thuộc tính đã tồn tại", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SanPham ttsp = new SanPham();
        ttsp.setMaSanPham(txtMaThuocTinh.getText());
        ttsp.setTenSanPham(txtTenThuocTinh.getText());
        ttsp.setMoTa(txt_MoTa.getText());
        return ttsp;
    }

    private void showTableThuocTinhSanPham(ArrayList<SanPham> lists) {
        dtmSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSanPham(), s.getTenSanPham(), s.getMoTa()}));
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
        txtTenThuocTinh = new com.Product.GUI.textfield.TextField();
        txtMaThuocTinh = new com.Product.GUI.textfield.TextField();
        btnThemThuocTinh = new com.Product.swing.ButtonBadges();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_MoTa = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTenThuocTinh.setLabelText("Tên Sản Phẩm");

        txtMaThuocTinh.setText("###");
        txtMaThuocTinh.setEnabled(false);
        txtMaThuocTinh.setLabelText("Mã Sản Phẩm");

        btnThemThuocTinh.setBackground(new java.awt.Color(255, 204, 153));
        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thêm Tên Sản Phẩm");

        txt_MoTa.setColumns(20);
        txt_MoTa.setRows(5);
        jScrollPane1.setViewportView(txt_MoTa);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mô Tả:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 124, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1))
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                            .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        // TODO add your handling code here:
        SanPham ttsp = getFormDataSP();
        if (ttsp != null) {
            if (sanPhamRepository.add(ttsp)) {
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
                this.dispose();
            }
        }


    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThemTenSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThemTenSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThemTenSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,txt_MoTa ex);
//        } catctxth (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThemTenSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemTenSanPhamJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.ButtonBadges btnThemThuocTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.Product.GUI.textfield.TextField txtMaThuocTinh;
    private com.Product.GUI.textfield.TextField txtTenThuocTinh;
    private javax.swing.JTextArea txt_MoTa;
    // End of variables declaration//GEN-END:variables
}

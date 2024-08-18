/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import main.repository.ChatLieuRepository;
import main.repository.ThuocTinhSanPhamRepository;
import main.entity.ThuocTinhSanPham;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThemChatLieuJFrame extends javax.swing.JFrame {

    private DefaultTableModel dtmThuocTinhSanPham;
    private ThuocTinhSanPhamRepository thuocTinhSanPhamRepository = new ThuocTinhSanPhamRepository();
    private ChatLieuRepository repoChatLieu = new ChatLieuRepository();

    /**
     * Creates new form ThemChatLieuJFrame
     */
    public ThemChatLieuJFrame() {
        initComponents();
        setTitle("Thêm Chất Liệu");
        setLocationRelativeTo(null);

    }

    private ThuocTinhSanPham getFormDataThuocTinhSP() {
        String tenChatLieu = txt_TenChatLieu.getText().trim();
        tenChatLieu = tenChatLieu.split(" ")[0];
        if (txt_TenChatLieu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên thuộc tính", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!tenChatLieu.matches("^[\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Tên chất liệu không được chứa số hoặc ký tự đặc biệt", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (tenChatLieu.length() > 20) {
            JOptionPane.showMessageDialog(null, "Tên chất liệu không được vượt quá 20 ký tự", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (repoChatLieu.isTenChatLieuExist(tenChatLieu)) {
            JOptionPane.showMessageDialog(null, "Tên thuộc tính đã tồn tại", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ThuocTinhSanPham ttsp = new ThuocTinhSanPham();
        ttsp.setMaThuocTinhSanPham(txt_MaChatLieu.getText());
        ttsp.setTenThuocTinhSanPham(txt_TenChatLieu.getText());

        return ttsp;
    }

    private void showTableThuocTinhSanPham(ArrayList<ThuocTinhSanPham> lists) {
        dtmThuocTinhSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmThuocTinhSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaThuocTinhSanPham(), s.getTenThuocTinhSanPham(),}));
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
        txt_MaChatLieu = new com.Product.GUI.textfield.TextField();
        txt_TenChatLieu = new com.Product.GUI.textfield.TextField();
        buttonBadges1 = new com.Product.swing.ButtonBadges();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thêm Chất Liệu");

        txt_MaChatLieu.setText("###");
        txt_MaChatLieu.setEnabled(false);
        txt_MaChatLieu.setLabelText("Mã Chất Liệu");

        txt_TenChatLieu.setLabelText("Tên chất liệu");

        buttonBadges1.setBackground(new java.awt.Color(255, 204, 51));
        buttonBadges1.setText("Thêm");
        buttonBadges1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonBadges1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBadges1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_MaChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(txt_TenChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 110, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(txt_MaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(txt_TenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBadges1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBadges1ActionPerformed
        // TODO add your handling code here:
        ThuocTinhSanPham ttsp = getFormDataThuocTinhSP();
        if (ttsp != null) {
            if (thuocTinhSanPhamRepository.insertChatLieu(ttsp)) {
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
                this.dispose();
            }
        }
    }//GEN-LAST:event_buttonBadges1ActionPerformed

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
            java.util.logging.Logger.getLogger(ThemChatLieuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemChatLieuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemChatLieuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemChatLieuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemChatLieuJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.ButtonBadges buttonBadges1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private com.Product.GUI.textfield.TextField txt_MaChatLieu;
    private com.Product.GUI.textfield.TextField txt_TenChatLieu;
    // End of variables declaration//GEN-END:variables
}
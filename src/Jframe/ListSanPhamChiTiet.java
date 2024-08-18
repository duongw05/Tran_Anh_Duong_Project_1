/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import com.Product.form.SanPhamForm;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.repository.SanPhamChiTietRepository;
import main.repository.SanPhamRepository;
import main.response.SanPhamChiTietRespone;

/**
 *
 * @author ADMIN
 */
public class ListSanPhamChiTiet extends javax.swing.JFrame {

    SanPhamRepository sprepo = new SanPhamRepository();
    private SanPhamChiTietRespone sp;
    SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();
    String id_tam = SanPhamForm.ma_sanpham;
    private DefaultTableModel dtm = new DefaultTableModel();
    public static String maSanPhamChiTiet;

    /**
     * Creates new form ListSanPhamChiTiet
     */
    public ListSanPhamChiTiet() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Danh sách sản phẩm chi tiết");
        showDataTable();

    }

    private void showDataTable() {

        Integer idSanPham = sprepo.getIDByMaSanPham(id_tam);
        ArrayList<SanPhamChiTietRespone> list = spctRepo.getSanPhamChiTietByIdSanPham(idSanPham);
        System.out.println("SPCT  " + list);
        if (list != null) {
            // Cập nhật thông tin sản phẩm
            dtm = (DefaultTableModel) tbl_SPCT.getModel();
            dtm.setRowCount(0);
            AtomicInteger index = new AtomicInteger(1);

            list.forEach(s -> {
                // Định dạng giá bán với ba chữ số thập phân
                String formattedGiaBan = String.format("%.3f", s.getGiaBan());

                dtm.addRow(new Object[]{
                    index.getAndIncrement(), s.getMaSPCT(), s.getTenSP(),
                    s.getThuongHieu(), s.getXuatXu(),
                    s.getMauSac(),
                    s.getKichThuoc(), s.getChatLieu(), s.getCoAo(), s.getDoDay(), s.getPhongCach(),
                    formattedGiaBan + " VND", // Định dạng giá bán với đơn vị VND
                    s.getSoLuong(),
                    s.isTrangThai() ? "Còn hàng" : "Hết Hàng"
                });
            });
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SPCT = new com.Product.GUI.Table();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tbl_SPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên SPCT", "Thương Hiệu", "Xuất Xứ", "Màu Sắc", "Kích Thước", "Chất Liệu", "Cổ Áo", "Độ Dày", "Phong Cách", "Giá Bán", "Số Lượng ", "Trạng Thái"
            }
        ));
        tbl_SPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SPCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SPCT);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thông Tin Sản Phẩm Chi Tiết");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(352, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tbl_SPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SPCTMouseClicked
        // TODO add your handling code here:
        int selectedRowIndex = tbl_SPCT.getSelectedRow();
        if (selectedRowIndex != -1) {
            // Giả sử cột thứ hai trong bảng là mã sản phẩm chi tiết
            maSanPhamChiTiet = (String) tbl_SPCT.getValueAt(selectedRowIndex, 1);
            System.out.println("ma: " + maSanPhamChiTiet);

            if (maSanPhamChiTiet != null && !maSanPhamChiTiet.isEmpty()) {

                ThongTinSPJFrame1 detailsFrame = new ThongTinSPJFrame1();
                detailsFrame.setVisible(true);

            } else {
                // Hiển thị thông báo lỗi nếu mã sản phẩm không hợp lệ
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Hiển thị thông báo lỗi nếu không có hàng nào được chọn
            JOptionPane.showMessageDialog(this, "Không có hàng nào được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tbl_SPCTMouseClicked

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
            java.util.logging.Logger.getLogger(ListSanPhamChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListSanPhamChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListSanPhamChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListSanPhamChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListSanPhamChiTiet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.Product.GUI.Table tbl_SPCT;
    // End of variables declaration//GEN-END:variables
}

package com.Product.form;

import com.Product.GUI.chart.ModelChart;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import main.config.DBConnect;
import main.entity.DataChart;
import main.entity.ThongKe;
import main.repository.ThongKeRepository;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThongKeForm extends javax.swing.JPanel {

    private DefaultTableModel dtm;
    private ThongKeRepository tkrepo;
    private DefaultComboBoxModel dcbmNam;

    public ThongKeForm() {
        initComponents();
        chart.setTitle("Thống Kê");
        chart.addLegend("Tổng tiền", Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.addLegend("Hóa Đơn", Color.decode("#e65c00"), Color.decode("#F9D423"));
        chart.addLegend("Khách Hàng", Color.decode("#58a5e8"), Color.decode("#0c74cf"));
        setOpaque(false);
        setData();
        dtm = (DefaultTableModel) tbThongKe.getModel();
        tkrepo = new ThongKeRepository();
        showDataThongKe(tkrepo.getAllThongKe());
//        dcbmNam = (DefaultComboBoxModel) cbbNam.getModel();
//        showCbbNam();
    }

    private void setData() {
        String sql = "SELECT \n"
                + "    MONTH(hd.ngay_cap_nhat) AS thang,\n"
                + "    SUM(hd.tong_tien) AS tong_tien_thang,\n"
                + "    COUNT(hd.id) AS tong_so_hoa_don,\n"
                + "    COUNT(DISTINCT id_khach_hang) AS so_luong_khach_hang\n"
                + "FROM \n"
                + "    HoaDon hd\n"
                + "WHERE \n"
                + "    YEAR(hd.ngay_cap_nhat) = 2024\n"
                + "GROUP BY \n"
                + "    MONTH(hd.ngay_cap_nhat)\n"
                + "ORDER BY \n"
                + "    thang;";
        List<DataChart> lists = new ArrayList<>();
        double doanhthu = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String month = rs.getString("thang");
                double tongtien = rs.getDouble("tong_tien_thang");
                int hoadon = rs.getInt("tong_so_hoa_don");
                int khachhang = rs.getInt("so_luong_khach_hang");
                doanhthu = doanhthu + tongtien;
                lists.add(new DataChart(month, tongtien, hoadon, khachhang));
            }
            rs.close();
            ps.close();
            // Add Data to chart
//            lists.sort();
            for (int i = lists.size() - 1; i >= 0; i--) {
                DataChart data = lists.get(i);
                chart.addData(new ModelChart(data.getMonth(), new double[]{data.getTongTien(), data.getHoadon(), data.getKhachhang()}));
            }
            lbDoanhThu.setText("" + doanhthu);
//            lbSoHD.setText("" +);
            // Start to show data with animation
            chart.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showDataThongKe(ArrayList<ThongKe> list_thongKe) {
        dtm.setRowCount(0);
        list_thongKe.forEach(tk -> dtm.addRow(new Object[]{
            tk.getTongTien(), tk.getSo_luong_hoa_don(), tk.getSo_luong_hoa_don_bi_huy(), tk.getSo_luong_khach_hang()
           }
         )
      );
    }
//     private void showCbbNam() {
//        cbbNam.removeAllItems();
//        tkrepo.searchNam().forEach(s -> cbbNam.addElement(s.getSo_luong_hoa_don()));
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbDoanhThu = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbSoHD = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbKH = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbSoHDHuy = new javax.swing.JLabel();
        panelShadow1 = new com.Product.GUI.chart.panel.PanelShadow();
        chart = new com.Product.GUI.chart.CurveLineChart();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbThongKe = new com.Product.GUI.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 700));

        jPanel2.setBackground(new java.awt.Color(255, 204, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Doanh Thu");

        lbDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDoanhThu.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(lbDoanhThu)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbDoanhThu)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Số HĐ");

        lbSoHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSoHD.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(lbSoHD)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSoHD)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 0));
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số Khách Hàng");

        lbKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbKH.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lbKH)
                        .addGap(91, 91, 91))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbKH)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 0));
        jPanel7.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số HĐ Hủy");

        lbSoHDHuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSoHDHuy.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(61, 61, 61))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(lbSoHDHuy)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSoHDHuy)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
        panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow1.setColorGradient(new java.awt.Color(34, 59, 69));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Xuất Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 2));

        tbThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Doanh Thu", "Hóa Đơn", "Hóa Đơn Hủy", "Khách Hàng"
            }
        ));
        tbThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThongKeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbThongKe);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(504, 504, 504)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(panelShadow1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addComponent(jButton1)
                .addGap(12, 12, 12)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet spreadsheet = workbook.createSheet("Thống Kê");

            // Tạo kiểu cho tiêu đề chính
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            XSSFFont titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            // Tạo kiểu cho tiêu đề cột
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Tạo kiểu cho các ô dữ liệu
            XSSFCellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Tiêu đề chính
            XSSFRow row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Thống Kê Doanh Thu");
            cell.setCellStyle(titleStyle);

            // Merge các ô cho tiêu đề chính
            spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 8));

            // Tiêu đề cột
            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            String[] headers = {"Doanh Thu", "Hóa Đơn", "Hóa Đơn Hủy", "Khách Hàng"};
            for (int i = 0; i < headers.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Độ rộng cột
            for (int i = 0; i < headers.length; i++) {
                spreadsheet.setColumnWidth(i, 4000);
            }

            // Lấy dữ liệu khách hàng
            ThongKeRepository thongkerepo = new ThongKeRepository();
            ArrayList<ThongKe> listItem = thongkerepo.getAllThongKe();

            // Thêm dữ liệu vào bảng
            for (int i = 0; i < listItem.size(); i++) {
                ThongKe tk = listItem.get(i);
                row = spreadsheet.createRow(4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(tk.getTongTien());
                row.createCell(1).setCellValue(tk.getSo_luong_hoa_don());
                row.createCell(2).setCellValue(tk.getSo_luong_hoa_don_bi_huy());
                row.createCell(3).setCellValue(tk.getSo_luong_khach_hang());
                for (int j = 0; j < headers.length; j++) {
                    row.getCell(j).setCellStyle(dataStyle);
                }
            }

            // Chọn vị trí và tên file để lưu
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

                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThongKeMouseClicked
        // TODO add your handling code here:
        int i = tbThongKe.getSelectedRow();
        lbDoanhThu.setText(tbThongKe.getValueAt(i, 0).toString());
        lbSoHD.setText(tbThongKe.getValueAt(i, 1).toString());
        lbSoHDHuy.setText(tbThongKe.getValueAt(i, 2).toString());
        lbKH.setText(tbThongKe.getValueAt(i, 3).toString());
    }//GEN-LAST:event_tbThongKeMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.GUI.chart.CurveLineChart chart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDoanhThu;
    private javax.swing.JLabel lbKH;
    private javax.swing.JLabel lbSoHD;
    private javax.swing.JLabel lbSoHDHuy;
    private com.Product.GUI.chart.panel.PanelShadow panelShadow1;
    private com.Product.GUI.Table tbThongKe;
    // End of variables declaration//GEN-END:variables
}

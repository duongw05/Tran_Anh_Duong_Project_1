 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HoaDonResponse1 {
    private Integer id;  
    
    private Integer idKH;
    
    private Integer idNV;
    
    private Integer id_vouCher;
    
    private Integer trangThai;
    
    private Double tongTien;
   
    private String maHoaDon;
    
    private String tenKhachHang;
    
    private String maNhanVien;

    private String hoTen;
    
    private String ngayTao;
    
    private Integer hinhThucThanhToan;
    
    private String maKH;

   private Double tienGiam;

}

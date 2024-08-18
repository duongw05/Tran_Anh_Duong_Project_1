/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.util.ArrayList;
import java.util.Date;
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
@AllArgsConstructor // contrutor full tham so 
@NoArgsConstructor // contrutor k tham so 
@Getter
@Setter
@ToString
@Builder // contructor tuy y tham so 
public class PhieuGiamGia1 {

    private int ID;
    private String Ma_Voucher;
    private Date Ngay_Bat_Dau;
    private Date Ngay_Het_Han;
    private String ten_Giam_Gia;
    private Double phan_Tram_Giam_Gia;
    private Double Gia_Tri_Don_Hang_Toi_Thieu;
    private Integer So_Lan_Su_Dung_Toi_Da;
    private Integer Trang_Thai;
    private String Mo_ta;
    
}

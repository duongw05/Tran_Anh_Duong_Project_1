/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.util.Date;

/**
 *
 * @author tungt
 */
public class NhanVienConnect {

    private String maNV, tenNV, matKhau, sdt, cccd;
    private boolean vaiTro,gioiTinh,trangThai;
    private String hinhAnh, email,diaChi;
    private Date ngaySinh;

    public NhanVienConnect() {
    }

    public NhanVienConnect(String maNV, String tenNV, String matKhau, String sdt, String cccd, boolean vaiTro, boolean gioiTinh, boolean trangThai, String hinhAnh, String email, String diaChi, Date ngaySinh) {
	this.maNV = maNV;
	this.tenNV = tenNV;
	this.matKhau = matKhau;
	this.sdt = sdt;
	this.cccd = cccd;
	this.vaiTro = vaiTro;
	this.gioiTinh = gioiTinh;
	this.trangThai = trangThai;
	this.hinhAnh = hinhAnh;
	this.email = email;
	this.diaChi = diaChi;
	this.ngaySinh = ngaySinh;
    }

    public String getMaNV() {
	return maNV;
    }

    public void setMaNV(String maNV) {
	this.maNV = maNV;
    }

    public String getTenNV() {
	return tenNV;
    }

    public void setTenNV(String tenNV) {
	this.tenNV = tenNV;
    }

    public String getMatKhau() {
	return matKhau;
    }

    public void setMatKhau(String matKhau) {
	this.matKhau = matKhau;
    }

    public String getSdt() {
	return sdt;
    }

    public void setSdt(String sdt) {
	this.sdt = sdt;
    }

    public String getCccd() {
	return cccd;
    }

    public void setCccd(String cccd) {
	this.cccd = cccd;
    }

    public boolean isVaiTro() {
	return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
	this.vaiTro = vaiTro;
    }

    public boolean isGioiTinh() {
	return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
	this.gioiTinh = gioiTinh;
    }

    public boolean isTrangThai() {
	return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
	this.trangThai = trangThai;
    }

    public String getHinhAnh() {
	return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
	this.hinhAnh = hinhAnh;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getDiaChi() {
	return diaChi;
    }

    public void setDiaChi(String diaChi) {
	this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
	return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
	this.ngaySinh = ngaySinh;
    }

    
    
}

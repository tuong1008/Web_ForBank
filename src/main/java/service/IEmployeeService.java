/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.NhanVien;

/**
 *
 * @author Tuong
 */
public interface IEmployeeService {
    List<NhanVien> getAll(HttpServletRequest req);
    NhanVien getOne(HttpServletRequest req, String maNV);
    public NhanVien getBySDTAndMaCN(HttpServletRequest req, String soDT, String maCN);
    String insertEmployee(HttpServletRequest req, String ho, String ten, String diaChi, String phai, 
                String soDT, String maCN, String pass, String role);
    String updateEmployee(HttpServletRequest req, String maNV, String ho, String ten, String diaChi, String phai, 
                String soDT);   
    String deleteEmployee(HttpServletRequest req, String maNV);
    
    String transferEmployee(HttpServletRequest req, String maNV,String serverChuyenDen, String maCNChuyenDen);
    
    String undoTransferEmployee(HttpServletRequest req, String soDT, String maCNHienTai,String serverChuyenDen, String maCNChuyenDen);
}   

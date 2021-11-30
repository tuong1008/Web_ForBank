/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Timestamp;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author Tuong
 */
public interface IEmployeeService {
    NhanVien findByUserNameAndPasswordAndStatus(String user, String password, int status);
    List<NhanVien> getAll();
    NhanVien getOne(String maNV);
    String insertEmployee(String ho, String ten, String diaChi, String phai, 
                String soDT, String maCN, String pass, String role);
    String updateEmployee(String maNV, String ho, String ten, String diaChi, String phai, 
                String soDT, String pass);
    String deleteEmployee(String maNV);
}   

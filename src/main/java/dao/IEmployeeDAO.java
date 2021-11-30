/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.NhanVien;

public interface IEmployeeDAO extends GenericDAO<NhanVien> {
    NhanVien findUserNameAndPasswordAndStatus(String userName, String password, int status);
    public List<NhanVien> getAll();
    public NhanVien getOne(String maNV);
    String insertEmployee(String ho, String ten, String diaChi, String phai, 
                String soDT, String maCN, String pass, String role);
    String updateEmployee(String maNV, String ho, String ten, String diaChi, String phai, 
                String soDT, String pass);
    String deleteEmployee(String maNV);
}

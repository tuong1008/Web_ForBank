/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import model.NhanVien;
import service.IEmployeeService;
import dao.IEmployeeDAO;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Tuong
 */
public class EmployeeService implements IEmployeeService{

    @Inject
    private IEmployeeDAO employeeDAO;
    
    @Override
    public NhanVien findByUserNameAndPasswordAndStatus(String user, String password, int status) {
        return employeeDAO.findUserNameAndPasswordAndStatus(user, password, status);
    }
    @Override
    public List<NhanVien> getAll(){
        return employeeDAO.getAll();
    }

    @Override
    public String insertEmployee(String ho, String ten, String diaChi, String phai, 
                String soDT, String maCN, String pass, String role) {
        return employeeDAO.insertEmployee(ho, ten, diaChi, phai, soDT, maCN, pass, role);
    }

    @Override
    public String updateEmployee(String maNV, String ho, String ten, String diaChi, String phai, String soDT, String pass) {
        return employeeDAO.updateEmployee(maNV, ho, ten, diaChi, phai, soDT, pass);
    }

    @Override
    public String deleteEmployee(String maNV) {
        return employeeDAO.deleteEmployee(maNV);
    }

    @Override
    public NhanVien getOne(String maNV) {
        return employeeDAO.getOne(maNV);
    }
}

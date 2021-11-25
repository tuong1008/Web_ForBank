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
                String soDT, String maCN, String lgName, String pass, String role) {
        return employeeDAO.insertEmployee(ho, ten, diaChi, phai, soDT, maCN, lgName, pass, role);
    }
}

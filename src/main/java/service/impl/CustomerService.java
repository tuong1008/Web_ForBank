/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.ICustomerDAO;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;
import model.KhachHang;
import service.ICustomerService;

/**
 *
 * @author Tuong
 */
public class CustomerService implements ICustomerService{

    @Inject
    ICustomerDAO customerDAO;
    
    @Override
    public List<KhachHang> getAll() {
        return customerDAO.getAll();
    }

    @Override
    public String insertCustomer(String CMND, String ho, String ten, String diaChi, String phai, Timestamp ngayCap, String soDT, String maCN, BigDecimal soDu) {
        return customerDAO.insertCustomer(CMND, ho, ten, diaChi, phai, ngayCap, soDT, maCN, soDu);
    }
    
}

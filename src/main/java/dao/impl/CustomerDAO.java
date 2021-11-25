/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.ICustomerDAO;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import mapper.CustomerMapper;
import model.KhachHang;

/**
 *
 * @author Tuong
 */
public class CustomerDAO extends AbstractDAO<KhachHang> implements ICustomerDAO{

    @Override
    public List<KhachHang> getAll() {
        return query("select * from KhachHang", new CustomerMapper());
    }

    @Override   //Thêm khách hàng và cấp tài khoản
    public String insertCustomer(String CMND, String ho, String ten, String diaChi, String phai, Timestamp ngayCap, String soDT, String maCN, BigDecimal soDu) {
        return crudAction("exec dbo.SP_INSERT_KHACHHANG ?,?,?,?,?,?,?,?,?;", CMND, ho, ten, diaChi, phai, ngayCap, soDT, maCN, soDu);
    }
    
}

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
        return crudAction(true, "exec dbo.SP_INSERT_KHACHHANG ?,?,?,?,?,?,?,?,?;", CMND, ho, ten, diaChi, phai, ngayCap, soDT, maCN, soDu);
    }

    @Override
    public String updateCustomer(String CMND, String ho, String ten, String diaChi, String phai, Timestamp ngayCap, String soDT) {
        return crudAction(false, "UPDATE KhachHang\n" +
        "SET HO = ?, TEN = ?, DIACHI = ?, PHAI = ?, NGAYCAP=?, SODT = ?\n" +
        "WHERE CMND=?;", ho, ten, diaChi, phai, ngayCap, soDT, CMND);
    }

    @Override
    public String deleteCustomer(String CMND) {
        return crudAction(true, "exec dbo.SP_DELETE_KHACHHANG ?;", CMND);
    }

    @Override
    public KhachHang getOne(String cmnd) {
        return query("select * from KhachHang where CMND = ?", new CustomerMapper(), cmnd).get(0);
    }
    
}

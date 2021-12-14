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
import javax.servlet.http.HttpServletRequest;
import mapper.CustomerMapper;
import model.KhachHang;

/**
 *
 * @author Tuong
 */
public class CustomerDAO extends AbstractDAO<KhachHang> implements ICustomerDAO{

    @Override
    public List<KhachHang> getAll(HttpServletRequest req) {
        return query(req, "select * from KhachHang", new CustomerMapper());
    }

    @Override   //Thêm khách hàng và cấp tài khoản
    public String insertCustomer(HttpServletRequest req, String CMND, String ho, String ten, String diaChi, String phai, Timestamp ngayCap, String soDT, String maCN, BigDecimal soDu) {
        return crudAction(req, true,true, "exec dbo.SP_INSERT_KHACHHANG ?,?,?,?,?,?,?,?,?;", CMND, ho, ten, diaChi, phai, ngayCap, soDT, maCN, soDu);
    }

    @Override
    public String updateCustomer(HttpServletRequest req, String CMND, String ho, String ten, String diaChi, String phai, Timestamp ngayCap, String soDT) {
        return crudAction(req, false,true, "UPDATE KhachHang\n" +
        "SET HO = ?, TEN = ?, DIACHI = ?, PHAI = ?, NGAYCAP=?, SODT = ?\n" +
        "WHERE CMND=?;", ho, ten, diaChi, phai, ngayCap, soDT, CMND);
    }

    @Override
    public KhachHang getOne(HttpServletRequest req, String cmnd) {
        return query(req, "select * from KhachHang where CMND = ?", new CustomerMapper(), cmnd).get(0);
    }

    @Override
    public List<KhachHang> thongKeKH(HttpServletRequest req) {
        return query(req, "select * from dbo.THONGKE_KH()", new CustomerMapper());
    }
    
}

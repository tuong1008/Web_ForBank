/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import model.KhachHang;

/**
 *
 * @author Tuong
 */
public interface ICustomerDAO extends GenericDAO<KhachHang>{
    public List<KhachHang> getAll();
    String insertCustomer(String CMND, String ho, String ten, String diaChi, 
                    String phai, Timestamp ngayCap, String soDT, String maCN, BigDecimal soDu);
}

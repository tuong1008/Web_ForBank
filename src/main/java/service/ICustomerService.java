/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import model.KhachHang;

/**
 *
 * @author Tuong
 */
public interface ICustomerService {
    List<KhachHang> getAll();
    KhachHang getOne(String cmnd);
    String insertCustomer(String CMND, String ho, String ten, String diaChi, String phai, Timestamp ngayCap, String soDT, String maCN, BigDecimal soDu);
    String updateCustomer(String CMND, String ho, String ten, String diaChi, 
                    String phai, Timestamp ngayCap, String soDT);
    String deleteCustomer(String CMND);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import model.TaiKhoan;

/**
 *
 * @author Tuong
 */
public interface IAccountDAO extends GenericDAO<TaiKhoan>{
    public List<TaiKhoan> getAll();
    public String insertAccount(String soTK, String CMND, BigDecimal soDu, String maCN, Timestamp ngayMoTK);
}

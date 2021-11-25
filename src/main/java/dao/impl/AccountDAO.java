/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.IAccountDAO;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import mapper.AccountMapper;
import model.TaiKhoan;

/**
 *
 * @author Tuong
 */
public class AccountDAO extends AbstractDAO<TaiKhoan> implements IAccountDAO {

    @Override
    public List<TaiKhoan> getAll() {
        return query("select * from TaiKhoan", new AccountMapper());
    }

    @Override
    public String insertAccount(String soTK, String CMND, BigDecimal soDu, String maCN, Timestamp ngayMoTK) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

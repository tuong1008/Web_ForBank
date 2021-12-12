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
import javax.servlet.http.HttpServletRequest;
import mapper.AccountMapper;
import model.TaiKhoan;

/**
 *
 * @author Tuong
 */
public class AccountDAO extends AbstractDAO<TaiKhoan> implements IAccountDAO {

    @Override
    public List<TaiKhoan> getAll(HttpServletRequest req) {
        return query(req, "select * from TaiKhoan", new AccountMapper());
    }

    @Override
    public String deleteAccount(HttpServletRequest req, String soTK) {
        return crudAction(req, true,true, "exec dbo.SP_DELETE_TAIKHOAN ?;", soTK);
    }

    @Override
    public TaiKhoan getByCMNDAndMaCN(HttpServletRequest req, String cmnd, String maCN) {
        return query(req, "select * from TaiKhoan where CMND=? and maCN = ?", new AccountMapper(),cmnd, maCN).get(0);
    }

    @Override
    public TaiKhoan getOne(HttpServletRequest req, String soTK) {
        return query(req, "select * from TaiKhoan where SOTK = ?", new AccountMapper(), soTK).get(0);
    }
    
}

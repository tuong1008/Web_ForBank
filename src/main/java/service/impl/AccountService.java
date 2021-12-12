/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IAccountDAO;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import model.TaiKhoan;
import service.IAccountService;

/**
 *
 * @author Tuong
 */
public class AccountService implements IAccountService{

    @Inject
    IAccountDAO accountDAO;
    
    @Override
    public List<TaiKhoan> getAll(HttpServletRequest req) {
        return accountDAO.getAll(req);
    }

    @Override
    public String deleteAccount(HttpServletRequest req, String soTK) {
        return  accountDAO.deleteAccount(req, soTK);
    }

    @Override
    public TaiKhoan getByCMNDAndMaCN(HttpServletRequest req, String cmnd, String maCN) {
        return accountDAO.getByCMNDAndMaCN(req, cmnd, maCN);
    }

    @Override
    public TaiKhoan getOne(HttpServletRequest req, String soTK) {
        return accountDAO.getOne(req, soTK);
    }
    
}

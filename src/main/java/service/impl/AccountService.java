/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IAccountDAO;
import java.util.List;
import javax.inject.Inject;
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
    public List<TaiKhoan> getAll() {
        return accountDAO.getAll();
    }

    @Override
    public String insertAccount(TaiKhoan acc) {
        return  accountDAO.insertAccount(acc.getSoTK(), acc.getCMND(), acc.getSoDu(), acc.getMaCN(), acc.getNgayMoTK());
    }
    
}

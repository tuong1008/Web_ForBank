/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IDepositWithdrawDAO;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;
import model.GD_GoiRut;
import service.IDepositWithdrawService;

/**
 *
 * @author Tuong
 */
public class DepositWithdrawService implements IDepositWithdrawService{

    @Inject
    IDepositWithdrawDAO depositWithdrawDAO;
    
    @Override
    public List<GD_GoiRut> getAll() {
        return depositWithdrawDAO.getAll();
    }

    @Override
    public String insertDepositWithdraw(GD_GoiRut trans){
        return depositWithdrawDAO.insertDepositWithdraw(trans.getSoTK(), 
                trans.getLoaiGD(), trans.getSoTien(), trans.getMaNV());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IMoneyTransferDAO;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;
import model.GD_ChuyenTien;
import service.IMoneyTransferService;

/**
 *
 * @author Tuong
 */
public class MoneyTransferService implements IMoneyTransferService{
    
    @Inject
    IMoneyTransferDAO moneyTransferDAO;

    @Override
    public List<GD_ChuyenTien> getAll() {
        return moneyTransferDAO.getAll();
    }

    @Override
    public String insertMoneyTransfer(GD_ChuyenTien trans) {
        return moneyTransferDAO.insertMoneyTransfer(trans.getSoTK_Chuyen(),
                trans.getSoTien(), trans.getSoTK_Nhan(), trans.getMaNV());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.IMoneyTransferDAO;
import java.math.BigDecimal;
import java.util.List;
import mapper.MoneyTransferMapper;
import model.GD_ChuyenTien;

/**
 *
 * @author Tuong
 */
public class MoneyTransferDAO extends AbstractDAO<GD_ChuyenTien> implements IMoneyTransferDAO{

    @Override
    public List<GD_ChuyenTien> getAll() {
        return query("select * from GD_CHUYENTIEN", new MoneyTransferMapper());
    }

    @Override
    public String insertMoneyTransfer(String soTK_Chuyen, BigDecimal soTien, String soTK_Nhan, String maNV) {
        return crudAction("exec SP_INSERT_GD_CHUYENTIEN ?, ?, ?, ?",
                soTK_Chuyen, soTien, soTK_Nhan, maNV);
    }
    
}

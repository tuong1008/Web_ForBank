/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import model.GD_GoiRut;

/**
 *
 * @author Tuong
 */
public interface IDepositWithdrawService {
    List<GD_GoiRut> getAll();
    String insertDepositWithdraw(GD_GoiRut trans);
}

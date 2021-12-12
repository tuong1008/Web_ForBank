/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.TaiKhoan;

/**
 *
 * @author Tuong
 */
public interface IAccountDAO extends GenericDAO<TaiKhoan>{
    public List<TaiKhoan> getAll(HttpServletRequest req);
    public TaiKhoan getOne(HttpServletRequest req, String soTK);
    public TaiKhoan getByCMNDAndMaCN(HttpServletRequest req, String cmnd, String maCN);
    public String deleteAccount(HttpServletRequest req, String soTK);
}

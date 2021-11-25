/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.IBranchDAO;
import java.util.List;
import mapper.BranchMapper;
import model.ChiNhanh;

/**
 *
 * @author Tuong
 */
public class BranchDAO extends AbstractDAO<ChiNhanh> implements IBranchDAO{

    @Override
    public List<ChiNhanh> getAll() {
        return query("select * from ChiNhanh", new BranchMapper());
    }

    @Override
    public String insertBranch(String maCN, String tenCN, String diaChi, String soDT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

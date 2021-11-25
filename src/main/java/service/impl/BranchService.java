/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IBranchDAO;
import java.util.List;
import javax.inject.Inject;
import model.ChiNhanh;
import service.IBranchService;

/**
 *
 * @author Tuong
 */
public class BranchService implements IBranchService{

    @Inject
    IBranchDAO branchDAO;
    
    @Override
    public List<ChiNhanh> getAll() {
        return branchDAO.getAll();
    }
    
}

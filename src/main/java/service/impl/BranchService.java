/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IBranchDAO;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
    public List<ChiNhanh> getAll(HttpServletRequest req) {
        return branchDAO.getAll(req);
    }
    
}

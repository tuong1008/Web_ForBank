/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IUserDAO;
import java.util.List;
import javax.inject.Inject;
import model.User;
import service.IUserService;

/**
 *
 * @author Tuong
 */
public class UserService implements IUserService{

    @Inject
    IUserDAO userDAO;
    @Override
    public User loginAndChangeServer(String serverName, String user, String password) {
        User userInfo=userDAO.loginAndChangeServer(serverName, user, password);
        if (userInfo==null){
            System.out.println("Sai tai khoan, mat khau");
            return null;
        }
        else if (userInfo.getHoTen()==null){
            System.out.println("Tai khoan da bi xoa");
            return null;
        }
        else{
            userInfo.setServerName(serverName);
            userInfo.setUser(user);
            userInfo.setPassword(password);
            
            return userInfo;
        }
    }

    @Override
    public String insertLogin(String loginName, String password, String userName, String role) {
        String result = userDAO.insertLogin(loginName, password, userName, role);
        if (result!=null){
            return "Thêm login thất bại!";
        }
        else{
            return "Thêm login thành công!";
        }
    }
    
}

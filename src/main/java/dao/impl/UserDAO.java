/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import constant.SystemConstant;
import dao.IUserDAO;
import java.util.List;
import mapper.UserMapper;
import model.User;

/**
 *
 * @author Tuong
 */
public class UserDAO extends AbstractDAO<User> implements IUserDAO{

    @Override
    public User loginAndChangeServer(String serverName, String user, String password) {
        SystemConstant.serverName = serverName;
        SystemConstant.user = user;
        SystemConstant.password = password;
        List<User> users= query("exec dbo.SP_DANGNHAP ?", new UserMapper(), user);
        if (users==null){
            //restore to server0
            SystemConstant.serverName = resourceBundle.getString("serverName");
            SystemConstant.user = resourceBundle.getString("user");
            SystemConstant.password = resourceBundle.getString("password");
            return null;
        }
        else{
            return users.get(0);
        }
    }

    @Override
    public String insertLogin(String loginName, String password, String userName, String role) {
        return crudAction("exec dbo.SP_TAOLOGIN ?,?,?,?", loginName, password, userName, role);
    }

    
    
}

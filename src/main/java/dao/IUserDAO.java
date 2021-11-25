/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;

/**
 *
 * @author Tuong
 */
public interface IUserDAO extends GenericDAO<User>{
    public User loginAndChangeServer(String serverName, String user, String password);
    public String insertLogin(String loginName, String password, String userName, String role);
}

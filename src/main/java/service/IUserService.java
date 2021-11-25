/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.User;

/**
 *
 * @author Tuong
 */
public interface IUserService {
    public User loginAndChangeServer(String serverName, String user, String password);
    public String insertLogin(String loginName, String password, String userName, String role);
}

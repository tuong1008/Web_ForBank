/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.servlet.http.HttpServletRequest;
import model.User;

/**
 *
 * @author Tuong
 */
public interface IUserService {
    public User loginAndChangeServer(HttpServletRequest req, String serverName, String user, String password);
    public User getOne(HttpServletRequest req, String user);
    public String insertLogin(HttpServletRequest req, String loginName, String password, String userName, String role);
    public String updatePassword(HttpServletRequest req, String oldPassword, String password, String maNV);
}

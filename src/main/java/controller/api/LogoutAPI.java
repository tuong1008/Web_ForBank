/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.api;

import constant.SystemConstant;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import service.IUserService;

/**
 *
 * @author Tuong
 */
@WebServlet(urlPatterns = {"/api-logout"})
public class LogoutAPI extends HttpServlet{

    @Inject
    IUserService userService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        SystemConstant.serverName = resourceBundle.getString("serverName");
        SystemConstant.user = resourceBundle.getString("user");
        SystemConstant.password = resourceBundle.getString("password");
        User userInfo=userService.loginAndChangeServer(SystemConstant.serverName, SystemConstant.user, SystemConstant.password);
        req.getSession().removeAttribute("user");
        
        JsonGenerator generator = Json.createGenerator(resp.getOutputStream());
        
            generator.writeStartObject()
                    .write("message", "Đăng xuất thành công!")
                    .writeEnd();
            generator.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import constant.SystemConstant;
import static dao.impl.AbstractDAO.resourceBundle;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PhanManh;
import model.User;
import service.ISubscriberService;

/**
 *
 * @author Tuong
 */
@WebServlet(urlPatterns = {"/api-subscriber"})
public class SubscriberAPI extends HttpServlet{

    @Inject
    ISubscriberService userService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        HttpSession session = request.getSession();
        User currentUser =(User) session.getAttribute("userInfo");
        if (request.getParameter("action")!=null){
            String currentSub = ((User) request.getSession().getAttribute("userInfo")).getMaCN();
            List<PhanManh> subscribers=userService.findOtherSubscribers(request, currentSub);
            mapper.writeValue(response.getOutputStream(), subscribers);
        }
        else{
           List<PhanManh> subscribers=userService.findAllSubscribers(request);
            SystemConstant.subscribersMap = subscribers.stream()
            .collect(Collectors.toMap(PhanManh::getMaCN, Function.identity()));
            
           mapper.writeValue(response.getOutputStream(), subscribers);   
        }
        if (currentUser!=null){
            session.setAttribute("serverName", currentUser.getServerName());
            session.setAttribute("user", currentUser.getUser());
            session.setAttribute("password", currentUser.getPassword());
        }
    }
    
}

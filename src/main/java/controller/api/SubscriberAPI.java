/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PhanManh;
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
        List<PhanManh> subscribers=userService.findAllSubscribers();
        mapper.writeValue(response.getOutputStream(), subscribers);
    }
    
}

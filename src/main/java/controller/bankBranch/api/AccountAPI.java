/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bankBranch.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TaiKhoan;
import service.IAccountService;
import utils.HttpUtil;

/**
 *
 * @author Tuong
 */
@WebServlet(urlPatterns = {"/api-account"})
public class AccountAPI extends HttpServlet{
    @Inject
    IAccountService accountService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        List<TaiKhoan> accs = accountService.getAll();
        mapper.writeValue(response.getOutputStream(), accs);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		TaiKhoan acc =  HttpUtil.of(req.getReader()).toModel(TaiKhoan.class);
        String messageAfterInsert = accountService.insertAccount(acc);
		JsonGenerator generator = Json.createGenerator(resp.getOutputStream());
        if (messageAfterInsert==null){
            messageAfterInsert = "Thêm thành công!";
        }
            generator.writeStartObject()
                    .write("message", messageAfterInsert)
                    .writeEnd();
            generator.close();
    }
    
    
    
}

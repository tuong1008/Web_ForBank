/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bankBranch.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.NhanVien;
import model.User;
import service.IEmployeeService;
import utils.HttpUtil;

/**
 *
 * @author Tuong
 */
@WebServlet(urlPatterns = {"/api-employee"})
public class EmployeeAPI extends HttpServlet{  
    
    @Inject
    IEmployeeService employeeService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
                String maNV = request.getParameter("maNV");
                if (maNV!=null){
                    NhanVien employeeModel = employeeService.getOne(maNV);
                    mapper.writeValue(response.getOutputStream(), employeeModel);
                }
                else{
                    List<NhanVien> employeeModels = employeeService.getAll();
                    mapper.writeValue(response.getOutputStream(), employeeModels);
                }
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        
        HttpSession session = req.getSession();
        User createBy =(User) session.getAttribute("user");
        System.out.println("Add employee session: "+ session.getId());
        JsonReader rdr = Json.createReader(req.getInputStream());
        JsonObject obj = rdr.readObject();
        String ho = obj.getJsonString("ho").getString();
        String ten = obj.getJsonString("ten").getString();
        String diaChi = obj.getJsonString("diaChi").getString();
        String phai = obj.getJsonString("phai").getString();
        String soDT = obj.getJsonString("soDT").getString();
        String maCN = createBy.getMaCN();
        String pass = obj.getJsonString("pass").getString();
        String role = createBy.getTenNhom();
        
        String messageAfterInsert = employeeService.insertEmployee(ho, ten, diaChi,
                phai, soDT, maCN, pass, role);
        
        JsonGenerator generator = Json.createGenerator(resp.getOutputStream());
        
        if (messageAfterInsert==null){
            messageAfterInsert = "Thêm thành công!";
        }
            generator.writeStartObject()
                    .write("message", messageAfterInsert)
                    .writeEnd();
            generator.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        
        JsonReader rdr = Json.createReader(req.getInputStream());
        JsonObject obj = rdr.readObject();
        String maNV = obj.getJsonString("maNV").getString();
        String ho = obj.getJsonString("ho").getString();
        String ten = obj.getJsonString("ten").getString();
        String diaChi = obj.getJsonString("diaChi").getString();
        String phai = obj.getJsonString("phai").getString();
        String soDT = obj.getJsonString("soDT").getString();
        String pass = obj.getJsonString("pass").getString();
        
        String messageAfterInsert = employeeService.updateEmployee(maNV, ho, ten, diaChi,
                phai, soDT, pass);
        
        JsonGenerator generator = Json.createGenerator(resp.getOutputStream());
        
        if (messageAfterInsert==null){
            messageAfterInsert = "Thêm thành công!";
        }
            generator.writeStartObject()
                    .write("message", messageAfterInsert)
                    .writeEnd();
            generator.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        
        JsonReader rdr = Json.createReader(req.getInputStream());
        JsonObject obj = rdr.readObject();
        String maNV = obj.getJsonString("maNV").getString();
        
        String messageAfterInsert = employeeService.deleteEmployee(maNV);
        
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

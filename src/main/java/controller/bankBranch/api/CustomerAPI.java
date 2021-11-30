/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bankBranch.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import constant.SystemConstant;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.KhachHang;
import model.TaiKhoan;
import model.User;
import service.ICustomerService;
import utils.HttpUtil;

/**
 *
 * @author Tuong
 */
@WebServlet(urlPatterns = {"/api-customer"})
public class CustomerAPI extends HttpServlet{
    @Inject
    ICustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String cmnd = req.getParameter("cmnd");
        if (cmnd != null){
            KhachHang cus = customerService.getOne(cmnd);
            mapper.writeValue(resp.getOutputStream(), cus);
        }
        else{
            List<KhachHang> cuss = customerService.getAll();
            mapper.writeValue(resp.getOutputStream(), cuss);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
        JsonReader rdr = Json.createReader(req.getInputStream());
        JsonObject obj = rdr.readObject();
        
        String cmnd = obj.getJsonString("cmnd").getString();
        String ho = obj.getJsonString("ho").getString();
        String ten = obj.getJsonString("ten").getString();
        String diaChi = obj.getJsonString("diaChi").getString();
        String phai = obj.getJsonString("phai").getString();
        String strNgayCap = obj.getJsonString("ngayCap").getString();
        Timestamp ngayCap=null;
        try {
            ngayCap = new Timestamp(SystemConstant.ddMMyyyy.parse(strNgayCap).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        String soDT = obj.getJsonString("soDT").getString();
        String maCN =((User) req.getSession().getAttribute("user")).getMaCN();
        BigDecimal soDu = new BigDecimal(obj.getJsonString("soDu").getString());
        
        String messageAfterInsert = customerService.insertCustomer(cmnd, ho, ten, diaChi,
                phai, ngayCap, soDT, maCN, soDu);
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
        
        String cmnd = obj.getJsonString("cmnd").getString();
        String ho = obj.getJsonString("ho").getString();
        String ten = obj.getJsonString("ten").getString();
        String diaChi = obj.getJsonString("diaChi").getString();
        String phai = obj.getJsonString("phai").getString();
        String strNgayCap = obj.getJsonString("ngayCap").getString();
        Timestamp ngayCap=null;
        try {
            ngayCap = new Timestamp(SystemConstant.ddMMyyyy.parse(strNgayCap).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        String soDT = obj.getJsonString("soDT").getString();
        
        String messageAfterInsert = customerService.updateCustomer(cmnd, ho, ten, diaChi,
                phai, ngayCap, soDT);
		JsonGenerator generator = Json.createGenerator(resp.getOutputStream());
        if (messageAfterInsert==null){
            messageAfterInsert = "Cập nhật thành công!";
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
        
        String cmnd = obj.getJsonString("cmnd").getString();
        
        String messageAfterInsert = customerService.deleteCustomer(cmnd);
		JsonGenerator generator = Json.createGenerator(resp.getOutputStream());
        if (messageAfterInsert==null){
            messageAfterInsert = "Xoá thành công!";
        }
            generator.writeStartObject()
                    .write("message", messageAfterInsert)
                    .writeEnd();
            generator.close();
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import model.NhanVien;
import dao.IEmployeeDAO;
import java.sql.Timestamp;
import java.util.List;
import mapper.EmployeeMapper;

/**
 *
 * @author Tuong
 */
public class EmployeeDAO extends AbstractDAO<NhanVien> implements IEmployeeDAO{

    @Override
    public NhanVien findUserNameAndPasswordAndStatus(String userName, String password, int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NhanVien> getAll() {
        return query("select * from NhanVien", new EmployeeMapper());
    }

    @Override
    public String insertEmployee(String ho, String ten, String diaChi, String phai, 
                String soDT, String maCN, String pass, String role) {
        return crudAction(true, "exec dbo.SP_INSERT_NHANVIEN ?, ?, ?, ?, ?, ?, ?, ?;",
                ho, ten, diaChi, phai, soDT, maCN, pass, role);
    }

    @Override
    public String updateEmployee(String maNV, String ho, String ten, String diaChi, String phai, String soDT, String pass) {
        return crudAction(true, "exec dbo.SP_UPDATE_NHANVIEN ?, ?, ?, ?, ?, ?, ?;",
                                maNV, ho, ten, diaChi, phai, soDT, pass);
    }

    @Override
    public String deleteEmployee(String maNV) {
        return crudAction(true, "exec dbo.SP_DELETE_NHANVIEN ?;", maNV);
    }

    @Override
    public NhanVien getOne(String maNV) {
        return query("select * from NhanVien where MANV=?", new EmployeeMapper(), maNV).get(0);
    }

    
}

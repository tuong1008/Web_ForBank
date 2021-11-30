/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.List;
import mapper.RowMapper;

public interface GenericDAO<T>  {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    String crudAction (boolean isStoredProcedured, String sql, Object... parameters);
    int count (String sql, Object... parameters);
    ResultSet query(String sql, Object... parameters);
}

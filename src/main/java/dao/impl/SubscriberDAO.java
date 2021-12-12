/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.util.List;
import mapper.SubscriberMapper;
import model.PhanManh;
import dao.ISubscriberDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tuong
 */
public class SubscriberDAO extends AbstractDAO<PhanManh> implements ISubscriberDAO{

    @Override
    public List<PhanManh> findAllSubscribers(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("serverName", resourceBundle.getString("serverName"));
        session.setAttribute("user", resourceBundle.getString("user"));
        session.setAttribute("password", resourceBundle.getString("password"));
        return query(req, "select * from dbo.V_DS_PHANMANH", new SubscriberMapper());
    }

    @Override
    public List<PhanManh> findOtherSubscribers(HttpServletRequest req, String currentSub) {
        List<PhanManh> results = new ArrayList<>();
        Connection connection = AbstractDAO.getConnection(resourceBundle.getString("serverName"), resourceBundle.getString("user"),
                                                                resourceBundle.getString("password"));
        try {
            PreparedStatement statement = connection.prepareStatement("select * from dbo.V_DS_PHANMANH where MACN<>?");
            setParameter(statement, currentSub);
            ResultSet resultSet = statement.executeQuery();
            SubscriberMapper mapper = new SubscriberMapper();
            while (resultSet.next()) {
                results.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return results;
    }
}

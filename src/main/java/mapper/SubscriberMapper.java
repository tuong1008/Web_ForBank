/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.PhanManh;

/**
 *
 * @author Tuong
 */
public class SubscriberMapper implements RowMapper<PhanManh>{

    @Override
    public PhanManh mapRow(ResultSet rs){
        try {
            PhanManh pm = new PhanManh();
            pm.setTenCN(rs.getString(1));
            pm.setTenServer(rs.getString(2));
            return pm;
        } catch (SQLException e) {
            return null;
        }
    }
    
}

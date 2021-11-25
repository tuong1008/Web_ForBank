/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import constant.SystemConstant;
import java.util.List;
import mapper.SubscriberMapper;
import model.PhanManh;
import dao.ISubscriberDAO;
import java.util.ResourceBundle;

/**
 *
 * @author Tuong
 */
public class SubscriberDAO extends AbstractDAO<PhanManh> implements ISubscriberDAO{

    @Override
    public List<PhanManh> findAllSubscribers() {
        SystemConstant.serverName = resourceBundle.getString("serverName");
        SystemConstant.user = resourceBundle.getString("user");
        SystemConstant.password = resourceBundle.getString("password");
        return query("select * from dbo.V_DS_PHANMANH", new SubscriberMapper());
    }
}

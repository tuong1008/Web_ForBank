/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.PhanManh;

/**
 *
 * @author Tuong
 */
public interface ISubscriberDAO extends GenericDAO<PhanManh>{
    public List<PhanManh> findAllSubscribers(HttpServletRequest req);
    public List<PhanManh> findOtherSubscribers(HttpServletRequest req, String currentSub);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.PhanManh;

/**
 *
 * @author Tuong
 */
public interface ISubscriberService {
    List<PhanManh> findAllSubscribers(HttpServletRequest req);
    public List<PhanManh> findOtherSubscribers(HttpServletRequest req, String currentSub);
}

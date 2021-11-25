/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import java.util.List;
import javax.inject.Inject;
import model.PhanManh;
import service.ISubscriberService;
import dao.ISubscriberDAO;

/**
 *
 * @author Tuong
 */
public class SubscriberService implements ISubscriberService{

    @Inject
    ISubscriberDAO subscriberDAO;
    
    @Override
    public List<PhanManh> findAllSubscribers() {
        return subscriberDAO.findAllSubscribers();
    }
    
}

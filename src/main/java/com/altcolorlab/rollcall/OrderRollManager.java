/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.altcolorlab.rollcall;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author justink
 */
public class OrderRollManager {
    

    public OrderRollManager(){

}
    
    public String searchOrder(String orderroll, int length){
      SessionFactory sf = HibernateUtil.getSessionFactory();   
      Session session = sf.openSession();
      Transaction tx = null;
      String orderRoll = null;
       try{
         tx = session.beginTransaction();
         orderRoll=orderroll;
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
     return orderRoll;
      
    }





}
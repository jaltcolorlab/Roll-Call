/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.altcolorlab.rollcall;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author justink
 */
public class OrderRollManager {
    List orderrolls;

    public OrderRollManager(){

}
    
    public void searchOrder(String orderroll){
      SessionFactory sf = HibernateUtil.getSessionFactory();   
      Session session = sf.openSession();
      Transaction tx = null;
       try{
         tx = session.beginTransaction();
         Query query = session.createQuery("SELECT DISTINCT orderroll FROM prfimages WHERE orderroll LIKE ?1");
         query.setParameter(1, orderroll+"%");
         //orderrolls = query.;
         System.out.println("Session open");
         tx.commit();
      }catch (HibernateException e) {
         e.printStackTrace();
         tx.rollback();
         System.out.println("It happened here.");
      }finally {
         session.close(); 
      }
      
     //return orderrolls;
      
    }

public void testMethod(String string){
    System.out.println(string);
}



}
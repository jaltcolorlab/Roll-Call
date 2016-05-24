/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.altcolorlab.rollcall;




/**
 *
 * @author justink
 */

public class OrderRoll{
   private int id;
   private String orderroll;
 
   public OrderRoll() {}
   public OrderRoll(int ID, String orderroll){
       this.id=ID;
       this.orderroll=orderroll;
   }
   public int getid(){
    return id;   
   }
   public void setid(int id){
       this.id=id;
   }
   public String getorderroll() {
      return orderroll;
   }
   public void setorderroll( String orderroll ) {
      this.orderroll = orderroll;
   }

}

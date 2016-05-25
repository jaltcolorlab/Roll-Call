
package com.altcolorlab.rollcall;




/**
 *
 * @author justink
 */
//the Java class that will be used to map the Java Object to the relating columns in the db table.
public class OrderRoll{
   private int prfimageid;
   private String orderroll;
 //constructor must be empty
   public OrderRoll() {}
 //method which takes in all the variables that are to be get/set. Getters and setters must be as close to the tables column in naming convention as possible to avoid errors.
   public OrderRoll(int ID, String orderroll){
       this.prfimageid=ID;
       this.orderroll=orderroll;
   }
   public int getprfimageid(){
    return prfimageid;   
   }
   public void setprfimageid(int id){
       this.prfimageid=id;
   }
   public String getorderroll() {
      return orderroll;
   }
   public void setorderroll( String orderroll ) {
      this.orderroll = orderroll;
   }

}

package com.altcolorlab.rollcall;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FXMLController implements Initializable {
    
    @FXML
    private Label searchMessage;
    @FXML
    private TextField tfRollNum;
    @FXML
    private Button searchButton;
    @FXML
    int order;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //disabling the search button if the Textfield is empty
        if(tfRollNum.getText()==null || tfRollNum.getText().trim().isEmpty()){
            searchButton.setDisable(true);
        }
        else{    
        searchButton.setDisable(false);
        }
        order = Integer.parseInt(tfRollNum.getText());
        //padding left with 0 if number is less than 6 digits
        String stringResult = String.format("%06d", order);
        //clearing the text field
        tfRollNum.clear();
        //retrieving the current hibernate session from HibernateUtil.java
        SessionFactory sf = HibernateUtil.getSessionFactory();   
        //opening session
        Session session = sf.openSession();
        //setting hibernate transaction to null
        Transaction tx = null;
            try{
                //System.out.println("Inside the try");
                tx = session.beginTransaction();
                //using native MYSql query through the current open Hibernate session
                Query query = session.createQuery("SELECT DISTINCT orderroll FROM OrderRoll WHERE orderroll LIKE ?");
                //setting the parameters for the query with stringResult needing to have the last digit a wildcard. First parameter must be 0 since the Query Object is 0 based.
                query.setParameter(0, stringResult+"%");
                //the results from the sql query are put into a list
                List result = query.list();
                //checking to see if the list is empty or not and then writing to the Javafx Label searchMessage accordingly.
                if(result.isEmpty()){
                    searchMessage.setText("That order is NOT being used");
                }
                else if(result.isEmpty()==false){
                    searchMessage.setText(stringResult+" is currently being used");
                }
                //commiting, thus ending the current transaction
                tx.commit();
                //catching the exception
                }catch (HibernateException e) {
                //rolling back the transaction if the transaction does not equal null    
                if (tx!=null) tx.rollback();
                e.printStackTrace(); 
                }finally {
                //closing the Hibernate session. This is imperative for the ActionEvent in Jafafx to "reset" itself
                session.close();
              }
            
    }
    @FXML
    public void handleEnterPressed(KeyEvent event){
     if(tfRollNum.getText()==null || tfRollNum.getText().trim().isEmpty()){
            searchButton.setDisable(true);
        }
    else{
      searchButton.setDisable(false);
    if (event.getCode() == KeyCode.ENTER) {
        order = Integer.parseInt(tfRollNum.getText());
        //adding a zero to front of TextField entry if the number entered is only 5 digits
        String stringResult = String.format("%06d", order);
        //clearing TextField
        tfRollNum.clear();
        //retrieving the current hibernate session from HibernateUtil.java
        SessionFactory sf = HibernateUtil.getSessionFactory();   
        //opening session
        Session session = sf.openSession();
        //setting hibernate transaction to null
        Transaction tx = null;
            try{
                //System.out.println("Inside the try");
                tx = session.beginTransaction();
                //using native MYSql query through the current open Hibernate session
                Query query = session.createQuery("SELECT DISTINCT orderroll FROM OrderRoll WHERE orderroll LIKE ?");
                //setting the parameters for the query with stringResult needing to have the last digit a wildcard. First parameter must be 0 since the Query Object is 0 based.
                query.setParameter(0, stringResult+"%");
                //putting the result of the sql query into a list
                List result = query.list();
                System.out.println(result);
                //checking to see if the list is empty or not and then writing to the Javafx Label searchMessage accordingly.
                if(result.isEmpty()){
                    searchMessage.setText(stringResult+" is NOT being used");
                }
                else if(result.isEmpty()==false){
                    searchMessage.setText(stringResult+" is currently being used");
                }
                //commiting, thus ending the current transaction
                tx.commit();
                //catching the exception
                }catch (HibernateException e) {
                //rolling back the transaction if the transaction does not equal null    
                if (tx!=null) tx.rollback();
                e.printStackTrace(); 
                }finally {
                //closing the Hibernate session. This is imperative for the ActionEvent in Jafafx to "reset" itself
                session.close();
              }
    }
  }
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //initializing the maxLength variable to 7 and then passing that as a parameter to the Listener along with the TextField so that the number entered cannot exceed 7 digits.  
      int maxLength=7;  
      tfRollNum.textProperty().addListener(new ChangeListener(tfRollNum, maxLength));
      
    }
    
}

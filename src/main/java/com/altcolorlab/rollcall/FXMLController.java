package com.altcolorlab.rollcall;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
    Integer order;
    OrderRollManager searchOrder;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(tfRollNum.getText()==null || tfRollNum.getText().trim().isEmpty()){
            searchButton.setDisable(true);
        }
        else{
        searchButton.setDisable(false);
        order = Integer.parseInt(tfRollNum.getText());
        //padding left with 0 if number is less than 6 digits
        String stringResult = String.format("%06d", order);
        tfRollNum.clear();
        SessionFactory sf = HibernateUtil.getSessionFactory();   
        Session session = sf.openSession();
        Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Query query = session.createQuery("SELECT DISTINCT orderroll FROM prfimages WHERE orderroll LIKE ?1");
                query.setParameter(1, stringResult+"%");
                System.out.println("event worked");
                tx.commit();
                }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace(); 
                }finally {
                session.close(); 
              }
        //searchMessage.setText("The result of your query is: "+result);
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
        String result = String.format("%06d", order);
        tfRollNum.clear();
        searchMessage.setText("The result of your query is: "+result);
    }
  }
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      int maxLength=7;  
      tfRollNum.textProperty().addListener(new ChangeListener(tfRollNum, maxLength));  
    }
    
}

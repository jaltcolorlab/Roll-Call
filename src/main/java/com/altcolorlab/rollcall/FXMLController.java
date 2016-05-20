package com.altcolorlab.rollcall;

import java.net.URL;
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
        Integer rollLength = tfRollNum.getLength();
        String tfInput = tfRollNum.getText();
        order = Integer.parseInt(tfInput);
        //padding left with 0 if number is less than 6 digits
        String result = String.format("%06d", order);
        tfRollNum.clear();
        result = searchOrder.searchOrder(result, rollLength);
        searchMessage.setText("The result of your query is: "+result);
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
        //Integer inputLength = tfRollNum.getLength();
        String tfInput = tfRollNum.getText();
        order = Integer.parseInt(tfInput);
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

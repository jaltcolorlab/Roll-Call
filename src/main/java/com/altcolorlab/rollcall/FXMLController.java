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
        searchOrder.searchOrder(stringResult);
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

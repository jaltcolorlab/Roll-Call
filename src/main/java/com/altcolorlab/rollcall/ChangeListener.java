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

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;





public class ChangeListener implements javafx.beans.value.ChangeListener<String> {

    
        private int maxLength;
        private TextField textField;
        
        public ChangeListener(TextField textField, int maxLength){
            this.textField=textField;
            this.maxLength=maxLength;
            
        }
        
        public int getMaxLength(){
            return maxLength;
        }
        
        @Override
        public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue){
        //check if the value is null then return
        if (newValue == null){
            return;
        }
        //check the length of new value length is less than defined max length then set the new value to the text field, else keep old value in text field
        if(newValue.length() > maxLength){
            textField.setText(oldValue);
        }else if(newValue.length() == 5){
            textField.setText(0+oldValue);
        }
        else{
            textField.setText(newValue);
        }
        } 
    }
    
    
    


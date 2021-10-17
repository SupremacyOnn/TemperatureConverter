package com.example.temperatureconverter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private TextField userInputField;

    @FXML
    private Button convertButton;

    private  static  final String C_TO_F ="Celsius to Fahrenheit";
    private  static  final String F_TO_C ="Fahrenheit to Celsius";
    private boolean isC_TO_F=true;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add(C_TO_F);
        choiceBox.getItems().add(F_TO_C);
        choiceBox.setValue(C_TO_F);
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if(t1.equals(C_TO_F)){
                isC_TO_F=true;
            }else {
                isC_TO_F=false;
            }
            
        });
        convertButton.setOnAction(actionEvent -> {
            convert();
        });
    }

    private void convert() {
        String input = userInputField.getText();
        float enteredTemp=0.0f;
        try {
            enteredTemp = Float.parseFloat(input);
        }catch (Exception e){
            warnUser();
            return;
        }
        float newtemp = 0.0f;
        if(isC_TO_F){
            newtemp=(enteredTemp*9/5)+32;
        }else {
            newtemp=(enteredTemp-32)*5/9;
        }
        display(newtemp);
    }

    private void warnUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please enter a valid temperature.");
        alert.show();
    }

    private void display(float newtemp) {
        String unit = isC_TO_F?"F":"C";
        System.out.println(newtemp+" "+unit);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new temperature is : "+newtemp+" "+unit);
        alert.show();
    }
}

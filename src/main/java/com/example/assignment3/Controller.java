package com.example.assignment3;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private Button btnSubmit;
    @FXML
    private Label Welcome;
    @FXML
    private static TextField textField;

    int N;

    public void Submit(ActionEvent actionEvent) {

        try {
            N = Integer.parseInt(textField.getText());
            if (N <= 26) {
                Welcome.setText("Working on it!!");
            } else {
                Welcome.setText("N must be at most 26!!");
            }
        } catch (NumberFormatException e) {
            Welcome.setText("Enter only numbers pls");
        } catch (Exception e) {
            Welcome.setText("error");
        }
    }

    public int getN () {
        return N;
    }


}

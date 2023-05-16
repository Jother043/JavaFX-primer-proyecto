package com.company.administracion_negocio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    protected void onHelloButtonClick2() {

    }

    public void onByeButtonClick(ActionEvent actionEvent) {

    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.addProduct(new Stage());

    }
}
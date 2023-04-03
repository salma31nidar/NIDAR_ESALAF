package com.example.esalaf1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Login;
import models.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField username ;
    @FXML
    private PasswordField password;
    @FXML
    private Label MessageLabel;


    public  void loginButton(ActionEvent event) throws SQLException, IOException {
        LoginDAO connected = new LoginDAO();
        Login login = connected.checklogin(new Login(username.getText(), password.getText()));

        Alert alert;
        if (username.getText().isBlank() || password.getText().isBlank()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else if (login == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Votre Username ou Password est incorrect!");
            alert.showAndWait();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 2000, 2000);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        }
    }

    public void inscrire(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 600 , 400);
        stage.setScene(scene);

        stage.show();
    }


}





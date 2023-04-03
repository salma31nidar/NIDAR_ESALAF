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
import models.Register;
import models.RegisterDAO;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField email ;
    @FXML
    private TextField username ;
    @FXML
    private PasswordField password;
    @FXML
    private Label MessageLabel;
    @FXML
    private PasswordField password1;

    public  void RegisterButton(ActionEvent event) throws SQLException, IOException {
        RegisterDAO inscrit = new RegisterDAO();
            Alert alert;
        if (username.getText().isBlank() || password.getText().isBlank() || email.getText().isBlank() || password1.getText().isBlank()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Remplissez tous les champs");
            alert.showAndWait();
        } else if (((!password.getText().equals(password1.getText())))){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Votre confirmation de mot de passe est fausse");
            alert.showAndWait();
        }
        else {
            inscrit.AddToLogin(new Register(username.getText(),password.getText(),email.getText()));

            Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root , 600 , 400);
            stage.setScene(scene);

            stage.show();
        }}

    public void Seconnecter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewLogin-.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 600 , 400);
        stage.setScene(scene);

        stage.show();
    }
}

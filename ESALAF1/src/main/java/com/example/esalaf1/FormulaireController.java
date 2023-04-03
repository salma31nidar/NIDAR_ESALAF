package com.example.esalaf1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;
import java.sql.SQLException;

public class FormulaireController {
    @FXML
    private TextField fullname ;
    @FXML
    private TextField nom_produit ;
    @FXML
    private TextField montant_restant ;
    @FXML
    private TextField total_credit ;
    @FXML
    private TextField telephone ;
    @FXML
    private Label message ;


    public  void AjouterButton(ActionEvent event) throws SQLException, IOException {
        creditDAO Ajout = new creditDAO();
        ProduitsDAO produitsDAO = new ProduitsDAO();
        Credit C = new Credit();
        int ID_pro = produitsDAO.getID(nom_produit.getText());
        Alert alert;
        if (fullname.getText().isBlank() || nom_produit.getText().isBlank() || montant_restant.getText().isBlank() || total_credit.getText().isBlank() || telephone.getText().isBlank()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Remplissez tous les champs");
            alert.showAndWait();
        } else if (((ID_pro ==0))){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Ce produit n'existe pas");
            alert.showAndWait();
        }
        else {
            Ajout.AddToCredit(new Credit(fullname.getText(),telephone.getText(),nom_produit.getText(),total_credit.getText(),montant_restant.getText()));
            message.setText("done");
        }}

    public void RetourButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AllCredit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}

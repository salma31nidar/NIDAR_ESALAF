package com.example.esalaf1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;
import java.sql.SQLException;

public class CommandeController {
    @FXML
    private TableView<Client> tableCommande ;
    @FXML
    private TableColumn<Client,String> fullname ;
    @FXML
    private TableColumn<Client,String> total_credit ;
    @FXML
    int selectedProduitId ;
    @FXML
    String selectedProduitNom;
    @FXML
    private Label message ;

    public String getSelectedProduitNom() {
        return selectedProduitNom;
    }

    public void setSelectedProduitNom(String selectedProduitNom) {
        this.selectedProduitNom = selectedProduitNom;
    }

    public int getSelectedProduitId() {
        return selectedProduitId;
    }

    public void setSelectedProduitId(int selectedProduitId) {
        this.selectedProduitId = selectedProduitId;
    }
@FXML
    public void TableCommande() throws SQLException {
        fullname.setCellValueFactory(new PropertyValueFactory<Client,String>("fullname"));
    total_credit.setCellValueFactory(new PropertyValueFactory<Client,String>("total_credit"));
        tableCommande.setItems(getDataCommande());

    }
    @FXML
    public ObservableList<Client> getDataCommande() throws SQLException {
        ClientDAO client1 = new ClientDAO();
        ObservableList<Client> listfx = FXCollections.observableArrayList();

        for(Client client : client1.Commande(selectedProduitId))
        {
            System.out.println(selectedProduitId);
            listfx.add(client);
        }
        return listfx;
    }

    public void ProduitsButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProduitView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 1000 , 1000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void deconnectedButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 600 , 400);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public void ClientButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 1000 , 1000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public void RetourButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProduitView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.setMaximized(true);
    }
    public void CreditButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AllCredit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 1000 , 1000);
        stage.setScene(scene);

        stage.show();
    }

    public void setMessage() {
        message.setText("les Credits pour le produit " + selectedProduitNom);
    }

    public void DshboardButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 1000 , 1000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}

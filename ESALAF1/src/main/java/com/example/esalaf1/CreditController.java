package com.example.esalaf1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import models.Client;
import models.ClientDAO;
import models.Credit;
import models.creditDAO;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreditController implements Initializable {
    @FXML
    private TableColumn<Client, Button> button;
    @FXML
    private TableView<Credit> tableCredit;
    @FXML
    private TableColumn<Credit, String> fullname1;
    @FXML
    private TableColumn<Credit, String> produitsC;
    @FXML
    private TableColumn<Credit, String> montant;
    @FXML
    private TableColumn<Credit, String> total;
    @FXML
    private Label message;

    private String selectedClientNom;

    public String getSelectedClientNom() {
        return selectedClientNom;
    }

    public void setSelectedClientNom(String selectedClientNom) {
        this.selectedClientNom = selectedClientNom;
    }

    @FXML
    int selectedClientId;
    private Connection connection;

    public int getSelectedClientId() {
        return selectedClientId;
    }

    public void setSelectedClientId(int selectedClientId) {
        this.selectedClientId = selectedClientId;
    }

    @FXML
    public void TableCredit() throws SQLException {
        tableCredit.setEditable(true);
        montant.setEditable(true);
        fullname1.setCellValueFactory(new PropertyValueFactory<Credit, String>("fullname"));
        produitsC.setCellValueFactory(new PropertyValueFactory<Credit, String>("nom_produit"));
        total.setCellValueFactory(new PropertyValueFactory<Credit, String>("total_credit"));
        montant.setCellValueFactory(new PropertyValueFactory<Credit, String>("montant_restant"));
        montant.setCellFactory(TextFieldTableCell.forTableColumn());

        tableCredit.setItems(getCreditClient());
    }

    @FXML
    public ObservableList<Credit> getCreditClient() throws SQLException {
        creditDAO creditDAO = new creditDAO();
        ObservableList<Credit> listfx = FXCollections.observableArrayList();

        for (Credit credit : creditDAO.CreditClient(selectedClientId)) {

            System.out.println(selectedClientId);
            listfx.add(credit);
        }
        return listfx;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Credit> credits = getCreditClient();
            tableCredit.setItems(credits);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteButton() throws SQLException {
        ClientDAO clientDAO = new ClientDAO();

        if (clientDAO.deleteClient(selectedClientId)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Attention tous les credits et les informations de votre client seront supprimes ");
            alert.showAndWait();
            TableCredit();
        }
    }


    public void EditMontant(TableColumn.CellEditEvent<Credit, String> creditStringCellEditEvent) throws SQLException {
        creditDAO creditDAO = new creditDAO();
        Credit credit = tableCredit.getSelectionModel().getSelectedItem();
        credit.setMontant_restant(creditStringCellEditEvent.getNewValue());
        creditDAO.UpDateMontant(creditStringCellEditEvent.getNewValue(), selectedClientId);

    }

    public void ClientButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 2000, 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void ProduitsButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProduitView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 2000, 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void deconnectedButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void retourButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 2000, 2000);
        stage.setMaximized(true);
        stage.setScene(scene);
    }

    public void CreditButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AllCredit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 2000, 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void getWelcomeMessage(String selectedClientNom) {
        message.setText("Les Informations de " + selectedClientNom);

    }

    public void DshboardButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    }







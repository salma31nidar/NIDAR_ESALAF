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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import models.Client;
import models.Credit;
import models.creditDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AllcreditController implements Initializable {
    @FXML
    private TableView<Credit> TableCredit ;
    @FXML
    private TableColumn<Credit,String> fullname ;
    @FXML
    private TableColumn<Credit,String> telephone ;
    @FXML
    private  TableColumn<Credit,Integer> ID_credit;
    @FXML
    private TableColumn<Credit,String> nom_produit ;
    @FXML
    private TableColumn<Credit,String> total_credit ;
    @FXML
    private TableColumn<Credit,String> montant ;
    @FXML
    private int selectedClientIdCredit;

    public int getSelectedClientFullname() {
        return selectedClientIdCredit;
    }

    public void setSelectedClientFullname(int  selectedClientFullname) {
        this.selectedClientIdCredit = selectedClientFullname;
    }

    @FXML
    public void TableCredit() throws SQLException {
        TableCredit.setEditable(true);
        montant.setEditable(true);
        fullname.setCellValueFactory(new PropertyValueFactory<Credit, String>("fullname"));
        nom_produit.setCellValueFactory(new PropertyValueFactory<Credit, String>("nom_produit"));
        telephone.setCellValueFactory(new PropertyValueFactory<Credit, String>("telephone"));
        total_credit.setCellValueFactory(new PropertyValueFactory<Credit, String>("total_credit"));
        montant.setCellValueFactory(new PropertyValueFactory<Credit, String>("montant_restant"));
        ID_credit.setCellValueFactory(new PropertyValueFactory<Credit, Integer>("ID_credit"));
        montant.setCellFactory(TextFieldTableCell.forTableColumn());
        TableCredit.setItems(getDataCredit());
    }




    public ObservableList<Credit> getDataCredit() throws SQLException {
        creditDAO creditDAO = new creditDAO();
        ObservableList<Credit> listfx = FXCollections.observableArrayList();
        List<Credit> credits = creditDAO.AllCredit();
        for (Credit credit : credits) {
            listfx.add(credit);
        }
        return listfx;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            TableCredit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }








    public void ClientButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();}
    public void ProduitsButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProduitView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
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
    @FXML
    public void EditMontant2(TableColumn.CellEditEvent<Credit, String> creditStringCellEditEvent) throws SQLException {
        Credit creditSelected = TableCredit.getSelectionModel().getSelectedItem();
        creditSelected.setMontant_restant( creditStringCellEditEvent.getNewValue());
        System.out.println(creditSelected.toString());

        creditDAO creditModel = new creditDAO();
        creditModel.UpDateMontant2(creditSelected.getMontant_restant(),creditSelected.getID_credit());

    }
    public void Add_credit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Formulaire.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
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


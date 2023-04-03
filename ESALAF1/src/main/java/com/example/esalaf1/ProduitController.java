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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private  TextField prix_produit;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<Produits> tableProduit ;
    @FXML
    private TableColumn<Produits, String> nom_produit ;
    @FXML
    private TableColumn<Produits, String> prix ;
    @FXML
    private TableColumn<Produits, Integer> ID_produit ;
    @FXML
    private TableColumn<Produits,Button> button ;


    @FXML
    public void TableProduit() throws SQLException {
        ID_produit.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("ID_produit"));
        nom_produit.setCellValueFactory(new PropertyValueFactory<Produits,String>("nom_produit"));
        prix.setCellValueFactory(new PropertyValueFactory<Produits,String>("prix"));
        button.setCellValueFactory(new PropertyValueFactory<Produits,Button>("button"));

        tableProduit.setItems(getDataProduits());

    }

    @FXML
    public ObservableList<Produits> getDataProduits() throws SQLException {
        ProduitsDAO produitsDAO = new ProduitsDAO();
        ObservableList<Produits> listfx = FXCollections.observableArrayList();

        for (Produits produits : produitsDAO.AfficheProduits()) {
            Button button = new Button("Consulter Les Achats ");
            produits.setButton(button);
            button.setOnAction(event -> {
                Produits selectedProduit = produits;
                int selectedProduitId = selectedProduit.getID_produit();
                System.out.println(selectedProduitId);
                String selectedProduitNom = selectedProduit.getNom_produit();
                listfx.add(produits);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeView.fxml"));
                    Parent root = loader.load();
                    CommandeController controller = loader.getController();
                    controller.setSelectedProduitId(selectedProduitId);
                    controller.setSelectedProduitNom(selectedProduitNom);
                    controller.TableCommande();
                    controller.setMessage();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1000, 1000);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
            listfx.add(produits);
        }
            return listfx;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            TableProduit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deconnectedButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 600 , 400);
        stage.setScene(scene);

        stage.show();
    }

    public void ClientButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 1000 , 1000);
        stage.setScene(scene);

        stage.show();
    }

    public  void AddProduct(ActionEvent event) throws SQLException, IOException {
        ProduitsDAO ProAjoute = new ProduitsDAO();

        Alert alert;
        if (nom.getText().isBlank() || prix_produit.getText().isBlank()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            ProAjoute.AddProduit(new Produits(nom.getText(),prix_produit.getText()));

            TableProduit();
        }
    }

    public ObservableList<Produits> deleteButton() throws SQLException {
        ProduitsDAO ProdCher = new ProduitsDAO();
        ObservableList<Produits> listfx = FXCollections.observableArrayList();

        if (nom.getText().isBlank()) {
            messageLabel.setText("veuillez saisir le nom de produit souhaite a supprime");
        } else {

            boolean produits = ProdCher.deleteProduit(nom.getText());
            if (produits  = true) {
                messageLabel.setText("votre produit est supprime ");
               TableProduit();

            }else {
                messageLabel.setText("Aucun client trouvé avec ce nom.");
            }
        }
        return listfx;
    }
    public void CreditButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AllCredit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 1000 , 1000);
        stage.setScene(scene);

        stage.show();
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

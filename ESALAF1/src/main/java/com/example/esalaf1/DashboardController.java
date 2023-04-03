package com.example.esalaf1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import models.Credit;
import models.Produits;
import models.ProduitsDAO;
import models.creditDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    @FXML
    private BarChart statistic1;
    @FXML
    private BarChart static2;

    public void ClientButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ClientDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public void ProduitsButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProduitView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 3000 , 3000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void deconnected(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 600 , 400);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public void CreditButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AllCredit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String , Integer> series = new XYChart.Series<>();
        XYChart.Series<String , Integer> series1 = new XYChart.Series<>();

        try {
            creditDAO creditmodel = new creditDAO();
            ProduitsDAO produitModel = new ProduitsDAO();
            List<Produits> produitStat = produitModel.getDataForStat();
            for (Produits ProduitsItems : produitStat){
                series.getData().add(new XYChart.Data<>(ProduitsItems.getNom_produit(), ProduitsItems.getNombre_Total_Achat()));
            }
            statistic1.getData().addAll(series);

            List<Credit> listStat2 = creditmodel.getStat();





        }
        catch (SQLException e)

        {
            throw new RuntimeException();
        }
    }

    public void esalaf(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 2000 , 2000);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}

package com.example.esalaf1;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Client;
import models.ClientDAO;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;




public class ClientController implements Initializable {
    @FXML
    private  TextField fullnameTextFiled;
    @FXML
    private  TextField CliCherche;
    @FXML
    private Label InformationMessage;
    @FXML
    private  TextField telephoneTextFiled;
    @FXML
    private TableView<Client> TableClient ;
    @FXML
    private TableColumn<Client,Integer> ID_client ;
    @FXML
    private TableColumn<Client,Button> button ;
    @FXML
    private TableColumn<Client,String> Fullname ;
    @FXML
    private TableColumn<Client,String> telephone ;
    @FXML
    private Button credit ;

    @FXML
    private Label MessageLabel;

@FXML
    public void TableClient() throws SQLException {
        ID_client.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id_client"));
        Fullname.setCellValueFactory(new PropertyValueFactory<Client,String>("fullname"));
        telephone.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
        button.setCellValueFactory(new PropertyValueFactory<Client,Button>("button"));
        TableClient.setItems(getDataClients());

    }
    public void TableRecherche() throws SQLException {
        ID_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        Fullname.setCellValueFactory(new PropertyValueFactory<Client,String>("fullname"));
        telephone.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
        button.setCellValueFactory(new PropertyValueFactory<Client,Button>("button"));
        TableClient.setItems(SearchButton());

    }

    public ObservableList<Client> getDataClients() throws SQLException {
        ClientDAO clientModel = new ClientDAO();
        ObservableList<Client> listfx = FXCollections.observableArrayList();

        for (Client listItems : clientModel.AfficheClient()) {
            Button button = new Button("consulter credit");
            listItems.setButton(button);
            button.setOnAction(event -> {
                Client selectedClient = listItems;
                int selectedClientId = selectedClient.getId_client();
                String selectedClientNom = selectedClient.getFullname();
                System.out.println(selectedClientNom);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditView.fxml"));
                    Parent root = loader.load();
                    CreditController controller = loader.getController();
                    controller.setSelectedClientId(selectedClientId);
                    controller.setSelectedClientNom(selectedClientNom);
                    controller.getWelcomeMessage(selectedClientNom);
                    controller.TableCredit();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 2000, 2000);
                    stage.setMaximized(true);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            listfx.add(listItems);
        }
        return listfx;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Client> clients = getDataClients();
            TableClient();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void initialize1(URL url, ResourceBundle resourceBundle) {
        try {
            SearchButton();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    public ObservableList<Client> SearchButton() throws SQLException {
        ClientDAO cliCherche = new ClientDAO();
        ObservableList<Client> listfx = FXCollections.observableArrayList();

        if (CliCherche.getText().isBlank()) {
            InformationMessage.setText("veuillez saisir le nom de client souhaite a cherche");
        } else {
            List<Client> clients = cliCherche.SearchPerson(CliCherche.getText());
            if (!clients.isEmpty()) {
                for (Client client : clients) {
                    Button button = new Button("consulter credit");
                    client.setButton(button);
                    listfx.add(client);

                    button.setOnAction(event -> {
                        Client selectedClient = client;
                        int selectedClientId = selectedClient.getId_client();
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditView.fxml"));
                            Parent root = loader.load();
                            CreditController controller = loader.getController();
                            controller.setSelectedClientId(selectedClientId);

                            controller.TableCredit();

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root, 1000, 1000);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException | SQLException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }
            } else {
                InformationMessage.setText("Aucun client trouvé avec ce nom.");
            }
        }
        return listfx;
    }


        public  void AddButton(ActionEvent event) throws SQLException, IOException {
        ClientDAO cliAjoute = new ClientDAO();

        if (fullnameTextFiled.getText().isBlank() || telephoneTextFiled.getText().isBlank()){
            MessageLabel.setText("veuillez remplir les champs ");
        }
        else {
            cliAjoute.AddClient(new Client(fullnameTextFiled.getText(),telephoneTextFiled.getText()));

            TableClient();
        }}


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
    public void CreditButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AllCredit.fxml"));
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




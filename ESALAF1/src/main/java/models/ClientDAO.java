package models;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends BaseDAO<Client> {


    public ClientDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Client object) throws SQLException {

    }

    @Override
    public void update(Client object) throws SQLException {

    }

    @Override
    public void delete(Client object) throws SQLException {

    }

    @Override
    public Client getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAll(Client object) throws SQLException {
        List<Client> mylist = new ArrayList<Client>();
        String req = "SELECT * FROM client" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new Client(this.resultSet.getInt("id_client"),this.resultSet.getString("fullname"),
                                    this.resultSet.getString("telephone")));


        }

        return mylist;

    }

    public List<Client> AfficheClient() throws SQLException {

        List<Client> clients = new ArrayList<Client>();
        String afficher = "SELECT * FROM client";


        this.preparedStatement = this.connection.prepareStatement(afficher);
        this.resultSet = this.preparedStatement.executeQuery();

        Client client = null;
        while (this.resultSet.next()) {
            int id_Client = this.resultSet.getInt("id_client");
            String fullname = this.resultSet.getString("fullname");
            String telephone = this.resultSet.getString("telephone");
            client = new Client(id_Client,fullname, telephone);
            clients.add(client);
        }

        return  clients;

    }



    public void AddClient(Client object ) throws SQLException {
        String ajoute = "insert into client ( fullname , telephone) values ( ? , ?) ;";

        this.preparedStatement = this.connection.prepareStatement(ajoute);

        this.preparedStatement.setString(1 , object.getFullname());
        this.preparedStatement.setString(2 , object.getTelephone());

        this.preparedStatement.execute();
    }


    public List<Client> SearchPerson(String fullname) throws SQLException {

        String chercher = "SELECT * FROM client WHERE fullname = ?";
        this.preparedStatement = this.connection.prepareStatement(chercher);
        this.preparedStatement.setString(1, fullname);
        this.resultSet = this.preparedStatement.executeQuery();
        List<Client> clients = new ArrayList<>();

        while (this.resultSet.next()) {
            int id_Client = this.resultSet.getInt("id_client");
            String fullName = this.resultSet.getString("fullname");
            String telephone = this.resultSet.getString("telephone");
            Client client = new Client(id_Client, fullName, telephone);
            clients.add(client);
        }
        return clients;
    }


    public  List<Client> Commande(int ID_produit) throws SQLException {

        List<Client> Client = new ArrayList<>();
        String req = "SELECT DISTINCT c.fullname, cr.total_credit FROM client c JOIN credit cr ON c.Id_client = cr.ID_client JOIN produits p ON cr.ID_produit = ?;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1, ID_produit);
        this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next()) {
            Client.add(
                    new Client(
                            this.resultSet.getString("fullname"),
                            this.resultSet.getInt("total_credit")

                    ));

        }

        return Client;
    }
    public boolean deleteClient(int ID_client) throws SQLException {
        String req = "DELETE FROM client WHERE ID_client = ?;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setInt(1,ID_client);
        this.preparedStatement.execute();


        return true;
    }
}



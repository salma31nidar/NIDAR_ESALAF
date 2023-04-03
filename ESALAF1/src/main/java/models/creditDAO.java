package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class creditDAO extends BaseDAO{
    public creditDAO() throws SQLException {
        super();
    }


    /**
     * @param object
     * @throws SQLException
     */
    @Override
    public void save(Object object) throws SQLException {

    }

    /**
     * @param object
     * @throws SQLException
     */
    @Override
    public void update(Object object) throws SQLException {

    }

    /**
     * @param object
     * @throws SQLException
     */
    @Override
    public void delete(Object object) throws SQLException {

    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Object getOne(Long id) throws SQLException {
        return null;
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List getAll() throws SQLException {
        return null;
    }

    /**
     * @param object
     * @return
     * @throws SQLException
     */
    @Override
    public List<Client> getAll(Client object) throws SQLException {
        return null;
    }
    public List<Credit> CreditClient(int ID_client) throws SQLException {

        List<Credit> credits = new ArrayList<Credit>();
        String creditClient = "SELECT c.*,  p.nom_produit, cl.fullname FROM credit c INNER JOIN produits p ON c.ID_produit = p.ID_produit INNER JOIN client cl ON c.ID_client = cl.ID_client WHERE c.ID_client =?";

        this.preparedStatement = this.connection.prepareStatement(creditClient);
        this.preparedStatement.setInt(1, ID_client);
        this.resultSet= this.preparedStatement.executeQuery();

        while (this.resultSet.next()){
            credits.add(
                    new Credit(
                            this.resultSet.getString("fullname"),
                            this.resultSet.getString("nom_produit"),
                            this.resultSet.getString("total_credit"),
                            this.resultSet.getString("montant_restant")
                    ));

        }

return credits;
}


    public void UpDateMontant(String montant_restant, int ID_client) throws SQLException {
        String query = "UPDATE credit SET montant_restant = ? WHERE ID_client = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setString(1, montant_restant);
        this.preparedStatement.setInt(2, ID_client);
        this.preparedStatement.executeUpdate();
        this.connection.setAutoCommit(false);
        this.connection.commit();

    }
    public List<Credit> AllCredit() throws SQLException {
        List<Credit> credits = new ArrayList<Credit>();
        String req = "SELECT  cr.ID_credit ,c.fullname, c.telephone, p.nom_produit, cr.total_credit, cr.montant_restant  FROM client c JOIN credit cr ON c.id_client = cr.id_client JOIN produits p ON p.ID_produit = cr.ID_produit;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.resultSet = this.preparedStatement.executeQuery();

        Credit credit = null;
        while (this.resultSet.next()) {
            int ID_credit= this.resultSet.getInt("ID_credit");
            String fullname = this.resultSet.getString("fullname");
            String telephone = this.resultSet.getString("telephone");
            String nom_produit = this.resultSet.getString("nom_produit");
            String total_credit = this.resultSet.getString("total_credit");
            String montant_restant = this.resultSet.getString("montant_restant");

            credit = new Credit(ID_credit,fullname, telephone, nom_produit,total_credit,montant_restant);
            credits.add(credit);
        }
        return credits;
    }
    public void UpDateMontant2(String montant_restant, int ID_credit) throws SQLException {
        String query = "UPDATE credit cr JOIN client c ON c.id_client = cr.id_client SET cr.montant_restant = ? WHERE cr.ID_credit = ?;";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setString(1, montant_restant);
        this.preparedStatement.setInt(2, ID_credit);
        this.preparedStatement.execute();



    }
    public void AddToCredit(Credit object) throws SQLException {
        // check if client already exists
        int id_client = -1;
        String req1 = "SELECT id_client FROM client WHERE fullname = ?";
        this.preparedStatement = this.connection.prepareStatement(req1);
        this.preparedStatement.setString(1, object.getFullname());
        this.resultSet = this.preparedStatement.executeQuery();
        if (this.resultSet.next()) {
            id_client = this.resultSet.getInt("id_client");
        } else {
            // insert new client
            String req2 = "INSERT INTO client (fullname, telephone) VALUES (?, ?)";
            this.preparedStatement = this.connection.prepareStatement(req2, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, object.getFullname());
            this.preparedStatement.setString(2, object.getTelephone());
            this.preparedStatement.executeUpdate();
            ResultSet generatedKeys = this.preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_client = generatedKeys.getInt(1);
            }
        }

        // check if product already exists
        int id_produit = -1;
        String req3 = "SELECT ID_produit FROM produits WHERE nom_produit = ?";
        this.preparedStatement = this.connection.prepareStatement(req3);
        this.preparedStatement.setString(1, object.getNom_produit());
        this.resultSet = this.preparedStatement.executeQuery();
        if (this.resultSet.next()) {
            id_produit = this.resultSet.getInt("ID_produit");
        } else {
            // insert new product
            String req4 = "INSERT INTO produits (nom_produit) VALUES (?)";
            this.preparedStatement = this.connection.prepareStatement(req4, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, object.getNom_produit());
            this.preparedStatement.executeUpdate();
            ResultSet generatedKeys = this.preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_produit = generatedKeys.getInt(1);
            }
        }

        // insert new credit
        String req5 = "INSERT INTO credit (id_client, id_produit, total_credit, montant_restant) VALUES (?, ?, ?, ?)";
        this.preparedStatement = this.connection.prepareStatement(req5);
        this.preparedStatement.setInt(1, id_client);
        this.preparedStatement.setInt(2, id_produit);
        this.preparedStatement.setString(3, object.getTotal_credit());
        this.preparedStatement.setString(4, object.getMontant_restant());
        this.preparedStatement.executeUpdate();
    }

    public List<Credit> getStat() throws SQLException{
        List<Credit> list = new ArrayList<Credit>();
        String req = "SELECT DISTINCT client.fullname, SUM(credit.total_credit) AS total_credit FROM client INNER JOIN credit ON client.ID_client = credit.ID_client GROUP BY client.ID_client;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.resultSet = this.preparedStatement.executeQuery();
        while(this.resultSet.next()){
            list.add(new Credit(this.resultSet.getString("fullname") , this.resultSet.getString("total_credit")) );
        }
        return list;
    }





}

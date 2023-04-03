package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitsDAO extends BaseDAO {
    public ProduitsDAO() throws SQLException {
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

    public List<Produits> AfficheProduits() throws SQLException {

        List<Produits> produits = new ArrayList<Produits>();
        String afficherProduit = "SELECT * FROM produits";


        this.preparedStatement = this.connection.prepareStatement(afficherProduit);
        this.resultSet = this.preparedStatement.executeQuery();

        Produits produit = null;
        while (this.resultSet.next()) {
            int ID_produit = this.resultSet.getInt("ID_produit");
            String nom_produit = this.resultSet.getString("nom_produit");
            String prix = this.resultSet.getString("prix");
            produit = new Produits(ID_produit, nom_produit, prix);
            produits.add(produit);
        }

        return produits;

    }

    public void AddProduit(Produits object) throws SQLException {
        String ajoute = "insert into produits ( nom_produit , prix) values ( ? , ?) ;";

        this.preparedStatement = this.connection.prepareStatement(ajoute);

        this.preparedStatement.setString(1, object.getNom_produit());
        this.preparedStatement.setString(2, object.getPrix());

        this.preparedStatement.execute();

    }

    public boolean deleteProduit(String nom_produit) throws SQLException {
        String req = "DELETE FROM produits WHERE nom_produit = ?;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1, nom_produit);
        this.preparedStatement.execute();


        return true;
    }

    public List<Produits> getDataForStat() throws SQLException{
        List<Produits> list= new ArrayList<Produits>();
        String req = "SELECT produits.nom_produit, COUNT(credit.ID_produit) AS nombre_Total_Achat " +
                "FROM produits " +
                "JOIN credit ON produits.id_produit = credit.id_produit " +
                "GROUP BY produits.id_produit;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.resultSet = this.preparedStatement.executeQuery();

        while(this.resultSet.next()){
            list.add(new Produits(this.resultSet.getString("nom_produit")   , this.resultSet.getInt("nombre_Total_Achat")));
        }
        return list;
    }
    public int getID (String nom_produit) throws SQLException{
        String req = "SELECT ID_produit FROM produits WHERE nom_produit = ?";
        this.preparedStatement = this. connection.prepareStatement (req);
        this.preparedStatement. setString(1 , nom_produit);
        this.resultSet = this.preparedStatement.executeQuery();
        int id_produit = 0;
        while (this.resultSet.next ()){
            id_produit = this.resultSet.getInt (  "ID_produit");
        }
        return id_produit;
}}



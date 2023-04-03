package models;

import javafx.scene.control.Button;

import java.math.BigDecimal;


public class Credit {
 private int ID_client;

    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    private  int ID_credit;

    public Credit(String fullname, String totalCredit) {
        this.fullname = fullname;
        this.total_credit = totalCredit;
    }

    public int getID_credit() {
        return ID_credit;
    }

    public void setID_credit(int ID_credit) {
        this.ID_credit = ID_credit;
    }

    public Credit(int idCredit, String fullname, String telephone, String nomProduit, String totalCredit, String montantRestant) {
        this.ID_credit=idCredit;
        this.fullname= fullname;
        this.telephone=telephone;
        this.nom_produit=nomProduit;
        this.total_credit=totalCredit;
        this.montant_restant=montantRestant;
    }



    private  String fullname;
    private  String nom_produit;

    private  String montant_restant;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private  String telephone;
    private Button button;

    public Credit(String fullname, String telephone, String nomProduit, String totalCredit, String montantRestant) {
        this.fullname=fullname;
        this.telephone=telephone;
        this.nom_produit=nomProduit;
        this.total_credit=totalCredit;
        this.montant_restant=montantRestant;

    }
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getMontant_restant() {
        return montant_restant;
    }

    public void setMontant_restant(String montant_restant) {
        this.montant_restant = montant_restant;
    }

    public String getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(String total_credit) {
        this.total_credit = total_credit;
    }


    private String total_credit;
    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public Credit(String fullname, String nom_produit, String total_credit, String montant_restant) {
        this.fullname = fullname;
        this.nom_produit = nom_produit;
        this.total_credit = total_credit;
        this.montant_restant = montant_restant;
    }
    public Credit() {
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id_credit=" + ID_credit +
                ", fullname='" + fullname + '\'' +
                ", nom_produit='" + nom_produit + '\'' +
                ", montant_restant='" + montant_restant + '\'' +
                ", telephone='" + telephone + '\'' +
                ", button=" + button +
                ", total_credit='" + total_credit + '\'' +
                '}';
    }
}

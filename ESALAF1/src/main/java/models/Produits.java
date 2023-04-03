package models;

import javafx.scene.control.Button;

import java.math.BigDecimal;
import java.util.Date;

public class Produits {
    private int ID_produit;

    private int nombre_Total_Achat;
    private String nom_produit;

    private String prix ;

    private Button button;
    private String fullname;
    private String tottal_credit;

    public Produits(String nom_produit , int nombre_Total_Achat) {
        this.nombre_Total_Achat = nombre_Total_Achat;
        this.nom_produit = nom_produit;
    }

    public int getNombre_Total_Achat() {
        return nombre_Total_Achat;
    }

    public void setNombre_Total_Achat(int nombre_Total_Achat) {
        this.nombre_Total_Achat = nombre_Total_Achat;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTottal_credit() {
        return tottal_credit;
    }

    public void setTottal_credit(String tottal_credit) {
        this.tottal_credit = tottal_credit;
    }


    public int getID_produit() {
        return ID_produit;
    }

    public void setID_produit(int ID_produit) {
        this.ID_produit = ID_produit;
    }



    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }


    public Produits(String nom_produit, String prix) {
        this.nom_produit = nom_produit;
        this.prix = prix;
    }

    public Produits(int ID_produit, String nom_produit, String prix) {

        this.ID_produit = ID_produit;

        this.nom_produit = nom_produit;
        this.prix = prix;

    }

    public Produits() {
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button=button;
    }
}

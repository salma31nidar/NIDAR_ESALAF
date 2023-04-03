package models;

import javafx.scene.control.Button;

public class Client {
    private int id_client ;
    private String fullname;
    private float total_credit;
    private String telephone;

    private Button button ;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Client(String fullname,  String telephone) {
        this.fullname = fullname;
        this.telephone = telephone;
    }

    public int getId_client() {
        return id_client;
    }
    private  String selectedClientNom;

    public String getSelectedClientNom() {
        return selectedClientNom;
    }

    public void setSelectedClientNom(String selectedClientNom) {
        this.selectedClientNom = selectedClientNom;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }




    public float getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(float total_credit) {
        this.total_credit = total_credit;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Client(int id_client, String fullname, String telephone) {
        this.id_client=id_client;
        this.fullname = fullname;
        this.telephone = telephone;
    }
    public Client( String fullname, float total_credit) {

        this.fullname = fullname;
        this.total_credit = total_credit;
    }


}

package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDAO extends BaseDAO<Login> {


    public LoginDAO() throws SQLException {
    }

    @Override
    public void save(Login object) throws SQLException {

    }

    @Override
    public void update(Login object) throws SQLException {

    }

    @Override
    public void delete(Login object) throws SQLException {

    }

    @Override
    public Login getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Login> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAll(Client object) throws SQLException {
    return null ;
    }

    public Login checklogin(Login object ) throws SQLException {
        String verifyLogin = "SELECT * FROM login WHERE username = ? AND password = ?";


        this.preparedStatement = this.connection.prepareStatement(verifyLogin);

        this.preparedStatement.setString(1 , object.getUsername());
        this.preparedStatement.setString(2 , object.getPassword());


        ResultSet result = this.preparedStatement.executeQuery();


        Login resultLogin = null;
        if(result.next()){
            resultLogin = new Login(result.getString("username") , result.getString("password"));
        }

        return resultLogin;
    }
}

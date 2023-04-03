package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDAO extends  BaseDAO{


    public RegisterDAO() throws SQLException {
    }

    @Override
    public void save(Object object) throws SQLException {

    }

    @Override
    public void update(Object object) throws SQLException {

    }

    @Override
    public void delete(Object object) throws SQLException {

    }

    @Override
    public Object getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAll(Client object) throws SQLException {
        return null;
    }

    public void AddToLogin(Register object ) throws SQLException {
        String verifyLogin = "insert into login (username , password , email) values (? , ? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(verifyLogin);

        this.preparedStatement.setString(1 , object.getUsername());
        this.preparedStatement.setString(2 , object.getPassword());
        this.preparedStatement.setString(3 , object.getEmail());



        this.preparedStatement.execute();


    }

}

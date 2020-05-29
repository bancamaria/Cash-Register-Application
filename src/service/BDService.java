package service;

import manager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDService {
    private BDService(){ }
    public static PreparedStatement getStatament(String sql){

        Connection con = database.getInstance().createConnection();
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
            return statement;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

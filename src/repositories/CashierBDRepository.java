package repositories;

import manager.database;
import model.Cashier;
import repositories.CashierRepository;
import service.BDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CashierBDRepository implements CashierRepository{
    @Override
    public void add(Cashier cashier) {
        String sql = "INSERT INTO cashier VALUES (NULL,?, ?, ?)";
        try {
            PreparedStatement statement =  BDService.getStatament(sql);
            statement.setInt(1, cashier.getCashierById());
            statement.setString(2, cashier.getCashiersByLastName());
            statement.setString(3, cashier.getCashiersByFirstName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Cashier getCashierById(Integer id) {
        String sql = "SELECT * FROM cashier WHERE id = ?";
        try{
            PreparedStatement statement = BDService.getStatament(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Cashier c = new Cashier();
                c.setCashierId(set.getInt("idCashier"));
                c.setCashierFirstName(set.getString("firstName"));
                c.setCashierLastName(set.getString("lastName"));
                return c;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Cashier> getCashier() {
        String sql = "SELECT * FROM cashier";
        try{
            Map<Integer, Cashier> mp = new HashMap<>();
            PreparedStatement statement = BDService.getStatament(sql);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Cashier c = new Cashier();
                c.setCashierId(set.getInt("idCashier"));
                c.setCashierFirstName(set.getString("firstName"));
                c.setCashierLastName(set.getString("lastName"));
                mp.put(set.getInt("idCashier"),c);
            }
            return mp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

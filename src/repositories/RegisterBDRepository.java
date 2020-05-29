package repositories;

import manager.database;
import model.Register;
import repositories.RegisterRepository;
import service.BDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterBDRepository implements RegisterRepository{
    @Override
    public void add(Register registers) {
        String sql = "INSERT INTO register VALUES (NULL,?, ?)";
        try {
            PreparedStatement statement =  BDService.getStatament(sql);
            statement.setInt(1, registers.getRegisterById());
            statement.setBoolean(2, registers.getRegistersByInUseState());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Register getRegisterById(Integer id) {
        String sql = "SELECT * FROM register WHERE id = ?";
        try{
            PreparedStatement statement = BDService.getStatament(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Register r = new Register();
                r.setRegisterById(set.getInt("idRegister"));
                r.setRegistersByInUseState(set.getBoolean("inUse"));
                return r;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Register> getCoupon() {
        String sql = "SELECT * FROM register";
        try{
            Map<Integer, Register> mp = new HashMap<>();
            PreparedStatement statement = BDService.getStatament(sql);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Register r = new Register();
                r.setRegistersById(set.getInt("idRegister"));
                r.setRegistersByInUseState(set.getBoolean("inUse"));
                mp.put(set.getInt("id"),r);
            }
            return mp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

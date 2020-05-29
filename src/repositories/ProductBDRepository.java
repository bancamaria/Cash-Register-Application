package repositories;

import manager.database;
import model.Product;
import repositories.ProductRepository;
import service.BDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBDRepository implements ProductRepository{
    @Override
    public void add(Product products) {
        String sql = "INSERT INTO product VALUES (NULL,?, ?)";
        try {
            PreparedStatement statement =  BDService.getStatament(sql);
            statement.setInt(1, products.getProductById());
            statement.setString(2, products.getProductsByName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Product getProductById(Integer id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try{
            PreparedStatement statement = BDService.getStatament(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Product p = new Product();
                p.setProductId(set.getInt("idProduct"));
                p.setProductName(set.getString("name"));
                return p;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Product> getCoupon() {
        String sql = "SELECT * FROM product";
        try{
            Map<Integer, Product> mp = new HashMap<>();
            PreparedStatement statement = BDService.getStatament(sql);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Product p = new Product();
                p.setProductId(set.getInt("idProduct"));
                p.setProductsName(set.getString("name"));
                mp.put(set.getInt("idProduct"),p);
            }
            return mp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
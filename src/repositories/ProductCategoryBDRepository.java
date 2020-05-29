package repositories;

import manager.database;
import model.ProductCategory;
import repositories.ProductCategoryRepository;
import service.BDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCategoryBDRepository implements ProductCategoryRepository{
    @Override
    public void add(ProductCategory productCategories) {
        String sql = "INSERT INTO productcategory VALUES (NULL,?, ?)";
        try {
            PreparedStatement statement =  BDService.getStatament(sql);
            statement.setInt(1, productCategories.getProductCategoryById());
            statement.setString(2, productCategories.getProductCategoryByName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProductCategory getProductCategoryById(Integer id) {
        String sql = "SELECT * FROM productcategory WHERE id = ?";
        try{
            PreparedStatement statement = BDService.getStatament(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                ProductCategory pc = new ProductCategory();
                pc.setProductCategoryId(set.getInt("idProductCategory"));
                pc.setProductCategoryName(set.getString("name"));
                return pc;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, ProductCategory> getCoupon() {
        String sql = "SELECT * FROM productcategory";
        try{
            Map<Integer, ProductCategory> mp = new HashMap<>();
            PreparedStatement statement = BDService.getStatament(sql);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                ProductCategory pc = new ProductCategory();
                pc.setProductCategoryId(set.getInt("idProductCategory"));
                pc.setProductCategoryName(set.getString("name"));
                mp.put(set.getInt("idProductCategory"),pc);
            }
            return mp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

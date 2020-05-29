package repositories;

import exceptions.InexistentCashierIdException;
import model.Product;
import model.ProductCategory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapProductCategoryRepository implements ProductCategoryRepository {
    private Map<Integer, ProductCategory> productCategories;
    public MapProductCategoryRepository(){ productCategories = new HashMap<>(); }

    @Override
    public void addRoute(ProductCategory r){ productCategories.put(r.getId(),r); }

    @Override
    public ProductCategory findRouteById(Integer id){
        ProductCategory productCategories = null;
        try{
            if(productCategories.get(id).equals(null))
                throw new InexistentCashierIdException();
            productCategories = productCategories.get(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return productCategories;
    }
}

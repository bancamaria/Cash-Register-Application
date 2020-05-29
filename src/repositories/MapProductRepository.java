package repositories;

import exceptions.InexistentCashierIdException;
import model.Product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapProductRepository implements ProductRepository{
    private Map<Integer, Product> products;
    public MapProductRepository(){ products = new HashMap<>(); }

    @Override
    public void addRoute(Product r){ products.put(r.getId(),r); }

    @Override
    public Product findRouteById(Integer id){
        Product products = null;
        try{
            if(products.get(id).equals(null))
                throw new InexistentCashierIdException();
            products = products.get(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }
}

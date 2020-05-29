package repositories;

import exceptions.InexistentCashierIdException;
import model.SoldProduct;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapSoldProductRepository implements SoldProductRepository{
    private Map<Integer, SoldProduct> soldProducts;
    public MapSoldProductRepository(){ soldProducts = new HashMap<>(); }

    @Override
    public void addRoute(SoldProduct r){ soldProducts.put(r.getId(),r); }

    @Override
    public SoldProduct findRouteById(Integer id){
        SoldProduct soldProducts = null;
        try{
            if(soldProducts.get(id).equals(null))
                throw new InexistentCashierIdException();
            soldProducts = soldProducts.get(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return soldProducts;
    }
}

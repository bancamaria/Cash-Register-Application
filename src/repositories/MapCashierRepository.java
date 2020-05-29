package repositories;

import exceptions.InexistentCashierIdException;
import model.Cashier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapCashierRepository implements CashierRepository{
    private Map<Integer, Cashier> cashier;
    public MapCashierRepository(){ cashier = new HashMap<>(); }

    @Override
    public void addRoute(Cashier r){ cashier.put(r.getId(),r); }

    @Override
    public Cashier findRouteById(Integer id){
        Cashier cashier = null;
        try{
            if(cashier.get(id).equals(null))
                throw new InexistentCashierIdException();
            cashier = cashier.get(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return cashier;
    }
}


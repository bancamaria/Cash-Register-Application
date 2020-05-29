package repositories;

import exceptions.InexistentCashierIdException;
import model.Receipt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapReceiptRepository implements ReceiptRepository{
    private Map<Integer, Receipt> receipts;
    public MapReceiptRepository(){ receipts = new HashMap<>(); }

    @Override
    public void addRoute(Receipt r){ receipts.put(r.getId(),r); }

    @Override
    public Receipt findRouteById(Integer id){
        Receipt receipts = null;
        try{
            if(receipts.get(id).equals(null))
                throw new InexistentCashierIdException();
            receipts = receipts.get(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return receipts;
    }
}

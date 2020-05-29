package repositories;

import exceptions.InexistentCashierIdException;
import model.Register;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapRegisterRepository implements RegisterRepository{
    private Map<Integer, Register> registers;
    public MapRegisterRepository(){ registers = new HashMap<>(); }

    @Override
    public void addRoute(Register r){ registers.put(r.getId(),r); }

    @Override
    public Register findRouteById(Integer id){
        Register registers = null;
        try{
            if(registers.get(id).equals(null))
                throw new InexistentCashierIdException();
            registers = registers.get(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return registers;
    }
}

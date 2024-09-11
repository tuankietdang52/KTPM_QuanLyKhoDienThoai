package BUS;

import java.util.ArrayList;
import DAO.DAOinterface;

/**
 * @param <T> DTO
 */
public abstract class BaseBUS<T> {
    protected ArrayList<T> list;
    protected DAOinterface<T> DAO;
    

    public BaseBUS(DAOinterface<T> DAO){
        this.DAO = DAO;
        list = DAO.selectAll();
    }

    public ArrayList<T> getAll(){
        return list;
    }

    public T getByIndex(int index){
        return list.get(index);
    }

    public boolean add(T DTO) {
        boolean check = DAO.insert(DTO) != 0;
        if (check) {
            list.add(DTO);
        }
        return check;
    }

    public abstract boolean delete(T DTO);
    
    public abstract boolean update(T DTO);
    
    public abstract int getIndexByCode(int code);
}

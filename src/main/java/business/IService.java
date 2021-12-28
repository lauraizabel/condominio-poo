package business;

import java.util.ArrayList;

public interface IService<T> {

    T getById(Integer id) ;

    ArrayList<T> getAll();

    boolean deleteById(Integer id);

    boolean save(T object) throws Exception;

    T update(T object);

    ArrayList<T> getAllAuditory();
}

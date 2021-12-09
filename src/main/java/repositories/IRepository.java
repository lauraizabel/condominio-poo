package repositories;

import java.util.ArrayList;

public interface IRepository<T> {
    T getById(Integer id);

    ArrayList<T> getAll();

    boolean deleteById(Integer id);

    boolean save(T object);

    T update(T object);
}

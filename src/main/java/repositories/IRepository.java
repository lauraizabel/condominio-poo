package repositories;

import java.util.List;

public interface IRepository<T> {
    T getById(Integer id);

    List<T> getAll();

    boolean deleteById(Integer id);

    boolean save(T object);

    T update(T object);
}

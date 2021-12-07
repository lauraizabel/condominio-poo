package DAO;

import javax.persistence.EntityManager;
import java.util.List;

public interface IEntityDAO<T> {
    public EntityManager getEntityManager();

    T getById(Integer id);

    boolean save(T object);

    List<T> getAll();

    T update(T object);

    boolean deleteById(Integer id);
}

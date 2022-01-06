package DAO;

import dados.Auditoria;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public interface IEntityDAO<T> {
    public EntityManager getEntityManager();

    T getById(Integer id);

    boolean save(T object);

    ArrayList<T> getAll();

    T update(T object);

    boolean deleteById(Integer id);

    ArrayList<Auditoria> getAllAuditory();
}

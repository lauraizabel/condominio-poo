package DAO.implementation;

import DAO.IEntityDAO;
import dados.Fornecedor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EntityDAO<T> implements IEntityDAO<T> {

  private Class<T> persistedClass;

  protected EntityManager em;
  public EntityDAO() {}

  protected EntityDAO(Class<T> persistedClass) {
    this();
    em = getEntityManager();
    this.persistedClass = persistedClass;
  }

  @Override
  public EntityManager getEntityManager() {
    var factory = Persistence.createEntityManagerFactory("default");
    if (em == null) { em = factory.createEntityManager(); }
    return em;
  }

  @Override
  public T getById(Integer id) {
    return em.find(persistedClass, id);
  }

  @Override
  public boolean save(T object) {
    try {
      em.getTransaction().begin();
      em.persist(object);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      em.getTransaction().rollback();
      return false;
    }
  }

  @Override
  public ArrayList<T> getAll() {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<T> query = builder.createQuery(persistedClass);
    query.from(persistedClass);

    // convertendo para ArrayList para ser um tipo aceito pelo hibernate
    List<T> result = em.createQuery(query).getResultList();
    ArrayList<T> arrayListResult = new ArrayList<T>(result);

    return arrayListResult;
  }

  @Override
  public T update(T object) {
    EntityTransaction t = em.getTransaction();
    t.begin();
    em.merge(object);
    em.flush();
    t.commit();
    return object;
  }

  @Override
  public boolean deleteById(Integer id) {
    try {
      T entity = em.find(persistedClass, id);
      EntityTransaction t = em.getTransaction();
      t.begin();
      T mergedEntity = em.merge(entity);
      em.remove(mergedEntity);
      em.flush();
      t.commit();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public AuditReader getAuditReader() {
    return AuditReaderFactory.get(em);
  }

  public ArrayList<T> getAllAuditory () {
    AuditReader reader = getAuditReader();
    AuditQuery query = reader.createQuery().forRevisionsOfEntity(persistedClass, false, true);
    ArrayList<T> newObjects = new ArrayList<T>();

    List<Object[]> result = query.getResultList();

    for (Object[] o : result) {
      T typeObject = (T) o[0];
      newObjects.add(typeObject);
    }

    return newObjects;
  }
}
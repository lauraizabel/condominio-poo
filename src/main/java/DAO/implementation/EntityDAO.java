package DAO.implementation;

import DAO.IEntityDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import dados.Almoxarifado;
import dados.Auditoria;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import validation.utils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EntityDAO<T> implements IEntityDAO<T> {

  private Class<T> persistedClass;

  public EntityDAO() {}

  protected EntityDAO(Class<T> persistedClass) {
    this();
    this.persistedClass = persistedClass;
  }

  @Override
  public EntityManager getEntityManager() {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    return factory.createEntityManager();
  }

  @Override
  public T getById(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(persistedClass, id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      this.closeConnection(em);
    }
  }

  @Override
  public boolean save(T object) {
    EntityManager em = getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(object);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      em.getTransaction().rollback();
      return false;
    } finally {
      this.closeConnection(em);
    }
  }

  @Override
  public ArrayList<T> getAll() {
    EntityManager em = getEntityManager();
    try {
      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<T> query = builder.createQuery(persistedClass);
      query.from(persistedClass);

      // convertendo para ArrayList para ser um tipo aceito pelo hibernate
      List<T> result = em.createQuery(query).getResultList();
      ArrayList<T> arrayListResult = new ArrayList<T>(result);

      return arrayListResult;
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<T>();
    } finally {
      this.closeConnection(em);
    }
  }

  @Override
  public T update(T object) {
    EntityManager em = getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(object);
      em.flush();
      em.getTransaction().commit();
      return object;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      this.closeConnection(em);
    }
  }

  @Override
  public boolean deleteById(Integer id) {
    EntityManager em = getEntityManager();
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
    } finally {
      this.closeConnection(em);
    }
  }

  public AuditReader getAuditReader() {
    EntityManager em = getEntityManager();
    try {
      return AuditReaderFactory.get(em);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      this.closeConnection(em);
    }
  }

  public ArrayList<Auditoria> getAllAuditory () {
    EntityManager em = null;
    try {
      em = getEntityManager();
      String q = "FROM Auditoria WHERE nomeTabela = :nomeTabela";
      Query query = em.createQuery(q).setParameter("nomeTabela",  persistedClass.getName());

      List<Auditoria> list = query.getResultList();
      ArrayList<Auditoria> arrayList = new ArrayList<>(list);

      return arrayList;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      this.closeConnection(em);
    }
    return null;
  }

  public void closeConnection(EntityManager em) {
    EntityManagerFactory factory = em.getEntityManagerFactory();
    if ( em.isOpen() ) {
      System.out.println("closing...");
      em.close();
      factory.close();
    }
  }
}
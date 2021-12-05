package repositories.implementation;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import repositories.IEntityDAO;

public class EntityDAO implements IEntityDAO {

  private static EntityDAO instance;
  
  protected EntityManager em;
  
  public static EntityDAO getInstance() {
    if (instance == null) {
      instance = new EntityDAO();
    }
    
    return instance;
  }
  
  private EntityDAO() {
    em = getEntityManager();
  }
  
  @Override
  public EntityManager getEntityManager() {
    var factory = Persistence.createEntityManagerFactory("default");
    
    if (em == null) {
      em = factory.createEntityManager();
    }
    
    return em;
  }
  
  @Override
  public Object findById(Integer id) {
    return em.find(Object.class, id);
  }
  
  @Override
  public void save(Object object) {
    try {
      em.getTransaction().begin();
      em.persist(object);
      em.getTransaction().commit();
    } catch (Exception ex) {
      ex.printStackTrace();
      em.getTransaction().rollback();
    }
  }
  
}

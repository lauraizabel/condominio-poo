package repositories;

import javax.persistence.EntityManager;

public interface IEntityDAO {
  
  EntityManager getEntityManager();
  
  Object findById(Integer id);
  
  void save(Object object);
  
}

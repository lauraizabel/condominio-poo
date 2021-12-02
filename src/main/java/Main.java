import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    
    EntityManager em = emf.createEntityManager();
    
    Teste T1 = new Teste();
    T1.setId(2);
    //Mudar o nome pra ver se salva correto no banco de dados;
    T1.setName("Laura");
    
    
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.merge(T1);
    tx.commit();
    
    em.close();
    emf.close();
    }
    
}

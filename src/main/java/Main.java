import dados.Morador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

//    Teste T1 = new Teste();
//    T1.setId(3);
//    //Mudar o nome pra ver se salva correto no banco de dados;
//    T1.setName("Laura");
    Integer id = 1;
    Morador morador1 = new Morador(id, "Wili", "40028922", "a@b.com", "12345677899");

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.merge(morador1);
    tx.commit();

    em.close();
    emf.close();
  }
}

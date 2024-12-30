package modelo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAO_Libro {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca");
    EntityManager em = emf.createEntityManager();


}

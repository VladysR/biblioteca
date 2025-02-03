package spring.biblioteca.repositorios;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.biblioteca.modelo.Ejemplar;
import spring.biblioteca.modelo.Libro;

import java.util.List;

@Repository
public interface EjemplarRepository extends CrudRepository<Ejemplar,Integer> {

    List<Ejemplar> isbn(Libro isbn);

    List<Ejemplar> getEjemplarsByIsbn(Libro isbn);

    List<Ejemplar> getEjemplarsByEstado(String estado);
}

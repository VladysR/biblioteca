package spring.biblioteca.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.biblioteca.modelo.Libro;
import spring.biblioteca.modelo.Usuario;

@Repository
public interface LibroRepository extends CrudRepository<Libro,Integer> {
    Libro getLibroByIsbnIsLike(String isbn);
}

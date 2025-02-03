package spring.biblioteca.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.biblioteca.modelo.Ejemplar;
import spring.biblioteca.modelo.Libro;
import spring.biblioteca.modelo.Prestamo;
import spring.biblioteca.modelo.Usuario;

import java.util.List;

@Repository
public interface PrestamoRepository extends CrudRepository<Prestamo,Integer> {

    List<Prestamo> findPrestamosByUsuario(Usuario usuario);

    Prestamo findPrestamosByEjemplar(Ejemplar ejemplar);
}

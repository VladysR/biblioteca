package spring.biblioteca.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.biblioteca.modelo.Usuario;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Usuario,Integer> {
    List<Usuario> getUsuarioByDni(String dni);
}

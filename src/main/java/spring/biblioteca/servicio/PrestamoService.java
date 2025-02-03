package spring.biblioteca.servicio;

import org.springframework.stereotype.Service;
import spring.biblioteca.modelo.Ejemplar;
import spring.biblioteca.modelo.Prestamo;
import spring.biblioteca.modelo.Usuario;
import spring.biblioteca.repositorios.EjemplarRepository;
import spring.biblioteca.repositorios.LibroRepository;
import spring.biblioteca.repositorios.PrestamoRepository;
import spring.biblioteca.repositorios.UserRepository;

import java.util.List;

@Service
public class PrestamoService {

    private PrestamoRepository prestamoRepository;
    private EjemplarRepository ejemplarRepository;
    private UserRepository usuarioRepository;

    public PrestamoService(EjemplarRepository ejemplarRepository,PrestamoRepository prestamoRepository,UserRepository usuarioRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    public List<Prestamo> getAllPrestamos() {
        return (List<Prestamo>) prestamoRepository.findAll();
    }
    public Prestamo addPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }
    public Prestamo getPrestamoById(int id){
        return prestamoRepository.findById(id).get();
    }
    public List<Prestamo> getPrestamoByUsr(Usuario usr) {
        return prestamoRepository.findPrestamosByUsuario(usr);
    }
    public Prestamo getPrestamoByEjemplar(Ejemplar e) {
        return prestamoRepository.findPrestamosByEjemplar(e);
    }
    public Prestamo updatePrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }
    public void deletePrestamo(int id){
        prestamoRepository.deleteById(id);
    }
}

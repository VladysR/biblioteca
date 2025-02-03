package spring.biblioteca.servicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.biblioteca.modelo.Ejemplar;
import spring.biblioteca.repositorios.EjemplarRepository;
import spring.biblioteca.repositorios.LibroRepository;

import java.util.List;

@Service
public class EjemplarService {

    private EjemplarRepository ejemplarRepository;
    private LibroRepository libroRepository;

    public EjemplarService(EjemplarRepository ejemplarRepository,LibroRepository libroRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.libroRepository = libroRepository;
    }
    @Transactional(readOnly = true)
    public List<Ejemplar> getAllEjemplar(){
        return (List<Ejemplar>) ejemplarRepository.findAll();
    }
    @Transactional
    public Ejemplar addEjemplar(Ejemplar ejemplar){
        return ejemplarRepository.save(ejemplar);
    }
    @Transactional(readOnly = true)
    public Ejemplar getEjemplarById(int id){
        return ejemplarRepository.findById(id).get();
    }
    @Transactional
    public List<Ejemplar> getEjemplaresByEstado(String estado){
        return  ejemplarRepository.getEjemplarsByEstado(estado);
    }
    @Transactional
    public List<Ejemplar> getEjemplaresByISBN(String isbn){
        return  ejemplarRepository.getEjemplarsByIsbn(libroRepository.getLibroByIsbnIsLike(isbn));
    }
    @Transactional
    public Ejemplar updateEjemplar(Ejemplar ejemplar){
        return ejemplarRepository.save(ejemplar);
    }
    @Transactional
    public void deleteEjemplar(int id){
        ejemplarRepository.deleteById(id);
    }
}

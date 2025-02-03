package spring.biblioteca.servicio;

import org.springframework.stereotype.Service;
import spring.biblioteca.modelo.Libro;
import spring.biblioteca.repositorios.LibroRepository;

import java.util.List;

@Service
public class LibroService {

    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    public List<Libro> getAllLibros(){
        return (List<Libro>) libroRepository.findAll();
    }
    public Libro addLibro(Libro libro){
        return libroRepository.save(libro);
    }
    public Libro getLibroById(int id){
        return libroRepository.findById(id).get();
    }
    public Libro getLibroByISBN(String isbn){
        return libroRepository.getLibroByIsbnIsLike(isbn);
    }
    public Libro updateLibro(Libro libro){
        return libroRepository.save(libro);
    }
    public void deleteLibro(int id){
        libroRepository.deleteById(id);
    }
}

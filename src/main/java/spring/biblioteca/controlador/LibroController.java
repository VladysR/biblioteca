package spring.biblioteca.controlador;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.biblioteca.modelo.Libro;
import spring.biblioteca.modelo.Prestamo;
import spring.biblioteca.servicio.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {
    LibroService servicio;
    public LibroController() {}

    @Autowired
    public LibroController(LibroService servicio) {
        this.servicio = servicio;
    }
    @GetMapping("/getLibros")
    public ResponseEntity<Iterable<Libro>> getLibros() {
        return ResponseEntity.ok(servicio.getAllLibros());
    }
    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> getLibroByISBN(@PathVariable String isbn) {
        return ResponseEntity.ok(servicio.getLibroByISBN(isbn));
    }
    @PostMapping("/addLibro")
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro) {
        Libro libroSalvo = this.servicio.addLibro(libro);
        return ResponseEntity.ok(libroSalvo);
    }
    @PostMapping("/modLibro")
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
        Libro libroSalvo = this.servicio.updateLibro(libro);
        return ResponseEntity.ok(libroSalvo);
    }
    @Transactional
    @DeleteMapping("/delLibro/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable int id) {
        Libro libro = this.servicio.getLibroById(id);

        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        this.servicio.deleteLibro(id);
        return ResponseEntity.ok(libro.toString() + "Ha sido eliminado");
    }


}

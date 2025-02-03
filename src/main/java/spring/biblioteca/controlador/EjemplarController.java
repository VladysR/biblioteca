package spring.biblioteca.controlador;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.biblioteca.modelo.Ejemplar;
import spring.biblioteca.modelo.Libro;
import spring.biblioteca.servicio.EjemplarService;
import spring.biblioteca.servicio.LibroService;

@RestController
@RequestMapping("/ejemplares")
@CacheConfig(cacheNames = {"ejemplares"})
public class EjemplarController {
    EjemplarService servicio;
    public EjemplarController() {}

    @Autowired
    public EjemplarController(EjemplarService servicio) {
        this.servicio = servicio;
    }
    @GetMapping("/getEjemplares")
    public ResponseEntity<Iterable<Ejemplar>> getEjemplares() {
        return ResponseEntity.ok(servicio.getAllEjemplar());
    }

    @GetMapping("/getEjemplar/{id}")
    @Cacheable
    public ResponseEntity<Ejemplar> getEjemplar(@PathVariable int id) {
        return ResponseEntity.ok(servicio.getEjemplarById(id));}

    @GetMapping("/getEjemplaresByEstado/{estado}")
    @Cacheable
    public ResponseEntity<Iterable<Ejemplar>> getEjemplaresByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(servicio.getEjemplaresByEstado(estado));
    }

    @GetMapping("/getEjemplaresByISBN/{isbn}")
    @Cacheable
    public ResponseEntity<Iterable<Ejemplar>> getEjemplaresByISBN(@PathVariable String isbn) {
        return ResponseEntity.ok(servicio.getEjemplaresByISBN(isbn));
    }

    @PostMapping("addEjemplar")
    @Cacheable
    public ResponseEntity<Ejemplar> addEjemplar(@RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarSalvo = this.servicio.addEjemplar(ejemplar);
        return ResponseEntity.ok(ejemplarSalvo);
    }

    @PostMapping("/modEjemplar")
    @Cacheable
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarSalvo = this.servicio.updateEjemplar(ejemplar);
        return ResponseEntity.ok(ejemplarSalvo);
    }

    @Transactional
    @DeleteMapping("/delEjemplar/{id}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable int id) {
        Ejemplar ejemplar = this.servicio.getEjemplarById(id);

        if (ejemplar == null) {
            return ResponseEntity.notFound().build();
        }
        this.servicio.deleteEjemplar(id);
        return ResponseEntity.ok(ejemplar.toString() + "Ha sido eliminado");
    }


}

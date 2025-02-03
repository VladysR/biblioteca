package spring.biblioteca.controlador;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.biblioteca.modelo.Ejemplar;
import spring.biblioteca.modelo.Prestamo;
import spring.biblioteca.modelo.Usuario;
import spring.biblioteca.servicio.EjemplarService;
import spring.biblioteca.servicio.PrestamoService;
import spring.biblioteca.servicio.UsuarioService;

@RestController
@RequestMapping("/prestamos")
@CacheConfig(cacheNames = {"ejemplares"})
public class PrestamoController {
    PrestamoService servicio;
    UsuarioService usuarioService;
    public PrestamoController() {}

    @Autowired
    public PrestamoController(PrestamoService servicio) {
        this.servicio = servicio;
    }
    @GetMapping("/getPrestamos")
    public ResponseEntity<Iterable<Prestamo>> getPrestamos() {
        return ResponseEntity.ok(servicio.getAllPrestamos());
    }

    @GetMapping("/getPrestamoByUsr/{usr}")
    public ResponseEntity<Iterable<Prestamo>> getPrestamoByUsuarioDni(@PathVariable String usr) {
        return ResponseEntity.ok(servicio.getPrestamoByUsr(usuarioService.getUsuarioBy));
    }

    @GetMapping("/getPrestamoByEjemplar/{Ejemplar}")
    @Cacheable
    public ResponseEntity<Prestamo> getPrestamoByEjemplar(@PathVariable Ejemplar ejemplar) {
        return ResponseEntity.ok(servicio.getPrestamoByEjemplar(ejemplar));
    }

    @PostMapping("addPrestamo")
    @Cacheable
    public ResponseEntity<Prestamo> addPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo prestamoSalvo = this.servicio.addPrestamo(prestamo);
        return ResponseEntity.ok(prestamoSalvo);
    }

    @PostMapping("/modPrestamo")
    public ResponseEntity<Prestamo> updatePrestamo(@RequestBody Prestamo prestamo) {
        Prestamo prestamoSalvo = this.servicio.updatePrestamo(prestamo);
        return ResponseEntity.ok(prestamoSalvo);
    }

    @Transactional
    @DeleteMapping("/delPrestamo/{id}")
    public ResponseEntity<String> deletePrestamo(@PathVariable int id) {
        Prestamo prestamo = this.servicio.getPrestamoById(id);

        if (prestamo == null) {
            return ResponseEntity.notFound().build();
        }
        this.servicio.deletePrestamo(id);
        return ResponseEntity.ok(prestamo.toString() + "Ha sido eliminado");
    }


}

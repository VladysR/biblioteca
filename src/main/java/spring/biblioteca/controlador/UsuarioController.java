package spring.biblioteca.controlador;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.biblioteca.modelo.Usuario;
import spring.biblioteca.servicio.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CacheConfig(cacheNames = {"usuarios"})
public class UsuarioController {
    UsuarioService servicio;
    public UsuarioController() {}

    @Autowired
    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }
    @GetMapping("/getUsrs")
    public ResponseEntity<Iterable<Usuario>> getUsuarios() {
        return ResponseEntity.ok(servicio.getAllUsuarios());
    }
    @GetMapping("/getUsr/{id}")
    @Cacheable
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) throws InterruptedException {
        return ResponseEntity.ok(servicio.getUsuarioById(id));
    }
    @GetMapping("/getUsrByDni/{dni}")
    public ResponseEntity<Usuario> getUsuarioByDNI(@PathVariable String dni) throws InterruptedException {
        return ResponseEntity.ok(servicio.getUsuarioByDNI(dni));
    }
    @PostMapping("/addUsr")
    @Cacheable
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = this.servicio.addUsuario(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }
    @PostMapping("/modUsr")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = this.servicio.updateUsuario(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @Transactional
    @DeleteMapping("/delUsr/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable int id) {
        Usuario usr = this.servicio.getUsuarioById(id);

        if (usr == null) {
            return ResponseEntity.notFound().build();
        }
        this.servicio.deleteUsuario(id);
        return ResponseEntity.ok(usr.toString() + "Ha sido eliminado");
    }


}

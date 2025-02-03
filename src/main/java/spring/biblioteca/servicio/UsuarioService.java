package spring.biblioteca.servicio;

import org.springframework.stereotype.Service;
import spring.biblioteca.modelo.Usuario;
import spring.biblioteca.repositorios.UserRepository;

import java.util.List;
@Service
public class UsuarioService {

    private UserRepository userRepository;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<Usuario> getAllUsuarios(){
        return (List<Usuario>) userRepository.findAll();
    }
    public Usuario addUsuario(Usuario usuario){
        return userRepository.save(usuario);
    }
    public Usuario getUsuarioById(int id){
        return userRepository.findById(id).get();
    }
    public Usuario updateUsuario(Usuario usuario){
        return userRepository.save(usuario);
    }
    public Usuario getUsuarioByDNI(String dni){
        return (Usuario) userRepository.getUsuarioByDni(dni);
    }
    public void deleteUsuario(int id){
        userRepository.deleteById(id);
    }
}

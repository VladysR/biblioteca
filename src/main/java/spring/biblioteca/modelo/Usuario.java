package spring.biblioteca.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 15)
    @NotBlank(message = "El DNI no puede estar en blanco")
    @Size(min = 9, max = 9, message = "El DNI debe tener 9 caracteres")
    @DNIValido(message = "Fromato DNI incorrecto")
    private String dni;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank (message = "El campo no puede estar vacio")
    @NotNull (message = "El campo no puede estar vacio")
    @NotEmpty (message = "El campo no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9\sñáéíóúÁÉÍÓÚüÜçÇ]$ ",message = "Solo se permiten letras")
    private String nombre;

    @Column(name = "email", nullable = false, length = 100)
    @NotBlank (message = "El campo no puede estar vacio")
    @NotNull (message = "El campo no puede estar vacio")
    @NotEmpty (message = "El campo no puede estar vacio")
    @Pattern(regexp = "^([A-Za-z0-9]{1,50}@(gmail\\.com))$", message = "Solo se permite el correo de GMAIL")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank (message = "El campo no puede estar vacio")
    @NotNull (message = "El campo no puede estar vacio")
    @NotEmpty (message = "El campo no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "La contraseña debe tener al 4 y maximo 12 caracteres")
    private String password;

    @Lob
    @Column(name = "tipo", nullable = false)
    @NotBlank (message = "El campo no puede estar vacio")
    @NotNull (message = "El campo no puede estar vacio")
    @NotEmpty (message = "El campo no puede estar vacio")
    @Pattern(regexp = "^(normal|administrador)$",message = "Solo puede haber usuario de tipo normal y administrador")
    private String tipo;

    @Column(name = "penalizacionHasta")
    private LocalDate penalizacionHasta;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-prestamo")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPenalizacionHasta() {
        return penalizacionHasta;
    }

    public void setPenalizacionHasta(LocalDate penalizacionHasta) {
        this.penalizacionHasta = penalizacionHasta;
    }

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
package spring.biblioteca.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.ToString;
import jakarta.validation.constraints.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    @NotBlank (message = "El campo no puede estar vacio")
    @NotNull (message = "El campo no puede estar vacio")
    @NotEmpty (message = "El campo no puede estar vacio")
    @Pattern(regexp = "^(?:ISBN(?:-13)?:?)(?=[0-9]{13}$)([0-9]{3}-){2}[0-9]{3}[0-9X]$", message = "Formato de ISBN incorrecto")
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    @NotBlank (message = "El campo no puede estar vacio")
    @NotNull (message = "El campo no puede estar vacio")
    @NotEmpty (message = "El campo no puede estar vacio")
    @Size(max = 200, message = "El título no puede exceder los 200 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\sñáéíóúÁÉÍÓÚüÜçÇ]*$", message = "El título solo puede contener caracteres alfanuméricos y espacios")
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    @NotBlank (message = "El campo no puede estar vacio")
    @NotNull (message = "El campo no puede estar vacio")
    @NotEmpty (message = "El campo no puede estar vacio")
    @Size(max = 100, message = "El autor no puede exceder los 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\sñáéíóúÁÉÍÓÚüÜçÇ]$", message = "El autor solo puede contener caracteres alfanuméricos y espacios")
    private String autor;

    @OneToMany(mappedBy = "isbn")
    @JsonManagedReference("ejemplar-libro")
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Set<Ejemplar> getEjemplars() {
        return ejemplars;
    }

    public void setEjemplars(Set<Ejemplar> ejemplars) {
        this.ejemplars = ejemplars;
    }
}
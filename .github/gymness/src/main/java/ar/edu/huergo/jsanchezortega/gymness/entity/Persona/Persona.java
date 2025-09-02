package ar.edu.huergo.jsanchezortega.gymness.entity.persona;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Persona")

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size (min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caractertes")
    private String nombre;

    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size (min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caractertes")
    private String apellido;

    @NotBlank(message = "El documnento de identidad es obligatorio")
    @Size(max = 8, message = "El documento debe tener maximo 8 digitos")
    private int documento;
    
}

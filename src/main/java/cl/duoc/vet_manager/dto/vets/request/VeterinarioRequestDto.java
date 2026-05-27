/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioRequestDto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String segundoNombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String segundoApellido;

    @NotBlank
    @Size(max = 22)
    private String rut;

    @NotBlank
    @Size(min = 1, max = 1)
    private String dv;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefonoCelular;

    @NotNull
    @Past
    private LocalDate fechaNacimiento;

    @NotBlank
    @Size(min = 8, max = 12)
    private String numeroRegistroProfesional;

    @Past
    private LocalDate egresoProfesional;

    @NotNull
    private Boolean puedeOperar;

    @NotNull
    private List<Long> horarioVeterinario;
}

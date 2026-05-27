/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.dto.vets.request;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioRequestDto {
    private String nombre;
    private String segundoNombre;
    private String apellido;
    private String segundoApellido;
    private String rut;
    private String dv;
    private String email;
    private String telefonoCelular;
    private LocalDate fechaNacimiento;
    private String numeroRegistroProfesional;
    private LocalDate egresoProfesional;
    private Boolean puedeOperar;
    private List<Long> horarioVeterinario;
}

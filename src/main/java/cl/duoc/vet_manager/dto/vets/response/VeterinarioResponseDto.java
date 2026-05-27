/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioResponseDto {

    private Long id;
    private String nombreCompleto;
    private String apellidos;
    private String rut;
    private String email;
    private Integer edad;
    private String numeroRegistroProfesional;
    private List<HorarioResponseDto> horarioVeterinario;
}
/*
 * Todos
 * private Long id;
 * private String nombre;
 * private String segundoNombre;
 * private String apellido;
 * private String segundoApellido;
 * private String rut;
 * private String dv;
 * private String email;
 * private String telefonoCelular;
 * private LocalDate fechaNacimiento;
 * private String numeroRegistroProfesional;
 * private LocalDate egresoProfesional;
 * private Boolean puedeOperar;
 * private List<HorarioResponseDto> horarioVeterinario;
 */

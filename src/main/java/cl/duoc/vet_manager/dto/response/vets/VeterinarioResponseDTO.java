/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.dto.response.vets;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioResponseDTO {
    private Long id;
    private String nombreCompleto;
    private String apellidos;
    private String rut;
    private String email;
    private Integer edad;
    private String numeroRegistroProfesional;
    private List<HorarioResponseDTO> horarioVeterinario;
}

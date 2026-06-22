/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.dto.response.pets;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaResponseDTO {
    private Long id;
    private String nombreMascota;
    private LocalDate fechaNacimientoMascota;
    private Boolean esDocilBoolean;
    private RazaResponseDTO razaResponse;
    private Long idCliente;
}

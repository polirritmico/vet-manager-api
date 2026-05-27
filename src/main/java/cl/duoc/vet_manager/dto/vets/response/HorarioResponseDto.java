/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vets_api.dto.response;

import cl.duoc.vets_api.model.DiasSemana;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponseDto {

    private Long id;
    private DiasSemana dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}

/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RazaResponseDTO {

    private Long id;
    private String nombreRaza = "Mestizo";
    private String DescripcionRaza;
    private EspecieResponseDTO especieResponse;
}

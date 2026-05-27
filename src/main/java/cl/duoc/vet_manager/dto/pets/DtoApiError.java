/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.api_mascotas.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoApiError {

    private LocalDate timestamp; // Fecha y hora en la que ocurrió el error.
    private int status; // Código de estado HTTP del error (ej. 404, 400).
    private String error; // Nombre formal del estado HTTP (ej. "Not Found").
    private String message; // Mensaje amigable o detallado explicando la causa del error.
    private String path; // Ruta de la URL donde ocurrió el error (ej. "/mascota/1").
    private String claseException; // Nombre de la clase de excepción de Java que provocó el error.
}

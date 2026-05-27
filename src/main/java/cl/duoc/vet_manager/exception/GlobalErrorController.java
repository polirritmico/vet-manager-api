/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.webflux.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalErrorController implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof ResponseStatusException resEx) {
            HttpStatusCode status = resEx.getStatusCode();

            if (status.isSameCodeAs(HttpStatus.NOT_FOUND) || status.isSameCodeAs(HttpStatus.METHOD_NOT_ALLOWED)) {
                log.error(
                        "Container error - Status: {}, Path: {} | Message: {}",
                        status.value(),
                        exchange.getRequest().getPath().value(),
                        ex.getMessage());
            }
            exchange.getResponse().setStatusCode(status);
            return exchange.getResponse().setComplete();
        }

        return Mono.error(ex);
    }
}

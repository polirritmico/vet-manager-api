# Makefile for Spring Boot Microservices v0.1-manager

.PHONY: run clean

default: run

help:
	@echo "Available targets:"
	@echo "  run    Start the project through Maven"
	@echo "  clean  Clean the project"

run:
	./mvnw spring-boot:run

clean:
	docker compose down -v
	./mvnw clean

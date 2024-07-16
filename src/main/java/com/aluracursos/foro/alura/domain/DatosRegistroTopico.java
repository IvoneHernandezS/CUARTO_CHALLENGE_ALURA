package com.aluracursos.foro.alura.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotBlank
        String autor,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Curso curso
) {
}

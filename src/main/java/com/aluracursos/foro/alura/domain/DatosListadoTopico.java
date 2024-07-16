package com.aluracursos.foro.alura.domain;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String fecha,
        String estatus,
        String autor,
        String curso
) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),
                topico.getEstatus().toString(), topico.getAutor(), topico.getCurso().toString());
    }
}

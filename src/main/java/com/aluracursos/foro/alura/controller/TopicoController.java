package com.aluracursos.foro.alura.controller;

import com.aluracursos.foro.alura.domain.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepositorio topicoRepositorio;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico
            (@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
             UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepositorio.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos
            (@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC)
             Pageable paginacion) {
        return ResponseEntity.ok(topicoRepositorio.findAll(paginacion)
                .map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepositorio.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),topico.getFecha(),topico.getEstatus().toString(),
                topico.getAutor(),topico.getAutor(),topico.getCurso().toString()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepositorio.getReferenceById(id);
        topicoRepositorio.delete(topico);
        return ResponseEntity.noContent().build();
    }





}

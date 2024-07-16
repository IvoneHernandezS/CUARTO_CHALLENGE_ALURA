package com.aluracursos.foro.alura.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepositorio extends JpaRepository<Topico,Long> {
}

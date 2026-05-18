package com.impexp.service_gestionusuarios.repository;

import com.impexp.service_gestionusuarios.model.Gestionusuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionUsuarioRepository extends JpaRepository<Gestionusuarios, Long> {
}
package com.impexp.service_gestioncargas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.impexp.service_gestioncargas.model.GestionCargas;

public interface GestionCargasRepository extends JpaRepository<GestionCargas, Long> {
}
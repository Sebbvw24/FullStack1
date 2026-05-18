package com.impexp.service_cargas.repository;

import com.impexp.service_cargas.model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargaRepository extends JpaRepository<Carga, Long> 
{
    List<Carga> findByCategoriaCarga(String categoriaCarga);
    List<Carga> findByManejoCarga(String manejoCarga);
}
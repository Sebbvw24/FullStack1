package com.impexp.service_gestioncargas.service;

import org.springframework.stereotype.Service;
import com.impexp.service_gestioncargas.model.GestionCargas;
import com.impexp.service_gestioncargas.repository.GestionCargasRepository;
import java.util.List;

@Service
public class GestionCargasService {

    private final GestionCargasRepository gestioncargasRepository;

    
    public GestionCargasService(GestionCargasRepository gestioncargasRepository) {
        this.gestioncargasRepository = gestioncargasRepository;
    }

    public List<GestionCargas> listarTodas() {
        return gestioncargasRepository.findAll();
    }

    public GestionCargas buscarPorId(Long id) {
        return gestioncargasRepository.findById(id).orElse(null);
    }

    public GestionCargas registrarCarga(String descripcion, String estado) {
    GestionCargas nuevacarga = new GestionCargas();
    nuevacarga.setDescripcion(descripcion);
    nuevacarga.setEstado(estado);
    return gestioncargasRepository.save(nuevacarga);
    }

    public GestionCargas editarCarga(Long id, String descripcion, String estado) {
    GestionCargas cargaExistente = gestioncargasRepository.findById(id).orElse(null);

    if (cargaExistente != null) {
        cargaExistente.setDescripcion(descripcion);
        cargaExistente.setEstado(estado);

        return gestioncargasRepository.save(cargaExistente);
    }

    return null;
    }

    public void eliminarCarga(Long id) {
    gestioncargasRepository.deleteById(id);
    }
}
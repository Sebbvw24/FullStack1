package com.impexp.service_cargas.service;

import com.impexp.service_cargas.model.Carga;
import com.impexp.service_cargas.repository.CargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class CargaService {
    
    @Autowired
    private CargaRepository cargaRepository;

    private static final List<String> MANEJO_PERMITIDO     = Arrays.asList("frágil (embalaje)", "no frágil");
    private static final List<String> CATEGORIA_PERMITIDA  = Arrays.asList("químico", "en frío", "normal");

    public List<Carga> obtenerTodas() {
        return cargaRepository.findAll();
    }

    
    public Carga obtenerPorId(Long id) {
        return cargaRepository.findById(id).orElseThrow(() -> new RuntimeException("Carga con id " + id + " no encontrada."));
    }

    
    public Carga registrar(Carga carga) {
        validar(carga);
        carga.setFechaRegistro(LocalDateTime.now());
        return cargaRepository.save(carga);
    }


    public List<Carga> filtrarPorCategoria(String categoria) {
        if (!CATEGORIA_PERMITIDA.contains(categoria)) {
            throw new RuntimeException("Categoría inválida. Opciones: " + CATEGORIA_PERMITIDA);
        }
        return cargaRepository.findByCategoriaCarga(categoria);
    }


    public List<Carga> filtrarPorManejo(String manejo) {
        if (!MANEJO_PERMITIDO.contains(manejo)) {
            throw new RuntimeException("Manejo inválido. Opciones: " + MANEJO_PERMITIDO);
        }
        return cargaRepository.findByManejoCarga(manejo);
    }

    
    private void validar(Carga carga) {
        if (carga.getMaterial() == null || carga.getMaterial().isBlank())
            throw new RuntimeException("El campo material es obligatorio.");

        if (carga.getPesoKg() == null || carga.getPesoKg() <= 0)
            throw new RuntimeException("El peso debe ser mayor a 0.");

        if (!MANEJO_PERMITIDO.contains(carga.getManejoCarga()))
            throw new RuntimeException("manejo_carga inválido. Opciones: " + MANEJO_PERMITIDO);

        if (!CATEGORIA_PERMITIDA.contains(carga.getCategoriaCarga()))
            throw new RuntimeException("categoria_carga inválida. Opciones: " + CATEGORIA_PERMITIDA);
    }
}
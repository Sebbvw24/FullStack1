package com.impexp.service_gestioncargas.controller;

import org.springframework.web.bind.annotation.*;
import com.impexp.service_gestioncargas.model.GestionCargas;
import com.impexp.service_gestioncargas.service.GestionCargasService;
import java.util.List;

@RestController
@RequestMapping("/api/cargas")
public class GestionCargasController {

    private final GestionCargasService gestioncargasservice;

    
    public GestionCargasController(GestionCargasService gestioncargasservice) {
        this.gestioncargasservice = gestioncargasservice;
    }

    @GetMapping
    public List<GestionCargas> obtenerTodas() {
        return gestioncargasservice.listarTodas();
    }

    @GetMapping("/{id}")
    public GestionCargas obtenerPorId(@PathVariable Long id) {
        return gestioncargasservice.buscarPorId(id);
    }

    @PostMapping
    public GestionCargas registrarCarga(@RequestBody String descripcion, String estado) {
        return gestioncargasservice.registrarCarga(descripcion, estado);
    }

    @PutMapping("/{id}")
    public GestionCargas editar(@PathVariable Long id, @RequestBody GestionCargas carga) {
        return gestioncargasservice.editarCarga(id, carga.getDescripcion(), carga.getEstado());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        gestioncargasservice.eliminarCarga(id);
    }
}
package com.impexp.service_cargas.controller;

import com.impexp.service_cargas.model.Carga;
import com.impexp.service_cargas.service.CargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cargas")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    
    @GetMapping
    public ResponseEntity<List<Carga>> getAll() {
        return ResponseEntity.ok(cargaService.obtenerTodas());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Carga> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cargaService.obtenerPorId(id));
    }

    
    @PostMapping
    public ResponseEntity<Carga> create(@RequestBody Carga carga) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cargaService.registrar(carga));
    }

    
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Carga>> getByCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(cargaService.filtrarPorCategoria(categoria));
    }

    @GetMapping("/manejo/{manejo}")
    public ResponseEntity<List<Carga>> getByManejo(@PathVariable String manejo) {
        return ResponseEntity.ok(cargaService.filtrarPorManejo(manejo));
    }
}
package com.impexp.service_gestionusuarios.controller;

import com.impexp.service_gestionusuarios.model.Gestionusuarios;
import com.impexp.service_gestionusuarios.service.GestionUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestion")
public class GestionusuarioController {

    @Autowired
    private GestionUsuarioService gestionUsuarioService;

    @GetMapping
    public List<Gestionusuarios> listarUsuarios() {
        return gestionUsuarioService.listarUsuarios();
    }

    @PutMapping("/{id}")
    public Gestionusuarios actualizarUsuario(@PathVariable Long id, @RequestBody Gestionusuarios usuario) {

        return gestionUsuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        gestionUsuarioService.eliminarUsuario(id);
    }
}
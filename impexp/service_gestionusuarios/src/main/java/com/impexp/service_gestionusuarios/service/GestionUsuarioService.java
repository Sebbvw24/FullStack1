package com.impexp.service_gestionusuarios.service;

import com.impexp.service_gestionusuarios.model.Gestionusuarios;
import com.impexp.service_gestionusuarios.repository.GestionUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionUsuarioService {

    @Autowired
    private GestionUsuarioRepository gestionusuarioRepository;

    public List<Gestionusuarios> listarUsuarios() {
        return gestionusuarioRepository.findAll();
    }

    public Gestionusuarios actualizarUsuario(Long id, Gestionusuarios gestionusuario) {

        Optional<Gestionusuarios> usuarioExistente = gestionusuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {

            Gestionusuarios user = usuarioExistente.get();

            user.setNombreEmpresa(gestionusuario.getNombreEmpresa());
            user.setRut(gestionusuario.getRut());
            user.setNumeroContacto(gestionusuario.getNumeroContacto());
            user.setGmail(gestionusuario.getGmail());
            user.setHorarioAtencion(gestionusuario.getHorarioAtencion());

            return gestionusuarioRepository.save(user);
        }

        return null;
    }

    public void eliminarUsuario(Long id) {
        gestionusuarioRepository.deleteById(id);
    }
}
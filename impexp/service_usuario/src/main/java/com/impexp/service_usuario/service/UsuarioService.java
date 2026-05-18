package com.impexp.service_usuario.service;

import com.impexp.service_usuario.model.Usuario;
import com.impexp.service_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuariorepository;

    public List<Usuario> listarUsuarios() {
        return usuariorepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuariorepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuario(Long id) {
        return usuariorepository.findById(id);
    }

    public void eliminarUsuario(Long id) {
        usuariorepository.deleteById(id);
    }
}
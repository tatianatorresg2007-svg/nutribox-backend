package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.Usuario;
import com.nutribox.NutriBox.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public Usuario registrar(Usuario usuario) {
        // Evita registrar dos veces el mismo email
        usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Ya existe un usuario registrado con ese email");
        });

        if (usuario.getRol() == null) {
            usuario.setRol(Usuario.RolUsuario.CLIENTE); // por defecto, nadie se auto-asigna ADMIN
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        Usuario usuario = obtenerPorId(id);
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
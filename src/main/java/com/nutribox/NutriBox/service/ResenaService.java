package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.Resena;
import com.nutribox.NutriBox.entity.Usuario;
import com.nutribox.NutriBox.repository.ResenaRepository;
import com.nutribox.NutriBox.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Resena> listarPorMenu(Long menuId) {
        return resenaRepository.findByMenuId(menuId);
    }

    public Resena crear(Resena resena) {
        // Trae el nombre real del usuario en vez de confiar en lo que mande el cliente
        Usuario usuario = usuarioRepository.findById(resena.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        resena.setNombreUsuario(usuario.getNombre());

        if (resena.getCalificacion() < 1 || resena.getCalificacion() > 5) {
            throw new RuntimeException("La calificación debe estar entre 1 y 5");
        }

        return resenaRepository.save(resena);
    }
}
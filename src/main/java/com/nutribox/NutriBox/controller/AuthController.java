package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.config.JwtUtil;
import com.nutribox.NutriBox.dto.LoginRequest;
import com.nutribox.NutriBox.dto.LoginResponse;
import com.nutribox.NutriBox.entity.Usuario;
import com.nutribox.NutriBox.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body("Email o contraseña incorrectos");
        }

        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol().name(), usuario.getId());

        return ResponseEntity.ok(new LoginResponse(
                token, usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRol().name()
        ));
    }
}
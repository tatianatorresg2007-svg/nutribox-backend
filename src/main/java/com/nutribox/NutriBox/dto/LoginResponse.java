package com.nutribox.NutriBox.dto;

public class LoginResponse {
    private String token;
    private Long usuarioId;
    private String nombre;
    private String email;
    private String rol;

    public LoginResponse(String token, Long usuarioId, String nombre, String email, String rol) {
        this.token = token;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public String getToken() { return token; }
    public Long getUsuarioId() { return usuarioId; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}
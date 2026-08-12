package com.nutribox.NutriBox.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.bucket}")
    private String bucket;

    public String subirImagen(MultipartFile archivo) throws IOException {
        String extension = obtenerExtension(archivo.getOriginalFilename());
        String nombreArchivo = UUID.randomUUID().toString() + extension;

        WebClient webClient = WebClient.builder()
                .baseUrl(supabaseUrl)
                .build();

        webClient.post()
                .uri("/storage/v1/object/" + bucket + "/" + nombreArchivo)
                .header("apikey", supabaseKey)
                .header("Authorization", "Bearer " + supabaseKey)
                .header("Content-Type", archivo.getContentType())
                .bodyValue(archivo.getBytes())
                .retrieve()
                .toBodilessEntity()
                .block();

        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + nombreArchivo;
    }

    private String obtenerExtension(String nombreOriginal) {
        if (nombreOriginal == null || !nombreOriginal.contains(".")) {
            return ".jpg";
        }
        return nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
    }
}
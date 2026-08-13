package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.Menu;
import com.nutribox.NutriBox.service.MenuService;
import com.nutribox.NutriBox.service.SupabaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private SupabaseStorageService supabaseStorageService;

    @GetMapping
    public List<Menu> listarTodos() {
        return menuService.listarTodos();
    }

    @GetMapping("/disponibles")
    public List<Menu> listarDisponibles() {
        return menuService.listarDisponibles();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Menu> listarPorCategoria(@PathVariable Menu.CategoriaMenu categoria) {
        return menuService.listarPorCategoria(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.obtenerPorId(id));
    }

    // ===== Crear/actualizar vía JSON (sin imagen, se manda imagenUrl como texto) =====

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> crear(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.crear(menu));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> actualizar(@PathVariable Long id, @RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.actualizar(id, menu));
    }

    // ===== Crear/actualizar vía formulario admin, con imagen incluida =====

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Menu> crearConImagen(
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam("categoria") Menu.CategoriaMenu categoria,
            @RequestParam(value = "disponible", defaultValue = "true") Boolean disponible,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {
        Menu menu = new Menu();
        menu.setNombre(nombre);
        menu.setDescripcion(descripcion);
        menu.setPrecio(precio);
        menu.setCategoria(categoria);
        menu.setDisponible(disponible);

        if (imagen != null && !imagen.isEmpty()) {
            String urlImagen = supabaseStorageService.subirImagen(imagen);
            menu.setImagenUrl(urlImagen);
        }

        return ResponseEntity.ok(menuService.crear(menu));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Menu> actualizarConImagen(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam("categoria") Menu.CategoriaMenu categoria,
            @RequestParam(value = "disponible", defaultValue = "true") Boolean disponible,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {
        Menu menu = new Menu();
        menu.setNombre(nombre);
        menu.setDescripcion(descripcion);
        menu.setPrecio(precio);
        menu.setCategoria(categoria);
        menu.setDisponible(disponible);

        if (imagen != null && !imagen.isEmpty()) {
            String urlImagen = supabaseStorageService.subirImagen(imagen);
            menu.setImagenUrl(urlImagen);
        }
        // Si no se manda imagen nueva, el service conserva la anterior

        return ResponseEntity.ok(menuService.actualizar(id, menu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        menuService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
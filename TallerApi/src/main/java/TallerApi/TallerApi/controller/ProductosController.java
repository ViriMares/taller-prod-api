package TallerApi.TallerApi.controller;

import TallerApi.TallerApi.dtos.ProductosDTO;
import TallerApi.TallerApi.models.Productos;
import TallerApi.TallerApi.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @PostMapping
    public Productos crearProductos(@RequestBody ProductosDTO productosDTO) {
        return productosService.crearProductos(productosDTO);
    }

    @GetMapping
    public List<Map<String, Object>> obtenerProductos(
            @RequestParam(required = false) String categoria) {

        if (categoria != null && !categoria.isEmpty()) {
            return productosService.obtenerProductosPorCategoria(categoria);
        }
        return productosService.obtenerTodosProductos();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Content-Type") String contentType) {

        if (!contentType.startsWith("multipart/form-data")) {
            return ResponseEntity.badRequest().body("Content-Type must be multipart/form-data");
        }

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        try {
            String fileName = productosService.guardarImagen(file);
            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading file: " + e.getMessage());
        }
    }
}
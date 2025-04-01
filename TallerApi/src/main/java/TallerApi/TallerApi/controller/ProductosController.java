package TallerApi.TallerApi.controller;

import TallerApi.TallerApi.dtos.ProductosDTO;
import TallerApi.TallerApi.models.Productos;
import TallerApi.TallerApi.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    @Autowired
    private ProductosService productosService;

    @PostMapping

    public Productos crearProductos(@RequestBody ProductosDTO productosDTO) {
        return productosService.crearProductos(productosDTO);
    }
}

package TallerApi.TallerApi.service;

import TallerApi.TallerApi.dtos.ProductosDTO;
import TallerApi.TallerApi.models.Productos;
import TallerApi.TallerApi.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    public Productos crearProductos(ProductosDTO productosDTO) {
        Productos productos = new Productos();
        productos.setNombreProducto(productosDTO.getNombreProducto());
        productos.setPrecio(productosDTO.getPrecio());
        productos.setStock(productosDTO.getStock());
        if (productosDTO.getFechaActualizacion() == null) {
            productos.setFechaActualizacion(LocalDateTime.now());
        } else {
            productos.setFechaActualizacion(productosDTO.getFechaActualizacion());
        }

        return productosRepository.save(productos);
    }
}
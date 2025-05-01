package TallerApi.TallerApi.service;

import TallerApi.TallerApi.dtos.ProductosDTO;
import TallerApi.TallerApi.models.Catalogo;
import TallerApi.TallerApi.models.Productos;
import TallerApi.TallerApi.repository.CatalogoRepository;
import TallerApi.TallerApi.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public Productos crearProductos(ProductosDTO productosDTO) {
        Productos productos = new Productos();
        productos.setNombreProducto(productosDTO.getNombreProducto());
        productos.setPrecio(productosDTO.getPrecio());
        productos.setStock(productosDTO.getStock());
        productos.setFechaActualizacion(
                productosDTO.getFechaActualizacion() != null ?
                        productosDTO.getFechaActualizacion() :
                        LocalDateTime.now()
        );

        Productos productoGuardado = productosRepository.save(productos);

        Catalogo catalogo = new Catalogo();
        catalogo.setProducto(productoGuardado);
        catalogo.setDescripcion(productosDTO.getDescripcion());
        catalogo.setImagenUrl(productosDTO.getImagenUrl());
        catalogo.setCategoria(productosDTO.getCategoria());

        catalogoRepository.save(catalogo);

        return productoGuardado;
    }

    public List<Map<String, Object>> obtenerTodosProductos() {
        Page<Object[]> page = productosRepository.findAllWithCatalogo(PageRequest.of(0, 50));
        return mapResults(page.getContent());
    }

    public List<Map<String, Object>> obtenerProductosPorCategoria(String categoria) {
        Page<Object[]> page = productosRepository.findProductosByCategoria(
                categoria,
                PageRequest.of(0, 50)
        );
        return mapResults(page.getContent());
    }

    private List<Map<String, Object>> mapResults(List<Object[]> results) {
        return results.stream().map(result -> {
            Map<String, Object> productoMap = new LinkedHashMap<>();
            Productos producto = (Productos) result[0];
            Catalogo catalogo = (Catalogo) result[1];

            productoMap.put("idProducto", producto.getIdProducto());
            productoMap.put("nombreProducto", producto.getNombreProducto());
            productoMap.put("precio", producto.getPrecio());
            productoMap.put("stock", producto.getStock());
            productoMap.put("fechaActualizacion", producto.getFechaActualizacion());

            if (catalogo != null) {
                productoMap.put("descripcion", catalogo.getDescripcion());
                productoMap.put("imagenUrl", catalogo.getImagenUrl());
                productoMap.put("categoria", catalogo.getCategoria());
            }

            return productoMap;
        }).collect(Collectors.toList());
    }

    public String guardarImagen(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío");
        }

        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }
}
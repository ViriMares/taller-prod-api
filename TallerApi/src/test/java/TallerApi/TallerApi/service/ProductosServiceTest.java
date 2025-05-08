package TallerApi.TallerApi.service;

import TallerApi.TallerApi.dtos.ProductosDTO;
import TallerApi.TallerApi.models.Catalogo;
import TallerApi.TallerApi.models.Productos;
import TallerApi.TallerApi.repository.CatalogoRepository;
import TallerApi.TallerApi.repository.ProductosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductosServiceTest {

    @Mock
    private ProductosRepository productosRepository;

    @Mock
    private CatalogoRepository catalogoRepository;

    @InjectMocks
    private ProductosService productosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProductos_CreaProductoYCatalogo_Correctamente() {
        ProductosDTO dto = new ProductosDTO();
        dto.setNombreProducto("Test");
        dto.setPrecio(100.0);
        dto.setStock(10);
        dto.setDescripcion("Desc");
        dto.setImagenUrl("url");
        dto.setCategoria("Cat");
        dto.setFechaActualizacion(LocalDateTime.of(2025,1,1,12,0));

        Productos saved = new Productos();
        saved.setIdProducto(1);
        when(productosRepository.save(any(Productos.class))).thenReturn(saved);
        when(catalogoRepository.save(any(Catalogo.class))).thenReturn(new Catalogo());

        Productos result = productosService.crearProductos(dto);

        assertNotNull(result);
        assertEquals(1, result.getIdProducto());
        verify(productosRepository, times(1)).save(any(Productos.class));
        verify(catalogoRepository, times(1)).save(any(Catalogo.class));
    }

    @Test
    void guardarImagen_ArchivoVacio_LanzaException() {
        org.springframework.mock.web.MockMultipartFile file =
                new org.springframework.mock.web.MockMultipartFile("file", new byte[0]);
        assertThrows(IllegalArgumentException.class, () -> productosService.guardarImagen(file));
    }
}
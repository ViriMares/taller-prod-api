package TallerApi.TallerApi.controller;

import TallerApi.TallerApi.models.Productos;
import TallerApi.TallerApi.service.ProductosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductosController.class)
class ProductosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductosService productosService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void crearProductos_RetornaProducto_JSON() throws Exception {
        Productos prod = new Productos(); prod.setIdProducto(1);
        when(productosService.crearProductos(any())).thenReturn(prod);

        String body = mapper.writeValueAsString(Collections.singletonMap("nombreProducto","Test"));

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1));

        verify(productosService, times(1)).crearProductos(any());
    }
}

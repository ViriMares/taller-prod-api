package TallerApi.TallerApi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TallerApiApplicationTests {

	@Test
	void contextLoads() {
		Product dummyProduct = new Product(1L, "Camara", 10, 199.99);
		when(service.findById(1L)).thenReturn(dummyProduct);

		// Act
		ResponseEntity<Product> response = controller.getById(1L);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Camiseta", response.getBody().getName());
		assertEquals(10, response.getBody().getStock());
	}

}

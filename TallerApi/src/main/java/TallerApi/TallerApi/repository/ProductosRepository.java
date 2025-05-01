package TallerApi.TallerApi.repository;

import TallerApi.TallerApi.models.Productos;
import TallerApi.TallerApi.models.Catalogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    @Query("SELECT p, c FROM Productos p LEFT JOIN Catalogo c ON p.idProducto = c.producto.idProducto")
    Page<Object[]> findAllWithCatalogo(Pageable pageable);

    @Query("SELECT p, c FROM Productos p JOIN Catalogo c ON p.idProducto = c.producto.idProducto WHERE c.categoria = :categoria")
    Page<Object[]> findProductosByCategoria(@Param("categoria") String categoria, Pageable pageable);
}
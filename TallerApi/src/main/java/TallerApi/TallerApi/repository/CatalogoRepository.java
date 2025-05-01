package TallerApi.TallerApi.repository;

import TallerApi.TallerApi.models.Catalogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {
    @Query("SELECT p, c FROM Productos p JOIN Catalogo c ON p.idProducto = c.producto.idProducto WHERE c.categoria = :categoria")
    List<Object[]> findProductosByCategoria(@Param("categoria") String categoria);
    Page<Catalogo> findByCategoria(String categoria, Pageable pageable);
    List<Catalogo> findByCategoria(String categoria);
}
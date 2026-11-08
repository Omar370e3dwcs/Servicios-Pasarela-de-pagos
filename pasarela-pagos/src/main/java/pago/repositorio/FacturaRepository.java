package pago.repositorio;

import pago.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    
    Optional<Factura> findByNumeroFactura(String numeroFactura);
    List<Factura> findByIdPago(Integer idPago);
    List<Factura> findByEstado(String estado);
}

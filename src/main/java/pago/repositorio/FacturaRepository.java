package pago.repositorio;

import pago.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    Optional<Factura> findByIdAndOrganizacionId(Integer id, String organizacionId);
    List<Factura> findAllByOrganizacionId(String organizacionId);
    List<Factura> findByOrganizacionIdAndIdPago(String organizacionId, Integer idPago);
}

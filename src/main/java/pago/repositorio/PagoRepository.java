package pago.repositorio;

import pago.modelo.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;



@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    Optional<Pago> findByIdAndOrganizacionId(Integer id, String organizacionId);
    List<Pago> findAllByOrganizacionId(String organizacionId);
    List<Pago> findByOrganizacionIdAndIdPaciente(String organizacionId, Integer idPaciente);
}

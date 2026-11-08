package pago.repositorio;

import pago.modelo.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    
    
    List<Pago> findByIdPaciente(Integer idPaciente);
    List<Pago> findByEstado(String estado);
    List<Pago> findByIdPacienteAndEstado(Integer idPaciente, String estado);
}

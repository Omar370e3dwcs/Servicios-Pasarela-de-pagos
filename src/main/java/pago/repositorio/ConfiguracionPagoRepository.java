package pago.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import pago.modelo.ConfiguracionPago;

public interface ConfiguracionPagoRepository
        extends JpaRepository<ConfiguracionPago, String> {
}

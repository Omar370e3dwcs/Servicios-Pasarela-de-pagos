package pago.servicio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pago.dto.FacturaDatos;
import pago.modelo.Pago;
import pago.repositorio.FacturaRepository;
import pago.repositorio.PagoRepository;
import pago.saas.ContextoOrganizacion;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AislamientoOrganizacionTest {
    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private FacturaRepository facturaRepository;

    @AfterEach
    void limpiarContexto() {
        ContextoOrganizacion.limpiar();
    }

    @Test
    void consultarPagosUsaSolamenteLaOrganizacionActual() {
        ContextoOrganizacion.establecer("ORG-A", "usuario-a", "ADMIN");
        Pago pago = new Pago();
        pago.setOrganizacionId("ORG-A");
        when(pagoRepository.findAllByOrganizacionId("ORG-A")).thenReturn(List.of(pago));

        List<Pago> resultado = new PagoService(pagoRepository).todos();

        assertThat(resultado).containsExactly(pago);
        verify(pagoRepository).findAllByOrganizacionId("ORG-A");
    }

    @Test
    void noPermiteObtenerUnPagoDeOtraOrganizacion() {
        ContextoOrganizacion.establecer("ORG-A", "usuario-a", "ADMIN");
        when(pagoRepository.findByIdAndOrganizacionId(91, "ORG-A"))
            .thenReturn(Optional.empty());

        assertThatThrownBy(() -> new PagoService(pagoRepository).obtener(91))
            .isInstanceOf(ResponseStatusException.class)
            .satisfies(error -> assertThat(((ResponseStatusException) error).getStatusCode().value())
                .isEqualTo(404));
    }

    @Test
    void noGeneraFacturaParaUnPagoDeOtraOrganizacion() {
        ContextoOrganizacion.establecer("ORG-A", "usuario-a", "FINANZAS");
        FacturaDatos datos = new FacturaDatos(42, new BigDecimal("100.00"));
        when(pagoRepository.findByIdAndOrganizacionId(42, "ORG-A"))
            .thenReturn(Optional.empty());

        FacturaService servicio = new FacturaService(facturaRepository, pagoRepository);

        assertThatThrownBy(() -> servicio.generar(datos))
            .isInstanceOf(ResponseStatusException.class)
            .hasMessageContaining("no pertenece a la organización");
    }
}

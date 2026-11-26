package pago.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pago.modelo.ConfiguracionPago;
import pago.repositorio.ConfiguracionPagoRepository;
import pago.saas.ContextoOrganizacion;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/configuracion")
public class ConfiguracionPagoControlador {
    private final ConfiguracionPagoRepository repositorio;

    public ConfiguracionPagoControlador(ConfiguracionPagoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public ConfiguracionPago obtener() {
        return repositorio.findById(ContextoOrganizacion.organizacionActual())
            .orElseThrow(() -> new ResponseStatusException(
                NOT_FOUND, "Configuración no encontrada"));
    }

    @PutMapping
    public ConfiguracionPago guardar(@RequestBody ConfiguracionPago datos) {
        if (datos.getMoneda() == null || datos.getMoneda().isBlank()
                || datos.getMoneda().length() != 3
                || datos.getProveedor() == null || datos.getProveedor().isBlank()) {
            throw new ResponseStatusException(
                BAD_REQUEST, "Moneda ISO de tres letras y proveedor son obligatorios");
        }
        String organizacionId = ContextoOrganizacion.organizacionActual();
        ConfiguracionPago configuracion =
            repositorio.findById(organizacionId).orElseGet(ConfiguracionPago::new);
        configuracion.setOrganizacionId(organizacionId);
        configuracion.setMoneda(datos.getMoneda());
        configuracion.setProveedor(datos.getProveedor());
        configuracion.setPermitePagoParcial(datos.isPermitePagoParcial());
        return repositorio.save(configuracion);
    }
}

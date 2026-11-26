package pago.servicio;

import pago.dto.FacturaDatos;
import pago.modelo.Factura;
import pago.repositorio.FacturaRepository;
import pago.repositorio.PagoRepository;
import pago.saas.ContextoOrganizacion;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.util.List;

@Service
public class FacturaService {
    
    
    private FacturaRepository repo;
    private PagoRepository pagoRepo;
    
    
    public FacturaService(FacturaRepository repo, PagoRepository pagoRepo){
        this.repo = repo;
        this.pagoRepo = pagoRepo;
    }
    
    
    
    // Genera una factura nuevo
    
    
    public Factura generar(FacturaDatos datos){
        if(datos.getIdPago() == null || 
           datos.getTotal() == null){
            throw new ResponseStatusException(BAD_REQUEST, "Falta id pago o total");
        }

        String organizacionId = ContextoOrganizacion.organizacionActual();
        pagoRepo.findByIdAndOrganizacionId(datos.getIdPago(), organizacionId)
            .orElseThrow(() -> new ResponseStatusException(
                BAD_REQUEST, "El pago no pertenece a la organización"));

        String numero = "FAC-" + organizacionId + "-" + System.currentTimeMillis();
        
        Factura f = new Factura();
        f.setOrganizacionId(organizacionId);
        f.setIdPago(datos.getIdPago());
        f.setNumeroFactura(numero);
        f.setTotal(datos.getTotal());
        f.setEstado("emitida");
        
        return repo.save(f);
    }
    
    
    
    public Factura obtener(Integer id){
        return buscar(id);
    }
    
    // Obtiene todas
    
    public List<Factura> todas(){
        return repo.findAllByOrganizacionId(ContextoOrganizacion.organizacionActual());
    }
    
    
    public Factura anular(Integer id){
        Factura f = buscar(id);
        f.setEstado("anulada");
        return repo.save(f);
    }

    private Factura buscar(Integer id) {
        return repo.findByIdAndOrganizacionId(
                id, ContextoOrganizacion.organizacionActual())
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Factura no encontrada"));
    }
}

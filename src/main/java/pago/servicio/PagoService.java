package pago.servicio;

import pago.dto.PagoDatos;
import pago.modelo.Pago;
import pago.repositorio.PagoRepository;
import pago.saas.ContextoOrganizacion;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.util.List;



@Service
public class PagoService {
    
    private PagoRepository repo;
    
    public PagoService(PagoRepository repo){
        this.repo = repo;
    }
    
    // Registra un nuevo pago
    
    
    public Pago registrar(PagoDatos datos){
        if(datos.getIdPaciente() == null || 
           datos.getMonto() == null){
            throw new ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST,
                "Falta id paciente o monto");
        }
        
        Pago p = new Pago();
        p.setOrganizacionId(ContextoOrganizacion.organizacionActual());
        p.setIdPaciente(datos.getIdPaciente());
        p.setIdCita(datos.getIdCita());
        p.setMonto(datos.getMonto());
        p.setMetodoPago(datos.getMetodoPago());
        p.setReferenciaPago(datos.getReferenciaPago());
        p.setObservaciones(datos.getObservaciones());
        p.setEstado("pendiente");
        
        return repo.save(p);
    }
    
    public Pago obtener(Integer id){
        return buscar(id);
    }
    
    
    
    // Retorna todos los pagos
    public List<Pago> todos(){
        return repo.findAllByOrganizacionId(ContextoOrganizacion.organizacionActual());
    }
    
    public Pago confirmar(Integer id){
        Pago p = buscar(id);
        p.setEstado("confirmado");
        return repo.save(p);
    }
    
    // Cancela el pago
    
    public Pago cancelar(Integer id){
        Pago p = buscar(id);
        p.setEstado("cancelado");
        return repo.save(p);
    }

    public Pago buscar(Integer id) {
        return repo.findByIdAndOrganizacionId(
                id, ContextoOrganizacion.organizacionActual())
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pago no encontrado"));
    }
}

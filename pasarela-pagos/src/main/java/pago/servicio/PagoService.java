package pago.servicio;

import pago.dto.PagoDatos;
import pago.modelo.Pago;
import pago.repositorio.PagoRepository;
import org.springframework.stereotype.Service;
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
            throw new RuntimeException("Falta id paciente o monto");
        }
        
        Pago p = new Pago();
        p.setIdPaciente(datos.getIdPaciente());
        p.setMonto(datos.getMonto());
        p.setMetodoPago(datos.getMetodoPago());
        p.setEstado("pendiente");
        
        return repo.save(p);
    }
    
    public Pago obtener(Integer id){
        return repo.findById(id).orElse(null);
    }
    
    
    
    // Retorna todos los pagos
    public List<Pago> todos(){
        return repo.findAll();
    }
    
    public Pago confirmar(Integer id){
        Pago p = repo.findById(id).orElse(null);
        if(p != null){
            p.setEstado("confirmado");
            repo.save(p);
        }
        return p;
    }
    
    // Cancela el pago
    
    public Pago cancelar(Integer id){
        Pago p = repo.findById(id).orElse(null);
        
        if(p != null){
            p.setEstado("cancelado");
            repo.save(p);
        }
        
        return p;
    }
}

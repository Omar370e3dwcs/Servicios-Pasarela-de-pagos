package pago.servicio;

import pago.dto.FacturaDatos;
import pago.modelo.Factura;
import pago.repositorio.FacturaRepository;
import pago.repositorio.PagoRepository;
import org.springframework.stereotype.Service;
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
            throw new RuntimeException("Falta id pago o total");
        }
        
        
    
        String numero = "FAC-" + System.currentTimeMillis();
        
        Factura f = new Factura();
        f.setIdPago(datos.getIdPago());
        f.setNumeroFactura(numero);
        f.setTotal(datos.getTotal());
        f.setEstado("emitida");
        
        return repo.save(f);
    }
    
    
    
    public Factura obtener(Integer id){
        return repo.findById(id).orElse(null);
    
    }
    
    // Obtiene todas
    
    public List<Factura> todas(){
        return repo.findAll();
    }
    
    
    public Factura anular(Integer id){
        Factura f = repo.findById(id).orElse(null);
        
        if(f != null){
            f.setEstado("anulada");
            repo.save(f);
        }
        
        
        
        return f;
    }
}

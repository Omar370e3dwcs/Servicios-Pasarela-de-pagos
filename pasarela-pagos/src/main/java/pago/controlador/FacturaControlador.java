package pago.controlador;


import pago.dto.FacturaDatos;
import pago.modelo.Factura;
import pago.servicio.FacturaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/facturas")
public class FacturaControlador {
    
    
    private FacturaService svc;
    
    public FacturaControlador(FacturaService svc){
        this.svc = svc;
    }
 
    
    
    @PostMapping("/generar")
    public Factura generar(@RequestBody FacturaDatos datos){
        return svc.generar(datos);
    }
    
    @GetMapping("/{id}")
    public Factura obtener(@PathVariable Integer id){
        
        return svc.obtener(id);
    }
    
    
    @GetMapping("/todas")
    public List<Factura> todas(){
        return svc.todas();
    }
    
    
    @PutMapping("/anular/{id}")
    public Factura anular(@PathVariable Integer id){
        return svc.anular(id);
    }
}

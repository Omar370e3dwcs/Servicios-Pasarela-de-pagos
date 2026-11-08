package pago.controlador;


import pago.dto.PagoDatos;
import pago.modelo.Pago;
import pago.servicio.PagoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/pagos")
public class PagoControlador {
    
    
    private PagoService svc;
    
    public PagoControlador(PagoService svc){
        this.svc = svc;
    }
    
    
    @PostMapping("/registrar")
    public Pago registrar(@RequestBody PagoDatos datos){
        
        return svc.registrar(datos);
    }
    
    
    @GetMapping("/{id}")
    public Pago obtener(@PathVariable Integer id){
        return svc.obtener(id);
    }
    
    
    @GetMapping("/todos")
    public List<Pago> todos(){
        return svc.todos();
    }
    
    
    @PutMapping("/confirmar/{id}")
    public Pago confirmar(@PathVariable Integer id){
        
        return svc.confirmar(id);
    }
    
    
    @PutMapping("/cancelar/{id}")
    public Pago cancelar(@PathVariable Integer id){
        return svc.cancelar(id);
    }
}

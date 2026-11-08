package pago.dto;

import java.math.BigDecimal;


public class PagoDatos {
    
    
    
    private Integer idPaciente;
    private Integer idCita;
    private BigDecimal monto;
    private String metodoPago;
    private String referenciaPago;
    private String observaciones;
    
    

    public PagoDatos() {}

    
    public PagoDatos(Integer idPaciente, BigDecimal monto, String metodoPago) {
        this.idPaciente = idPaciente;
        this.monto = monto;
        this.metodoPago = metodoPago;
        
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    
    public Integer getIdCita() {
        
        return idCita;
        
    }
    
    

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public BigDecimal getMonto() {
        return monto;
        
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
        
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    
    
    public String getReferenciaPago() {
        return referenciaPago;
        
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public String getObservaciones() {
        return observaciones;
        
    }

    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
         
    }
}

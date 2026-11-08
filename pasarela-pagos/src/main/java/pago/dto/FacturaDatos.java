package pago.dto;



import java.math.BigDecimal;


public class FacturaDatos {
    
    
    private Integer idPago;
    private String numeroFactura;
    private BigDecimal total;

    
    public FacturaDatos() {}

    
    public FacturaDatos(Integer idPago, BigDecimal total) {
        this.idPago = idPago;
        this.total = total;
    }

    
    public Integer getIdPago() {
        return idPago;
    }

    
    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }
    
    

    public String getNumeroFactura() {
        return numeroFactura;
    }
    

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getTotal() {
        return total;
    }
    

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

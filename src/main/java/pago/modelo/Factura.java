package pago.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas",
       uniqueConstraints = @UniqueConstraint(
           name = "uk_factura_org_numero",
           columnNames = {"organizacion_id", "numero_factura"}),
       indexes = @Index(name = "idx_facturas_organizacion", columnList = "organizacion_id"))
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "organizacion_id", nullable = false, length = 64)
    private String organizacionId;

    @Column(name = "id_pago", nullable = false)
    private Integer idPago;
    
    
    @Column(name = "numero_factura")
    private String numeroFactura;
    
    @Column(nullable = false)
    private BigDecimal total;
    
    
    @Column(nullable = false)
    private String estado = "emitida";
    
    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision = LocalDateTime.now();

    public String getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(String organizacionId) {
        this.organizacionId = organizacionId;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}

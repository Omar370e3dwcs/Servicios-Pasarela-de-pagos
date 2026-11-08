package pago.modelo;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "pagos")

public class Pago {
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;
    
    @Column(name = "id_cita")
    private Integer idCita;
    
    
    @Column(nullable = false)
    private BigDecimal monto;
    
    @Column(name = "metodo_pago")
    private String metodoPago;
    
    
    @Column(nullable = false)
    private String estado = "pendiente";
    
    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago = LocalDateTime.now();
    
    
    @Column(name = "referencia_pago")
    private String referenciaPago;
    
    private String observaciones;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }
    

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
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


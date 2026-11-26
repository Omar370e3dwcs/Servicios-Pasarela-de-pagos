package pago.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "configuraciones_pago")
public class ConfiguracionPago {
    @Id
    @Column(name = "organizacion_id", length = 64)
    private String organizacionId;

    @Column(nullable = false, length = 3)
    private String moneda = "MXN";

    @Column(nullable = false, length = 64)
    private String proveedor = "SIMULADO";

    @Column(nullable = false)
    private boolean permitePagoParcial;

    public String getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(String organizacionId) {
        this.organizacionId = organizacionId;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public boolean isPermitePagoParcial() {
        return permitePagoParcial;
    }

    public void setPermitePagoParcial(boolean permitePagoParcial) {
        this.permitePagoParcial = permitePagoParcial;
    }
}

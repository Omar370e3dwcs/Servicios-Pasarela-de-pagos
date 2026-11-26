package pago.saas;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public final class ContextoOrganizacion {
    private static final ThreadLocal<IdentidadOrganizacion> CONTEXTO = new ThreadLocal<>();

    private ContextoOrganizacion() {
    }

    public static void establecer(String organizacionId, String usuarioId, String rol) {
        CONTEXTO.set(new IdentidadOrganizacion(organizacionId, usuarioId, rol));
    }

    public static String organizacionActual() {
        return actual().organizacionId();
    }

    public static String usuarioActual() {
        return actual().usuarioId();
    }

    public static String rolActual() {
        return actual().rol();
    }

    public static void limpiar() {
        CONTEXTO.remove();
    }

    private static IdentidadOrganizacion actual() {
        IdentidadOrganizacion identidad = CONTEXTO.get();
        if (identidad == null) {
            throw new ResponseStatusException(
                INTERNAL_SERVER_ERROR, "No existe un contexto de organización");
        }
        return identidad;
    }
}

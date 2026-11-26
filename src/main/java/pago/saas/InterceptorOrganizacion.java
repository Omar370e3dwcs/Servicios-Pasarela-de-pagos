package pago.saas;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

@Component
public class InterceptorOrganizacion implements HandlerInterceptor {
    private static final String HEADER_ORGANIZACION = "X-Organization-Id";
    private static final String HEADER_USUARIO = "X-User-Id";
    private static final String HEADER_ROL = "X-User-Role";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String organizacionId = encabezado(request, HEADER_ORGANIZACION);
        String usuarioId = encabezado(request, HEADER_USUARIO);
        String rol = encabezado(request, HEADER_ROL);

        if (organizacionId == null || usuarioId == null || rol == null) {
            return rechazar(response, 401,
                "Se requieren X-Organization-Id, X-User-Id y X-User-Role");
        }

        String rolNormalizado = rol.toUpperCase();
        if (!esRolValido(rolNormalizado)) {
            return rechazar(response, 403, "Rol de usuario no válido");
        }
        if (esMutacion(request) && !puedeModificar(rolNormalizado)) {
            return rechazar(response, 403, "El rol no puede modificar registros");
        }
        if (request.getRequestURI().contains("/configuracion")
                && !"ADMIN".equals(rolNormalizado)) {
            return rechazar(response, 403, "Solo ADMIN puede gestionar configuración");
        }

        ContextoOrganizacion.establecer(organizacionId, usuarioId, rolNormalizado);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        ContextoOrganizacion.limpiar();
    }

    private static String encabezado(HttpServletRequest request, String nombre) {
        String valor = request.getHeader(nombre);
        return valor == null || valor.isBlank() ? null : valor.trim();
    }

    private static boolean esRolValido(String rol) {
        return "ADMIN".equals(rol)
            || "FINANZAS".equals(rol)
            || "LECTOR".equals(rol)
            || "SERVICIO".equals(rol);
    }

    private static boolean esMutacion(HttpServletRequest request) {
        return !"GET".equalsIgnoreCase(request.getMethod())
            && !"OPTIONS".equalsIgnoreCase(request.getMethod());
    }

    private static boolean puedeModificar(String rol) {
        return "ADMIN".equals(rol)
            || "FINANZAS".equals(rol)
            || "SERVICIO".equals(rol);
    }

    private static boolean rechazar(
            HttpServletResponse response,
            int estado,
            String mensaje) throws Exception {
        response.setStatus(estado);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"" + mensaje + "\"}");
        return false;
    }
}

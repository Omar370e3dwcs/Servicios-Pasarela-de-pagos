package pago.configuracion;

import pago.saas.InterceptorOrganizacion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {
    private final InterceptorOrganizacion interceptor;
    private final String[] allowedOrigins;

    public ConfiguracionWeb(
            InterceptorOrganizacion interceptor,
            @Value("${app.cors.allowed-origins:}") String[] allowedOrigins) {
        this.interceptor = interceptor;
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/api/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (allowedOrigins.length == 1 && allowedOrigins[0].isBlank()) {
            return;
        }
        registry.addMapping("/api/**")
            .allowedOrigins(allowedOrigins)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders(
                "Content-Type",
                "X-Organization-Id",
                "X-User-Id",
                "X-User-Role")
            .maxAge(3600);
    }
}

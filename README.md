# Servicio de pagos

Servicio REST para registrar pagos y generar facturas de un consultorio dental. Está hecho con Spring Boot y guarda la información en PostgreSQL.

El servicio registra y cambia el estado de los pagos; **no procesa pagos reales con bancos ni plataformas de pago**. Tampoco incluye una página web.

## Requisitos

- Java 17 o superior.
- Maven.
- PostgreSQL.
- NetBeans o alguna herramienta para ejecutar el proyecto (opcional).

## Configuración

1. Crea una base de datos en PostgreSQL llamada `pagos_soa`.
2. En la carpeta del proyecto que contiene `pom.xml`, configura la conexión a PostgreSQL. Puedes usar estas variables de entorno en PowerShell:

   ```powershell
   $env:SPRING_DATASOURCE_URL = "jdbc:postgresql://localhost:5432/pagos_soa"
   $env:SPRING_DATASOURCE_USERNAME = "tu_usuario"
   $env:SPRING_DATASOURCE_PASSWORD = "tu_contraseña"
   ```

   Sustituye los valores por los de tu instalación. Estas variables duran mientras la ventana de PowerShell esté abierta.
3. Inicia el servicio desde la misma ventana:

   ```powershell
   mvn spring-boot:run
   ```

   También puedes abrir el proyecto que contiene `pom.xml` en NetBeans y ejecutarlo. En ese caso, configura la conexión para ese entorno.

El servicio usa el puerto **8084**: `http://localhost:8084`. Hibernate está configurado con `ddl-auto=update`, por lo que puede modificar el esquema de la base de datos al iniciar. Usa una base de datos de prueba.

## Endpoints

### Pagos

| Método | Ruta | Función |
| --- | --- | --- |
| POST | `/api/pagos/registrar` | Registrar un pago |
| GET | `/api/pagos/todos` | Consultar todos los pagos |
| GET | `/api/pagos/{id}` | Consultar un pago por ID |
| PUT | `/api/pagos/confirmar/{id}` | Confirmar un pago |
| PUT | `/api/pagos/cancelar/{id}` | Cancelar un pago |

Ejemplo para registrar un pago desde PowerShell:

```powershell
$body = @{
    idPaciente = 1
    monto = 100.50
    metodoPago = "efectivo"
} | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:8084/api/pagos/registrar" -ContentType "application/json" -Body $body
```

Para consultar los pagos:

```powershell
Invoke-RestMethod -Method Get -Uri "http://localhost:8084/api/pagos/todos"
```

### Facturas

| Método | Ruta | Función |
| --- | --- | --- |
| POST | `/api/facturas/generar` | Generar una factura |
| GET | `/api/facturas/todas` | Consultar todas las facturas |
| GET | `/api/facturas/{id}` | Consultar una factura por ID |
| PUT | `/api/facturas/anular/{id}` | Anular una factura |

Ejemplo de datos para generar una factura, usando el ID de un pago:

```json
{
  "idPago": 1,
  "total": 100.50
}
```

El servicio genera automáticamente el número de factura. Por ahora no verifica que el pago exista ni que esté confirmado.

## Seguridad

No subas contraseñas ni tokens al repositorio. Usa variables de entorno para los datos de conexión. Si una credencial se publicó, cámbiala o revócala: añadir un archivo a `.gitignore` no elimina lo que ya se encuentra en Git o en su historial.

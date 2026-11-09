CREATE TABLE pagos (
    id SERIAL PRIMARY KEY,
    id_paciente INTEGER NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado VARCHAR(50) DEFAULT 'PENDIENTE',
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE facturas (
    id SERIAL PRIMARY KEY,
    id_pago INTEGER NOT NULL,
    numero_factura VARCHAR(255),
    total NUMERIC(38,2) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    fecha_emision TIMESTAMP
);
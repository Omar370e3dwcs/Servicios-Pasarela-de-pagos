CREATE TABLE pagos (
    id SERIAL PRIMARY KEY,
    organizacion_id VARCHAR(64) NOT NULL,
    id_paciente INTEGER NOT NULL,
    id_cita INTEGER,
    monto DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado VARCHAR(50) DEFAULT 'PENDIENTE',
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    referencia_pago VARCHAR(255),
    observaciones VARCHAR(255)
);

CREATE TABLE facturas (
    id SERIAL PRIMARY KEY,
    organizacion_id VARCHAR(64) NOT NULL,
    id_pago INTEGER NOT NULL,
    numero_factura VARCHAR(255),
    total NUMERIC(38,2) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    fecha_emision TIMESTAMP,
    CONSTRAINT uk_factura_org_numero
        UNIQUE (organizacion_id, numero_factura)
);

CREATE TABLE configuraciones_pago (
    organizacion_id VARCHAR(64) PRIMARY KEY,
    moneda VARCHAR(3) NOT NULL DEFAULT 'MXN',
    proveedor VARCHAR(64) NOT NULL DEFAULT 'SIMULADO',
    permite_pago_parcial BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_pagos_organizacion ON pagos (organizacion_id);
CREATE INDEX idx_facturas_organizacion ON facturas (organizacion_id);
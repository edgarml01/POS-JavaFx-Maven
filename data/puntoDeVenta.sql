-- Tabla Roles
CREATE TABLE IF NOT EXISTS roles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT
);

-- Tabla Segmentos
CREATE TABLE IF NOT EXISTS segmentos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT
);

-- Tabla Usuarios
CREATE TABLE IF NOT EXISTS users(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    password TEXT NOT NULL,
    rol_id INTEGER,
    FOREIGN KEY (rol_id) REFERENCES Roles(id)
);

-- Tabla Productos
CREATE TABLE IF NOT EXISTS productos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    costo REAL not null,
    precio REAL not null,
    stock INTEGER NOT NULL,
    segmento_id INTEGER,
    FOREIGN KEY (segmento_id) REFERENCES Segmentos(id)
);

-- Tabla Ventas
CREATE TABLE IF NOT EXISTS ventas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    total REAL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabla DetallesVenta
CREATE TABLE IF NOT EXISTS detallesVenta (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    venta_id INTEGER,
    producto_id INTEGER,
    cantidad INTEGER NOT NULL,
    precio_venta REAL NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES Ventas(id),
    FOREIGN KEY (producto_id) REFERENCES Productos(id)
);

-- Tabla Permisos
CREATE TABLE IF NOT EXISTS permisos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT
);

-- Tabla RolesPermisos
CREATE TABLE IF NOT EXISTS rolesPermisos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    rol_id INTEGER,
    permiso_id INTEGER,
    FOREIGN KEY (rol_id) REFERENCES Roles(id),
    FOREIGN KEY (permiso_id) REFERENCES Permisos(id)
);

INSERT INTO roles (nombre) VALUES ('Administrador');
INSERT INTO roles (nombre) VALUES ('Empleado');
INSERT INTO roles (nombre) VALUES ('Supervisor');

INSERT INTO segmentos (nombre) VALUES ('Papelería');
INSERT INTO segmentos (nombre) VALUES ('Abarrotes');

-- Para simplificar las contraseñas, estas están en texto plano, pero en un sistema real deberías utilizar un hash
INSERT INTO users (nombre, password, rol_id) VALUES ('Juan Pérez', 'password123', 1); -- Admin
INSERT INTO users (nombre, password, rol_id) VALUES ('Maria López', 'password123', 2); -- Empleado
INSERT INTO users (nombre, password, rol_id) VALUES ('Carlos Jiménez', 'password123', 3); -- Supervisor

INSERT INTO productos (nombre, stock, segmento_id, costo, precio) VALUES ('Cuaderno', 100, 1, 50, 22); -- Papelería
INSERT INTO productos (nombre, stock, segmento_id, costo, precio) VALUES ('Lápiz', 150, 1, 50, 22); -- Papelería
INSERT INTO productos (nombre, stock, segmento_id, costo, precio) VALUES ('Agua Embotellada', 50, 2, 50, 22); -- Abarrotes
INSERT INTO productos (nombre, stock, segmento_id, costo, precio) VALUES ('Galletas', 75, 2, 50, 22); -- Abarrotes

INSERT INTO ventas (total) VALUES (50.00);
INSERT INTO ventas (total) VALUES (25.50);
INSERT INTO ventas (total) VALUES (100.00);

INSERT INTO detallesVenta (venta_id, producto_id, cantidad, precio_venta) VALUES (1, 1, 2, 10.00); -- Venta de 2 cuadernos
INSERT INTO detallesVenta (venta_id, producto_id, cantidad, precio_venta) VALUES (1, 3, 1, 30.00); -- Venta de 1 agua embotellada
INSERT INTO detallesVenta (venta_id, producto_id, cantidad, precio_venta) VALUES (2, 2, 3, 8.50); -- Venta de 3 lápices
INSERT INTO detallesVenta (venta_id, producto_id, cantidad, precio_venta) VALUES (3, 4, 10, 10.00); -- Venta de 10 galletas

INSERT INTO permisos (nombre) VALUES ('Crear Usuarios');
INSERT INTO permisos (nombre) VALUES ('Eliminar Usuarios');
INSERT INTO permisos (nombre) VALUES ('Ver Reportes');
INSERT INTO permisos (nombre) VALUES ('Modificar Productos');

-- Asociamos roles con permisos
INSERT INTO rolesPermisos (rol_id, permiso_id) VALUES (1, 1); -- Administrador puede crear usuarios
INSERT INTO rolesPermisos (rol_id, permiso_id) VALUES (1, 2); -- Administrador puede eliminar usuarios
INSERT INTO rolesPermisos (rol_id, permiso_id) VALUES (1, 3); -- Administrador puede ver reportes
INSERT INTO rolesPermisos (rol_id, permiso_id) VALUES (1, 4); -- Administrador puede modificar productos

INSERT INTO rolesPermisos (rol_id, permiso_id) VALUES (2, 3); -- Empleado puede ver reportes
INSERT INTO rolesPermisos (rol_id, permiso_id) VALUES (3, 4); -- Supervisor puede modificar productos

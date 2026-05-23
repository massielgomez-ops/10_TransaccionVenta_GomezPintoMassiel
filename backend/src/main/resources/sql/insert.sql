USE StudentCRUD;
GO

INSERT INTO dbo.students (nombre, apellido, correo, telefono, edad, carrera, codigo, direccion)
VALUES
('Sofía', 'Cárdenas', 'sofia.cardenas@demo.com', '912345671', 20, 'Ingeniería de Software', 'EST-2001', 'Av. Los Pinos 145'),
('Diego', 'Salazar', 'diego.salazar@demo.com', '912345672', 22, 'Administración de Empresas', 'EST-2002', 'Jr. Santa Rosa 234'),
('Valeria', 'Molina', 'valeria.molina@demo.com', '912345673', 19, 'Marketing Digital', 'EST-2003', 'Calle Las Begonias 567'),
('Andrés', 'Huamán', 'andres.huaman@demo.com', '912345674', 23, 'Contabilidad y Finanzas', 'EST-2004', 'Av. Grau 890'),
('Camila', 'Ortega', 'camila.ortega@demo.com', '912345675', 21, 'Diseño Gráfico', 'EST-2005', 'Jr. El Sol 321'),
('Mateo', 'Ríos', 'mateo.rios@demo.com', '912345676', 24, 'Ingeniería Industrial', 'EST-2006', 'Calle Los Cedros 654'),
('Lucía', 'Paredes', 'lucia.paredes@demo.com', '912345677', 20, 'Negocios Internacionales', 'EST-2007', 'Av. Las Palmeras 778'),
('Javier', 'Núñez', 'javier.nunez@demo.com', '912345678', 25, 'Sistemas de Información', 'EST-2008', 'Jr. Comercio 412');
GO

INSERT INTO dbo.customers (id, name, document, email, phone)
VALUES
(1, 'Elena Torres', '1003004001', 'elena.torres@demo.com', '3001112201'),
(2, 'Mario Castro', '1003004002', 'mario.castro@demo.com', '3001112202'),
(3, 'Paula Rojas', '1003004003', 'paula.rojas@demo.com', '3001112203'),
(4, 'Ricardo Vega', '1003004004', 'ricardo.vega@demo.com', '3001112204'),
(5, 'Natalia Flores', '1003004005', 'natalia.flores@demo.com', '3001112205'),
(6, 'Sebastián León', '1003004006', 'sebastian.leon@demo.com', '3001112206'),
(7, 'Lorena Díaz', '1003004007', 'lorena.diaz@demo.com', '3001112207'),
(8, 'Pablo Herrera', '1003004008', 'pablo.herrera@demo.com', '3001112208');
GO

INSERT INTO dbo.products (id, name, sku, category, price, stock, description)
VALUES
 (11, 'Laptop Essential 15', 'LE-015', 'Tecnología', 2890.00, 10, 'Equipo portátil para oficina y estudio.'),
 (12, 'Tablet Pro 11', 'TP-011', 'Tecnología', 1650.00, 14, 'Tablet con lápiz y pantalla de alta resolución.'),
 (13, 'Auriculares ANC', 'ANC-013', 'Accesorios', 260.00, 28, 'Auriculares con cancelación activa de ruido.'),
 (14, 'Monitor Curvo 32"', 'MC-032', 'Tecnología', 1250.00, 9, 'Monitor curvo ideal para productividad.'),
 (15, 'Teclado Mecánico RGB', 'TK-015', 'Accesorios', 290.00, 18, 'Teclado gaming retroiluminado RGB.'),
 (16, 'Mouse Gamer', 'MG-016', 'Accesorios', 140.00, 25, 'Mouse de alta precisión para juegos.'),
 (17, 'Impresora Multifuncional', 'IM-017', 'Oficina', 920.00, 7, 'Impresora, escáner y copiadora en un solo equipo.'),
 (18, 'Silla Ergonómica', 'SE-018', 'Oficina', 780.00, 11, 'Silla cómoda para largas jornadas de trabajo.');
GO

INSERT INTO dbo.sales (id, client_id, client_name, sale_date, total, created_at)
VALUES
 (2001, 1, 'Elena Torres', CONVERT(NVARCHAR(20), GETDATE(), 23), 2890.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126)),
 (2002, 2, 'Mario Castro', CONVERT(NVARCHAR(20), GETDATE(), 23), 1650.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126)),
 (2003, 3, 'Paula Rojas', CONVERT(NVARCHAR(20), GETDATE(), 23), 260.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126)),
 (2004, 4, 'Ricardo Vega', CONVERT(NVARCHAR(20), GETDATE(), 23), 2500.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126)),
 (2005, 5, 'Natalia Flores', CONVERT(NVARCHAR(20), GETDATE(), 23), 420.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126)),
 (2006, 6, 'Sebastián León', CONVERT(NVARCHAR(20), GETDATE(), 23), 920.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126)),
 (2007, 7, 'Lorena Díaz', CONVERT(NVARCHAR(20), GETDATE(), 23), 780.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126)),
 (2008, 8, 'Pablo Herrera', CONVERT(NVARCHAR(20), GETDATE(), 23), 580.00, CONVERT(NVARCHAR(50), SYSDATETIME(), 126));
GO

INSERT INTO dbo.sale_items (sale_id, product_id, product_name, quantity, unit_price, subtotal, stock)
VALUES
 (2001, 11, 'Laptop Essential 15', 1, 2890.00, 2890.00, 10),
 (2002, 12, 'Tablet Pro 11', 1, 1650.00, 1650.00, 14),
 (2003, 13, 'Auriculares ANC', 1, 260.00, 260.00, 28),
 (2004, 14, 'Monitor Curvo 32"', 2, 1250.00, 2500.00, 9),
 (2005, 16, 'Mouse Gamer', 3, 140.00, 420.00, 25),
 (2006, 17, 'Impresora Multifuncional', 1, 920.00, 920.00, 7),
 (2007, 18, 'Silla Ergonómica', 1, 780.00, 780.00, 11),
 (2008, 15, 'Teclado Mecánico RGB', 2, 290.00, 580.00, 18);
GO
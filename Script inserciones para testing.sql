USE grupo24_oo2;

INSERT INTO 
usuario(
	dni,
    apellido,
    nombre,
    fechaDeNacimiento,
    baja
) 
VALUES(
	11111111,
    'Fernandez',
    'Joel',
    '2001-08-02',
    false
), (
	22222222,
    'Aguirre',
    'Diego',
    '1999-10-7',
    false
), (
	33333333,
    'Brisa',
    'Aguilera',
    '2002-04-14',
    false
);

SELECT * FROM usuario;

INSERT INTO 
contacto(
	idContacto,
    telefono,
    email
)
VALUES (
	1,
    '1121647566',
    'joelfernandez.relg@gmail.com'
), (
	2,
    '1123334642',
    'diegoaguirre@hotmail.com'
), (
	3,
    '1152646654',
    'brisaaguilera@outlook.es'
);

SELECT * FROM contacto;

INSERT INTO
empleado(
	idEmpleado,
    fechaDeIngreso
)
VALUES(
	1,
    '2010-10-08'
);

SELECT * FROM empleado;

INSERT INTO 
usuariofinal(
	idUsuarioFinal
) 
VALUES (
	2
), (
	3
);

SELECT * FROM usuarioFinal;

INSERT INTO
ticket(
	titulo,
    descripcion,
    motivo,
    fechaDeCreacion,
    usuarioFinal
) VALUES (
	'Problema con Siu Guarani',
    'No puedo inscribirme',
    'Reclamo',
    '2024-12-10',
    2
), (
	'Falta de cupos',
    'Puede existir contracursada?',
    'Consulta',
    '2025-03-20',
    3
), (
	'Solicito servicio tecnico',
    'Una de las pcs no anda',
    'Servicio tecnico',
    '2025-04-30',
    2
);

SELECT * FROM ticket;

INSERT INTO
TicketTieneEmpleado(
	idTicket,
    idEmpleado
) VALUES (
	1,
    1
), (
	2,
    1
);

SELECT * FROM TicketTieneEmpleado;

INSERT INTO
Estado(
	idEstado,
    tipoDeEstado,
    descripcionDelEstado,
    ultimoCambioEstado
) VALUES (
	1,
    'finalizado',
    'Ya habilitamos tu usuario',
    '2025-01-10'
), (
	2,
    'recibido',
    'Estamos evaluando una contracursada',
    '2025-04-10'
), (
	3,
    'enviado',
    'Tu solicitud esta en cola',
    '2025-04-30'
);

SELECT * from Estado;
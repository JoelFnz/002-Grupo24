use grupo24_oo2;

INSERT INTO servicio (id_servicio, descripcion, nombre_servicio)
SELECT * FROM (SELECT 1 AS id_servicio, "Servicio de reparación y mantenimiento para computadoras y servidores" AS descripcion, "Servicio técnico" AS nombre_servicio) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM servicio WHERE id_servicio = 1
)
UNION ALL
SELECT * FROM (SELECT 2 AS id_servicio, "Servicio de atención al cliente" AS descripcion, "Asistencia técnica" AS nombre_servicio) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM servicio WHERE id_servicio = 2
);

select * from servicio;
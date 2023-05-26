# pruebaKonecta
Prueba Konecta Desarrollador IVR

CÓDIGO EJEMPLO DE IMPLEMENTACIÓN EN PostgreSQl:


-- Para crear la BD
```
CREATE DATABASE "IVR_CONSULTA"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
    
  ```

-- Para crear tabla de Areas
```
CREATE TABLE public."AREAS"
(
    "CODE" character varying(5),
    "AREA_NAME" character varying(50) NOT NULL,
    "BUDGET" numeric,
    PRIMARY KEY ("AREA_NAME")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."AREAS"
    OWNER to postgres;
    
```

-- Para crear la tabla employees
```
CREATE TABLE public."EMPLOYEES"
(
    "DOCUMENT" character varying(20) NOT NULL,
    "FIRST_NAME" character varying(50),
    "LAST_NAME" character varying(50),
    "POSITION" character varying(100),
    "AREA" character varying(50) NOT NULL,
    "ADMISSION_DATE" date NOT NULL,
    PRIMARY KEY ("DOCUMENT"),
    CONSTRAINT "FK_AREA" FOREIGN KEY ("AREA")
        REFERENCES public."AREAS" ("AREA_NAME") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."EMPLOYEES"
    OWNER to postgres;
    
```

-- Insertando registros de areas.

```
INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00001', 'IVR', 50000);
INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00002', 'IVR_BANCO', 60000);
INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00003', 'KCRM', 45000);
INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00004', 'BPM', 72000);
```

-- Para registrar empleados
```
INSERT INTO public."EMPLOYEES"(
	"DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
	VALUES ('1065658680', 'Andrés', 'Guerra', 'Jefe', 'IVR', '2023-04-01');
	
INSERT INTO public."EMPLOYEES"(
	"DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
	VALUES ('1065658682', 'Carlos', 'Lozano', 'Analista', 'IVR_BANCO', '2023-01-03');
	
INSERT INTO public."EMPLOYEES"(
	"DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
	VALUES ('1065658685', 'Leo', 'Cano', 'Líder', 'KCRM', '2022-04-01');
	
INSERT INTO public."EMPLOYEES"(
	"DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
	VALUES ('1065658688', 'Lina', 'Guerra', 'Analista', 'BPM', '2021-04-01');
  
```

-- Para consultar los registros de la BD.
```
SELECT * FROM public."EMPLOYEES";
```

-- a)Una lista con todos los empleados, en una sola columna llamada EQUIPOS que contenga la siguiente estructura: nombre apellido (cargo – área) y esté ordenado alfabéticamente por área. Ejemplo: Pedro Pérez (Analista – IVR):
```
SELECT CONCAT("FIRST_NAME", ' ',"LAST_NAME", ' (' ,"POSITION",' - ',"AREA", ')') AS "EQUIPOS" FROM "EMPLOYEES" ORDER BY "AREA";
```

-- b)Todos los cargos que existen, sin repeticiones:
```
SELECT DISTINCT("POSITION") FROM "EMPLOYEES";
```

-- c)La cantidad de personas que hay en el área de IVR cuyo cargo es Analista:
```
SELECT COUNT(*) AS "CANTIDAD ANALISTAS IVR" FROM "EMPLOYEES" WHERE "AREA" = 'IVR' AND "POSITION" = 'Analista';
```

-- d) Obtener el presupuesto total de todas las áreas:
```
SELECT SUM("BUDGET") AS "PRESUPUESTO TOTAL" FROM "AREAS"; 
```

-- e) e)La cantidad de empleados que hay en cada área, ordenada de manera descendente:
```
SELECT "AREA", COUNT(*) AS "CANTIDAD EMPLEADOS" FROM "EMPLOYEES" GROUP BY "AREA" ORDER BY COUNT(*) DESC;
```

-- f) Obtener los documentos y nombres completos de los empleados que trabajen en áreas cuyo presupuesto se encuentre entre 46000 y 70000 USD: 
```
SELECT "DOCUMENT" AS DOCUMENTO, CONCAT("FIRST_NAME",' ',"LAST_NAME") AS "NOMBRE" FROM "EMPLOYEES" INNER JOIN "AREAS" 
ON "EMPLOYEES"."AREA" = "AREAS"."AREA_NAME" WHERE "AREA" IN (SELECT "AREA_NAME" FROM "AREAS" WHERE "BUDGET" BETWEEN 46000 AND 70000);
```
-- g) Cambie los empleados del área IVR BANCO al área de IVR:
```
UPDATE "EMPLOYEES" SET "AREA" = 'IVR' WHERE "AREA" = 'IVR BANCO';
```

-- h) Elimine los empleados que lleven 1 año o menos en la compañía:
```
DELETE FROM "EMPLOYEES" WHERE (CURRENT_DATE - "ADMISSION_DATE") <= 365;
```

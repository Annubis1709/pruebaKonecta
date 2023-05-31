# PRUEBA TÉCNICA
## **1. CONOCIMIENTOS EN BASE DE DATOS**

### CÓDIGO EJEMPLO DE IMPLEMENTACIÓN EN PostgreSQL

- Crear una base de datos llamada por ejemplo, `IVR_CONSULTA`:

```sql
CREATE DATABASE "IVR_CONSULTA"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
```

- Creación de la tabla `AREAS`:

```sql

CREATE TABLE public."AREAS"(
    "CODE" character varying(5),
    "AREA_NAME" character varying(50) NOT NULL,
    "BUDGET" numeric,
     PRIMARY KEY ("AREA_NAME")
)

WITH (OIDS = FALSE);
ALTER TABLE public."AREAS"
    OWNER to postgres;

```

- Creación de la tabla `EMPLOYEES`:

```sql

CREATE TABLE public."EMPLOYEES"(
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
WITH (OIDS = FALSE);
ALTER TABLE public."EMPLOYEES"
    OWNER to postgres;

```

- Inserta registros de Areas:

```sql

INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00001', 'IVR', 50000);
INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00002', 'IVR_BANCO', 60000);
INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00003', 'KCRM', 45000);
INSERT INTO public."AREAS"("CODE", "AREA_NAME", "BUDGET") VALUES ('00004', 'BPM', 72000);

```

- Inserta registros de Empleados:

```sql

INSERT INTO public."EMPLOYEES"("DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
VALUES ('1065658680', 'Andrés', 'Guerra', 'Jefe', 'IVR', '2023-04-01');
	
INSERT INTO public."EMPLOYEES"(	"DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
VALUES ('1065658682', 'Carlos', 'Lozano', 'Analista', 'IVR_BANCO', '2023-01-03');
	
INSERT INTO public."EMPLOYEES"("DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
VALUES ('1065658685', 'Leo', 'Cano', 'Líder', 'KCRM', '2022-04-01');

INSERT INTO public."EMPLOYEES"("DOCUMENT", "FIRST_NAME", "LAST_NAME", "POSITION", "AREA", "ADMISSION_DATE")
VALUES ('1065658688', 'Lina', 'Guerra', 'Analista', 'BPM', '2021-04-01');
```

- Para consultar los registros de la tabla Empleados y Areas :

```sql

SELECT *
FROM public."EMPLOYEES";

```

```sql

SELECT *
FROM public."AREAS";

```



### A continuación, se muestran las consultas en PostgreSQL para realizar las acciones requeridas:

a) Una lista con todos los empleados, en una sola columna llamada EQUIPOS que 
contenga la siguiente estructura: nombre apellido (cargo – área) y esté 
ordenado alfabéticamente por área. Ejemplo: Pedro Pérez (Analista – IVR):

```sql

SELECT CONCAT("FIRST_NAME", ' ',"LAST_NAME", ' (' ,"POSITION",' - ',"AREA", ')') AS "EQUIPOS" 
FROM "EMPLOYEES" 
ORDER BY "AREA";

```

b) Lista de todos los cargos sin repeticiones:

```sql

SELECT DISTINCT("POSITION") 
FROM "EMPLOYEES";

```

c) Cantidad de personas en el área de IVR con cargo de Analista:

```sql

SELECT COUNT(*) AS "CANTIDAD ANALISTAS IVR" 
FROM "EMPLOYEES" 
WHERE "AREA" = 'IVR' AND "POSITION" = 'Analista';
```

d) Presupuesto total de todas las áreas:

```sql

SELECT SUM("BUDGET") AS "PRESUPUESTO TOTAL" 
FROM "AREAS";

```

e) Cantidad de empleados en cada área, ordenada de manera descendente:

```sql

SELECT "AREA", COUNT(*) AS "CANTIDAD EMPLEADOS" 
FROM "EMPLOYEES" 
GROUP BY "AREA" 
ORDER BY COUNT(*) DESC;

```

f) Documentos y nombres completos de empleados en áreas con presupuesto entre 
46000 y 70000 USD:

```sql

SELECT "DOCUMENT" AS DOCUMENTO, CONCAT("FIRST_NAME",' ',"LAST_NAME") AS "NOMBRE" 
FROM "EMPLOYEES"
INNER JOIN "AREAS" 
ON "EMPLOYEES"."AREA" = "AREAS"."AREA_NAME" 
WHERE "AREA" IN (SELECT "AREA_NAME" FROM "AREAS" WHERE "BUDGET" BETWEEN 46000 AND 70000);

```

g) Cambio de empleados del área IVR_BANCO al área IVR:

```sql

UPDATE "EMPLOYEES" SET "AREA" = 'IVR' 
WHERE "AREA" = 'IVR BANCO';

```

h) Eliminación de empleados con 1 año o menos en la compañía:

```sql

DELETE FROM "EMPLOYEES" 
WHERE (CURRENT_DATE - "ADMISSION_DATE") <= 365;

```

<br>

## **2. CONOCIMIENTOS DE GIT**

a) Crear un nuevo repositorio local para un proyecto llamado `KSF_PRUEBA_IVR`:

```

git init KSF_PRUEBA_IVR

```

b) Enlazar este repositorio local con el repositorio remoto:

```

git remote add origin <URL_del_repositorio_remoto>

```

c) Crear y cambiarse a una rama llamada `IVR-{{CEDULA}}`:

```

git branch IVR-{{CEDULA}}
git checkout IVR-{{CEDULA}}   

```
ó en una sóla línea de comando:

```

 git checkout -b IVR-{{CEDULA}}

```

d) Realizar un commit (suponga que sí hay archivos modificados en su repositorio local):

-- Primero agrego los archivos al área de preparación (stage)<br>
-- y luego realizo el commit

```

git add .
git commit -m "Mensaje del commit"

```

e) Subir el contenido del último commit del repositorio local al repositorio remoto:

```

git push origin <nombre_de_la_rama>

```

f) Solicitar la fusión de la rama `IVR-{{CEDULA}}` con la rama master:

```

git merge IVR-{{CEDULA}}

```

g) Si la rama estuviera desactualizada, indique el comando para actualizar su repositorio local:

```

git pull origin <nombre_de_la_rama>

```
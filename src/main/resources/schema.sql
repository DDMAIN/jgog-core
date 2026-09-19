CREATE TABLE "juego" (
  "id" INTEGER PRIMARY KEY,
  "nombre" VARCHAR(1000) NOT NULL
);

CREATE TABLE "fichero" (
  "id" INTEGER PRIMARY KEY,
  "id_juego" INTEGER NOT NULL,
  "nombre" VARCHAR(1000) NOT NULL,
  "tamanno" INTEGER NOT NULL,
  "activo" BOOLEAN NOT NULL
);

ALTER TABLE "fichero" ADD FOREIGN KEY ("id_juego") REFERENCES "juego" ("id");
-- Script para crear la base de datos y tabla de personas
-- Ejecutar este script en MySQL antes de usar la aplicación

CREATE DATABASE IF NOT EXISTS personas_db;
USE personas_db;

CREATE TABLE IF NOT EXISTS personas (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL
);

-- Insertar algunos datos de ejemplo para pruebas
INSERT INTO personas (codigo, nombre, edad) VALUES 
(1, 'Juan Pérez', 25),
(2, 'María García', 30),
(3, 'Carlos López', 28),
(4, 'Ana Martínez', 22),
(5, 'Luis Rodríguez', 35);

-- Verificar que los datos se insertaron correctamente
SELECT * FROM personas;
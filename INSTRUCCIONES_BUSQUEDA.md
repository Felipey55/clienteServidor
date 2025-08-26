# Funcionalidad de Búsqueda Cliente-Servidor

## Descripción
Se ha implementado la funcionalidad de búsqueda en los botones de búsqueda de las interfaces ClientForm y ServerForm para consultar información de personas en la base de datos.

## Configuración Previa

### 1. Base de Datos
- Ejecutar el script `database_setup.sql` en MySQL para crear la base de datos y tabla de personas
- Verificar que la conexión en `DBConnection.java` tenga las credenciales correctas

### 2. Compilación
```bash
javac -cp "lib\mysql-connector-j-8.2.0.jar" -d build\classes src\clienteservidor\*.java
```

## Uso de la Funcionalidad

### En el Servidor (ServerForm)
1. Ejecutar la aplicación servidor
2. Hacer clic en "Iniciar servidor"
3. En el campo de texto, escribir un código numérico o nombre de persona
4. Hacer clic en "Buscar"
5. Los resultados aparecerán en el mismo campo de texto y en el área de mensajes

### En el Cliente (ClientForm)
1. Ejecutar la aplicación cliente
2. Hacer clic en "Conectar" para conectarse al servidor
3. En el campo de texto, escribir un código numérico o nombre de persona
4. Hacer clic en "Buscar"
5. La solicitud se enviará al servidor y los resultados aparecerán automáticamente en el campo de texto

## Características Implementadas

### Búsqueda Inteligente
- **Por código**: Si se ingresa un número, busca por código de persona
- **Por nombre**: Si se ingresa texto, busca por nombre (búsqueda parcial con LIKE)

### Comunicación Cliente-Servidor
- El cliente envía comandos con formato `BUSCAR:criterio`
- El servidor responde con formato `RESULTADO:codigo|nombre|edad` o `RESULTADO:NO_ENCONTRADO`
- Manejo de errores y validaciones

### Actualización de Interfaz
- Los resultados se muestran automáticamente en los campos de texto
- Mensajes informativos en el área de chat
- Validaciones de entrada y conexión

## Archivos Modificados
- `src/clienteservidor/ClientForm.java` - Funcionalidad de búsqueda del cliente
- `src/clienteservidor/ServerForm.java` - Funcionalidad de búsqueda del servidor
- `src/clienteservidor/DatabaseManager.java` - Nueva clase para manejo de base de datos
- `database_setup.sql` - Script de configuración de base de datos

## Ejemplos de Uso
- Buscar por código: `1`, `2`, `3`
- Buscar por nombre: `Juan`, `María`, `Carlos`
- Búsqueda parcial: `Pér` encontrará "Juan Pérez"

## Notas Importantes
- Asegurar que MySQL esté ejecutándose
- Verificar que las credenciales de base de datos sean correctas
- El servidor debe estar iniciado antes de que el cliente se conecte
- La búsqueda es case-insensitive para nombres
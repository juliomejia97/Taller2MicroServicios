# Taller2MicroServicios

## Descripción del repositorio

### Parte 1

Este proyecto se llama serverREST el cual contiene dos proyectos. El primero contiene el servidor (**tallerServer**), el cual provee todos los servicios necesarios para:

Path: http://localhost:8080/myapp/myresource/trips

1. Listar Paseos
2. Borrar un Paseo
3. Actualizar un Paseo
4. Crear un Paseo

Este servidor se debe correr:

`mvn clean install`

`mvn exec:java`

Por otra parte, se encuentre el cliente(**RestClient**), el cual es una aplicación en consola con un menú, el cual utiliza todos los servicios mencionados anteriormente.

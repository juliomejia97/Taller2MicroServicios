# Taller2MicroServicios

## Descripción del repositorio

### Parte 1

Este proyecto se llama serverREST el cual contiene dos proyectos. El primero contiene el servidor (**tallerServer**), el cual provee todos los servicios necesarios para:

Path: http://localhost:8080/myapp/myresource/trips

1. Listar Paseos. Petición GET al path
2. Borrar un Paseo Petición DELETE al path con PathParam del id del paseo
3. Actualizar un Paseo.Petición PUT al path con un PathParam del id del paseo y dos QueryParams (origen y destino)
4. Crear un Paseo Petición POST al path que recibe un JSON.

Este servidor se debe correr en consola dentro de su carpeta correspondiente:

`mvn clean install`

`mvn exec:java`

Por otra parte, se encuentre el cliente(**RestClient**), el cual es una aplicación en consola con un menú, el cual utiliza todos los servicios mencionados anteriormente. Para correrlo basta con importar el proyecto en eclipse y correrlo el el símbolo RUN.

### Parte 2

La estructura del proyecto en este caso se encuentra en la carpeta **Spring Cloud**, donde se almacenan los proyectos: eurekaServer, calculadora, sumador, restador, multip y divisor. Estos tienen gateways que son usados por la calculadora, con dos query parametros que son a y b:

1. Sumador: /suma?a={a}&b={b}
2. Restador:/resta?a={a}&b={b}
3. División: /division?a={a}&b={b}
4. Multiplicación: /multiplicacion?a={a}&b={b}

Así mismo la calculadora ofrece gateways con los siguientes formatos:

1. Suma: /calculadora/suma?a={a}&b={b}&user={user}
2. Resta: /calculadora/resta?a={a}&b={b}&user={user}
3. Multiplicación: /calculadora/multiplicacion?a={a}&b={b}&user={user}
4. División: /calculadora/division?a={a}&b={b}&user={user}



Vale la pena aclarar que cada uno de estos proyectos dentro de su carpeta y en una consola debe correrse los comandos: 

`mvn clean install`

`mvn spring-boot:run`

A continuación se encuentra una tabla con los puertos en los cuales corre cada uno de los proyectos base:

|   Proyecto   | Puerto |
| :----------: | :----: |
| EurekaServer |  8761  |
| Calculadora  |  8888  |
|   Sumador    |  9999  |
|   Restador   |  6999  |
|    Multip    |  7090  |
|   Divisor    |  7999  |

Para correr alguno de los proyectos en un puerto diferente se debe correr de la siguiente forma:

##### Windows

`SET SERVER_PORT=PUERTO`

##### Mac

`SERVER_PORT=PUERTO mvn spring-boot:run`

##### Linux

`mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=PUERTO`

### Video Explicativo:

https://youtu.be/BTIH5lxwlV4

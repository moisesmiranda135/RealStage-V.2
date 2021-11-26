# RealStage-V.2

## Desarrollada por :

### Moisés Miranda Corrales


Esta API REST permite controlar el funcionamiento de la app REAL ESTATE, la cual gestiona el alquiler y venta de viviendas, así como el API para poder gestionar todos los datos. Ademas de tener implementada SEGURIDAD I. JWT.

Esto incluye las siguientes funcionalidades:

- Las peticiones que se detallan más abajo.
- La docuementación generada con OpenApi 3.0 y Swagger.
- Una colección de Postman con las peticiones generadas en JSON para poder realizar pruebas de la app.
- Implementación de seguridad a la hora de la realización de peticiones, para que dichas peticiones solamente la puedan hacer usuarios registrados y con diferentes roles que son ADMIN, GESTOR Y PROPIETARIO.


## Entidades

Las entidades que componen esta API son:

- Vivienda
- Inmobiliaria
- Usuario
- Interesa

Para el correcto manejo de cada entidad contamos con las siguientes clases:

### Vivienda

- **ViviendaController**   

- **ViviendaRepository**
    
- **ViviendaService**
   

### Inmobiliaria

- **InmobiliariaController**

- **InmobiliariaRepository**
    
- **InmobiliariaService**


### Usuario

- **PropietarioController**

- **PropietarioRepository**
    
- **PropietarioService**


### Interesa

- **InteresadoController**

- **InteresadoRepository**
    
- **InteresadoService**


Por otro lado, para el correcto manejo de las asociaciones entre entidades, se han creado diferentes DTOs (Data Transfer Object); estos nos permiten crear nuevos objetos con los atributos de las entidades que más nos interesen.

También en la carpeta controller tenemos las distintas peticiones, que tenemos incluidas en postman. En la documentación de swagger situada en la parte superior de cada petición, esta definido que función realiza y que respuestas puede devolver.

Por otro lado tenemos la carpeta security en la que añadimos todo lo necesario para añadir la seguridad a nuestra API, una de las clases a mirar es SecurityConfig, en la que sepuede ver los permisos añadidos a cada petición.


## Funcionamiento

Para el correcto funcionamiento de la aplicación, se necesitan seguir los siguientes pasos:

1. Descargar el repositorio
2. Importar el proyecto de backend en IntelliJ IDEA.
3. Ejecutar el proyecto con Maven y Spring Boot.
4. Importar el JSON en la app de Postman

Una vez seguidos estos pasos, dentro de postman podremos ir probando las distintas peticiones.

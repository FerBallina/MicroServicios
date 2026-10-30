# MicroServicios
- para correr el principal se puede usar curl
- el servicio de consultarFechas funciona solo si se llama al today, si se quiere llamar a otro endpoin se necesita el servicio feriados corriendo tambien
- curl -X GET http://localhost:8081/consultarFecha/all
- /actuator/health para saber si esta corriendo el servicio
- /swagger-ui.html me da la direccion de swagger para mirar las apis

- curl -X GET http://localhost:8082/feriados/all


- recordar habilitar kafka en las properties
# README #

This README would normally document whatever steps are necessary to get your application up and running.

### clonar el proyecto ###

* git clone https://github.com/henrybpp/EVALUACION.git

### copiar el archivo de configuración en la ruta /external/properties ###

* cp EVALUACION/files/backend-dev.properties /external/properties

### 1RA FORMA --> CON DOCKER-COMPOSE: en la ruta ./files ejecutar el siguiente comando ###

* docker-compose -f docker-compose-products.yml up
* docker-compose -f docker-compose-products.yml ps

![image](https://user-images.githubusercontent.com/51663180/158215405-be29d6e1-4485-413f-9953-bca20e6d7078.png)


### 2DA FORMA --> CON DOCKER NATIVO: ejecutar las sentencias al mismo nivel que el repositorio ###

* cp EVALUACION/files/deploy.sh deploy.sh
* chmod u+x deploy.sh
* bash -x deploy.sh

![image](https://user-images.githubusercontent.com/51663180/158215894-76a5d10a-fc1c-44f9-a560-76f2846dfe81.png)


### Comandos de prueba rest api ###

* curl -X POST -d '{"brand":"abba","description":"oko"}' -H 'Content-Type: application/json' http://192.168.0.20:8082/products/getbycondition

* curl -X GET -H 'Content-Type: application/json' http://192.168.0.20:8082/products/getall

* curl -X GET -H 'Content-Type: application/json' http://192.168.0.20:8082/products/getbyid/622e4280d2a229d94df6199f

![image](https://user-images.githubusercontent.com/51663180/158214701-899f9f88-58cf-49ed-bcb7-8eac591bf9c7.png)


### SWAGGER-UI ###
* http://192.168.0.20:8082/swagger-ui.html

![image](https://user-images.githubusercontent.com/51663180/158216124-ce58b0cd-519e-4d92-8206-5db36c440d13.png)


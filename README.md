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

### INSOMNIA ###
![test-docker](https://user-images.githubusercontent.com/51663180/158218767-01a74f77-c29e-4cab-84ee-53cd67056cc0.png)


### SWAGGER-UI ###
* http://192.168.0.20:8082/swagger-ui.html

![image](https://user-images.githubusercontent.com/51663180/158216124-ce58b0cd-519e-4d92-8206-5db36c440d13.png)

### MONGO ATLAS ###

![image](https://user-images.githubusercontent.com/51663180/158217372-949713fb-9058-4f91-997d-5080042bf650.png)

![image](https://user-images.githubusercontent.com/51663180/158217753-293a30fe-05da-4d87-9537-2fd594484ded.png)

### DOCKERHUB REGISTRY ###
![image](https://user-images.githubusercontent.com/51663180/158223491-826af2b3-bd56-4b71-a7e3-b5108cfd7e69.png)

### SONARQUBE TEST ###
![image](https://user-images.githubusercontent.com/51663180/158231011-953f6c53-04a7-48d7-9a00-8e60ba43fbbf.png)





# Identificar ADN mutante

## Compilar y ejecutar aplicación.
En este repositorio se encuentra el código fuente del proyecto, para correr y desplegar el servicio local, se debe ejecutar desde una terminal las siguientes instrucciones en la raíz del proyecto.

    ./gradlew clean build
    java -jar applications/app-service/build/libs/app-service.jar

El servicio se levanta en el puerto 8080, si se desea cambiar el puerto, deberá actualizar la variable de configuración <port> que se encuentra en el archivo ..\applications\app-service\src\main\resources\application.yaml.

### Jacoco Report
Actualmente el proyecto tiene una cobertura de pruebas superior al 80%, para visualizar esta cobertura de forma gráfica deberá ejecutar el siguiente comando desde una terminal en la carpeta raíz del proyecto.

    gradlew clean jacocoMergedReport

![jacocoCodeCoverage.png](jacocoCodeCoverage.png)
El reporte es generado en \build\reports\jacocoMergedReport\html en formato html.

### Pruebas de performance

Se ejecutaron las pruebas de performance sobre el microservicio usando la herramienta Jmeter, en esta se logró un rendimiento de aproximado de 3981.7 TPS, con 2000 usuarios concurrentes, una rampa de subida de un 4 minutos y una duración de 8 minutos. Aclarando que las pruebas se realizan en una maquina local.

![performanceTest.png](performanceTest.png)

# REST API

La aplicación expone un servidor web con los siguientes recursos:

### Validar secuencia ADN
Recurso encargado de validar si la secuencia de ADN ingresada corresponde a la de un mutante o humano.
### Request

`POST /api/v1/mutant`

    curl --location --request POST 'http://localhost:8080/api/v1/mutant' --header 'Content-Type: application/json' --data-raw '{"dna": ["AGGEGG","AAGIGC","ATGIGI","TIIIIG","CGAATA","ITTTTG"]}'

### Response

    HTTP/1.1 200 OK (ADN corresponde a un mutante)
    HTTP/1.1 403 Forbidden (ADN corresponde a un humano)

## Estadísticas
Devuelve un json con las estadísticas de las verificaciones de ADN.

### Request

    `GET /api/v1/stats`

    curl --location --request GET 'http://localhost:8080/api/v1/stats'

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json
        
    {
        "countMutantDna": 1,
        "countHumanDna": 8,
        "ratio": 0.1
    }



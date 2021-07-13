# Identificar ADN mutante

## Instrucciones Ejecución de la API

**Url API** : http://Mutantvalidator-env.eba-uhwmjmdm.us-east-2.elasticbeanstalk.com
+ **Operaciones**
    + **POST → /api/v1/mutant/**
      Al consumir esta operación podrás saber si la secuencia DNA que envías corresponde a un mutante o no.
      **Ejemplo Body:**
      `
      {
      “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
      }
      `

      **Posibles respuestas:**
        - **Http 200**: Para una secuencia DNA mutante
        - **Http 403**: Para una secuencia DNA no mutante
        - **Http 400**: Si envías una secuencia DNA inválida.
          Se considera inválida una solicitud que no cumpla las siguientes características:
            - La secuencia ADN solo puede contener las letras (A,T,C,G). No importa sin son letras
              mayúsculas o minúsculas.
            - La secuencia ADN debe de ser un array de dimensiones NxN

    + **GET → /api/v1/stats**
      Al consumir esta operación podrás obtener una estadística de los humanos mutantes y no mutantes.
      
### Jacoco Report
Actualmente el proyecto tiene una cobertura de pruebas superior al 80%, para visualizar esta cobertura de forma gráfica deberá ejecutar el siguiente comando desde una terminal en la carpeta raíz del proyecto.

    gradlew clean jacocoMergedReport

![jacocoCodeCoverage.png](jacocoCodeCoverage.png)
El reporte es generado en \build\reports\jacocoMergedReport\html en formato html.

### Pruebas de performance

Se ejecutaron las pruebas de performance sobre el microservicio usando la herramienta Jmeter, en esta se logró un rendimiento de aproximado de 3981.7 TPS, con 2000 usuarios concurrentes, una rampa de subida de un 4 minutos y una duración de 8 minutos. Aclarando que las pruebas se realizan en una maquina local.

![performanceTest.png](performanceTest.png)



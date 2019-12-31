# Magneto Service
Este proyecto tiene como objetivo cumplir los requerimientos solicitados en el examen MercadoLibre para ayudar a Magneto a contratar mutantes para luchar contra los X-Mens.

El proyecto consta de 3 servicios que deben actuar conjuntamente para lograr cubrir las necesidades de Magneto.
Los servicios son:
* [Magneto-Service](https://github.com/hccr/magneto-service/), El cual expone un Endpoint para validar si un ADN es mutante o no.
* [Magneto-Processor](https://github.com/hccr/magneto-processor), Proyecto que esta encargado de almacenar en una base de datos los ADN consultados. Este proceso ocurre de manera asincrona.
* [Magneto-Statistics](https://github.com/hccr/magneto-statistics), Quien es el encargado de entretar las estadisticas sobre los ADN consultados.

## Supuestos considerados
Dentro del desarrollo del proyecto se consideraron supuestos, los cuales condicionan el funcionamiento del sistema.

* Las secuencias de ADN vienen en un arreglo de String y se podrán convertir en una matriz de NxN. El no cumplir ese requerimiento arrojará una excepción `NotNxNDnaFormatException`.
* Las secuencias de ADN solo podrán tener los caracteres `A,C,G,T`, si una secuencia contiene otro caracter se arrojará la excepción `NotAllowesCharException`
* La secuencia de ADN al ser convertida en matríz NxN debe tener un tamaño mayor igual que 4x4 y menor o igual que 64x64, de lo contrario se arrojará la excepción `DnaSizeException`

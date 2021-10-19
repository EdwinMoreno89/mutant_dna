# API REST que detecta si un humano es mutante basándose en su secuencia de ADN.

## Entorno
* Java 1.8
* MySql
* Maven
* JUnit5 

## Configurar Base De Datos MySql
Desde mysql ejecutar el contenido del archivo: /mutant/src/main/resources/schema-mysql.sql
Si desea ejecutarlo desde una base de datos local, debe cambiar el archivo properties: /mutant/src/main/resources/application.properties con la url de localhost o donde se quiera ejecutar el dump de la base de datos.

## Descarga del código fuente clonando el proyecto git desde Java

https://github.com/EdwinMoreno89/mutant_dna.git

Ejecutar Run As Maven Install para descargar las librerias
luego Ejecutar como spring-boot:run para disponibilizar el servidor con spring-boot

## Ejercicio

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

### No-Mutante 
A T G C G A 

C A G T G C

T T A T T T

A G A C G G

G C G T C A

T C A C T G

### Mutante
A T G C G A

C A G T G C

T T A T G T

A G A A G G

C C C C T A

T C A C T G

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.
Ejemplo (Caso mutante):

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

### endPoint


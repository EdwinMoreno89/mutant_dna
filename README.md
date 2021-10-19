# API REST que detecta si un humano es mutante basándose en su secuencia de ADN.
y el registro en base de datos en caso que no exista.
 {
"dna":["AAGCTA","AGGTGC","TTATGT","TGAAGG","TCCCCT","TCACTG"]
}
![image](https://user-images.githubusercontent.com/32916973/137903482-0825d555-caa9-4592-a390-178652b23015.png)
 Ejecute una petición POST con una cadena de caracteres No Mutante para obtener un Http code 403 Forbiden y el registro en base de datos en caso que no exista.
![image](https://user-images.githubusercontent.com/32916973/137904006-80b12087-00c9-46f2-86f2-1c52fdf163c5.png)
{
"dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]
}

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
## Entorno
* Java 1.8
* MySql
* Maven
* JUnit5 

## Configurar Base De Datos MySql
Desde mysql ejecutar el contenido del archivo: /mutant/src/main/resources/schema-mysql.sql
Si desea ejecutarlo desde una base de datos local, debe cambiar el archivo properties: /mutant/src/main/resources/application.properties con la url de localhost o donde se quiera ejecutar el dump de la base de datos.

## Descarga del código fuente clonando el proyecto git desde Java

* cree una carpeta en donde va a clonar el proyecto.
* posicionese sobre la ruta de la carpeta desde la linea de comandos
* Ejecute el comando gh repo clone EdwinMoreno89/mutant_dna
* Una vez clonado el proyecto, abralo como proyecto maven existente en el espacio de trabajo e importelo
* al dar click sobre el proyecto ejecute Run As Maven Install para descargar las librerias.
* luego Ejecutar como: spring-boot:run

## Test del proyecto
Desde postman o cualquier otra herramienta, cree una nueva petición con la URL de localhost:8080/stats
Ejecute una petición GET para obtener las estadisticas de las cadenas de ADN validadas.
![image](https://user-images.githubusercontent.com/32916973/137903327-d1284b2a-8d82-4e2f-8a88-96efc8e4af4f.png)
 Ejecute una petición POST con una cadena de caracteres Mutante para obtener un Http code 200 Ok 

### endPoint
POST → http://mutantdnaapi-env.eba-h4xceyuc.us-east-2.elasticbeanstalk.com/mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

GET → http://mutantdnaapi-env.eba-h4xceyuc.us-east-2.elasticbeanstalk.com/stats/




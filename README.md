# UNED-POO
Práctica UNED de desarrollo de un TPV en Java (Curso 2015)

La práctica del presente curso va a ser diseñar e implementar una terminal punto de venta (por sus siglas, TPV). Esto servirá para estudiar y practicar los mecanismos de la Programación Orientada a Objetos. Se plantearán diferentes niveles de acabado, permitiéndose así que el alumno pueda optar de antemano a diferentes calificaciones conociendo previamente los requisitos a cumplir para cada una de esas calificaciones.

## Definición de TPV y Características
Según la Wikipedia (www.wikipedia.org), un terminal punto de venta (cuyo acrónimo es TPV hace referencia al dispositivo y tecnologías que ayudan en la tarea de gestión de un establecimiento comercial de venta al público que puede contar con sistemas informáticos especializados mediante una interfaz accesible para los vendedores. Los TPV permiten la creación e impresión del tique de venta mediante las referencias de productos, realizan diversas operaciones durante todo el proceso de venta, así como cambios en el inventario. También generan diversos reportes que ayudan en la gestión del negocio. Los TPV se componen de una parte hardware (dispositivos físicos) y otra software (sistema operativo y programa de gestión).

En nuestro caso concreto, el hadware será un ordenador tipo PC o similar y nuestro software será una aplicación desarrollada en Java que se ejecutará sobre dicho equipo. 

## Funcionalidades
Los TPV permiten la implementación desde labores simples de gestión de una venta, hasta operaciones más complejas como es la gestión de almacén o inventario, gestión de facturación o gestión de clientes. En esta práctica, se propondrá diferentes funcionalidades en función de la calificación a la que se aspire. De este modo, una mayor complejidad a desarrollar implicará una calificación mayor en la evaluación de la práctica.
Es importante considerar que para optar a la calificación de un nivel superior han de cumplirse todas y cada una de las funcionalidades especificadas en el nivel inmediatamente anterior. Caso de no ser así (no cumplir con todas los requerimientos de un nivel) no se podrá obtener una calificación superior a la marcada por el nivel cuyas restricciones no se cumplen en su totalidad. Del mismo modo, los niveles han de implementarse en el orden que se indican, no
siendo posible implementar niveles no consecutivos para obtener calificaciones superiores..
Para cada uno de los niveles se van a indicar unos requisitos mínimos de cumplimiento. Esto quiere decir que para cualquier otro detalle de diseño que no se encuentre descrito expresamente en lo indicado en este enunciado, el alumno tiene libertad para tomar cuantas decisiones considere oportunas.

### Nivel 1 - Puntuación Total Máxima a Obtener: 1 punto.
Lo que se pretende que el alumno desarrolle en este nivel son las relaciones de clase, herencia y demás que van asociadas al desarrollo de la práctica. Así, se pide realizar las siguientes tareas:

* Planteamiento del Problema: actores participantes, relaciones entre actores, funcionalidad a cumplir por la práctica a desarrollar.
* Establecimiento de diferentes clases a intervenir en la práctica, relaciones de dependencia entre clases, identificar diferentes jerarquías de clases, etc.
* Elaboración de un documento escrito (memoria de la práctica) que contenga el primer punto y los correspondientes ficheros para BlueJ que implementen lo segundo.

### Nivel 2 - Puntuación Total Máxima a Obtener: 3 puntos.
Los alumnos que implementen este nivel de finalización de la práctica podrán sumar a la calificación que obtuvieron por realizar el Nivel 1 (que fue de 1 punto) hasta 2 puntos adicionales, con lo que la implementación del Nivel 2 acarreará la consecución de una puntuación máxima de 3 puntos. Sólo se podrá optar a este Nivel si se ha implementado satisfactoriamente y en su totalidad los requerimientos especificados en el Nivel 1.
Lo que se pretende que el alumno desarrolle en este nivel es la parte de gestión de inventario del establecimiento comercial. De este modo, el sistema deberá permitir lo siguiente:
* Llevar un control de diferentes elementos que existen en nuestro establecimiento. Así, los productos habrán de estar identificados en el sistema por, al menos, los siguientes datos: código descriptivo (por ejemplo, el código de barras), descripción, precio unitario sin IVA, IVA aplicable, precio unitario con IVA, cantidad disponible en stock.
* El sistema debe permitir dar de alta nuevos productos, dar de baja productos existentes así como modificar los datos del mismo.
* Realizar la importación y/o exportación de los productos a/desde ficheros (u otro método similar que el alumno considere en su lugar).
* Modificación del documento escrito desarrollado en el Nivel 1 (memoria de la práctica) y los correspondientes ficheros para BlueJ de modo que se implemente esta
funcionalidad y quede reflejada dicha modificación en la memoria correspondiente.
Del mismo modo, la memoria contendrá un Manual de Usuario simple que muestre la forma de manejar la aplicación con las funcionalidades añadidas en este nivel. La
implementación puede ser realizada en modo textual (sin usar librerías gráficas).

### Nivel 3 - Puntuación Total Máxima a Obtener: 5 puntos.
Los alumnos que implementen este nivel de finalización de la práctica podrán sumar a la calificación que obtuvieron por realizar el Nivel 2 (que fue de 3 puntos) hasta 2 puntos adicionales, con lo que la implementación del Nivel 3 acarreará la consecución de una puntuación máxima de 5 puntos. Sólo se podrá optar a este Nivel si se ha implementado satisfactoriamente y en su totalidad los requerimientos especificados en el Nivel 2.
Lo que se pretende que el alumno desarrolle en este nivel es la parte de gestión de la venta del establecimiento comercial. De este modo, el sistema deberá permitir lo siguiente:
* Llevar un control de las diferentes ventas que se producen. Así, el sistema deberá llevar un control de tickets generados, de modo que cada ticket se considerará una venta. Cada ticket tiene que tener un código de identificador único. Una forma de generar un código único podría ser de la forma AAAAMMDDHHMM, donde AAAA es
el año en curso, MM el mes en que se genera la venta, DD el día de la venta, HHMM las horas y minutos en las que se inicia la venta. Asumiremos que sólo hay un TPV,
por lo que no procede que haya dos ventas simultáneas. 
* La venta consistirá en la inclusión de varios productos en una lista, generándose una línea por cada producto vendido. Cada línea mostrará, al menos, el código del
producto, la descripción del producto, la cantidad de unidades vendidas, el precio unitario con IVA, el IVA que se le aplica y el importe total de la venta de ese producto según el número de unidades vendidas.
* El proceso de venta implicará automáticamente un proceso de actualización del inventario tal y como se ha definido en el Nivel 2. De este modo, si se introduce un
código que no pertenece a ningún producto, o si se introduce un producto que no existe en stock (o más unidades de las existentes), el programa deberá mostrar los
errores correspondientes.
* El sistema deberá permitir también introducir un producto a vender en el ticket haciendo una búsqueda por la descripción, además de con el código que lo identifica.
* Realizar la importación y/o exportación de los diferentes tickets de ventas a/desde ficheros (u otro método similar que el alumno considere en su lugar).
* Modificación del documento escrito desarrollado en el Nivel 2 (memoria de la práctica) y los correspondientes ficheros para BlueJ de modo que se implemente esta
funcionalidad y quede reflejada dicha modificación en la memoria correspondiente.

Del mismo modo, la memoria contendrá un Manual de Usuario simple que muestre la forma de manejar la aplicación con las funcionalidades añadidas en este nivel. La
implementación puede ser realizada en modo textual (sin usar librerías gráficas).

### Nivel 4 - Puntuación Total Máxima a Obtener: 7 puntos.
Los alumnos que implementen este nivel de finalización de la práctica podrán sumar a la calificación que obtuvieron por realizar el Nivel 3 (que fue de 5 puntos) hasta 2 puntos adicionales, con lo que la implementación del Nivel 4 acarreará la consecución de una puntuación máxima de 7 puntos. Sólo se podrá optar a este Nivel si se ha implementado satisfactoriamente y en su totalidad los requerimientos especificados en el Nivel 3. 
Lo que se pretende que el alumno desarrolle en este nivel es la parte de facturación y clientes del establecimiento comercial. De este modo, el sistema deberá permitir lo siguiente:
* Llevar un control de los diferentes clientes que trabajan con el establecimiento comercial. Así, los clientes habrán de estar identificados en el sistema por, al menos, los siguientes datos: código identificativo del cliente, NIF o CIF, nombre y apellidos / razón social, domicilio, fecha de alta en el sistema.
* El sistema debe permitir dar de alta nuevos clientes, dar de baja clientes existentes así como modificar los datos de los mismos.
* Realizar la importación y/o exportación de los clientes a/desde ficheros (u otro método similar que el alumno considere en su lugar).
* Permitir generar facturas a partir de un conjunto de tickets. Puede generar facturas agrupando diferentes tickets siempre y cuando pertenezcan al mismo cliente y se han realizado dentro del mismo periodo fiscal (es decir, dentro del mismo año). La información que irá en cada factura deberá ser, al menos, la siguiente: número de la factura (identificador único), CIF del vendedor, razón social del vendedor, fecha de emisión de la factura, datos del cliente (los indicados con anterioridad, excepto la fecha de alta en el sistema), listado de los diferentes productos vendidos (especificando para cada producto, el ticket en el que se encuentra, su cantidad
vendida e importe total) así como suma del total de la venta (valor total de la factura).
* Realizar la importación y/o exportación de las facturas a/desde ficheros (u otro método similar que el alumno considere en su lugar).
* El sistema deberá permitir también introducir un producto a vender en el ticket haciendo una búsqueda por la descripción, además de con el código que lo identifica.
* Realizar la importación y/o exportación de los diferentes tickets de ventas a/desde ficheros (u otro método similar que el alumno considere en su lugar).
* Generación de listados: se deberá implementar, al menos, la emisión de tres listados, a saber: ventas realizadas en un intervalo de tiempo determinado agrupadas estas ventas por clientes, ventas realizadas en un intervalo de tiempo determinado a un cliente y ranking de productos más vendidos en un intervalo de tiempo determinado.
* Modificación del documento escrito desarrollado en el Nivel 3 (memoria de la práctica) y los correspondientes ficheros para BlueJ de modo que se implemente esta
funcionalidad y quede reflejada dicha modificación en la memoria correspondiente.
Del mismo modo, la memoria contendrá un Manual de Usuario simple que muestre la forma de manejar la aplicación con las funcionalidades añadidas en este nivel. La
implementación puede ser realizada en modo textual (sin usar librerías gráficas).

### Nivel 5 - Puntuación Total Máxima a Obtener: 10 puntos.
Los alumnos que implementen este nivel de finalización de la práctica podrán sumar a la calificación que obtuvieron por realizar el Nivel 4 (que fue de 7 puntos) hasta 3 puntos adicionales, con lo que la implementación del Nivel 5 acarreará la consecución de una puntuación máxima de 10 puntos. Sólo se podrá optar a este Nivel si se ha implementado satisfactoriamente y en su totalidad los requerimientos especificados en el Nivel 4.
Lo que se pretende que el alumno desarrolle en este nivel es llevar a cabo la implementación en un entorno gráfico de todos los niveles planteados en la práctica
(Niveles 1 a 4). De este modo, habrá de implementare de manera gráfica tanto la gestión de inventario/stocks, como la gestión de venta, gestión de usuarios y listados. Los requerimientos de funcionamiento en el modo gráfico serán exactamente los mismos que los que se expusieron en cada uno de los niveles. Se deja al alumno libertad completa para decidir el estilo y diseño gráfico que puede desarrollar en este punto.
Del mismo modo, para la superación de este nivel se exigirá la modificación del documento escrito desarrollado en el Nivel 4 (memoria de la práctica) y los
correspondientes ficheros para BlueJ de modo que se implemente esta funcionalidad y quede reflejada dicha modificación en la memoria correspondiente. Del mismo modo, la memoria contendrá un Manual de Usuario simple que muestre la forma de manejar la aplicación con las funcionalidades añadidas en este nivel.

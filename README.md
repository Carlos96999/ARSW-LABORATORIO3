
  
  
### Escuela Colombiana de Ingeniería
### Arquitecturas de Software – ARSW


## Laboratorio – Programación concurrente, condiciones de carrera y sincronización de hilos - Caso Inmortales

### Descripción
Este laboratorio tiene como fin que el estudiante conozca y aplique conceptos propios de la programación concurrente, además de estrategias que eviten condiciones de carrera.
### Dependencias:

* [Ejercicio Introducción al paralelismo - Hilos - BlackList Search](https://github.com/ARSW-ECI-beta/PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH)
#### Parte I – Antes de terminar la clase.

Control de hilos con wait/notify. Productor/consumidor.

1. Revise el funcionamiento del programa y ejecútelo. Mientras esto ocurren, ejecute jVisualVM y revise el consumo de CPU del proceso correspondiente. A qué se debe este consumo?, cual es la clase responsable?

Al ejecutar la clase StartProduction(), se inicia el programa y al verificar en jVisualVM nos aparece el siguiente consumo de CPU.

<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/Consumo.PNG?raw=true">

2. Haga los ajustes necesarios para que la solución use más eficientemente la CPU, teniendo en cuenta que -por ahora- la producción es lenta y el consumo es rápido. Verifique con JVisualVM que el consumo de CPU se reduzca.

Se puede denotar el consumo al cambiar las clases Consumer y Producer, aplicando para lo Threads un wait().

<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/Consumo2Punto.PNG?raw=true">

3. Haga que ahora el productor produzca muy rápido, y el consumidor consuma lento. Teniendo en cuenta que el productor conoce un límite de Stock (cuantos elementos debería tener, a lo sumo en la cola), haga que dicho límite se respete. Revise el API de la colección usada como cola para ver cómo garantizar que dicho límite no se supere. Verifique que, al poner un límite pequeño para el 'stock', no haya consumo alto de CPU ni errores.

Al realizar los cambios pertinentes se puede evidenciar que el consumo de la CPU bajo notoriamente, esto debido a los cambios aplicados en dichas clases, donde el Producer ira añadiendo a la cola los elementos y que el Consumer consuma estos mas lento de lo que son añadidos.

<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/ConsumoChange.PNG?raw=true">

#### Parte II. – Antes de terminar la clase.

Teniendo en cuenta los conceptos vistos de condición de carrera y sincronización, haga una nueva versión -más eficiente- del ejercicio anterior (el buscador de listas negras). En la versión actual, cada hilo se encarga de revisar el host en la totalidad del subconjunto de servidores que le corresponde, de manera que en conjunto se están explorando la totalidad de servidores. Teniendo esto en cuenta, haga que:

- La búsqueda distribuida se detenga (deje de buscar en las listas negras restantes) y retorne la respuesta apenas, en su conjunto, los hilos hayan detectado el número de ocurrencias requerido que determina si un host es confiable o no (_BLACK_LIST_ALARM_COUNT_).  
- Lo anterior, garantizando que no se den condiciones de carrera.  
	```
	    Después de implementar la solución, podemos evidenciar que termino al momento de encontrar todas las ocurrencias
	```
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosUno.PNG?raw=true">

#### Parte II. – Avance para la siguiente clase

Sincronización y Dead-Locks.

![](http://files.explosm.net/comics/Matt/Bummed-forever.png)

1. Revise el programa “highlander-simulator”, dispuesto en el paquete edu.eci.arsw.highlandersim. Este es un juego en el que:

	* Se tienen N jugadores inmortales.
	* Cada jugador conoce a los N-1 jugador restantes.
	* Cada jugador, permanentemente, ataca a algún otro inmortal. El que primero ataca le resta M puntos de vida a su contrincante, y aumenta en esta misma cantidad sus propios puntos de vida.
	* El juego podría nunca tener un único ganador. Lo más probable es que al final sólo queden dos, peleando indefinidamente quitando y sumando puntos de vida.

2. Revise el código e identifique cómo se implemento la funcionalidad antes indicada. Dada la intención del juego, un invariante debería ser que la sumatoria de los puntos de vida de todos los jugadores siempre sea el mismo(claro está, en un instante de tiempo en el que no esté en proceso una operación de incremento/reducción de tiempo). Para este caso, para N jugadores, cual debería ser este valor?.

	```Teniendo en cuenta que el valor de la vida es de 100, para N jugadores debería ser 100*N
	```
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigDos.PNG?raw=true">  
	
3. Ejecute la aplicación y verifique cómo funcionan las opción ‘pause and check’. Se cumple el invariante?.
	``Lastimosamente el invariante no se cumple en el caso, los valores siempre son diferentes
	``
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigTres.PNG?raw=true">    
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigTresDos.PNG?raw=true">    
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigTresTres.PNG?raw=true">    
	

4. Una primera hipótesis para que se presente la condición de carrera para dicha función (pause and check), es que el programa consulta la lista cuyos valores va a imprimir, a la vez que otros hilos modifican sus valores. Para corregir esto, haga lo que sea necesario para que efectivamente, antes de imprimir los resultados actuales, se pausen todos los demás hilos. Adicionalmente, implemente la opción ‘resume’.
	```Implementamos nuevos métodos de tipo synchronized para manejar la pausa y la reanudación de los hilos.  
	```Añadimos un bloque de código en el método run para tener un control al momento de que los personajes sean detenidos.  
	
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigCuatro.PNG?raw=true">    
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigCuatroDos.PNG?raw=true">    

5. Verifique nuevamente el funcionamiento (haga clic muchas veces en el botón). Se cumple o no el invariante?.
	```El invariante sigue sin cumplirse después de esto, ya que lo realizado fue para detener y reanudar la partida de los personajes.  
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigCinco.PNG?raw=true">    
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigCincoDos.PNG?raw=true">      
	<img src="https://github.com/Carlos96999/ARSW-LABORATORIO3/blob/main/img/parteDosHigCincoTres.PNG?raw=true">    

6. Identifique posibles regiones críticas en lo que respecta a la pelea de los inmortales. Implemente una estrategia de bloqueo que evite las condiciones de carrera. Recuerde que si usted requiere usar dos o más ‘locks’ simultáneamente, puede usar bloques sincronizados anidados:

	```java
	synchronized(locka){
		synchronized(lockb){
			…
		}
	}
	```

7. Tras implementar su estrategia, ponga a correr su programa, y ponga atención a si éste se llega a detener. Si es así, use los programas jps y jstack para identificar por qué el programa se detuvo.

8. Plantee una estrategia para corregir el problema antes identificado (puede revisar de nuevo las páginas 206 y 207 de _Java Concurrency in Practice_).

9. Una vez corregido el problema, rectifique que el programa siga funcionando de manera consistente cuando se ejecutan 100, 1000 o 10000 inmortales. Si en estos casos grandes se empieza a incumplir de nuevo el invariante, debe analizar lo realizado en el paso 4.

10. Un elemento molesto para la simulación es que en cierto punto de la misma hay pocos 'inmortales' vivos realizando peleas fallidas con 'inmortales' ya muertos. Es necesario ir suprimiendo los inmortales muertos de la simulación a medida que van muriendo. Para esto:
	* Analizando el esquema de funcionamiento de la simulación, esto podría crear una condición de carrera? Implemente la funcionalidad, ejecute la simulación y observe qué problema se presenta cuando hay muchos 'inmortales' en la misma. Escriba sus conclusiones al respecto en el archivo RESPUESTAS.txt.
	* Corrija el problema anterior __SIN hacer uso de sincronización__, pues volver secuencial el acceso a la lista compartida de inmortales haría extremadamente lenta la simulación.

11. Para finalizar, implemente la opción STOP.


<a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc/4.0/88x31.png" /></a><br />Este contenido hace parte del curso Arquitecturas de Software del programa de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería, y está licenciado como <a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/">Creative Commons Attribution-NonCommercial 4.0 International License</a>.

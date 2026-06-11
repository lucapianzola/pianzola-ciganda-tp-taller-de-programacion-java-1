# Trabajo Práctico 1 — Taller de Programación Java

**Integrantes:** Pianzola, Ciganda

## Estructura del Proyecto

```
tp1-sistemas/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           └── tp1/
│               ├── Problema1.java
│               ├── Problema2.java
│               ├── Problema3.java
│               ├── Problema4.java
│               └── Problema5.java
└── README.md
```

## Cómo ejecutar los tests

Se provee un script `ejecutable.sh` que automatiza la compilación y ejecución de todos los problemas utilizando Maven. El script asegura que las aserciones de Java (`-ea`) estén activas.

```bash
chmod +x ejecutable.sh
./ejecutable.sh
```

Alternativamente, puede ejecutar cada problema individualmente con Maven:

```bash
mvn exec:java -Dexec.mainClass="tp1.Problema1" -q
```

---

## Estrategias de Resolución

### Problema 1 — Palabra más usada

Se usa `String.split()` con una expresión regular para tokenizar el texto
eliminando cualquier carácter no alfabético (incluyendo acentos y ñ).
Las frecuencias se acumulan en un `HashMap`, actualizando el máximo en la misma pasada.
Complejidad: **O(N)** en tiempo y espacio.

### Problema 2 — Fibonacci

Solución iterativa con tres variables (`prev2`, `prev1`, `current`) que garantiza
uso de memoria **O(1)**. El tipo `long` cubre hasta `fib(90)`.
Se sigue la definición del PDF: `fib(0)=1`, `fib(1)=1`.
Para `N > 90` se provee una extensión usando `BigInteger`.
Complejidad: **O(N)**.

### Problema 3 — Árbol de búsqueda binaria

Recorrido recursivo en pre-orden que propaga límites `min` y `max` actualizables hacia cada subárbol.
Esto verifica que todo nodo cumpla la propiedad BST respecto a todos sus ancestros.
Complejidad: **O(N)**.

### Problema 4 — Fotografía artística

Uso de arreglos de suma de prefijos sobre `'f'` y `'e'`.
Al iterar sobre cada `'a'`, se obtiene en **O(1)** cuántas `'f'` y `'e'` hay en los
rangos `[i-Y, i-X]` e `[i+X, i+Y]`. Se cuentan combinaciones `f-a-e` y `e-a-f`.
Complejidad: **O(N)**.

### Problema 5 — Laberinto mágico

Algoritmo BFS para garantizar el camino más corto.
Las ubicaciones de cada portal se pre-calculan. Al pisar un portal, se encolan
todos sus destinos con costo `dist + 1`. Cada tipo de portal se activa una sola vez.
Complejidad: **O(F × C)**.

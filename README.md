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

Se usa `String.split()` con la expresión regular `[^a-zA-ZáéíóúÁÉÍÓÚñÑ]+` para tokenizar el texto
eliminando cualquier carácter no alfabético. Las frecuencias se acumulan en un `HashMap<String, Integer>`
actualizando el máximo en la misma pasada. Complejidad: **O(N)** en tiempo y espacio.

### Problema 2 — Fibonacci

Solución iterativa con tres variables (`prev2`, `prev1`, `current`) que garantiza uso de memoria **O(1)**.
El tipo `long` cubre hasta `fib(90)`. Para `N = 1000` se provee `fibonacciExtendido` usando
`java.math.BigInteger` (biblioteca estándar de Java, sin dependencias externas). Complejidad: **O(N)**.

### Problema 3 — Árbol de búsqueda binaria

Recorrido recursivo en pre-orden que propaga límites `min` y `max` actualizables hacia cada subárbol.
Así se verifica que todo nodo cumpla la propiedad BST respecto a *todos* sus ancestros, no solo su padre
inmediato. Cada nodo se visita exactamente una vez. Complejidad: **O(N)**.

### Problema 4 — Fotografía artística

En lugar de fuerza bruta **O(N³)**, se construyen **arreglos de suma de prefijos** sobre los caracteres
`'f'` y `'e'`. Al iterar sobre cada `'a'`, se obtiene en **O(1)** cuántas `'f'` y `'e'` hay en los
rangos `[i-Y, i-X]` y `[i+X, i+Y]`. Se cuentan combinaciones en ambas direcciones (f-a-e y e-a-f).
Complejidad total: **O(N)**.

### Problema 5 — Laberinto mágico

BFS garantiza el camino más corto en grafos con pesos uniformes. Las ubicaciones de cada portal se
pre-calculan en un `HashMap<Character, List<int[]>>`. Al pisar una celda-portal, se encolan *todos*
sus extremos con costo `dist + 1`. Cada portal se activa a lo sumo una vez (marcado en `portalesUsados`)
para evitar ciclos infinitos. Complejidad: **O(F × C)** donde F y C son filas y columnas del mapa.

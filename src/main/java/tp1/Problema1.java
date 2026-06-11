package tp1;

import java.util.HashMap;
import java.util.Map;

/**
 * Estrategia: Se usa String.split() con una expresión regular para dividir el texto
 * eliminando cualquier carácter no alfabético.
 * Las frecuencias se acumulan en un HashMap, actualizando el máximo en la misma pasada.
 * Complejidad: O(N).
 */
public class Problema1 {

    public static String palabraMasUsada(String texto, int N) {
        if (texto == null || texto.isEmpty()) return null;

        Map<String, Integer> frecuencias = new HashMap<>();
        // Dividimos el texto en palabras eliminando caracteres no alfabéticos
        String[] palabras = texto.split("[^a-zA-ZáéíóúÁÉÍÓÚñÑ]+");

        String maxPalabra = null;
        int maxFrec = 0;

        for (String p : palabras) {
            if (p.length() >= N) {
                p = p.toLowerCase();
                // Actualizamos la frecuencia y mantenemos registro de la palabra más usada
                int count = frecuencias.getOrDefault(p, 0) + 1;
                frecuencias.put(p, count);

                if (count > maxFrec) {
                    maxFrec = count;
                    maxPalabra = p;
                }
            }
        }
        return maxPalabra;
    }

    public static void main(String[] args) {
        String texto = "Hola mundo. Hola a todos; el mundo es gigante. HOLA";
        String resultado = palabraMasUsada(texto, 4);
        System.out.println("Salida esperada: hola | Obtenida: " + resultado);
        assert "hola".equals(resultado) : "Fallo en el Problema 1";

        
        String r2 = palabraMasUsada("uno dos dos tres tres tres", 1);
        System.out.println("Salida esperada: tres | Obtenida: " + r2);
        assert "tres".equals(r2) : "Fallo en el Problema 1 - caso 2";

        
        String r3 = palabraMasUsada("", 1);
        System.out.println("Texto vacío esperado: null | Obtenido: " + r3);
        assert r3 == null : "Fallo en el Problema 1 - caso vacío";

        System.out.println("Test P1 superado.");
    }
}

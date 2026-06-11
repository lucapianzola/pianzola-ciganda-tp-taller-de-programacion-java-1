package tp1;

/**
 * Estrategia: Uso de arreglos de suma de prefijos sobre 'f' y 'e'. 
 * Al iterar sobre cada 'a', se obtiene en O(1) cuántas 'f' y 'e' hay en los 
 * rangos [i-Y, i-X] e [i+X, i+Y]. Se cuentan combinaciones f-a-e y e-a-f.
 * Complejidad: O(N).
 */
public class Problema4 {

    public static long contarFotografias(String A, int X, int Y) {
        int n = A.length();
        int[] prefF = new int[n + 1];
        int[] prefE = new int[n + 1];

        // Construimos arreglos de sumas acumuladas para contar 'f' y 'e' en rangos
        for (int i = 0; i < n; i++) {
            prefF[i + 1] = prefF[i] + (A.charAt(i) == 'f' ? 1 : 0);
            prefE[i + 1] = prefE[i] + (A.charAt(i) == 'e' ? 1 : 0);
        }

        long total = 0;
        for (int i = 0; i < n; i++) {
            if (A.charAt(i) == 'a') {
                // Al encontrar una 'a', buscamos 'f' y 'e' en los rangos permitidos [X, Y]
                int leftStart = Math.max(0, i - Y);
                int leftEnd   = i - X;
                int rightStart = i + X;
                int rightEnd   = Math.min(n - 1, i + Y);

                long fIzquierda = count(prefF, leftStart, leftEnd);
                long eDerecha   = count(prefE, rightStart, rightEnd);

                long eIzquierda = count(prefE, leftStart, leftEnd);
                long fDerecha   = count(prefF, rightStart, rightEnd);

                total += (fIzquierda * eDerecha) + (eIzquierda * fDerecha);
            }
        }
        return total;
    }

    private static int count(int[] pref, int start, int end) {
        if (start > end) return 0;
        return pref[end + 1] - pref[start];
    }

    public static void main(String[] args) {
        long r1 = contarFotografias("afaea", 1, 2);
        System.out.println("Caso 1 Esperado: 1 | Obtenido: " + r1);
        assert r1 == 1 : "Fallo P4 - caso 1";

        long r2 = contarFotografias("afaea", 2, 3);
        System.out.println("Caso 2 Esperado: 0 | Obtenido: " + r2);
        assert r2 == 0 : "Fallo P4 - caso 2";

        long r3 = contarFotografias(".feaaf.e", 1, 3);
        System.out.println("Caso 3 Esperado: 3 | Obtenido: " + r3);
        assert r3 == 3 : "Fallo P4 - caso 3";

    long r4 = contarFotografias("fffeeee", 1, 5);
        System.out.println("Sin 'a' Esperado: 0 | Obtenido: " + r4);
        assert r4 == 0 : "Fallo P4 - sin 'a'";

        System.out.println("Test P4 superado.");
    }
}

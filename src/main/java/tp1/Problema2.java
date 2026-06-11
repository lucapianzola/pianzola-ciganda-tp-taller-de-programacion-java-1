package tp1;

import java.math.BigInteger;

/**
 * Estrategia: Solución iterativa con tres variables (prev2, prev1, current) que 
 * garantiza uso de memoria O(1). El tipo long cubre hasta fib(90). 
 * Se sigue la definición del PDF: fib(0)=1, fib(1)=1.
 * Para N > 90 se provee una extensión usando BigInteger.
 * Complejidad: O(N).
 */
public class Problema2 {

    public static long fibonacci(int n) {
        if (n < 0 || n > 90) throw new IllegalArgumentException("N debe estar entre 0 y 90");
        if (n == 0 || n == 1) return 1;

        long prev2 = 1, prev1 = 1, current = 0;
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static BigInteger fibonacciExtendido(int n) {
        if (n < 0) throw new IllegalArgumentException("N debe ser no negativo");
        if (n == 0 || n == 1) return BigInteger.ONE;

        BigInteger prev2 = BigInteger.ONE, prev1 = BigInteger.ONE, current = BigInteger.ZERO;
        for (int i = 2; i <= n; i++) {
            current = prev1.add(prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static void main(String[] args) {
        
        assert fibonacci(0) == 1  : "Fallo fib(0)";
        assert fibonacci(1) == 1  : "Fallo fib(1)";
        assert fibonacci(2) == 2  : "Fallo fib(2)";
        assert fibonacci(5) == 8  : "Fallo fib(5)";
        assert fibonacci(10) == 89 : "Fallo fib(10)";

        System.out.println("fib(5)  Esperado: 8  | Obtenido: " + fibonacci(5));
        System.out.println("fib(10) Esperado: 89 | Obtenido: " + fibonacci(10));
        System.out.println("fib(1000) = " + fibonacciExtendido(1000));

        boolean threw = false;
        try { fibonacci(91); } catch (IllegalArgumentException e) { threw = true; }
        assert threw : "Fallo: debería lanzar excepción para n=91";

        System.out.println("Test P2 superado.");
    }
}

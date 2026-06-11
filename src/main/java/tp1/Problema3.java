package tp1;

/**
 * Estrategia: Recorrido recursivo en pre-orden que propaga límites min y max 
 * actualizables hacia cada subárbol. Esto verifica que todo nodo cumpla la 
 * propiedad BST respecto a todos sus ancestros.
 * Complejidad: O(N).
 */
public class Problema3 {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static boolean esArbolDeBusqueda(Node raiz) {
        return esValido(raiz, null, null);
    }

    private static boolean esValido(Node node, Integer min, Integer max) {
        if (node == null) return true;
        // El valor del nodo debe estar estrictamente dentro del rango (min, max)
        if (min != null && node.data <= min) return false;
        if (max != null && node.data >= max) return false;
        // Al bajar al hijo izquierdo el máximo es el nodo actual, al derecho el mínimo es el nodo actual
        return esValido(node.left, min, node.data) && esValido(node.right, node.data, max);
    }

    public static void main(String[] args) {
        
        Node raiz = new Node(10);
        raiz.left = new Node(5);
        raiz.right = new Node(15);
        raiz.left.right = new Node(7);
        assert esArbolDeBusqueda(raiz) : "Fallo P3 - árbol válido";
        System.out.println("Árbol válido  Esperado: true  | Obtenido: " + esArbolDeBusqueda(raiz));

        
        Node invalido = new Node(10);
        invalido.left = new Node(5);
        invalido.right = new Node(15);
        invalido.left.right = new Node(12);
        assert !esArbolDeBusqueda(invalido) : "Fallo P3 - árbol inválido";
        System.out.println("Árbol inválido Esperado: false | Obtenido: " + esArbolDeBusqueda(invalido));

        
        assert esArbolDeBusqueda(new Node(42)) : "Fallo P3 - nodo único";

        
        assert esArbolDeBusqueda(null) : "Fallo P3 - nulo";

        System.out.println("Test P3 superado.");
    }
}

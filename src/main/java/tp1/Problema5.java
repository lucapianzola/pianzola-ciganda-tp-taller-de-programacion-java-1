package tp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Estrategia: Algoritmo BFS para garantizar el camino más corto. 
 * Las ubicaciones de cada portal se pre-calculan. Al pisar un portal, se encolan 
 * todos sus destinos con costo dist + 1. Cada tipo de portal se activa una sola vez.
 * Complejidad: O(F x C).
 */
public class Problema5 {

    public static int resolverLaberinto(char[][] mapa) {
        int F = mapa.length;
        int C = mapa[0].length;

        Map<Character, List<int[]>> portales = new HashMap<>();
        int startR = -1, startC = -1;

        for (int i = 0; i < F; i++) {
            for (int j = 0; j < C; j++) {
                char celda = mapa[i][j];
                if (celda == 'E') {
                    startR = i;
                    startC = j;
                } else if (celda >= 'a' && celda <= 'z') {
                    portales.computeIfAbsent(celda, k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }

        boolean[][] visitado = new boolean[F][C];
        Queue<int[]> queue = new LinkedList<>();
        Set<Character> portalesUsados = new HashSet<>();

        visitado[startR][startC] = true;
        queue.add(new int[]{startR, startC, 0});

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], dist = curr[2];
            char celda = mapa[r][c];

            if (celda == 'S') return dist;

            // Exploramos los 4 movimientos adyacentes posibles (arriba, abajo, izquierda, derecha)
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < F && nc >= 0 && nc < C
                        && !visitado[nr][nc] && mapa[nr][nc] != '#') {
                    visitado[nr][nc] = true;
                    queue.add(new int[]{nr, nc, dist + 1});
                }
            }

            // Si la celda es un portal (a-z), saltamos a todos sus extremos con costo 1
            if (celda >= 'a' && celda <= 'z' && !portalesUsados.contains(celda)) {
                portalesUsados.add(celda);
                for (int[] p : portales.get(celda)) {
                    if (!visitado[p[0]][p[1]]) {
                        visitado[p[0]][p[1]] = true;
                        queue.add(new int[]{p[0], p[1], dist + 1});
                    }
                }
            }
        }
    return -1;
    }

    public static void main(String[] args) {
        // Ejemplo 1 del PDF: 4
        char[][] mapa1 = {
            {'E', '.', '.'},
            {'.', '.', '.'},
            {'.', '.', 'S'}
        };
        int r1 = resolverLaberinto(mapa1);
        System.out.println("Caso 1 Esperado: 4 | Obtenido: " + r1);
        assert r1 == 4 : "Fallo P5 - caso 1";

        // Ejemplo 2 del PDF: -1
        char[][] mapa2 = {
            {'E', '#', '.'},
            {'.', '#', '.'},
            {'.', '#', 'S'}
        };
        int r2 = resolverLaberinto(mapa2);
        System.out.println("Caso 2 Esperado: -1 | Obtenido: " + r2);
        assert r2 == -1 : "Fallo P5 - caso 2";

        // Ejemplo 3 del PDF: 2
        char[][] mapa3 = {
            {'E', '.', 'S'},
            {'.', '.', '.'},
            {'.', '.', 'S'}
        };
        int r3 = resolverLaberinto(mapa3);
        System.out.println("Caso 3 Esperado: 2 | Obtenido: " + r3);
        assert r3 == 2 : "Fallo P5 - caso 3";

        // Ejemplo 4 del PDF: 13
        char[][] mapa4 = {
            {'S', '.', 'b', '#', 'b'},
            {'#', '#', '#', '#', 'a'},
            {'.', '.', 'E', '#', '#'},
            {'c', '#', '#', '.', 'c'},
            {'#', 'a', '.', '.', '.'}
        };
        int r4 = resolverLaberinto(mapa4);
        System.out.println("Caso 4 Esperado: 13 | Obtenido: " + r4);
        assert r4 == 13 : "Fallo P5 - caso 4";

        System.out.println("Test P5 superado.");
    }
}

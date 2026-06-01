package tp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

            // Movimiento adyacente
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < F && nc >= 0 && nc < C
                        && !visitado[nr][nc] && mapa[nr][nc] != '#') {
                    visitado[nr][nc] = true;
                    queue.add(new int[]{nr, nc, dist + 1});
                }
            }

            // Movimiento por portal (costo 1)
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
        return -1; // sin solución
    }

    public static void main(String[] args) {
        // Caso 1: camino directo sin portales
        // . . .
        // . E .
        // . . S
        // Camino más corto: E(1,1) -> (1,2) -> (2,2) -> S  distancia 3... o (2,1)->(2,2) distancia 2?
        // E está en (1,1), S en (2,2). Distancia Manhattan = 2 pasos.
        char[][] mapa1 = {
            {'.', '.', '.'},
            {'.', 'E', '.'},
            {'.', '.', 'S'}
        };
        int r1 = resolverLaberinto(mapa1);
        System.out.println("Caso 1 Esperado: 2 | Obtenido: " + r1);
        assert r1 == 2 : "Fallo P5 - caso 1";

        // Caso 2: laberinto con portal
        // E a . #
        // # # . #
        // S . a .
        // Portal 'a' conecta (0,1) con (2,2). Camino: E->a(0,1)->a(2,2)->S(2,0)?
        // Desde (2,2) a S(2,0): 2 pasos. Total: 1(E->a) + 1(portal) + 2 = 4
        char[][] mapa2 = {
            {'E', 'a', '.', '#'},
            {'#', '#', '.', '#'},
            {'S', '.', 'a', '.'}
        };
        int r2 = resolverLaberinto(mapa2);
        System.out.println("Caso 2 (portal) Esperado: 4 | Obtenido: " + r2);
        assert r2 == 4 : "Fallo P5 - caso 2";

        // Caso 3: sin salida
        char[][] mapa3 = {
            {'E', '#'},
            {'#', 'S'}
        };
        int r3 = resolverLaberinto(mapa3);
        System.out.println("Caso 3 sin solución Esperado: -1 | Obtenido: " + r3);
        assert r3 == -1 : "Fallo P5 - caso 3";

        System.out.println("Test P5 superado.");
    }
}

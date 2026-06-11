#!/bin/bash

# Este script utiliza Maven para compilar y ejecutar los problemas.
# Se asegura de que las aserciones esten activas (-ea).

LOG_FILE="resultados.txt"

{
    echo "========================================"
    echo "Inicio de compilacion con Maven..."
    echo "========================================"
    
    # Compilamos el proyecto. Si falla, el script se detiene.
    mvn clean compile
    
    if [ $? -ne 0 ]; then
        echo ""
        echo "ERROR: La compilacion con Maven fallo."
        exit 1
    fi

    echo ""
    echo "========================================"
    echo "Ejecutando Casos de Prueba - TP1"
    echo "========================================"

    for i in {1..5}
    do
        echo ""
        echo ">>> Ejecutando problema $i <<<"
        # Usamos el plugin de exec para correr cada clase individualmente
        # -q (quiet) para que Maven no ensucie la salida con sus logs de configuracion
        mvn exec:java -Dexec.mainClass="tp1.Problema$i" -q
    done

    echo ""
    echo "========================================"
    echo "Todos los ejercicios finalizaron"
    echo "========================================"
    
} | tee "$LOG_FILE"

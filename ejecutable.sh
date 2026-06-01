#!/bin/bash

LOG_FILE="resultados.txt"

{   
    mkdir -p target/classes
    
    javac -d target/classes src/main/java/tp1/*.java

    echo ""
    echo "========================================"
    echo "Ejecutando Casos de Prueba - TP1"
    echo "========================================"

    for i in {1..5}
    do
        echo ""
        echo ">>> Ejecutando Problema $i <<<"
        java -ea -cp target/classes tp1.Problema$i 2>&1
    done

    echo ""
    echo "========================================"
    echo "¡Todos los ejercicios finalizaron!"
    echo "========================================"
    
} | tee "$LOG_FILE"
#!/bin/bash

declare implementations=""
#implementations="${implementations} de.red6.gameoflife.impl.saraj.BoardImpl"
#implementations="${implementations} de.red6.gameoflife.impl.saraj2.BoardImpl"
implementations="${implementations} de.red6.gameoflife.daniel.GameOfLifeMitAbbruch"
implementations="${implementations} de.red6.gameoflife.daniel.GameOfLife"

declare filling_level="10 40 80"
declare size="16 1024 4096"
declare steps=200

echo "implementation,boardSize,level,steps,milliSeconds"
for impl in $implementations ; do
    for board_size in $size ; do
        for level in $filling_level ; do
            declare elapsed=$(java -cp target/gameOfLifeRunner-1.0-SNAPSHOT.jar de.red6.gameoflife.runner.Runner "${impl}" "${board_size}" 64357452154576 "${level}" "${steps}")
            echo "${impl},${board_size},${level},${steps},${elapsed}"
        done
    done
done

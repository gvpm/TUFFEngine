reset
set grid
#set ytic 0.1
#set xtic 0.1
set yrange[0:150]
set xrange[0:100]
set xlabel "Density"
set ylabel "AvgVel km/h"
 

plot 'plotar.txt' using ($2):($3) w p t "Velocidade/Densidade"
#plot 'plotar.txt' using ($14):($13) w p t "Direita"

set terminal pdf color 

set out 'plotado-fluxo-dens-esq.pdf'
replot


set terminal postscript eps color lw 2 "Helvetica" 20

set out 'plotado-fluxo-dens-esq.eps'
replot




reset
set grid
#set ytic 0.1
#set xtic 0.1
set yrange[0:1500]
set xrange[0:100]
#set yrange[0:3600]
#set xrange[0:133]
set xlabel "Density"
set ylabel "Flow"
 

plot 'plotar.txt' using ($2):($1) w p t "Fluxo Densidade"
#plot 'plotar.txt' using ($14):($13) w p t "Direita"

set terminal pdf color 

set out 'plotado-fluxo-dens-esq.pdf'
replot


set terminal postscript eps color lw 2 "Helvetica" 20

set out 'plotado-fluxo-dens-esq.eps'
replot




reset
set grid
#set ytic 0.1
#set xtic 0.1
#set yrange[0:1850]
#set xrange[0:100]
set yrange[0:2600]
set xrange[0:100]
set xlabel "Density"
set ylabel "Flow"
 

plot 'plotar.txt' using ($2):($1) w p t "Fluxo Densidade"

set terminal pdf color 

set out 'plotado-fluxo-dens.pdf'
replot


set terminal postscript eps color lw 2 "Helvetica" 20

set out 'plotado-fluxo-dens.eps'
replot




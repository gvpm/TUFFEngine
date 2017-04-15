# TUFFEngine
A java version of the TUFF Simulator program that implements the Traffic Simulation Model presented in the following publication. 

Marcelo Zamith ; TOLEDO, R. C. P. L. ; Esteban Clua ; Elson M. Toledo ; Guilherme V.P. Magalhães . A new stochastic cellular automata model for traffic flow simulation with drivers behavior prediction. Journal of Computational Science, v. 9, p. 51-56, 2015.  doi:10.1016/j.jocs.2015.04.005

The first program was originally developed by Marcelo Zamith in c++.

This is a netbeans project.

## Running the simulation.
If you just want to run the simulation you only need the files from **TUFFEngineRun** folder.
There you will find 2 files:
- The "TUFFEngine.jar": The program itself.
- The "simulation.txt": The default configuration file.
  A file on this model is required to run the simulation.
  
There are 2 ways of running the simulation.
- #### Using the simulation GUI
  To use the simulation GUI you simply need to click on the "TUFFEngine.jar" file, a window will open and the simulation will run with the parameters set on the 'simulation.txt' file that should be in the same folder.
  
- #### Via prompt:


   If you want to run a configuraton file with a different name you need to run the program via prompt.
   
   To do that you need to open the folder containing the files. Use `cd` command to open teh desired folder.
   
   After that, use the following command to run the simulation.
   
   `java -jar TUFFEngine.jar 'yourConfigFileName.txt'`
   
   The file you pick should be in the same folder.

  
  



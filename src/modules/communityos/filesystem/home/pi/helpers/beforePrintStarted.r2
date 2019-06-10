G21  ; set to millimeters
G90  ; set to absolute mode
G92 E0  ; zero extruder
M107  ; turn off fans
M104 S230  ;  non-blocking heat up of extruder (ABS, PETG)
M140 S60  ; non-blocking heat up of bed
G36  ; robo firmware HOME
G4 S2  ; pause for 2 seconds
G1 Z15 F300  ; move bed down 15mm
G1 X10 Y1 F7200  ; move to front left corner
M109 S230  ; heat to priming line temp
G1 Z0.3  ; move bed to printing position
G1 X190 E15 F500  ; print 190mm priming line
G1 Z15 F300  ; move bed down
G92 E0  ; zero extruder
G1 F7200  ; set movement speed

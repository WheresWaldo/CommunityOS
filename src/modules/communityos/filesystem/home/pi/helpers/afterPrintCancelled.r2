M410  ; Quick-Stop
M104 S0  ; turn off hotend heater
M140 S0  ; turn off the bed heater
G91  ; set relative mode
G1 Z10  ; move bed down 10 mm
G1 Z10
G90  ; set to absolute mode
G28 X0 Y0  ; home XY axis
G92 E0  ; zero extruder
M84 ; turn off motors
M107  ; turn off fans
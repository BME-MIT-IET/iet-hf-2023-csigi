@echo off

set /p l=load: 
set /p s=save: 


REM Futtatas:

if not defined l ( if not defined s ( java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.domain.Controller
pause ))
if not defined l ( if defined s ( java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.domain.Controller >"%s%.txt" ))
if defined l ( if not defined s ( type "Test_txt\%l%.txt"|java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.domain.Controller
pause ))
if defined l ( if defined s ( type "Test_txt\%l%.txt"|java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.domain.Controller >"%s%.txt" ))
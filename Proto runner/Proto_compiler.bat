@echo off

REM Mappak letrehozasa, ha kell:
if not exist Vilagtalan_virologusok\ mkdir Vilagtalan_virologusok

if not exist Vilagtalan_virologusok\forditott\ mkdir Vilagtalan_virologusok\forditott

set /p l=load: 
set /p s=save: 


REM Unzip
tar -xf Proto.zip -C Vilagtalan_virologusok

REM Fordiatas, futtatas:
javac -encoding UTF-8 -d Vilagtalan_virologusok\forditott Vilagtalan_virologusok\*.java

if not defined l ( if not defined s ( java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.Controller
pause ))
if not defined l ( if defined s ( java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.Controller >"%s%.txt" ))
if defined l ( if not defined s ( type "Test_txt\%l%.txt"|java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.Controller
pause ))
if defined l ( if defined s ( type "Test_txt\%l%.txt"|java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.Controller >"%s%.txt" ))
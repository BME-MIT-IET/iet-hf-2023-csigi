@echo off

REM Mappak letrehozasa, ha kell:
if not exist Vilagtalan_virologusok\ mkdir Vilagtalan_virologusok

if not exist Vilagtalan_virologusok\forditott\ mkdir Vilagtalan_virologusok\forditott


REM Unzip
tar -xf Skeleton.zip -C Vilagtalan_virologusok

REM Fordiatas, futtatas:
javac -encoding UTF-8 -d Vilagtalan_virologusok\forditott Vilagtalan_virologusok\*.java
java -cp Vilagtalan_virologusok\forditott\ vilagtalan_virologusok.domain.Controller
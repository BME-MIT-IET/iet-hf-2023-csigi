# Build keretrendszer b�zemel�se


### �sszefoglal�s

A build keretrendszer be�zemel�se k�zel sem volt annyira trivi�lis folyamat, mint amennyire terveztem.
A java projekthez a Maven keretrendszert haszn�ltam.
A f� probl�m�t egy olyan dependency jelentette, aminek a jelenl�te nem volt egy�rtelm� a projekt f�jljaib�l ad�d�an, mivel nem tartalmazta a projekt.
Ez egy IntelliJ specifikus Swing UI �p�t� GUI, melynek a f�gg�s�g�t el�sz�r megpr�b�ltam �gy csatolni, hogy ne kelljen a hozz�tartoz� forms_rt.jar-t belehelyezni a projektbe.
Sok hosszadalmas �ra kis�rletez�s sor�n kider�lt, hogy az �gy buildel� program t�bb, megmagyar�zhatatlan hib�t tartalmaz (pl. Include hi�nya, ami nem hi�nyzott).

### Tanuls�g, Eredm�nyek

V�g�l a hagyom�nyos megk�zel�t�s, hogy bem�solom a jar-t m�k�d�tt, csak emell� be kellett �ll�tani a forms f�jloknak, hogy ne bin�risan buildeljenek.
A feladat tanuls�ga, hogy nem szabad al�becs�lni semmilyen feladatot, mert b�rmikor felbukkanhat valamilyen v�ratlan probl�ma, ami megsokszorozhatja a tervezett id�tartamot.
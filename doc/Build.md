A build keretrendszer beüzemelése közel sem volt annyira triviális folyamat, mint amennyire terveztem.
A java projekthez a Maven keretrendszert használtam.
A fõ problémát egy olyan dependency jelentette, aminek a jelenléte nem volt egyértelmû a projekt fájljaiból adódóan, mivel nem tartalmazta a projekt.
Ez egy IntelliJ specifikus Swing UI építõ GUI, melynek a függõségét elõször megpróbáltam úgy csatolni, hogy ne kelljen a hozzátartozó forms_rt.jar-t belehelyezni a projektbe.
Sok hosszadalmas óra kisérletezés során kiderült, hogy az így buildelõ program több, megmagyarázhatatlan hibát tartalmaz (pl. Include hiánya, ami nem hiányzott).

Végül a hagyományos megközelítés, hogy bemásolom a jar-t mûködött, csak emellé be kellett állítani a forms fájloknak, hogy ne binárisan buildeljenek.
A feladat tanulsága, hogy nem szabad alábecsülni semmilyen feladatot, mert bármikor felbukkanhat valamilyen váratlan probléma, ami megsokszorozhatja a tervezett idõtartamot.
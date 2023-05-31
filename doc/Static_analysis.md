## Elvégzett feladat

A munkám során megismerkedtem többféle statikus kódellenőrző szoftverrel (pl. JetBrains-es Qodana ami még elég kevés dokumentációval rendelkezik, SpotBugs). Végül a Sonar mellett döntöttem, mert ahhoz rengeteg dokumentáció és anyag elérhető, és intuitív a felhasználói felülete. Mivel nem volt jogosultságom a repót hozzáadni SonarCloudhoz, ezért egy lokális SonarQube-on futtattam a projekt ellenőrzését. Ezután végignéztem a talált hibákat, és javítottam őket, lehetőség szerint ügyelve arra, hogy csak az összetartozó változások kerüljenek egy commitba.

## Eredmények, tanulságok

Több, különböző típusú hibákat javítottam, amik nagyrészt a Clean Code égisze alá tartoznak, például elnevezési konvenciók és annotációk. Kellemes volt látni, hogy a SonarQube-on hogyan csökkennek a bugok és code smellek száma minden egyes commit-tal. Nagyon hasznos eszköznek érzem, mert a biztonsági réseket és a teljesítményrontó kódokat is kijelzi, igaz ez a mi projektünknél nem volt nagy jelentőségű. Segítségével jobban tudnak a fejlesztők az algoritmusokra és a nagy képre összpontosítani, nem kell az apró, kódolási részletekben elveszniük, megcsinálja helyettük a Sonar.
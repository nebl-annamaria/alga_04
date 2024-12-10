## Algoritmusok és adatszerkezetek gyakorlat beadandó feladat

### Nébl Annamária, Neptun: K04POD

### Fák, gráfok

Link a feladathoz:
[Labyrinth](https://cses.fi/problemset/task/1193)

CSES tesztfileok a projektbe csatolva unit tesztekként

## Feladat:
A feladatban megkapjuk egy labirintus térképét, 
ennek alapján kell megtalálni a helyes utat 
a kezdőponttól a végpontig.

## Input:
Első sor: n és m egész számok, melyek a térkép magasságát és szélességét jelölik

Ezután n db sor következik, melyek mindegyike m db karaktert tartalmaz.
* . : padló
* \# : fal 
* A : kezdőpont 
* B : végpont

## Algoritmus:
Szélességi keresés (BFS)

## Kivitelezés:
1. **Adatok inicializálása**:
    - a labirintust (`maze`) egy 2D karakter tömbben tároljuk
    - a kezdő- és végpont koordinátáit beolvassuk

2. **Irányok és segédváltozók definiálása**:
    - a négy lehetséges mozgásirány (`dx` és `dy` tömbök): balra, jobbra, fel, le.
    - betűkódjaik (`dir`): `'L', 'R', 'U', 'D'`.

3. **szélességi keresés indítása**:
    - egy `Queue` tárolja a meglátogatandó csomópontokat. Minden elem egy `[x, y]` koordinátapár
    - egy `visited` mátrix nyilvántartja, hogy mely cellákat látogattuk már meg
    - a kezdőpontot hozzáadják a `Queue`-hoz, és megjelöljük látogatottként

4. **Szomszédos cellák vizsgálata**:
    - amíg a `Queue` nem üres, egy cellát (`[x, y]`) kiolvasunk belőle
    - minden szomszédos cellára ellenőrzik, hogy:
        - a labirintuson belül van-e
        - nem akadály-e
        - még nem látogatták meg
    - ha egy cella megfelel, akkor hozzáadják a `Queue`-hoz, látogatottnak jelöljük, és rögzítjük, hogy melyik cellából érkeztek oda (`parent` tömb)

5. **Útvonal rekonstrukciója**:
    - ha a végpontot (`B`) eléri az algoritmus, a `parent` tömb segítségével visszafelé rekonstruáljuk az utat
    - az utat a kezdőponttól a végpontig fordított sorrendben összerakjuk, majd megfordítjuk, és a mozgásirányok kódjait (`L`, `R`, `U`, `D`) visszaadjuk

6. **Eredmény kiírása**:
    - lépések száma
    - útvonal

***Lényeg***

 * Program modelljének felépítése

***3.1***

 * Objektumkatalógus
 * Objektumok felelősségeinek leírása
 * Angol nyelvű osztályok, ha lehet... :)

***3.2***

 * Osztálydiagramok
 * Interfészek, absztrakt ősök
 * Ami a 3.1-ben benne van, ezen is rajta kell legyen
 * A grafikus felülettel nem kell itt foglalkozni

***3.3***

 * Osztályok leírása
 * 3.1-es, +interfészek, ősosztályok, metódusok mire valók, attribútumok
 * Sablon betartása!

***3.4 Szekvenciadiagramok***

 * Mit akarunk csinálni
 * Összes belső működésről
 * Pl, hogyan lő a torony
 * Minden belső use-case-ről
 * 20-30 diagram, de legalább 15

***3.5***

 * Állapotátmenetek
 * Játékra nem csinálunk!
 * 1 state-chart - 1 objektum


***Általánosságban:***

 * Nem grafikailag és matematikailag kell toppon legyen
 * Objektumorientáltság a lényeg
 * Bővíthetőség
 * Koordinátákat nem kell belerakni
 * Mezők
 * Út ismeri a szomszédait, de a helyét nem
 * instance_of NE LEGYEN! Tehát: csapda nem tudja, hogy ki lép bele
 * Visitor-acceptor pattern
 * Ne legyen "Isten" objektum
 * Játékmotor lehet, de az interakciót nem ő vezérli

**Visitor-acceptor pattern**

 * Interakciót kezdeményező és ezeket befogadó objektumok

**Szekvenciadiagramok**

 * Inicializálás (hogy pakolom fel a szereplőket stb)
 * Lépés
 * Csapdával interakció
 * Lövedékkel interakció
 * Célba érés
 * Lépések vezérlése
 * Torony, csapda létrehozása
 * Életerő vesztése
 * Objektum meghalása, átalakulása mannává stb.
 * Egy kör történései
 * és a többi...

 
***Saját cuccok***

 * ** Torony: **
 
  * Ismeri a hatókörében lévő mezőket, feliratkozik az "onEntered" eseményre

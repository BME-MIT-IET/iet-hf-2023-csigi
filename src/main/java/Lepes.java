/**
 * Ez a normál lépést megvalósító osztály. Célja, hogy megmondja, hogy a játékos hova léphet.
 *
 * @author Rádai Ronald
 */
public class Lepes {

    /**
     * Visszaadja az adott lépés prioritását.
     *
     * @return Prioritás
     */
    public int getPriority() {
        return 10;
    }

    /**
     * Visszadja a to attributumot. (Sima egyszerű lépés megvalósítása)
     *
     * @param to i. mező, ahova a játékos szeretne lépni
     * @param n  a jelenlegi mező szomszédjainak a számossága
     * @return A kiválasztott lépés (to értéke)
     */
    public int lepes(int to, int n) {
        if (to >= 0 && to < n)
            return to;
        return -1;
    }
}

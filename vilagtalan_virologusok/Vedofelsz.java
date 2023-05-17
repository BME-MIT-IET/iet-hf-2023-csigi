package vilagtalan_virologusok;/*
* Description
*
*  @author
*/

/** A védőfelszerelések absztrakt ősosztálya. */
public abstract class Vedofelsz extends Hatas {
    /** az a virológus, akinek a tulajdonában van ez a védőfelszerelés */
    protected Virologus tulaj;

    /**
     * Tulajdonos hozzáadása
     * @param v a virológus aki a tulaj lesz
     * @author safar
     * */
    public void begin(Virologus v) {
        tulaj = v;
    }

    public abstract String getName();

}

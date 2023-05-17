package vilagtalan_virologusok;

import java.util.Random;


/**
 * Ez egy olyan Lepes megvalósítás, amely a medvével fertőzött állapotbam
 * való lépést implementálja. (választ egy random szomszédos mezőt) Vitustánc < Priorítása < Bena
 *
 *
 * @author Rádai Ronald
 */

public class Medvetancolo extends Vitustancolo
{
    /**
     * Visszaadja az adott lépés prioritását.
     * @return Prioritás
     */
    @Override
    public int getPriority() { return 25; }
}

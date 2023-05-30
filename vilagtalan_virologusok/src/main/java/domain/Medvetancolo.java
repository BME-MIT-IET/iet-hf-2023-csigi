package domain;


/**
 * Ez egy olyan domain.Lepes megvalósítás, amely a medvével fertőzött állapotbam
 * való lépést implementálja. (választ egy random szomszédos mezőt) Vitustánc < Priorítása < domain.Bena
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

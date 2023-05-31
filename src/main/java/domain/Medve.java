package domain;

public class Medve extends Agens
{

    /**
     * A megpéldányosított Medve_lepes referenciája
     */
    private final Medvetancolo medve_lepes;

    Medve()
    {
        medve_lepes = new Medvetancolo();
    }


    /**
     * Hozzáad a paraméterben kapott virológus lépésmanageréhez egy vitustáncoló lépést
     * @param v Ki az a domain.Virologus aki medve lesz
     */
    public void begin(Virologus v)
    {
        if (!elhasznalt) {
            elhasznalt = true;
            virologus = v;
            v.addLepes(medve_lepes);
        }
    }

    /** Klónozza az ágenst*/
    public Agens duplicate()
    {
        return new Medve();
    }

    /**
     * Visszaadja az ágens nevét
     * @return név
     */
    @Override
    public String getName() {
        return "medve";
    }
}

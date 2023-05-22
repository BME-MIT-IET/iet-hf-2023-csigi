public class Medve extends Agens
{

    /**
     * A megpéldányosított Medve_lepes referenciája
     */
    private final Medvetancolo medve;

    Medve()
    {
        medve = new Medvetancolo();
    }


    /**
     * Hozzáad a paraméterben kapott virológus lépésmanageréhez egy vitustáncoló lépést
     * @param v Ki az a Virologus aki medve lesz
     */
    public void begin(Virologus v)
    {
        if (!elhasznalt) {
            elhasznalt = true;
            virologus = v;
            v.addLepes(medve);
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

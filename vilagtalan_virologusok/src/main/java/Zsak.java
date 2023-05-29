/**
* Olyan védőfelszerelést jelent, amely megnöveli a virológus anyaggyűjtő képességét a másfélszeresére.
*
*  @author Vesztergombi
*/
public class Zsak extends Vedofelsz {

    /**
     * Tulajdonossal ellátott paraméterű konstruktor
     * @param v leendő tulajdonos
     * @author Vesztergombi
     */
    public Zsak(Virologus v){
        tulaj = v;
        v.addVedofelsz(this);
    }

    /**
     * Paraméter nélküli konstruktor
     */
    public Zsak(){
    }

    /**
     * megnöveli a virológus anyaggyűjtőképességét, beállítja a tulajdonost
     * @param v a virológus aki a tulaj lesz
     */
    public void begin(Virologus v){
        v.getAnyag().maxNovel();
        tulaj = v;
    }

    @Override
    public String getName() {
        return "zsak";
    }

    /**
     * Visszaállítja a tulajdonosa anyaggyűjtőképességét
     */
    @Override
    public void end(){
        tulaj.getAnyag().maxCsokkent();
    }

}

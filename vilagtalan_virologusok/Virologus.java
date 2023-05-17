package vilagtalan_virologusok;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A játékos által irányítható karakter. Feladata, hogy mezőről mezőre lépjen,
 * genetikai kódokat tanuljon meg a játék megnyeréséhez, védőfelszereléseket
 * szedjen fel, ágenseket alkalmazzon magára vagy más virológusokra, kiraboljon
 * más virológusokat.
 */
public class Virologus implements Leptetheto {
    /** A virológus lépésmanagere */
    private final Lepesmgr lepesmgr = new Lepesmgr(this);
    /** A központi Jatek (manager) objektum */
    private static Jatek jatek;
    /** Él-e a virológus */
    private boolean elo = true;
    /** Lépett-e már a virológus ebben a körben. */
    private boolean lepett = false;
    /**
     * Az a mező ahol jelenleg áll a virológus
     */
    private Mezo mostMezo;
    /**
     * A virológusra ható ágensek
     */
    private ArrayList<Agens> agensek = new ArrayList<Agens>();
    /**
     * A virológusnál lévő védőfelszerelések
     */
    private ArrayList<Vedofelsz> vedofelszek = new ArrayList<Vedofelsz>();
    /**
     * A virológus anyagát kezelő osztály
     */
    private Anyag anyag = new Anyag();
    /**
     * A virológus által ismert genetikai kódok
     */
    private ArrayList<Agens> ismertKodok = new ArrayList<>();
    /**
     * A virológus alatt lévő mező szomszédjai
     */
    private ArrayList<Mezo> szomszedok = new ArrayList<>();

    public void setJatek(Jatek j){
        jatek = j;
    }

    /**
     * sima konstruktor
     */
    public Virologus() { }

    /**
     * Konstruktor, egyből a megadott mezőre teszi a virológust
     * @param m A mező ahová tenni szeretnénk
     * @author sisak
     */
    public Virologus(Mezo m){
        mostMezo = m;
        szomszedok = mostMezo.ralep(this);
    }

    /**
     * konstruktor rablos es leptetos tesztekhez
     *
     * @author safar
     */
    public Virologus(boolean bena, ArrayList<Vedofelsz> vedo, ArrayList<Agens> agns) {
        if (bena) addLepes(new Bena());
        vedofelszek = vedo;
        agensek = agns;
    }

    /**
     * Konstruktor a felejtős tesztesethez
     * @param ismert_agensek Ezeket az Ágenseket fogja tudni fejből a Virologus
     * @author Ákos
     */
    public Virologus(ArrayList<Agens> ismert_agensek)
    {
        ismertKodok = ismert_agensek;
    }

    /**
     * Megtámadtak, ennek a lekezelését hajtja végre a függvény
     *
     * @param tamado a virológus, aki megtámadott
     * @return a másik által elrabolt védőfelszerelések listája
     * @author safar
     */
    public ArrayList<Vedofelsz> megtamad(Virologus tamado) {
        if (lepesmgr.bena()) {
            ArrayList<Vedofelsz> vfsz_copy = new ArrayList<Vedofelsz>();
            vfsz_copy.addAll(vedofelszek);

            for (int i = vedofelszek.size() - 1; i >= 0; i--) {
                vedofelszek.get(i).end();
                vedofelszek.remove(i);
            }

            anyag.kirabolnak(tamado);
            return vfsz_copy;
        } else {
            return null;
        }
    }

    /**
     * Egy másik virológus megtámadását kezdeményezzük
     *
     * @param v a virológus akit megtámadjunk
     * @author safar
     */
    public void tamaddMeg(Virologus v) {
        ArrayList<Vedofelsz> vfsz = null;
        if (!this.isBena()) {
            vfsz = v.megtamad(this);
        }

        if (vfsz != null) {
            for (Vedofelsz fsz : vfsz) {
                fsz.begin(this);
            }
            vedofelszek.addAll(vfsz);
        }
    }

    /**
     * Kód letapogatását kezdeményező függvény
     * @return sikerült-e letapogatni
     * @author sisak
     */
    public boolean tapogasdLe() {
        Agens agns = null;
        if (!this.isBena()) {
            agns = mostMezo.letapogat();
        }
        if (agns != null && !jatek.containsAgn(ismertKodok, agns)) {
            ismertKodok.add(agns);
            return true;
        }
        return false;
    }

    /**
     * A védőfelszerelés aktuális mezőről való felvételét kezdeményező függvény
     *
     * @author sisak
     */
    public boolean veddFelVedofelsz() {
        Vedofelsz vdflsz = null;
        if (!this.isBena()) {
            vdflsz = mostMezo.felveszVedofelsz();
            addVedofelsz(vdflsz);
        }
        return vdflsz != null;
    }

    /**
     * Védőfelszerelés hozzáadása az eddigiekhez
     *
     * @param vdflsz A hozzáadni kívánt védőfelszerelés
     * @author Vesztergombi
     */
    public void addVedofelsz(Vedofelsz vdflsz) {
        if (vdflsz != null && vedofelszek.size() < 3) {
            vedofelszek.add(vdflsz);
            vdflsz.begin(this);
        }
    }

    /**
     * Visszater a virologusnal levo vedofelszek listajaval
     * @return
     * @author sisak
     */
    public ArrayList<Vedofelsz> getVedofelszList(){return vedofelszek;}

    /**
     * Az anyag felvételét kezdeményező függvény
     *
     * @author sisak
     */
    public void addAnyag() {
        if (!this.isBena()) {
            mostMezo.felveszAnyag(this);
        }
    }


    /**
     * @param v Ki próbál megkenni minket?
     * @param a Mivel próbálnak megkenni minket?
     * @return Kivédtük-e a kenést, megakadályozzuk-e hogy ránk kenjék?
     * @author Ákos
     */
    public boolean megken(Virologus v, Agens a) {
        //Itt ellenőrzi, hogy van-e olyan cucca ami megakadályozza..
        boolean forbids = false;

        var hatasok = new ArrayList<Hatas>();
        hatasok.addAll(agensek);
        hatasok.addAll(vedofelszek);

        for (Hatas h : hatasok) {
                if (h.megkentek(a, v)) {
                    forbids = true;
                    break;
            }
        }

        if (!forbids) {
            //agensek.add(a);       // átírva az ágens kódjába
            a.begin(this);
        }

        return forbids;
    }

    /**
     * Megken egy virológust egy ágenssel
     * @param v A megkenendő virológus
     * @param a A kenendő ágens
     * @return sikerült-e megkenni a másikat (ha nem, lehet pl. kesztyű, köpeny, nincs elég anyag)
     */
    public boolean kendMeg(Virologus v, Agens a) {
        int vAgensSize = 0;
        if (!this.isBena()) {
            int price = a.getAr();
            vAgensSize = v.getAgensek().size();
            if (anyag.csokkent(price)) {
                Agens uj_agens = a.duplicate();
                v.megken(this, uj_agens);
            }
        }
        return v.getAgensek().size() > vAgensSize;
    }

    /**
     * A virológus léptethető tulajdonaira meghívja a tick függvényt
     *
     * @author safar
     */
    public void tick() {
        var hatasok = new ArrayList<Hatas>();
        hatasok.addAll(agensek);
        hatasok.addAll(vedofelszek);

        for (Hatas h : hatasok) {
            h.tick();
        }
        lepett = false;
    }


    /**
     * Elfelejteti a Agenst a virologussal ha azt már megtanulta
     *
     * //@param a az az Agens amit a Virologusnak el kell felejtenie
     */
    public void torolAgensek() {
        ismertKodok = new ArrayList<>();
    }

    /**
     * Végrehajtja a lépést az i. irányba.
     *
     * @param i Amerre lép a virológus
     * @return sikerült-e a lépés
     * @author Rádai
     */
    public boolean lep(int i) {
        if(lepett)
            return false;
        lepett = true;
        int lep = lepesmgr.Lep(i, szomszedok.size());
        boolean medve = lepesmgr.medve();
        if (lep != -1) {
            if (medve)
                vegigken();
            mostMezo.ellep(this);
            mostMezo = szomszedok.get(lep);
            szomszedok = mostMezo.ralep(this);
            if (medve)
            {
                vegigken();
                mostMezo.Destroy();
            }
        }
        return true;
    }


    /**
     * Visszaadja a már megismert genetikai kódokat.
     *
     * @return A már megtanult genetikai kódok ArrayList-ben
     */
    public ArrayList<Agens> getIsmertKodok() {
        return ismertKodok;
    }

    /**
     * @return A virológus anyagát kezelő osztály
     */
    public Anyag getAnyag() {
        return anyag;
    }

    /**
     * Hozzáadunk egy lépésfajtát a virológus által használható lépések közé
     *
     * @param l A hozzáadni kívánt lépés
     */
    public void addLepes(Lepes l) {
        lepesmgr.addLepes(l);
    }

    /**
     * Elveszünk egy lépésfajtát a virológus által használható lépések közül
     *
     * @param l Az eltávolítani kívánt lépés
     */
    public void removeLepes(Lepes l) {
        lepesmgr.removeLepes(l);
    }

    /**
     * A megadott védőfelszerelést kiveszi a virológus felszerelései közül
     *
     * @author safar
     */
    public void removeVedofelsz(Vedofelsz v) {
        if (vedofelszek.contains(v) /*&& v != null*/) {
            v.end();
            vedofelszek.remove(v);
        }
    }

    /**
     * Végigkeni az adott mezőn tartózkodó összes virológust Medve ágenssel
     *
     * @author Rádai
     */
    public void vegigken()
    {
        ArrayList<Virologus> x = mostMezo.ittLevok();
        for(int i = 0; i < x.size(); ++i)
            x.get(i).megken(null, new Medve());
    }

    /**
     * Meghívódik, amikor a virológust megölik.
     * Szól a központi Jateknak (managernek), hogy többször ne kerüljön sorra.
     */
    public void meghal(){
        elo = false;
        jatek.virologusMeghal(this);

    }

    /**
     *
     * @return Él-e a virológus
     */
    public boolean isElo() {
        return elo;
    }

    /**
     * Visszaadja, hogy a virológus medve-e?
     * @return Medve-e a virológus
     */
    public boolean isMedve() {
        return lepesmgr.medve();
    }

    /**
     * Visszaadja, hogy béna-e a virológus
     * @return Béna-e a virológus
     */
    public boolean isBena() {
        return lepesmgr.bena();
    }

    /**
     * Visszaadja a mezot amin eppen all a virologus
     * @return
     * @author sisak
     */
    public Mezo getMostMezo(){return mostMezo;}

    /**
     * Visszaadja a virologusra hato agensek listajat
     * @return
     * @author sisak
     */
    public ArrayList<Agens> getAgensek(){return agensek;}


}

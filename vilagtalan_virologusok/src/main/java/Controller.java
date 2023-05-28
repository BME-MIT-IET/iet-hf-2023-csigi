import javax.swing.*;
import java.util.*;


/**
 * Ez adja a kapcsolatot a megjelenítés és a modell között, ezen keresztül tudnak kommunikálni.
 * Ez az osztály vezérli a modellt a megjelenítésbeli kérések alapján.
 */
public class Controller {
    /**
     * A jatek grafikus felulete
     */
    private static MainFrame frame;

    /**
     * A jatek modelljet reprezentalo objektum
     */
    private static Jatek jatek;

    /**Bena lepes prioritasa*/
    public static final int BENALEPES_PRIORITY = 30;
    /**Medve lepes prioritasa*/
    public static final int MEDVELEPES_PRIORITY = 25;
    /**Veletlensyeruseget vezerlo valtozo*/
    public static boolean random = true;
    /**Elindult e a jatek*/
    private static boolean started = false; //TODO: Új játéknál állítsuk át true-ra

    /**
     * String konstansok a különböző hibaüzenetekre
     * @author Benczik
     */
    private static final String VIROLOGIST_DOES_NOT_EXIST = "Failed: virologist doesn't exist";
    private static final String GAME_HAS_NOT_STARTED = "Failed: the game hasn't started yet";
    private static final String NOT_ENOUGH_ARGUMENTS = "Failed: not enough arguments";
    private static final String ID_IS_NAN_ARGUMENT = "Failed: bad arguments (id is NaN)";
    private static final String NOT_EXISTING_VIROLOGIST = "Failed: virologist by this id doesn't exist";

    /**Belepesi pont*/
    public static void main(String[] args){
        Controller c = new Controller();
        jatek = new Jatek(frame);
        newgame(3);
        frame = new MainFrame();
    }

    /**
     * Kovetkezo jatekosra valto parancs fuggvenye
     */
    public static void nextPlayer(){
        started = true;
        jatek.kovJatekos();
            //jatek.startGame(10, 5);
        if(jatek.getVirologus(jatek.getActiveVirologusID()).isBena())
        {
            System.out.println("next player is " + jatek.getActiveVirologusID() + ", who is paralyzed");
            return;
        }
        System.out.println("next player is " + jatek.getActiveVirologusID() + ", neighbours:");
        for (Mezo m : jatek.getVirologus(jatek.getActiveVirologusID()).getMostMezo().getSzomszedok()){
            System.out.println("\t" + jatek.getMezoId(m));
        }
    }

    /**
     * Szomszedos mezok megismeresere szolgalo mezo
     * A view-hoz kell
     * @return visszaadja a szomszédos mezők azonosítóit tartalmazó listát
     */
    public static ArrayList<String> listNghMezo(){
        try{
            ArrayList<Mezo> mezok = jatek.getVirologus(jatek.getActiveVirologusID()).getMostMezo().getSzomszedok();
            ArrayList<String> mezoIdk = new ArrayList<>();
            for (Mezo m : mezok){
                if (jatek.getMezoId(m) != null)
                    mezoIdk.add(jatek.getMezoId(m));
            }
            return mezoIdk;
        } catch (Exception e){
            System.out.println("hiba történt");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Aktiv {@link Virologus} altal ismert kodokat visszaado fuggveny
     * @return az ismert kódok nevei (típusai)
     */
    public static ArrayList<String> listcodes(){
        Virologus v = jatek.getVirologus(jatek.getActiveVirologusID());
        if(v == null){System.out.println(VIROLOGIST_DOES_NOT_EXIST); return null;}

        ArrayList<String> kodok = new ArrayList<>();
        for(Agens a: v.getIsmertKodok()){
            kodok.add(a.getName());
        }
        return kodok;
    }

    /**
     * Visszaadja hogy az adott {@link Virologus} medve e
     * @param vir_id A vizsgalt {@link Virologus}
     * @return true ha a vir_id id-jű virológus medvevírussal fertőzött, false egyébként
     */
    public static boolean isMedve(String vir_id){

        Virologus v = jatek.getVirologus(vir_id);
        if(v == null){System.out.println(VIROLOGIST_DOES_NOT_EXIST); return false;}        // ez jó, hogy ilyenkor false-t ad vissza?

        return v.isMedve();
    }

    /**
     * Visszaadja az adott {@link Virologus} anyagmennyiseget
     * @param vir_id a vizsgalt {@link Virologus}
     * @return mennyi anyaga van a vir_id id-jű virológusnak
     */
    public static int showmatter(String vir_id){
        Virologus v = jatek.getVirologus(vir_id);
        if(v == null){System.out.println(VIROLOGIST_DOES_NOT_EXIST); return -1;}
        return v.getAnyag().mennyi();
    }

    /**
     * Vedofelsz eldobasara szolgala parancs fuggvenye. Megnézi van-e olyan védőfelszerelése az aktív virológusnak, mint a paraméterben kapott
     * string meghatároz, ha van, eldobja az olyan védőfelszerelését, ha nincs, hibaüzenettel tér vissza
     * @param args 1 milyen típusú védőfelszerelést szeretnénk eldobni
     * @return üres string ha sikerült eldobnunk, egyébként a hibaüzenetet tartalmazó string
     */
    public static String dropequipment(String[] args){
        System.out.print("dropping ");

        if(args == null || args.length < 2){System.out.println("Failed: Not enough arguments"); return "Failed: Not enough arguments";}
        System.out.print(args[1]+":");
        Vedofelsz type = null;

        switch(args[1]){
            case("kesztyu"):
                type = new Kesztyu();
                break;
            case("kopeny"):
                type = new Kopeny();
                break;
            case("zsak"):
                type = new Zsak();
                break;
            case("balta"):
                type = new Balta();
                break;
            default:
                System.out.println("Failed: Vedofelsz of type "+args[1]+" doesn't exist");
                return "Failed: Vedofelsz of type "+args[1]+" doesn't exist";
        }

        String vir_id = jatek.getActiveVirologusID();
        if(vir_id == null){System.out.println(GAME_HAS_NOT_STARTED); return GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(vir_id);
        ArrayList<Vedofelsz> vdfsz = v.getVedofelszList();

        for(Vedofelsz a: vdfsz){
            if(a.getClass() == type.getClass()){
                v.removeVedofelsz(a);
                System.out.println("Successful");
                return "";
            }
        }
        System.out.println("Failed: Virologus doens't have this type of Vedofelsz");
        return "Failed: Virologus doens't have this type of Vedofelsz";
    }

    /**
     * Elindít egy új játékot, létrehozza hozzá a pályát, illetve hozzáadja a játékosokat.
     * A pálya mérete a játékosok számától függ, amennyiben random módban van a játék.
     * Amennyiben determinisztikus módban vagyunk, a pályát mi kell létrehozzuk.
     * @param virCount 0. eleme a játékosok száma
     */
    public static String newgame(int virCount){
        if (!random || jatek == null){
            return "Failed";
        }
        try {
            jatek.startGame(virCount*2, virCount);
            started = true;
        } catch (Exception e) {
            e.printStackTrace();
            return "new map: Failed";
        }
        return "";
    }

    /**
     * Aktiv {@link Virologus}t mozgato parancs fuggvenye
     * @param args {"", következőMezőIDje}
     */
    public static String move(String[] args){
        if(args == null || args.length < 2){ return NOT_ENOUGH_ARGUMENTS; }

        String neigh_id = null;
        try{neigh_id = args[1];}
        catch(NumberFormatException e){ return ID_IS_NAN_ARGUMENT;}
        System.out.print(neigh_id+": ");

        String vir_id = jatek.getActiveVirologusID();
        if(vir_id== null){ return  GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(vir_id);

            try {
                Mezo m = jatek.getMezo(neigh_id);
                int index = v.getMostMezo().getSzomszedok().indexOf(m);
                if (index == -1){
                    return "Failed: nincs ilyen szomszéd";
                }
                boolean sik = v.lep(index);
                if(sik)
                {
                    if(v.isBena())
                        return "Failed Bena active";
                    else if(v.isMedve())
                        return "Failed medve active, moved to " + jatek.getMezoId(v.getMostMezo());
                    else if (jatek.getMezoId(v.getMostMezo()).compareTo(neigh_id) == 0)
                        return "";
                    else
                        return "Failed vitus active, moved to " + jatek.getMezoId(v.getMostMezo());
                }
                else
                    return "Failed: a körben már léptél";

            } catch (Exception e) {
                return "Failed: ismeretlen";
            }
    }

    /**
     * {@link Agens}t keno parancs fuggvenye
     * @param vir_id A {@link Virologus} akire kenni szeretnenk
     * @param agens Az {@link Agens} amit kenni szeretnenk
     * @return üres string ha hibátlanul sikerült kennünk, egyébként a hibaüzenetet tartalmazó string
     */
    public static String useAgn(String vir_id, String agens){
        String activeID = jatek.getActiveVirologusID();
        if(activeID == null){return GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(activeID);

        System.out.print(vir_id+": ");
        Virologus v2 = jatek.getVirologus(vir_id);
        if(v2 == null){return "Failed: Virologist by this id doesn't exist";}

        //Ellenorzi, hogy letezik e
        Agens agns = null;
        for(Agens ag : v.getIsmertKodok()){
            if(Objects.equals(ag.getName(), agens)){
                agns = ag; break;       // duplicate nem kell
            }
        }
        if(agns == null){return "Failed: Virologist doesn't know the code for this agent";}

        if (v.kendMeg(v2, agns)){
            return "";
        } else{
            return "failed";
        }
    }

    /**
     * Tamado parancs fuggvenye
     * Ellenőrzi hogy egy mezőn áll-e azzal a virológussal akit meg akar támadni, de azok nem mi magunk vagyunk. Ellenőrzi, hogy az a virológus béna-e
     * Ha ezek teljesülnek, elveszi minden anyagát, az aktív virológus anyagát megnöveli az elvett mennyiséggel, maximalizállva a maximum anyagmennyiségére
     * majd elveszi a megtámadott virológus védőfelszereléseit, amennyit tud, magának ad, a többit a mezőn hagyja
     * @param args az 1-es indexű helyen a megtámadandó virológus id-ja
     * @return üres string ha nincs hiba, a hibaüzenet ha van hiba
     */
    public static String attack(String[] args){
        System.out.print("attacking ");
        if(args == null|| args.length< 2){return NOT_ENOUGH_ARGUMENTS;}
        String vir_id = jatek.getActiveVirologusID();
        if(vir_id == null){System.out.println(GAME_HAS_NOT_STARTED); return GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(vir_id);

        try {
            vir_id = args[1];
        } catch (NumberFormatException e) {
            System.out.println(ID_IS_NAN_ARGUMENT);
            return ID_IS_NAN_ARGUMENT;
        }
        System.out.print(vir_id+":");
        Virologus v2 = jatek.getVirologus(vir_id);
        if(v2 == null){System.out.println(NOT_EXISTING_VIROLOGIST); return "NOT_EXISTING_VIROLOGIST;}
        if(v == v2){return "Failed: Can't attack self";};

        //Ellenorzi, hogy egy mezon allnak e
        boolean sameMezo = false;
        for(Virologus vi: v.getMostMezo().ittLevok()){
            if(vi == v2){
                sameMezo = true; break;
            }
        }
        if(!sameMezo){System.out.println("Failed: Virologists have to be standing on the same field to attack"); return NOT_EXISTING_VIROLOGIST;}

        v.tamaddMeg(v2);
        if(v2.isBena())
            System.out.println("successful");
        else{
            System.out.println("failed reason: celpont nem benult");
            return "failed reason: celpont nem benult";
        }
        return "";
    }

    /**
     * Baltat hasznalo parancs fuggvenye. Magadat és veled nem egy mezőn álló virológust nem tudsz lebaltázni
     * @param vir_id annak a virológusnak az id-ja akit megpróbálunk baltázni
     * @return üres string ha nincs hiba, a hibaüzenettet tartalmezó string ha van
     * @author Benczik
     */
    public static String useAxe(String vir_id){
        String activeId = jatek.getActiveVirologusID();
        if(activeId == null){return GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(activeId);

        System.out.print(vir_id+":");
        Virologus v2 = jatek.getVirologus(vir_id);
        if(v2 == null){ return NOT_EXISTING_VIROLOGIST;}

        //Ellenorzi, hogy egy mezon allnak e
        boolean sameMezo = false;
        for(Virologus vi: v.getMostMezo().ittLevok()){
            if(vi == v2){
                sameMezo = true; break;
            }
        }
        if(!sameMezo){return "Failed: Virologists have to be standing on the same field to attack";}

        if(!v2.isMedve()) { return "failed target not a bear";}

        //Ellenorzi, hogy van e nem csorba balta
        for(Vedofelsz vdf: v.getVedofelszList()){
            if(vdf instanceof Balta && !((Balta) vdf).isCsorba()){
                ((Balta)vdf).use(v2);
                return "";

            }
        }
        return "Failed: virologist doesn't have any useable axes";
    }

    /**
     * Letapogatast vegzo parancs fugggvenye. Meghívja az aktív virológus tapogasdLe() függvényét, annak sikerességétől függően tér vissza
     * üres stringgel vagy hibaüzenettel
     * @return üres string ha nincs hiba, a hibaüzenet ha van
     * @author Benczik
     */
    public static String getcode(){
        String vir_id = jatek.getActiveVirologusID();
        if(vir_id == null){ return GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(vir_id);
        boolean success = v.tapogasdLe();
        if(!success)
            return "Failed";
        if (jatek.nyerte_e(jatek.getVirologus(vir_id).getIsmertKodok().size())) {
            return "Az " + vir_id + ". azonosítójú játékos nyert!!";
        }
        return "";
    }

    /**
     * Anyagfelvetelt vegzo parancs fuggvenye
     * @return kiírandó visszajelzés
     */
    public static String getMatter(){
        //System.out.print("getting matter: ");
        String vir_id = jatek.getActiveVirologusID();
        if(vir_id == null){return GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(vir_id);
        int old_amount = v.getAnyag().mennyi();
        v.addAnyag();
        int new_amount = v.getAnyag().mennyi();
        if(old_amount == new_amount){
            return "Nem tudsz több anyagot fölvenni";
        }
        else{ return "";}
    }

    /**
     * {@link Vedofelsz}et felvevo parancs fuggvenye
     */
    public static String getEquipment(){
        System.out.print("getting equipment: ");
        String vir_id = jatek.getActiveVirologusID();
        if(vir_id == null){return GAME_HAS_NOT_STARTED;}
        Virologus v = jatek.getVirologus(vir_id);
        if (v.getVedofelszList().size() >= 3){
            return "nincs eleg hely felszerelesnek";
        } else {
            if(v.veddFelVedofelsz())
                return "";
            else
                return "Nem lehet fölvenni a védőfelszerelést";
        }
    }

    /**
     * Az akitv {@link Virologus}nal levo {@link Vedofelsz}eket listazo parancs fuggvenye
     * @return a soron lévő virológusnál lévő védőfelszerelések neveinek (típusainak) tömbje
     */
    public static ArrayList<String> listeq(){
        Virologus v;
        if((v= jatek.getVirologus(jatek.getActiveVirologusID())) == null){System.out.println("Failed: Virologist by this id doesn't exist"); return null;}
        ArrayList<Vedofelsz> vdfsz = v.getVedofelszList();
        ArrayList<String> vedofelszNevek = new ArrayList<>();
        for(int i = 0; i < vdfsz.size(); ++i){
            vedofelszNevek.add(vdfsz.get(i).getName());
        }
        return vedofelszNevek;

    }

    /**
     * Az adott {@link Virologus}ra hato {@link Agens}eket listazo parancs fuggvenye
     * @param args {"", String virologusID}
     */
    public static void listagn(String[] args){
        System.out.print("current agents on ");
        if(args == null|| args.length < 2){System.out.println(NOT_ENOUGH_ARGUMENTS); return;}
        System.out.println(args[1]+":");

        String vir_id = null;
        try{ vir_id = args[1];}
        catch(NumberFormatException e){System.out.println(ID_IS_NAN_ARGUMENT); return;}

        Virologus v = jatek.getVirologus(vir_id);
        if(v == null){System.out.println(NOT_EXISTING_VIROLOGIST); return;}
        ArrayList<Agens> agns = v.getAgensek();
        for(Agens ag: agns){
            System.out.println("\t"+ag.getName());
        }
    }

    /**
     * A szomszédokat listázza
     * @return azonos mezőn tartózkodó virológusok azonosítóinak listája
     */
    public static ArrayList<String> listNgh(){
        String vir_id = jatek.getActiveVirologusID();
        if(vir_id == null){
            JOptionPane.showMessageDialog(jatek.frame,
                    GAME_HAS_NOT_STARTED);
            return null;
        }

        Virologus v = jatek.getVirologus(vir_id);
        Mezo m = v.getMostMezo();
        ArrayList<Virologus> virs = m.ittLevok();
        ArrayList<String> virIds = new ArrayList<>();
        for (Virologus vir : virs){
            if (jatek.getVirologusID(vir) != null)
                virIds.add(jatek.getVirologusID(vir));
        }
        return virIds;
    }

    /**
     * Veletlenszeruseget kapcsolo parancs fuggvenye
     * @param args {"", boolean randomLegyenE}
     */
    public static void isrand(String[] args){
        System.out.print("randomness set to ");
        if(args == null || args.length < 2){System.out.println(NOT_ENOUGH_ARGUMENTS); return;}
        boolean setTo = Boolean.parseBoolean(args[1]);
        random = setTo;
        System.out.println(setTo ? "on" : "off");
    }

    /**
     * Visszater a Virologus ID-jevel
     * @param v A vizsgalt virologus
     * @return v ID-je
     */
    public static String getVirologusId(Virologus v){
        return jatek.getVirologusID(v);
    }

    /**
     * Visszater az eppen aktiv Virologus ID-jevel
     * @return aktív Virológus ID-je
     */
    public static String getActiveVirologusId(){
        return jatek.getActiveVirologusID();
    }

    /**
     * Frissíti a MainFrame adatait
     */
    public static void updateMainFrame(){
        frame.update();
    }

    /**
     * Visszaadja a soron lévő virológus mezőjének azonosítóját
     * @return aktuális mező azonosítója
     */
    public static String getMostMezoId(){
        return jatek.getMezoId(jatek.getVirologus(jatek.getActiveVirologusID()).getMostMezo());
    }

    /**
     * Visszaadja a soron lévő játékos mezőjánek típúsát szövegként
     * @return aktuális mező típusa szövegként
     */
    public static String getMostMezoType(){
        return jatek.getVirologus(jatek.getActiveVirologusID()).getMostMezo().getTypeName();
    }



}
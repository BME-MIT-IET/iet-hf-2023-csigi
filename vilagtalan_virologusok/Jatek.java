package vilagtalan_virologusok;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

/**
 * Vezeti a játékot, Létrehozza a játékteret és a virológusokat.
 * */
public class Jatek {
	/** A virológusokat tartalmazó lista, ennek sorrendjében hívja a játékosokat */
	//private Map<Integer, Virologus> virologusok;
	private Map<String, Virologus> virologusok;

	/** Milyen genetikai kódok vannak a pályán */
	private ArrayList<Agens> kodok;

	/**
	 * Jelenleg soron lévő játékos indexe
	 * */
	private int activeIndex = -1;
	/** A pálya összes mezőjét tartalmazó lista */
	//private Map<Integer, Mezo> palya;
	private Map<String, Mezo> palya;

	/** A játékot megjelenítő ablak */
	static public MainFrame frame;
	/** Random generáló objektum a pályageneráláshoz*/
	private Random rand;


	/**
	 * Játék konstruktora
	 * Készít egy mapot a virológusoknak és a pályának és egy list-et a kódoknak
	 * @param Frame - A megjelenítő ablakot reprezentáló Frame referenciája
	 * */
	public Jatek(MainFrame Frame){
		virologusok = new HashMap<>();
		palya = new HashMap<>();
		kodok = new ArrayList<>();
		frame = Frame;
		rand = new Random();
	}

	/**
	 * Visszaadja a megfelelő id-jű mezőt
	 * @param i id
	 * @return  a kért mező referenciája
	 * @author Ronald, Ákos
	 */
	public Mezo getMezo(String i) { return palya.get(i);  }

	/**
	 * Visszaadja m mező id-jét.
	 * @param m a keresett mező
	 * @return  a kért mező id-je
	 * @author Ronald
	 */
	public String getMezoId(Mezo m)
	{
		for (Map.Entry<String,Mezo> e : palya.entrySet())
			if(e.getValue() == m)
				return e.getKey();
		return null;
	}

	/**
	 * A játéktér felépítése, virológusok létrehozása, minden készenlétbe állítása, a játék indítása.
	 * */
	public void startGame(int boardSize, int virologusCount) {
		palya.clear();
		virologusok.clear();
		kodok.clear();
		generateMezok(boardSize);
		boardSzomszed();
		virologusokElhelyez(virologusCount, this);
		kovJatekos();
	}

	/**
	 * Létrehoz paraméterben megadott számú mezőt, random típusúakat, random esetleges tartalommal.
	 * @param boardSize Mennyi mező jöjjön létre.
	 */
	public void generateMezok(int boardSize){
		int r1, r2;
		for (int i = 0; i < boardSize; ++i){
			r1 = rand.nextInt(4);
			switch (r1){
				case 0:
					palya.put(Integer.toString(i), new Mezo());
					break;
				case 1:
					r2 = rand.nextInt(4);
					switch (r2){
						case 0: Kopeny ko = new Kopeny(); palya.put(Integer.toString(i), new Ovohely(ko)); break;
						case 1: Balta b = new Balta(); palya.put(Integer.toString(i), new Ovohely(b)); break;
						case 2: Zsak zs = new Zsak(); palya.put(Integer.toString(i), new Ovohely(zs)); break;
						case 3: Kesztyu ke = new Kesztyu(); palya.put(Integer.toString(i), new Ovohely(ke)); break;
					}
					break;
				case 2:
					r2 = rand.nextInt(4);
					boolean r3 = rand.nextDouble() > 0.9; //isBear
					switch (r2){
						case 0: Vitustanc vi = new Vitustanc(); palya.put(Integer.toString(i), new Labor(vi, r3)); kodok.add(vi); break;
						case 1: Vedo ve = new Vedo(); palya.put(Integer.toString(i), new Labor(ve, r3)); kodok.add(ve);	 break;
						case 2: Benit be = new Benit(); palya.put(Integer.toString(i), new Labor(be, r3)); kodok.add(be); break;
						case 3: Felejt f = new Felejt(); palya.put(Integer.toString(i), new Labor(f, r3)); 	kodok.add(f); break;
					}
					break;
				case 3:
					palya.put(Integer.toString(i), new Raktar());
			}
		}

	}

	/**
	 * A generált mezőkhöz beállítja, hogy egyesek egymás szomszédai legyenek.
	 * Figyel arra, hogy a létrehozott szomszédsági gráf összefüggő legyen az összes mezőre nézve
	 */
	public void boardSzomszed(){
		int r;
		ArrayList<Mezo> szomszedok = new ArrayList<>();

		for (String key : palya.keySet()) {
			if (!key.equals("0")) {		// az azonosítóknak 0-tól kell kezdődniük és számoknak kell lenniük sorban!
				getMezo(key).set_szomszedok(new ArrayList<>());
				palya.get(key).getSzomszedok().add(palya.get(Integer.toString(Integer.parseInt(key)-1)));
				palya.get(Integer.toString(Integer.parseInt(key)-1)).getSzomszedok().add(palya.get(key));
			}
		}
		for (Mezo m : palya.values()){
			szomszedok = m.getSzomszedok();
			for (int i = 0; i < rand.nextInt(palya.size()-1); ++i){
				r = rand.nextInt(palya.size()-2);
				if (!szomszedok.contains(palya.get(Integer.toString(r))) && !m.equals(palya.get(Integer.toString(r)))) {
					szomszedok.add(palya.get(Integer.toString(r)));
					palya.get(Integer.toString(r)).getSzomszedok().add(m);
				}
			}
			m.set_szomszedok(szomszedok);
		}
	}

	/**
	 * Paraméterben megadott számú virológust generál és elhelyezi a pályán véletlenszerűen.
	 * @param virologusCount mennyi virológus legyen a pályán
	 */
	public void virologusokElhelyez(int virologusCount, Jatek jatek){
		int r;
		for (int i = 0; i < virologusCount; ++i){
			r = rand.nextInt(palya.size());
			Virologus v = new Virologus(palya.get(Integer.toString(r)));
			v.setJatek(jatek);
			virologusok.put(Integer.toString(i), v);
		}
	}
	
	/**
	 * Következő sorra kerülő játékost kiválasztja
	 * @author safar
	 * */
	public void kovJatekos() {
		activeIndex = (activeIndex + 1) % virologusok.size();
		ArrayList<Virologus> virologusList = new ArrayList<Virologus>(virologusok.values());
		virologusList.get(activeIndex).tick();
	}
	
	/** Lekérdezi a virológus, hogy az őáltala ismert kódok száma vajon megegyezik-e az összes kód számával.
	 * @return true, ha minden, a pályán létező kódot ismer a virológus, és false, ha nem ismer még mindet
	 * */
	public boolean nyerte_e(int i) {
		HashMap<String, String> map = new HashMap<>();
		for (Agens a : kodok){
			String nev = a.getName();
			map.put(nev, nev);
		}
		return i == map.size();
	}

	/**
	 * Ha meghal egy virológus, eltávolítjuk a virológusok listából, többé már nem kerül sorra.
	 * A halott virológus "teste" ott marad a pályán, lehet rá kenni ágenst stb.
	 * @param virologus amelyik virológus meghal
	 * @author Vesztergombi
	 */
	public void virologusMeghal(Virologus virologus){
		if (virologusok.containsValue(virologus)) {
			virologusok.values().remove(virologus);
		}
	}


	/**
	 * Hozzáad egy virológust a megadott id-vel a megadott mezőre. A newvir parancshoz szükséges.
	 * @param vir_id virológus azonosítója
	 * @param field_id mező azonosítója
	 * @param has_matter ha true, akkor max anyaga van, ha false, akkor nincs anyaga.
	 */
	public void addVirologus(String vir_id, String field_id, boolean has_matter){
		Virologus v = new Virologus(palya.get(field_id));
		virologusok.put(vir_id, v);
		if (!has_matter){
			Anyag a = v.getAnyag();
			a.csokkent(a.mennyi());
		}
	}


	/**
	 * Szomszédossá teszünk két mezőt.
	 * @param field_id Egyik mező
	 * @param neighbour_id Másik mező
	 */
	public void addFieldNeighbour(String field_id, String neighbour_id){
		Mezo m1 = palya.get(field_id);
		var szomszedok1 = m1.getSzomszedok();
		szomszedok1.add(palya.get(neighbour_id));
		Mezo m2 = palya.get(neighbour_id);
		var szomszedok2 = m2.getSzomszedok();
		szomszedok2.add(palya.get(field_id));

	}

	public enum FieldType{
		MEZO,
		OVOHELY,
		LABOR,
		RAKTAR
	}

	/**
	 * Eldönti két ágensről hogy ugyanolyan típusúak-e
	 * @param agn1 az egyik ágens
	 * @param agn2 a másik ágens
	 * @return true ha a két paraméter ugyanolyan típusú ágens, false egyébként
	 */
	public boolean compareAgn(Agens agn1, Agens agn2){
		return agn1.getName().equals( agn2.getName() );
	}

	public boolean containsAgn(ArrayList<Agens> agnList, Agens agn){
		boolean ret = false;
		for (Agens ag : agnList)
			if (compareAgn(ag, agn)) ret =true;
		return ret;
	}

	/**
	 * Hozzaad egy uj mezot a palyahoz
	 * @param field_id A mezo leendo azonositoja
	 * @param field_type A mezo tipusa (0: Mezo, 1: Ovohely, 2: Labor, 3: Raktar)
	 * @param equipment A mezon talalhato vedofelsz (csak ovohely)
	 * @param isBear Fertoz e medvevirussal (csak labor)
	 * @param code A mezon talalhato kod (csak labor)
	 */
	public void addField(String field_id, FieldType field_type, Vedofelsz equipment, boolean isBear, Agens code){
		switch (field_type){
			case MEZO:
				Mezo m = new Mezo();
				palya.put(field_id, m);
				if (code != null && !containsAgn(kodok, code))
					kodok.add(code);
				break;
			case OVOHELY:
				Ovohely ovo = new Ovohely(equipment);
				palya.put(field_id, ovo);
				if (code != null && !containsAgn(kodok, code))
					kodok.add(code);
				break;
			case LABOR:
				Labor l = new Labor(code, isBear);
				palya.put(field_id, l);
				if (code != null && !containsAgn(kodok, code))
					kodok.add(code);
				break;
			case RAKTAR:
				Raktar r = new Raktar();
				palya.put(field_id, r);
				if (code != null && !containsAgn(kodok, code))
					kodok.add(code);
				break;
		}
	}

	/**
	 * Torol egy mezot a palyarol
	 * @param field_id a torlendo mezo azonositoja
	 */
	public void removeField(Integer field_id){

	}

	/**
	 * Visszaadja az adott azonositoval rendelkezo virologust.
	 * @param vir_id A virologus id-ja
	 * @return Az adott id-jű virologus referenciaja
	 */
	public Virologus getVirologus(String vir_id){ return virologusok.get(vir_id);}

	/**
	 * Visszaadja a soron levo virologus id-jet, ha mar megy a jatek (egyebkent nullt)
	 * @return az aktív virológus id-ja stringként, ha már megy a játék, null ha még nem indult el
	 */
	public String getActiveVirologusID(){
		if (activeIndex == -1) return null;
		return new ArrayList<String>(virologusok.keySet()).get(activeIndex);
	}

	/**
	 * Visszaadja a parameterkent kapott virologus objektum azonositojat
	 * @param v a virológus, akinek keressük az id-ját
	 * @return A keresett virológus id-ja stringként
	 * @author sisak
	 */
	public String getVirologusID(Virologus v){
		for (Map.Entry<String,Virologus> e : virologusok.entrySet())
			if(e.getValue() == v)
				return e.getKey();
		return null;
	}
}

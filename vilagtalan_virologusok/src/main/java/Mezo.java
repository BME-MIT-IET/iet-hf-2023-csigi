import java.util.ArrayList;

/**
 * A játék mezőit reprezentáló osztály. Mezőkön vannak a virológusok, azonos mezőkön állva találkoznak.
 * A mezők szomszédosak egymással, így adják ki a játékteret.
 * */
public class Mezo {
	/** A mezőn álló virológusok */
	private ArrayList<Virologus> virologusok = new ArrayList<Virologus>();
	
	/** Szomszédos mezők */
	private ArrayList<Mezo> szomszedok = new ArrayList<Mezo>();

	/**
	 * Szomszédok listája
	 * @return A szomszédok listáját adja vissza
	 * @author Vesztergombi
	 */
	public ArrayList<Mezo> getSzomszedok() {
		return szomszedok;
	}

	/**
	 * Mezőn lévők listája
	 * @return Visszaadja a mezőn lévő virológusok listáját
	 * @author Vesztergombi
	 */
	public ArrayList<Virologus> ittLevok() {
		return virologusok;
	}

	/**
	 * Fölveszi a rajta lévő virológusok közé
	 * @param v A virológus, aki rálép
	 * @return A szomszédok listája
	 * @author Vesztergombi
	 */
	public ArrayList<Mezo> ralep(Virologus v) {
		virologusok.add(v);
		return getSzomszedok();
	}

	/**
	 * Szomszédok beállítása
	 * @param sz Szomszédok listája
	 */
	public void  set_szomszedok(ArrayList<Mezo> sz)
	{
		szomszedok = sz;
	}
	
	/**
	 * Akkor hívja meg a virológus, ha ellép a mezőről
	 * @param ki Az ellépő virológus
	 * */
	public void ellep(Virologus ki)
	{
		virologusok.remove(ki);
	}
	
	/**
	 * Megpróbálja letapogatni a genetikai kódot (nem sikerül)
	 * */
	public Agens letapogat() {
		return null;
	}
	
	/**
	 * Megpróbálja felvenni a tárolt anyagot (nem sikerül)
	 * */
	public void felveszAnyag(Virologus v) {
		//Sima mezőn nem lehet anyagot felvenni, ezért üres ez a metódus
	}
	
	/**
	 * Megpróbálja felvenni az itt lévő felszerelést (nem sikerül)
	 * */
	public Vedofelsz felveszVedofelsz() {
		return null;
	}

	/**
	 * Raktár típusú mezőn elpusztítja az ott lévő anyagot
	 * @author sisak
	 */
	public void Destroy(){
		//Csak a raktárnál van értelme elpusztítani az ott lévő anyagot, a Raktárnál felül van definiálva
	}

	/**
	 * Visszaadja a mező típusának nevét
	 * @return típusnév
	 */
	public String getTypeName(){
		return "sima mező";
	}
}

package domain;

/**
 * A virológus anyagkészleteit tároló osztály.
 * */
public class Anyag {
	/**Azt jelzi mennyi anyaga van a virológusnak épp most */
	private int mennyiseg;
	
	/** Azt jelzi mennyi a maximális anyagmennyiség, ami a virológusnál lehet*/
	private int maxAnyag;

	/** Az induló anyag mennyiség */
	private static int alapMennyiseg = 100;

	/** Az induló maximális anyag mennyiség */
	private static int alapMaxAnyag = 100;

	/** Alap konstruktor*/
	public Anyag(){
		mennyiseg = alapMennyiseg;
		maxAnyag = alapMaxAnyag;
	}
	
	/**
	 * Megnöveli az anyagmennyiséget a paraméterben kapott értékkel, maximálva a maximum értékre
	 * */
	public void novel(int mennyi)
	{
		mennyiseg += mennyi;
		if(mennyiseg > maxAnyag)
		{
			mennyiseg = maxAnyag;
		}
	}
	
	/**
	 * Csökkenti az anyagmennyiséget a megadott értékkel ha tudja, és visszatér true-val.
	 * Ha nem tudja, nem csökkent semmivel és false-szal tér vissza.
	 * */
	public boolean csokkent(int mennyi)
	{
		if(mennyiseg < mennyi)
			return false;
		mennyiseg -= mennyi;
		return true;
	}
	
	/**
	 * Getter az anyagmennyiséghez
	 * */
	public int mennyi()
	{
		return mennyiseg;
	}
	
	/**
	 * Kirabolnak, vagyis a nálam lévő anyag átlerül a támadóhoz
	 * @param tamado a támadó virológus objektuma
	 * @author safar
	 * */
	public void kirabolnak(Virologus tamado) {
		tamado.getAnyag().novel(mennyiseg);
		mennyiseg = 0;
	}
	
	/**
	 * Amikor raktárból vesz föl anyagot, a domain.Raktar ezt hívja meg
	 * @author sisak
	  */
	public void raktar() {
		mennyiseg = maxAnyag;
	}
	
	/** Növeli az anyagfelvevő-képességet a 1,5-szeresére*/
	public void maxNovel()
	{
		maxAnyag *= 1.5;
	}
	
	/** Csökkenti az anyagfelvevőképességet a 2/3-ára*/
	public void maxCsokkent()
	{
		maxAnyag *= 2; maxAnyag /= 3;  //Így lesz jól visszacsökkentve az eredeti értékére
	}
}


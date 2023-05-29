package domain;

/**
 * A kesztű védőfelszerelést reprezentálja.
 * Ezzel a valamely virológus által felkent ágens a kenőre visszadobható.
 * A kesztyűk hatása 3 használat után megszűnik, a kesztyűk lehámlanak, eltűnnek.
 * */
public class Kesztyu extends Vedofelsz {

	/**Hányszor tud még kenést visszadobni */
	private int elet = 3;

	/**
	 * Tulajdonossal ellátott paraméterű konstruktor
	 * @author Vesztergombi
	 */
	public Kesztyu(Virologus v){
		tulaj = v;
		v.addVedofelsz(this);
	}

	/**
	 * Paraméter nélküli konstruktor
	 * @author Vesztergombi
	 */
	public Kesztyu(){ }

	/**
	 * Megkenés hatására a domain.Kesztyu által végbemenő események
	 * @param mivel Milyen Agenssel próbálnak megkenni minket
	 * @param ki Ki azaz melyik virológus próbál megkenni minket (ha nem virológus, null)
	 * @return Ki tudta-e védeni a megkenést
	 * @author Vesztergombi
	 */
	@Override
    public boolean megkentek(Agens mivel, Virologus ki){
		if (ki == null){		// Medvevírus
			return false;
		}
		if (elet <= 0) {
				return false;
		}
		elet -= 1;
		boolean b = ki.megken(tulaj, mivel);
		return !b;		//Ha az ellenfél ki tudta védeni, akkor én nem tudtam kivédeni, és fordítva
	}

	/**
	 * Figyeli, hogy elhasználódott-e a kesztyű, ha igen, meghívja az end()-et.
	 * @author Vesztergombi
	 * */
	@Override
	public void tick() {
		if (elet <= 0){
			tulaj.removeVedofelsz(this);
		}
	}

	@Override
	public String getName() {
		String life = Integer.toString(elet);
		return "kesztyu - " + life;
	}
}

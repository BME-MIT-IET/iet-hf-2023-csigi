package vilagtalan_virologusok;


/**
* Olyan ágenst valósít meg, ami „véletlenszerű bolyongásra” kényszeríti
* a virológust. Ilyenkor nem lehet eldönteni, hogy mikor és merre akarunk
* lépni, egy véletlenszerű irányba fogja léptetni a virológust a köre elején.
*
*
*  @author Rádai Ronald
*/
public class Vitustanc extends Agens {

	/**
	 * A megpéldányosított vírustánc referenciája
	 */
	private final Vitustancolo vitustancolo;

	Vitustanc()	{ vitustancolo = new Vitustancolo(); }


	/**
	 * Hozzáad a paraméterben kapott virológus lépésmanageréhez egy vitustáncoló lépést
	 * @param v Ki az a Virologus aki vitustáncolni fog
	 */
	public void begin(Virologus v)
	{
		if (!elhasznalt) {
			elhasznalt = true;
			virologus = v;
			v.getAgensek().add(this);
			v.addLepes(vitustancolo);
		}
	}
	
	/**
	 * Az ágens lejáratának a kezelése.
	 * Eltávolítja a tárolt referenciát a lépések közül-
	 */
	public void end() {	virologus.removeLepes(vitustancolo); }
	
	/** Klónozza az ágenst*/
	public Agens duplicate() { return new Vitustanc(); }

	/**
	 * Visszaadja az ágens nevét
	 * @return név
	 */
	@Override
	public String getName() {
		return "vitus";
	}
}

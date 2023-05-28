/**
* Egy olyan ágenst valósít meg, ami lebénítja a virológust,
* aki a bénultság ideje alatt nem tud akciókat végrehajtani,
* és célpontja lehet rablásoknak.
*
*
*  @author Rádai Ronald
*/
public class Benit extends Agens {

	/**
	 * A megpéldányosított bena referenciája
	 */
	private final Bena bena;

	/**
	 * Alap konstruktor
	 */
	Benit()	{ bena = new Bena(); }

	/**
	 * Hozzáad a paraméterben kapott virológus lépésmanageréhez egy bena lépést
	 * @param v Ki az a Virologus aki le fog bénulni
	 */
	public void begin(Virologus v)
	{
		if (!elhasznalt){
			elhasznalt = true;
			virologus = v;
			virologus.addLepes(bena);
			v.getAgensek().add(this);
		}
	}

	/**
	 * Az ágens lejáratának a kezelése.
	 * Eltávolítja a tárolt referenciát a lépések közül-
	 */
	public void end() { virologus.removeLepes(bena); }
	
	/**
	 * Klónozza az ágenst
	 * */
	public Agens duplicate() { return new Benit(); }

	/**
	 * Visszaadja az ágens nevét
	 * @return név
	 */
	@Override
	public String getName() {
		return "benit";
	}
}

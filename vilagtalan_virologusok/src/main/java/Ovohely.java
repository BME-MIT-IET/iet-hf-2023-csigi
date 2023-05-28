/**
 * A védőfelszerelések az óvóhelyeken vannak szétszórva.
 * Egy felszerelés megszerzéséhez a virológusnak a megfelelő óvóhelyre kell bemennie, és a védőfelszerelést fel kell vennie.
 * */
public class Ovohely extends Mezo {
	/** A mezőn lévő védőfelszerelés */
	private Vedofelsz vedofelsz;

	/**
	 * Konstruktor
	 * @param vdf A mezőn elhelyezendő védőfelszerelés
	 */
	public Ovohely(Vedofelsz vdf){
		vedofelsz = vdf;
	}


	/** Odaadja a mezőn lévő védőfelszerelést (a mezőről törlődik)
	 * @return A védőfelszerelés */
	@Override
	public Vedofelsz felveszVedofelsz() {
		Vedofelsz vdf = vedofelsz;
		vedofelsz = null;
		return vdf;
	}

	/**
	 * Visszaadja a mező típusának nevét
	 *
	 * @return típusnév
	 */
	@Override
	public String getTypeName() {
		return "óvóhely";
	}
}

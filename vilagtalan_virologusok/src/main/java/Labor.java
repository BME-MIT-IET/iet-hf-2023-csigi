import java.util.ArrayList;
import java.util.Random;

/**
 * Labor típusú mezőt reprezentáló osztály.
 * */
public class Labor extends Mezo {

	/**
	 * medve-e
	 */
	private boolean isMedve;

	/**Mely genetikai kód van a laborban */
	private Agens kod;

	/**
	 * Konstruktor
	 * @param a A laborban levo agens
	 * @author sisak
	 */
	public Labor(Agens a, boolean isBear){

		isMedve = isBear;

		kod = a;
	}

	/**
	 * Rálépve megkeni medvével a virológust, ha medvés a mező
	 * @param v A virológus, aki rálép
	 * @return Szomszédok listája
	 */
	public ArrayList<Mezo> ralep(Virologus v){
		if(isMedve)
			v.megken(null, new Medve());
		return super.ralep(v);
	}


	/**A virologus letapogatja a genetikai kódot
	 * @return A laborban lévő genetikai kód */
	@Override
	public Agens letapogat() {
		return kod;
	}

	/**
	 * Visszaadja a mező típusának nevét
	 *
	 * @return típusnév
	 */
	@Override
	public String getTypeName() {
		return "labor";
	}
}

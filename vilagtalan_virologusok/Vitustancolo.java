package vilagtalan_virologusok;
import java.util.Random;


/**
 * Ez egy olyan Lepes megvalósítás, amely a vitustánc állapotában
 * való lépést implementálja. (választ egy random szomszédos mezőt) Lepes < Priorítása < Bena
 *
 *
 * @author Rádai Ronald
 */
public class Vitustancolo extends Lepes
{
	/** Random szám generáló objektum a véletlen lépés megvalósításához
	 * @author Benczik
	 */
	private Random rand = new Random();

	/**
	 * Visszaadja az adott lépés prioritását.
	 * @return Prioritás
	 */
	@Override
	public int getPriority() { return 20; }

	/**
	 * Visszaad egy random lépést
	 * @param to i. mező, ahova a játékos szeretne lépni
	 * @param n a jelenlegi mező szomszédjainak a számossága
	 * @return A kiválasztott lépés (to értéke)
	 */
	@Override
	public int lepes(int to, int n)
	{
		if(Controller.random)
			return rand.nextInt(n);
		else
			return 0;
	}
}
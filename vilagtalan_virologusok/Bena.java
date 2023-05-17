package vilagtalan_virologusok;


/**
 * Ez egy olyan Lepes megvalósítás, amely a bénultság állapotában való
 * lépést implementálja. (semmit) Priorítása > Vitustancolo
 *
 *
 * @author Rádai Ronald
 */
public class Bena extends Lepes
{
	/**
	 * Visszaadja az adott lépés prioritását.
	 * @return Prioritás
	 */
	@Override
	public int getPriority() {  return 30; }

	/**
	 * Bénult állapotbani lépés megvalósítása.
	 * -1-et ad vissza, mint hogy nem léphet
	 * @param to i. mező, ahova a játékos szeretne lépni
	 * @param n a jelenlegi mező szomszédjainak a számossága
	 * @return A kiválasztott lépés (to értéke)
	 */
	@Override
	public int lepes(int to, int n)
	{
		return -1;
	}
}

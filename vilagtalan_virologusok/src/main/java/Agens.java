/**
 * A különböző hatásokat megvalósító egyedi ágensek közös tulajdonságait és metódusait összefogó ősosztály.
 * */
public abstract class Agens extends Hatas implements Leptetheto{
	/**
	 * Amikor rá van kenve valakire, azt jelzi, hogy még hány körig aktív rajta, minden körben eggyel csökken az értéke
	 * A megtanult ágensekre az értéke int_max
	 * */
	protected int eletek = 2;
	
	/**
	 * Mennyi az ágens anyagköltsége?
	 * */
	protected int ar = 10;
	
	/**
	 * Melyik virológuson aktív éppen.
	 * */
	protected Virologus virologus;

	/** Rákenték-e már valakire az ágenst. */
	protected boolean elhasznalt = false;

	//@Override
	//public int compareTo(Agens agn) {
	//	return agn.getName().compareTo(this.getName());
	//}

	/**
	 * Minden körben csökken eggyel, amikor lejár, megszűnik az ágens
	 * @author safar
	 * */
	public void tick() { eletek--; if(eletek == 0) end();}


	/**
	 * Getter az Agens anyagárához
	 * @return Egy nemnegatív szám, ami az adott Agens legyártásához szükséges anyagmennyiséget jelenti
	 */
	public int getAr() { return ar; }
	
	/**
	 * Létrehoz egy új példányt az Ágensből
	 * */
	public abstract Agens duplicate();

	public abstract String getName();

	/**
	 * Az ágens aktiválódásakor hívódik. Beállítja, hogy elhasználták.
	 * @param v Ki az a Virologus akire érvényesíteni kell a hatásokat
	 */
	public void begin(Virologus v){
		if (!elhasznalt) {
			elhasznalt = true;
			v.getAgensek().add(this);
		}
	}
}
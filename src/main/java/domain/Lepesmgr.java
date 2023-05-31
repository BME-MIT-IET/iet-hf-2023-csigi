package domain;

import java.util.ArrayList;


/**
 * Ez egy virológus lépésközpontja. Itt döntődik el, hogy a játékos
 * akaratával szemben hogyan lép majd a virológus. Le lehet tőle kérdezni,
 * hogy béna-e a varázsló.
 *
 *
 *  @author Rádai Ronald
 */
public class Lepesmgr
{

	/**
	 * A virológus referenciája
	 */
	private Virologus v;

	/**
	 * A virulógus által használható lépések
	 */
	private ArrayList<Lepes> lepesek = new ArrayList<Lepes>();

	/**
	 * A lépésmenedzser konstruktorja
	 */
	public Lepesmgr(Virologus vir)
	{
		v = vir;
		lepesek.add(new Lepes());
	}

	/**
	 * Hozzáad egy kapott lépést.
	 * @param l A kapott lépés
	 */
	public void addLepes(Lepes l) { lepesek.add(l); }

	/**
	 * Eltávolít egy kapott lépést.
	 * @param l A kapott lépés
	 */
	public void removeLepes(Lepes l) { lepesek.remove(l); }

	/**
	 * Ebben a függvényben végig kérdezgeti a Lépéseket, és közülük
	 * a legnagyobb prioritásút választja.
	 * @param to ide szeretnénk lépni
	 * @param n jelenlegi szomszédok száma
	 * @return hányadik szomszédra lép
	 */
	public int lep(int to, int n)
	{
		if (lepesek.size() == 0)
			throw new RuntimeException("Hibás a Lépésmgr!");
		int maxIndex = 0;
		int maxPriority = 0;
		for (int i = 1; i < lepesek.size(); ++i)
		{
			int jelp = lepesek.get(i).getPriority();
			if(jelp > maxPriority)
			{
				maxIndex = i;
				maxPriority = jelp;
			}
		}
		return lepesek.get(maxIndex).lepes(to, n);
	}

	/**
	 * Visszaadja, hogy a virológus le van-e bénulva
	 * @return Béna-e a varázsló
	 */
	public boolean bena()
	{
		int max = 0;
		for (Lepes lepes : lepesek) {max = Math.max(lepes.getPriority(), max);}
		return  max == Controller.BENALEPES_PRIORITY;
	}

	/**
	 * Visszaadja, hogy a virológus medve-e
	 * @return domain.Medve-e a varázsló
	 */
	public boolean medve()
	{
		boolean medve = false;
		for (Lepes lepes : lepesek) {if(lepes.getPriority() == Controller.MEDVELEPES_PRIORITY) medve = true;}
		return medve;
	}
}

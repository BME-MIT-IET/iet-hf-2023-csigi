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
	public int Lep(int to, int n)
	{
		if (lepesek.size() == 0)
			throw new RuntimeException("Hibás a Lépésmgr!");
		int maxi = 0;
		int maxip = 0;
		for (int i = 1; i < lepesek.size(); ++i)
		{
			int jelp = lepesek.get(i).getPriority();
			if(jelp > maxip)
			{
				maxi = i;
				maxip = jelp;
			}
		}
		int res = lepesek.get(maxi).lepes(to, n);
		return res;
	}

	/**
	 * Visszaadja, hogy a virológus le van-e bénulva
	 * @return Béna-e a varázsló
	 */
	public boolean bena()
	{
		int max = 0;
		for (Lepes lepes : lepesek) {max = Math.max(lepes.getPriority(), max);}
		return  max == Controller.benalepes_priority;
	}

	/**
	 * Visszaadja, hogy a virológus medve-e
	 * @return Medve-e a varázsló
	 */
	public boolean medve()
	{
		boolean medve = false;
		for (Lepes lepes : lepesek) {if(lepes.getPriority() == Controller.medvelepes_priority) medve = true;}
		return medve;
	}
}

package vilagtalan_virologusok;/*
* Description
*
*  @author Ákos
*/



/** Egy olyan ágenst valósít meg, ami megvédi a virológust további ágensek rákenésétől. */
public class Vedo extends Agens {



	/**
	 * @param mivel Milyen Agenssel próbálnak megkenni minket
	 * @param ki    Ki azaz melyik virológus próbál megkenni minket
	 * @return true-val tér vissza, hisz sikeresen megvéd minket a kenéstől
	 * @author Ákos
	 */
	@Override
	public boolean megkentek(Agens mivel, Virologus ki)
	{
		return true;
	}
	
	/**
	 *
	 * */
	public Agens duplicate()
	{
		return new Vedo();
	}

	@Override
	public String getName() {
		return "vedo";
	}
}

package vilagtalan_virologusok;/*
* Description
*
*  @author
*/



/**
 * Az aminosavak és a nukleotidok (együttes nevükön anyagok) különféle raktárakban szedhetők össze, ezeket jeleníti meg az osztály.
 * @author sisak
 * */
public class Raktar extends Mezo {
	private boolean isActive = true;

	/**
	 * Megakadályozza, hogy a raktárból a későbbiekben anyagot lehessen felvenni
	 * @author sisak
	 */
	public void Destroy(){
		isActive = false;
	}

	/**
	 * Az anyag fölvételekor hívódik meg, végrehajtja az anyag felvételét.
	 * @author sisak
	 * */
	public void felveszAnyag(Virologus v) {
		if(isActive) {
			Anyag anyg = v.getAnyag();
			anyg.raktar();
		}
	}

	/**
	 * Visszaadja a mező típusának nevét
	 *
	 * @return típusnév
	 */
	@Override
	public String getTypeName() {
		return "raktár";
	}
}

package vilagtalan_virologusok;



/**
 * Különleges hatásokkal rendelkező osztályok absztrakt ősosztálya.
 * @author Ákos
 * */
public abstract class Hatas implements Leptetheto {

	/**
	 * Agens rákenésekor vagy Vedofelsz felszedésekor hívódik meg, a kezdeti hatásokat valósítja meg
	 * @param v Ki az a Virologus akire érvényesíteni kell a hatásokat
	 */
	public void begin(Virologus v) {
	}


	/**
	 * A begin() ellenpárja, Agens lejártakor vagy Vedofelsz elvesztésekor hívódik meg
	 * "Visszacsinálja", semmissé teszi az Agens/Vedofelsz hatását
	 */
	public void end() {
	}

	/**
	 * @param mivel Milyen Agenssel próbálnak megkenni minket
	 * @param ki Ki azaz melyik virológus próbál megkenni minket
	 * @return Azzal tér vissza hogy megvéd-e minket az adott Vedofelsz vagy Agens attól, hogy megkenjenek
	 * 			true ha megvédett minket és alapból false, ha hagyja a kenést
	 */
	public boolean megkentek(Agens mivel, Virologus ki){ //throws Kesztyu.KesztyuLejarException {
		return false;
	}

	/**
	 * Nem csinál semmit
	 * */
	public void tick() {}
}

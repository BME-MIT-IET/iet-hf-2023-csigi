package domain;

/**
 * Különleges hatásokkal rendelkező osztályok absztrakt ősosztálya.
 * @author Ákos
 * */
public abstract class Hatas implements Leptetheto {

	/**
	 * domain.Agens rákenésekor vagy domain.Vedofelsz felszedésekor hívódik meg, a kezdeti hatásokat valósítja meg
	 * @param v Ki az a domain.Virologus akire érvényesíteni kell a hatásokat
	 */
	public void begin(Virologus v) {
	}


	/**
	 * A begin() ellenpárja, domain.Agens lejártakor vagy domain.Vedofelsz elvesztésekor hívódik meg
	 * "Visszacsinálja", semmissé teszi az domain.Agens/domain.Vedofelsz hatását
	 */
	public void end() {
	}

	/**
	 * @param mivel Milyen Agenssel próbálnak megkenni minket
	 * @param ki Ki azaz melyik virológus próbál megkenni minket
	 * @return Azzal tér vissza hogy megvéd-e minket az adott domain.Vedofelsz vagy domain.Agens attól, hogy megkenjenek
	 * 			true ha megvédett minket és alapból false, ha hagyja a kenést
	 */
	public boolean megkentek(Agens mivel, Virologus ki){ //throws domain.Kesztyu.KesztyuLejarException {
		return false;
	}

	/**
	 * Nem csinál semmit
	 * */
	public void tick() {}
}

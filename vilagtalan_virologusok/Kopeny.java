package vilagtalan_virologusok;

import java.util.Random;



/**
 * Védőköpenyt reprezentáló osztály, a köpeny az ágenseket 82,3%-os hatásfokkal tartja távol.
 * */
public class Kopeny extends Vedofelsz {
	/** Random szám generáló objektum a kivédés esélyének modellezéséhez
	 * @author Benczik
	 */
	private Random rand = new Random();

	/**
	 * Tulajdonossal ellátott paraméterű konstruktor
	 * @author Vesztergombi
	 */
	public Kopeny(Virologus v){
		tulaj = v;
		v.addVedofelsz(this);
		int a = Controller.BENALEPES_PRIORITY;
	}

	/**
	 * Paraméter nélküli konstruktor
	 * @author Vesztergombi
	 */
	public Kopeny(){ }

	/**
	 * A köpenyt viselő virológus megkenése
	 * @param mivel Milyen Agenssel próbálnak megkenni minket
	 * @param ki Ki azaz melyik virológus próbál megkenni minket
	 * @return Ki tudta-e védeni a megkenést
	 * @author Vesztergombi
	 */
	@Override
    public boolean megkentek(Agens mivel, Virologus ki) {
		if (Controller.random) {
			if (rand.nextDouble() > 0.823) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		return "kopeny";
	}
}

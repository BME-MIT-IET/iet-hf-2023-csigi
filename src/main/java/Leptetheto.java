/**
 * Minden osztályban, ami megvalósítja a Leptetheto interfészt van egy tick() metódus.
 * Ezt meghívhatjuk minden körben, ezáltal pl. öregítés megvalósítható.
 * */
public interface Leptetheto {
	/**
	 * Ez a metódus pl. öregíti az ágenseket, illetve elvégezhet egyéb teendőket.
	 * A virológus birtokában lévő Leptethetoknél minden alkalommal lefut a tick(), amikor az adott játékos kerül sorra.
	 * */
	public void tick();
}

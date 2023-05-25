public class Felejt extends Agens {
	
	/**  */
	public void begin(Virologus v) {
		if (!elhasznalt){
			elhasznalt = true;
			v.getAgensek().add(this);
			v.torolAgensek();
		}
	}
	
	/** Kónozza az ágenst*/
	public Agens duplicate() { return new Felejt(); }

	@Override
	public String getName() {
		return "felejt";
	}
}

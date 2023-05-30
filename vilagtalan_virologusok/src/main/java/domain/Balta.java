package domain;

public class Balta extends Vedofelsz {

    /** Ki van-e csorbulva a balta */
    private boolean csorba = false;

    /**
     * A balta használata valamelyik virológuson
     * @param kin melyik virológust csapják meg
     */
    public void use(Virologus kin){
        if (csorba) return;
        if(kin.isMedve()){
            kin.meghal();
            csorba = true;
        }
    }

    /**
     *
     * @return Csorba-e a balta
     */
    public boolean isCsorba() {
        return csorba;
    }

    @Override
    public String getName() {
        String is_used = csorba ? "used" : "unused";
        return "balta - " + is_used;
    }
}

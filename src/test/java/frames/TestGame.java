package frames;

import domain.Agens;
import domain.Jatek;
import frames.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class TestGame extends Jatek {

    /**
     * Játék konstruktora
     * Készít egy mapot a virológusoknak és a pályának és egy list-et a kódoknak
     *
     * @param Frame - A megjelenítő ablakot reprezentáló Frame referenciája
     */
    public TestGame(MainFrame Frame) {
        super(Frame);
    }

    public void setLearnable(ArrayList<Agens> learnables){
        this.kodok = learnables;
    }
}

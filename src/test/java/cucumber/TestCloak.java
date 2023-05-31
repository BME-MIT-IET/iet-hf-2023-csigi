package cucumber;

import domain.Agens;
import domain.Kopeny;
import domain.Virologus;

public class TestCloak extends Kopeny {
    @Override
    public boolean megkentek(Agens mivel, Virologus ki) {
        return true;
    }
}

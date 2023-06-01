package cucumber;

import domain.Vitustancolo;

public class TestRandomMove extends Vitustancolo {
    @Override
    public int lepes(int to, int n) {
        return to +1;
    }
}

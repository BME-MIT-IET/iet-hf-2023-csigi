package groupId;

import domain.Vitustancolo;

public class TestRandomMove extends Vitustancolo {
    @Override
    public int lepes(int to, int n) {
        if(to + 1 >= n)
            return 0;
        return to +1;
    }
}

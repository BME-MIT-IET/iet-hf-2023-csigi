package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class VitustancoloTest {
    private Vitustancolo vitustancolo = new Vitustancolo();

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 7})
    void lepes_random(int szomszedSzamossag) {
        Controller.random = true;
        var mezoNumber = vitustancolo.lepes(1, szomszedSzamossag);
        assertTrue(mezoNumber >= 0);
        assertTrue(mezoNumber < szomszedSzamossag);
    }
}
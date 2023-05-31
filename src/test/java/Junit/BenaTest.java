package Junit;

import domain.Bena;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BenaTest {
    private Bena bena = new Bena();

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 100})
    void lepes(int to) {
        var result = bena.lepes(to, to * 2);

        assertEquals(-1, result);
    }
}
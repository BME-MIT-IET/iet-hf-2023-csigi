package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

class AnyagTest {

    private final Anyag anyag = new Anyag();
    private final int alapMennyiseg = 100;

    @ParameterizedTest
    @ValueSource(ints = {alapMennyiseg, alapMennyiseg-1})
    void csokkent_elegendo_anyag(int kivonando) {
        var isSuccessful = anyag.csokkent(kivonando);
        assertTrue(isSuccessful);

        var amount = anyag.mennyi();
        assertEquals(alapMennyiseg - kivonando, amount);
    }

    @ParameterizedTest
    @ValueSource(ints = { alapMennyiseg + 1, alapMennyiseg + 100, alapMennyiseg + 10000})
    void csokkent_nincs_eleg_anyag(int kivonando) {
        var isSuccessful = anyag.csokkent(kivonando);
        assertFalse(isSuccessful);

        var amount = anyag.mennyi();
        assertEquals(alapMennyiseg, amount);
    }
}
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

class LepesTest {
    private Lepes lepes = new Lepes();

    @ParameterizedTest
    @ArgumentsSource(LepesValidArgumentsProvider.class)
    void lepes_valid(int to, int szomszedSzamossag) {
        var stepTo = lepes.lepes(to, szomszedSzamossag);

        assertEquals(to, stepTo);
    }

    @ParameterizedTest
    @ArgumentsSource(LepesInvalidArgumentsProvider.class)
    void lepes_to_invalid(int to, int szomszedSzamossag){
        var stepTo = lepes.lepes(to, szomszedSzamossag);

        assertEquals(-1, stepTo);
    }
}


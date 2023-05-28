import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LepesTest {
    private Lepes lepes = new Lepes();

    @ParameterizedTest
    @ArgumentsSource(ValidLepesArgumentsProvider.class)
    void lepes_valid(int to, int szomszedSzamossag) {
        var stepTo = lepes.lepes(to, szomszedSzamossag);

        assertEquals(stepTo, to);
    }
}


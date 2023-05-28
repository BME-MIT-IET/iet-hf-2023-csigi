import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KesztyuTest {
    private final Kesztyu kesztyu = new Kesztyu();

    @Test
    void megkentek_medve() {
        var medveVirus = mock(Medve.class);
        var result = kesztyu.megkentek(medveVirus, null);

        assertFalse(result);
    }

    @Test
    void megkentek_van_elet() {
        var benit = mock(Benit.class);
        var kenoVirologus = mock(Virologus.class);
        var kentVirologus = mock(Virologus.class);
        kesztyu.begin(kentVirologus);
        when(kenoVirologus.megken(kentVirologus, benit)).thenReturn(false);

        var result = kesztyu.megkentek(benit, kenoVirologus);

        assertTrue(result);
        verify(kenoVirologus).megken(kentVirologus, benit);
    }
}
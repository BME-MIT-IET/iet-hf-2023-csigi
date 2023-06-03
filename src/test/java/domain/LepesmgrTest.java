package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LepesmgrTest {
    private Lepesmgr lepesmgr;

    @BeforeEach
    void initialize(){
        var virologus = mock(Virologus.class);
        lepesmgr = new Lepesmgr(virologus);
    }

    @Test
    void lep_priorities1(){
        var lepes1Result = -1;
        var lepes1 = mock(Lepes.class);
        when(lepes1.lepes(anyInt(), anyInt())).thenReturn(lepes1Result);
        when(lepes1.getPriority()).thenReturn(30);

        var lepes2Result = 1;
        var lepes2 = mock(Lepes.class);
        when(lepes2.lepes(anyInt(), anyInt())).thenReturn(lepes2Result);
        when(lepes2.getPriority()).thenReturn(35);
        lepesmgr.addLepes(lepes2);

        var stepTo = lepesmgr.lep(1, 3);

        assertEquals(lepes2Result, stepTo);
    }

    @Test
    void lep_priorities2(){
        var lepes1Result = -1;
        var lepes1 = mock(Lepes.class);
        when(lepes1.lepes(anyInt(), anyInt())).thenReturn(lepes1Result);
        when(lepes1.getPriority()).thenReturn(30);

        var lepes2Result = 1;
        var lepes2 = mock(Lepes.class);
        when(lepes2.lepes(anyInt(), anyInt())).thenReturn(lepes1Result);
        when(lepes2.getPriority()).thenReturn(3);
        lepesmgr.addLepes(lepes2);

        var stepTo = lepesmgr.lep(1, 3);

        assertEquals(lepes1Result, stepTo);
    }

    @Test
    void bena_igaz() {
        var bena = mock(Bena.class);
        when(bena.getPriority()).thenReturn(Controller.BENALEPES_PRIORITY);
        lepesmgr.addLepes(bena);

        var isBena = lepesmgr.bena();

        assertTrue(isBena);
    }

    @Test
    void bena_hamis() {
        var lepes = mock(Lepes.class);
        when(lepes.getPriority()).thenReturn(10);
        lepesmgr.addLepes(lepes);

        var isBena = lepesmgr.bena();

        assertFalse(isBena);
    }

    @Test
    void medve_igaz() {
        var medve = mock(Medvetancolo.class);
        when(medve.getPriority()).thenReturn(Controller.MEDVELEPES_PRIORITY);
        lepesmgr.addLepes(medve);

        var isMedve = lepesmgr.medve();

        assertTrue(isMedve);
    }

    @Test
    void medve_hamis() {
        var lepes = mock(Lepes.class);
        when(lepes.getPriority()).thenReturn(10);
        lepesmgr.addLepes(lepes);

        var isMedve = lepesmgr.medve();

        assertFalse(isMedve);
    }
}
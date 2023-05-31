package Junit;

import domain.*;
import frames.MainFrame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JatekTest {
    private static Jatek jatek;
    private static Benit agens1;
    private static ArrayList<Agens> agensek;

    @BeforeAll
    static void initialize() {
        var mockFrame = mock(MainFrame.class);
        jatek = new Jatek(mockFrame);
        agens1 = new Benit();
        Vitustanc agens2 = new Vitustanc();
        agensek = new ArrayList<>();
        agensek.add(agens1);
        agensek.add(agens2);
    }

    @Test
    void containsAgn_benit() {
        var contains = jatek.containsAgn(agensek, agens1);

        assertTrue(contains);
    }

    @Test
    void containsAgn_vitustanc() {
        var contains = jatek.containsAgn(agensek, agens1);

        assertTrue(contains);
    }

    @Test
    void containsAgn_not_contains() {
        var contains = jatek.containsAgn(agensek, new Vedo());

        assertFalse(contains);
    }
}
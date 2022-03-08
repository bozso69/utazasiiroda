package com.example.utazasiiroda;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class utilTest {
    util util;

    @BeforeEach
    void setUp() {
        util = new util();
    }

    @AfterEach
    void tearDown() {
        util = null;
    }


    @Test
    void hiba() {

    }

    @Test
    @DisplayName("Kedvezmény számítás")
    void kedvezmenyesArSzamolo() {
        assertEquals("0", util.kedvezmenyesArSzamolo("1000", 100));
    }

    @Test
    @DisplayName("Kettő - 18 közötti életkor")
    void ketto_tizennyolcE() {
       assertTrue(util.Ketto_tizennyolcE(15));
       //assertTrue(util.Ketto_tizennyolcE(2));
       assertTrue(util.Ketto_tizennyolcE(18));
       assertFalse(util.Ketto_tizennyolcE(1));
       assertFalse(util.Ketto_tizennyolcE(0));
       assertFalse(util.Ketto_tizennyolcE(19));

    }

    @Test
    @DisplayName("Két éves")
    void keteves() {
        assertTrue(util.keteves(2));
        //assertFalse(util.keteves(1));
        //assertFalse(util.keteves(0));
        assertFalse(util.keteves(3));
        //assertFalse(util.keteves(-3));

    }

    @Test
    @DisplayName("Üres dátum")
    void uresEdatum() {
        assertFalse(util.uresEdatum(null));
        assertTrue(util.uresEdatum(LocalDate.of(2020,01,01)));
    }

}
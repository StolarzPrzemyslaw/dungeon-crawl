package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    public void getId_getValidMapId_Returns1() {
        assertEquals(1, Map.LEVEL1.getId());
    }

    @Test
    public void getId_wantToGetNonExistingEnum_throwsException() {
        assertThrows(RuntimeException.class, () -> Map.values()[Map.values().length].getId());
    }
}
package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourItemTest {

    @Test
    void testEquals() {
        TourItem tourItem = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        TourItem tourItem2 = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        assertEquals(tourItem, tourItem2);
    }

    @Test
    void testHashCode() {
        TourItem tourItem = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        TourItem tourItem2 = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        assertEquals(tourItem.hashCode(), tourItem2.hashCode());
    }

    @Test
    void testToString() {
        TourItem tourItem = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        assertNotNull(tourItem.toString());
    }
}
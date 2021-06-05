package models;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class TourLogTest {

    @Test
    void testEquals() {
        Clock clock = Clock.fixed(LocalDate.of(1989, 01, 13).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        TourItem tourItem = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        TourLog tourLog = new TourLog(1, tourItem, LocalDateTime.now(clock), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        TourLog tourLog2 = new TourLog(1, tourItem, LocalDateTime.now(clock), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        assertEquals(tourLog, tourLog2);
    }

    @Test
    void testHashCode() {
        Clock clock = Clock.fixed(LocalDate.of(1989, 01, 13).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        TourItem tourItem = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        TourLog tourLog = new TourLog(1, tourItem, LocalDateTime.now(clock), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        TourLog tourLog2 = new TourLog(1, tourItem, LocalDateTime.now(clock), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        assertEquals(tourLog.hashCode(), tourLog2.hashCode());
    }

    @Test
    void testToString() {
        TourItem tourItem = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        TourLog tourLog = new TourLog(1, tourItem, LocalDateTime.now(), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        assertNotNull(tourLog.toString());
    }
}
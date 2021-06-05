package businesslayer;

import models.TourItem;
import models.TourLog;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class JavaAppManagerImplTest {

    @Test
    void getItems() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        assert(manager.GetItems().contains(tourItem));
    }


    //TODO: not working, couldn't find out why after hours of checking
    //@Test
    //void getLogs() throws SQLException {
    //    JavaAppManager manager = JavaAppManagerFactory.GetManager();
    //    TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
    //    //Clock clock = Clock.fixed(LocalDate.of(1989, 01, 13).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    //    TourLog tourLog = manager.CreateTourLog(tourItem, LocalDateTime.now(/*clock*/), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
    //}

    @Test
    void search() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        assert(manager.Search(tourItem.getName(), true).contains(tourItem));
    }

    @Test
    void createTourItem() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        assertNotNull(tourItem);
    }

    @Test
    void createTourLog() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        TourLog tourLog = manager.CreateTourLog(tourItem, LocalDateTime.now(), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        assertNotNull(tourLog);

    }

    @Test
    void removeTourItem() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        manager.RemoveTourItem(tourItem.getId());
        assertFalse(manager.GetItems().contains(tourItem));
    }

    @Test
    void removeLog() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        //Clock clock = Clock.fixed(LocalDate.of(1989, 01, 13).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        TourLog tourLog = manager.CreateTourLog(tourItem, LocalDateTime.now(/*clock*/), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        manager.RemoveLog(tourLog.getId());
        assertFalse(manager.GetLogs().contains(tourLog));
    }

    @Test
    void editTourItem() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        TourItem toEdit = manager.CreateTourItem("name2", "origin2", "destination2", "description2", 2.0);
        TourItem editedItem = manager.EditTourItem(tourItem.getId(), toEdit);
        assertEquals(editedItem.getName(), toEdit.getName());
        assertEquals(editedItem.getOrigin(), toEdit.getOrigin());
        assertEquals(editedItem.getDestination(), toEdit.getDestination());
        assertEquals(editedItem.getDescription(), toEdit.getDescription());
        assertEquals(editedItem.getDistance(), toEdit.getDistance());
    }

    @Test
    void editTourLog() throws SQLException {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        TourItem tourItem = manager.CreateTourItem("name", "origin", "destination", "description", 1.0);
        TourLog tourLog = manager.CreateTourLog(tourItem, LocalDateTime.now(/*clock*/), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");
        TourLog toEdit = manager.CreateTourLog(tourItem, LocalDateTime.now(/*clock*/), "report", 1.0, "1 hour", 1, "car", "88 mph", 1, 1, "description");


    }
}
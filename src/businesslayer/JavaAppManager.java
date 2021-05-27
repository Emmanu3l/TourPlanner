package businesslayer;

import models.TourItem;
import models.TourLog;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface JavaAppManager {
    List<TourItem> GetItems() throws SQLException;
    List<TourLog> GetLogs() throws SQLException; //added
    List<TourItem> Search(String itemName, boolean caseSensitive) throws SQLException;
    TourItem CreateTourItem(String name, String origin, String destination, String description, double distance) throws SQLException;
    TourLog CreateTourLog(String logText, TourItem item) throws SQLException;
}

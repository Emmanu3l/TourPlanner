package dataaccesslayer.dao;

import models.TourItem;
import models.TourLog;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ITourLogDAO {
    TourLog FindById(Integer logId) throws SQLException;
    TourLog AddNewItemLog(TourItem logItem, LocalDateTime creationTime, String report, double distance, String totalTime, int rating, String vehicleType, String averageSpeed, int horsepower, int joule, String description) throws SQLException;
    List<TourLog> GetLogsForItem(TourItem item) throws SQLException;

    List<TourLog> GetLogs() throws SQLException; //added

    void RemoveLog(Integer id) throws SQLException;

    void EditLog(TourLog modifiedLog) throws SQLException;
}

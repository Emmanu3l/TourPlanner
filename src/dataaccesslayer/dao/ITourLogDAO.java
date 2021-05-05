package dataaccesslayer.dao;

import models.TourItem;
import models.TourLog;

import java.util.List;

public interface ITourLogDAO {
    TourLog FindById(Integer id);
    TourLog AddNewItemLog(String logText, TourItem logItem);
    List<TourLog> GetLogsForItem(TourItem item);
}

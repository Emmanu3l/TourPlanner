package dataaccesslayer.dao;

import models.TourItem;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ITourItemDAO {
    TourItem FindById(Integer itemId) throws SQLException;
    TourItem AddNewItem(String name, String origin, String destination, String description, double distance) throws SQLException;
    List<TourItem> GetItems() throws SQLException; // gibt all tour items zurück, daher keine parameter vonnöten
}

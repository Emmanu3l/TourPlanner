package dataaccesslayer.dao;

import models.TourItem;

import java.time.LocalDateTime;
import java.util.List;

public interface ITourItemDAO {
    TourItem FindById(Integer itemId);
    TourItem AddNewItem(String name, String url, LocalDateTime creationTime);
    List<TourItem> GetItems(); // gibt all tour items zurück, daher keine parameter vonnöten
}

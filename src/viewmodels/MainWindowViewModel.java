package viewmodels;

import businesslayer.JavaAppManager;
import businesslayer.NameGenerator;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import models.TourItem;
import models.TourLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MainWindowController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MainWindowViewModel {

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    public void search(ObservableList<TourItem> tourItems, JavaAppManager manager, TextField searchField) throws SQLException {
        tourItems.clear();

        List<TourItem> items = manager.Search(searchField.textProperty().getValue(), false);
        tourItems.addAll(items);
        //search.setText("Results found");
        logger.info("Searched for item.");
    }

    public void clear(ObservableList<TourItem> tourItems, JavaAppManager manager, TextField searchField) throws SQLException {
        tourItems.clear();
        searchField.textProperty().setValue("");

        List<TourItem> items = manager.GetItems();
        tourItems.addAll(items);
        logger.info("Cleared search bar.");
    }


    public void generateItem(ObservableList<TourItem> tourItems, JavaAppManager manager) throws SQLException {
        TourItem genItem = manager.CreateTourItem(NameGenerator.GenerateName(4), NameGenerator.GenerateName(4), NameGenerator.GenerateName(4), NameGenerator.GenerateName(4), 42.0);
        tourItems.add(genItem);
        logger.info("Item has been randomly generated");
    }

    public void generateLog(ObservableList<TourLog> tourLogs, JavaAppManager manager, TourItem currentItem) throws SQLException {
        TourLog genLog = manager.CreateTourLog(currentItem, LocalDateTime.now(), NameGenerator.GenerateName(4), 4.0, NameGenerator.GenerateName(4), 4, NameGenerator.GenerateName(4), NameGenerator.GenerateName(4), 4, 4, NameGenerator.GenerateName(4));
        tourLogs.add(genLog); //added
        logger.info("Log has been randomly generated");
    }

    public void removeTour(TourItem currentItem, JavaAppManager manager, ObservableList<TourItem> tourItems) throws SQLException {
        manager.RemoveTourItem(currentItem.getId()); //remove from db
        tourItems.remove(currentItem); //remove from view
        logger.info("Tour has been removed");
    }

    public void removeLog(TourLog currentLog, JavaAppManager manager, ObservableList<TourLog> tourLogs) throws SQLException {
        manager.RemoveLog(currentLog.getId()); //remove from db
        tourLogs.remove(currentLog); //remove from view
        logger.info("Log has been removed");
    }
}

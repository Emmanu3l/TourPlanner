package viewmodels.tours;

import businesslayer.JavaAppManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import lombok.Getter;
import models.TourItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MainWindowController;

import java.sql.SQLException;

public class AddTourViewModel {

     @Getter private final StringProperty name = new SimpleStringProperty("");
     @Getter private final StringProperty origin = new SimpleStringProperty("");
     @Getter private final StringProperty destination = new SimpleStringProperty("");
     @Getter private final StringProperty description = new SimpleStringProperty("");
     @Getter private final StringProperty distance = new SimpleStringProperty("");

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    public void addTour(JavaAppManager manager, ObservableList<TourItem> tourItems) throws SQLException {
        TourItem addedItem = manager.CreateTourItem(name.get(), origin.get(), destination.get(), description.get(), Double.parseDouble(distance.get()));
        tourItems.add(addedItem);
        //MapQuest.createStaticMapImage(addedItem); it's better to do that in the preview selected tour function
        logger.info("Tour has been added");
    }

}

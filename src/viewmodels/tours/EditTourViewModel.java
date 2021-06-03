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

public class EditTourViewModel {

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    @Getter private final StringProperty name = new SimpleStringProperty("");
    @Getter private final StringProperty origin = new SimpleStringProperty("");
    @Getter private final StringProperty destination = new SimpleStringProperty("");
    @Getter private final StringProperty description = new SimpleStringProperty("");
    @Getter private final StringProperty distance = new SimpleStringProperty("");

    public void editTour(TourItem currentItem, JavaAppManager manager, ObservableList<TourItem> tourItems) throws SQLException {
        //if you have the time, modify EditTourItem because you technically only need one parameter, since the correct id is already in the modifiedItem
        //TourItem modifiedItem = new TourItem(currentItem.getId(), name.getText(), origin.getText(), destination.getText(), description.getText(), Double.parseDouble(distance.getText()));
        TourItem modifiedItem = new TourItem(currentItem.getId(), name.getValue(), origin.getValue(), destination.getValue(), description.getValue(), Double.parseDouble(distance.getValue()));
        manager.EditTourItem(currentItem.getId(), modifiedItem);
        tourItems.remove(currentItem);
        tourItems.add(modifiedItem);
        logger.info("Tour has been modified");
        //TODO: also, make the list views show multiple colums already.
    }


}

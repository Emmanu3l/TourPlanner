package views.tours;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.TourItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MainWindowController;

import java.sql.SQLException;

public class EditTourWindowController {

    public TextField name;
    public TextField origin;
    public TextField destination;
    public TextField description;
    public TextField distance;

    public Button modifyTour; //or addTour?

    TourItem currentItem;
    private JavaAppManager manager;
    private ObservableList<TourItem> tourItems;
    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    public void initData(TourItem currentItem, JavaAppManager manager, ObservableList<TourItem> tourItems) {
        this.currentItem = currentItem;
        this.manager = manager;
        this.tourItems = tourItems;
    }

    public void editTour(ActionEvent actionEvent) throws SQLException {
        //TODO: fill the textfields with the current values automatically?
        //use -1 to signify that this id shouldn't actually be used in the db, it's just a placeholder
        //or just pass the parameters instead?
        //maybe use the correct id so you can reuse it when adding the item or generally only need one parameter?
        TourItem modifiedItem = new TourItem(currentItem.getId(), name.getText(), origin.getText(), destination.getText(), description.getText(), Double.parseDouble(distance.getText()));
        manager.EditTourItem(currentItem.getId(), modifiedItem);
        tourItems.remove(currentItem);
        tourItems.add(modifiedItem);
        logger.info("Tour has been modified");
        //TODO: working correctly (technically, but it doesn't immediately get update in the list view. use tourItems to remove and re-add?)
        //TODO: also, make the list views show multiple colums already.

    }

}

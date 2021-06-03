package viewmodels;

import businesslayer.JavaAppManager;
import businesslayer.MapQuest;
import businesslayer.NameGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Getter;
import models.TourItem;
import models.TourLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MainWindowController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MainWindowViewModel {

    @Getter private final StringProperty searchField = new SimpleStringProperty("");

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    public void search(ObservableList<TourItem> tourItems, JavaAppManager manager/*, TextField searchField*/) throws SQLException {
        tourItems.clear();

        List<TourItem> items = manager.Search(searchField/*.textProperty()*/.get(), false);
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
        logger.info("Item has been randomly generated.");
    }

    public void generateLog(ObservableList<TourLog> tourLogs, JavaAppManager manager, TourItem currentItem) throws SQLException {
        TourLog genLog = manager.CreateTourLog(currentItem, LocalDateTime.now(), NameGenerator.GenerateName(4), 4.0, NameGenerator.GenerateName(4), 4, NameGenerator.GenerateName(4), NameGenerator.GenerateName(4), 4, 4, NameGenerator.GenerateName(4));
        tourLogs.add(genLog); //added
        logger.info("Log has been randomly generated.");
    }

    public void removeTour(TourItem currentItem, JavaAppManager manager, ObservableList<TourItem> tourItems) throws SQLException {
        manager.RemoveTourItem(currentItem.getId()); //remove from db
        tourItems.remove(currentItem); //remove from view
        logger.info("Tour has been removed.");
    }

    public void removeLog(TourLog currentLog, JavaAppManager manager, ObservableList<TourLog> tourLogs) throws SQLException {
        manager.RemoveLog(currentLog.getId()); //remove from db
        tourLogs.remove(currentLog); //remove from view
        logger.info("Log has been removed.");
    }

    public void copyTour(JavaAppManager manager, ObservableList<TourItem> tourItems, TourItem currentItem) throws SQLException {
        TourItem copiedItem = manager.CreateTourItem(currentItem.getName(), currentItem.getOrigin(), currentItem.getDestination(), currentItem.getDescription(), currentItem.getDistance());
        tourItems.add(copiedItem);
        logger.info("Tour has been duplicated.");
    }

    public void copyLog(JavaAppManager manager, ObservableList<TourLog> tourLogs, TourLog currentLog) throws SQLException {
        TourLog copiedLog = manager.CreateTourLog(currentLog.getLogTourItem(), currentLog.getCreationTime(), currentLog.getReport(), currentLog.getDistance(), currentLog.getTotalTime(), currentLog.getRating(), currentLog.getVehicleType(), currentLog.getAverageSpeed(), currentLog.getHorsepower(), currentLog.getJoule(), currentLog.getDescription());
        tourLogs.add(copiedLog);
        logger.info("Log has been duplicated.");
    }

    public void previewImage(TourItem currentItem) throws IOException {
        logger.info("Initiated image generation.");
        Stage stage = new Stage();
        String imagePath = MapQuest.createStaticMapImage(currentItem);
        InputStream stream = new FileInputStream(imagePath);
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        //imageView.setFitWidth(600); //optional
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());
        Group root = new Group(imageView);
        Scene scene = new Scene(root);
        stage.setTitle("Tour map");
        stage.setScene(scene);
        stage.show();
        logger.info("Image has been generated.");
    }
}

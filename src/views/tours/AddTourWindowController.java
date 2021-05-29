package views.tours;

import businesslayer.JavaAppManager;
import businesslayer.JavaAppManagerFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import models.TourItem;
import views.MainWindowController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddTourWindowController implements Initializable {

    public TextField name;
    public TextField origin;
    public TextField destination;
    public TextField description;
    public TextField distance;

    public Button submitTour; //or addTour?

    private JavaAppManager manager;

    private ObservableList<TourItem> tourItems;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //manager = JavaAppManagerFactory.GetManager();
        //tourItems = FXCollections.observableArrayList();
        //tourItems.addAll(manager.GetItems());
    }

    public void initData(JavaAppManager manager, ObservableList<TourItem> tourItems) {
        this.manager = manager;
        this.tourItems = tourItems;
    }

    //TODO: get distance from API

    public void addTour(ActionEvent actionEvent) throws SQLException {
        double distanceValue = 0;
        if (!distance.getText().isEmpty()) {
            distanceValue = Double.parseDouble(distance.getText());
        }
        TourItem addedItem = manager.CreateTourItem(name.getText(), origin.getText(), destination.getText(), description.getText(), distanceValue);
        tourItems.add(addedItem);
        //TODO I should return the parameters and Create the item with the manager in the mwc, right?

        //tourItems.add(genItem); //TODO: get touritems from mwc? otherwise its obviously null because it hasn't been initialized
        //TourItem genItem = manager.CreateTourItem("test", "test", "test", "test", (int)(1));
        //TourItem genItem = new TourItem(name.getText(), origin.getText(), destination.getText(), description.getText(), Double.parseDouble(distance.getText()));
        /*System.out.println(name.getText());
        System.out.println(origin.getText());
        System.out.println(destination.getText());
        System.out.println(description.getText());
        System.out.println(Double.parseDouble(distance.getText()));*/

    }

    //separate function for additional attributes

}

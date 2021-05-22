package views.tours;

import businesslayer.JavaAppManager;
import businesslayer.JavaAppManagerFactory;
import businesslayer.NameGenerator;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.TourItem;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddTourWindowController implements Initializable {
    private JavaAppManager manager;

    public TextField name;
    public TextField origin;
    public TextField destination;
    public TextField description;
    public TextField distance;

    public Button submitTour; //or addTour?

    private ObservableList<TourItem> tourItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager = JavaAppManagerFactory.GetManager();
    }

    public void addTour() throws SQLException {
        TourItem genItem = manager.CreateTourItem(name.toString(), NameGenerator.GenerateName(8), LocalDateTime.now());
        tourItems.add(genItem);
    }

}

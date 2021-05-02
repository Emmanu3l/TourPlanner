package views;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Button search;

    public Button addTour;
    public Button removeTour;
    public Button modifyTour;

    public Button addLog;
    public Button removeLog;
    public Button modifyLog;

    //public ListView<TourItem> listTourItems;


    public void searchAction() {
        search.setText("Results found");
    }


    public void addLogAction() {
        System.out.println("pressed");
    }

    public void removeLogAction() {
        System.out.println("pressed");
    }

    public void modifyLogAction() {
        System.out.println("pressed");
    }


    public void addTourAction() {
        System.out.println("pressed");
    }

    public void removeTourAction() {
        System.out.println("pressed");
    }

    public void modifyTourAction() {
        System.out.println("pressed");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

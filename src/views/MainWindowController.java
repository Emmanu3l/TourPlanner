package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import models.TourItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Button search;
    public Button clear;

    public Button addTour;
    public Button removeTour;
    public Button modifyTour;

    public Button addLog;
    public Button removeLog;
    public Button modifyLog;

    public ListView<TourItem> listTourItems;

    private ObservableList<TourItem> tourItems;
    private TourItem currentItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourItems = FXCollections.observableArrayList();
        tourItems.addAll();
        listTourItems.setItems(tourItems);

        // format cells to show name
        listTourItems.setCellFactory((param -> new ListCell<TourItem>() {
            @Override
            protected void updateItem(TourItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || (item == null) || (item.getName() == null)) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        }));

        listTourItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((newValue != null) && (oldValue != newValue)) {
                currentItem = newValue;
            }
        }));
    }

    public void searchAction() {
        search.setText("Results found");
    }

    public void clearAction(ActionEvent actionEvent) {
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


}

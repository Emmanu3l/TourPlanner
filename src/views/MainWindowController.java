package views;

import businesslayer.JavaAppManager;
import businesslayer.JavaAppManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.TourItem;

import java.net.URL;
import java.util.List;
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

    public TextField searchField;
    public ListView<TourItem> listTourItems;

    private ObservableList<TourItem> tourItems;
    private TourItem currentItem;
    private JavaAppManager manager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager = JavaAppManagerFactory.GetManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();

    }

    private void SetupListView() {
        tourItems = FXCollections.observableArrayList();
        tourItems.addAll(manager.GetItems());
        listTourItems.setItems(tourItems);
    }

    private void FormatCells() {
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
    }

    private void SetCurrentItem() {
        listTourItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((newValue != null) && (oldValue != newValue)) {
                currentItem = newValue;
            }
        }));
    }

    public void searchAction() {
        tourItems.clear();

        List<TourItem> items = manager.Search(searchField.textProperty().getValue(), false);
        tourItems.addAll(items);
        //search.setText("Results found");
    }

    public void clearAction(ActionEvent actionEvent) {
        tourItems.clear();
        searchField.textProperty().setValue("");

        List<TourItem> items = manager.GetItems();
        tourItems.addAll(items);
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

package views;

import businesslayer.JavaAppManager;
import businesslayer.JavaAppManagerFactory;
import businesslayer.NameGenerator;
import dataaccesslayer.common.DALFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import models.TourItem;
import models.TourLog;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager = JavaAppManagerFactory.GetManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();

    }

    private void SetupListView() throws SQLException {
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

    public void searchAction() throws SQLException {
        tourItems.clear();

        List<TourItem> items = manager.Search(searchField.textProperty().getValue(), false);
        tourItems.addAll(items);
        //search.setText("Results found");
    }

    public void clearAction(ActionEvent actionEvent) throws SQLException {
        tourItems.clear();
        searchField.textProperty().setValue("");

        List<TourItem> items = manager.GetItems();
        tourItems.addAll(items);
    }

    public void genItemAction(ActionEvent actionEvent) throws SQLException {
        TourItem genItem = manager.CreateTourItem(NameGenerator.GenerateName(4), NameGenerator.GenerateName(8), LocalDateTime.now());
        tourItems.add(genItem);
    }

    public void genLogAction(ActionEvent actionEvent) throws SQLException {
        TourLog genLog = manager.CreateTourLog(NameGenerator.GenerateName(40), currentItem);
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

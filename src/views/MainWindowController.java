package views;

import businesslayer.JavaAppManager;
import businesslayer.JavaAppManagerFactory;
import businesslayer.NameGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import models.TourItem;
import models.TourLog;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    //TODO: allow manually adding, removing and editing logs and tours
    //TODO: implement at least 20 unit tests (JUnit)
    //TODO: HTTP for MapQuest
    //TODO: Logginglog4j
    //TODO: report-generation library of choice
    //TODO: documentation annotation/generation with doxygen [OPTIONAL]

    //TODO: [NameGenerator.java] possibly remove single and three letter syllables so you can guarantee a certain length
    //TODO: [Database.java] ADD CREATE, DROP, SELECT STATEMENTS TO DAO CLASSES AND MAKE THEM ACCESSIBLE AS A FUNCTION
    //TODO: add dependencies to git repository?
    //TODO: https://youtu.be/jPKxqc8Zg-0

    //TODO: für morgen:
    // configuration: da scheint config.properties zu reichen
    // deployment: siehe launch4j

    //TODO: Crud für TourItem und TourLog loggen, siehe Checkliste

    //TODO: I misinterpreted the given UI. The section in the middle is for displaying the selected tour.
    // Therefore I should have a pop-up window whenever I click the "+" button in the Tour or Log section.
    // Furthermore, when I click the "-" button the currently selected Tour or Log should be deleted
    // and when clicking "[]" a pop-up for editing the Tour or Log should open

    //TODO: implement MapQuest API

    //TODO: jar to exe mit jlink als unique feature

    //TODO: add directory for images etc. to the config.properties

    public Button search;
    public Button clear;

    public Button addTour;
    public Button removeTour;
    public Button modifyTour;

    public Button addLog;
    public Button removeLog;
    public Button modifyLog;
    public Button genLog;

    public TextField searchField;
    public ListView<TourItem> listTourItems;
    public ListView<TourLog> listTourLogs; //added
    public VBox previewSelectedTour;

    private ObservableList<TourItem> tourItems;
    private ObservableList<TourLog> tourLogs; //added
    private TourItem currentItem;
    private JavaAppManager manager;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager = JavaAppManagerFactory.GetManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();

        // log sollte nur generierbar sein wenn ein item ausgewählt ist
        genLog.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        previewSelectedTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull()); //TODO
    }

    private void SetupListView() throws SQLException {
        tourItems = FXCollections.observableArrayList();
        tourItems.addAll(manager.GetItems());
        listTourItems.setItems(tourItems);

        //added:
        tourLogs = FXCollections.observableArrayList();
        tourLogs.addAll(manager.GetLogs()); //TODO: how to get items?
        listTourLogs.setItems(tourLogs); //Cannot invoke "javafx.scene.control.ListView.setItems(javafx.collections.ObservableList)" because "this.listLogItems" is null
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

        //added
        listTourLogs.setCellFactory((param -> new ListCell<TourLog>() {
            @Override
            protected void updateItem(TourLog log, boolean empty) {
                super.updateItem(log, empty);

                if (empty || (log == null) || (log.getReport() == null)) {
                    setText(null);
                } else {
                    setText(log.getReport());
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
        tourLogs.add(genLog); //added
    }

    public void addLogAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logs/addLogWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // so the main window can't be used while this pop-up is open
            stage.setTitle("Add Log");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeLogAction() {
        System.out.println("pressed");
    }

    public void modifyLogAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logs/editLogWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // so the main window can't be used while this pop-up is open
            stage.setTitle("Edit Log");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTourAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tours/addTourWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // so the main window can't be used while this pop-up is open
            stage.setTitle("Add Tour");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTourAction() {
        System.out.println("pressed");
    }

    public void modifyTourAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tours/editTourWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // so the main window can't be used while this pop-up is open
            stage.setTitle("Edit Tour");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

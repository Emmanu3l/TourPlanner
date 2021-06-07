package views;

import businesslayer.ConfigurationManager;
import businesslayer.ImportExport.Export;
import businesslayer.ImportExport.Import;
import businesslayer.JavaAppManager;
import businesslayer.JavaAppManagerFactory;
import businesslayer.PDFGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.TourItem;
import models.TourLog;
import viewmodels.MainWindowViewModel;
import viewmodels.tours.AddTourViewModel;
import views.logs.AddLogWindowController;
import views.logs.EditLogWindowController;
import views.tours.AddTourWindowController;
import views.tours.EditTourWindowController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    //TODO: the tour preview that gets shown when you select it should get the appropriate image from the path. how to identify it?

    // optionally - from maven: org.apache.logging.log4j:log4j-1.2-api:2.14.1
    //TODO: falls du log4j from maven nutzt, lösch log4j aus dem lib folder


    //01.06.2021
    //TODO: information von MapQuest in die Attribute einfügen bei set image (distance etc.)!
    //TODO: bei edit alte values inserten
    //TODO: required fields mit stern kennzeichnen und programmintern input validation anwenden
    //TODO: log sollte auch daten von mapquest oder von der tour nehmen
    //TODO: export import in json? ist das pflicht?
    //TODO: preset values für ratings und vehicle type inkl. drop down
    //TODO: reports erstellen
    //TODO: Unit tests!
    //TODO: progress bar wenn foto geöffnet wird
    //TODO: distance sollte (aber muss nicht) über mapquest geholt werden
    //TODO: theoretisch muss mapquest nur als bild download tool verwendet werden
    //TODO: sollte der search komplexer werden?
    //TODO: json import/export wäre nicht schlecht, am besten dann über das file menu
    //TODO: daten in html verwandeln und dann in pdf einfügen?
    //TODO: validation: zb bei distance nur integer erlauben
    //TODO: cascading delete wenn man eine tour löscht und es dafür noch logs gibt

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

    public Button copyTour;
    public Button copyLog;

    public VBox previewCurrentItem; //TODO: use this instead of having a separate window for editing tours, hier vielleicht auch ein image preview verwenden, auch ein log preview rechts von der liste hinzufügen
    public Label previewTitle;
    public Label previewRoute;
    public Label previewDescription;
    public Label previewDistance;
    public ImageView previewImage;
    public Button generateImage;
    public Label previewId;
    public MenuItem report;

    private ObservableList<TourItem> tourItems;
    private ObservableList<TourLog> tourLogs; //added
    private TourItem currentItem;
    private JavaAppManager manager;

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    private TourLog currentLog;

    public MainWindowViewModel viewModel = new MainWindowViewModel();

    public void BindViewModel() {
        searchField.textProperty().bindBidirectional(viewModel.getSearchField());
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logger.info("MainWindow has been initialized.");

        manager = JavaAppManagerFactory.GetManager();

        SetupListView();
        FormatCells();
        SetCurrentItem();
        SetCurrentLog();
        BindViewModel();
        //PreviewCurrentItem();

        PDFGenerator.generatePDF(tourItems.size(), tourLogs.size());

        // log sollte nur generierbar sein wenn ein item ausgewählt ist
        genLog.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        addLog.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        previewCurrentItem.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        removeTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        modifyTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        copyTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        generateImage.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());

        modifyLog.disableProperty().bind(listTourLogs.getSelectionModel().selectedItemProperty().isNull());
        removeLog.disableProperty().bind(listTourLogs.getSelectionModel().selectedItemProperty().isNull());
        copyLog.disableProperty().bind(listTourLogs.getSelectionModel().selectedItemProperty().isNull());

        report.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());

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

    /*public void generateImage(TourItem tourItem) throws IOException {
        MapQuest.createStaticMapImage(tourItem);
    }*/

    private void FormatCells() {
        // format cells to show name
        listTourItems.setCellFactory((param -> new ListCell<TourItem>() {
            @Override
            protected void updateItem(TourItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || (item == null) || (item.getName() == null)) {
                    setText(null);
                } else {
                    setText(item.getName()); //originally, there was only this
                    //the following only overrides the last value, not set multiple values
                    /*setText(item.getId().toString());
                    setText(item.getOrigin());
                    setText(item.getDestination());
                    setText(item.getDescription());
                    setText(item.getDistance() + "");*/
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
                previewTitle.setText(currentItem.getName());
                previewRoute.setText("Von " + currentItem.getOrigin() + " nach " + currentItem.getDestination());
                previewDescription.setText(currentItem.getDescription());
                previewDistance.setText(currentItem.getDistance() + "");
                previewId.setText(currentItem.getId().toString());
            }
        }));
    }

    /*private void PreviewCurrentItem() {
        //perhaps i should just call this function inside of setcurrentitem?
        listTourItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((newValue != null) && (oldValue != newValue)) {
                previewTitle.setText(currentItem.getName());
                previewRoute.setText("Von " + currentItem.getOrigin() + " nach " + currentItem.getDestination());
                previewDescription.setText(currentItem.getDescription());
                previewDistance.setText(currentItem.getDistance() + "");
                previewId.setText(currentItem.getId().toString());
            }
        }));
    }*/


    private void SetCurrentLog() {
        listTourLogs.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((newValue != null) && (oldValue != newValue)) {
                currentLog = newValue;
            }
        }));
    }

    public void searchAction() throws SQLException {
        viewModel.search(tourItems, manager/*, searchField*/);
    }

    public void clearAction(ActionEvent actionEvent) throws SQLException {
        viewModel.clear(tourItems, manager, searchField);
    }

    public void genItemAction(ActionEvent actionEvent) throws SQLException {
        //TourItem genItem = manager.CreateTourItem(NameGenerator.GenerateName(4), NameGenerator.GenerateName(8), LocalDateTime.now());
        viewModel.generateItem(tourItems, manager);
        //generateImage(genItem);
    }

    public void genLogAction(ActionEvent actionEvent) throws SQLException {
        //TourLog genLog = manager.CreateTourLog(NameGenerator.GenerateName(40), currentItem);
        viewModel.generateLog(tourLogs, manager, currentItem);
    }

    public void addLogAction() {
        try {
            AddLogWindowController addLogWindowController = loadFXML("logs/addLogWindow.fxml", "Add Log").getController();
            addLogWindowController.initData(manager, tourLogs, currentItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Log window has been opened.");
    }

    public void removeLogAction() throws SQLException {
        viewModel.removeLog(currentLog, manager, tourLogs);
    }

    public void modifyLogAction(ActionEvent actionEvent) {
        try {
            /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logs/editLogWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // so the main window can't be used while this pop-up is open
            stage.setTitle("Edit Log");
            stage.setScene(new Scene(root1));
            stage.show();*/
            EditLogWindowController editLogWindowController = loadFXML("logs/editLogWindow.fxml", "Edit Log").getController();
            editLogWindowController.initData(currentLog, manager, tourLogs);
            //TourItem addedItem = addTourWindowController.addTour(actionEvent);
            //TODO: remove old and add new
            //tourLogs.add(editLogWindowController.editLog(actionEvent));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Modify log window has been opened.");
    }

    public void addTourAction(ActionEvent actionEvent) {
        try {
            AddTourWindowController addTourWindowController = loadFXML("tours/addTourWindow.fxml", "Add Tour").getController();
            addTourWindowController.initData(manager, tourItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Tour window has been opened.");
    }

    public void removeTourAction() throws SQLException {
        viewModel.removeTour(currentItem, manager, tourItems);
    }

    public void modifyTourAction() {
        /*try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tours/editTourWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // so the main window can't be used while this pop-up is open
            stage.setTitle("Edit Tour");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            EditTourWindowController editTourWindowController = loadFXML("tours/editTourWindow.fxml", "Edit Tour").getController();
            editTourWindowController.initData(currentItem, manager, tourItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Modify tour window has been opened.");
    }

    public FXMLLoader loadFXML(String fxmlName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // so the main window can't be used while this pop-up is open
        stage.setTitle(title);
        stage.setScene(new Scene(root1));
        stage.show();
        return fxmlLoader;
    }

    public void copyTourAction(ActionEvent actionEvent) throws SQLException {
        viewModel.copyTour(manager, tourItems, currentItem);
    }

    public void copyLogAction(ActionEvent actionEvent) throws SQLException {
        viewModel.copyLog(manager, tourLogs, currentLog);
    }

    public void setPreviewImage() throws IOException {
        viewModel.previewImage(currentItem);
    }

    public void importAction(ActionEvent actionEvent) throws IOException, SQLException {
        viewModel.importFile(manager, tourItems);
        //tourItems.addAll(importedTours);

    }

    public void exportAction(ActionEvent actionEvent) throws IOException {
        //Export.exportTour(listTourItems.getItems());
        Export.exportTours(listTourItems.getItems());
        logger.info("Tour(s) have been exported.");
    }

    public void reportAction(ActionEvent actionEvent) {
        //viewModel.reportTour(manager, currentItem);
        PDFGenerator.tourPDF(currentItem);
        logger.info("Tour report has been generated.");
    }
}

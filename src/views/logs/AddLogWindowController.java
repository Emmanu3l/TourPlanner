package views.logs;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.TourItem;
import models.TourLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MainWindowController;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class AddLogWindowController {
    //du wirst dann den parameter LocalDateTime.now() für die creationTime brauchen, siehe genItem (auskommentiert)

    public TextField logTourItem;
    public TextField creationTime;
    public TextField report;
    public TextField distance;
    public TextField totalTime;
    public TextField rating;

    public TextField vehicleType;
    public TextField averageSpeed;
    public TextField horsepower;
    public TextField joule;
    public TextField description;

    public Button submitLog;

    private JavaAppManager manager;
    private ObservableList<TourLog> tourLogs;
    private TourItem currentItem;

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    //TODO: creation time und log tour items sollten unveränderlich sein, aber trotzdem in der ansicht angezeigt werden

    public void initData(JavaAppManager manager, ObservableList<TourLog> tourLogs, TourItem currentItem) {
        this.manager = manager;
        this.tourLogs = tourLogs;
        this.currentItem = currentItem;
    }

    public void addLog(ActionEvent actionEvent) throws SQLException {
        //TourItem genLog = manager.CreateTourItem(name.getText(), origin.getText(), destination.getText(), description.getText(), Double.parseDouble(distance.getText()));
        //tourLogs.add(genLog);

        TourLog addedLog = manager.CreateTourLog(currentItem, LocalDateTime.now(), report.getText(), Double.parseDouble(distance.getText()), totalTime.getText(), Integer.parseInt(rating.getText()), vehicleType.getText(), averageSpeed.getText(), Integer.parseInt(horsepower.getText()), Integer.parseInt(joule.getText()), description.getText());
        tourLogs.add(addedLog);
        logger.info("Log has been added");
    }

}

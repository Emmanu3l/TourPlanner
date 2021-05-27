package views.logs;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.TourItem;

import java.sql.SQLException;

public class AddLogWindowController {
    //du wirst dann den parameter LocalDateTime.now() für die creationTime brauchen, siehe genItem (auskommentiert)

    private JavaAppManager manager;

    public TextField logTourItem;
    public TextField creationTime;
    public TextField report;
    public TextField distance;
    public TextField totalTime;
    public TextField rating;

    public Button submitTour;

    private ObservableList<TourItem> tourLogs;

    //TODO: creation time und log tour items sollten unveränderlich sein, aber trotzdem in der ansicht angezeigt werden

    public void addLog(ActionEvent actionEvent) throws SQLException {
        //TourItem genLog = manager.CreateTourItem(name.getText(), origin.getText(), destination.getText(), description.getText(), Double.parseDouble(distance.getText()));
        //tourLogs.add(genLog);
    }

}

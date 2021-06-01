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

public class EditLogWindowController {

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());
    public Button modifyLog;
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

    private TourLog currentLog;
    private JavaAppManager manager;
    private ObservableList<TourLog> tourLogs;

    public TourLog editLog(ActionEvent actionEvent) throws SQLException {

        TourLog modifiedLog = new TourLog(currentLog.getId(), currentLog.getLogTourItem(), currentLog.getCreationTime(), report.getText(), Double.parseDouble(distance.getText()), totalTime.getText(), Integer.parseInt(rating.getText()), vehicleType.getText(), averageSpeed.getText(), Integer.parseInt(horsepower.getText()), Integer.parseInt(joule.getText()), description.getText());

        manager.EditTourLog(modifiedLog);

        //TourLog modifiedLog
        tourLogs.remove(currentLog);
        tourLogs.add(modifiedLog);

        logger.info("Log has been modified");
        return null;
    }

    public void initData(TourLog currentLog, JavaAppManager manager, ObservableList<TourLog> tourLogs) {
        this.currentLog = currentLog;
        this.manager = manager;
        this.tourLogs = tourLogs;

        //logTourItem.setText(currentLog.);
        //creationTime.setText(currentLog.);
        report.setText(currentLog.getReport());
        distance.setText(currentLog.getDistance() + "");
        totalTime.setText(currentLog.getTotalTime());
        rating.setText(currentLog.getRating() + "");
        vehicleType.setText(currentLog.getVehicleType());
        averageSpeed.setText(currentLog.getAverageSpeed());
        horsepower.setText(currentLog.getHorsepower() + "");
        joule.setText(currentLog.getJoule() + "");
        description.setText(currentLog.getDescription());

    }
}

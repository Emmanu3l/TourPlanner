package viewmodels.logs;

import businesslayer.JavaAppManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import lombok.Getter;
import models.TourLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MainWindowController;

import java.sql.SQLException;

public class EditLogViewModel {

    @Getter private final StringProperty report = new SimpleStringProperty("");
    @Getter private final StringProperty distance = new SimpleStringProperty("");
    @Getter private final StringProperty totalTime = new SimpleStringProperty("");
    @Getter private final StringProperty rating = new SimpleStringProperty("");
    @Getter private final StringProperty vehicleType = new SimpleStringProperty("");
    @Getter private final StringProperty averageSpeed = new SimpleStringProperty("");
    @Getter private final StringProperty horsepower = new SimpleStringProperty("");
    @Getter private final StringProperty joule = new SimpleStringProperty("");
    @Getter private final StringProperty description = new SimpleStringProperty("");

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    public void editLog(TourLog currentLog, JavaAppManager manager, ObservableList<TourLog> tourLogs) throws SQLException {
        TourLog modifiedLog = new TourLog(currentLog.getId(), currentLog.getLogTourItem(), currentLog.getCreationTime(), report.get(), Double.parseDouble(distance.get()), totalTime.get(), Integer.parseInt(rating.get()), vehicleType.get(), averageSpeed.get(), Integer.parseInt(horsepower.get()), Integer.parseInt(joule.get()), description.get());
        manager.EditTourLog(modifiedLog);
        //TourLog modifiedLog
        tourLogs.remove(currentLog);
        tourLogs.add(modifiedLog);
        logger.info("Log has been modified");
    }

}

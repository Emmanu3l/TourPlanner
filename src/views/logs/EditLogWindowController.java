package views.logs;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import models.TourLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MainWindowController;

public class EditLogWindowController {

    static Logger logger = LogManager.getLogger(MainWindowController.class.getName());

    public TourLog editLog(ActionEvent actionEvent) {

        //TourLog modifiedLog

        logger.info("Log has been modified");
        return null;
    }

    public void initData(TourLog currentLog, JavaAppManager manager, ObservableList<TourLog> tourLogs) {
    }
}

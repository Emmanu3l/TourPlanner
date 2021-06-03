package views.logs;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.TourItem;
import models.TourLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import viewmodels.logs.EditLogViewModel;
import views.MainWindowController;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditLogWindowController implements Initializable {

    public Button modifyLog;

    //public TextField logTourItem;
    //public TextField creationTime;
    public Label logTourItem;
    public Label creationTime;

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

    EditLogViewModel viewModel = new EditLogViewModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BindViewModel();
    }

    private void BindViewModel() {
        report.textProperty().bindBidirectional(viewModel.getReport());
        distance.textProperty().bindBidirectional(viewModel.getDistance());
        totalTime.textProperty().bindBidirectional(viewModel.getTotalTime());
        rating.textProperty().bindBidirectional(viewModel.getRating());
        vehicleType.textProperty().bindBidirectional(viewModel.getVehicleType());
        averageSpeed.textProperty().bindBidirectional(viewModel.getAverageSpeed());
        horsepower.textProperty().bindBidirectional(viewModel.getHorsepower());
        joule.textProperty().bindBidirectional(viewModel.getJoule());
        description.textProperty().bindBidirectional(viewModel.getDescription());
    }

    public void editLog(ActionEvent actionEvent) throws SQLException {
        viewModel.editLog(currentLog, manager, tourLogs);
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

        this.creationTime.setText(currentLog.getCreationTime().toString());

    }

}

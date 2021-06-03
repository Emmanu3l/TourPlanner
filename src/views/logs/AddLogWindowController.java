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
import viewmodels.logs.AddLogViewModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLogWindowController implements Initializable {
    //du wirst dann den parameter LocalDateTime.now() für die creationTime brauchen, siehe genItem (auskommentiert)

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

    public Button submitLog;

    private JavaAppManager manager;
    private ObservableList<TourLog> tourLogs;
    private TourItem currentItem;

    public AddLogViewModel viewModel = new AddLogViewModel();


    //TODO: creation time und log tour items sollten unveränderlich sein, aber trotzdem in der ansicht angezeigt werden

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

    public void initData(JavaAppManager manager, ObservableList<TourLog> tourLogs, TourItem currentItem) {
        this.manager = manager;
        this.tourLogs = tourLogs;
        this.currentItem = currentItem;

        this.logTourItem.setText(currentItem.getId().toString());
    }

    public void addLog(ActionEvent actionEvent) throws SQLException {
        //TourItem genLog = manager.CreateTourItem(name.getText(), origin.getText(), destination.getText(), description.getText(), Double.parseDouble(distance.getText()));
        //tourLogs.add(genLog);
        viewModel.addLog(manager, currentItem, tourLogs);

    }

}

package views.tours;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.TourItem;
import viewmodels.tours.AddTourViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddTourWindowController implements Initializable {

    public TextField name;
    public TextField origin;
    public TextField destination;
    public TextField description;
    public TextField distance;

    public Button submitTour; //or addTour?

    private JavaAppManager manager;

    private ObservableList<TourItem> tourItems;

    public AddTourViewModel viewModel = new AddTourViewModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BindViewModel();
    }

    private void BindViewModel() {
        name.textProperty().bindBidirectional(viewModel.getName());
        origin.textProperty().bindBidirectional(viewModel.getOrigin());
        destination.textProperty().bindBidirectional(viewModel.getDestination());
        description.textProperty().bindBidirectional(viewModel.getDescription());
        distance.textProperty().bindBidirectional(viewModel.getDistance());
    }

    public void initData(JavaAppManager manager, ObservableList<TourItem> tourItems) {
        this.manager = manager;
        this.tourItems = tourItems;
    }

    //TODO: get distance from API
    //TODO: move stuff into viewmodel?

    public void addTour(ActionEvent actionEvent) throws SQLException, IOException {
        viewModel.addTour(manager, tourItems);
    }

}

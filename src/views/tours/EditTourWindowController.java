package views.tours;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.TourItem;
import viewmodels.tours.EditTourViewModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditTourWindowController implements Initializable {

    public TextField name;
    public TextField origin;
    public TextField destination;
    public TextField description;
    public TextField distance;

    public Button modifyTour; //or addTour?

    TourItem currentItem;
    private JavaAppManager manager;
    private ObservableList<TourItem> tourItems;

    public EditTourViewModel viewModel = new EditTourViewModel();

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

    public void initData(TourItem currentItem, JavaAppManager manager, ObservableList<TourItem> tourItems) {
        this.currentItem = currentItem;
        this.manager = manager;
        this.tourItems = tourItems;

        name.setText(currentItem.getName());
        origin.setText(currentItem.getOrigin());
        destination.setText(currentItem.getDestination());
        description.setText(currentItem.getDescription());
        distance.setText(currentItem.getDistance() + "");
    }

    public void editTour(ActionEvent actionEvent) throws SQLException {
        viewModel.editTour(currentItem, manager, tourItems);
    }

}

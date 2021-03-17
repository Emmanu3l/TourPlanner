package sample;

import javafx.scene.control.Button;

public class Controller {

    public Button search;

    public Button addTour;
    public Button removeTour;
    public Button modifyTour;

    public Button addLog;
    public Button removeLog;
    public Button modifyLog;


    public void handleSearch() {
        search.setText("Results found");
    }


    public void handleAddLog() {
        System.out.println("pressed");
    }

    public void handleRemoveLog() {
        System.out.println("pressed");
    }

    public void handleModifyLog() {
        System.out.println("pressed");
    }


    public void handleAddTour() {
        System.out.println("pressed");
    }

    public void handleRemoveTour() {
        System.out.println("pressed");
    }

    public void handleModifyTour() {
        System.out.println("pressed");
    }

}

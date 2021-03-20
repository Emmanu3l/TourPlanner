package tourplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tour Planner");
        Parent root = FXMLLoader.load(getClass().getResource("tourplanner.fxml"));
        /*

        VBox leftMenu = new VBox();
        Button createTour = new Button("Create Tour");
        Button modifyTour = new Button("Modify Tour");
        Button deleteTour = new Button("Delete Tour");
        Button copyTour = new Button("Copy Tour");
        leftMenu.getChildren().addAll(createTour, modifyTour, deleteTour, copyTour);*/

        /*BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);*/

        primaryStage.setScene(new Scene(root, 1280, 720));
        //primaryStage.setScene(new Scene(grid, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

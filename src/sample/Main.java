package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tour Planner");
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        HBox topMenu = new HBox();
        Button fileButton = new Button("File");
        Button editButton = new Button("Edit");
        Button optionsButton = new Button("Options");
        Button helpButton = new Button("Help");
        topMenu.getChildren().addAll(fileButton, editButton, optionsButton, helpButton);

        VBox leftMenu = new VBox();
        Button createTour = new Button("Create Tour");
        Button modifyTour = new Button("Modify Tour");
        Button deleteTour = new Button("Delete Tour");
        Button copyTour = new Button("Copy Tour");
        leftMenu.getChildren().addAll(createTour, modifyTour, deleteTour, copyTour);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);

        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(borderPane, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

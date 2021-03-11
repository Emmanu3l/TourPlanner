package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tour Planner");
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8); //set vertical spacing
        grid.setHgap(10); //set horizontal spacing

        Label titleLabel = new Label("Title: ");
        GridPane.setConstraints(titleLabel, 0, 0);

        //TextField titleInput = new TextField("Name the tour");
        TextField titleInput = new TextField();
        titleInput.setPromptText("Name the tour");
        GridPane.setConstraints(titleInput, 1, 0);

        grid.getChildren().addAll(titleLabel, titleInput);

        /*HBox topMenu = new HBox();
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
        leftMenu.getChildren().addAll(createTour, modifyTour, deleteTour, copyTour);*/

        /*BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);*/

        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(grid, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

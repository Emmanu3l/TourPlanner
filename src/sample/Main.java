package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        button = new Button("Start"); //create button and set label
        button.setOnAction(this); //whenever the user clicks the button the code to handle it is in *this* class, instead of a separate one

        StackPane layout = new StackPane(); //simple layout
        layout.getChildren().add(button); //add button to layout

        primaryStage.setTitle("Tour Planner");
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(layout, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) { //handle user event
        if (actionEvent.getSource() == button) { //check which button was pressed
            System.out.println("test");
        }
    }
}

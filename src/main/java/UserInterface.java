package main.java;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Flower tamagotchi");
        primaryStage.setResizable(false);
        Group root = new Group();

        Scene scene = new Scene(root, 600, 400);

        int width = 500; // max hp
        GridPane hp = new GridPane();
        //hp.setPrefSize(width, 30);
        hp.getColumnConstraints().add(new ColumnConstraints(width*0.1)); // dead zone red
        hp.getColumnConstraints().add(new ColumnConstraints(width*0.55)); // normal zone yellow-green
        hp.getColumnConstraints().add(new ColumnConstraints(width*0.35)); // perfect zone green


        Rectangle deadzone = new Rectangle(width*0.1, 20, Color.rgb(255, 80, 80, 0.85));
        Rectangle normalzone = new Rectangle(width*0.55, 20, Color.rgb(204, 255, 51));
        Rectangle perfzone = new Rectangle(width*0.35, 20, Color.rgb(102, 255, 102));

        hp.add(deadzone,0,0);
        hp.add(normalzone,1,0);
        hp.add(perfzone,2,0);

        root.getChildren().add(hp);


        Text textStart = new Text();
        textStart.setY(100);
        Button stratButton = new Button("Start");
        stratButton.setAlignment(Pos.CENTER_LEFT);
        stratButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){
                textStart.setText(HumanBeing.satrtinfo());
            }
        });
        root.getChildren().add(textStart);
        root.getChildren().add(stratButton);
        Button newgameButton = new Button("New game");

        Text text = new Text();
        Button addButton = new Button("Add resources");
        Button checkButton = new Button("Check stata");
        checkButton.setOnMouseClicked(mouseEvent -> {text.setText("stats");});
        checkButton.setLayoutY(300);
        text.setY(400);
        root.getChildren().add(text);
        root.getChildren().add(checkButton);

        Button addWater = new Button("Wather");
        Button addFood = new Button("Food");
        Button addLove = new Button("Love");

        primaryStage.setScene(scene);
        scene.setFill(Color.rgb(0, 102, 102));
        primaryStage.show();

    }
}

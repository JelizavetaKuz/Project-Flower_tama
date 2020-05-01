import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
        String fileName ="flower.txt";

        primaryStage.setTitle("Flower tamagotchi");
        primaryStage.setResizable(false);
        Group root = new Group();

        Scene startScene = new Scene(root, 600, 400);
        //Scene mainScene = new Scene(root,600, 400);
        HBox addResources = new HBox();






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







        //PlayTamagotchi.startGame();
        Text textStart = new Text();
        textStart.setY(100);
        Button stratButton = new Button("Start");
        stratButton.setAlignment(Pos.CENTER_LEFT);
        stratButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){


                try {
                    if(FileConnection.fileCheck(fileName)==2) {
                        textStart.setText(HumanBeing.satrtinfo());
                        //Scan for name
                        //FileConnection.fileCreate(fileName,);
                    }
                    else {
                        if (FileConnection.fileCheck(fileName)==0) {
                            PlayTamagotchi.createflower(FileConnection.getFlowerName(fileName));
                            // Screen with other buttons
                        }
                        else {
                            textStart.setText("You can try once again!");
                            // Scan for name
                            //FileConnection.fileCreate(fileName, scanned");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().add(textStart);
        root.getChildren().add(stratButton);

        TextField setflowerName = new TextField();
        String flowerName;


        //not needed right now
        Button newgameButton = new Button("New game");
        newgameButton.setOnMouseClicked(mouseEvent -> {setflowerName.setPromptText("Flower name"); setflowerName.getText();});




        Text text = new Text();
        Button addButton = new Button("Add resources");



        Button checkButton = new Button("Check stata");
        checkButton.setOnMouseClicked(mouseEvent -> {
            try {
                Flower flower = PlayTamagotchi.createflower(FileConnection.getFlowerName(fileName));
                if (flower.getCurrenthp() <= 0)
                    text.setText(PlayTamagotchi.endGame(flower));
                else
                    text.setText(HumanBeing.checkstats(flower));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        checkButton.setLayoutY(300);
        text.setY(50);
        root.getChildren().add(text);
        root.getChildren().add(checkButton);





        Button addWater = new Button("Wather");
        Button addFood = new Button("Food");
        Button addLove = new Button("Love");





        primaryStage.getOnHiding();




        primaryStage.setScene(startScene);
        startScene.setFill(Color.rgb(0, 102, 102));
        primaryStage.show();

    }
}

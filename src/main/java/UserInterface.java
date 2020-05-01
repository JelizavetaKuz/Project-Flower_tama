import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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


        VBox startGame = new VBox();
        Scene startScene = new Scene(startGame, 600, 400);
        primaryStage.setScene(startScene);
        startScene.setFill(Color.rgb(0, 102, 102));


        Scene mainScene = new Scene(root,600, 400);

        VBox newgameBox = new VBox();
        Scene newgameScene = new Scene(newgameBox,600, 400);

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







        //PlayTamagotchi.startGame(); start scene
        Text textStart = new Text();
        textStart.setY(100);
        Button stratButton = new Button("Start");
        stratButton.setAlignment(Pos.CENTER_LEFT);
        stratButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){

                try {
                    if(FileConnection.fileCheck(fileName)==2) {
                        textStart.setText(HumanBeing.satrtinfo() +
                                "\nHow would you like to name your flower?");
                        //Scan for name
                        //FileConnection.fileCreate(fileName,);
                        primaryStage.setScene(newgameScene);
                    }
                    else {
                        if (FileConnection.fileCheck(fileName)==0) {
                            PlayTamagotchi.createflower(FileConnection.getFlowerName(fileName));
                            //obnovit koordinatu
                            primaryStage.setScene(mainScene);
                            // Screen with other buttons
                        }
                        else {
                            textStart.setText("You can try once again! \n How would you like to name your new flower?");
                            // Scan for name
                            //FileConnection.fileCreate(fileName, scanned");
                            primaryStage.setScene(newgameScene);
                        }
                    }
                } catch (IOException e) {
                    primaryStage.close();
                }
            }
        });
        newgameBox.getChildren().add(textStart);

        startGame.getChildren().add(stratButton);

        // newgame scene
        TextField setflowerName = new TextField();
        setflowerName.setPromptText("Flower name");
        setflowerName.setOnKeyPressed(new EventHandler<KeyEvent>(){

            public void handle(KeyEvent key){
                if(key.getCode().equals(KeyCode.ENTER)){
                    String flowerName = setflowerName.getText();
                    try {
                        FileConnection.fileCreate(fileName, flowerName);
                        primaryStage.setScene(mainScene);
                    } catch (IOException e) {
                        e.printStackTrace();
                        primaryStage.close();
                    }
                }
            }
        });

        newgameBox.getChildren().add(setflowerName);



        //not needed right now
        //Button newgameButton = new Button("New game");
        //newgameButton.setOnMouseClicked(mouseEvent -> {});




        // mainScene
        Text text = new Text();
        Button addButton = new Button("Add resources");



        // mainScene
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





        // new Scene...TODO new scene
        Button addWater = new Button("Wather");
        Button addFood = new Button("Food");
        Button addLove = new Button("Love");





        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {

                //Stage init
                final Stage dialog = new Stage();

                // Label
                Label label = new Label("Do you really want to quit?");

                Button okButton = new Button("Yes");
                okButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dialog.close();
                        try {
                            if (FileConnection.fileCheck(fileName)==0) {
                                PlayTamagotchi.createflower(FileConnection.getFlowerName(fileName));
                            }
                            primaryStage.close();
                        } catch (IOException e) {
                            primaryStage.close();
                        }
                    }
                });


                Button cancelButton = new Button("No");
                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        dialog.close();
                    }
                });
                FlowPane pane = new FlowPane(10, 10);
                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(okButton, cancelButton);

                // küsimuse ja nuppude gruppi paigutamine
                VBox vBox = new VBox(10);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(label, pane);

                //stseeni loomine ja näitamine
                Scene stseen2 = new Scene(vBox);
                dialog.setScene(stseen2);
                dialog.show();
            }
        });





        primaryStage.show();
    }
}

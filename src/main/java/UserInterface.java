package main.java;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import java.util.Stack;


public class UserInterface extends Application {
    Flower flower;
    int stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        String fileName ="flower.txt";

        primaryStage.setTitle("Flower tamagotchi");
        //primaryStage.setResizable(false);



        FlowPane startGame = new FlowPane();
        startGame.setAlignment(Pos.CENTER);
        Scene startScene = new Scene(startGame, 600, 400);

        //startScene.setFill(Color.rgb(0, 102, 102));
        primaryStage.setScene(startScene);
        BackgroundImage bg = new BackgroundImage(new Image("pikrepo.com.jpg",600,400,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        startGame.setBackground(new Background(bg));


        Group root = new Group();
        Scene mainScene = new Scene(root,600, 400);
        mainScene.setFill(Color.rgb(0, 102, 102));

        HBox newgameBox = new HBox();
        Scene newgameScene = new Scene(newgameBox,600, 400);
        newgameScene.setFill(Color.rgb(0, 102, 102));








        int width = 200; // max, hp from flower
        GridPane hp = new GridPane();
        //hp.setPrefSize(width, 30);
        hp.getColumnConstraints().add(new ColumnConstraints(width*0.1)); // dead zone red
        hp.getColumnConstraints().add(new ColumnConstraints(width*0.55)); // normal zone yellow-green
        hp.getColumnConstraints().add(new ColumnConstraints(width*0.35)); // perfect zone green


        Rectangle deadzone = new Rectangle(width*0.1, 20, Color.rgb(255, 80, 80, 0.85));
        Rectangle normalzone = new Rectangle(width*0.55, 20, Color.rgb(204, 255, 51));
        Rectangle perfzone = new Rectangle(width*0.35, 20, Color.rgb(102, 255, 102));
        Rectangle runner = new Rectangle(2, 20, Color.rgb(51, 26, 0, 0.75) );

        hp.add(deadzone,0,0);
        hp.add(normalzone,1,0);
        hp.add(perfzone,2,0);

        root.getChildren().add(hp);
        root.getChildren().add(runner);







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
                            flower = PlayTamagotchi.createflower(FileConnection.getFlowerName(fileName));
                            //current hp from flower
                            runner.setX(flower.getCurrenthp());
                            PlayTamagotchi.updateFile(fileName, flower);
                            primaryStage.setScene(mainScene);
                            stage = flower.getStage();
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
        mainScene.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FlowPane paneContain = new FlowPane();
                paneContain.setPrefSize(300,300);
                paneContain.setBackground(new Background(new BackgroundImage(new Image("container.png",300,300,false,true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
                //paneContain.setAlignment(Pos.BOTTOM_RIGHT);
                paneContain.setLayoutY(180);
                paneContain.setLayoutX(300);
                paneContain.setVisible(true);


                StackPane flowerPane = new StackPane();
                flowerPane.setPrefSize(300,300);

                if(stage == 1) {flowerPane.setBackground(new Background(new BackgroundImage(new Image("flower1.png",300,300,false,true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));flowerPane.setLayoutX(300) ; flowerPane.setLayoutY(140);}
                else if(stage == 2) {flowerPane.setBackground(new Background(new BackgroundImage(new Image("flower2.png",300,200,false,true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));flowerPane.setLayoutX(300) ; flowerPane.setLayoutY(150); }
                else if(stage == 3) {flowerPane.setBackground(new Background(new BackgroundImage(new Image("flower3.png",300,300,false,true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT))); flowerPane.setLayoutX(300) ; flowerPane.setLayoutY(50);}
                else if(stage == 4) {flowerPane.setBackground(new Background(new BackgroundImage(new Image("flower4.png",300,300,false,true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT))); flowerPane.setLayoutX(300) ; flowerPane.setLayoutY(50);}

                root.getChildren().addAll(paneContain, flowerPane);

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



        // new Scene...TODO new scene
        Button addWater = new Button("Wather");
        addWater.setOnMouseClicked(mouseEvent -> {
            HumanBeing.addWater(flower);
        });
        Button addFood = new Button("Food");
        addFood.setOnMouseClicked(mouseEvent -> {
            HumanBeing.addFood(flower);
        });
        Button addLove = new Button("Love");
        addLove.setOnMouseClicked(mouseEvent -> {
            HumanBeing.addLove(flower);
        });


        HBox addResources = new HBox();
        addResources.setLayoutY(220);
        addResources.setMinSize(50, 50);
        addResources.setAlignment(Pos.BOTTOM_LEFT);

        HBox addBox = new HBox();
        addBox.setAlignment(Pos.BOTTOM_LEFT);

        addBox.setVisible(false);
        Button addButton = new Button("Add resources");
        addButton.setAlignment(Pos.TOP_LEFT);
        addButton.setOnMouseClicked(mouseEvent -> {
            addBox.setVisible(true);
            try {
                PlayTamagotchi.updateFile(fileName,flower);
            } catch (IOException e) {
                primaryStage.close();
            }
        });
        addBox.setOnMouseExited(mouseEvent -> {addBox.setVisible(false);
            try {
                PlayTamagotchi.updateFile(fileName,flower);
            } catch (IOException e) {
                primaryStage.close();
            }
        });
        addBox.getChildren().addAll(addFood,addWater,addLove);
        addResources.getChildren().addAll(addButton, addBox);
        root.getChildren().add(addResources);



        // mainScene
        Text text = new Text();
        text.setY(100);
        text.setX(60);
        FlowPane fp = new FlowPane();
        fp.setVisible(false);
        fp.setAlignment(Pos.CENTER);
        fp.setLayoutY(30);

        fp.setPrefSize(300,200);
        BackgroundImage bgi = new BackgroundImage(new Image("background.jpg",300,300,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        fp.setBackground(new Background(bgi));

        Button checkButton = new Button("Check stata");
        checkButton.setOnMouseClicked(mouseEvent -> {
            try {
                if (flower.getCurrenthp() <= 0)
                    text.setText(PlayTamagotchi.endGame(flower));
                else{
                    PlayTamagotchi.updateFile(fileName,flower);
                    text.setText(HumanBeing.checkstats(flower));

                    fp.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        fp.setOnMouseClicked(mouseEvent -> fp.setVisible(false));
        fp.getChildren().add(text);
        checkButton.setLayoutY(280);
        root.getChildren().addAll(checkButton, fp);










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
                                FileConnection.fileWrite(fileName, flower);
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



//        try{
//        Image flower1 = new Image((new FileInputStream("flower1.jpg")));
//        Image flower2 = new Image((new FileInputStream("flower2.jpg")));
//        Image flower3 = new Image((new FileInputStream("flower3.jpg")));
//        Image flower4 = new Image((new FileInputStream("flower4.jpg")));
//        Image container = new Image((new FileInputStream("container.jpg")));
//        } catch (IOException e){
//            primaryStage.close();
//        }






        primaryStage.show();
    }
}

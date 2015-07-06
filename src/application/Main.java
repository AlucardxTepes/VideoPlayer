package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        VideoPlayer player = new VideoPlayer("file:///C:/eclipse/video.mp4");

        // add the player to the scene, set width and height according to video
        // and background color to black
        Scene scene = new Scene(player, 640, 360);
        // add scene to the stage and finally show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
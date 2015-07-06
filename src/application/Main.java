package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        VideoPlayer player = new VideoPlayer("file:///C:/eclipse/video.mp4");

        // add the player to the scene, set width and height according to video
        // Video Res is 640x360. Adding 30px Y axis to accomodate mediabar with controls
        Scene scene = new Scene(player, 640, 390);
        // add scene to the stage and finally show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
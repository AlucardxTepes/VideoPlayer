package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {

    VideoPlayer mPlayer;
    FileChooser mFileChooser;

    @Override
    public void start(final Stage primaryStage) {

        MenuItem openMenuItem = new MenuItem("Open");
        Menu fileMenu = new Menu("File");
        MenuBar menu = new MenuBar();

        fileMenu.getItems().add(openMenuItem);
        menu.getMenus().add(fileMenu);

        mFileChooser = new FileChooser();


        mPlayer = new VideoPlayer("file:///C:/eclipse/video.mp4");
        // add Menu bar at the top
        mPlayer.setTop(menu);

        // add the mPlayer to the scene, set width and height according to video
        // Video Res is 640x360. Adding 35px Y axis to accomodate mediabar with controls
        // and additional 25px for top menu
        Scene scene = new Scene(mPlayer, 640, 420);
        // add scene to the stage and finally show it
        primaryStage.setScene(scene);
        primaryStage.show();


        openMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mPlayer.player.pause();
                File file = mFileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        mPlayer = new VideoPlayer(file.toURI().toURL().toExternalForm());
                        Scene scene = new Scene(mPlayer, 640, 420);
                        primaryStage.setScene(scene);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
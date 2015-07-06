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
    MenuItem mOpenMenuItem, mExitMenuItem, mShowInfoMenuItem;
    Menu mFileMenu;
    MenuBar mMenu;
    Scene mScene;

    @Override
    public void start(final Stage primaryStage) {

        initPlayer(primaryStage, 640, 420);

    }

    private void initPlayer(Stage stage, double w, double h){
        // initialize menu
        initMenu(stage);
        // Create empty player window layout
        mPlayer = new VideoPlayer();
        // add Menu bar at the top
        mPlayer.setTop(mMenu);
        // add the mPlayer to the scene, set width and height according to video
        // Video Res is 640x360. Adding 35px to the Y axis to accommodate mediabar with controls
        // and additional 25px for top mMenu
        mScene = new Scene(mPlayer, w, h);
        // add scene to the stage and finally show it
        stage.setScene(mScene);
        stage.show();
    }

    private void initPlayer(Stage stage, File f) {
        if (f != null) {
            try {
                mPlayer = new VideoPlayer(f.toURI().toURL().toExternalForm());
//                Scene scene = new Scene(mPlayer, mPlayer.media.getWidth(), mPlayer.media.getHeight());
                mPlayer.setTop(mMenu);
                mScene.setRoot(mPlayer);
                stage.setScene(mScene);
                stage.show();
//                System.out.println("Video width: " + mPlayer.media.getWidth() + " Video Height: " + mPlayer.view.fitHeightProperty());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private void resizeWindow(Stage stage){
        stage.setWidth(mPlayer.media.getWidth());
        stage.setHeight(mPlayer.media.getHeight());
    }

    private void initMenu(Stage stage) {
        mOpenMenuItem = new MenuItem("Open");
        mShowInfoMenuItem = new MenuItem("Show Media Info");
        mExitMenuItem = new MenuItem("Exit");
        mFileMenu = new Menu("File");
        mMenu = new MenuBar();

        mFileMenu.getItems().add(mOpenMenuItem);
        mFileMenu.getItems().add(mShowInfoMenuItem);
        mFileMenu.getItems().add(mExitMenuItem);
        mMenu.getMenus().add(mFileMenu);

        mFileChooser = new FileChooser();
        // apply mp4 files filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Video files (*.mp4)", "*.mp4");
        mFileChooser.getExtensionFilters().add(extFilter);

        // set event listeners for menu items
        mOpenMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (mPlayer.player != null) {
                    // if theres a video playing
                    mPlayer.player.pause();
                }
                File file = mFileChooser.showOpenDialog(stage);
                initPlayer(stage, file);
            }
        });
        mShowInfoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Video width: " + mPlayer.media.getWidth() + " Video Height: " + mPlayer.media.getHeight());
//                resizeWindow(stage);
            }
        });
        mExitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                mPlayer.player.stop();
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
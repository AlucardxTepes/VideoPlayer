package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * Created by Alucard on 7/5/2015.
 */
public class VideoPlayer extends BorderPane {
    // to create media and play it you need 3 things
    Media media;
    MediaPlayer player;
    MediaView view;

    MediaBar bar; // custom class that extends JavaFX HBox
    Pane mediaPane; // mediaplayer container

    public VideoPlayer(String file, Stage stage){
        media = new Media(file);
        player = new MediaPlayer(media);
        view = new MediaView(player);
        // add view to the Pane
        mediaPane = new Pane();
        mediaPane.getChildren().add(view);
        // add pane to the center of this videoplayer(BorderPane)
        setCenter(mediaPane);
        // add mediabar controls and add it to the bottom of the pane
        bar = new MediaBar(player);
        setBottom(bar);
        setStyle("-fx-background-color: #bfc2c7"); // set HBox(mediabar) background color to gray
        // and finally play the video
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                System.out.println("Player ready, resizing to: " + media.getWidth() +
                                    " , " + media.getHeight());
                stage.setWidth(media.getWidth());
                stage.setHeight(media.getHeight() + 60);
            }
        });
        player.play();
    }

    public VideoPlayer(){
        mediaPane = new Pane();
        // add pane to the center of this videoplayer(BorderPane)
        setCenter(mediaPane);
        // add mediabar controls and add it to the bottom of the pane
        bar = new MediaBar();
        setBottom(bar);
        setStyle("-fx-background-color: #bfc2c7"); // set HBox(mediabar) background color to gray
    }

    public String showMediaInfo(){
        return "Video width: " + media.getWidth() + ", Video height: " + media.getHeight() +
                "\nDuration: " + media.getDuration();
    }

}

package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Created by Alucard on 7/5/2015.
 */
public class VideoPlayer extends BorderPane {
    // to create media and play it you need 3 things
    Media media;
    MediaPlayer player;
    MediaView view;

    Pane mediaPane; // mediaplayer container

    public VideoPlayer(String file){
        media = new Media(file);
        player = new MediaPlayer(media);
        view = new MediaView(player);
        // add view to the Pane
        mediaPane = new Pane();
        mediaPane.getChildren().add(view);
        // add pane to the center of this videoplayer(BorderPane)
        setCenter(mediaPane);
        // and finally play the video
        player.play();
    }
}

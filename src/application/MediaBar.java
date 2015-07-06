package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

/**
 * Created by Alucard on 05-Jul-15.
 */
public class MediaBar extends HBox { // HBox is a horizontal box part of JavaFX library

    // init controls
    Slider time = new Slider();
    Slider vol = new Slider();
    Label volume = new Label("Volume: ");
    Button playButton = new Button("||");

    MediaPlayer player;



    public MediaBar(MediaPlayer play){
        player = play;

        // setup styling
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,10,5,10));

        // set sizing
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100); // default volume value is 100

        HBox.setHgrow(time, Priority.ALWAYS);
        // prevent resizing for playbutton
        playButton.setPrefWidth(30);

        // add views to MediaBar, in right order
        getChildren().add(playButton);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);
    }
}

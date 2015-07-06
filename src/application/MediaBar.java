package application;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        setPadding(new Insets(5, 10, 5, 10));

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



        /************* EVENT LISTENERS **************/


        // playButton listener
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaPlayer.Status status = player.getStatus();

                if(status == MediaPlayer.Status.PLAYING){
                    // replay video if video has ended:
                    if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                        player.seek(player.getStartTime());
                        player.play();
                    } else {
                        // otherwise pause video normally
                        player.pause();
                        playButton.setText(">");
                    }
                }

                if(status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED){
                    player.play();
                    playButton.setText("||");
                }
            }
        });

        // time / navigation bar Listener for keeping track of video time position on slider
        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateValues();
            }
        });
        // time listener for jumping and skipping video time / slider position
        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (time.isPressed()) {
                    // jump to clicked position in time slider
                    player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
                }
            }
        });

        // volume slider listener
        vol.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (vol.isPressed()){
                    player.setVolume(vol.getValue()/100);
                }
            }
        });
    }

    // no arguments constructor
    public MediaBar(){
        // setup styling
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));

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

    private void updateValues() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Calculate video current position using formula:
                // current time / total time * 100
                time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
            }
        });

    }
}

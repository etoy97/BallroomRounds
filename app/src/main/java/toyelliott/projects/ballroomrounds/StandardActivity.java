package toyelliott.projects.ballroomrounds;


import android.widget.Button;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;


public class StandardActivity extends RoundsActivity{
    @Override
    protected Button getmButtonPlay() { return findViewById(R.id.standardPlayButton); }

    @Override
    protected Button getmButtonPause() { return findViewById(R.id.standardPauseButton); }

    @Override
    protected List<String> videos() {
        return Arrays.asList("B5lKqLmZ-bQ");
    }

    @Override
    protected void setTag() {
        this.TAG = "StandardRounds";
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_standard;
    }

    @Override
    protected YouTubePlayerSupportFragment getFrag() {
        return (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.YouTubePlayer);
    }
}

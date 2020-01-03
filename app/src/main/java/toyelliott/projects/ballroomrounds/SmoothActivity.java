package toyelliott.projects.ballroomrounds;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;


public class SmoothActivity extends RoundsActivity{
    @Override
    protected Button getmButtonPlay() { return findViewById(R.id.smoothPlayButton); }

    @Override
    protected Button getmButtonPause() { return findViewById(R.id.smoothPauseButton); }

    @Override
    protected List<String> videos() {
        return Arrays.asList("B5lKqLmZ-bQ");
    }

    @Override
    protected void setTag() {
        this.TAG = "SmoothRounds";
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_smooth;
    }

    @Override
    protected YouTubePlayerSupportFragment getFrag() {
        return (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.YouTubePlayer);
    }

}


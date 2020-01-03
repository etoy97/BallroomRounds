package toyelliott.projects.ballroomrounds;

import android.widget.Button;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;

public class LatinActivity extends RoundsActivity {
    @Override
    protected Button getmButtonPlay() { return findViewById(R.id.latinPlayButton); }

    @Override
    protected Button getmButtonPause() { return findViewById(R.id.latinPauseButton); }

    @Override
    protected List<String> videos() {
        return Arrays.asList("7gwBxKHBoEI", "W4hTJybfU7s");
    }

    @Override
    protected void setTag() {
        this.TAG = "LatinRounds";
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_latin;
    }

    @Override
    protected YouTubePlayerSupportFragment getFrag() {
        return (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.YouTubePlayer);
    }
}


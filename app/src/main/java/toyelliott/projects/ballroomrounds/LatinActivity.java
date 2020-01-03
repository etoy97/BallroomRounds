package toyelliott.projects.ballroomrounds;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;

public class LatinActivity extends RoundsActivity {
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


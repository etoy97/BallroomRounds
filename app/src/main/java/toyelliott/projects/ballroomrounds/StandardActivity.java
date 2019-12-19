package toyelliott.projects.ballroomrounds;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;


public class StandardActivity extends RoundsActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

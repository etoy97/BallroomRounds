package toyelliott.projects.ballroomrounds;

import android.content.Intent;
import android.util.Log;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;

public class LatinActivity extends RoundsActivity {
    @Override
    protected int getFadeSec() {
        Intent intent = getIntent();
        Integer fadeSec = intent.getIntExtra(ConfigureTime.fS, 5);
        Log.d(TAG,fadeSec.toString());
        return fadeSec;
    }

    @Override
    protected int getVideoLength() {
        Intent intent = getIntent();
        Integer videoLength = intent.getIntExtra(ConfigureTime.vL, 10)*1000;
        Log.d(TAG, videoLength.toString());
        return videoLength;
    }

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


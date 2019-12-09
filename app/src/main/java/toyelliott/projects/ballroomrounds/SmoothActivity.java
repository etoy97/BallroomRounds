package toyelliott.projects.ballroomrounds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class SmoothActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{

    private static final String TAG = "SmoothClass";

    YouTubePlayer video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooth);
        YouTubePlayerSupportFragment frag = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.YouTubePlayer);

        frag.initialize(YouTubeConfig.getAPI_KEY(), this);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        Log.d(TAG, "onClick: Done Initializing");

        video = player;
        video.loadVideo("B5lKqLmZ-bQ");
    }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
            Log.d(TAG, "onClick: Failed Initializing");
        }

    public void pressPlay (View view) {
        Log.d(TAG, "pressed play");
        video.play();
    }

    public void pressPause(View view) {
        Log.d(TAG, "pressed pause");
        video.pause();
    }
}


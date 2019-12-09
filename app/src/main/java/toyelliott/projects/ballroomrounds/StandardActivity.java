package toyelliott.projects.ballroomrounds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Timer;
import java.util.TimerTask;

public class StandardActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{

    private static final String TAG = "StandardClass";

    YouTubePlayer video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        YouTubePlayerSupportFragment frag = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.YouTubePlayer);

        frag.initialize(YouTubeConfig.getAPI_KEY(), this);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                video.pause();
            }
        }, 10000);
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


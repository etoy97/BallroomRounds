package toyelliott.projects.ballroomrounds;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class StandardActivty extends YouTubeBaseActivity {

    private static final String TAG = "StandardClass";

    YouTubePlayerView ytPlayer;
    YouTubePlayer.OnInitializedListener ytListener;
    YouTubePlayer video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        Log.d(TAG, "onCreate: Starting.");
        ytPlayer = findViewById(R.id.YouTubePlayer);

        ytListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Done Initializing");

                video = youTubePlayer;
                video.loadVideo("B5lKqLmZ-bQ");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Failed Initializing");
            }
        };

        ytPlayer.initialize(YouTubeConfig.getAPI_KEY(), ytListener);
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

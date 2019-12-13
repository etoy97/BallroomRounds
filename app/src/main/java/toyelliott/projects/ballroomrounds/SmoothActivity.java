package toyelliott.projects.ballroomrounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Timer;
import java.util.TimerTask;

public class SmoothActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{
    private static final String TAG = "SmoothClass";

    AudioManager am;
    YouTubePlayer video;
    Integer minVolume;
    Integer curVolume;
    Long waitTime;
    Float fadeSec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        //Creates a fragment YoutubePlayer
        YouTubePlayerSupportFragment frag = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.YouTubePlayer);

        //Initializes with API Key that I created
        frag.initialize(YouTubeConfig.getAPI_KEY(), this);

        //Create an audio Manager to control volume
        AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        am = myAudioManager;
        minVolume = am.getStreamMinVolume(am.STREAM_MUSIC);

        //Will make this something the user can specify
        fadeSec = (float) 5;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MusicPlayer.fadeOut(fadeSec, TAG, am);
            }
        }, 90000);

    }

    //Fades volume when pushed
    public void pressFadeOut(View view) {
        Log.d(TAG, "presseFadeOut begin");
        MusicPlayer.fadeOut(fadeSec, TAG, am);
        Log.d(TAG, "pressFadeOut end");
    }

    //Increases volume when pushed
    public void pressVolumeUp(View view) {
        Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
        Log.d(TAG, "pressed volume up");
        am.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
    }

    public void pressPlay (View view) {
        Log.d(TAG, "pressed play");
        video.play();
    }

    public void pressPause(View view) {
        Log.d(TAG, "pressed pause");
        video.pause();
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

}


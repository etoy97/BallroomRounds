package toyelliott.projects.ballroomrounds;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class RoundsActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {
    protected String TAG = "";

    AudioManager am;
    YouTubePlayer video;
    Float fadeSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTag();
        setContentView(getLayoutResourceId());

        //Creates a fragment YoutubePlayer
        YouTubePlayerSupportFragment frag = getFrag();

        //Initializes with API Key that I created
        frag.initialize(YouTubeConfig.getAPI_KEY(), this);

        //Create an audio Manager to control volume
        AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        am = myAudioManager;

        //Will make this something the user can specify
        fadeSec = (float) 5;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "Running timed fade");
                MusicPlayer.fadeOut(fadeSec, TAG, am);

                video.pause();
            }
        }, 10000);

    }

    //Fades volume when pushed
    public void pressFadeOut(View view) {
        Log.d(this.TAG, "pressFadeOut begin");
        MusicPlayer.fadeOut(fadeSec, TAG, am);

        Log.d(TAG, "pressFadeOut end");
    }

    //Increases volume when pushed
    public void pressVolumeUp(View view) {
        Log.d(this.TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
        Log.d(TAG, "pressed volume up");
        am.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
    }

    public void pressPlay(View view){
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

        //Loads a specific video based on the URL and assigns to video
        // so it can be references in the buttons
        video = player;
        List<String> lst = videos();
        video.loadVideos(lst);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
        Log.d(TAG, "onClick: Failed Initializing");
    }


    protected void setTag() {
        this.TAG = "RoundsActivity";
    }

    protected List<String> videos() {
        return Arrays.asList("7gwBxKHBoEI", "W4hTJybfU7s");
    }

    protected abstract int getLayoutResourceId();
    protected abstract YouTubePlayerSupportFragment getFrag();
}

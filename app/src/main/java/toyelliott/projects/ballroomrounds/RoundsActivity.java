package toyelliott.projects.ballroomrounds;

import android.content.Context;
import android.content.Intent;
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
    Integer fadeSec;
    Integer videoLength;
    Timer timer;
    TimerTask tt;

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

        //These get specified in the ConfigureTime class
        // and propagated to the Standard/Latin/Smooth class in intents
        fadeSec = getFadeSec();
        videoLength = getVideoLength();
        Integer delay = videoLength - fadeSec*1000;


        timer = new Timer();
        tt = new TimerTask() {
            @Override
            public void run() throws IllegalStateException{

                Log.d(TAG, "Running timed fade");
                MusicPlayer.fadeOut((float)fadeSec, TAG, am);

                video.pause();
            }
        };
        timer.schedule(tt, delay);

    }

    //When the activity is closed, we stop the timer so no error gets returned
    @Override
    public void onStop() {
        Log.d(this.TAG, "Went Back");
        tt.cancel();
        timer.cancel();
        super.onStop();
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

    //getFadeSec gets the FadeSec from ConfigureTime intent
    protected int getFadeSec() {
        Intent intent = getIntent();
        Integer fadeSec = intent.getIntExtra(ConfigureTime.fS, 5);
        Log.d(TAG, fadeSec.toString());
        return fadeSec;
    }

    //getVideoLength gets the length of the video from ConfigureTime intent
    protected int getVideoLength() {
        Intent intent = getIntent();
        Integer videoLength = intent.getIntExtra(ConfigureTime.vL, 10)*1000;
        Log.d(TAG, videoLength.toString());
        return videoLength;
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

    //Below methods get overrided based on whatever each subclass
    //has for Tags and videos
    protected void setTag() {
        this.TAG = "RoundsActivity";
    }
    protected List<String> videos() {
        return Arrays.asList("dQw4w9WgXcQ", "dQw4w9WgXcQ");
    }

    protected abstract int getLayoutResourceId();
    protected abstract YouTubePlayerSupportFragment getFrag();
}

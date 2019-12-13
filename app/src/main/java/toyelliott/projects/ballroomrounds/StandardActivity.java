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


public class StandardActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{
    private static final String TAG = "StandardClass";

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
                    fadeOut();
            }
        }, 90000);

    }

    //Fades volume when pushed
    public void pressFadeOut(View view) {
        Log.d(TAG, "presseFadeOut begin");
        fadeOut();
        Log.d(TAG, "pressFadeOut end");
    }

    //Helper function for either pressFadeOut for testing or timer based fading
    private void fadeOut() {
        Log.d(TAG, "fadeOut begin");
        Thread fade = new Thread(new Runnable() {
            public void run(){
                //Base the wait time on how many seconds for fade divided by current volume
                waitTime = (long) ((fadeSec/am.getStreamVolume(am.STREAM_MUSIC)) *1000);

                curVolume = am.getStreamVolume(am.STREAM_MUSIC);

                //Fade music to quiet
                while (curVolume > minVolume) {
                    curVolume = am.getStreamVolume(am.STREAM_MUSIC);
                    am.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Iteration");
                    Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
                }

                Log.d(TAG, "Out of fading");
                return;
            }
        });
        fade.start();

        Log.d(TAG, "fadeOut end");
    }

    //Increases volume when pushed
    public void pressVolumeUp(View view) {
        Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
        Log.d(TAG, "pressed volume up");
        am.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
    }

    //Plays when pushed
    public void pressPlay (View view) {
        Log.d(TAG, "pressed play");
        video.play();
    }

    //Pauses when pushed
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
        video.loadVideo("B5lKqLmZ-bQ");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
        Log.d(TAG, "onClick: Failed Initializing");
    }
}

package toyelliott.projects.ballroomrounds;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;

public abstract class RoundsActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {
    protected String TAG = "";

    AudioManager am;
    YouTubePlayer video;
    Long fadeSec;
    Long videoLength;
    CountDownTimer timer;

    Button mButtonPlay;
    Button mButtonPause;
    Button mButtonFadeOut;
    Button mButtonVolumeUp;

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

        mButtonPlay = getmButtonPlay();
        mButtonPause = getmButtonPause();
        mButtonFadeOut = getmButtonFadeOut();
        mButtonVolumeUp = getmButtonVolumeUp();

        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        mButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseVideo();
            }
        });

        mButtonFadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeOut();
            }
        });

        mButtonVolumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressVolumeUp();
            }
        });

        //These get specified in the ConfigureTime class
        // and propagated to the Standard/Latin/Smooth/Rhythm class in intents
        fadeSec = getFadeSecFromIntent();
        //Have to subtract by fadeSec so it starts fading before the video is over
        // The 4000 is to add the extra few seconds that it takes for the video to load
        videoLength = getVideoLengthFromIntent() - fadeSec*1000 + 4000;
        //Integer delay = videoLength - fadeSec*1000;

        startTimer();
    }


    private void startTimer() {
        timer = new CountDownTimer(videoLength, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                videoLength = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "Finished Countdown");
                fadeOut();
                if (video.hasNext()) {
                    Log.d(TAG, "Went to next video");
                    videoLength = getVideoLengthFromIntent() - fadeSec*1000 + 4000;
                    video.next();
                    startTimer();
                } else {
                    pauseVideo();
                }
            }
        }.start();
    }

    //Fades volume when pushed
    public void fadeOut() {
        Log.d(this.TAG, "fadeOut begin");
        MusicPlayer.fadeOut(fadeSec, TAG, am);

        Log.d(TAG, "fadeOut end");
    }

    //Increases volume when pushed
    public void pressVolumeUp() {
        Log.d(this.TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
        Log.d(TAG, "pressed volume up");
        am.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
    }

    public void playVideo(){
        Log.d(TAG, "pressed play");
        video.play();
        startTimer();
    }

    public void pauseVideo() {
        Log.d(TAG, "pressed pause");
        video.pause();
        timer.cancel();
    }

    //getFadeSec gets the FadeSec from ConfigureTime intent, in seconds
    protected long getFadeSecFromIntent() {
        Intent intent = getIntent();
        Long fadeSec = intent.getLongExtra(ConfigureTime.fS, 5);
        Log.d(TAG, fadeSec.toString());
        return fadeSec;
    }

    //getVideoLength gets the length of the video from ConfigureTime intent, in milliseconds
    protected long getVideoLengthFromIntent() {
        Intent intent = getIntent();
        Long videoLength = intent.getLongExtra(ConfigureTime.vL, 10000);
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

    protected abstract Button getmButtonPlay();
    protected abstract Button getmButtonPause();
    protected Button getmButtonFadeOut() { return findViewById(R.id.FadeOut); }
    protected Button getmButtonVolumeUp() { return findViewById(R.id.VolumeUp); }
    protected abstract int getLayoutResourceId();
    protected abstract YouTubePlayerSupportFragment getFrag();
}

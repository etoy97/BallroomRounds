package toyelliott.projects.ballroomrounds;

import android.media.AudioManager;
import android.util.Log;

public class MusicPlayer {

    //Helper function for either pressFadeOut for testing or timer based fading
    public static void fadeOut(final float fadeSec, final String TAG, final AudioManager am){
        Log.d(TAG, "fadeOut begin");
        final Integer minVolume = am.getStreamMinVolume(am.STREAM_MUSIC);

        Thread fade = new Thread(new Runnable() {
            public void run(){
                //Base the wait time on how many seconds for fade divided by current volume
                long waitTime = (long) ((fadeSec/am.getStreamVolume(am.STREAM_MUSIC)) *1000);

                Integer curVolume = am.getStreamVolume(am.STREAM_MUSIC);

                try {
                    //Fade music to quiet
                    while (curVolume > minVolume) {
                        curVolume = am.getStreamVolume(am.STREAM_MUSIC);
                        am.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                        Thread.sleep(waitTime);
                        Log.d(TAG, "Iteration");
                        Log.d(TAG, Integer.toString(am.getStreamVolume(am.STREAM_MUSIC)));
                    }

                    Log.d(TAG, "Out of fading");
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fade.start();
        try {
            fade.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "fadeOut end");
        return;
    }

}

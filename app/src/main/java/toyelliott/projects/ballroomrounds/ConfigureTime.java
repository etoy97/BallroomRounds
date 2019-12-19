package toyelliott.projects.ballroomrounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConfigureTime extends AppCompatActivity {
    private static final String TAG = "ConfigureTime";
    public static final String fS = "fadeSec";
    public static final String vL = "videoLength";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_time);
    }

    private int getFadeSec() {
        EditText text = findViewById(R.id.fadeSecConfiguration);
        Integer sec = Integer.parseInt(text.getText().toString());
        Log.d(TAG, "fadeSec:" + sec.toString());
        return sec;
    }

    private int getVideoLength() {
        EditText text = findViewById(R.id.videoLengthConfiguration);
        Integer sec = Integer.parseInt(text.getText().toString());
        Log.d(TAG, "videoLength:" + sec.toString());
        return sec;
    }

    public void startRounds(View view) {
        Log.d(TAG, "pressed startRounds");
        Integer fadeSec = getFadeSec();
        Integer videoLength = getVideoLength();

        String className = getIntent().getStringExtra(MainActivity.className);
        System.out.println(className);
        if (className.equals("standard")){
            Intent intent = new Intent(this, StandardActivity.class);
            intent.putExtra(fS, fadeSec);
            intent.putExtra(vL, videoLength);
            startActivity(intent);
        }
        if (className.equals("latin")){
            Intent intent = new Intent(this, LatinActivity.class);
            intent.putExtra(fS, fadeSec);
            intent.putExtra(vL, videoLength);
            startActivity(intent);
        }
        if (className.equals("smooth")){
            Intent intent = new Intent(this, SmoothActivity.class);
            intent.putExtra(fS, fadeSec);
            intent.putExtra(vL, videoLength);
            startActivity(intent);
        }
    }
}

package toyelliott.projects.ballroomrounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startStandard(View view) {
        Intent intent = new Intent(this, StandardActivty.class);
        startActivity(intent);
    }


    public void startLatin(View view) {
        Intent intent = new Intent(this, LatinActvity.class);
        startActivity(intent);
    }
}

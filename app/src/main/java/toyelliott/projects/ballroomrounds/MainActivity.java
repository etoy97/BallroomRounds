package toyelliott.projects.ballroomrounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    public static final String className = "className";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startStandard(View view) {
        Intent intent = new Intent(this, ConfigureTime.class);
        intent.putExtra(className, "standard");
        startActivity(intent);
    }

    public void startLatin(View view) {
        Intent intent = new Intent(this, ConfigureTime.class);
        intent.putExtra(className, "latin");
        startActivity(intent);
    }

    public void startSmooth(View view) {
        Intent intent = new Intent(this, ConfigureTime.class);
        intent.putExtra(className, "smooth");
        startActivity(intent);
    }

    public void startConfiguration(View view) {
        Intent intent = new Intent(this, ConfigureTime.class);
        startActivity(intent);
    }
}

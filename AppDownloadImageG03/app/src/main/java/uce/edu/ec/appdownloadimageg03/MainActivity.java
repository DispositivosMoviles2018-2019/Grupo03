package uce.edu.ec.appdownloadimageg03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebImageView imageView =
                (WebImageView) findViewById(R.id.webImage);
        imageView.setPlaceholderImage(R.drawable.ic_launcher_background);
        imageView.setImageUrl("http://lorempixel.com/400/200");
    }
}
package com.example.i_instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText description;
    Button capture;
    ImageView image;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = findViewById(R.id.etDescription);
        capture = findViewById(R.id.btnCaptureImage);
        image = findViewById(R.id.ivPostImage);
        submit = findViewById(R.id.btnPost);
    }
}

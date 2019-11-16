package com.example.i_instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";

    EditText usernameEditText;
    EditText passwordEditText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.username_new);
        passwordEditText = findViewById(R.id.password_new_user);
        signUpButton = findViewById(R.id.sign_up_new);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick " + " Sign Up");
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                signUp(username, password);
            }
        });
    }

    private void signUp(String username, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.d(TAG, "something went wrong when signing up");
                    e.printStackTrace();
                    return;
                }
                Log.i(TAG, "Going to main activity");
                goMainActivity();
            }
        });

    }

    private void goMainActivity() {
        Log.i(TAG, "Navigating to Main Activity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

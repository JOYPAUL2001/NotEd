package com.example.noted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SignUP extends AppCompatActivity {

    EditText mPassSignUp,mNameSignUp,mMailSignUp;
    Button mSignUp,mSignIn;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageView imageView = findViewById(R.id.imageView2);
        Glide.with(this).asGif().load(R.raw.user).into(imageView);

        mPassSignUp = findViewById(R.id.etPassSignUp);
        mNameSignUp = findViewById(R.id.etNameSignUp);
        mMailSignUp = findViewById(R.id.etMailSignUp);

        mSignUp = findViewById(R.id.bt_signup_SignUp);
        mSignIn = findViewById(R.id.bt_signin_SignUp);


        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUP.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
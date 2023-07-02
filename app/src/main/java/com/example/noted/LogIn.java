package com.example.noted;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class LogIn extends AppCompatActivity {

    EditText mPassSignIn,mMailSignIn;
    Button mSignUp,mSignIn,mForgetpass;

    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ImageView imageView = findViewById(R.id.imageView1);
        Glide.with(this).asGif().load(R.raw.user_login).into(imageView);


        mPassSignIn = findViewById(R.id.etPassSignIn);
        mMailSignIn = findViewById(R.id.etMailSignIn);
        mSignUp = findViewById(R.id.bt_signup_SignIn);
        mSignIn = findViewById(R.id.bt_signin_SignIn);
        mForgetpass = findViewById(R.id.bt_forgetPassSignIn);

        //Going to forget pass section
        mForgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,ForgetPass.class);
                startActivity(intent);
            }
        });

        //Going to Sign Up section

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,SignUP.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
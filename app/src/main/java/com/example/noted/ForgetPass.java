package com.example.noted;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ForgetPass extends AppCompatActivity {

    EditText email;
    Button resetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        ImageView imageView = findViewById(R.id.imageView3);
        Glide.with(this).asGif().load(R.raw.confusion).into(imageView);

        email = findViewById(R.id.etMailForgetPass);
        resetpass = findViewById(R.id.btForgetPass);


    }
}
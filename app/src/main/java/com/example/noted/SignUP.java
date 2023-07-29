package com.example.noted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUP extends AppCompatActivity {

    EditText mPassSignUp,mNameSignUp,mMailSignUp;
    Button mSignUp,mSignIn;
    boolean passwordVisible;

    FirebaseAuth firebaseAuth;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        ImageView imageView = findViewById(R.id.imageView2);
        Glide.with(this).asGif().load(R.raw.user).into(imageView);

        mPassSignUp = findViewById(R.id.etPassSignUp);
        mNameSignUp = findViewById(R.id.etNameSignUp);
        mMailSignUp = findViewById(R.id.etMailSignUp);

        mSignUp = findViewById(R.id.bt_signup_SignUp);
        mSignIn = findViewById(R.id.bt_signin_SignUp);

        firebaseAuth = FirebaseAuth.getInstance();


        mSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUP.this,LogIn.class);
            startActivity(intent);
            finish();
        });

          mSignUp.setOnClickListener(v -> {
              String mail = mMailSignUp.getText().toString().trim();
              String password = mPassSignUp.getText().toString().trim();
              String name = mNameSignUp.getText().toString().trim();

              if(mail.isEmpty() || password.isEmpty() || name.isEmpty()){
                  Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
              }
              else if(password.length()<7){
                  Toast.makeText(getApplicationContext(),"Password should greater than 7 digits",Toast.LENGTH_SHORT).show();
              }
              else{
                  //register to the user

                  firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {

                          if(task.isSuccessful()){

                              Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                              sendEmailVerification();
                          }
                          else{
                              Toast.makeText(getApplicationContext(),"Failed to register",Toast.LENGTH_SHORT).show();
                          }
                      }
                  });
              }

          });



        // Function to see password and hide password
        mPassSignUp.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= mPassSignUp.getRight() - mPassSignUp.getCompoundDrawables()[Right].getBounds().width()) {
                    int selection = mPassSignUp.getSelectionEnd();
                    //Handles Multiple option popups
                    if (passwordVisible) {
                        //set drawable image here
                        mPassSignUp.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                        //for hide password
                        mPassSignUp.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordVisible = false;
                    } else {
                        //set drawable image here
                        mPassSignUp.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0);
                        //for show password
                        mPassSignUp.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordVisible = true;
                    }
                    mPassSignUp.setLongClickable(false); //Handles Multiple option popups
                    mPassSignUp.setSelection(selection);
                    return true;
                }
            }
            return false;
        });



    }

    public void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verification Email is sent,verify and Log In again!",Toast.LENGTH_LONG).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(SignUP.this,LogIn.class));
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"Failed to sent verification Email!",Toast.LENGTH_SHORT).show();
        }
    }
}
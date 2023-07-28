package com.example.noted;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    EditText mPassSignIn,mMailSignIn;
    Button mSignUp,mSignIn,mForgetpass;

    boolean passwordVisible;

    FirebaseAuth firebaseAuth;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().hide();

        ImageView imageView = findViewById(R.id.imageView1);
        Glide.with(this).asGif().load(R.raw.user_login).into(imageView);


        mPassSignIn = findViewById(R.id.etPassSignIn);
        mMailSignIn = findViewById(R.id.etMailSignIn);
        mSignUp = findViewById(R.id.bt_signup_SignIn);
        mSignIn = findViewById(R.id.bt_signin_SignIn);
        mForgetpass = findViewById(R.id.bt_forgetPassSignIn);

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(LogIn.this,MainActivity.class));

        }

        //Going to forget pass section
        mForgetpass.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this,ForgetPass.class);
            startActivity(intent);
        });

        //Going to Sign Up section

        mSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this,SignUP.class);
            startActivity(intent);
            finish();
        });

        mSignIn.setOnClickListener(v -> {
            String mail = mMailSignIn.getText().toString().trim();
            String password = mPassSignIn.getText().toString().trim();

            if(mail.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
            }
            else if(password.length()<7){
                Toast.makeText(getApplicationContext(),"Password should greater than 7 digits",Toast.LENGTH_SHORT).show();
            }else{
                //login the user
                firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            CheckMailVerification();
                        }else{
                            Toast.makeText(getApplicationContext(),"Account doesn't exist!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Function to see password and hide password
        mPassSignIn.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= mPassSignIn.getRight() - mPassSignIn.getCompoundDrawables()[Right].getBounds().width()) {
                    int selection = mPassSignIn.getSelectionEnd();
                    //Handles Multiple option popups
                    if (passwordVisible) {
                        //set drawable image here
                        mPassSignIn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                        //for hide password
                        mPassSignIn.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordVisible = false;
                    } else {
                        //set drawable image here
                        mPassSignIn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0);
                        //for show password
                        mPassSignIn.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordVisible = true;
                    }
                    mPassSignIn.setLongClickable(false); //Handles Multiple option popups
                    mPassSignIn.setSelection(selection);
                    return true;
                }
            }
            return false;
        });
    }
    void CheckMailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser.isEmailVerified()==true){
            Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(LogIn.this,MainActivity.class));
        }else{
            Toast.makeText(getApplicationContext(),"Verify your mail first!",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
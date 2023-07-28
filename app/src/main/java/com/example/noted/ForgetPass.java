package com.example.noted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPass extends AppCompatActivity {

    EditText email;
    Button resetpass;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        getSupportActionBar().hide();

        ImageView imageView = findViewById(R.id.imageView3);
        Glide.with(this).asGif().load(R.raw.confusion).into(imageView);

        email = findViewById(R.id.etMailForgetPass);
        resetpass = findViewById(R.id.btForgetPass);

        firebaseAuth = FirebaseAuth.getInstance();

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                if(mail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your mail first",Toast.LENGTH_SHORT).show();
                }
                else{
                    //we have to send
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Mail sent! you can recover your mail through Gmail",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgetPass.this,LogIn.class));
                            }
                            else{
                                    Toast.makeText(getApplicationContext(),"Email is wrong or Account doesn't exist!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
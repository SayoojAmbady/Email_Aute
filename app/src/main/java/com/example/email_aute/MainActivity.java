package com.example.email_aute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button register,signup;
    FirebaseAuth Fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      email= findViewById(R.id.editTextTextPersonName);
      password=findViewById(R.id.editTextTextPersonName2);
      register=findViewById(R.id.btn1);
      signup=findViewById(R.id.btn2);
      Fauth=FirebaseAuth.getInstance();


       signup.setOnClickListener(new View.OnClickListener() {
         @Override
           public void onClick(View v) {
              Intent intent=new Intent(MainActivity.this,RegActivity.class);
               startActivity(intent);
           }
       });

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Email=email.getText().toString().trim();
               String Password=password.getText().toString().trim();

               Fauth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(MainActivity.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),Home.class));
                       }

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                   }
               });
           }
       });



    }
}
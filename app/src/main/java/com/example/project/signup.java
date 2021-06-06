package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.FederatedSignInActivity;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity{

    ImageView img;
    EditText editText;
    EditText editText1;
    Button button;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       Button btn=findViewById(R.id.button12);
        img=findViewById(R.id.imagebutton2);
        button=findViewById(R.id.signupbutton);
        editText=findViewById(R.id.editText5);//email
        editText1=findViewById(R.id.editText7);//password
        mAuth=FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString().trim();
                String password=editText1.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {

                       editText.setError("Invalid Email");
                       editText.setFocusable(true);
                }
                else if(password.length()<6)
                {
                    editText1.setError("Password length at least  6");
                    editText1.setFocusable(true);
                }
                else
                {
                    registerUser(email,password);
                }
            }

            private void registerUser(String email, String password)
            {
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {

                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    progressDialog.dismiss();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(signup.this, "Registered...\n"+user.getEmail(), Toast.LENGTH_SHORT).show();;
                                    startActivity(new Intent(signup.this,login.class));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();;
                                }


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(signup.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        Button btn1=findViewById(R.id.button12);
        ImageButton bt=findViewById(R.id.imagebutton2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(signup.this,login.class);
                startActivity(in);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(signup.this,login.class);
                startActivity(in);
            }
        });
    }

}

package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.security.PrivateKey;

public class Signup extends AppCompatActivity implements View.OnClickListener{

    private EditText edtemail, edtphone, edtpassword;
    private Button btnsignup, btntowardsloginpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtemail = findViewById(R.id.edtemailsignup);
        edtphone = findViewById(R.id.edtphonesignup);
        edtpassword = findViewById(R.id.edtpasswordsignup);

        btnsignup = findViewById(R.id.btnsignup);
        btntowardsloginpage = findViewById(R.id.btnloginonsignup);

        btnsignup.setOnClickListener(Signup.this);
        btntowardsloginpage.setOnClickListener(Signup.this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btnsignup:

                final ParseUser appuser = new ParseUser();
                appuser.setEmail(edtemail.getText().toString());
                appuser.setUsername(edtphone.getText().toString());
                appuser.setPassword(edtpassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(Signup.this);
                progressDialog.setMessage("Signing in the user: " + edtemail.getText());
                progressDialog.show();

                appuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){

                            Toast.makeText(Signup.this," You have sucessfully signed up",
                                    Toast.LENGTH_SHORT).show();
                            TransitionOfActivity();

                        }else{
                            Toast.makeText(Signup.this," An error occurred" + "\n" + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        progressDialog.cancel();
                    }
                });
                break;

            case R.id.btnloginonsignup:
                Intent intent = new Intent(Signup.this,Login_page.class);
                startActivity(intent);
                break;
        }
    }

    private void TransitionOfActivity(){
        Intent intent = new Intent(Signup.this,PrimaryAcitivty.class);
        startActivity(intent);
    }
}

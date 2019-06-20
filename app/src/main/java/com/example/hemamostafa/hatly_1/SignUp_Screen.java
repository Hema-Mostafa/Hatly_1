package com.example.hemamostafa.hatly_1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Screen extends MyBaseActivity implements View.OnClickListener {

    TextView backLoginTextView;
    EditText userName,password,mail;
    CheckBox checkBox;
    Button signup_btn;
    String stringName;
    String stringMail;
    String stringPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__screen);
        inititViews();
        mAuth = FirebaseAuth.getInstance();

    }

    private void inititViews(){
        userName=(EditText) findViewById(R.id.editTextUserNamee);
        password=(EditText)findViewById(R.id.editTextPassword);
        mail=(EditText)findViewById(R.id.editTextEmail);
        checkBox = findViewById(R.id.checkBox);
        backLoginTextView = findViewById(R.id.backToLogin);
        signup_btn=findViewById(R.id.signBtn);
        signup_btn.setOnClickListener(this);
        backLoginTextView.setOnClickListener(this);



    }

    private boolean validateData() {

        stringName = userName.getText().toString().trim();
        stringMail = mail.getText().toString().trim();
        stringPassword = password.getText().toString().trim();

        if (stringName.isEmpty()) {
            userName.setError(getString(R.string.input_error_name));
            userName.requestFocus();
            return false;
        }

        if (stringMail.isEmpty()) {
            mail.setError(getString(R.string.input_error_email));
            mail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(stringMail).matches()) {
            mail.setError(getString(R.string.input_error_email_invalid));
            mail.requestFocus();
            return false;
        }

        if (stringPassword.isEmpty()) {
            password.setError(getString(R.string.input_error_password));
            password.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            password.setError(getString(R.string.input_error_password_length));
            password.requestFocus();
            return false;
        }

        if(checkBox.isChecked()){
            checkBox.setError(null);
        }
        else {
            checkBox.setError("check box");
            return false;
        }
        return  true;

    }

    public  void registerUsers() {


        mAuth.createUserWithEmailAndPassword(stringMail, stringPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();

                            User user = new User(user_id,stringName,stringMail,stringPassword,"","","","");

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(user_id)
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(activity, getString(R.string.registration_success), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUp_Screen.this, MyNewHome.class));
                                    } else {
                                        Toast.makeText(activity, "Regestration  Failed ", Toast.LENGTH_SHORT).show();
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(activity, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signBtn:
                Toast.makeText(activity," button Is Clicked",Toast.LENGTH_SHORT).show();
                if (validateData()){
                    registerUsers();
                }
                else {
                    Toast.makeText(activity," Not Valid Data",Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.backToLogin:
                startActivity(new Intent(activity,LoginScreen.class));
                break;



        }
    }

    }

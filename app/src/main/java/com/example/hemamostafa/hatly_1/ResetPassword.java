package com.example.hemamostafa.hatly_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;

public class ResetPassword extends MyBaseActivity {

    TextView backToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    public void backToLoginFunction(View view) {
        startActivity(new Intent(ResetPassword.this,LoginScreen.class));
        backToLogin=findViewById(R.id.backToLogin);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,LoginScreen.class));
            }
        });
    }
}

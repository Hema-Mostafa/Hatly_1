package com.example.hemamostafa.hatly_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ChangeDealRequest extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_deal_request);
        toolbar=findViewById(R.id.custom_toolBar);

        toolbarTitle=findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Change Deal Price");
    }
}

package com.example.goodeatingfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calificanosActivity extends AppCompatActivity {


    private Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificanos);
        btn=(Button) findViewById(R.id.button_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(calificanosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
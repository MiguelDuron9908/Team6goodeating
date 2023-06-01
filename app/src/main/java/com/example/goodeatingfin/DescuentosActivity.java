package com.example.goodeatingfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DescuentosActivity extends AppCompatActivity {
    private Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descuentos);

        boton2=(Button) findViewById(R.id.button_back);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DescuentosActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
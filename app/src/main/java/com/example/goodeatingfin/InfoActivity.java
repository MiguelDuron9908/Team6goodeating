package com.example.goodeatingfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {
    private Button btnTerminos, btnIdioma, btnCalificacion, btnDescuentos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnTerminos = (Button) findViewById(R.id.btn_terminos);
        btnIdioma = (Button) findViewById(R.id.btn_idioma);
        btnCalificacion = (Button) findViewById(R.id.btn_calificacion);
        btnDescuentos = (Button) findViewById(R.id.btn_descuentos);

        btnTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, TerminosActivity.class);
                startActivity(intent);
            }
        });

        btnIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }
        });

        btnCalificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, calificanosActivity.class);
                startActivity(intent);
            }
        });

        btnDescuentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, DescuentosActivity.class);
                startActivity(intent);
            }
        });



    }
}
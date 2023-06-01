package com.example.goodeatingfin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class InicioActivity extends AppCompatActivity
    implements GestureOverlayView.OnGesturePerformedListener {

        private GestureLibrary gLibrary;
    Button user, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///////////////elimina la barra de estado////////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        ///////////////////////////////

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        gestureSetup();
        user = findViewById(R.id.btn_login);
        admin = findViewById(R.id.btn_admin);

        user.setOnClickListener(view -> {
            startActivity(new Intent(InicioActivity.this, LoginActivity.class));
        });

        admin.setOnClickListener(view -> {
            startActivity(new Intent(InicioActivity.this, Logina.class));
        });
    }
    private void gestureSetup()
    {
        gLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLibrary.load()) {
            finish();
        }
        GestureOverlayView gOverlay = findViewById(R.id.gestures);
        gOverlay.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
    {
        ArrayList<Prediction> predictions = gLibrary.recognize(gesture);
        if (predictions.size() > 0 && predictions.get(0).score > 1.0)
        {
            String action = predictions.get(0).name;

            if(action.equals("usuario")){
            Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InicioActivity.this, LoginActivity.class));
            }
            if(action.equals("admin")){
                Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InicioActivity.this, Logina.class));
            }


        }
    }
}
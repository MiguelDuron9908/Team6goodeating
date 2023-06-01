package com.example.goodeatingfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Logina extends AppCompatActivity {
    private TextInputEditText numero, codigo;
    private Button enviarnumero, enviarcodigo;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String VerificacionID;
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private FirebaseAuth auth;
    private ProgressDialog dialog;
    private String phoneNumber;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///////////////elimina la barra de estado////////////////
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        ///////////////////////////////


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logina);
        numero=(TextInputEditText)findViewById(R.id.numeroadmin);
        codigo=(TextInputEditText)findViewById(R.id.codigoadmin);
        enviarnumero=(Button)findViewById(R.id.enviarnumeroadmin);
        enviarcodigo=(Button)findViewById(R.id.enviarcodigoadmin);

        auth= FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        enviarnumero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                phoneNumber = numero.getText().toString();
                if(TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(Logina.this, "Ingresa tu numero primero....", Toast.LENGTH_SHORT).show();


                }else{
                    dialog.setTitle("Validando numero");
                    dialog.setMessage("Por favor espere mientras validamos su numero");
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(true);
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber(phoneNumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(Logina.this)
                            .setCallbacks(callbacks)
                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options); //Envia el numero....
                }
            }
        });

        enviarcodigo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                numero.setVisibility(View.GONE);
                enviarnumero.setVisibility(View.GONE);
                String VerificacionCode = codigo.getText().toString();
                if(TextUtils.isEmpty(VerificacionCode)){
                    Toast.makeText(Logina.this, "Ingresa el codigo recibido", Toast.LENGTH_SHORT).show();


                }else{
                    dialog.setTitle("Verificando");
                    dialog.setMessage("Espere por favor....");
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(true);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificacionID, VerificacionCode);
                    IngresadoConExito(credential);
                }
            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                IngresadoConExito(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                dialog.dismiss();
                Toast.makeText(Logina.this, "Fallo el iniciar", Toast.LENGTH_SHORT).show();
                numero.setVisibility(View.VISIBLE);
                enviarnumero.setVisibility(View.VISIBLE);
                codigo.setVisibility(View.GONE);
                enviarcodigo.setVisibility(View.GONE);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token){
                VerificacionID = s;
                resendingToken = token;
                dialog.dismiss();
                Toast.makeText(Logina.this, "Codigo enviado correctamente, Favor de revisar", Toast.LENGTH_SHORT).show();
                numero.setVisibility(View.GONE);
                enviarnumero.setVisibility(View.GONE);
                codigo.setVisibility(View.VISIBLE);
                enviarcodigo.setVisibility(View.VISIBLE);

            }
        };

    }



    private void IngresadoConExito(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(Logina.this, "Ingresado con exito", Toast.LENGTH_SHORT).show();
                    EnviarlaPrincipal();
                }else{
                    String err = task.getException().toString();
                    Toast.makeText(Logina.this, "Error:"+err, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if(firebaseUser !=null){
            EnviarlaPrincipal();
        }
    }

    private void EnviarlaPrincipal(){
        Intent intent = new Intent(Logina.this,AdminActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("phone", phoneNumber);
        startActivity(intent);
        finish();
    }
}
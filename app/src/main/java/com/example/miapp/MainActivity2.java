package com.example.miapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity2 extends AppCompatActivity {
    Button miBoton = null;
    Button miBoton2 = null;
    EditText caja = null;
    String defaultLocal = "+507";int limiteCodeLocal = defaultLocal.length();int limitePhone = 8;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showLog("Activity Created");
        caja = findViewById(R.id.cajaNum);
        miBoton2 = findViewById(R.id.boton2);
        miBoton = findViewById(R.id.button);
        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        miBoton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageWhatsapp(caja.getText().toString());
            }
        });

    }
    private void sendMessageWhatsapp(String phone){
        String myphone = phone;
        if (myphone.length() == (limiteCodeLocal + limitePhone)) {
            String numeroPhone = myphone.substring(myphone.lastIndexOf(defaultLocal));
            System.out.println("numeroPhone = " + numeroPhone);
            Handler handler = new Handler();

            handler.post(new Runnable() {
                public void run() {
                    String url = "https://api.whatsapp.com/send?phone=" + phone;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    finish();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "FAVOR INGRESE UN CONTACTO VÁLIDO", Toast.LENGTH_SHORT).show();
        }
    }


    private static final String HOME_ACTIVITY_TAG = MainActivity2.class.getSimpleName();

    private void showLog(String text) {
        Log.d(HOME_ACTIVITY_TAG, text);
    }


    @Override

    protected void onRestart() {

        super.onRestart();//call to restart after onStop
        caja.setText("1");
        showLog("Activity restarted");

    }

    @Override

    protected void onStart() {

        super.onStart();//soon be visible

        showLog("Activity started");


    }

    @Override

    protected void onResume() {

        super.onResume();//visible

        showLog("Activity resumed");

    }

    @Override

    protected void onPause() {

        super.onPause();//invisible

        showLog("Activity paused");
        caja.setText("");

    }

    @Override

    protected void onStop() {

        super.onStop();

        showLog("Activity stopped");

    }

    @Override

    protected void onDestroy() {

        super.onDestroy();

        showLog("Activity is being destroyed");

    }

}


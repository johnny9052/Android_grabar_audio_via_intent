package com.example.johnny.android_grabar_audio_via_intent;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /*Identificador de la solucitud realizada*/
    int identificador = 1;
    /*Ruta del archivo creado*/
    Uri url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void grabar(View v) {
        /*Se crea un intent con la activity propia de android para la grabacion de sonido*/
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        /*Se manda como referencia el intent, y un valor entero positivo, con este se identifica
        * luego cuando retorne el resultado, a que ejecucion corresponde, dado el caso que se
        * ejecuten multiples startActivityForResult*/
        startActivityForResult(intent, identificador);
    }

    public void reproducir(View v) {
        /*Se instancia un objeto para reproduccion, mandando por parametro la ruta del archivo*/
        MediaPlayer mediaPlayer = MediaPlayer.create(this, url);
        /*Se reproduce el archivo*/
        mediaPlayer.start();
    }


    /*Funcion que se ejecuta automaticamente, tan pronto se obtenga un resultado de la ejecucion
    * del startActivityForResult*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*Si se ejecuto correctamente, y ademas corresponde a la ejecucion que estabamos esperando*/
        if (resultCode == RESULT_OK && requestCode == identificador) {
            /*Obtenga la ruta del elemento que se guardo*/
            url = data.getData();
        }
    }
}

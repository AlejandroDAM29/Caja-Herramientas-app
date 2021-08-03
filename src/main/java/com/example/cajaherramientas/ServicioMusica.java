package com.example.cajaherramientas;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import static android.media.MediaPlayer.create;

public class ServicioMusica extends Service {
MediaPlayer miReproductor;

@Override
public void onCreate(){
    super.onCreate();

    miReproductor = create(this,R.raw.musica_fondo);
    miReproductor.setLooping(true);
    //Para establecer volumen de música: lado izquierdo, lado derecho
    miReproductor.setVolume(100,100);
}



@Override
public int onStartCommand(Intent intent, int flags, int startId){
miReproductor.start();
return START_STICKY;
}


@Override
public void onDestroy(){
    super.onDestroy();
    if(miReproductor.isPlaying()) miReproductor.stop();
    //Para liberar el servicio
    miReproductor.release();
    //Para liberar el recurso de la memoria
    miReproductor=null;

}













    //Método que no voy a utilizar, pero estás obligado a implentarlo porque es el método de la clase abstracta.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

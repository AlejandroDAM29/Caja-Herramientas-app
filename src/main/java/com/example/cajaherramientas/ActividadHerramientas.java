package com.example.cajaherramientas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;


import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ActividadHerramientas extends AppCompatActivity implements ComunicaMenu,ManejaFlashCamara{
//Crear array para obtener los botones
private Fragment[] misFragmentos;
//Declaramos el gestor de cámara de android
private CameraManager miCamara;
//Para almacenar el id de la cámara que contiene el flash
private String idCamara;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_herramientas);

        misFragmentos = new Fragment[3];
        misFragmentos[0]=new Linterna();
        misFragmentos[1]=new Musica();
        misFragmentos[2]=new Nivel();

        Bundle extras = getIntent().getExtras();
        menu(extras.getInt("BOTONPULSADO"));

        //Aquí declaro mi gestor de cámara android
        miCamara = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        //Este método devuelve un arrayString con las cámaras disponibles y lanza una excepción
        try {
            idCamara = miCamara.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void menu(int queboton) {
        //Métodos para cambiar un frame por otro
        FragmentManager miManejador = getFragmentManager();
        FragmentTransaction miTransaccion = miManejador.beginTransaction();

        //Para menú iluminado
        Fragment menu_iluminado = new Menu();
        Bundle datos = new Bundle();
        datos.putInt("BOTONPULSADO",queboton);
        menu_iluminado.setArguments(datos);
        miTransaccion.replace(R.id.menu,menu_iluminado);

        miTransaccion.replace(R.id.herramientas,misFragmentos[queboton]);
        miTransaccion.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void enciendeApaga(boolean estadoFlash) {
        //El método setTorchMode enciende/apaga el flash. Lanza un error.
        try {
            if (estadoFlash) {
                Toast.makeText(this, "Flash apagado", Toast.LENGTH_SHORT).show();
                miCamara.setTorchMode(idCamara, false);
            } else {
                Toast.makeText(this, "Flash encendido ", Toast.LENGTH_SHORT).show();
                miCamara.setTorchMode(idCamara, true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        //Esto es para mover la pila de recursos y servicios al final. Esto provoca salir de la app
        moveTaskToBack(true);
    }




}
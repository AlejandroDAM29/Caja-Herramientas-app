package com.example.cajaherramientas;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Linterna extends Fragment {
//Declaro una variable imágen que actuará como boton. Y un booleano para saber si está o no presionado
    private ImageView botonCamara;
    //El valor por defecto del boolean es false, asique el boton aparecerá sin resalta (apagado)
    private boolean encendida;


    public Linterna() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Convierto el return en vista para poder trabajar con lo que hay en ella.
        View fragmento = inflater.inflate(R.layout.fragment_linterna, container, false);
        //Ahora guardo en una variable la imágen de la linterna
        botonCamara = (ImageView)fragmento.findViewById(R.id.linterna);
        //Esto es para que el fragment linterna no quite el resaltado de haber sido pulsado
        if(encendida)botonCamara.setImageResource(R.drawable.linterna2);
        //Esto es para que la orientación del teléfono no cambie en esta actividad
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //El botón ha de estar a la escucha si se hace click para encender la cámara
        botonCamara.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*Se comprueba si la cámara está encendida por el método botonApagaFlash/
                 botonEnciendeFlash(). Si lo está, entonces se apagará.
                 */
                if(encendida){
                    botonApagaFlash();
                    encendida=false;
                }
                else{
                    botonEnciendeFlash();
                    //Se pone la linterna como encendida con el boolean
                    encendida = true;
                }
            }
        });


        return fragmento;
    }//Fin método onCreate


    /*
MÉTODOS PARA APAGAR Y ENCENDER FLASH DEL TELÉFONO
---------------------------------------------------------------------------------------------------
 */
    public void botonEnciendeFlash(){
        //Para cambiar la imágen que hay en el fragment grande
        botonCamara.setImageResource(R.drawable.linterna2);
        Activity estaActividad = getActivity();
        ((ManejaFlashCamara)estaActividad).enciendeApaga(encendida);
    }

    public void botonApagaFlash(){
        botonCamara.setImageResource(R.drawable.linterna);
        Activity estaActividad = getActivity();
        ((ManejaFlashCamara)estaActividad).enciendeApaga(encendida);
    }
}
package com.example.cajaherramientas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Musica extends Fragment {

    private boolean encendida;
    private ImageView botonMusica;

    public Musica() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmento =  inflater.inflate(R.layout.fragment_musica, container, false);
        botonMusica = (ImageView)fragmento.findViewById(R.id.musica);
        //Esto es para comprobar que el botón de la música sigue iluminado, para mantener la selección
        if(encendida) botonMusica.setImageResource(R.drawable.musica2);
        //Esto es para que la orientación del teléfono no cambie en esta actividad
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        botonMusica.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(encendida)apagaMusica();
                else enciendeMusica();
            }
        });
        return fragmento;
    }


    public void enciendeMusica(){
        botonMusica.setImageResource(R.drawable.musica2);
        Intent miReproductor = new Intent(getActivity(), ServicioMusica.class);
        getActivity().startService(miReproductor);
        encendida = !encendida;
    }


    public void apagaMusica(){
        botonMusica.setImageResource(R.drawable.musica);
        Intent miReproductor = new Intent(getActivity(), ServicioMusica.class);
        getActivity().stopService(miReproductor);
        encendida = !encendida;
    }





}
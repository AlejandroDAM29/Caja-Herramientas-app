package com.example.cajaherramientas;

import android.app.Activity;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple (@link Fragment) subclass.
 */
public class Menu extends Fragment {

private final int[] BOTONESMENU={R.id.linterna,R.id.musica,R.id.nivel};
//Para el botón iluminado
private final int[] BOTONESILUMINADOS={R.drawable.linterna2,R.drawable.musica2,R.drawable.nivel2};
//Variable para almacenar el boton pulsado
private int boton;

    public Menu(){
        //Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View mimenu = inflater.inflate(R.layout.fragment_menu, container, false);

      boton=-1;

      if(getArguments()!=null)
      boton = getArguments().getInt("BOTONPULSADO");

      ImageButton botonmenu;

      for(int i=0;i<BOTONESMENU.length;i++){

          botonmenu = (ImageButton) mimenu.findViewById(BOTONESMENU[i]);
          //Esto es para sobreescribir el botón creado por el botón iluminado
          if(boton==i){
              botonmenu.setImageResource(BOTONESILUMINADOS[i]);
          }

          //A cada vuelta de bucle la i será un botón distinto
          final int queboton = i;
          botonmenu.setOnClickListener(new View.OnClickListener(){

              @Override
              public void onClick(View v) {
                    //Así veo en qué actividad está este boton
                  Activity estaActividad = getActivity();
                  /*Para saber qué botón he pulsado. Se pone ComunicaMenu entre los paréntesis
                  porque el método menú pertenece a esa interfaz. Es hacer un custing
                   */
                  ((ComunicaMenu)estaActividad).menu(queboton);


              }
          });//Fin de click listener


      }//Fin del menú onCreate

      return mimenu;
    }
}
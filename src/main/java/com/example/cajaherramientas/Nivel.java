package com.example.cajaherramientas;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Nivel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Nivel extends Fragment implements SensorEventListener {

    //Parámetros manuales
    private SensorManager miManager;
    private Sensor miSensor;
    private NivelPantalla pantalla;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public Nivel() {
    }


    public static Nivel newInstance(String param1, String param2) {
        Nivel fragment = new Nivel();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            miManager=(SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
            miSensor=miManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            int lado=getResources().getDimensionPixelSize(R.dimen.maximo);
            pantalla = new NivelPantalla(getActivity(),lado);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Esto es para que la orientación del teléfono no cambie en esta actividad
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return pantalla;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        pantalla.angulos(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onResume(){
        super.onResume();
        miManager.registerListener(this,miSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause(){
        super.onPause();
        miManager.registerListener(this,miSensor,SensorManager.SENSOR_DELAY_GAME);
    }









}
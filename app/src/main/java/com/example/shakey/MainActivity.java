package com.example.shakey;

import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;
import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sn = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        TextView text = findViewById(R.id.text1);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragmentContainerView, BlankFragment2.class, null)
                .setReorderingAllowed(true)
                .commit();


        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float sensor = sensorEvent.values[0];
                float sensorSecond = sensorEvent.values[1];
                float sensorThird = sensorEvent.values[2];
                int sensor1 = (int) sensor;
                int sensor2 = (int) sensorSecond;
                int sensor3 = (int) sensorThird;

                text.setText("Rounded values: " + "\n" + sensor1 + "\n" + sensor2 + "\n" + sensor3 + "\n" + "Real time values: " + "\n" + sensor + "\n" + sensorSecond + "\n" + sensorThird);



                if(sensor1 != 0 && sensor2 != 0 && sensor2 != 00) {


                    fm.beginTransaction().replace(R.id.fragmentContainerView, BlankFragment.class, null).commit();



                }else{
                    fm.beginTransaction().replace(R.id.fragmentContainerView, BlankFragment2.class, null).commit();



                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


       // sm.registerListener(listener, sn, 15000000); //gravity
        sensorManager.registerListener(listener, sensor, 1500); //linear accell
    }
}
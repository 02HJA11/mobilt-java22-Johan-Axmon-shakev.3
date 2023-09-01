package com.example.shakey;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        TextView text = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.textView);

        ImageButton ib = findViewById(R.id.imageButton);
        ImageView iv = findViewById(R.id.imageView2);


        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragmentContainerView, BlankFragment2.class, null)
                .setReorderingAllowed(true)
                .commit();
        MediaPlayer sound = MediaPlayer.create(this, R.raw.drive);


        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float sensor = sensorEvent.values[0];
                float sensorSecond = sensorEvent.values[1];
                float sensorThird = sensorEvent.values[2];
                int sensor1 = (int) sensor;
                int sensor2 = (int) sensorSecond;
                int sensor3 = (int) sensorThird;
                int ryan = (int) iv.getAlpha();
                text2.setText("" + ryan);

                text.setText("Rounded values: " + "\n" + sensor1 + "\n" + sensor2 + "\n" + sensor3 + "\n" + "Real time values: " + "\n" + sensor + "\n" + sensorSecond + "\n" + sensorThird);
                ib.setRotation(sensor);
                ib.setRotationX(sensorSecond);
                ib.setRotationY(sensorThird);
                iv.setAlpha(sensorSecond + sensorSecond + sensorThird);
                String sensortotext = String.valueOf(sensor);
                Log.d("sensorChange", sensortotext);

                if (ryan <= -50 || ryan >= 50) {
                    sound.start();
                    text2.setText("trigger");
                }
                if(sensor1 != 0 && sensor2 != 0 && sensor3 != 00) {


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